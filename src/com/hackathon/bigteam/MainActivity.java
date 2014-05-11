package com.hackathon.bigteam;

import com.hackaton.util.HttpRequest;
import com.hackaton.util.InitializingFinished;
import com.hackaton.util.NextScreen;
import com.hackaton.util.UrlMaker;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends Activity {
	boolean hasBeenOpened = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		 	
		
		NextScreen initializingFinished = new InitializingFinished(ArticlesListActivity.class);
		HttpRequest request = new HttpRequest(MainActivity.this, initializingFinished, 0, false);
		String url = UrlMaker.GetXArticles();
		request.execute(url);
		Log.i("MainActivity starts url: ", url);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
