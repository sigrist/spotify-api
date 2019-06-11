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

import com.github.sigrist.spotify.Me;
import com.github.sigrist.spotify.Spotify;
import com.github.sigrist.spotify.SpotifyConfiguration;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

/**
 * Implementation for the {@link Spotify} and {@link SpotifyInternal}
 * interfaces.
 * @since 1.0.0
 */
public final class SpotifyImpl implements SpotifyInternal {

    /**
     * The configuration values to connect with the Spotify Web API.
     */
    private final SpotifyConfiguration conf;

    /**
     * The Feign Builder instance to create the feign instances.
     */
    private final Feign.Builder builder;

    /**
     * Default constructor.
     * @param configuration The configuration instance.
     */
    public SpotifyImpl(final SpotifyConfiguration configuration) {
        this.conf = configuration;
        this.builder = Feign.builder().decoder(new JacksonDecoder()).encoder(new JacksonEncoder());
    }

    @Override
    public <T> T build(final Class<T> clazz) {
        final String server = this.conf.getServer();
        return this.builder.target(clazz, server);
    }

    @Override
    public Me user() {
        return new MeImpl(this);
    }

    /**
     * Return the configuration instance.
     * @return The configuration instance.
     */
    public SpotifyConfiguration configuration() {
        return this.conf;
    }
}
