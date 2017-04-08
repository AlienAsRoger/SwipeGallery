package com.developer4droid.swipegallery.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.developer4droid.swipegallery.application.MyApplication;
import com.developer4droid.swipegallery.events.OpenAlbumEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 11:30
 */

public abstract class BaseActivity extends AppCompatActivity {

	@Inject
	protected EventBus eventBus;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().getGlobalComponent().inject(this);
	}

	@Override
	public void onStart() {
		super.onStart();
		eventBus.register(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		eventBus.unregister(this);
	}


	@Subscribe
	public void onEvent(OpenAlbumEvent event) {
	}
}
