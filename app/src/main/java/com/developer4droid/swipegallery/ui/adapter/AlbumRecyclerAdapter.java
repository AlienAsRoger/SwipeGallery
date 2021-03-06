package com.developer4droid.swipegallery.ui.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.developer4droid.swipegallery.R;
import com.developer4droid.swipegallery.databinding.AlbumGalleryRowViewBinding;
import com.developer4droid.swipegallery.dataloading.interfaces.ImageLoader;
import com.developer4droid.swipegallery.model.AlbumItem;
import com.developer4droid.swipegallery.ui.viewmodel.AlbumViewModel;
import com.developer4droid.swipegallery.utils.Utils;

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

	public int getPositionByLabel(String albumName) {
		for (int pos = 0; pos < itemList.size(); pos++) {
			AlbumItem albumItem = itemList.get(pos);
			if (albumItem.getName().equals(albumName)) {
				return pos;
			}
		}
		return 0;
	}

	public static class AlbumViewHolder extends RecyclerView.ViewHolder implements ImageLoader {
		AlbumViewModel viewModel;
		AlbumGalleryRowViewBinding binding;

		AlbumViewHolder(View view, final AlbumGalleryRowViewBinding binding, AlbumViewModel viewModel) {
			super(view);
			this.viewModel = viewModel;
			this.binding = binding;

			Utils.extendTouchArea(view, binding);
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

		public ImageView getImageView() {
			return binding.imageImg;
		}
	}

}
