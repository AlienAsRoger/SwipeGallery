package com.developer4droid.swipegallery.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.SharedElementCallback;
import android.databinding.BindingAdapter;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.TouchDelegate;
import android.view.View;
import com.developer4droid.swipegallery.databinding.AlbumGalleryRowViewBinding;
import com.developer4droid.swipegallery.ui.view.LongPressImageView;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 11:14
 */

public class Utils {

	public static String getNameFromPath(String path) {
		List<String> segments = Uri.parse(path).getPathSegments();
		return segments.get(segments.size() - 1);
	}

	@BindingAdapter("app:albumName")
	public static void albumName(LongPressImageView view, String label){
		view.setAlbumLabel(label);
	}

	public static void extendTouchArea(final View view, final AlbumGalleryRowViewBinding binding) {
		view.post(new Runnable() {
			// Post in the parent's message queue to make sure the parent
			// lays out its children before you call getHitRect()
			@Override
			public void run() {
				// The bounds for the delegate view (an ImageButton
				// in this example)
				Rect delegateArea = new Rect();
				View imageView = binding.imageImg;

				// The hit rectangle for the ImageButton
				imageView.getHitRect(delegateArea);

				// Extend the touch area of the ImageButton out of its bounds
				delegateArea.left -= 100;
				delegateArea.top -= 100;
				delegateArea.right += 100;
				delegateArea.bottom += 100;

				// Instantiate a TouchDelegate.
				// "delegateArea" is the bounds in local coordinates of
				// the containing view to be mapped to the delegate view.
				// "imageView" is the child view that should receive motion
				// events.
				TouchDelegate touchDelegate = new TouchDelegate(delegateArea, imageView);

				// Sets the TouchDelegate on the parent view, such that touches
				// within the touch delegate bounds are routed to the child.
				if (View.class.isInstance(imageView.getParent())) {
					((View) imageView.getParent()).setTouchDelegate(touchDelegate);
				}
				view.setTouchDelegate(touchDelegate);
			}
		});
	}

	public static final String EXTRA_STARTING_ALBUM_POSITION = "extra_starting_item_position";
	public static final String EXTRA_CURRENT_ALBUM_POSITION = "extra_current_item_position";

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public static void createReenterTransition(final Activity activity, final RecyclerView recyclerView, final Bundle reenterState,
											   final String newTransitionName) {
		activity.setExitSharedElementCallback(new SharedElementCallback() {
			@TargetApi(Build.VERSION_CODES.LOLLIPOP)
			@Override
			public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
				if (reenterState != null) {
					int startingPosition = reenterState.getInt(EXTRA_STARTING_ALBUM_POSITION);
					int currentPosition = reenterState.getInt(EXTRA_CURRENT_ALBUM_POSITION);
					if (startingPosition != currentPosition) {

						View newSharedElement = recyclerView.findViewWithTag(newTransitionName);
						if (newSharedElement != null) {
							names.clear();
							names.add(newTransitionName);
							sharedElements.clear();
							sharedElements.put(newTransitionName, newSharedElement);
						}
					}

				} else {
					// If reenterState is null, then the activity is exiting.
					View navigationBar = activity.findViewById(android.R.id.navigationBarBackground);
					View statusBar = activity.findViewById(android.R.id.statusBarBackground);
					if (navigationBar != null) {
						names.add(navigationBar.getTransitionName());
						sharedElements.put(navigationBar.getTransitionName(), navigationBar);
					}
					if (statusBar != null) {
						names.add(statusBar.getTransitionName());
						sharedElements.put(statusBar.getTransitionName(), statusBar);
					}
				}
			}
		});
	}
}
