package com.github.sigrist.spotify.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.sigrist.spotify.Playlist;
import com.github.sigrist.spotify.impl.proxy.Lazy;
import com.github.sigrist.spotify.impl.proxy.SpotifyProxy;

import lombok.NonNull;

public class PlaylistJsonImpl extends AbstractSpotifyObject implements Playlist {

	private final JsonNode json;

	private final SpotifyInternal spotify;

	public static Playlist instance(final SpotifyInternal spotify, final JsonNode json) {
		return new SpotifyProxy<PlaylistJsonImpl>(new PlaylistJsonImpl(spotify, json)).proxy();
	}

	private PlaylistJsonImpl(@NonNull final SpotifyInternal spotify, final @NonNull JsonNode json) {
		this.spotify = spotify;
		this.json = json;
	}

	@Override
	protected JsonNode load() {
		return this.json;
	}

	@Override
	@Lazy
	public Boolean collaborative() {
		return asBoolean("collaborative");
	}
	
	@Override
	@Lazy
	public String name() {
		return asText("name");
	}
	
}
