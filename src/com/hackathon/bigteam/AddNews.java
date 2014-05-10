package com.hackathon.bigteam;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class AddNews extends Activity {

	String picturePath;
	String imageUrl;
	boolean hasPassed;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add_news);
		Parse.initialize(this, "pW2LSNJN29SgorrElj4Ij40WfD5Llvao1OXNf1PS", "gshhHu0PMzLeeLighk4LQqAb3mio8zf0yqCnuXbO");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_news, menu);
		return true;
	}
	
	
	public void publishButtonClicked(View view)throws IOException{		
		
		byte[] image = readInFile(picturePath);
		
		
		ParseFile file = new ParseFile("articlePicture", image);
		
		file.saveInBackground(new SaveCallback() {
			
			@Override
			public void done(ParseException arg0) {
				if(arg0==null){
					hasPassed = true;
				}else{
					hasPassed = false;
					arg0.printStackTrace();					
				}
				
			}
		});
		
		if(hasPassed){
			imageUrl = file.getUrl();
		}
		
		/*ParseObject imgupload = new ParseObject("Image");
		imgupload.put("Image", "picturePath");		
		imgupload.put("ImageFile", file);		
		imgupload.saveInBackground();*/
	}
	
	private byte[] readInFile(String path) throws IOException {
	    // TODO Auto-generated method stub
	    byte[] data = null;
	    File file = new File(path);
	    InputStream input_stream = new BufferedInputStream(new FileInputStream(
	            file));
	    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	    data = new byte[16384]; // 16K
	    int bytes_read;
	    while ((bytes_read = input_stream.read(data, 0, data.length)) != -1) {
	        buffer.write(data, 0, bytes_read);
	    }
	    input_stream.close();
	    return buffer.toByteArray();

	}
	
	
}
