package com.hackathon.bigteam;

import com.hackaton.model.Article;
import com.hackaton.util.JsonParser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
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
		pager.setCurrentItem(0);
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
	
	private LayoutInflater inflater;
	private Activity activity;
	
	public ReaderPagerAdapter (Activity activity){

		this.activity = activity;
	}
	
	
	@Override
	public Object instantiateItem(ViewGroup collection, int position){
			View v = LayoutInflater.from(activity).inflate(R.layout.single_article, null);
		
		    int view = R.layout.single_article;
            Intent intent = activity.getIntent();
    		String jsons = intent.getStringExtra("jsons");
    		Log.i("T555EST: ", jsons);
    		Article article = JsonParser.ParseArticle(jsons);
    		Log.i("TEST: ", "tu");
    		articlePicture = (ImageView) v.findViewById(R.id.articlePicture);
    		
    		articleHeadline = (TextView) v.findViewById(R.id.articleHeadline);
    		articleHeadline.setText(article.getArticleHeadline());
    		contentTextView = (TextView) v.findViewById(R.id.contentTextView);
    		contentTextView.setText(article.getArticleText());
    		hashesTextView = (TextView) v.findViewById(R.id.hashesTextView);
    		hashesTextView.setText(article.getArticleDate());
    		
    		//jsonParser a = JSONPAR.parseartice(jsons);
    		((ViewPager) collection).addView(v);
        return v;
       
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		Log.i("TEST: ", "tutututusfdsfsf");
		return arg0 == ((View) arg1);
	}
	
	
}
