package com.developer4droid.swipegallery.swipegallery.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.developer4droid.swipegallery.swipegallery.R;
import com.developer4droid.swipegallery.swipegallery.activity.entities.ImageItem;
import com.developer4droid.swipegallery.swipegallery.databinding.MainImageRowViewBinding;
import com.developer4droid.swipegallery.swipegallery.viewmodel.MainImageViewModel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 07.04.2017
 * Time: 18:42
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
		MainImageViewModel viewModel = new MainImageViewModel();
		MainImageRowViewBinding binding = DataBindingUtil.inflate(inflater, R.layout.main_image_row_view,
				parent, false);
		return new ImageViewHolder(binding, viewModel);
	}

	@Override
	public void onBindViewHolder(ImageViewHolder holder, int position) {
		holder.setItem(itemList.get(position));
	}

	@Override
	public int getItemCount() {
		return itemList == null ? 0 : itemList.size();
	}

	public static class ImageViewHolder extends RecyclerView.ViewHolder {
		MainImageViewModel viewModel;
		MainImageRowViewBinding binding;

		public ImageViewHolder(MainImageRowViewBinding binding, MainImageViewModel viewModel) {
			super(binding.mainFrame);
			this.viewModel = viewModel;
			this.binding = binding;
		}

		void setItem(ImageItem item) {
			viewModel.setItem(item);
			binding.executePendingBindings();
		}
	}

}
