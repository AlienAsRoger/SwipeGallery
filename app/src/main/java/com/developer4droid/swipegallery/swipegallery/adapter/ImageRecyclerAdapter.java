package com.developer4droid.swipegallery.swipegallery.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.developer4droid.swipegallery.swipegallery.R;
import com.developer4droid.swipegallery.swipegallery.databinding.MainImageRowViewBinding;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 07.04.2017
 * Time: 18:42
 */
public class ImageRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


	@Override
	public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		MainImageRowViewBinding binding = DataBindingUtil.inflate(inflater, R.layout.main_image_row_view,
				parent, false);
		return new ImageViewHolder(binding);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return 0;
	}

	public static class ImageViewHolder extends RecyclerView.ViewHolder {

		public ImageViewHolder(MainImageRowViewBinding binding) {
			super(binding.mainFrame);
		}
	}
}
