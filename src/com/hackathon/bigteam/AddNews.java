package com.hackathon.bigteam;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.hackaton.util.BitmapDownloader;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class AddNews extends Activity {

	String picturePath;
	static boolean flag = false;
	Bitmap bitmap = null;
	String imageUrl;
	boolean hasPassed = false;
	ImageView headerImage;

	AlertDialog dial;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add_news);
		Parse.initialize(this, "pW2LSNJN29SgorrElj4Ij40WfD5Llvao1OXNf1PS", "gshhHu0PMzLeeLighk4LQqAb3mio8zf0yqCnuXbO");
		headerImage = (ImageView) findViewById(R.id.addPictureImageView);
		
		AlertDialog.Builder dialog = new AlertDialog.Builder(AddNews.this);
		dialog.setMessage("Odaberite odakle želite uèitati sliku?")
	       .setTitle("Uèitavanje");
		dialog.setPositiveButton("Galerija", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               // User clicked OK button
	        	   
	        	   Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
	 			  intent.setType("image/*");
	 			  startActivityForResult(intent, 0);     	   
      	  
	           }
	       });
		
		
		dialog.setNegativeButton("Kamera", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               // User cancelled the dialog
	        	   
	        	   dispatchTakePictureIntent();
	           }
	       });
		
		
		 dial = dialog.create();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_news, menu);
		return true;
	}
	
	public void addPictureButtonClicked (View view) {
		 
		dial.show();
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
	    super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

	    switch(requestCode) { 
	    case 0:
	         if(resultCode == RESULT_OK){ 
	             Uri selectedImage = imageReturnedIntent.getData();
	             String[] filePathColumn = {MediaStore.Images.Media.DATA};

	             Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
	             cursor.moveToFirst();

	             int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	             picturePath = cursor.getString(columnIndex); // file path of selected image
	             bitmap = BitmapFactory.decodeFile(picturePath);
	             headerImage.setImageBitmap(bitmap);
	             cursor.close();
	             
	            Log.i("tu u slici", picturePath);
	            break;
	            
	        }
	    case REQUEST_IMAGE_CAPTURE:
	    	if (resultCode == RESULT_OK) {
	            
	            bitmap = (Bitmap) imageReturnedIntent.getExtras().get("data");
	            
	            flag = true;
	            headerImage.setImageBitmap(bitmap);
	            Log.i("tu u kameri", flag+"");
		        break;
	        }

	    }
	}
	
	static final int REQUEST_IMAGE_CAPTURE = 1;

	private void dispatchTakePictureIntent() {
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
	        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
	    }
	}
	
	
	public void publishButtonClicked(View view)throws IOException{		
		
		byte[] image;
		
		if(!flag){
			image = readInFile(picturePath);
		}else{
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bitmap = BitmapDownloader.Resize(bitmap, 256);
				bitmap.compress(Bitmap.CompressFormat.PNG,100, stream);
			image = stream.toByteArray();
		}
		
		
		final ParseFile file = new ParseFile("articlePicture", image);
		Log.i("Nesto", "tu sam");
		final ProgressDialog prog = new ProgressDialog(this);
		prog.setTitle("Loading");
		prog.setMessage("Ucitavam...");
		prog.show();
		file.saveInBackground(new SaveCallback() {
			
			@Override
			public void done(ParseException arg0) {
				if(arg0==null){
					imageUrl = file.getUrl();
					ParseObject imgupload = new ParseObject("Image");
					imgupload.put("Image", "picturePath");		
					imgupload.put("ImageFile", file);		
					imgupload.saveInBackground();
					Log.i("Nesto", imageUrl);
				}else{
					hasPassed = false;
					arg0.printStackTrace();					
				}				
				runOnUiThread(new Runnable() {					
					@Override
					public void run() {
						prog.dismiss();						
					}
				});
			}
		});
		
		
		
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
