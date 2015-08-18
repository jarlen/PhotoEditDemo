package cn.ffmpeg;

/**
 * 
 * @author jarlen
 * 
 */
public class NativeFFmpeg
{

	static
	{
		System.loadLibrary("ffconvert");
	}

	/**
	 * FFmpegConvertGMp4AndLogo
	 * 
	 * 功能：此函数接口支持为mp4文件添加水印图层需要添加的水印图片是一张png图片 并且添加的位置是mp4视频文件的左上角坐标（0,0）
	 * 
	 * @param jin_mp4file
	 *            源mp4文件
	 * 
	 * @param jadd_logo
	 *            要添加的水印的png图片
	 * 
	 * @param jout_mp4file
	 *            添加水印后的mp4输出文件
	 * 
	 * @return
	 * 
	 *         返回值: 为 0成功,为 非0出错。 说明:
	 *         此函数接口支持为mp4文件添加水印图层需要添加的水印图片是一张png图片并且添加的位置是mp4视频文件的左上角坐标（0,0）
	 * 
	 * @sample 将 / mp4下的Mp4.mp4文件添加上/logo/05.png水印
	 *         FFmpegConvertGMp4AndLogo(“/mp4/Mp4.mp4”, “/logo/05.png”,
	 *         /mp4/logo.mp4); 添加完成会在/mp4下生成带有水印的logo.mp4视频文件
	 * 
	 */

	public native int FFmpegConvertGMp4AndLogo(String jin_mp4file,
			String jadd_logo, String jout_mp4file);

	/**
	 * FFmpegConvertGMp4ToMp3 功能：从mp4文件中提取出音频保存成mp3文件
	 * 
	 * 
	 * @param jin_mp4file
	 *            源mp4文件
	 * @param jstartTimer
	 *            开始位置(即从源mp4文件的那个位置开始两种表示方式（1.时间表达00:00:03、2.直接写秒数）) （注：
	 *            可传入字符串“NULL”表示不使用此参数，但是传入的字符串“NULL”必须为字符串“NULL”，大小写均可）
	 * 
	 * @param jlength
	 *            提取长度（即提取音频从源mp4文件的jstartTimer处开始jlength的长度的声音），单位秒。 (注：
	 *            可传入字符串“NULL”表示不使用此参数，但是传入的字符串“NULL”必须为字符串“NULL”，大小写均可)
	 * 
	 * @param jout_mp3file
	 * 
	 *            提取完成要保存成的mp3文件
	 * 
	 * @return 0成功,为 非0出错 说明: 此函数接口支持从mp4视频文件中提取出全部或一部分的音频保存成mp3声音文件
	 * 
	 * @sample
	 * 
	 *         从 / mp4下的Mp4.mp4视频文件中提取出从第3秒处开始到8秒的音频保存成/mp3/mp3声音文件
	 *         FFmpegConvertGMp4ToMp3(“/mp4/Mp4.mp4”, “00:00:03” , “5”,
	 *         “/mp3/mp3.mp3”); 或者 FFmpegConvertGMp4ToMp3(“/mp4/Mp4.mp4”, “3” ,
	 *         “5”, “/mp3/mp3.mp3”); 或者 参数 “NULL”表示不使用此参数
	 *         FFmpegConvertGMp4ToMp3(“/mp4/Mp4.mp4”, “NULL” , “NULL”,
	 *         “/mp3/mp3.mp3”); 执行文成会在/mp3下生成mp3.mp3声音文件
	 * 
	 */
	public native int FFmpegConvertGMp4ToMp3(String jin_mp4file,
			String jstartTimer, String jlength, String jout_mp3file);

