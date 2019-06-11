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
import com.github.sigrist.spotify.Playlists;
import com.github.sigrist.spotify.impl.feign.PlaylistEndpoint;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * {@link Playlists} to get the current user playlists.
 * @since 1.0.0
 */
public final class MePlaylistsImpl extends AbstractSpotifyObject implements Playlists {

    /**
     * The {@link SpotifyInternal} implementation.
     */
    private final SpotifyInternal spotify;

    /**
     * The {@link PlaylistEndpoint} implementation.
     */
    private final PlaylistEndpoint endpoint;

    /**
     * The list of {@link Playlist}.
     */
    private final List<Playlist> playlist;

    /**
     * Constructor.
     * @param spotify The {@link SpotifyInternal} implementation.
     */
    public MePlaylistsImpl(final SpotifyInternal spotify) {
        this.spotify = spotify;
        this.endpoint = this.spotify.build(PlaylistEndpoint.class);
        this.playlist = new LinkedList<>();
    }

    @Override
    public Iterator<Playlist> iterator() {
        final String field = "items";
        if (this.json().has(field)) {
            final JsonNode items = this.json().get(field);
            this.processItems(items);
        }
        return this.playlist.iterator();
    }

    @Override
    public Integer limit() {
        return this.asInteger("limit");
    }

    @Override
    public Integer offset() {
        return this.asInteger("offset");
    }

    @Override
    public Integer total() {
        return this.asInteger("total");
    }

    @Override
    public JsonNode load() {
        return this.endpoint.playlists();
    }

    // @todo #26 Aqui vai percorrer a paginacao sozinho.
    // @todo #26 Criar uma implementacao abstrata para isso.
    /**
     * Process the JsonNode item conterting it to a Playlist instance and add it to
     * {@link #playlist}.
     * @param items The JsonNone items.
     * @see PlaylistJsonImpl
     */
    private void processItems(final JsonNode items) {
        for (final JsonNode item : items) {
            this.playlist.add(new PlaylistJsonImpl(this.spotify, item));
        }
    }

}
