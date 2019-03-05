package com.github.sigrist.spotify.impl;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.sigrist.spotify.Me;
import com.github.sigrist.spotify.impl.feign.MeEndpoint;
import com.github.sigrist.spotify.impl.proxy.Lazy;
import com.github.sigrist.spotify.impl.proxy.LazyMethod;

public class MeImpl implements Me {

	private final SpotifyInternal spotify;
	private final MeEndpoint meEndpoint;

	private JsonNode json;

	public static Me instance(final SpotifyInternal spotify) {

		return new SpotifyProxy<MeImpl>(new MeImpl(spotify)).proxy();
	}

	private MeImpl(final SpotifyInternal spotify) {
		this.spotify = spotify;
		this.meEndpoint = this.spotify.build(MeEndpoint.class);

	}

	@LazyMethod
	public void load() {
		this.json = this.meEndpoint.me();
		System.out.println("load");

	}

	@Override
	@Lazy
	public LocalDate birthDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Lazy
	public String country() {
		// TODO Auto-generated method stub
		return json.get("country").asText();
	}

	@Override
	public String displayName() {
		// TODO Auto-generated method stub
		return json.get("display_name").asText();
	}

	@Override
	@Lazy
	public String email() {
		// TODO Auto-generated method stub
		return null;
	}

}
