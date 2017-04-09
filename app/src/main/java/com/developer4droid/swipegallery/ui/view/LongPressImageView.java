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
import com.developer4droid.swipegallery.events.LongPressImageViewEvent;
import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: roger developer4droid@gmail.com
 * Date: 08.04.2017
 * Time: 19:14
 */

public class LongPressImageView extends android.support.v7.widget.AppCompatImageView implements View.OnTouchListener {

	private static final int THRESHOLD = 1000; // 1 sec

	@Inject
	EventBus eventBus;

	private float dragX;
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

	private void init(Context context, AttributeSet attrs) {
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
	private boolean onActionDown(MotionEvent event) {
		long downTime = event.getDownTime();
		Log.d("TEST", "onActionDown: down Timer = " + downTime);

		// start timer
		handler.postDelayed(openPreviewRunnable, THRESHOLD);
		return true;
	}

	private boolean onActionMove(MotionEvent event) {
		long downTime = event.getDownTime();
		Log.d("TEST", "onActionMove: down Timer = " + downTime);
		dragX = event.getX();
		return true;
	}

	private boolean onActionCancel(MotionEvent event) {
		long downTime = event.getDownTime();
		Log.d("TEST", "onActionCancel: down Timer = " + downTime);
		return true;
	}

	private boolean onActionUp(MotionEvent event) {
		long downTime = event.getDownTime();
		Log.d("TEST", "onActionUp: down Timer = " + downTime);
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
