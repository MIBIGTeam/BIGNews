package com.hackaton.util;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hackaton.model.Article;

public class JsonParser {
	
	public static Article ParseArticle(String json){
		
		Article article = new Article();

		try	{
			
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				article = new Article();
				
				article.setArticleID(jsonObject.optString("ArticleID"));
				article.setArticleHeadline(jsonObject.optString("ArticleHeadline"));
				article.setArticleText(jsonObject.optString("ArticleText"));
				article.setArticleAuthor(jsonObject.optString("ArticleAuthor"));
				article.setArticleDate(jsonObject.optString("ArticleDate"));
				article.setPictureUrl(jsonObject.optString("PictureUrl"));
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}

		return article;
	}

}
