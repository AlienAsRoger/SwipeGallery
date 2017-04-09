package com.developer4droid.swipegallery.events;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 19:37
 */

public class OpenAlbumPreviewEvent {

	private String albumName;

	public OpenAlbumPreviewEvent(String albumName) {
		this.albumName = albumName;
	}

	public String getAlbumName() {
		return albumName;
	}

}
