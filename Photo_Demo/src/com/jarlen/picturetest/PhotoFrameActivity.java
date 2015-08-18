package com.jarlen.picturetest;

import java.io.File;

import com.js.photosdk.utils.FileUtils;
import com.js.photosdk.photoframe.PhotoFrame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class PhotoFrameActivity extends Activity implements OnClickListener
{

	private PhotoFrame mImageFrame;
	private ImageView picture;

	private ImageView backBtn, okBtn;

	private Bitmap mBitmap;
	private Bitmap mTmpBmp;
	private String pathName = Environment.getExternalStorageDirectory()
			+ "/DCIM/Camera/test.jpg";

	private String photoResPath;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo_frame);

		Intent photoFrameIntent = getIntent();
		photoResPath = photoFrameIntent.getStringExtra("camera_path");

		Options mOption = new Options();
		mOption.inSampleSize = 1;

		mBitmap = BitmapFactory.decodeFile(photoResPath, mOption);
		mTmpBmp = mBitmap;
		initView();
	}

	private void initView()
	{
		backBtn = (ImageView) findViewById(R.id.btn_cancel);
		backBtn.setOnClickListener(this);
		okBtn = (ImageView) findViewById(R.id.btn_ok);
		okBtn.setOnClickListener(this);
		picture = (ImageView) findViewById(R.id.picture);

		findViewById(R.id.photoRes_one).setOnClickListener(
				new PhotoFrameOnClickListener());
		findViewById(R.id.photoRes_two).setOnClickListener(
				new PhotoFrameOnClickListener());
		findViewById(R.id.photoRes_three).setOnClickListener(
				new PhotoFrameOnClickListener());

		reset();
		mImageFrame = new PhotoFrame(this, mBitmap);
	}

	/**
	 * 重新设置一下图片
	 */
	private void reset()
	{
		picture.setImageBitmap(mTmpBmp);
		picture.invalidate();
	}

	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
			case R.id.btn_cancel :
				Intent cancelData = new Intent();
				setResult(RESULT_CANCELED, cancelData);
				recycle();
				this.finish();
				break;
			case R.id.btn_ok :

				FileUtils.writeImage(mTmpBmp, photoResPath, 100);

				Intent okData = new Intent();
				okData.putExtra("camera_path", photoResPath);
				setResult(RESULT_OK, okData);
				recycle();
				this.finish();
				break;

			default :
				break;
		}
	}

	private void recycle()
	{
		mTmpBmp.recycle();
	}

	private class PhotoFrameOnClickListener implements OnClickListener
	{

		@Override
		public void onClick(View view)
		{
			switch (view.getId())
			{

				case R.id.photoRes_one :

					mImageFrame.setFrameType(PhotoFrame.FRAME_SMALL);
					mImageFrame.setFrameResources(
							R.drawable.frame_around1_left_top,
							R.drawable.frame_around1_left,
							R.drawable.frame_around1_left_bottom,
							R.drawable.frame_around1_bottom,
							R.drawable.frame_around1_right_bottom,
							R.drawable.frame_around1_right,
							R.drawable.frame_around1_right_top,
							R.drawable.frame_around1_top);
					mTmpBmp = mImageFrame.combineFrameRes();

					break;

				case R.id.photoRes_two :

					mImageFrame.setFrameType(PhotoFrame.FRAME_SMALL);
					mImageFrame.setFrameResources(
							R.drawable.frame_around2_left_top,
							R.drawable.frame_around2_left,
							R.drawable.frame_around2_left_bottom,
							R.drawable.frame_around2_bottom,
							R.drawable.frame_around2_right_bottom,
							R.drawable.frame_around2_right,
							R.drawable.frame_around2_right_top,
							R.drawable.frame_around2_top);
					mTmpBmp = mImageFrame.combineFrameRes();

					break;

				case R.id.photoRes_three :
					mImageFrame.setFrameType(PhotoFrame.FRAME_BIG);
					mImageFrame.setFrameResources(R.drawable.frame_big1);

					mTmpBmp = mImageFrame.combineFrameRes();

					break;

				default :
					break;

			}
			reset();

		}

	}
}
