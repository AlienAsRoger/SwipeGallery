package com.developer4droid.swipegallery.ui.viewmodel;

import android.util.Log;
import com.developer4droid.swipegallery.events.CancelLongPressEvent;
import com.developer4droid.swipegallery.events.SwipeImageEvent;
import com.developer4droid.swipegallery.ui.interfaces.ImageGalleryPagerContract;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 09.04.2017
 * Time: 11:13
 */

public class ImageGalleryPagerViewModel extends ImageGalleryViewModel {

	private int currentPosition;

	public ImageGalleryPagerViewModel(String albumName) {
		super(albumName);
	}

	public void onResume(ImageGalleryPagerContract.ViewFrame viewFrame) {
		super.onResume(viewFrame);
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

	// --------- //
	// Event Bus //
	// --------- //

	@SuppressWarnings("unused")
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onEvent(SwipeImageEvent event) {
		if (!event.getLabel().equals(getAlbumName())) {
			return;
		}
		Log.d("TEST", "onEvent: album = " + getAlbumName());

		if (event.getDirection() == SwipeImageEvent.LEFT) {
			currentPosition++;
		} else {
			currentPosition--;
		}

		// drop counter if out of bounds
		if (currentPosition < 0) {
			currentPosition = 0;
		} else if (currentPosition >= itemList.size()) {
			currentPosition = itemList.size() - 1;
		}

		Log.d("TEST", "onEvent: cur pos = " + currentPosition);
		((ImageGalleryPagerContract.ViewFrame) viewFrame).setPage(currentPosition);
	}


	@SuppressWarnings("unused")
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onEvent(CancelLongPressEvent event) {
		if (!event.getLabel().equals(getAlbumName())) {
			return;
		}
		if (!event.isFromDialog()) {
			((ImageGalleryPagerContract.ViewFrame) viewFrame).dismissScreen();
		}
	}
}
