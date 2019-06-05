package com.github.sigrist.spotify;

import java.util.HashMap;
import java.util.Map;

/**
 * @todo remove builder to construct the SpotifyCOnfiguration
 * @author sigrist
 *
 */
public class SpotifyConfiguration {

	private final Map<String, String> configuration;

	private final String token;
	
	private final String server;

	private SpotifyConfiguration(final String token, final String server, final Map<String, String> configuration) {
		this.token = token;
		this.server = server;
		this.configuration = configuration;
	}

	public final String get(final String key, final String defaultValue) {
		return this.configuration.getOrDefault(key, defaultValue);
	}

	public static SpotifyConfigurationBuilder builder() {
		return new SpotifyConfigurationBuilder();
	}

	public String getToken() {
		return token;
	}
	
	public String getServer() {
		return server;
	}

	public static class SpotifyConfigurationBuilder {
		private String token;
		private String server;
		private Map<String, String> configuration = new HashMap<>();

		public final SpotifyConfigurationBuilder token(final String token) {
			this.token = token;
			return this;
		}

		public final SpotifyConfigurationBuilder server(final String server) {
			this.server = server;
			return this;
		}

		public final SpotifyConfigurationBuilder configuration(Map<String, String> configuration) {
			this.configuration.putAll(configuration);
			return this;
		}

		public final SpotifyConfigurationBuilder configuration(String key, String value) {
			this.configuration.put(key, value);
			return this;
		}

		public final SpotifyConfiguration build() {
			return new SpotifyConfiguration(token, server, configuration);
		}
	}
}
