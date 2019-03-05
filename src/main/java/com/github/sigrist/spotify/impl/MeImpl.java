package com.github.sigrist.spotify.impl;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.sigrist.spotify.Me;
import com.github.sigrist.spotify.impl.feign.MeEndpoint;
import com.github.sigrist.spotify.impl.proxy.Lazy;
import com.github.sigrist.spotify.impl.proxy.SpotifyProxy;

public class MeImpl extends AbstractSpotifyObject implements Me {

	private final SpotifyInternal spotify;
	private final MeEndpoint meEndpoint;

	public static Me instance(final SpotifyInternal spotify) {
		return new SpotifyProxy<MeImpl>(new MeImpl(spotify)).proxy();
	}

	private MeImpl(final SpotifyInternal spotify) {
		this.spotify = spotify;
		this.meEndpoint = this.spotify.build(MeEndpoint.class);
	}

	@Override
	protected final JsonNode load() {
		return this.meEndpoint.me();
	}

	@Override
	@Lazy
	public final LocalDate birthDate() {
		return asLocalDate("birthdate");
	}

	@Override
	@Lazy
	public final String country() {
		return asText("country");
	}

	@Override
	@Lazy
	public final String displayName() {
		return asText("display_name");
	}

	@Override
	@Lazy
	public final String email() {
		return asText("email");
	}

}
