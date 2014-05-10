package com.hackathon.bigteam;

import java.util.ArrayList;
import java.util.HashMap;
import com.hackaton.model.Article;
import com.hackaton.util.JsonParser;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		ArrayList<Article> articles = new ArrayList<Article>();
		itemsBitmaps = new HashMap<String, Bitmap>();
		itemsIV = new HashMap<Article, ImageView>();
		// this.adapter = new ArticleAdapter(this, R.layout.profile_item,
		// articles);
		setListAdapter(this.adapter);

		Intent intent = getIntent();
		// articles = JsonParser.parseProfiles(intent.getStringExtra("jsons"));
		Article ar = new Article();
		ar.setArticleHeadline("wtf");
		articles.add(ar);
		Log.i("Lista", "Dodano: " + articles.size());
		numberOfLastAddedMembers = articles.size();

		if (articles.size() > 0) {
			for (int i = 0; i < articles.size(); i++)
				adapter.add(articles.get(i));
		}
		adapter.notifyDataSetChanged();

		// inace nema smisla dodavat ove listenere
		if (numberOfLastAddedMembers != 0) {
			getListView().setOnItemClickListener(listlistener);
			getListView().setOnScrollListener(scrollListener);
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
		public void onItemClick(AdapterView<?> parent, View arg1, int position,
				long arg3) {
			// Profile1Activity.profile = (Article)
			// parent.getItemAtPosition(position);
			// Profile1Activity.profileBmp =
			// itemsBitmaps.get(Profile1Activity.profile.getId());
			// Intent i = new Intent(getBaseContext(), Profile1Activity.class);
			// startActivity(i);
		}
	};

	private class ArticleAdapter extends ArrayAdapter<Article> {
		private ArrayList<Article> items;

		public ArticleAdapter(Context context, int textViewResourceId,
				ArrayList<Article> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}

		@SuppressWarnings("deprecation")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				// v = vi.inflate(R.layout.profile_item, null);
			}
			Article o = items.get(position);
			if (o != null) {
				// TextView tt = (TextView) v.findViewById(R.id.toptext);
				// TextView bt = (TextView) v.findViewById(R.id.bottomtext);
				// TextView city = (TextView) v.findViewById(R.id.city);
				// ImageView iv = (ImageView) v.findViewById(R.id.profile_icon);
				//
				// if (tt != null)
				// {
				// tt.setText(o.getFirstName() + " " + o.getLastName());
				// }
				// if (bt != null)
				// {
				// bt.setText(o.getSubject());
				// }
				// if (city != null)
				// {
				// city.setText(o.getCity());
				// }
				// if (iv != null)
				// {
				// if (!itemsIV.containsKey(o))
				// {
				// itemsIV.put(o, iv);
				// //iv.setAlpha(0.0f);
				// new DownloadImageTask().execute(o);
				// }
				//
				// if (itemsBitmaps.containsKey(o.getId()))
				// {
				// iv.setAlpha(255);
				// iv.setImageBitmap(itemsBitmaps.get(o.getId()));
				// }
				// else
				// iv.setAlpha(0); // sometimes it gets wrong imageView so we
				// need to put opacity to 0 until new image is loaded
				// }
				// }
			}
			return v;
		}
	}
}
