package com.github.sigrist.spotify;

import java.io.IOException;
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
			return IOUtils.resourceToString(resource, Charset.defaultCharset());
		} catch (IOException e) {
			throw new RuntimeException("Error loading resource", e);
		}
	}
}
