package com.developer4droid.swipegallery.swipegallery.interfaces;

import com.developer4droid.swipegallery.swipegallery.activity.entities.ImageItem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 07.04.2017
 * Time: 21:22
 */

public interface LoadListener {

	void onDataLoaded(List<ImageItem> itemList);
}
