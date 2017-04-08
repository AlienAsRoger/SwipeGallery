package com.developer4droid.swipegallery.di;

import android.app.Application;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 7:23
 */
@Module
public class AppModule {
	Application application;

	public AppModule(Application application) {
		this.application = application;
	}

	@Provides
	@Singleton
	Application providesApplication() {
		return application;
	}

}
