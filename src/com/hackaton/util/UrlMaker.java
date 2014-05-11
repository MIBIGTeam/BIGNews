package com.hackaton.util;

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

		return "http://trashnfun.com/News/editRating.php?articleByAuthor="
				+ security + "&ArticleId=" + articleId + "&Sum=" + sum;

	}

	public static String createGetNextFilteredURL(int articleID) {
		
		return "http://trashnfun.com/News/getNext15Articles.php?articleByAuthor="+security+"&ArticleId="+articleID;
	}
	
	public static String createAddArticleURL(String headline, String text, String imageURL, String author) {
		
		return "http://trashnfun.com/News/addArticle.php?ArticleHeadline=" + headline +
				"&ArticleText=" + text +
				"&Author=" + author +
				"&PictureUrl" + imageURL + 
				"&articleByAuthor=" + security;
	}
}
