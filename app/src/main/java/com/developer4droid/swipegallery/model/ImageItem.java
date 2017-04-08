package com.developer4droid.swipegallery.model;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 07.04.2017
 * Time: 20:52
 */

public class ImageItem {
	private String label;
	private boolean stub;
	private String imageUri;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public static ImageItem createStub() {
		ImageItem imageItem = new ImageItem();
		imageItem.setStub(true);
		return imageItem;
	}

	public void setStub(boolean stub) {
		this.stub = stub;
	}

	public boolean isStub() {
		return stub;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}
}
