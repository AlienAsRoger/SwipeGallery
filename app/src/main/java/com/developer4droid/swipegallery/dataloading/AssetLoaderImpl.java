package com.developer4droid.swipegallery.dataloading;

import com.developer4droid.swipegallery.dataloading.interfaces.AssetLoader;
import com.developer4droid.swipegallery.interfaces.LoadListener;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 7:35
 */

public class AssetLoaderImpl implements AssetLoader {

	public AssetLoaderImpl() {

	}

	@Override
	public void loadImages(LoadListener loadListener) {
		new AssetLoadingTask(loadListener).execute();
	}
}
