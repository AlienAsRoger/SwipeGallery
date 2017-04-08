package com.developer4droid.swipegallery.viewmodel;

import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import com.developer4droid.swipegallery.adapter.ImageRecyclerAdapter;
import com.developer4droid.swipegallery.application.MyApplication;
import com.developer4droid.swipegallery.model.AlbumItem;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 07.04.2017
 * Time: 18:49
 */

public class AlbumViewModel extends BaseViewModel {

	private String label;
	private AlbumItem item;

	public AlbumViewModel() {
		MyApplication.getInstance().getGlobalComponent().inject(this);
	}

	public void setItem(AlbumItem item, ImageRecyclerAdapter.ImageLoader imageLoader) {
		this.item = item;
		setLabel(item.getName());
		imageLoader.loadImage(item.getImageUri());
	}

	@Bindable
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
		notifyPropertyChanged(BR.label);
	}

}
