package com.developer4droid.swipegallery.utils;

import android.databinding.BindingAdapter;
import android.graphics.Rect;
import android.net.Uri;
import android.view.TouchDelegate;
import android.view.View;
import com.developer4droid.swipegallery.databinding.AlbumGalleryRowViewBinding;
import com.developer4droid.swipegallery.ui.view.LongPressImageView;

import java.util.List;

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

	public static void extendTouchArea(View view, final AlbumGalleryRowViewBinding binding) {
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
			}
		});
	}
}
