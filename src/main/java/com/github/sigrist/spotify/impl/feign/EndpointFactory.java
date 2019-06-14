package com.github.sigrist.spotify.impl.feign;

import com.github.sigrist.spotify.Spotify;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class EndpointFactory {

    /**
     * The Feign Builder instance to create the feign instances.
     */
    private final Feign.Builder builder;

    /**
     * The {@link Spotify} instance to get the configuration;
     */
    private final Spotify spotify;

    public EndpointFactory(final Spotify spotify) {
        this.spotify = spotify;
        this.builder = Feign.builder().decoder(new JacksonDecoder()).encoder(new JacksonEncoder());

    }
    
    
    public <T> T build(final Class<T> clazz) {
        final String server = this.spotify.conf().getServer();
        return this.builder.target(clazz, server);
    }

}
