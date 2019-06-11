package com.github.sigrist.spotify.impl;

import java.time.LocalDate;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.sigrist.spotify.Me;
import com.github.sigrist.spotify.Playlists;
import com.github.sigrist.spotify.impl.feign.MeEndpoint;

public class MeImpl extends AbstractSpotifyObject implements Me {

    private final SpotifyInternal spotify;
    private final MeEndpoint meEndpoint;

    public MeImpl(final SpotifyInternal spotify) {
        this.spotify = spotify;
        this.meEndpoint = this.spotify.build(MeEndpoint.class);
    }

    @Override
    protected final JsonNode load() {
        return this.meEndpoint.me();
    }

    @Override
    public final LocalDate birthDate() {
        return asLocalDate("birthdate");
    }

    @Override
    public final String country() {
        return asText("country");
    }

    @Override
    public final String displayName() {
        return asText("display_name");
    }

    @Override
    public final String email() {
        return asText("email");
    }

    @Override
    public final Playlists playlists() {
        return new MePlaylistsImpl(this.spotify);
    }

}