	/**
	 * 功能：给无声的mp4视频文件添加声音
	 * 
	 * @param jin_mp4file
	 *            源mp4文件
	 * 
	 * @param jstartTimer
	 *            开始位置(即要添加的声音从jadd_soundfile(mp3声音文件)的那个位置开始，两种表示方式（1.时间表达00:00
	 *            :03、2.直接写秒数）) （注：
	 *            可传入字符串“NULL”表示不使用此参数，但是传入的字符串“NULL”必须为字符串“NULL”，大小写均可）
	 * 
	 * @param jlength
	 *            添加的音频长度（即添加的声音从jadd_soundfile(mp3声音文件)
	 *            jstartTimer处开始jlength的长度的声音），单位秒 （注：
	 *            可传入字符串“NULL”表示不使用此参数，但是传入的字符串“NULL”必须为字符串“NULL”，大小写均可）
	 * 
	 * @param jadd_soundfile
	 *            要添加的声音文件（mp3文件）
	 * 
	 * @param jout_mp4file
	 *            添加声音完成后生成带声音的mp4视频文件
	 * 
	 * @return 为 0成功,为 非0出错。 说明: 此函数接口支持为无声的mp4文件添加一段声音。
	 * 
	 * @sample
	 * 
	 *         为 / mp4下的Mp4.mp4视频文件添加一段声音
	 *         从/mp3/mp3.mp3声音文件的第3秒处开始到8秒的并将生成的mp4视频文件保存成/mp4/outmp4.mp4视频文件
	 *         FFmpegConvertGMp4AndAcc(“/mp4/Mp4.mp4”, “00:00:03” , “5”,
	 *         “/mp3/mp3.mp3”,“/mp4/outmp4.mp4”); 或者
	 *         FFmpegConvertGMp4AndAcc(“/mp4/Mp4.mp4”, “3” , “5”,
	 *         “/mp3/mp3.mp3”,“/mp4/outmp4.mp4”); 或者
	 * 
	 *         参数 “NULL”表示不使用此参数 FFmpegConvertGMp4AndAcc(“/mp4/Mp4.mp4”, “NULL”
	 *         , “NULL”, “/mp3/mp3.mp3”,“/mp4/outmp4.mp4”);
	 *         执行文成会在/mp4下生成带有声音的mp4.mp4视频文件
	 */
	public native int FFmpegConvertGMp4AndAcc(String jin_mp4file,
			String jstartTimer, String jlength, String jadd_soundfile,
			String jout_mp4file);

	/**
	 * 
	 * 功能：从一个mp4视频文件中截取其中一段保存成成mp4文件同时把声音过滤掉
	 * 
	 * @param jin_mp4file
	 *            源mp4文件
	 * @param jstartTimer
	 *            开始位置(即从源mp4文件的那个位置开始两种表示方式（1.时间表达00:00:03、2.直接写秒数）) ， （注：
	 *            可传入字符串“NULL”表示不使用此参数，但是传入的字符串“NULL”必须为字符串“NULL”，大小写均可）
	 * 
	 * @param jlength
	 *            截取长度（即截取视频从源mp4文件的jstartTimer处开始jlength的长度的视频），单位秒， （注：
	 *            可传入字符串“NULL”表示不使用此参数，但是传入的字符串“NULL”必须为字符串“NULL”，大小写均可）
	 * 
	 * @param jout_mp4file
	 *            截取完成后生成无声音的mp4视频文件
	 * 
	 * @param flag
	 *            是否同时过滤掉声音（YES：过滤掉，NO：保留声音，不过滤掉）
	 * 
	 * @return 为 0成功,为 非0出错。 说明: 此函数接口支持从一个mp4视频文件中截取其中一段保存成成mp4文件同时把声音过滤掉
	 * 
	 * @sample
	 * 
	 *         截取 / mp4下的Mp4.mp4视频文件的第3秒处开始到8秒的的视频并过滤掉声音并将截取生成的mp4视频文件保存成/mp4/
	 *         outmp4.mp4视频文件 FFmpegConvertGMp4ToMp4 (“/mp4/Mp4.mp4”, “00:00:03”
	 *         , “5”, “/mp4/outmp4.mp4”，“YES”); // 不保留声 或者 保留声音
	 *         FFmpegConvertGMp4ToMp4 (“/mp4/Mp4.mp4”, “3” , “5”,
	 *         “/mp4/outmp4.mp4”,“NO”); 执行文成会在/mp4下生成截取过来的无声音的outmp4.mp4视频文件
	 * 
	 */
	public native int FFmpegConvertGMp4ToMp4(String jin_mp4file,
			String jstartTimer, String jlength, String jout_mp4file, String flag);

