package com.developer4droid.swipegallery.ui.interfaces;

import com.developer4droid.swipegallery.model.ImageItem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 12:02
 */

public interface ImagesLoadListener {

	void onDataLoaded(List<ImageItem> itemList);

}
