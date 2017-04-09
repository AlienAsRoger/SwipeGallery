package com.developer4droid.swipegallery.injection;

import com.developer4droid.swipegallery.dataloading.AssetLoaderImpl;
import com.developer4droid.swipegallery.dataloading.interfaces.AssetLoader;
import dagger.Module;
import dagger.Provides;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 8:30
 */

@Module
public class NetModule {

	@Provides
	AssetLoader provideAssetLoader() {
		return new AssetLoaderImpl();
	}
}