	/**
	 * 
	 * 功能：从一个mp4视频文件中提取帧并保存成png图片或jpg图片
	 * 
	 * @param jin_mp4file
	 *            源mp4文件
	 * 
	 * @param jstartTimer
	 *            开始位置(即从源mp4文件的那个位置开始两种表示方式（1.时间表达00:00:03、2.直接写秒数）)， （注：
	 *            可传入字符串“NULL”表示不使用此参数，但是传入的字符串“NULL”必须为字符串“NULL”，大小写均可）
	 * 
	 * @param jlength
	 *            提取视频长度（即提取帧在源mp4文件的jstartTimer处开始jlength的长度的视频内），单位秒。 （注：
	 *            可传入字符串“NULL”表示不使用此参数，但是传入的字符串“NULL”必须为字符串“NULL”，大小写均可）
	 * 
	 * @param jframerate
	 *            帧率（即一秒提取几帧） （注：
	 *            可传入字符串“NULL”表示不使用此参数，但是传入的字符串“NULL”必须为字符串“NULL”，大小写均可）
	 * 
	 * @param jout_jpgOpngfile
	 *            输出的图片(输出的图片的表达方式img%2d.jpg, 即表示生成的图片从img00.jpg，img01.jpg …
	 *            等，或者 img%3d.jpg,即表示生成的图片从img000.jpg，img001.jpg … 等)
	 * 
	 * 
	 * @return 为 0成功,为 非0出错。 说明:
	 *         此函数接口支持从一个mp4视频文件中提取帧并保存成png图片或jpg图片，保存是是按一定的规律排列的。
	 * 
	 * @sample
	 * 
	 *         将/ mp4下的Mp4.mp4视频文件的第3秒处开始到8秒的的视频每秒钟提取两帧保存成/photo/下一系列图片。
	 *         FFmpegConvertGMp4ToJpgOPng(“/mp4/Mp4.mp4”, “00:00:03” , “5” ,
	 *         “2”, “/photo/image%2d.jpg”); 或者
	 *         FFmpegConvertGMp4ToJpgOPng(“/mp4/Mp4.mp4”, “3” , “5” , “2”,
	 *         “/photo/image%2d.jpg”);
	 *         执行成功会在/photo/下生成一系列图片，image00.jpg，image01.jpg，image02.jpg …
	 * 
	 */
	public native int FFmpegConvertGMp4ToJpgOPng(String jin_mp4file,
			String jstartTimer, String jlength, String jframerate,
			String jout_jpgOpngfile);

	/**
	 * 
	 * 功能：将一系列按一定规律命名的图片按一定帧率合成一个mp4文件无声音
	 * 
	 * @param jin_jpgfile
	 *            按一定规律命名的图片
	 * 
	 * @param jframerate
	 *            帧率（即一秒几帧） （注：
	 *            可传入字符串“NULL”表示不使用此参数，但是传入的字符串“NULL”必须为字符串“NULL”，大小写均可）
	 * 
	 * @param jout_mp4file
	 *            按照 jframerate 帧率合成的mp4 文件
	 * 
	 * @return 返回值: 为 0成功,为 非0出错。 说明: 此函数接口支持将一系列按一定规律命名的图片按一定帧率合成一个mp4文件无声音
	 * 
	 * @sample
	 * 
	 *         将/photo/下按照一定命名规律的一系列图片按照每秒两帧速率合成/mp4/下的mp4.mp4视频文件。
	 *         FFmpegConvertGMJpgOPngToMp4 (“/photo/image%2d.jpg”, “2”,
	 *         “/mp4/Mp4.mp4”); 或者 FFmpegConvertGMJpgOPngToMp4
	 *         (“/photo/image%2d.jpg”, “2”, “/mp4/Mp4.mp4”);
	 *         执行成功会在/mp4/下生成mp4.mp4视频文件每秒播放两帧
	 * 
	 */
	public native int FFmpegConvertGMJpgOPngToMp4(String jin_jpgfile,
			String jframerate, String jout_mp4file);

