package com.jarlen.temptest;

import com.jarlen.picturetest.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class EnhanceActivity extends Activity implements OnSeekBarChangeListener
{
	
	private ImageView pictureShow;
	
	private SeekBar seekBar;
	
	
	String picturePath;
	Bitmap pictureBitmap;

	int mWidth, mHeight;

	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_enhance);
		
		Intent intent = getIntent();

		picturePath = intent.getStringExtra("camera_path");
		pictureBitmap = BitmapFactory.decodeFile(picturePath);

		mWidth = pictureBitmap.getWidth();
		mHeight = pictureBitmap.getHeight();
		
		
		pictureShow = (ImageView) findViewById(R.id.enhancePicture);
		
		pictureShow.setImageBitmap(pictureBitmap);
		seekBar = (SeekBar) findViewById(R.id.contrast);
		seekBar.setOnSeekBarChangeListener(this);
	}


	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser)
	{
		// TODO Auto-generated method stub
		
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
		
	}
	
	
	
	
}
