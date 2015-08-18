package com.jarlen.picturetest;

import com.js.photosdk.crop.CropImageType;
import com.js.photosdk.crop.CropImageView;
import com.js.photosdk.utils.FileUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class ImageCropActivity extends Activity implements OnClickListener
{

	private CropImageView cropImage;

	private String mPath = null;

	private ImageButton cancleBtn, okBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.crop_image);

		Intent intent = getIntent();

		mPath = intent.getStringExtra("camera_path");
		Bitmap bit = BitmapFactory.decodeFile(mPath);

		cropImage = (CropImageView) findViewById(R.id.cropmageView);

		cancleBtn = (ImageButton) findViewById(R.id.btn_cancel);
		cancleBtn.setOnClickListener(this);
		okBtn = (ImageButton) findViewById(R.id.btn_ok);
		okBtn.setOnClickListener(this);

		Bitmap hh = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.crop_button);

		cropImage.setCropOverlayCornerBitmap(hh);
		cropImage.setImageBitmap(bit);

		// Bitmap bit =
		// BitmapFactory.decodeResource(this.getResources(),R.drawable.hi0);

		cropImage.setGuidelines(CropImageType.CROPIMAGE_GRID_ON_TOUCH);// 触摸时显示网格

		cropImage.setFixedAspectRatio(false);// 自由剪切

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		menu.add(0, 1, 1, "自由剪切");

		menu.add(0, 2, 2, "1:1剪切");
		menu.add(0, 3, 3, "3:2剪切");
		menu.add(0, 4, 4, "4:3剪切");
		menu.add(0, 5, 5, "16:9剪切");

		menu.add(0, 6, 6, "旋转");

		menu.add(0, 7, 7, "上下翻转");
		menu.add(0, 8, 8, "左右翻转");

		menu.add(0, 9, 9, "执行剪切");

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		switch (item.getItemId())
		{
			case 1 :

				cropImage.setFixedAspectRatio(false);

				break;

			case 2 :
				cropImage.setFixedAspectRatio(true);
				cropImage.setAspectRatio(10, 10);

				break;
			case 3 :

				cropImage.setFixedAspectRatio(true);
				cropImage.setAspectRatio(30, 20);
				break;
			case 4 :
				cropImage.setFixedAspectRatio(true);
				cropImage.setAspectRatio(40, 30);

				break;
			case 5 :

				cropImage.setFixedAspectRatio(true);
				cropImage.setAspectRatio(160, 90);
				break;
			case 6 :

				cropImage.rotateImage(90);

				break;
			case 7 :
				cropImage.reverseImage(CropImageType.REVERSE_TYPE.UP_DOWN);
				break;

			case 8 :
				cropImage.reverseImage(CropImageType.REVERSE_TYPE.LEFT_RIGHT);
				break;

			case 9 :

				Bitmap cropImageBitmap = cropImage.getCroppedImage();
				Toast.makeText(
						this,
						"已保存到相册；剪切大小为 " + cropImageBitmap.getWidth() + " x "
								+ cropImageBitmap.getHeight(),
						Toast.LENGTH_SHORT).show();
				FileUtils.saveBitmapToCamera(this, cropImageBitmap, "crop.jpg");
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
				this.finish();

				break;

			case R.id.btn_ok :

				Bitmap bit = cropImage.getCroppedImage();
				FileUtils.writeImage(bit, mPath, 100);

				Intent okData = new Intent();
				okData.putExtra("camera_path", mPath);
				setResult(RESULT_OK, okData);
				this.finish();

				break;

			default :
				break;
		}

	}

}
