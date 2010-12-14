package com.subtitleparser.subtypes;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;

import com.subtitleparser.MalformedSubException;
import com.subtitleparser.SubData;
import com.subtitleparser.SubtitleApi;
import com.subtitleparser.SubtitleParser;

class IdxSubApi extends SubtitleApi
{
	native static RawData getIdxsubRawdata(int millisec); 
	native void setIdxFile(String name);
	public  static int subtitle_file_position = 0;
	public  static int subtitle_delay = 0;
	private static Bitmap bf_show = null;
	private static RawData inter_data = null;
	 private Bitmap bitmap=null;
	 private String filename;
	 IdxSubApi(String name)
	 {
		 filename =name;
		 setIdxFile(filename);
	 };

	 public SubData getdata(int millisec )
	 {
		 //add  value to bitmap
		 //add  value to begingtime,endtime
		 inter_data = getIdxsubRawdata(millisec);
		 if(inter_data != null){
			Log.i("InSubApi", "-----------------------getdata-----"+inter_data.width +inter_data.height );
		 	bf_show = Bitmap.createBitmap(inter_data.rawdata, inter_data.width,
				inter_data.height, Config.ARGB_8888);
			
			Log.i("SubData",	"time b: " + millisec+"  e:"+inter_data.sub_delay);
//			File myCaptureFile = new File("/sdcard/idxsubtmp" + ".jpg");
//			try {
//				myCaptureFile.createNewFile();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//			BufferedOutputStream bos;
//			try {
//				Log.i("","save file begin!!!!!!!!!!!!!!!!!!!");
//
//				bos = new BufferedOutputStream(
//				                                         new FileOutputStream(myCaptureFile));
//				bf_show.compress(Bitmap.CompressFormat.JPEG, 80, bos);
//				bos.flush();
//				bos.close();
//				Log.i("","save file ok!!!!!!!!!!!!!!!!!!!");
//				
//	        } catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}


			
			
			
			
			return new SubData( bf_show, millisec,millisec+3000);
		 }else
		 {
//			Log.i("SubData",	"get return null");
//			int[] data = new int[1];
//			Arrays.fill(data, 0x55555500);
//			bitmap= Bitmap.createBitmap( data,  1,  1, Config.ARGB_8888  ) ;	
//	 		return new SubData( bitmap, millisec, millisec+300);

//			Log.i("InSubApi", "------------getdata2-----------" );
			int[] data = new int[1];
			Arrays.fill(data, 0x99999900);    	
			bitmap= Bitmap.createBitmap( data,  1,  1, Config.ARGB_8888  ) ;
 			return new SubData( bitmap, millisec,millisec+300);
		 
		 }
	 };
}


public class IdxSubParser implements SubtitleParser{
	
	public SubtitleApi parse(String filename) throws MalformedSubException{
		//call jni to init parse;
		return new IdxSubApi(filename);
	};

	public SubtitleApi parse(String inputString,String st2) throws MalformedSubException{
		return null;
	};
}