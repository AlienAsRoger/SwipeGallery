package com.developer4droid.swipegallery.dataloading;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;
import com.developer4droid.swipegallery.interfaces.ImagesLoadListener;
import com.developer4droid.swipegallery.model.ImageItem;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.developer4droid.swipegallery.dataloading.AlbumsLoadingTask.*;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 12:03
 */

public class ImagesLoadingTask extends AsyncTask<AssetManager, Integer, List<ImageItem>> {

	private WeakReference<ImagesLoadListener> loadListener;
	private String albumName;
	private List<ImageItem> imageList;

	ImagesLoadingTask(ImagesLoadListener loadListener, String albumName) {
		this.loadListener = new WeakReference<>(loadListener);
		this.albumName = albumName;
	}

	@Override
	protected List<ImageItem> doInBackground(AssetManager... assetManagers) {
		imageList = new ArrayList<>();
		AssetManager assetManager = assetManagers[0];
		boolean isLoaded = addImagesFromAlbum(ASSET_ROOT + PATH_DELIMITER + albumName, assetManager);
		if (!isLoaded) {
			Log.d("TEST", "doInBackground: use to throw an error");
		}

		return imageList;
	}

	private boolean addImagesFromAlbum(String path, AssetManager assetManager) {

		String[] list;
		try {
			list = assetManager.list(path);
			if (list.length <= 0) {
				return false;
			}

			for (String fileName : list) {
				if (isFile(fileName)) {
					ImageItem imageItem = new ImageItem(fileName);
					imageItem.setImageUri(getImageUri(path, fileName));
					imageList.add(imageItem);
				}
			}

		} catch (IOException e) {
			return false;
		}

		return true;
	}


	@Override
	protected void onPostExecute(List<ImageItem> imageItems) {
		super.onPostExecute(imageItems);

		ImagesLoadListener listener = loadListener.get();
		if (listener != null) {
			listener.onDataLoaded(imageItems);
		}
	}
}
