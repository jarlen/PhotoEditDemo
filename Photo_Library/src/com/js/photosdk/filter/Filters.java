package com.js.photosdk.filter;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

/**
 * 
 * @author jarlen
 * 
 *         使用：
 * 
 *         Filters filters = new Filters(bitmap,type); filters.process(factor);
 * 
 */

public class Filters
{

	private int filterType = 0;
	private int[] pixels;

	private NativeFilters nativeFilters;

	private int width, height;

	public Filters(Bitmap bitmap, int type)
	{
		this.filterType = type;
		nativeFilters = new NativeFilters();

		width = bitmap.getWidth();
		height = bitmap.getHeight();

		pixels = new int[width * height];

		bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
	}

	/**
	 * 执行滤镜操作
	 * 
	 * @param factor
	 *            (0 <= factor <= 1)
	 * @return
	 * 
	 *         Bitmap
	 */
	public Bitmap process(float factor)
	{
		int[] newPixels = null;

		switch (filterType)
		{
		/* 黑白效果 */
			case FilterType.FILTER4BlackWhite :

				newPixels = nativeFilters.ToBlackWhite(pixels, width, height,
						factor);
				break;
			/* 褐色效果 */
			case FilterType.FILTER4BROWN :

				newPixels = nativeFilters
						.ToBrown(pixels, width, height, factor);
				break;
			/* 雕刻 */
			case FilterType.FILTER4CARVING :
				newPixels = nativeFilters.ToCarving(pixels, width, height,
						factor);
				break;
			/* 连环画效果 */
			case FilterType.FILTER4COMICS :
				newPixels = nativeFilters.ToComics(pixels, width, height,
						factor);

				break;

			/* 灰色 */
			case FilterType.FILTER4GRAY :
				newPixels = nativeFilters.ToGray(pixels, width, height, factor);
				break;
			/* LOMO效果 */
			case FilterType.FILTER4LOMO :
				newPixels = nativeFilters.ToLOMO(pixels, width, height, factor);
				break;

			/* 马赛克效果 */
			case FilterType.FILTER4MOSATIC :
				int mosatic = (int) (factor * 30);
				newPixels = nativeFilters.ToMosatic(pixels, width, height,
						mosatic);
				break;

			/* 底片效果 */
			case FilterType.FILTER4NEGATIVE :
				newPixels = nativeFilters.ToNegative(pixels, width, height,
						factor);

				break;

			/* 霓虹效果 */
			case FilterType.FILTER4NiHong :
				newPixels = nativeFilters.ToNiHong(pixels, width, height,
						factor);
				break;

			/* 怀旧效果 */
			case FilterType.FILTER4NOSTALGIC :
				newPixels = nativeFilters.ToNostalgic(pixels, width, height,
						factor);
				break;

			/* 过度曝光 */
			case FilterType.FILTER4OVEREXPOSURE :
				newPixels = nativeFilters.ToOverExposure(pixels, width, height,
						factor);
				break;

			/* 浮雕效果 */
			case FilterType.FILTER4RELIEF :
				newPixels = nativeFilters.ToRelief(pixels, width, height,
						factor);
				break;
			/* 锐化效果 */
			case FilterType.FILTER4RUIHUA :
				newPixels = nativeFilters.ToRuiHua(pixels, width, height,
						factor);
				break;
			/* 素描 */
			case FilterType.FILTER4SKETCH :
				newPixels = nativeFilters.ToSketch(pixels, width, height,
						factor);
				break;
			/* 铅笔画 */
			case FilterType.FILTER4SKETCH_PENCIL :
				newPixels = nativeFilters.ToSketchPencil(pixels, width, height,
						factor);
				break;

			/* 柔化效果 */
			case FilterType.FILTER4SOFTNESS :
				newPixels = nativeFilters.ToSoftness(pixels, width, height,
						factor);
				break;

			/* 亮白效果 */
			case FilterType.FILTER4WHITELOG :
				newPixels = nativeFilters.ToWhiteLOG(pixels, width, height,
						FilterType.BeitaOfWhiteLOG, factor);
				break;

			default :
				newPixels = pixels;
				break;
		}

		Bitmap newBitmap = Bitmap.createBitmap(newPixels, width, height,
				Config.ARGB_8888);
		return newBitmap;
	}

}
