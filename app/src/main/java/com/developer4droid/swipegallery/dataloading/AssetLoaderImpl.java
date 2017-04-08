package com.developer4droid.swipegallery.dataloading;

import android.app.Application;
import com.developer4droid.swipegallery.application.MyApplication;
import com.developer4droid.swipegallery.dataloading.interfaces.AssetLoader;
import com.developer4droid.swipegallery.interfaces.AlbumLoadListener;
import com.developer4droid.swipegallery.interfaces.ImagesLoadListener;

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
	public void loadAlbums(AlbumLoadListener loadListener) {
		new AlbumsLoadingTask(loadListener).execute(application.getAssets());
	}

	@Override
	public void loadImagesFromAlbum(ImagesLoadListener loadListener, String albumName) {
		new ImagesLoadingTask(loadListener, albumName).execute(application.getAssets());
	}
}
