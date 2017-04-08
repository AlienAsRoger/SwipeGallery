package com.developer4droid.swipegallery.swipegallery.viewmodel;

import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 07.04.2017
 * Time: 18:49
 */

public class MainImageViewModel extends BaseViewModel {

	private String label;

	public MainImageViewModel(String label) {
		this.label = label;
		this.label = "test";
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
