package com.developer4droid.swipegallery.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.developer4droid.swipegallery.R;
import com.developer4droid.swipegallery.adapter.ImageRecyclerAdapter;
import com.developer4droid.swipegallery.databinding.ActivityMainBinding;
import com.developer4droid.swipegallery.interfaces.MainActivityContract;
import com.developer4droid.swipegallery.model.ImageItem;
import com.developer4droid.swipegallery.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityContract.ViewFrame {

	@BindView(R.id.recycler_view)
	RecyclerView recyclerView;

	private ImageRecyclerAdapter adapter;
	private MainActivityViewModel viewModel;
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

		binding.setMain(viewModel);
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
		viewModel = new MainActivityViewModel();
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
	public void updateAdapter(List<ImageItem> itemList) {
		adapter.updateItems(itemList);
	}
}
