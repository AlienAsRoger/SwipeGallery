package com.developer4droid.swipegallery.interfaces;

import com.developer4droid.swipegallery.model.AlbumItem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 07.04.2017
 * Time: 21:22
 */

public interface AlbumLoadListener {

	void onDataLoaded(List<AlbumItem> itemList);
}
