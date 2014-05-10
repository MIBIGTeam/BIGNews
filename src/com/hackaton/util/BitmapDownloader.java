package com.hackaton.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapDownloader {

	public static Bitmap DownloadBitmap(String url, int hPixels)
    {
    	URL tUrl = null;
		try
		{
			tUrl = new URL(url);
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ******* 1. DOWNLOAD AND RESIZE BITMAP ********
		Bitmap bmp = null;
		try
		{
			Bitmap downloaded = BitmapFactory.decodeStream(tUrl
					.openConnection().getInputStream());
			// keeps the aspect ratio
			bmp = Resize(downloaded, hPixels);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bmp;
    }
    
    public static Bitmap Resize(Bitmap bmp, int hPixels)
    {
			Bitmap retBmp = Bitmap.createScaledBitmap(bmp,
							(int) (((float) bmp.getWidth() / (float) bmp.getHeight()) * hPixels), hPixels, false);
		
		return retBmp;
    }
	
}
