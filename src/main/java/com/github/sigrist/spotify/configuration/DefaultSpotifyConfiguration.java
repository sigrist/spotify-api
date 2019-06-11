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
package com.github.sigrist.spotify.configuration;

import com.github.sigrist.spotify.SpotifyConfiguration;

/**
 * Default configuration.
 * @since 1.0.0
 */
public final class DefaultSpotifyConfiguration implements SpotifyConfiguration {

    /**
     * The user token to access the API.
     */
    private final String token;

    /**
     * Default constructor.
     * @param token The user token to access the API.
     */
    public DefaultSpotifyConfiguration(final String token) {
        this.token = token;
    }

    /**
     * Returns the default value.
     * @param key The configuration key.
     * @param def The default value if not found.
     * @return Always the defaultValue
     */
    @Override
    public String get(final String key, final String def) {
        return def;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public String getServer() {
        return "https://api.spotify.com";
    }

}
