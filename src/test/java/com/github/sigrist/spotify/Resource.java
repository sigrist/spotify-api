package com.github.sigrist.spotify;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

public class Resource {

	private final String resource;

	public Resource(final String resource) {
		this.resource = resource;
	}

	@Override
	public String toString() {
		try {
			final URL url = this.getClass().getResource(resource);
			return IOUtils.toString(url, Charset.defaultCharset());
		} catch (IOException e) {
			throw new RuntimeException("Error loading resource", e);
		}
	}
}
