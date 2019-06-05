package com.github.sigrist.spotify;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration classe to create a Spotify Connection.
 * 
 * @author sigrist
 * 
 */
public class SpotifyConfiguration {
	/** A map of custom configurations. */
	private final Map<String, String> configuration;

	/** The user token. */
	private final String token;

	/** The server to be used. */
	// @todo #7:30m The server should be optional and must have the default server.
	private final String server;

	/**
	 * The constructor with all needed settings.
	 * 
	 * @param token         The user token
	 * @param server        The server URL
	 * @param configuration The custom configuration.
	 **/
	private SpotifyConfiguration(final String token, final String server, final Map<String, String> configuration) {
		this.token = token;
		this.server = server;
		this.configuration = configuration;
	}

	/**
	 * Get a configuration value or the default value if not found.
	 * 
	 * @param key          The configuration key.
	 * @param defaultValue The default value if not found.
	 * @return The value or the default value.
	 */
	public final String get(final String key, final String defaultValue) {
		return this.configuration.getOrDefault(key, defaultValue);
	}

	/**
	 * Returns the configuration builder.
	 * @return a {@link SpotifyConfigurationBuilder} instance.
	 */
	public static SpotifyConfigurationBuilder builder() {
		return new SpotifyConfigurationBuilder();
	}

	/**
	 * 
	 * @return the token.
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 
	 * @return the server.
	 */
	public String getServer() {
		return server;
	}

	/**
	 * The builder pattern to construct the {@link SpotifyConfiguration} class.
	 * 
	 * @todo #7 Replace this implementation to other pattern. Builders are not OO friendly.
	 * @author sigrist
	 *
	 */
	public static class SpotifyConfigurationBuilder {
		private String token;
		private String server ="https://api.spotify.com";
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
