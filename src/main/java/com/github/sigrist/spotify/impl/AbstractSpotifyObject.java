package com.github.sigrist.spotify.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.sigrist.spotify.FieldNotFoundException;
import java.time.LocalDate;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;

public abstract class AbstractSpotifyObject {

    // @todo #21 Remove the Unchecked and do proper exception handling
    private final Unchecked<JsonNode> scalar;

    public AbstractSpotifyObject() {
        this.scalar = new Unchecked<>(new Sticky<>(() -> {
            return load();
        }));
    }

    protected abstract JsonNode load();

    protected JsonNode json() {
        return this.scalar.value();
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
        final JsonNode json = this.json();

        if (json != null && json.has(fieldName)) {
            return json.get(fieldName);
        } else {
            throw new FieldNotFoundException(fieldName, json);
        }
    }
}
