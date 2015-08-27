package com.jarlen.temptest;

import com.jarlen.picturetest.R;
import com.js.photosdk.utils.PhotoUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class PhotoRotate extends Activity
{
	
	private ImageView picture;
	private Button testBtn;
	
	private String pathName;
	
	private Bitmap srcBitmap ;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		
		Intent intent = getIntent();

		pathName = intent.getStringExtra("camera_path");
		
		picture = (ImageView) findViewById(R.id.content);
		srcBitmap = BitmapFactory.decodeFile(pathName);
		
		picture.setImageBitmap(srcBitmap);
		
		
		
		findViewById(R.id.testBtn).setOnClickListener(new OnClickListener()
		{
			
			Bitmap bit = srcBitmap;
			float dee = 0f;
			@Override
			public void onClick(View v)
			{
				
				bit = PhotoUtils.rotateImage(bit, 90);
				RotateAnimation rotateAnimation = new RotateAnimation(dee, dee+90);
				rotateAnimation.setDuration(300);
				dee +=90;
				if(dee > 360)
				{
					dee = 0;
				}
				//picture.setAnimation(rotateAnimation);
				
				picture.setImageBitmap(bit);
			}
		});
		
		
	}
}
