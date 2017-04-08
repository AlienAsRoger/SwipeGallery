package com.developer4droid.swipegallery.dataloading.interfaces;

import com.developer4droid.swipegallery.interfaces.AlbumLoadListener;
import com.developer4droid.swipegallery.interfaces.ImagesLoadListener;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 07.04.2017
 * Time: 21:20
 */

public interface AssetLoader {

	void loadAlbums(AlbumLoadListener loadListener);

	void loadImagesFromAlbum(ImagesLoadListener loadListener, String albumName);

}
