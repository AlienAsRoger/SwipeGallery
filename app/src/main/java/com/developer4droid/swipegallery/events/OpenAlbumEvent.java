package com.developer4droid.swipegallery.events;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 11:34
 */

public class OpenAlbumEvent {
	private String albumName;

	public OpenAlbumEvent(String albumName) {
		this.albumName = albumName;
	}

	public String getAlbumName() {
		return albumName;
	}
}
