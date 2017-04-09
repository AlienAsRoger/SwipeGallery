package com.developer4droid.swipegallery.events;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 20:09
 */

public class LongPressImageViewEvent {
	private String label;

	public LongPressImageViewEvent(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
