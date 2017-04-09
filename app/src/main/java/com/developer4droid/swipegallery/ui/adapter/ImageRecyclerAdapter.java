package com.developer4droid.swipegallery.ui.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.developer4droid.swipegallery.R;
import com.developer4droid.swipegallery.databinding.ImageGalleryRowViewBinding;
import com.developer4droid.swipegallery.dataloading.interfaces.ImageLoader;
import com.developer4droid.swipegallery.model.ImageItem;
import com.developer4droid.swipegallery.ui.viewmodel.ImageViewModel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 12:17
 */

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ImageViewHolder> {

	private List<ImageItem> itemList;

	public ImageRecyclerAdapter(List<ImageItem> itemList) {
		this.itemList = itemList;
	}

	public void updateItems(List<ImageItem> itemList) {
		this.itemList = itemList;
		notifyDataSetChanged();
	}

	@Override
	public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View itemView = inflater.inflate(R.layout.image_gallery_row_view, parent, false);
		ImageViewModel viewModel = new ImageViewModel();
		ImageGalleryRowViewBinding binding = ImageGalleryRowViewBinding.bind(itemView);
		binding.setImage(viewModel);

		return new ImageViewHolder(itemView, binding, viewModel);
	}

	@Override
	public void onBindViewHolder(ImageViewHolder holder, int position) {
		holder.setItem(itemList.get(position));
	}

	@Override
	public int getItemCount() {
		return itemList == null ? 0 : itemList.size();
	}

	public static class ImageViewHolder extends RecyclerView.ViewHolder implements ImageLoader {
		ImageViewModel viewModel;
		ImageGalleryRowViewBinding binding;

		ImageViewHolder(View view, ImageGalleryRowViewBinding binding, ImageViewModel viewModel) {
			super(view);
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
