package com.github.sigrist.spotify;

import java.time.LocalDate;

public interface Me {

	LocalDate birthDate();
	String country();
	String displayName();
	String email();
	
	// @todo #7 Add other fields.
	
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
