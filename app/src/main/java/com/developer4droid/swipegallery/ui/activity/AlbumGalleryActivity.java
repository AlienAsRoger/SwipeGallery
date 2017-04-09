package com.developer4droid.swipegallery.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.developer4droid.swipegallery.R;
import com.developer4droid.swipegallery.databinding.ActivityMainBinding;
import com.developer4droid.swipegallery.events.OpenAlbumEvent;
import com.developer4droid.swipegallery.events.OpenAlbumPreviewEvent;
import com.developer4droid.swipegallery.model.AlbumItem;
import com.developer4droid.swipegallery.ui.adapter.AlbumRecyclerAdapter;
import com.developer4droid.swipegallery.ui.fragment.ImageGalleryPreviewFragment;
import com.developer4droid.swipegallery.ui.interfaces.AlbumGalleryContract;
import com.developer4droid.swipegallery.ui.viewmodel.AlbumGalleryViewModel;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class AlbumGalleryActivity extends BaseActivity implements AlbumGalleryContract.ViewFrame {

	public static final String IMAGE_GALLERY_POPUP = "image_gallery_popup";

	@BindView(R.id.recycler_view)
	RecyclerView recyclerView;

	private AlbumRecyclerAdapter adapter;
	private AlbumGalleryViewModel viewModel;
	private ActivityMainBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

		// Use butter knife for fast binding
		ButterKnife.bind(this);

		init();
		initViews();
	}

	@Override
	protected void onResume() {
		super.onResume();

		binding.setAlbumGallery(viewModel);
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
		adapter = new AlbumRecyclerAdapter(null);
		viewModel = new AlbumGalleryViewModel();
	}

	/**
	 * Initiate views after main objects
	 */
	private void initViews() {
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
	}

	// ------------------------ //
	// Interface Implementation //
	// ------------------------ //

	@Override
	public void updateAdapter(List<AlbumItem> itemList) {
		adapter.updateItems(itemList);
	}

	// --------- //
	// Event Bus //
	// --------- //

	@SuppressWarnings("unused")
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onOpenAlbumEvent(OpenAlbumEvent event) {
		startActivity(ImageGalleryActivity.createIntent(this, event.getAlbumName()));
	}

	@SuppressWarnings("unused")
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onEvent(OpenAlbumPreviewEvent event) {
		ImageGalleryPreviewFragment fragment = ImageGalleryPreviewFragment.createInstance(event.getAlbumName());
		fragment.show(getSupportFragmentManager(), IMAGE_GALLERY_POPUP);
	}

}
