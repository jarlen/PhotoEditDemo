package com.jarlen.temptest;

import com.jarlen.picturetest.R;
import com.jarlen.utils.FrameFilterUtils;
import com.js.photosdk.filter.NativeFrameFilters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class FrameFilterActivity extends Activity implements OnClickListener, OnSeekBarChangeListener
{

	private ImageView pictureShow;
	private TextView infoTime;
	
	private SeekBar seekBar;

	Button resetBtn, testBtn;

	String picturePath;
	Bitmap pictureBitmap;

	int mWidth, mHeight;
	
	int[] pixelSrc;
	int[] pixelMask;
	Bitmap maskBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_filter);

		Intent intent = getIntent();

		picturePath = intent.getStringExtra("camera_path");
		
		Options op = new Options();
		op.inSampleSize = 1;
		pictureBitmap = BitmapFactory.decodeFile(picturePath, op);

		mWidth = pictureBitmap.getWidth();
		mHeight = pictureBitmap.getHeight();

		pictureShow = (ImageView) findViewById(R.id.filterPicture);
		pictureShow.setImageBitmap(pictureBitmap);
		
		infoTime = (TextView) findViewById(R.id.info_time);
		
		seekBar = (SeekBar) findViewById(R.id.seekBar);
		seekBar.setOnSeekBarChangeListener(this);

		resetBtn = (Button) findViewById(R.id.reSet);
		resetBtn.setOnClickListener(this);

		testBtn = (Button) findViewById(R.id.testFilter);
		testBtn.setOnClickListener(this);
		
		

		
		
		maskBitmap = BitmapFactory.decodeResource(
				this.getResources(), R.drawable.youhua);

		maskBitmap = FrameFilterUtils.ResizeBitmap(maskBitmap, mWidth, mHeight);
//
//		pixelSrc = new int[mWidth * mHeight];
//		pictureBitmap.getPixels(pixelSrc, 0, mWidth, 0, 0, mWidth,
//				mHeight);
//		pixelMask = new int[mWidth * mHeight];
//		maskBitmap.getPixels(pixelMask, 0, mWidth, 0, 0, mWidth,
//				mHeight);

	}

	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
			case R.id.reSet :

				pictureShow.setImageBitmap(pictureBitmap);

				break;
			case R.id.testFilter :
				
//				pixelSrc = new int[mWidth * mHeight];
//				pictureBitmap.getPixels(pixelSrc, 0, mWidth, 0, 0, mWidth,
//						mHeight);
//				pixelMask = new int[mWidth * mHeight];
//				maskBitmap.getPixels(pixelMask, 0, mWidth, 0, 0, mWidth,
//						mHeight);
//				
//				getFilter(pixelSrc, pixelMask,1);
				
				Bitmap bit = FrameFilterUtils.getTestFilter(pictureBitmap, maskBitmap);
				pictureShow.setImageBitmap(bit);

				break;

			default :
				break;
		}
	}

	public void getFilter(int[] pixels1, int[] pixels2,double factor)
	{
		long time0 = System.currentTimeMillis();
		int[] result = NativeFrameFilters.gettest(pixels1, pixels2, mWidth, mHeight, factor);

		Bitmap tempBitmap = Bitmap.createBitmap(result, mWidth, mHeight,
				Config.ARGB_8888);

		pictureShow.setImageBitmap(tempBitmap);
		
		long time = System.currentTimeMillis() - time0;
		infoTime.setText("w:"+mWidth+" h:"+mHeight+"耗时: "+ time+"ms");
	}
	
	private int progress = 0;

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser)
	{
		// TODO Auto-generated method stub
		this.progress = progress;
		
		
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar)
	{
		// TODO Auto-generated method stub
		
//		pixelSrc = new int[mWidth * mHeight];
//		pictureBitmap.getPixels(pixelSrc, 0, mWidth, 0, 0, mWidth,
//				mHeight);
//		pixelMask = new int[mWidth * mHeight];
//		maskBitmap.getPixels(pixelMask, 0, mWidth, 0, 0, mWidth,
//				mHeight);
//		
//		getFilter(pixelSrc, pixelMask, progress/255.0);
		
	}


	

	


	

}
