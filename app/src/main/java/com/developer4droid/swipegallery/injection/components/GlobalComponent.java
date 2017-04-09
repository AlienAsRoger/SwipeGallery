package com.developer4droid.swipegallery.injection.components;

import com.developer4droid.swipegallery.dataloading.AssetLoaderImpl;
import com.developer4droid.swipegallery.injection.AppModule;
import com.developer4droid.swipegallery.injection.NetModule;
import com.developer4droid.swipegallery.ui.activity.BaseActivity;
import com.developer4droid.swipegallery.ui.fragment.ImageGalleryPreviewFragment;
import com.developer4droid.swipegallery.ui.view.LongPressImageView;
import com.developer4droid.swipegallery.ui.viewmodel.*;
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

	void inject(BaseViewModel inject);

	void inject(AlbumViewModel inject);

	void inject(ImageViewModel inject);

	void inject(AssetLoaderImpl inject);

	void inject(LongPressImageView inject);

	void inject(ImageGalleryPreviewFragment inject);
}
