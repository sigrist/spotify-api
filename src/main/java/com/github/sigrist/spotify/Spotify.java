package com.github.sigrist.spotify;

import com.github.sigrist.spotify.impl.SpotifyImpl;

public interface Spotify {

	public static Spotify instance(final SpotifyConfiguration configuration) {
		return new SpotifyImpl(configuration);
	}

	public Me me();
}
