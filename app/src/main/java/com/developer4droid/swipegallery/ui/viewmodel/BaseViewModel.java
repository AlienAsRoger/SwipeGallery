package com.developer4droid.swipegallery.ui.viewmodel;

import android.databinding.BaseObservable;
import com.developer4droid.swipegallery.application.MyApplication;
import com.developer4droid.swipegallery.events.OpenAlbumEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 07.04.2017
 * Time: 19:50
 */

public abstract class BaseViewModel extends BaseObservable {

	@Inject
	protected EventBus eventBus;


	public BaseViewModel() {
		MyApplication.getInstance().getGlobalComponent().inject(this);
		eventBus.register(this);
	}

	/**
	 * Stub. Required here to not throw exception that no method with @Subscribe annotation
	 */
	@Subscribe
	public void onEvent(OpenAlbumEvent event) {
	}
}
