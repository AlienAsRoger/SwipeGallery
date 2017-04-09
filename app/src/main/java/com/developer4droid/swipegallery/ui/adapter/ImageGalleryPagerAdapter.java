package com.developer4droid.swipegallery.ui.adapter;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.developer4droid.swipegallery.R;
import com.developer4droid.swipegallery.databinding.ImageGalleryPageViewBinding;
import com.developer4droid.swipegallery.dataloading.interfaces.ImageLoader;
import com.developer4droid.swipegallery.model.ImageItem;
import com.developer4droid.swipegallery.ui.viewmodel.ImageViewModel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 09.04.2017
 * Time: 7:32
 */

public class ImageGalleryPagerAdapter extends PagerAdapter {

	private List<ImageItem> itemsList;

	public ImageGalleryPagerAdapter(List<ImageItem> itemsList) {
		this.itemsList = itemsList;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		LayoutInflater inflater = LayoutInflater.from(container.getContext());
		ImageGalleryPageViewBinding binding = DataBindingUtil.inflate(inflater, R.layout.image_gallery_page_view, container, false);
		View view = binding.getRoot();
		// create view model, binding and put it to holder
		ImageViewModel viewModel = new ImageViewModel();
		binding.setImage(viewModel);

		ImageViewHolder holder = new ImageViewHolder(binding, viewModel);
		holder.setItem(itemsList.get(position));

		view.setTag(holder);

		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup collection, int position, Object view) {
		collection.removeView((View) view);
	}

	@Override
	public int getCount() {
		return itemsList == null ? 0 : itemsList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	public void setItemsList(List<ImageItem> itemsList) {
		this.itemsList = itemsList;
		notifyDataSetChanged();
	}

	// ---------------- //
	// ImageView Holder //
	// ---------------- //

	public static class ImageViewHolder implements ImageLoader {
		ImageViewModel viewModel;
		ImageGalleryPageViewBinding binding;

		ImageViewHolder(ImageGalleryPageViewBinding binding, ImageViewModel viewModel) {
			this.viewModel = viewModel;
			this.binding = binding;
		}

		void setItem(ImageItem item) {
			viewModel.setItem(item, this);
			binding.executePendingBindings();
		}

		@Override
		public void loadImage(String uri) {
			Glide.with(binding.imageImg.getContext())
					.load(Uri.parse(uri))
					.into(binding.imageImg);
		}
	}

}
