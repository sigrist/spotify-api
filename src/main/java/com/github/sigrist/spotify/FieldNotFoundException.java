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
package com.github.sigrist.spotify;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Custom exception when trying to get a field from the JSON return and it does
 * not exist.
 * @since 1.0.0
 */
public class FieldNotFoundException extends SpotifyApiException {

    /**
     * The serial version UID.
     */
    private static final long serialVersionUID = 1583001111402261727L;

    /**
     * The original JSON object.
     */
    private final transient JsonNode json;

    /**
     * The field name.
     */
    private final String field;

    /**
     * Full constructor.
     * @param field The field name to get.
     * @param json The original JSON response from the API.
     */
    public FieldNotFoundException(final String field, final JsonNode json) {
        super("The field '" + field + "' does not exist.");
        this.field = field;
        this.json = json;
    }

    /**
     * Get the JSON object.
     * @return The JSON response from the API.
     */
    public final JsonNode getJson() {
        return this.json;
    }

    /**
     * Get the field name.
     * @return The field name.
     */
    public String getField() {
        return this.field;
    }
}
