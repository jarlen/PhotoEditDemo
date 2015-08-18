package com.js.photosdk.bodywarp;

public class BodyWarp
{
	static
	{
		System.loadLibrary("bodywarp");
	}

	public native int initArray();

	public native int[] warpPhotoFromC(int[] image, int height, int width,
			double max_dist, double orig_x, double orig_y, double cur_x,
			double cur_y);

}
