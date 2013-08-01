package com.gitub.eddboyer.launchpadrecruits.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Wrapper reader to make it easy to print out an input stream once it's been read.
 * 
 * @author Edd Boyer
 */
public class ToStringReader extends Reader {

	private Reader wrapped;
	private Writer writer = new StringWriter();

	/**
	 * Constructor.
	 * 
	 * @param wrapped
	 *            input stream to wrap
	 */
	public ToStringReader(Reader wrapped) {
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
		writer.write(read);
		System.out.println("Reading " + read);
		return read;
	}

	/**
	 * Overrides superclass method.
	 * <p>
	 * From overridden method javadoc:
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		int read = wrapped.read(cbuf, off, len);
		for (char c : cbuf) {
			writer.write(c);
		}
		return read;
	}

	@Override
	public void close() throws IOException {
		wrapped.close();
		writer.close();
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
		return writer.toString();
	}

}
