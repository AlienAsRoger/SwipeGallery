package com.developer4droid.swipegallery.events;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 09.04.2017
 * Time: 11:46
 */

public class CancelLongPressEvent {

	/**
	 * Prevent loop interaction
	 */
	private boolean fromDialog;
	private String label = "";

	public CancelLongPressEvent(String label) {
		this.label = label;
		this.fromDialog = false;
	}

	public CancelLongPressEvent(boolean fromDialog) {
		this.fromDialog = fromDialog;
	}

	public static CancelLongPressEvent fromInside() {
		return new CancelLongPressEvent(true);
	}

	public boolean isFromDialog() {
		return fromDialog;
	}

	public String getLabel() {
		return label;
	}
}
