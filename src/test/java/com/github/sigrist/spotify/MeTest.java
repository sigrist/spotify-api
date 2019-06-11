package com.github.sigrist.spotify;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

@DisplayName("Test Me implementation")
public class MeTest {
	public WireMockRule wireMockRule = new WireMockRule(8089);

	private SpotifyServerMock spotifyServerMock = new SpotifyServerMock();
	private Spotify spotify;

	@BeforeEach
	public void init() {
		SpotifyConfiguration configuration = new UnitTestSpotifyConfiguration();
		spotify = Spotify.instance(configuration);

		this.wireMockRule.start();
	}

	@AfterEach
	public void after() {
		this.wireMockRule.stop();
	}

	@Test
	@DisplayName("Test if the me is not null")
	public void testMe() {
		// Simulate /me endpoint
		this.spotifyServerMock.me(wireMockRule);

		// get Me
		Me me = spotify.me();
		assertNotNull(me);

		assertEquals("BR", me.country());
		assertEquals("Paulo Sigrist", me.displayName());
		assertEquals("paulo.sigrist@gmail.com", me.email());
		assertEquals(LocalDate.of(1979, 5, 25), me.birthDate());
		this.spotifyServerMock.verifyMe(wireMockRule);

	}

	@Test
	@DisplayName("Test if the me playlist is ok")
	public void testMePlaylist() {
		// Simulate /me/playlists
		this.spotifyServerMock.mePlaylists020(wireMockRule);
		
		final Me me = spotify.me();
		final Playlists playlists = me.playlists();

		final Iterator<Playlist> iterator = playlists.iterator();
		
		assertNotNull(me);
		assertNotNull(playlists);
		assertNotNull(iterator);
		assertTrue(iterator.hasNext());
		assertEquals(20, playlists.limit());
		assertEquals(0, playlists.offset());
		assertEquals(36, playlists.total());
		this.spotifyServerMock.verifyMePlaylists020(wireMockRule);
		
		Playlist playlist = iterator.next();
		assertNotNull(playlist);
		assertTrue(iterator.hasNext());
		
		assertEquals("Dirty Rock", playlist.name());
		assertFalse(playlist.collaborative());

		// Item 2	
		playlist = iterator.next();
		assertNotNull(playlist);
		assertTrue(iterator.hasNext());
		
		assertEquals("blues", playlist.name());
		assertFalse(playlist.collaborative());

		// Item 3	
		playlist = iterator.next();
		assertNotNull(playlist);
		assertTrue(iterator.hasNext());
		              
		assertEquals("Suits: All Seasons (Soundtrack)", playlist.name());
		assertFalse(playlist.collaborative());

		// Item 4	
		playlist = iterator.next();
		assertNotNull(playlist);
		assertTrue(iterator.hasNext());
		              
		assertEquals("Mindhunter Soundtrack Season 2 (Netflix)", playlist.name());
		assertFalse(playlist.collaborative());

		// Check for all other items
		for (int i = 4; i<18; i++) {
			playlist = iterator.next();
			assertNotNull(playlist);
			assertTrue(iterator.hasNext());
		}
		playlist = iterator.next();
		assertNotNull(playlist);
		assertFalse(iterator.hasNext());

	}
}
