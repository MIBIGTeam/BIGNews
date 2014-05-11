package com.hackaton.model;

import java.sql.Date;

import android.graphics.Bitmap;

public class Article {

	private int ArticleID;
	private String ArticleHeadline;
	private String ArticleText;
	private String ArticleAuthor;
	private Date ArticleDate;
	private String PictureUrl;
	private Bitmap bitmap;

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public int getArticleID() {
		return ArticleID;
	}

	public void setArticleID(int articleID) {
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

	public Date getArticleDate() {
		return ArticleDate;
	}

	public void setArticleDate(String articleDate) {
		Date time=new Date(Long.valueOf(articleDate)*1000);
		ArticleDate = time;
	}

	public String getPictureUrl() {
		return PictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		PictureUrl = pictureUrl;
	}
}
