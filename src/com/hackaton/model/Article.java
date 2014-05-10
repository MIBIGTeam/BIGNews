package com.hackaton.model;

public class Article {
	
	private String ArticleID;
	private String ArticleHeadline;
	private String ArticleText;
	private String ArticleAuthor;
	private String ArticleDate;
	private String PictureUrl;
	
	public String getArticleID() {
		return ArticleID;
	}
	public void setArticleID(String articleID) {
		ArticleID = articleID;
	}
	public String getArticleHeadline() {
		return ArticleHeadline;
	}
	public void setArticleHeadline(String articleHeadline) {
		ArticleHeadline = articleHeadline;
	}
	public String getArticleText() {
		return ArticleText;
	}
	public void setArticleText(String articleText) {
		ArticleText = articleText;
	}
	public String getArticleAuthor() {
		return ArticleAuthor;
	}
	public void setArticleAuthor(String articleAuthor) {
		ArticleAuthor = articleAuthor;
	}
	public String getArticleDate() {
		return ArticleDate;
	}
	public void setArticleDate(String articleDate) {
		ArticleDate = articleDate;
	}
	public String getPictureUrl() {
		return PictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		PictureUrl = pictureUrl;
	}
}

