package com.github.sigrist.spotify.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.sigrist.spotify.Playlist;
import com.github.sigrist.spotify.Playlists;
import com.github.sigrist.spotify.impl.feign.PlaylistEndpoint;
import com.github.sigrist.spotify.impl.proxy.Lazy;
import com.github.sigrist.spotify.impl.proxy.SpotifyProxy;

public class MePlaylistsImpl extends AbstractSpotifyObject implements Playlists {
	private final SpotifyInternal spotify;
	private final PlaylistEndpoint playlistEndpoint;

	public static Playlists instance(final SpotifyInternal spotify) {
		return new SpotifyProxy<MePlaylistsImpl>(new MePlaylistsImpl(spotify)).proxy();
	}

	private MePlaylistsImpl(final SpotifyInternal spotify) {
		this.spotify = spotify;
		this.playlistEndpoint = this.spotify.build(PlaylistEndpoint.class);
	}

	@Lazy
	@Override
	public Iterator<Playlist> iterator() {
		if (this.json().has("items")) {
			final JsonNode items = this.json().get("items");
			return processItems(items).iterator();
		}
		return Collections.emptyIterator();
	}

	private List<Playlist> processItems( final JsonNode items) {
		// @todo #7 Aqui vai percorrer a paginacao sozinho.
		// @todo #7 Criar uma implementacao abstrata para isso.
		
		final List<Playlist> list = new ArrayList<>();
		for (final JsonNode item : items) {
			list.add(PlaylistJsonImpl.instance(spotify, item));
		}

		return list;

	}

	@Override
	protected JsonNode load() {
		return this.playlistEndpoint.playlists();
	}

	@Lazy
	@Override
	public Integer limit() {
		return asInteger("limit");
	}

	@Lazy
	@Override
	public Integer offset() {
		return asInteger("offset");
	}

	@Lazy
	@Override
	public Integer total() {
		return asInteger("total");
	}

}
