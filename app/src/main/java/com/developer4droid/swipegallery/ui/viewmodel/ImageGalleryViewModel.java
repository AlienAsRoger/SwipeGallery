package com.developer4droid.swipegallery.ui.viewmodel;

import android.databinding.Bindable;
import com.developer4droid.swipegallery.BR;
import com.developer4droid.swipegallery.application.MyApplication;
import com.developer4droid.swipegallery.dataloading.interfaces.AssetLoader;
import com.developer4droid.swipegallery.ui.interfaces.ImagesLoadListener;
import com.developer4droid.swipegallery.model.ImageItem;

import javax.inject.Inject;
import java.util.List;

import static com.developer4droid.swipegallery.ui.interfaces.ImageGalleryContract.ActionListener;
import static com.developer4droid.swipegallery.ui.interfaces.ImageGalleryContract.ViewFrame;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 11:50
 */

public class ImageGalleryViewModel extends BaseViewModel implements ActionListener, ImagesLoadListener {

	@Inject
	AssetLoader assetLoader;
	
	private ViewFrame viewFrame;
	private boolean isLoading;
	private String albumName;

	public ImageGalleryViewModel(String albumName) {
		this.albumName = albumName;
		MyApplication.getInstance().getGlobalComponent().inject(this);
	}

	@Override
	public void onResume(ViewFrame viewFrame) {
		this.viewFrame = viewFrame;

		// load data
		setLoading(true);
		assetLoader.loadImagesFromAlbum(this, albumName);

	}

	@Override
	public void onDataLoaded(List<ImageItem> itemList) {
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
