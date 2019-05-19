package com.github.sigrist.spotify.impl;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.sigrist.spotify.FieldNotFoundException;
import com.github.sigrist.spotify.impl.proxy.LazyMethod;

public abstract class AbstractSpotifyObject {

	private JsonNode json;

	protected abstract JsonNode load();

	@LazyMethod
	public void loadJson() {
		this.json = load();
	}
	
	protected JsonNode json() {
		return json;
	}

	protected final String asText(final String fieldName) {
		return jsonNode(fieldName).asText();
	}
	
	protected final LocalDate asLocalDate(final String fieldName) {
		final String value = asText(fieldName);
		
		return LocalDate.parse(value);
	}
	
	protected final Integer asInteger(final String fieldName) {
		return jsonNode(fieldName).asInt();
	}

	protected final Boolean asBoolean(final String fieldName) {
		return jsonNode(fieldName).asBoolean();
	}

	private JsonNode jsonNode(final String fieldName) {
		if (json != null && json.has(fieldName)) {
			return json.get(fieldName);
		} else
			throw new FieldNotFoundException(fieldName, json);
	}
}
