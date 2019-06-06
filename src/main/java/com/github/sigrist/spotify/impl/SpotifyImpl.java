package com.github.sigrist.spotify.impl;

import com.github.sigrist.spotify.Me;
import com.github.sigrist.spotify.SpotifyConfiguration;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class SpotifyImpl implements SpotifyInternal {

	private final SpotifyConfiguration configuration;

	private final Feign.Builder feignBuilder;

	public SpotifyImpl(final SpotifyConfiguration configuration) {
		this.configuration = configuration;
		this.feignBuilder = Feign.builder().decoder(new JacksonDecoder()).encoder(new JacksonEncoder());
	}

	@Override
	public <T> T build(Class<T> clazz) {
		final String server = this.configuration.getServer();
		return this.feignBuilder.target(clazz, server);
	}

	@Override
	public Me me() {
		return new MeImpl(this);
	}

	 public SpotifyConfiguration getConfiguration() {
		return configuration;
	}
}
