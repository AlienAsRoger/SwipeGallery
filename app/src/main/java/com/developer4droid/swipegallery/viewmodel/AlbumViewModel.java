package com.developer4droid.swipegallery.viewmodel;

import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import com.developer4droid.swipegallery.application.MyApplication;
import com.developer4droid.swipegallery.dataloading.interfaces.ImageLoader;
import com.developer4droid.swipegallery.events.OpenAlbumEvent;
import com.developer4droid.swipegallery.model.AlbumItem;
import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 07.04.2017
 * Time: 18:49
 */

public class AlbumViewModel extends BaseViewModel {

	@Inject
	EventBus eventBus;

	private String label;

	public AlbumViewModel() {
		MyApplication.getInstance().getGlobalComponent().inject(this);
	}

	public void setItem(AlbumItem item, ImageLoader imageLoader) {
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

	public void openAlbum() {
		eventBus.post(new OpenAlbumEvent(label));
	}

}
