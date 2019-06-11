package com.github.sigrist.spotify;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Custom exception when trying to get a field from the JSON return and it does
 * not exist.
 * @author sigrist
 * @since 1.0.0
 */
public class FieldNotFoundException extends SpotifyAPIException {

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1583001111402261727L;

    /**
     * The original JSON object.
     */
    private final JsonNode json;

    /**
     * Full constructor.
     * @param fieldName The field name to get.
     * @param json The original JSON response from the API.
     */
    public FieldNotFoundException(final String fieldName, final JsonNode json) {
        super("The field '" + fieldName + "' does not exist.");
        this.json = json;
    }

    /**
     * Get the JSON object.
     * @return The JSON response from the API.
     */
    public final JsonNode getJson() {
        return json;
    }

}
