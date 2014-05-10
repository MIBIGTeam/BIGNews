package com.hackathon.bigteam;

import com.hackaton.model.Article;
import com.hackaton.util.JsonParser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.Window;

public class ReadNews extends Activity {

	private Article article;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_read_news);
		
		Intent intent = getIntent();
		String jsons = intent.getStringExtra("jsons");
		article = JsonParser.ParseArticle(jsons);
		Log.i("TEST: ", article.getArticleHeadline());
		Log.i("TEST: ", article.getArticleID());
		Log.i("TEST: ", article.getArticleText());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read_news, menu);
		return true;
	}

}
