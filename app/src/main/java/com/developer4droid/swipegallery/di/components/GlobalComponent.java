package com.developer4droid.swipegallery.di.components;

import com.developer4droid.swipegallery.di.NetModule;
import com.developer4droid.swipegallery.di.AppModule;
import com.developer4droid.swipegallery.viewmodel.MainActivityViewModel;
import com.developer4droid.swipegallery.viewmodel.MainImageViewModel;
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

	void inject(MainActivityViewModel inject);
	
	void inject(MainImageViewModel inject);
}
