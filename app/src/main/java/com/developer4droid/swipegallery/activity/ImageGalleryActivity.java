package com.developer4droid.swipegallery.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.developer4droid.swipegallery.R;
import com.developer4droid.swipegallery.adapter.ImageRecyclerAdapter;
import com.developer4droid.swipegallery.databinding.ActivityImageGalleryBinding;
import com.developer4droid.swipegallery.interfaces.ImageGalleryContract;
import com.developer4droid.swipegallery.model.ImageItem;
import com.developer4droid.swipegallery.viewmodel.ImageGalleryViewModel;

import java.util.List;

public class ImageGalleryActivity extends BaseActivity implements ImageGalleryContract.ViewFrame {

	public static final String ALBUM_NAME = "album_name";

	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.recycler_view)
	RecyclerView recyclerView;

	private ImageRecyclerAdapter adapter;
	private ImageGalleryViewModel viewModel;
	private ActivityImageGalleryBinding binding;

	public static Intent createIntent(Context context, String albumName) {
		Intent intent = new Intent(context, ImageGalleryActivity.class);
		intent.putExtra(ALBUM_NAME, albumName);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this, R.layout.activity_image_gallery);

		// Use butter knife for fast binding
		ButterKnife.bind(this);

		init();
		initViews();
	}

	@Override
	protected void onResume() {
		super.onResume();

		binding.setImageGallery(viewModel);
		// load data
		viewModel.onResume(this);
	}

	// ------------- //
	// Local methods //
	// ------------- //

	/**
	 * Init Adapter and ViewModel
	 */
	private void init() {
		adapter = new ImageRecyclerAdapter(null);
		String albumName = getIntent().getStringExtra(ALBUM_NAME);

		viewModel = new ImageGalleryViewModel(albumName);
	}

	/**
	 * Initiate views after main objects
	 */
	private void initViews() {
		setSupportActionBar(toolbar);

		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
	}

	// ------------------------ //
	// Interface Implementation //
	// ------------------------ //

	@Override
	public void updateAdapter(List<ImageItem> itemList) {
		adapter.updateItems(itemList);
	}
}
