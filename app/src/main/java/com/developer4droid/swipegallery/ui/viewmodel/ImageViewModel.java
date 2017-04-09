package com.developer4droid.swipegallery.ui.viewmodel;

import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import com.developer4droid.swipegallery.application.MyApplication;
import com.developer4droid.swipegallery.dataloading.interfaces.ImageLoader;
import com.developer4droid.swipegallery.model.ImageItem;
import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 11:51
 */

public class ImageViewModel extends BaseViewModel {


	@Inject
	EventBus eventBus;

	private String label;

	public ImageViewModel() {
		MyApplication.getInstance().getGlobalComponent().inject(this);
	}

	public void setItem(ImageItem item, ImageLoader imageLoader) {
		setLabel(item.getLabel());
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
