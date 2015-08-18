package com.jarlen.temptest;

import java.io.FileNotFoundException;

import com.jarlen.picturetest.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ImgeditActivity extends Activity
{
	/** Called when the activity is first created. */
	private Bitmap srcBitmap, dstBitmap;
	private String pathName = null;

	private ImageView dstimage = null;
	private SeekBar SaturationseekBar = null;
	private SeekBar BrightnessseekBar = null;
	private SeekBar ContrastseekBar = null;
	private int imgHeight, imgWidth;

	public static final int PICTURE = 0;
	public static final int MAX_WIDTH = 240;
	public static final int MAX_HEIGHT = 240;
	private Uri imageUri;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.duibidu);

		Intent intent = getIntent();

		pathName = intent.getStringExtra("camera_path");
		
		dstimage = (ImageView) findViewById(R.id.dstImageView);
		SaturationseekBar = (SeekBar) findViewById(R.id.Saturationseekbar);
		BrightnessseekBar = (SeekBar) findViewById(R.id.Brightnessseekbar);
		ContrastseekBar = (SeekBar) findViewById(R.id.Contrastseekbar);

		srcBitmap = BitmapFactory.decodeFile(pathName);
		dstimage.setImageBitmap(srcBitmap);
		imgHeight = srcBitmap.getHeight();
		imgWidth = srcBitmap.getWidth();

		dstBitmap = Bitmap.createBitmap(imgWidth, imgHeight, Config.ARGB_8888);

		SaturationseekBar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
				{
					// 当拖动条的滑块位置发生改变时触发该方法
					public void onProgressChanged(SeekBar arg0, int progress,
							boolean fromUser)
					{
						// 创建一个相同尺寸的可变的位图区,用于绘制调色后的图片
						Bitmap bmp = Bitmap.createBitmap(imgWidth, imgHeight,
								Config.ARGB_8888);
						ColorMatrix cMatrix = new ColorMatrix();
						// 设置饱和度
						cMatrix.setSaturation((float) (progress / 100.0));

						Paint paint = new Paint();
						paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));

						Canvas canvas = new Canvas(bmp);
						// 在Canvas上绘制一个已经存在的Bitmap。这样，dstBitmap就和srcBitmap一摸一样了
						canvas.drawBitmap(srcBitmap, 0, 0, paint);

						dstimage.setImageBitmap(bmp);

					}

					public void onStartTrackingTouch(SeekBar bar)
					{
					}

					public void onStopTrackingTouch(SeekBar bar)
					{
					}
				});

		BrightnessseekBar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
				{
					// 当拖动条的滑块位置发生改变时触发该方法
					public void onProgressChanged(SeekBar arg0, int progress,
							boolean fromUser)
					{
						Bitmap bmp = Bitmap.createBitmap(imgWidth, imgHeight,
								Config.ARGB_8888);
						int brightness = progress - 127;
						ColorMatrix cMatrix = new ColorMatrix();
						cMatrix.set(new float[]{1, 0, 0, 0, brightness, 0, 1,
								0, 0, brightness,// 改变亮度
								0, 0, 1, 0, brightness, 0, 0, 0, 1, 0});

						Paint paint = new Paint();
						paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));

						Canvas canvas = new Canvas(bmp);
						// 在Canvas上绘制一个已经存在的Bitmap。这样，dstBitmap就和srcBitmap一摸一样了
						canvas.drawBitmap(srcBitmap, 0, 0, paint);
						dstimage.setImageBitmap(bmp);

					}

					public void onStartTrackingTouch(SeekBar bar)
					{
					}

					public void onStopTrackingTouch(SeekBar bar)
					{
					}
				});

		ContrastseekBar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
				{
					// 当拖动条的滑块位置发生改变时触发该方法
					public void onProgressChanged(SeekBar arg0, int progress,
							boolean fromUser)
					{
						Bitmap bmp = Bitmap.createBitmap(imgWidth, imgHeight,
								Config.ARGB_8888);
						// int brightness = progress - 127;
						float contrast = (float) ((progress + 64) / 128.0);
						ColorMatrix cMatrix = new ColorMatrix();
						cMatrix.set(new float[]{contrast, 0, 0, 0, 0, 0,
								contrast, 0, 0, 0,// 改变对比度
								0, 0, contrast, 0, 0, 0, 0, 0, 1, 0});

						Paint paint = new Paint();
						paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));

						Canvas canvas = new Canvas(bmp);
						// 在Canvas上绘制一个已经存在的Bitmap。这样，dstBitmap就和srcBitmap一摸一样了
						canvas.drawBitmap(srcBitmap, 0, 0, paint);

						dstimage.setImageBitmap(bmp);
					}

					public void onStartTrackingTouch(SeekBar arg0)
					{
						// TODO Auto-generated method stub

					}

					public void onStopTrackingTouch(SeekBar seekBar)
					{
						// TODO Auto-generated method stub
					}
				});
	}

	/**
	 * 需要加载的图片可能是大图，我们需要对其进行合适的缩小处理
	 * 
	 * @param imageUri
	 */
	private Bitmap getSrcImage(Uri imageUri)
	{
		try
		{
			BitmapFactory.Options ops = new BitmapFactory.Options();
			ops.inJustDecodeBounds = true;
			Bitmap bmp = BitmapFactory.decodeStream(this.getContentResolver()
					.openInputStream(imageUri), null, ops);
			int wRatio = (int) Math.ceil(ops.outWidth / (float) MAX_WIDTH);
			int hRatio = (int) Math.ceil(ops.outHeight / (float) MAX_HEIGHT);

			if (wRatio > 1 && hRatio > 1)
			{
				if (wRatio > hRatio)
				{
					ops.inSampleSize = wRatio;
				} else
				{
					ops.inSampleSize = hRatio;
				}
			}

			ops.inJustDecodeBounds = false;
			bmp = BitmapFactory.decodeStream(this.getContentResolver()
					.openInputStream(imageUri), null, ops);

			return bmp;

		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(this.getClass().getName(), e.getMessage());
		}

		return null;
	}
}
