package com.developer4droid.swipegallery.swipegallery.viewmodel;

import android.databinding.Bindable;
import com.developer4droid.swipegallery.swipegallery.BR;
import com.developer4droid.swipegallery.swipegallery.activity.dataloading.AssetLoader;
import com.developer4droid.swipegallery.swipegallery.activity.entities.ImageItem;
import com.developer4droid.swipegallery.swipegallery.interfaces.LoadListener;

import java.util.List;

import static com.developer4droid.swipegallery.swipegallery.interfaces.MainActivityContract.ActionListener;
import static com.developer4droid.swipegallery.swipegallery.interfaces.MainActivityContract.ViewFrame;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 07.04.2017
 * Time: 19:06
 */

public class MainActivityViewModel extends BaseViewModel implements ActionListener, LoadListener {

	private AssetLoader assetLoader;
	private ViewFrame viewFrame;
	private boolean isLoading;

	@Override
	public void onResume(ViewFrame viewFrame) {
		this.viewFrame = viewFrame;

		// load data
		setLoading(true);
//		assetLoader.loadImages(this);
	}

	@Override
	public void onDataLoaded(List<ImageItem> itemList) {
		viewFrame.updateAdapter(itemList);
	}

	@Bindable
	public boolean isLoading() {
		return isLoading;
	}

	public void setLoading(boolean loading) {
		isLoading = loading;
		notifyPropertyChanged(BR.loading);
	}
}
