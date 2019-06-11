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
import com.github.sigrist.spotify.Me;
import com.github.sigrist.spotify.Playlists;
import com.github.sigrist.spotify.impl.feign.MeEndpoint;
import java.time.LocalDate;

/**
 * {@link Me} implementation that uses {@link MeEndpoint} to get data from
 * Spotify API.
 * @since 1.0.0
 */
public class MeImpl extends AbstractSpotifyObject implements Me {

    /**
     * The {@link MeEndpoint} instance.
     */
    private final MeEndpoint endpoint;

    /**
     * Default constructor. Initialize the endpoint.
     * @param spotify The {@link SpotifyInternal} instance.
     */
    public MeImpl(final SpotifyInternal spotify) {
        super(spotify);
        this.endpoint = this.getSpotify().build(MeEndpoint.class);
    }

    @Override
    public final LocalDate birthDate() {
        return this.asLocalDate("birthdate");
    }

    @Override
    public final String country() {
        return this.asText("country");
    }

    @Override
    public final String displayName() {
        return this.asText("display_name");
    }

    @Override
    public final String email() {
        return this.asText("email");
    }

    @Override
    public final Playlists playlists() {
        return new MePlaylistsImpl(this.getSpotify());
    }

    @Override
    protected final JsonNode load() {
        return this.endpoint.user();
    }

}
