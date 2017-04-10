package com.developer4droid.swipegallery.ui.activity;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.developer4droid.swipegallery.R;
import com.developer4droid.swipegallery.databinding.ActivityMainBinding;
import com.developer4droid.swipegallery.events.CancelLongPressEvent;
import com.developer4droid.swipegallery.events.OpenAlbumEvent;
import com.developer4droid.swipegallery.events.OpenAlbumPreviewEvent;
import com.developer4droid.swipegallery.model.AlbumItem;
import com.developer4droid.swipegallery.ui.adapter.AlbumRecyclerAdapter;
import com.developer4droid.swipegallery.ui.fragment.ImageGalleryPreviewFragment;
import com.developer4droid.swipegallery.ui.interfaces.AlbumGalleryContract;
import com.developer4droid.swipegallery.ui.viewmodel.AlbumGalleryViewModel;
import com.developer4droid.swipegallery.utils.Utils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import static com.developer4droid.swipegallery.ui.adapter.AlbumRecyclerAdapter.AlbumViewHolder;
import static com.developer4droid.swipegallery.utils.Utils.EXTRA_CURRENT_ALBUM_POSITION;
import static com.developer4droid.swipegallery.utils.Utils.EXTRA_STARTING_ALBUM_POSITION;

public class AlbumGalleryActivity extends BaseActivity implements AlbumGalleryContract.ViewFrame {

	public static final String IMAGE_GALLERY_POPUP = "image_gallery_popup";




	@BindView(R.id.recycler_view)
	RecyclerView recyclerView;

	private AlbumRecyclerAdapter adapter;
	private AlbumGalleryViewModel viewModel;
	private ActivityMainBinding binding;

	private Bundle reenterState;
	private boolean isImageGalleryActivityStarted;

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

		isImageGalleryActivityStarted = false;
	}

	@Override
	public void onActivityReenter(int requestCode, Intent data) {
		super.onActivityReenter(requestCode, data);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			// adjust re-enter transition animation
			reenterState = new Bundle(data.getExtras());
			int startingPosition = reenterState.getInt(EXTRA_STARTING_ALBUM_POSITION);
			int currentPosition = reenterState.getInt(EXTRA_CURRENT_ALBUM_POSITION);
			if (startingPosition != currentPosition) {
				recyclerView.scrollToPosition(currentPosition);
			}
			postponeEnterTransition();
			recyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
				@TargetApi(Build.VERSION_CODES.LOLLIPOP)
				@Override
				public boolean onPreDraw() {
					recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
					recyclerView.requestLayout();
					startPostponedEnterTransition();
					return true;
				}
			});
		}
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

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			String newTransitionName = getString(R.string.transition_tag1);
			Utils.createReenterTransition(this, recyclerView, reenterState, newTransitionName);
		}
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

		Intent intent = ImageGalleryActivity.createIntent(this, event.getAlbumName());
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
			if (!isImageGalleryActivityStarted) {
				isImageGalleryActivityStarted = true;

				int position = adapter.getPositionByLabel(event.getAlbumName());
				View view = recyclerView.getLayoutManager().findViewByPosition(position);
				AlbumViewHolder viewHolder = (AlbumViewHolder) recyclerView.getChildViewHolder(view);

				ImageView imageView = viewHolder.getImageView();

				String tag = getString(R.string.transition_tag1);
				startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, imageView, tag).toBundle());
			}
		} else {
			startActivity(intent);
		}
	}

	@SuppressWarnings("unused")
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onEvent(OpenAlbumPreviewEvent event) {
		// don't intercept any touch events
		recyclerView.setEnabled(false);
		ImageGalleryPreviewFragment fragment = ImageGalleryPreviewFragment.createInstance(event.getAlbumName());
		fragment.show(getSupportFragmentManager(), IMAGE_GALLERY_POPUP);
	}

	@SuppressWarnings("unused")
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onEvent(CancelLongPressEvent event) {
		recyclerView.setEnabled(true);
	}

}
