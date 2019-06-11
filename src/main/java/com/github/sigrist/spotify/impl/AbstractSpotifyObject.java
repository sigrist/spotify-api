/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Paulo Sigrist
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.sigrist.spotify.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.sigrist.spotify.FieldNotFoundException;
import java.time.LocalDate;
import org.cactoos.scalar.Sticky;
import org.cactoos.scalar.Unchecked;

/**
 * Abstract class that handle the JSON returned by the Spotify WEB API and make
 * easy get get the field values.
 * @since 1.0.0
 */
public abstract class AbstractSpotifyObject {

    // @todo #21 Remove the Unchecked and do proper exception handling
    /**
     * The scalar to lazy load the JSON content.
     */
    private final Unchecked<JsonNode> scalar;

    /**
     * The {@link SpotifyInternal} implementation.
     */
    private final SpotifyInternal spotify;

    /**
     * Default constructor.
     * @param spotify The {@link SpotifyInternal} implementation.
     */
    public AbstractSpotifyObject(final SpotifyInternal spotify) {
        this.spotify = spotify;
        this.scalar = new Unchecked<>(new Sticky<>(() -> {
            return this.load();
        }));
    }

    /**
     * Method be implemented in the subclasses to get the JsonNode from the API.
     * @return The JsonNode from the API.
     */
    protected abstract JsonNode load();

    /**
     * Get the JSON content.
     * @return The JSON content.
     */
    protected final JsonNode json() {
        return this.scalar.value();
    }

    /**
     * Get a field value as text.
     * @param field The field name.
     * @return The field value as text.
     */
    protected final String asText(final String field) {
        return this.jsonNode(field).asText();
    }

    /**
     * Get a field value as {@link LocalDate}.
     * @param field The field name.
     * @return The field value as LocalDate.
     * @see LocalDate#parse(CharSequence)
     */
    protected final LocalDate asLocalDate(final String field) {
        final String value = this.asText(field);
        return LocalDate.parse(value);
    }

    /**
     * Get a field value as Integer.
     * @param field The field name.
     * @return The field value as Integer.
     */
    protected final Integer asInteger(final String field) {
        return this.jsonNode(field).asInt();
    }

    /**
     * Get a field value as Boolean.
     * @param field The field name.
     * @return The field value as Boolean.
     */
    protected final Boolean asBoolean(final String field) {
        return this.jsonNode(field).asBoolean();
    }

    /**
     * Get the {@link SpotifyInternal} instance.
     * @return The instance.
     */
    protected final SpotifyInternal getSpotify() {
        return this.spotify;
    }

    /**
     * Extracts the field from the original JSON node.
     * @param field The field name.
     * @return The field as JsonNode.
     * @throws FieldNotFoundException when the field does not exist.
     */
    private JsonNode jsonNode(final String field) {
        final JsonNode json = this.json();
        if (json != null && json.has(field)) {
            return json.get(field);
        } else {
            throw new FieldNotFoundException(field, json);
        }
    }

}
