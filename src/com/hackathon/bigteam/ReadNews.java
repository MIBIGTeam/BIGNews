package com.hackathon.bigteam;

import com.hackaton.model.Article;
import com.hackaton.util.JsonParser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class ReadNews extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_read_news);
		
		ReaderPagerAdapter swipeReader = new ReaderPagerAdapter(ReadNews.this);
		ViewPager pager = (ViewPager) findViewById(R.id.singleArticlePager);
		pager.setAdapter(swipeReader);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read_news, menu);
		return true;
	}

}


class ReaderPagerAdapter extends PagerAdapter{
	
	private ImageView articlePicture;
	private TextView articleHeadline;
	private TextView contentTextView;
	private TextView hashesTextView;
	
	
	private Activity activity;
	
	public ReaderPagerAdapter (Activity activity){
		this.activity = activity;
	}
	
	public Object instatiateItem(View collection, int position){
		
		
		 int resId = 0;
            resId = R.id.scrollView;
            Intent intent = activity.getIntent();
    		String jsons = intent.getStringExtra("jsons");
    		Article article = JsonParser.ParseArticle(jsons);
    		
    		articlePicture = (ImageView) activity.findViewById(R.id.articlePicture);
    		
    		articleHeadline = (TextView) activity.findViewById(R.id.articleHeadline);
    		articleHeadline.setText(article.getArticleHeadline());
    		contentTextView = (TextView) activity.findViewById(R.id.contentTextView);
    		contentTextView.setText(article.getArticleText());
    		hashesTextView = (TextView) activity.findViewById(R.id.hashesTextView);
    		hashesTextView.setText(article.getArticleText());
    		
    		//jsonParser a = JSONPAR.parseartice(jsons);
    		Log.i("TEST: ", jsons);
        
        return activity.findViewById(resId);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == ((View) arg1);
	}
	
	
}
