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
package com.github.sigrist.spotify;

import com.github.sigrist.spotify.impl.MeImpl;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.time.LocalDate;
import java.util.Iterator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@link MeImpl}.
 * @since 1.0.0
 */
@DisplayName("Test Me implementation")
public class MeTest {
    /**
     * The mock that simulates the API.
     */
    private final WireMockRule rule = new WireMockRule(8089);

    /**
     * The server mock that instruments the mocks.
     */
    private final SpotifyServerMock mock = new SpotifyServerMock();

    /**
     * The {@link Spotify} instance to execute the tests.
     */
    private Spotify spotify;

    /**
     * Initialize the {@link #spotify} instance and start the {@link #rule}.
     */
    @BeforeEach
    public void init() {
        final SpotifyConfiguration conf = new UnitTestSpotifyConfiguration();
        this.spotify = Spotify.instance(conf);
        this.rule.start();
    }

    /**
     * Stop the {@link #rule}.
     */
    @AfterEach
    public void after() {
        this.rule.stop();
    }

    @Test
    @DisplayName("Test if the me is not null")
    public void testMe() {
        this.mock.me(this.rule);
        final Me user = this.spotify.user();
        Assertions.assertNotNull(user, "Me instance should be not null");
        Assertions.assertEquals("BR", user.country(), "Country is invalid");
        Assertions.assertEquals("Paulo Sigrist", user.displayName(), "DisplayName is invalid");
        Assertions.assertEquals("paulo.sigrist@gmail.com", user.email(), "Email is invalid");
        // CHECKSTYLE:OFF
        Assertions.assertEquals(LocalDate.of(1979, 5, 25), user.birthDate(),
            "Birth Date is invalid");
        // CHECKSTYLE:ON
        this.mock.verifyMe(this.rule);
    }

    @Test
    @DisplayName("Test if the me playlist is ok")
    @SuppressWarnings("checkstyle:MagicNumberCheck")
    public void testMePlaylist() {
        this.mock.mePlaylists020(this.rule);
        final Me user = this.spotify.user();
        final Playlists playlists = user.playlists();
        final Iterator<Playlist> iterator = playlists.iterator();
        Assertions.assertNotNull(user, "Me instance should be not null");
        Assertions.assertNotNull(playlists, "Playlists instance should be not null");
        Assertions.assertNotNull(iterator, "Iterator instance should be not null");
        Assertions.assertEquals(20, playlists.limit(), "Playlists limit is invalid");
        Assertions.assertEquals(0, playlists.offset(), "Playlists offset is invalid");
        Assertions.assertEquals(36, playlists.total(), "Playlists total is invalid");
        Assertions.assertTrue(iterator.hasNext(), "First iterator must be true");
        this.mock.verifyMePlaylists020(this.rule);
        Playlist playlist = iterator.next();
        Assertions.assertNotNull(playlist, "First playlist must be not null");
        Assertions.assertEquals("Dirty Rock", playlist.name());
        Assertions.assertFalse(playlist.collaborative());
        Assertions.assertTrue(iterator.hasNext());
        playlist = iterator.next();
        Assertions.assertNotNull(playlist);
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("blues", playlist.name());
        Assertions.assertFalse(playlist.collaborative());
        playlist = iterator.next();
        Assertions.assertNotNull(playlist);
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("Suits: All Seasons (Soundtrack)", playlist.name());
        Assertions.assertFalse(playlist.collaborative());
        playlist = iterator.next();
        Assertions.assertNotNull(playlist);
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("Mindhunter Soundtrack Season 2 (Netflix)", playlist.name());
        Assertions.assertFalse(playlist.collaborative());
        for (int index = 4; index < 18; index = index + 1) {
            playlist = iterator.next();
            Assertions.assertNotNull(playlist);
            Assertions.assertTrue(iterator.hasNext());
        }
        playlist = iterator.next();
        Assertions.assertNotNull(playlist);
        Assertions.assertFalse(iterator.hasNext());
    }
}
