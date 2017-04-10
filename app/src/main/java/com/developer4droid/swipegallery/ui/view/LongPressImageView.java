package com.developer4droid.swipegallery.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.developer4droid.swipegallery.R;
import com.developer4droid.swipegallery.application.MyApplication;
import com.developer4droid.swipegallery.events.CancelLongPressEvent;
import com.developer4droid.swipegallery.events.LongPressImageViewEvent;
import com.developer4droid.swipegallery.events.SwipeImageEvent;
import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 19:14
 */

public class LongPressImageView extends android.support.v7.widget.AppCompatImageView implements View.OnTouchListener {

	private static final int THRESHOLD = 500; // delay between long press preview will appear
	private static final float SWIPE_DETECT_OFFSET = 30;

	@Inject
	EventBus eventBus;

	private float previousX;

	private Handler handler;
	private String label;

	public LongPressImageView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public LongPressImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	protected void init(Context context, AttributeSet attrs) {
		MyApplication.getInstance().getGlobalComponent().inject(this);

		// get label from XML. In case of data binding doesn't work, so use BindingAdapter in Utils class
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LongPressImageView);
		if (array == null) {
			return;
		}
		try {
			if (array.hasValue(R.styleable.LongPressImageView_albumName)) {
				label = array.getString(R.styleable.LongPressImageView_albumName);
			}
		} finally {
			array.recycle();
		}

		handler = new Handler();
		setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN: {
				return onActionDown(event);
			}
			case MotionEvent.ACTION_MOVE: {
				return onActionMove(event);
			}
			case MotionEvent.ACTION_UP: {
				return onActionUp(event);
			}
			case MotionEvent.ACTION_CANCEL: {
				return onActionCancel(event);
			}
		}
		return super.onTouchEvent(event);
	}

	protected boolean onActionDown(MotionEvent event) {
		Log.d("TEST", "onActionDown: ");
		// start timer
		handler.postDelayed(openPreviewRunnable, THRESHOLD);
		return true;
	}

	protected boolean onActionMove(MotionEvent event) {
		float dragX = event.getX();
//		Log.d("TEST", "onActionMove: delta = " + Math.abs(dragX - previousX));
		if (Math.abs(dragX - previousX) > SWIPE_DETECT_OFFSET) {
			if (dragX > previousX) {
				Log.d("TEST", "onActionMove: move right for = " + label);
				eventBus.post(SwipeImageEvent.createRightEvent(label));
			} else {
				Log.d("TEST", "onActionMove: move left for = " + label);
				eventBus.post(SwipeImageEvent.createLeftEvent(label));
			}
			previousX = event.getX();
		}

		return true;
	}

	protected boolean onActionCancel(MotionEvent event) {
		handler.removeCallbacks(openPreviewRunnable);
		Log.d("TEST", "onActionCancel: ");
		eventBus.post(new CancelLongPressEvent(label));
		return true;
	}

	protected boolean onActionUp(MotionEvent event) {
		Log.d("TEST", "onActionUp: ");
		checkTouchTime(event);
		return true;
	}

	/**
	 * If user holds more than threshold we open preview, otherwise we fire regular click
	 */
	private void checkTouchTime(MotionEvent event) {
		long downTime = event.getDownTime();
		long eventTime = event.getEventTime();
		if (eventTime - downTime < THRESHOLD) {
			Log.d("TEST", "checkTouchTime: call onclick");
			handler.removeCallbacks(openPreviewRunnable);
			callOnClick();
		} else {
			Log.d("TEST", "checkTouchTime: more than threshold");
			// cancel long press action and all related stuff
			eventBus.post(new CancelLongPressEvent(label));
		}
	}

	/**
	 * Tells VM to fire next event.
	 */
	private Runnable openPreviewRunnable = new Runnable() {
		@Override
		public void run() {
			handler.removeCallbacks(this);
			Log.d("TEST", "run: fire LongPressImageViewEvent");
			eventBus.post(new LongPressImageViewEvent(label));
		}
	};

	public void setAlbumLabel(String albumLabel) {
		this.label = albumLabel;
	}
}
