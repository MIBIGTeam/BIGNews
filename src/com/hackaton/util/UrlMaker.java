package com.hackaton.util;

public class UrlMaker {
	
	private static String security = "2e435d9b2aae179ca7f5d3993b0a76c80a17e8cd";
	
	public static String GetArticle(int articleID) {
		
		return "http://trashnfun.com/News/getArticleById.php?&sigurnost=" + security + "&ArticleId=" + articleID;
	}
	
}
