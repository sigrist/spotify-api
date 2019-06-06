module com.github.sigrist.spotify {
	exports com.github.sigrist.spotify;
	
	requires transitive com.fasterxml.jackson.core;
	requires transitive com.fasterxml.jackson.databind;
	
	requires feign.core;
	requires feign.jackson;
	
	requires org.apache.commons.lang3;
	
	
}