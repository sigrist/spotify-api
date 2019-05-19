package com.github.sigrist.spotify;

import com.fasterxml.jackson.databind.JsonNode;

public class FieldNotFoundException extends SpotifyAPIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1583001111402261727L;
	private final JsonNode json;

	public FieldNotFoundException(final String fieldName, final JsonNode json) {
		super("The field '" + fieldName + "' does not exist.");
		this.json = json;
	}
	
	public final JsonNode getJson() {
		return json;
	}

}
