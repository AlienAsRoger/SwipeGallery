package com.developer4droid.swipegallery.model;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 10:22
 */

import java.util.List;

/**
 * Contains information about images in this album
 */
public class AlbumItem {
	
	private List<ImageItem> imageList;
	private String name;
	private String imageUri;

	public AlbumItem(String name) {
		this.name = name;
	}

	public List<ImageItem> getImageList() {
		return imageList;
	}

	public void setImageList(List<ImageItem> imageList) {
		this.imageList = imageList;
	}

	public String getName() {
		return name;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}
}
