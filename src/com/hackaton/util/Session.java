package com.hackaton.util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public final class Session
{
	
	public static void setLikes(Activity activity, String articleId)
	{
		SharedPreferences settingsPreferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
		SharedPreferences.Editor editor = settingsPreferences.edit();
		editor.putString("Likes", articleId);
		editor.commit();
	}
	
	public static void removeLikes(Activity activity)
	{
		SharedPreferences settingsPreferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
		SharedPreferences.Editor editor = settingsPreferences.edit();
		editor.remove("Likes");
		editor.commit();
	}
	
	public static String getLikesList(Activity activity)
	{
		SharedPreferences settingsPreferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
		return settingsPreferences.getString("Likes", null);
	}
	
	public static void setDisLikes(Activity activity, String articleId)
	{
		SharedPreferences settingsPreferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
		SharedPreferences.Editor editor = settingsPreferences.edit();
		editor.putString("DisLikes", articleId);
		editor.commit();
	}
	
	public static void removeDisLikes(Activity activity)
	{
		SharedPreferences settingsPreferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
		SharedPreferences.Editor editor = settingsPreferences.edit();
		editor.remove("DisLikes");
		editor.commit();
	}
	
	public static String getDisLikesList(Activity activity)
	{
		SharedPreferences settingsPreferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
		return settingsPreferences.getString("DisLikes", null);
	}
	
	
}
