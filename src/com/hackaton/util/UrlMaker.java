package com.hackaton.util;

import java.net.URLEncoder;

import android.util.Log;

public class UrlMaker {

	private static String security = "2e435d9b2aae179ca7f5d3993b0a76c80a17e8cd";

	public static String GetArticle(int articleID) {

		return "http://trashnfun.com/News/getArticleById.php?articleByAuthor="
				+ security + "&ArticleId=" + articleID;
	}

	public static String GetXArticles() {

		return "http://trashnfun.com/News/getXArticles.php?articleByAuthor="
				+ security;
	}

	public static String UpdateRating(String articleId, String sum) {

		Log.i("sksks", articleId + "  " + sum);
		return "http://trashnfun.com/News/editRating.php?articleByAuthor=2e435d9b2aae179ca7f5d3993b0a76c80a17e8cd"
				+ "&ArticleId=" + articleId + "&Sum=" + sum;

	}

	public static String createGetNextFilteredURL(int articleID) {

		return "http://trashnfun.com/News/getNext15Articles.php?articleByAuthor="
				+ security + "&ArticleId=" + articleID;
	}

	public static String createAddArticleURL(String headline, String text,
			String imageURL, String author) {

		imageURL = imageURL.replace("http://", "");

		return "http://trashnfun.com/News/addArticle.php?ArticleHeadline="
				+ URLEncoder.encode(headline) + "&ArticleText="
				+ URLEncoder.encode(text) + "&Author="
				+ URLEncoder.encode(author) + "&PictureUrl="
				+ URLEncoder.encode(imageURL) + "&articleByAuthor=" + security;
	}

	public static String createAddTagsToArticle(String articleID, String tags) {

		return "http://trashnfun.com/News/addTagToArticle.php?articleByAuthor="
				+ security + "&ArticleId=" + articleID + "&Tags="
				+ StringModifier.modifyString(tags);
	}

	public static String createGetAllTags() {
		return "http://trashnfun.com/News/getAllTags.php?articleByAuthor="
				+ security;

	}

	public static String getArticlesByTag(String tag) {
		return "http://trashnfun.com/News/getArticlesByTag.php?articleByAuthor="
				+ security + "&Tag=" + tag;

	}
}
