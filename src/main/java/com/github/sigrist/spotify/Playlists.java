package com.github.sigrist.spotify;

public interface Playlists extends Iterable<Playlist> {
	Integer limit();
	Integer offset();
	Integer total();
}
