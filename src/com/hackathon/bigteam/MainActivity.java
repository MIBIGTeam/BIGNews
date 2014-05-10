package com.hackathon.bigteam;

import com.hackaton.util.HttpRequest;
import com.hackaton.util.InitializingFinished;
import com.hackaton.util.NextScreen;
import com.hackaton.util.UrlMaker;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		  button = (Button)findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				NextScreen initializingFinished = new InitializingFinished(ReadNews.class);
				HttpRequest request = new HttpRequest(MainActivity.this, initializingFinished, 2, true);
				String url = UrlMaker.GetArticle();
				request.execute(url);
				Log.i("MainActivity starta url: ", url);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
