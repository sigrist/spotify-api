package com.github.sigrist.spotify;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;

public class FieldNotFoundException extends SpotifyAPIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1583001111402261727L;
	@Getter
	private final JsonNode json;

	public FieldNotFoundException(final String fieldName, final JsonNode json) {
		super("The field '' does not exist.");
		this.json = json;
	}

}
