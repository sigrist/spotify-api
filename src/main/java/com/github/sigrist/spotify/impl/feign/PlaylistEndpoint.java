/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Paulo Sigrist
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.sigrist.spotify.impl.feign;

import com.fasterxml.jackson.databind.JsonNode;
import feign.Param;
import feign.RequestLine;

/**
 * Methods to access the playlists endpoints.
 * @since 1.0.0
 */
public interface PlaylistEndpoint {

    /**
     * Access the `GET /v1/me/playlists` endpoint.
     * @return The JSON content of the endpoint.
     */
    @RequestLine("GET /v1/me/playlists")
    JsonNode playlists();

    /**
     * Access the `GET /v1/me/playlists` endpoint wit parameters.
     * @param offset The index of the first playlist to return.
     * @param limit The maximum number of playlists to return.
     * @return The JSON content of the endpoint.
     */
    @RequestLine("GET /v1/me/playlists?offset={offset}&limit={limit}")
    JsonNode playlists(@Param("offset") Integer offset, @Param("limit") Integer limit);

}
