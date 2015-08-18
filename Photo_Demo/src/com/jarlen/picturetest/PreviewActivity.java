package com.jarlen.picturetest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class PreviewActivity extends Activity  {
	ImageView preView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preview);
		String path = getIntent().getStringExtra("path");
		preView = (ImageView) findViewById(R.id.preView);
		Bitmap bmp = BitmapFactory.decodeFile(path);
		preView.setImageBitmap(bmp);
	}

}
