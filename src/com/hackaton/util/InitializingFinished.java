package com.hackaton.util;

import android.app.Activity;
import android.content.Intent;

public class InitializingFinished implements NextScreen
{
	private Class<?> cls;
	
	public InitializingFinished(Class<?> cls){
		super();
		this.cls = cls;
	}


	@Override
	public void runScreen(Activity activity, String message){
		
		Intent i = new Intent(activity.getBaseContext(), cls);    
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.putExtra("jsons", message);
		
		activity.startActivity(i);
	}
}


