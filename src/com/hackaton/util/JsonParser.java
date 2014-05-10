package com.hackaton.util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hackaton.model.Article;

public class JsonParser {
	
	public static ArrayList<Article> ParseArticles(String json){
		
		ArrayList<Article> articles = new ArrayList<Article>();

		try	{
			
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Article article = new Article();
				article = new Article();
				
				article.setArticleID(jsonObject.optInt("ArticleID"));
				article.setArticleHeadline(jsonObject.optString("ArticleHeadline"));
				article.setArticleText(jsonObject.optString("ArticleText"));
				article.setArticleAuthor(jsonObject.optString("ArticleAuthor"));
				article.setArticleDate(jsonObject.optString("ArticleDate"));
				article.setPictureUrl("http://" + jsonObject.optString("PictureUrl"));
				articles.add(article);
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}

		return articles;
	}

}
