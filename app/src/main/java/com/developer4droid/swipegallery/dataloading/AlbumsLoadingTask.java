package com.developer4droid.swipegallery.dataloading;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import com.developer4droid.swipegallery.Utils;
import com.developer4droid.swipegallery.interfaces.AlbumLoadListener;
import com.developer4droid.swipegallery.model.AlbumItem;
import com.developer4droid.swipegallery.model.ImageItem;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 7:37
 */

public class AlbumsLoadingTask extends AsyncTask<AssetManager, Integer, List<AlbumItem>> {

	private static final String PNG = ".png";
	private static final String JPG = ".jpg";
	static final String PATH_DELIMITER = File.separator;
	private static final String FILE_PATH = "file:///android_asset/";
	static final String ASSET_ROOT = "Images";

	private WeakReference<AlbumLoadListener> loadListener;
	private List<AlbumItem> albumList;

	AlbumsLoadingTask(AlbumLoadListener loadListener) {
		this.loadListener = new WeakReference<>(loadListener);
	}

	@Override
	protected List<AlbumItem> doInBackground(AssetManager... assetManagers) {
		albumList = new ArrayList<>();
		AssetManager assetManager = assetManagers[0];
		try {
			// get list of albums
			String[] list = assetManager.list(ASSET_ROOT);
			for (String albumPath : list) {
				addAlbumsAndImages(ASSET_ROOT + PATH_DELIMITER + albumPath, assetManager);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return albumList;
	}

	private boolean addAlbumsAndImages(String path, AssetManager assetManager) {

		boolean filesExist = true;
		String[] list;
		try {
			list = assetManager.list(path);
			if (list.length <= 0) {
				return true;
			}

			AlbumItem albumItem = new AlbumItem(Utils.getNameFromPath(path));
			// set first image as album image
			String firstFileName = list[0];
			albumItem.setImageUri(getImageUri(path, firstFileName));

			List<ImageItem> imageList = new ArrayList<>();

			for (String fileName : list) {
				// for simplicity we differ folders from files based on names. We don't assume that folders will have sub-folders as it will require more complex logic
				if (isFile(fileName)) {
					ImageItem imageItem = new ImageItem(fileName);
					imageItem.setImageUri(getImageUri(path, fileName));
					imageList.add(imageItem);
				}

				if (!addAlbumsAndImages(path + PATH_DELIMITER + fileName, assetManager)) {
					filesExist = false;
					break;
				}
			}
			albumItem.setImageList(imageList);
			albumList.add(albumItem);

		} catch (IOException e) {
			return false;
		}

		return filesExist;
	}

	static String getImageUri(String path, String firstFileName) {
		return FILE_PATH + path + PATH_DELIMITER + firstFileName;
	}

	static boolean isFile(String fileName) {
		return fileName.contains(JPG) || fileName.contains(PNG);
	}

	@Override
	protected void onPostExecute(List<AlbumItem> imageItems) {
		super.onPostExecute(imageItems);

		AlbumLoadListener listener = loadListener.get();
		if (listener != null) {
			listener.onDataLoaded(imageItems);
		}
	}
}
