package com.hackathon.bigteam;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		 
		Intent i = new Intent(MainActivity.this,AddNews.class);
		startActivity(i);
		
//		button.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				
//				NextScreen initializingFinished = new InitializingFinished(ReadNews.class);
//				HttpRequest request = new HttpRequest(MainActivity.this, initializingFinished, 2, true);
//				String url = UrlMaker.GetArticle(3);
//				request.execute(url);
//				Log.i("MainActivity starts url: ", url);
//				
//			}
//		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
