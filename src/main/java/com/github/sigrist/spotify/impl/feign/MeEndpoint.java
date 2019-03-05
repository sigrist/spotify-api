package com.github.sigrist.spotify.impl.feign;
import com.fasterxml.jackson.databind.JsonNode;

import feign.RequestLine;

public interface MeEndpoint {

	@RequestLine("GET /v1/me")
	JsonNode me();
}
