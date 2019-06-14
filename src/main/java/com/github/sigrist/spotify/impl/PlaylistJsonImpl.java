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
import com.github.sigrist.spotify.Playlist;
import com.github.sigrist.spotify.Spotify;
import com.github.sigrist.spotify.impl.feign.PlaylistEndpoint;

/**
 * {@link Playlist} implementation that uses {@link PlaylistEndpoint} to get
 * date from the Spotify API.
 * @since 1.0.0
 */
public final class PlaylistJsonImpl extends AbstractSpotifyObject implements Playlist {

    /**
     * The original json content.
     */
    private final JsonNode json;

    /**
     * Constructor.
     * @param spotify The {@link Spotify} instance.
     * @param json The Playlist JsonNode.
     */
    public PlaylistJsonImpl(final Spotify spotify, final JsonNode json) {
        super(spotify);
        this.json = json;
    }

    @Override
    public JsonNode load() {
        return this.json;
    }

    @Override
    public Boolean collaborative() {
        return this.asBoolean("collaborative");
    }

    @Override
    public String name() {
        return this.asText("name");
    }

}