	/**
	 * 
	 * 功能：将一系列按一定规律命名的图片按一定帧率加上一段mp3声音文件合成一个mp4视频文件。
	 * 
	 * @param jin_jpgfile
	 *            按一定规律命名的图片
	 * 
	 * @param jframerate
	 *            帧率（即一秒几帧）。 （注：
	 *            可传入字符串“NULL”表示不使用此参数，但是传入的字符串“NULL”必须为字符串“NULL”，大小写均可）
	 * 
	 * @param jstartTimer
	 *            开始位置(即要添加的声音从jadd_soundfile(mp3声音文件)的那个位置开始，两种表示方式（1.时间表达00:00
	 *            :03、2.直接写秒数）)。 （注：
	 *            可传入字符串“NULL”表示不使用此参数，但是传入的字符串“NULL”必须为字符串“NULL”，大小写均可）
	 * 
	 * @param jlength
	 *            添加的音频长度（即添加的声音从jadd_soundfile(mp3声音文件)
	 *            jstartTimer处开始jlength的长度的声音），单位秒。 （注：
	 *            可传入字符串“NULL”表示不使用此参数，但是传入的字符串“NULL”必须为字符串“NULL”，大小写均可）
	 * 
	 * @param jadd_soundfile
	 *            要添加的声音文件（mp3文件）
	 * 
	 * @param jout_mp4file
	 *            图片加上声音合成的mp4视频文件
	 * 
	 * @return 返回值: 为 0成功,为 非0出错。 说明:
	 *         此函数接口支持将一系列按一定规律命名的图片按一定帧率加上一段mp3声音文件合成一个mp4视频文件
	 * 
	 * @sample
	 * 
	 *         将/photo/下按照一定命名规律的一系列图片按照每秒两帧速率再加上/mp3下的mp3.mp3从第3秒处开始到8秒的声合成/mp4
	 *         /下的mp4.mp4视频文件。 FFmpegConvertGMJpgOPngToMp4
	 *         (“/photo/image%2d.jpg”, “2”,“00:00:03”, “5”, “/mp3/mp3.mp3”,
	 *         “/mp4/Mp4.mp4”); 或者 FFmpegConvertGMJpgOPngToMp4
	 *         (“/photo/image%2d.jpg”, “2”,“3” , “5”, “/mp3/mp3.mp3”,
	 *         “/mp4/Mp4.mp4”); 执行成功会在/mp4/下生成mp4.mp4有声音的视频文件每秒播放两帧
	 * 
	 */
	public native int FFmpegConvertGMJpgOPngAndMp3ToMp4(String jin_jpgfile,
			String jframerate, String jstartTimer, String jlength,
			String jadd_soundfile, String jout_mp4file);

