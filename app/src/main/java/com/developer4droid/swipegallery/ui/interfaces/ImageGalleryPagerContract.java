package com.developer4droid.swipegallery.ui.interfaces;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 09.04.2017
 * Time: 11:15
 */

public interface ImageGalleryPagerContract extends ImageGalleryContract {

	interface ViewFrame extends ImageGalleryContract.ViewFrame{

		void setPage(int position);

		void dismissScreen();
	}
}
