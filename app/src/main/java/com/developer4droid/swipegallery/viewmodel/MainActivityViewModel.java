package com.developer4droid.swipegallery.viewmodel;

import android.databinding.Bindable;
import com.developer4droid.swipegallery.BR;
import com.developer4droid.swipegallery.application.MyApplication;
import com.developer4droid.swipegallery.dataloading.interfaces.AssetLoader;
import com.developer4droid.swipegallery.interfaces.LoadListener;
import com.developer4droid.swipegallery.interfaces.MainActivityContract;
import com.developer4droid.swipegallery.model.AlbumItem;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 07.04.2017
 * Time: 19:06
 */

public class MainActivityViewModel extends BaseViewModel implements MainActivityContract.ActionListener, LoadListener {

	@Inject
	AssetLoader assetLoader;

	private MainActivityContract.ViewFrame viewFrame;
	private boolean isLoading;

	public MainActivityViewModel() {
		MyApplication.getInstance().getGlobalComponent().inject(this);
	}

	@Override
	public void onResume(MainActivityContract.ViewFrame viewFrame) {
		this.viewFrame = viewFrame;

		// load data
		setLoading(true);
		assetLoader.loadImages(this);
	}

	@Override
	public void onDataLoaded(List<AlbumItem> itemList) {
		setLoading(false);
		viewFrame.updateAdapter(itemList);
	}

	@Bindable
	public boolean isLoading() {
		return isLoading;
	}

	private void setLoading(boolean loading) {
		isLoading = loading;
		notifyPropertyChanged(BR.loading);
	}
}
