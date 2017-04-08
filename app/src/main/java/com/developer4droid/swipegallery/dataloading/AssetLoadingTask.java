package com.developer4droid.swipegallery.dataloading;

import android.os.AsyncTask;
import com.developer4droid.swipegallery.interfaces.LoadListener;
import com.developer4droid.swipegallery.model.ImageItem;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 7:37
 */

public class AssetLoadingTask extends AsyncTask<Void, Integer, List<ImageItem>> {

	private WeakReference<LoadListener> loadListener;

	public AssetLoadingTask(LoadListener loadListener) {

		this.loadListener = new WeakReference<>(loadListener);
	}

	@Override
	protected List<ImageItem> doInBackground(Void... voids) {
		List<ImageItem> imageItems = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			ImageItem imageItem = new ImageItem();
			imageItem.setLabel("test  " + i);
			imageItems.add(imageItem);
		}
//		Drawable.createFromStream(context.getResources().getAssets().open("flags/" + userCountry + ".png"), null);
		return imageItems;
	}

	@Override
	protected void onPostExecute(List<ImageItem> imageItems) {
		super.onPostExecute(imageItems);

		LoadListener listener = this.loadListener.get();
		if (listener != null) {
			listener.onDataLoaded(imageItems);
		}
	}
}
