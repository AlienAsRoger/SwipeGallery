package com.developer4droid.swipegallery.interfaces;

import com.developer4droid.swipegallery.model.ImageItem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 07.04.2017
 * Time: 19:08
 */

public interface MainActivityContract {

	interface ViewFrame{

		void updateAdapter(List<ImageItem> itemList);
	}

	interface ActionListener{
		void onResume(ViewFrame viewFrame);
	}

}