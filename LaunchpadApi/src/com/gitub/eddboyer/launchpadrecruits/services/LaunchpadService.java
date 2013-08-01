package com.gitub.eddboyer.launchpadrecruits.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

import com.gitub.eddboyer.launchpadrecruits.beans.LaunchpadApplicationStatusType;
import com.gitub.eddboyer.launchpadrecruits.beans.LaunchpadCompletedInterviewStateType;
import com.gitub.eddboyer.launchpadrecruits.exceptions.ServiceException;
import com.gitub.eddboyer.launchpadrecruits.util.ToStringInputStream;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.stream.JsonReader;

/**
 * Top level service class for LaunchPad Recruits api.
 * 
 * @author Edd Boyer
 */
public abstract class LaunchpadService {

	private static final String dateFormat = "yyyy-MM-dd";

	/**
	 * The base request url string with trailing slash e.g. "http://test.launchpadrecruits.com/1/".
	 */
	private final String requestBaseString;
	private final String username;

	/**
	 * Constructor.
	 * 
	 * @param requestBaseString
	 *            the base request url string for all api requests with trailing slash e.g.
	 *            "http://test.launchpadrecruits.com/1/"
	 * @param username
	 *            the username for the api access
	 */
	public LaunchpadService(String requestBaseString, String username) {
		this.requestBaseString = requestBaseString;
		this.username = username;
	}

	/**
	 * Sends a request of the specified type to the specified uri with the given data as a json
	 * object.
	 * 
	 * @param uri
	 *            the uri
	 * @param requestData
	 *            the data object (will be converted to json). Not required for GET requests
	 * @param requestType
	 *            the types expected: {@link HttpPost#METHOD_NAME}, {@link HttpGet#METHOD_NAME},
	 *            {@link HttpDelete#METHOD_NAME} or {@link HttpPut#METHOD_NAME}. Will default to GET
	 *            if not recognised
	 * @return the json element from the response
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	protected JsonElement getJsonElement(URI uri, Object requestData, String requestType) throws ServiceException {

		JsonElement jsonElement = null;

		HttpEntity entity = null;
		JsonReader reader = null;
		DefaultHttpClient httpclient = new DefaultHttpClient();

		try {
			httpclient.getCredentialsProvider().setCredentials(new AuthScope(uri.getHost(), uri.getPort()),
					new UsernamePasswordCredentials(getUsername(), ""));

			// Generate BASIC scheme object and add it to the local auth cache
			AuthCache authCache = new BasicAuthCache();
			BasicScheme basicAuth = new BasicScheme();
			authCache.put(URIUtils.extractHost(uri), basicAuth);

			// Add AuthCache to the execution context
			BasicHttpContext localcontext = new BasicHttpContext();
			localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);

			HttpRequestBase httpRequest;

			if (StringUtils.equals(requestType, HttpPost.METHOD_NAME)) {
				httpRequest = new HttpPost(uri);
			} else if (StringUtils.equals(requestType, HttpPut.METHOD_NAME)) {
				httpRequest = new HttpPut(uri);
			} else if (StringUtils.equals(requestType, HttpDelete.METHOD_NAME)) {
				httpRequest = new HttpDelete(uri);
			} else {
				httpRequest = new HttpGet(uri);
			}

//			Logger.getLogger().info("Request: " + httpRequest.getRequestLine());

			// add data to request body if required
			if ((httpRequest instanceof HttpEntityEnclosingRequestBase) && (requestData != null)) {

//				Logger.getLogger().info("Request data: " + getGson().toJson(requestData));

				HttpEntityEnclosingRequestBase httpEntityRequest = (HttpEntityEnclosingRequestBase) httpRequest;

				HttpEntity requestEntity = new StringEntity(getGson().toJson(requestData), ContentType.APPLICATION_JSON);
				httpEntityRequest.setEntity(requestEntity);
			}

			HttpResponse response = httpclient.execute(httpRequest, localcontext);
			entity = response.getEntity();

//			Logger.getLogger().info("----------------------------------------");
//			Logger.getLogger().info(response.getStatusLine().toString());
			if (entity != null) {
//				Logger.getLogger().info("Response content length: " + entity.getContentLength());

				ToStringInputStream in = new ToStringInputStream(entity.getContent());

				reader = new JsonReader(new InputStreamReader(in));
				JsonParser jsonParser = new JsonParser();
				jsonElement = jsonParser.parse(reader).getAsJsonObject();

//				Logger.getLogger().info("Response: " + in.toString());
			}

		} catch (FileNotFoundException e) {
			throw new ServiceException(e);
		} catch (MalformedURLException e) {
			throw new ServiceException(e);
		} catch (IOException e) {
			throw new ServiceException(e);
		} finally {
			IOUtils.closeQuietly(reader);

			try {
				EntityUtils.consume(entity);
			} catch (IOException e) {
				throw new ServiceException(e);
			}

			httpclient.getConnectionManager().shutdown();
		}

		return jsonElement;
	}

	/**
	 * Sends an http request to the specified uri of the specified request type without a body.
	 * 
	 * @param uri
	 *            the uri
	 * @param requestType
	 *            the type expected: {@link HttpPost#METHOD_NAME}, {@link HttpGet#METHOD_NAME},
	 *            {@link HttpDelete#METHOD_NAME} or {@link HttpPut#METHOD_NAME}. Will default to GET
	 *            if not recognised
	 * @return the json element from the response
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	protected JsonElement getJsonElement(URI uri, String requestType) throws ServiceException {

		return getJsonElement(uri, null, requestType);
	}

	/**
	 * Sends a GET request to the specified uri.
	 * 
	 * @param uri
	 *            the uri
	 * @return the json element from the response
	 * @throws ServiceException
	 *             a {@link ServiceException}
	 */
	protected JsonElement getJsonElement(URI uri) throws ServiceException {

		return getJsonElement(uri, null, HttpGet.METHOD_NAME);
	}

