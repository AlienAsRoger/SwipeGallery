package com.developer4droid.swipegallery.ui.interfaces;

import com.developer4droid.swipegallery.model.ImageItem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 11:56
 */

public interface ImageGalleryContract {

	interface ViewFrame {
		void updateAdapter(List<ImageItem> itemList);
	}

	interface ActionListener {
		void onResume(ViewFrame viewFrame);
	}

}