	/**
	 * 
	 * 功能：从一个mp4视频文件中截取其中一段转换成GIF图像文件
	 * 
	 * @param jin_mp4file
	 *            源mp4文件
	 * 
	 * @param jbrate
	 *            码率(控制画质，可以采用1500，码率越高画质越好，但是文件越大)。 （注：此参数 可传入字符串 “1”
	 *            表示不使用此参数使用默认码率，传入字符串 “NULL”表示 使用1500 码率，或传入 人为指定的码率）
	 * 
	 * @param jframerate
	 *            帧率（即一秒几帧）。 （注：
	 *            可传入字符串“NULL”表示不使用此参数，但是传入的字符串“NULL”必须为字符串“NULL”，大小写均可）
	 * 
	 * @param jstartTimer
	 *            开始位置(即从源mp4文件的那个位置开始两种表示方式（1.时间表达00:00:03、2.直接写秒数）)。 （注：
	 *            可传入字符串“NULL”表示不使用此参数，但是传入的字符串“NULL”必须为字符串“NULL”，大小写均可）
	 * 
	 * @param jlength
	 *            截取长度（即截取视频从源mp4文件的jstartTimer处开始jlength的长度的视频），单位秒。 （注：
	 *            可传入字符串“NULL”表示不使用此参数，但是传入的字符串“NULL”必须为字符串“NULL”，大小写均可）
	 * 
	 * @param jout_giffile
	 *            截取转换完成后生成GIF图像文件
	 * 
	 * @return 返回值: 为 0成功,为 非0出错。 说明: 此函数接口支持从一个mp4视频文件中截取其中一段转换成GIF图像文件。
	 * 
	 * @sample:
	 * 
	 *          截取 /
	 *          mp4下的Mp4.mp4视频文件从第3秒处开始到8秒地方的视频转换成1500的码率每秒2帧的GIF图像文件并保存到/GIF/下.
	 *          FFmpegConvertGMp4ToGif (“/mp4/Mp4.mp4”, “1500” , “2” ,
	 *          “00:00:03” , “5”, “/GIF/outgif.gif”); 或者 FFmpegConvertGMp4ToGif
	 *          (“/mp4/Mp4.mp4”, “1500” , “2” , “3” , “5”, “/GIF/ outgif.gif”);
	 *          执行文成会在/ GIF下生成outgif.gif 文件
	 * 
	 */
	public native int FFmpegConvertGMp4ToGif(String jin_mp4file, String jbrate,
			String jframerate, String jstartTimer, String jlength,
			String jout_giffile);

	/**
	 * 
	 * 功能：采用一定的码率对mp4文件 和GIF文件 互相转换。 mp4和gif的互转 可设置 码率 如采用1500码率可传入NULL
	 * 
	 * @param jin_file
	 *            源mp4文件或者 源GIF文件
	 * 
	 * @param jbrate
	 *            码率(控制画质，可以采用1500，码率越高画质越好，但是文件越大) （注：此参数 可传入字符串 “1”
	 *            表示不使用此参数使用默认码率，传入字符串 “NULL”表示 使用1500 码率，或传入 人为指定的码率）
	 * 
	 * @param jout_file
	 *            转换生成的mp4文件或者GIF文件。
	 * 
	 * @return
	 * 
	 *         返回值: 为 0成功,为 非0出错。 说明: 此函数接口支持采用一定的码率对mp4文件 和GIF文件 互相转换。
	 * 
	 * @sample:
	 * 
	 *          将/ mp4下的Mp4.mp4视频文件转换成1500的码率的GIF图像文件并保存到/GIF/下的outgif.gif
	 *          将/GIF/下的outgif.gif文件转换成1500的码率的mp4视频文件并保存到/ mp4下的Mp4.mp4
	 *          FFmpegConvertGMChangeBitRate(“/mp4/Mp4.mp4”, “1500” ,
	 *          “/GIF/outgif.gif”); 或者 FFmpegConvertGMChangeBitRate(“/GIF/
	 *          outgif.gif”, “1500” , “/mp4/Mp4.mp4”); 或者 不使用 此参数 使用 默认码率
	 *          FFmpegConvertGMChangeBitRate(“/GIF/ outgif.gif”, “1” ,
	 *          “/mp4/Mp4.mp4”); 或者 使用1500 码率
	 *          FFmpegConvertGMChangeBitRate(“/GIF/ outgif.gif”, “NULL” ,
	 *          “/mp4/Mp4.mp4”); 执行文成会在/ GIF下生成outgif.gif 文件或者在/
	 *          mp4下生成Mp4.mp4视频文件。
	 * 
	 */
	public native int FFmpegConvertGMChangeBitRate(String jin_file,
			String jbrate, String jout_file);

}