	/**
	 * @return the configured {@link Gson} object for use with LaunchPad Recruits api.
	 */
	protected Gson getGson() {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		gsonBuilder.registerTypeAdapter(LaunchpadCompletedInterviewStateType.class,
				new CompletedInterviewStateDeserializer());
		gsonBuilder.registerTypeAdapter(LaunchpadApplicationStatusType.class, new ApplicationStatusDeserializer());
		gsonBuilder.registerTypeAdapter(Date.class, new DateTypeDeserializer());

		gsonBuilder.setDateFormat(dateFormat);

		return gsonBuilder.create();
	}

	/**
	 * Temporary fix? to handle "deadline":"" in create interview response when deadline has not
	 * been set.
	 * 
	 * @author Edd Boyer
	 */
	private static class DateTypeDeserializer implements JsonDeserializer<Date> {

		private DateFormat df = new SimpleDateFormat(dateFormat);

		@Override
		public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
			try {
				if (StringUtils.isNotBlank(json.getAsString())) {
					return df.parse(json.getAsString());
				} else {
					return null;
				}
			} catch (ParseException e) {
				return null;
			}
		}
	}

	/**
	 * Adapter for deserialising {@link LaunchpadCompletedInterviewStateType} values.
	 * 
	 * @author Edd Boyer
	 */
	private static class CompletedInterviewStateDeserializer implements
			JsonDeserializer<LaunchpadCompletedInterviewStateType> {
		@Override
		public LaunchpadCompletedInterviewStateType deserialize(JsonElement element, Type typeOfT,
				JsonDeserializationContext context) {

			LaunchpadCompletedInterviewStateType[] stateTypes = LaunchpadCompletedInterviewStateType.values();
			for (LaunchpadCompletedInterviewStateType stateType : stateTypes) {
				if (stateType.getJsonValue().equals(element.getAsString())) {
					return stateType;
				}
			}
			return null;
		}
	}

	/**
	 * Adapter for deserialising {@link LaunchpadApplicationStatusType} values.
	 * 
	 * @author Edd Boyer
	 */
	private static class ApplicationStatusDeserializer implements JsonDeserializer<LaunchpadApplicationStatusType>,
			JsonSerializer<LaunchpadApplicationStatusType> {
		@Override
		public LaunchpadApplicationStatusType deserialize(JsonElement element, Type typeOfT,
				JsonDeserializationContext context) {

			LaunchpadApplicationStatusType[] statusTypes = LaunchpadApplicationStatusType.values();
			for (LaunchpadApplicationStatusType statusType : statusTypes) {
				if (statusType.getJsonValue().equals(element.getAsString())) {
					return statusType;
				}
			}
			return null;
		}

		@Override
		public JsonElement serialize(LaunchpadApplicationStatusType status, Type typeOfT,
				JsonSerializationContext context) {

			if (status != null) {
				return new JsonPrimitive(status.getJsonValue());
			}

			return null;
		}

	}

	/**
	 * @return the requestBaseString (has trailing slash)
	 */
	protected String getRequestBaseString() {
		return requestBaseString;
	}

	/**
	 * @return the username
	 */
	protected String getUsername() {
		return username;
	}

}
