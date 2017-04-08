package com.developer4droid.swipegallery.dataloading;

import android.app.Application;
import com.developer4droid.swipegallery.application.MyApplication;
import com.developer4droid.swipegallery.dataloading.interfaces.AssetLoader;
import com.developer4droid.swipegallery.interfaces.LoadListener;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 7:35
 */

public class AssetLoaderImpl implements AssetLoader {

	@Inject
	Application application;

	public AssetLoaderImpl() {
		MyApplication.getInstance().getGlobalComponent().inject(this);
	}

	@Override
	public void loadImages(LoadListener loadListener) {
		new AssetLoadingTask(loadListener).execute(application.getAssets());
	}
}
