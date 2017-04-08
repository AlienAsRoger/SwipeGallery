package com.developer4droid.swipegallery.di.components;

import com.developer4droid.swipegallery.activity.BaseActivity;
import com.developer4droid.swipegallery.dataloading.AssetLoaderImpl;
import com.developer4droid.swipegallery.di.AppModule;
import com.developer4droid.swipegallery.di.NetModule;
import com.developer4droid.swipegallery.viewmodel.AlbumGalleryViewModel;
import com.developer4droid.swipegallery.viewmodel.AlbumViewModel;
import com.developer4droid.swipegallery.viewmodel.ImageGalleryViewModel;
import com.developer4droid.swipegallery.viewmodel.ImageViewModel;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 8:13
 */

@Singleton
@Component(modules = {
		NetModule.class,
		AppModule.class
})
public interface GlobalComponent {

	void inject(BaseActivity inject);

	void inject(AlbumGalleryViewModel inject);

	void inject(ImageGalleryViewModel inject);

	void inject(AlbumViewModel inject);

	void inject(ImageViewModel inject);

	void inject(AssetLoaderImpl inject);
}
