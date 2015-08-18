package com.js.photosdk.filter;

public class NativeFrameFilters {

	static
	{
		System.loadLibrary("framefilters");
	}
	
	public static native int getLayerBlendingScreen(int baseColor,int overlayColor,double factor);
	
	public static native int[] gettest(int[] base,int[] overlay,int width,int height,double factor);
}
