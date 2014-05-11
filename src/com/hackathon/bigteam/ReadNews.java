package com.hackathon.bigteam;

import java.util.List;

import com.hackaton.model.Article;
import com.hackaton.util.HttpRequest;
import com.hackaton.util.JsonParser;
import com.hackaton.util.Session;
import com.hackaton.util.UrlMaker;
import com.squareup.picasso.Picasso;

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
import android.widget.Toast;

public class ReadNews extends Activity {
	static int id;
	static List<Article> articles;
	static int current;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_read_news);

		ReaderPagerAdapter swipeReader = new ReaderPagerAdapter(ReadNews.this,
				articles.size(), articles);
		ViewPager pager = (ViewPager) findViewById(R.id.singleArticlePager);
		pager.setAdapter(swipeReader);

		for (int i = 0; i < articles.size(); i++) {
			if (id == articles.get(i).getArticleID()) {
				pager.setCurrentItem(i);
				current = articles.get(i).getArticleID();
			}
		}

	}

	public void onClickLike(View view) {
		Log.i("lajk", current + "" + "clanak: " + String.valueOf(current)
				+ " broj: " + String.valueOf(1));
		if (Session.getLikesList(ReadNews.this) != null) {
			
			Log.i("tu san upa", Session.getLikesList(ReadNews.this));
			String likeId = Session.getLikesList(ReadNews.this);
			String[] splitted = likeId.split(";");
			boolean flag = false;
			for (int i = 0; i < splitted.length; i++) {
				Log.i("tu san upa", splitted[i]);
				if (splitted[i].equals(String.valueOf(current))) {
					Toast.makeText(ReadNews.this,
							"Veæ ste lajkali ovaj èlanak!", Toast.LENGTH_LONG)
							.show();
					flag = true;
					break;
				}
			}

			if (!flag) {
				StringBuffer sb = new StringBuffer();
				sb.append(likeId);
				sb.append(';');
				sb.append(current);
				Session.setLikes(ReadNews.this, sb.toString());
				HttpRequest hp = new HttpRequest(ReadNews.this, null, 0, false);
				String url = UrlMaker.UpdateRating(String.valueOf(current),
						String.valueOf(1));
				hp.execute(url);
			}
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append(current);
			sb.append(';');
			Session.setLikes(ReadNews.this, sb.toString());
			HttpRequest hp = new HttpRequest(ReadNews.this, null, 0, false);
			String url = UrlMaker.UpdateRating(String.valueOf(current),
					String.valueOf(1));
			hp.execute(url);
		}

	}


	public void onClickDislike(View view) {
		Log.i("dislajk", current + "");
		if (Session.getDisLikesList(ReadNews.this) != null) {
			String likeId = Session.getDisLikesList(ReadNews.this);
			String[] splitted = likeId.split(";");
			boolean flag = false;
			for (int i = 0; i < splitted.length; i++) {
				if (splitted[i].equals(String.valueOf(current))) {
					Toast.makeText(ReadNews.this,
							"Veæ ste dislajkali ovaj èlanak!",
							Toast.LENGTH_LONG).show();
					flag = true;
					break;
				}
			}
			if (!flag) {
				StringBuffer sb = new StringBuffer();
				sb.append(likeId);
				sb.append(';');
				sb.append(current);
				Session.setDisLikes(ReadNews.this, sb.toString());
				HttpRequest hp = new HttpRequest(ReadNews.this, null, 0, false);
				String url = UrlMaker.UpdateRating(String.valueOf(current),
						String.valueOf(-1));
				hp.execute(url);

			}
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append(current);
			sb.append(';');
			Session.setDisLikes(ReadNews.this, sb.toString());
			HttpRequest hp = new HttpRequest(ReadNews.this, null, 0, false);
			String url = UrlMaker.UpdateRating(String.valueOf(current),
					String.valueOf(-1));
			hp.execute(url);
		}

	}
	
	public void readNewsToListButton(View view){
		Intent inte = new Intent(this, ArticlesListActivity.class);
		startActivity(inte);
	}
	
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read_news, menu);
		return true;
	}

}

class ReaderPagerAdapter extends PagerAdapter {

	private ImageView articlePicture;
	private TextView articleHeadline;
	private TextView contentTextView;
	private TextView hashesTextView;

	private int size;
	private Activity activity;
	private List<Article> articles;

	public ReaderPagerAdapter(Activity activity, int size,
			List<Article> articles) {
		this.activity = activity;
		this.size = size;
		this.articles = articles;
	}

	@Override
	public Object instantiateItem(View collection, int position) {

		View v = LayoutInflater.from(activity).inflate(R.layout.single_article,
				null);

		// Intent intent = activity.getIntent();
		// String jsons = intent.getStringExtra("jsons");

		Article article = articles.get(position);

		articlePicture = (ImageView) v.findViewById(R.id.articlePicture);
		Picasso.with(activity).load(article.getPictureUrl())
				.into(articlePicture);

		articleHeadline = (TextView) v.findViewById(R.id.articleHeadline);
		articleHeadline.setText(article.getArticleHeadline().trim());
		contentTextView = (TextView) v.findViewById(R.id.contentTextView);
		contentTextView.setText(article.getArticleText().trim());
		hashesTextView = (TextView) v.findViewById(R.id.hashesTextView);
		hashesTextView.setText(article.getArticleAuthor().trim());

		Log.i("TEST ko je autor: ", article.getArticleAuthor());
		((ViewPager) collection).addView(v);

		return v;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == ((View) arg1);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((View) object);
	}

}
