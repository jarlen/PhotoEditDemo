package com.js.photosdk.filter;

public class NativeFilters {

	static {
		System.loadLibrary("filters");
	}

	/**
	 * 
	 * @param pixels
	 *            图像像素点集
	 * @param width
	 *            图像像素点宽度
	 * @param height
	 *            图像像素点高度
	 * @param factor
	 *            图像滤镜变化程度( 0 < factor < 1)
	 * @return 处理之后的图像素点集
	 */
	public native int[] ToGray(int[] pixels, int width, int height, float factor);

	/**
	 * 
	 * @param pixels
	 *            图像像素点集
	 * @param width
	 *            图像像素点宽度
	 * @param height
	 *            图像像素点高度
	 * @param factor
	 *            马赛克程度( 0 < factor < 30)，数值范围按算法效率而定，越小，效率越低。
	 * @return 处理之后的图像素点集
	 */
	public native int[] ToMosatic(int[] pixels, int width, int height,
			int factor);

	/**
	 * 
	 * @param pixels
	 * @param width
	 * @param height
	 * @param factor
	 * @return
	 */
	public native int[] ToLOMO(int[] pixels, int width, int height, float factor);

	/**
	 * 
	 * @param pixels
	 * @param width
	 * @param height
	 * @param factor
	 * @return 怀旧效果
	 */
	public native int[] ToNostalgic(int[] pixels, int width, int height,
			float factor);

	/**
	 * 
	 * @param pixels
	 * @param width
	 * @param height
	 * @param factor
	 * @return 漫画效果
	 */
	public native int[] ToComics(int[] pixels, int width, int height,
			float factor);

	/**
	 * 
	 * @param pixels
	 * @param width
	 * @param height
	 * @param factor
	 * @return 黑白效果
	 */
	public native int[] ToBlackWhite(int[] pixels, int width, int height,
			float factor);

	/**
	 * 
	 * @param pixels
	 * @param width
	 * @param height
	 * @param factor
	 * @return 反色(底片)
	 */
	public native int[] ToNegative(int[] pixels, int width, int height,
			float factor);

	/**
	 * 
	 * @param pixels
	 * @param width
	 * @param height
	 * @param factor
	 * @return 流年风格
	 */
	public native int[] ToBrown(int[] pixels, int width, int height,
			float factor);

	/**
	 * 
	 * @param pixels
	 * @param width
	 * @param height
	 * @param factor
	 * @return 素描效果---铅笔画
	 */
	public native int[] ToSketchPencil(int[] pixels, int width, int height,
			float factor);

	public native int[] ToSketch(int[] pixels, int width, int height,
			float factor);

	public native int[] ToColorSketch(int[] pixels, int width, int height,
			float factor);

	/**
	 * 
	 * @param pixels
	 * @param width
	 * @param height
	 * @param factor
	 * @return 过度曝光
	 */
	public native int[] ToOverExposure(int[] pixels, int width, int height,
			float factor);

	/**
	 * 采用log算法美白，(x,y) = log((x0,y0)*(β - 1) + 1)/log(β);
	 * 
	 * @param pixels
	 * @param width
	 * @param height
	 * @param beita
	 *            : β
	 * @param factor
	 * @return
	 */
	public native int[] ToWhiteLOG(int[] pixels, int width, int height,
			int beita, float factor);

	//
	//
	// 柔化
	//

	public native int[] ToSoftness(int[] pixels, int width, int height,
			float factor);

	//
	//
	// 霓虹
	//

	public native int[] ToNiHong(int[] pixels, int width, int height,
			float factor);

	//
	//
	// 雕刻
	//

	public native int[] ToCarving(int[] pixels, int width, int height,
			float factor);

	//
	//
	// 浮雕
	//

	public native int[] ToRelief(int[] pixels, int width, int height,
			float factor);

	//
	// 油画
	//
	//

	public native int[] ToRuiHua(int[] pixels, int width, int height,
			float factor);

}
