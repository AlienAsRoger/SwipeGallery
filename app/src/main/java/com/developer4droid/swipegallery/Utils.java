package com.developer4droid.swipegallery;

import android.net.Uri;

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
}
