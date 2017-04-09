package com.developer4droid.swipegallery.events;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 09.04.2017
 * Time: 11:01
 */

public class SwipeImageEvent {

	public static final int RIGHT = 0;
	public static final int LEFT = 1;
	private String label;

	private int direction;

	public SwipeImageEvent(int direction) {
		this.direction = direction;
	}

	public static SwipeImageEvent createRightEvent(String label) {
		SwipeImageEvent event = new SwipeImageEvent(RIGHT);
		event.label = label;
		return event;
	}

	public static SwipeImageEvent createLeftEvent(String label) {
		SwipeImageEvent event = new SwipeImageEvent(LEFT);
		event.label = label;
		return event;
	}

	public int getDirection() {
		return direction;
	}

	public String getLabel() {
		return label;
	}
}
