package com.developer4droid.swipegallery.swipegallery.viewmodel;

import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import com.developer4droid.swipegallery.swipegallery.activity.entities.ImageItem;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 07.04.2017
 * Time: 18:49
 */

public class MainImageViewModel extends BaseViewModel {

	private String label;
	private ImageItem item;

	public void setItem(ImageItem item) {
		this.item = item;
		label = item.getLabel();
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
