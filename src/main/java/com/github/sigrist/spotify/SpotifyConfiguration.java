package com.github.sigrist.spotify;

import java.util.Map;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;

@Builder
public class SpotifyConfiguration {

	@Singular("configuration")
	private Map<String, String> configuration;

	@NonNull
	private String token;

	public final String get(@NonNull final String key, @NonNull final String defaultValue) {
		return this.configuration.getOrDefault(key, defaultValue);
	}
}
