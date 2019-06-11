package com.github.sigrist.spotify.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.sigrist.spotify.Playlist;
import com.github.sigrist.spotify.Playlists;
import com.github.sigrist.spotify.impl.feign.PlaylistEndpoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MePlaylistsImpl extends AbstractSpotifyObject implements Playlists {
    private final SpotifyInternal spotify;
    private final PlaylistEndpoint playlistEndpoint;
    private final List<Playlist> playlist;

    public MePlaylistsImpl(final SpotifyInternal spotify) {
        this.spotify = spotify;
        this.playlistEndpoint = this.spotify.build(PlaylistEndpoint.class);
        this.playlist = new ArrayList<>();
    }

    @Override
    public Iterator<Playlist> iterator() {
        if (this.json().has("items")) {
            final JsonNode items = this.json().get("items");
            processItems(items);
        }
        return this.playlist.iterator();
    }

    private void processItems(final JsonNode items) {
        // @xtodo #7 Aqui vai percorrer a paginacao sozinho.
        // @xtodo #7 Criar uma implementacao abstrata para isso.
        for (final JsonNode item : items) {
            this.playlist.add(new PlaylistJsonImpl(spotify, item));
        }
    }

    @Override
    protected JsonNode load() {
        return this.playlistEndpoint.playlists();
    }

    @Override
    public Integer limit() {
        return asInteger("limit");
    }

    @Override
    public Integer offset() {
        return asInteger("offset");
    }

    @Override
    public Integer total() {
        return asInteger("total");
    }

}
