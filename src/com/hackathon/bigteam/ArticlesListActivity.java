package com.hackathon.bigteam;

import java.util.ArrayList;
import java.util.HashMap;

import com.hackaton.model.Article;
import com.hackaton.util.HttpRequest;
import com.hackaton.util.InitializingFinished;
import com.hackaton.util.JsonParser;
import com.hackaton.util.NextScreen;
import com.hackaton.util.UrlMaker;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticlesListActivity extends ListActivity {

	private HashMap<String, Bitmap> itemsBitmaps;
	private HashMap<Article, ImageView> itemsIV;
	private ArticleAdapter adapter;
	private int numberOfLastAddedMembers;
	public static Boolean enableInfiniteScroll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_articles_list);

		ArrayList<Article> articles = new ArrayList<Article>();
		//itemsBitmaps = new HashMap<String, Bitmap>();
		//itemsIV = new HashMap<Article, ImageView>();
		

		Intent intent = getIntent();
		articles = JsonParser.ParseArticles(intent.getStringExtra("jsons"));
		Log.i("ArticlesListActivity", "Added: " + articles.size());
		numberOfLastAddedMembers = articles.size();
		
		this.adapter = new ArticleAdapter(this, R.layout.single_listview_item, articles);
		setListAdapter(this.adapter);

		// inace nema smisla dodavat ove listenere
		if (numberOfLastAddedMembers != 0) {
			getListView().setOnItemClickListener(listlistener);
			//getListView().setOnScrollListener(scrollListener);
		}
	}

	private OnScrollListener scrollListener = new OnScrollListener() {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {

		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			boolean loadMore = // maybe add a padding
			firstVisibleItem + visibleItemCount >= totalItemCount;

			if (loadMore && enableInfiniteScroll) {
				// String page =
				// UrlMaker.createGetNextFilteredURL(adapter.getItem(adapter.getCount()
				// - 1).getId(), filterName, filterSurname, filterCity,
				// filterSubjects);
				// readWebpage(page);
			}
		}
	};

	private OnItemClickListener listlistener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
			
			
			Article article = (Article) parent.getItemAtPosition(position);			
			NextScreen initializingFinished = new InitializingFinished(ReadNews.class);
			HttpRequest request = new HttpRequest(ArticlesListActivity.this, initializingFinished, 0, true);
			String url = UrlMaker.GetArticle(article.getArticleID());
			request.execute(url);
			Log.i("ArticlesListActivity starts url: ", url);
		}
	};

	private class ArticleAdapter extends ArrayAdapter<Article> {
		
		private ArrayList<Article> items;

		public ArticleAdapter(Context context, int textViewResourceId, ArrayList<Article> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Log.i("ArticleAdapter", "1");
			View v = convertView;
			Log.i("ArticleAdapter", "2");
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.single_listview_item, null);
			}
			Log.i("ArticleAdapter", "3");
			Article o = items.get(position);
			if (o != null) {
				TextView tt = (TextView) v.findViewById(R.id.listViewHeadline);
				ImageView iv = (ImageView) v.findViewById(R.id.listViewArticlePicture);

				if (tt != null) {
					tt.setText(o.getArticleHeadline());
				}
				if (iv != null) {
						//itemsIV.put(o, iv);
						//iv.setAlpha(0.0f);
						// new DownloadImageTask().execute(o);
					}
				}
			return v;
		}
	}
}
