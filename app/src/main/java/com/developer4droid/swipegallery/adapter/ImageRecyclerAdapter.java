package com.developer4droid.swipegallery.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.developer4droid.swipegallery.R;
import com.developer4droid.swipegallery.databinding.MainImageRowViewBinding;
import com.developer4droid.swipegallery.model.AlbumItem;
import com.developer4droid.swipegallery.viewmodel.AlbumViewModel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 07.04.2017
 * Time: 18:42
 */
public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ImageViewHolder> {

	/**
	 * Interface to load image from VM to ImageView
	 */
	public interface ImageLoader{
		void loadImage(String uri);
	}

	private List<AlbumItem> itemList;

	public ImageRecyclerAdapter(List<AlbumItem> itemList) {
		this.itemList = itemList;
	}

	public void updateItems(List<AlbumItem> itemList) {
		this.itemList = itemList;
		notifyDataSetChanged();
	}

	@Override
	public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View itemView = inflater.inflate(R.layout.main_image_row_view, parent, false);
		AlbumViewModel viewModel = new AlbumViewModel();
		MainImageRowViewBinding binding = MainImageRowViewBinding.bind(itemView);
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

	public static class ImageViewHolder extends RecyclerView.ViewHolder implements ImageLoader{
		AlbumViewModel viewModel;
		MainImageRowViewBinding binding;

		ImageViewHolder(View view, MainImageRowViewBinding binding, AlbumViewModel viewModel) {
			super(view);
			this.viewModel = viewModel;
			this.binding = binding;
		}

		void setItem(AlbumItem item) {
			viewModel.setItem(item, this);
			binding.executePendingBindings();
		}

		@Override
		public void loadImage(String uri) {
			Glide.with(binding.imageImg.getContext())
					.load(Uri.parse(uri))
//				.load(Uri.parse("file:///android_asset/Images/Animals/1.jpg"))
					.into(binding.imageImg);
		}
	}

}
