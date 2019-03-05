package com.github.sigrist.spotify.impl.feign;

import com.fasterxml.jackson.databind.JsonNode;

import feign.Param;
import feign.RequestLine;

public interface PlaylistEndpoint {

	@RequestLine("GET /v1/me/playlists")
	JsonNode playlists();

	@RequestLine("GET /v1/me/playlists?offset={offset}&limit={limit}")
	JsonNode playlists(@Param("offset") Integer offset, @Param("limit") Integer limit);

}
