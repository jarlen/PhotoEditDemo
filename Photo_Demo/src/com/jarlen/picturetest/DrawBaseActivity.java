package com.jarlen.picturetest;

import java.util.Timer;
import java.util.TimerTask;

import com.js.photosdk.operate.OperateUtils;
import com.js.photosdk.scrawl.*;
import com.js.photosdk.utils.FileUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DrawBaseActivity extends Activity implements OnClickListener
{

	private DrawingBoardView drawView;

	ScrawlTools casualWaterUtil = null;

	private LinearLayout drawLayout;

	String mPath;

	private ImageButton cancelBtn, okBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// initialDrawAttribute();
		setContentView(R.layout.layout_draw);

		drawView = (DrawingBoardView) findViewById(R.id.drawView);
		drawLayout = (LinearLayout) findViewById(R.id.drawLayout);

		cancelBtn = (ImageButton) findViewById(R.id.btn_cancel);
		cancelBtn.setOnClickListener(this);

		okBtn = (ImageButton) findViewById(R.id.btn_ok);
		okBtn.setOnClickListener(this);

		Intent intent = getIntent();

		mPath = intent.getStringExtra("camera_path");

		timer.schedule(task, 10, 1000);

	}

	final Handler myHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			if (msg.what == 1)
			{
				if (drawLayout.getWidth() != 0)
				{
					// Log.i("jarlen", drawLayout.getWidth() + "");
					// Log.i("jarlen", drawLayout.getHeight() + "");
					// 取消定时器
					timer.cancel();
					compressed();
				}
			}
		}
	};

	Timer timer = new Timer();
	TimerTask task = new TimerTask()
	{
		public void run()
		{
			Message message = new Message();
			message.what = 1;
			myHandler.sendMessage(message);
		}
	};

	private void compressed()
	{

		OperateUtils operateUtils = new OperateUtils(this);

		// Bitmap bit = BitmapFactory.decodeResource(this.getResources(),
		// R.drawable.river);
		Bitmap bit = BitmapFactory.decodeFile(mPath);

		Bitmap resizeBmp = operateUtils.compressionFiller(bit, drawLayout);

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				resizeBmp.getWidth(), resizeBmp.getHeight());

		drawView.setLayoutParams(layoutParams);

		casualWaterUtil = new ScrawlTools(this, drawView, resizeBmp);

		Bitmap paintBitmap = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.crayon);

		// int[] res = new int[]{
		// R.drawable.stamp0star,R.drawable.stamp1star,R.drawable.stamp2star,R.drawable.stamp3star
		// };

		casualWaterUtil.creatDrawPainter(DrawAttribute.DrawStatus.PEN_WATER,
				paintBitmap, 0xffadb8bd);

		// casualWaterUtil.creatStampPainter(DrawAttribute.DrawStatus.PEN_STAMP,res,0xff00ff00);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO Auto-generated method stub
		menu.add(0, 1, 1, "画笔1");
		menu.add(0, 2, 2, "画笔2");
		menu.add(0, 3, 3, "画笔大小");
		menu.add(0, 4, 4, "画笔颜色");
		menu.add(0, 5, 5, "贴图");
		menu.add(0, 6, 6, "橡皮擦");

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO Auto-generated method stub

		switch (item.getItemId())
		{

			case 1 :
				Bitmap paintBitmap1 = BitmapFactory.decodeResource(
						this.getResources(), R.drawable.marker);
				casualWaterUtil.creatDrawPainter(
						DrawAttribute.DrawStatus.PEN_WATER, paintBitmap1,
						0xffadb8bd);

				break;
			case 2 :

				Bitmap paintBitmap2 = BitmapFactory.decodeResource(
						this.getResources(), R.drawable.crayon);
				casualWaterUtil.creatDrawPainter(
						DrawAttribute.DrawStatus.PEN_CRAYON, paintBitmap2,
						0xffadb8bd);
				break;
			case 3 :

				Options option = new Options();
				option.inSampleSize = 2;
				Bitmap paintBitmap3 = BitmapFactory.decodeResource(
						this.getResources(), R.drawable.marker, option);
				casualWaterUtil.creatDrawPainter(
						DrawAttribute.DrawStatus.PEN_WATER, paintBitmap3,
						0xffadb8bd);
				break;

			case 4 :

				Bitmap paintBitmap4 = BitmapFactory.decodeResource(
						this.getResources(), R.drawable.marker);
				casualWaterUtil.creatDrawPainter(
						DrawAttribute.DrawStatus.PEN_WATER, paintBitmap4,
						0xff002200);
				break;

			case 5 :

				int[] res = new int[]{R.drawable.stamp0star,
						R.drawable.stamp1star, R.drawable.stamp2star,
						R.drawable.stamp3star};

				casualWaterUtil.creatStampPainter(
						DrawAttribute.DrawStatus.PEN_STAMP, res, 0xff00ff00);

				break;

			case 6 :
				Bitmap paintBitmap6 = BitmapFactory.decodeResource(
						this.getResources(), R.drawable.eraser);

				casualWaterUtil.creatDrawPainter(
						DrawAttribute.DrawStatus.PEN_ERASER, paintBitmap6,
						0xffadb8bd);
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

				Bitmap bit = casualWaterUtil.getBitmap();

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

	private void recycle()
	{

	}
}
