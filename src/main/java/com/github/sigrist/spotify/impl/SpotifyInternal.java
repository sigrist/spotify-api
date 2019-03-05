package com.github.sigrist.spotify.impl;

import com.github.sigrist.spotify.Spotify;

public interface SpotifyInternal extends Spotify {

	<T> T build(Class<T> clazz);
}
