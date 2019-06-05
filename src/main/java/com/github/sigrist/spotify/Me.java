package com.github.sigrist.spotify;

import java.time.LocalDate;

public interface Me {

	LocalDate birthDate();
	String country();
	String displayName();
	String email();
	
	// @xtodo #7 Add other fields.
	
	// external URL
	// followers
	// href
	// id
	// images
	// product
	// type
	// uri.
	
	Playlists playlists();
}
