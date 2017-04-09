package com.developer4droid.swipegallery.ui.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.developer4droid.swipegallery.R;
import com.developer4droid.swipegallery.databinding.ImageGalleryFragmentBinding;
import com.developer4droid.swipegallery.model.ImageItem;
import com.developer4droid.swipegallery.ui.adapter.ImageGalleryPagerAdapter;
import com.developer4droid.swipegallery.ui.interfaces.ImageGalleryContract;
import com.developer4droid.swipegallery.ui.viewmodel.ImageGalleryViewModel;

import java.util.List;

import static com.developer4droid.swipegallery.ui.activity.ImageGalleryActivity.ALBUM_NAME;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 19:57
 */

public class ImageGalleryPreviewFragment extends DialogFragment implements ImageGalleryContract.ViewFrame {


	@BindView(R.id.view_pager)
	ViewPager viewPager;

	private ImageGalleryViewModel viewModel;
	private ImageGalleryFragmentBinding binding;
	private ImageGalleryPagerAdapter adapter;

	public static ImageGalleryPreviewFragment createInstance(String albumName) {
		ImageGalleryPreviewFragment fragment = new ImageGalleryPreviewFragment();
		Bundle bundle = new Bundle();
		bundle.putString(ALBUM_NAME, albumName);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setStyle(STYLE_NO_FRAME, 0);

		String albumName;
		if (savedInstanceState != null) {
			albumName = savedInstanceState.getString(ALBUM_NAME);
		} else {
			albumName = getArguments().getString(ALBUM_NAME);
		}

		init(albumName);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// set transparent background
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

		binding = DataBindingUtil.inflate(inflater, R.layout.image_gallery_fragment, container, false);
		View view = binding.getRoot();
		ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		initViews();
	}

	@Override
	public void onResume() {
		super.onResume();

		binding.setImageGallery(viewModel);

		viewModel.onResume(this);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putString(ALBUM_NAME, viewModel.getAlbumName());
	}

	// ------------- //
	// Local methods //
	// ------------- //

	/**
	 * Init Adapter and ViewModel
	 */
	private void init(String albumName) {
		adapter = new ImageGalleryPagerAdapter(null);
		viewModel = new ImageGalleryViewModel(albumName);
	}

	/**
	 * Initiate views after main objects
	 */
	private void initViews() {
		viewPager.setAdapter(adapter);
	}

	// ------------------------ //
	// Interface Implementation //
	// ------------------------ //

	@Override
	public void updateAdapter(List<ImageItem> itemList) {
		adapter.setItemsList(itemList);
	}
}
