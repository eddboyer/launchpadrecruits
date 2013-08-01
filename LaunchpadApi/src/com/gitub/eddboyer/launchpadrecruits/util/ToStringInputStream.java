package com.gitub.eddboyer.launchpadrecruits.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Wrapper input stream to make it easy to print out an input stream once it's been read.
 * 
 * @author Edd Boyer
 */
public class ToStringInputStream extends InputStream {

	private InputStream wrapped;
	private ByteArrayOutputStream bout = new ByteArrayOutputStream();

	/**
	 * Constructor.
	 * 
	 * @param wrapped
	 *            input stream to wrap
	 */
	public ToStringInputStream(InputStream wrapped) {
		this.wrapped = wrapped;
	}

	/**
	 * Overrides superclass method.
	 * <p>
	 * From overridden method javadoc:
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public int read() throws IOException {
		int read = wrapped.read();
		bout.write(read);
		return read;
	}

	@Override
	public void close() throws IOException {
		wrapped.close();
		bout.close();
		super.close();
	}

	/**
	 * Overrides superclass method.
	 * <p>
	 * From overridden method javadoc:
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return new String(bout.toByteArray(), Charset.forName("UTF8"));
	}

}
