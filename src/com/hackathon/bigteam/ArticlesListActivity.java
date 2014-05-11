package com.hackathon.bigteam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.hackaton.model.Article;
import com.hackaton.util.BitmapDownloader;
import com.hackaton.util.HttpRequest;
import com.hackaton.util.InitializingFinished;
import com.hackaton.util.JsonParser;
import com.hackaton.util.NextScreen;
import com.hackaton.util.UrlMaker;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArticlesListActivity extends ListActivity {

	private ArticleAdapter adapter;
	private int numberOfLastAddedMembers;
	public static Boolean enableInfiniteScroll = true;
	private ArrayList<Article> articles = null;

	private Runnable run;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_articles_list);

		articles = new ArrayList<Article>();

		 articles = new ArrayList<Article>();

		Intent intent = getIntent();
		articles = JsonParser.ParseArticles(intent.getStringExtra("jsons"));
		Log.i("ArticlesListActivity", "Added: " + articles.size());
		numberOfLastAddedMembers = articles.size();

		this.adapter = new ArticleAdapter(this, R.layout.single_listview_item,
				articles);
		setListAdapter(this.adapter);

		// inace nema smisla dodavat ove listenere
		if (numberOfLastAddedMembers != 0) {
			getListView().setOnItemClickListener(listlistener);
			getListView().setOnScrollListener(scrollListener);
		}
	}

	public void listArticlesToAddArticlesClicked(View view) {
		Intent intten = new Intent(this, AddNews.class);
		startActivity(intten);
	}

	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
     if(resultCode==RESULT_OK){
    	 Log.i("kfkfk", "tu sam se stvorija");
    	 refreshButtonClicked(null);
     }
    }
	
	
	
	
	public void refreshButtonClicked(View view){
		String tmp = UrlMaker.GetXArticles();
		HttpRequest	ht = new HttpRequest(ArticlesListActivity.this, null, 0, true);
		String json;
		try {
			json = ht.execute(tmp).get();
			List<Article> articles = JsonParser.ParseArticles(json);
			adapter.clear();
			adapter.addAll(articles);
			adapter.notifyDataSetChanged();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				String page = UrlMaker.createGetNextFilteredURL(adapter
						.getItem(adapter.getCount() - 1).getArticleID());
				Log.i("string", page);
				readWebpage(page);
			}
		}
	};

	private class DownloadAndParseWebPageTask extends
			AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {
			String response = "";
			for (String url : urls) {
				Log.i("Lista", url);
				DefaultHttpClient client = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(url);
				try {
					HttpResponse execute = client.execute(httpGet);
					InputStream content = execute.getEntity().getContent();

					BufferedReader buffer = new BufferedReader(
							new InputStreamReader(content));
					String s = "";
					while ((s = buffer.readLine()) != null) {
						response += s;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return response;
		}

		@Override
		protected void onPostExecute(String result) {
			ArrayList<Article> tempArticles;
			tempArticles = JsonParser.ParseArticles(result);
			Log.i("Lista", "Downloadano: " + result);
			if (tempArticles != null && tempArticles.size() > 0) {
				for (int i = 0; i < tempArticles.size(); i++)
					adapter.add(tempArticles.get(i));
			}
			adapter.notifyDataSetChanged();
			Log.i("Lista", "Dodano " + tempArticles.size() + " clanova.");
			numberOfLastAddedMembers = tempArticles.size();
		}
	}

	DownloadAndParseWebPageTask task = null;

	public void readWebpage(String URL) {
		if (task == null) {
			task = new DownloadAndParseWebPageTask();
			task.execute(new String[] { URL });
			Log.i("Lista",
					"Poslan ID "
							+ adapter.getItem(adapter.getCount() - 1)
									.getArticleID()
							+ " kao zadnji ID za GetNext.");
		} else if ((task.getStatus() == Status.FINISHED)
				&& (numberOfLastAddedMembers != 0)) // if
													// numberOfLastAddedMembers
													// is zero there is no point
													// in downloading more
													// profiles
		{
			task = new DownloadAndParseWebPageTask();
			task.execute(new String[] { URL });
			Log.i("Lista",
					"Poslan ID "
							+ adapter.getItem(adapter.getCount() - 1)
									.getArticleID()
							+ " kao zadnji ID za GetNext.");
		}
	}

	private OnItemClickListener listlistener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View arg1, int position,
				long arg3) {

			Article article = (Article) parent.getItemAtPosition(position);

			ReadNews.id = article.getArticleID();
			ReadNews.articles = articles;
			Intent i = new Intent(ArticlesListActivity.this, ReadNews.class);
			startActivity(i);

		}
	};

	private class ArticleAdapter extends ArrayAdapter<Article> {

		private ArrayList<Article> items;
		private Context context;

		public ArticleAdapter(Context context, int textViewResourceId,
				ArrayList<Article> items) {
			super(context, textViewResourceId, items);
			this.items = items;
			this.context = context;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.single_listview_item, null);

			Article o = items.get(position);

			if (o != null) {
				TextView tt = (TextView) v.findViewById(R.id.listViewHeadline);
				tt.setText(o.getArticleHeadline());
				ImageView iv = (ImageView) v
						.findViewById(R.id.listViewArticlePicture);
				Picasso.with(context).load(o.getPictureUrl()).into(iv);

			}

			return v;
		}

	}

}
