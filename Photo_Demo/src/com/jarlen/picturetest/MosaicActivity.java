package com.jarlen.picturetest;

import com.js.photosdk.mosaic.DrawMosaicView;
import com.js.photosdk.mosaic.MosaicUtil;
import com.js.photosdk.mosaic.MosaicUtil.MosaicType;
import com.js.photosdk.utils.FileUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MosaicActivity extends Activity implements OnClickListener
{

	private DrawMosaicView mosaic;

	String mPath;
	private int mWidth, mHeight;

	Bitmap srcBitmap = null;

	private ImageButton cancelBtn, okBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_mosaic);

		initView();

		Intent intent = getIntent();
		mPath = intent.getStringExtra("camera_path");
		mosaic.setMosaicBackgroundResource(mPath);

		srcBitmap = BitmapFactory.decodeFile(mPath);

		mWidth = srcBitmap.getWidth();
		mHeight = srcBitmap.getHeight();
		Bitmap bit = MosaicUtil.getMosaic(srcBitmap);

		mosaic.setMosaicResource(bit);
		mosaic.setMosaicBrushWidth(10);

	}

	private void initView()
	{
		mosaic = (DrawMosaicView) findViewById(R.id.mosaic);

		cancelBtn = (ImageButton) findViewById(R.id.btn_cancel);
		cancelBtn.setOnClickListener(this);

		okBtn = (ImageButton) findViewById(R.id.btn_ok);
		okBtn.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		menu.add(0, 1, 1, "花色").setIcon(R.drawable.flower);
		menu.add(0, 2, 2, "马赛克");
		menu.add(0, 3, 3, "毛玻璃");
		menu.add(0, 4, 4, "大小");
		menu.add(0, 5, 5, "橡皮");

		return super.onCreateOptionsMenu(menu);
	}

	int size = 5;
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case 1 :
				Bitmap bit = BitmapFactory.decodeResource(this.getResources(),
						R.drawable.hi4);
				bit = FileUtils.ResizeBitmap(bit, mWidth, mHeight);
				mosaic.setMosaicResource(bit);

				break;

			case 2 :
				Bitmap bitmapMosaic = MosaicUtil.getMosaic(srcBitmap);
				mosaic.setMosaicResource(bitmapMosaic);
				break;

			case 3 :
				Bitmap bitmapBlur = MosaicUtil.getBlur(srcBitmap);
				mosaic.setMosaicResource(bitmapBlur);
				break;

			case 4 :

				if (size >= 30)
				{
					size = 5;
				} else
				{
					size += 5;
				}
				mosaic.setMosaicBrushWidth(size);
				break;

			case 5 :

				mosaic.setMosaicType(MosaicType.ERASER);
				break;

			default :
				break;
		}

		return super.onOptionsItemSelected(item);

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
				
				Bitmap bit = mosaic.getMosaicBitmap();
				
				FileUtils.writeImage(bit, mPath, 100);

				Intent okData = new Intent();
				okData.putExtra("camera_path", mPath);
				setResult(RESULT_OK, okData);
				recycle();
				MosaicActivity.this.finish();

				break;

			default :
				break;
		}

	}

	private void recycle()
	{
		if (srcBitmap != null)
		{
			srcBitmap.recycle();
			srcBitmap = null;
		}
	}

}
