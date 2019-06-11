package com.github.sigrist.spotify;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class SpotifyServerMock {

    public void me(WireMockRule rule) {
        rule.stubFor(get(urlEqualTo("/v1/me"))
            // .withHeader("Accept", equalTo("application/json"))
            // .withHeader("Content-Type", equalTo("application/json"))
            // .withHeader("Authorization", equalTo("Bearer xyz"))
            .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json")
                .withBody(new Resource("me/me.json").toString())));
    }

    public void verifyMe(final WireMockRule rule) {
        rule.verify(1, getRequestedFor(urlMatching("/v1/me"))
        // .withHeader("Content-Type", equalTo("application/json"))
        );
    }

    public void mePlaylists020(WireMockRule rule) {
        rule.stubFor(get(urlEqualTo("/v1/me/playlists"))
            // .withHeader("Accept", equalTo("application/json"))
            // .withHeader("Content-Type", equalTo("application/json"))
            // .withHeader("Authorization", equalTo("Bearer xyz"))
            .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json")
                .withBody(new Resource("me/me-playlists-0-20.json").toString())));
    }

    public void verifyMePlaylists020(final WireMockRule rule) {
        rule.verify(getRequestedFor(urlMatching("/v1/me/playlists"))
        // .withHeader("Content-Type", equalTo("application/json"))
        );
    }

}
