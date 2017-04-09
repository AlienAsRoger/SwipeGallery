package com.developer4droid.swipegallery.application;

import android.app.Application;
import com.developer4droid.swipegallery.injection.AppModule;
import com.developer4droid.swipegallery.injection.components.DaggerGlobalComponent;
import com.developer4droid.swipegallery.injection.components.GlobalComponent;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 7:30
 */

public class MyApplication extends Application {

	private static MyApplication instance;
	private GlobalComponent component;

	@Override
	public void onCreate() {
		super.onCreate();

		instance = this;

		component = DaggerGlobalComponent.builder()
				.appModule(new AppModule(this))
				.build();
	}

	public static MyApplication getInstance() {
		return instance;
	}

	public GlobalComponent getGlobalComponent() {
		return component;
	}
}
