package com.developer4droid.swipegallery.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.developer4droid.swipegallery.R;
import com.developer4droid.swipegallery.databinding.AlbumGalleryRowViewBinding;
import com.developer4droid.swipegallery.dataloading.interfaces.ImageLoader;
import com.developer4droid.swipegallery.model.AlbumItem;
import com.developer4droid.swipegallery.viewmodel.AlbumViewModel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 07.04.2017
 * Time: 18:42
 */
public class AlbumRecyclerAdapter extends RecyclerView.Adapter<AlbumRecyclerAdapter.AlbumViewHolder> {

	private List<AlbumItem> itemList;

	public AlbumRecyclerAdapter(List<AlbumItem> itemList) {
		this.itemList = itemList;
	}

	public void updateItems(List<AlbumItem> itemList) {
		this.itemList = itemList;
		notifyDataSetChanged();
	}

	@Override
	public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View itemView = inflater.inflate(R.layout.album_gallery_row_view, parent, false);
		AlbumViewModel viewModel = new AlbumViewModel();
		AlbumGalleryRowViewBinding binding = AlbumGalleryRowViewBinding.bind(itemView);
		binding.setAlbum(viewModel);

		return new AlbumViewHolder(itemView, binding, viewModel);
	}

	@Override
	public void onBindViewHolder(AlbumViewHolder holder, int position) {
		holder.setItem(itemList.get(position));
	}

	@Override
	public int getItemCount() {
		return itemList == null ? 0 : itemList.size();
	}

	public static class AlbumViewHolder extends RecyclerView.ViewHolder implements ImageLoader {
		AlbumViewModel viewModel;
		AlbumGalleryRowViewBinding binding;

		AlbumViewHolder(View view, AlbumGalleryRowViewBinding binding, AlbumViewModel viewModel) {
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
					.into(binding.imageImg);
		}
	}

}
