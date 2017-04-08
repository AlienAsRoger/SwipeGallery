package com.developer4droid.swipegallery.viewmodel;

import android.databinding.Bindable;
import com.developer4droid.swipegallery.BR;
import com.developer4droid.swipegallery.application.MyApplication;
import com.developer4droid.swipegallery.dataloading.interfaces.AssetLoader;
import com.developer4droid.swipegallery.interfaces.AlbumLoadListener;
import com.developer4droid.swipegallery.model.AlbumItem;

import javax.inject.Inject;
import java.util.List;

import static com.developer4droid.swipegallery.interfaces.AlbumGalleryContract.ActionListener;
import static com.developer4droid.swipegallery.interfaces.AlbumGalleryContract.ViewFrame;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 07.04.2017
 * Time: 19:06
 */

public class AlbumGalleryViewModel extends BaseViewModel implements ActionListener, AlbumLoadListener {

	@Inject
	AssetLoader assetLoader;

	private ViewFrame viewFrame;
	private boolean isLoading;

	public AlbumGalleryViewModel() {
		MyApplication.getInstance().getGlobalComponent().inject(this);
	}

	@Override
	public void onResume(ViewFrame viewFrame) {
		this.viewFrame = viewFrame;

		// load data
		setLoading(true);
		assetLoader.loadAlbums(this);
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
