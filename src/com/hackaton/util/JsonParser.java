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
				
				article.setArticleID(jsonObject.getString("ArticleID"));
				article.setArticleHeadline(jsonObject.getString("ArticleHeadline"));
				article.setArticleText(jsonObject.getString("ArticleText"));
				article.setArticleAuthor(jsonObject.getString("ArticleAuthor"));
				article.setArticleDate(jsonObject.getString("ArticleDate"));
				article.setPictureUrl(jsonObject.getString("PictureUrl"));
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}

		return article;
	}

}
