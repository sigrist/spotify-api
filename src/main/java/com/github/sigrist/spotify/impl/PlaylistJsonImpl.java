package com.github.sigrist.spotify.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.sigrist.spotify.Playlist;

public class PlaylistJsonImpl extends AbstractSpotifyObject implements Playlist {

	private final JsonNode json;

	private final SpotifyInternal spotify;

	public PlaylistJsonImpl(final SpotifyInternal spotify, final JsonNode json) {
		this.spotify = spotify;
		this.json = json;
	}

	@Override
	protected JsonNode load() {
		return this.json;
	}

	@Override
	public Boolean collaborative() {
		return asBoolean("collaborative");
	}

	@Override
	public String name() {
		return asText("name");
	}

}
