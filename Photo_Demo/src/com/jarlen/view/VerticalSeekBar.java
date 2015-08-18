package com.jarlen.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

/**
 * 
 * @author jarlen
 * 
 */
public class VerticalSeekBar extends SeekBar
{

	private int mLastProgress = 0;

	private OnSeekBarChangeListener mOnChangeListener;

	public VerticalSeekBar(Context context)
	{
		super(context);
	}

	public VerticalSeekBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public VerticalSeekBar(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public synchronized int getMaximum()
	{
		return getMax();
	}

	@Override
	protected void onDraw(Canvas c)
	{
		c.rotate(-90);
		c.translate(-getHeight(), 0);
		super.onDraw(c);
	}

	@Override
	protected synchronized void onMeasure(int widthMeasureSpec,
			int heightMeasureSpec)
	{
		super.onMeasure(heightMeasureSpec, widthMeasureSpec);
		setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(h, w, oldh, oldw);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if (!isEnabled())
		{
			return false;
		}

		switch (event.getAction())
		{
			case MotionEvent.ACTION_DOWN :
				if (mOnChangeListener != null)
				{
					mOnChangeListener.onStartTrackingTouch(this);
				}
				setPressed(true);
				setSelected(true);
				break;
			case MotionEvent.ACTION_MOVE :
				super.onTouchEvent(event);
				int progress = getMax()
						- (int) (getMax() * event.getY() / getHeight());

				// Ensure progress stays within boundaries
				if (progress < 0)
				{
					progress = 0;
				}
				if (progress > getMax())
				{
					progress = getMax();
				}
				setProgress(progress); // Draw progress
				if (progress != mLastProgress)
				{
					// Only enact listener if the progress has actually changed
					mLastProgress = progress;
					if (mOnChangeListener != null)
					{
						mOnChangeListener.onProgressChanged(this, progress,
								true);
					}
				}

				onSizeChanged(getWidth(), getHeight(), 0, 0);
				setPressed(true);
				setSelected(true);
				break;
			case MotionEvent.ACTION_UP :
				if (mOnChangeListener != null)
				{
					mOnChangeListener.onStopTrackingTouch(this);
				}
				setPressed(false);
				setSelected(false);
				break;
			case MotionEvent.ACTION_CANCEL :
				super.onTouchEvent(event);
				setPressed(false);
				setSelected(false);
				break;
		}
		return true;
	}

	public synchronized void setMaximum(int maximum)
	{
		setMax(maximum);
	}

	@Override
	public void setOnSeekBarChangeListener(
			OnSeekBarChangeListener onChangeListener)
	{
		this.mOnChangeListener = onChangeListener;
	}

	public synchronized void setProgressAndThumb(int progress)
	{
		setProgress(progress);
		onSizeChanged(getWidth(), getHeight(), 0, 0);
		if (progress != mLastProgress)
		{
			mLastProgress = progress;
			if (mOnChangeListener != null)
			{
				mOnChangeListener.onProgressChanged(this, progress, true);
			}
		}
	}
}
