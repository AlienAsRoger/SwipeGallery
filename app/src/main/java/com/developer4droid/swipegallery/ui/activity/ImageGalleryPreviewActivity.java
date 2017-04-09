package com.developer4droid.swipegallery.ui.activity;

import android.content.Context;
import android.content.Intent;

public class ImageGalleryPreviewActivity extends ImageGalleryActivity {

	public static Intent createIntent(Context context, String albumName) {
		Intent intent = new Intent(context, ImageGalleryPreviewActivity.class);
		intent.putExtra(ALBUM_NAME, albumName);
		return intent;
	}
}
