package com.github.sigrist.spotify.impl;

import com.github.sigrist.spotify.Me;
import com.github.sigrist.spotify.SpotifyConfiguration;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.Getter;
import lombok.NonNull;

public class SpotifyImpl implements SpotifyInternal {

	@Getter
	private final SpotifyConfiguration configuration;
	
	private final Feign.Builder feignBuilder;

	public SpotifyImpl(@NonNull final SpotifyConfiguration configuration) {
		this.configuration = configuration;
		this.feignBuilder = Feign.builder().decoder(new JacksonDecoder())
				.encoder(new JacksonEncoder());
	}
	
	@Override
	public <T> T build(Class<T> clazz) {
		final String server = this.configuration.get("server", ""); 
		return this.feignBuilder.target(clazz, server);
	}

	@Override
	public Me me() {
		return MeImpl.instance(this);
	}

}
