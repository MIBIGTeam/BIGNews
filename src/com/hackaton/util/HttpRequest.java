package com.hackaton.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class HttpRequest extends AsyncTask<String, String, String> {

	private ProgressDialog prgDlg;
	private Activity activity;
	private NextScreen nextScreen;
	private int extraDelayInMiliseconds;
	private Boolean showLoadingNoteForUser;
	
	public HttpRequest(Activity activity, NextScreen nextScreen, int extraDelayInMiliseconds, Boolean showLoadingNoteForUser)
	{
		prgDlg = new ProgressDialog(activity);
		this.activity = activity;
		this.nextScreen = nextScreen;
		this.extraDelayInMiliseconds = extraDelayInMiliseconds;
		this.showLoadingNoteForUser = showLoadingNoteForUser;
	}

	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();
		
		if (showLoadingNoteForUser)
		{
			prgDlg.setTitle("Molimo prièekajte");
			prgDlg.setMessage("Uèitavam podatke!");
			
			//prgDlg.setIcon(R.drawable.icon_search);	
			prgDlg.show();
		}
	}

	@Override
	protected String doInBackground(String... uri)
	{
		// extra delay
		 try
		{
			Thread.sleep(extraDelayInMiliseconds);
		} catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// continue
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		String responseString = null;
		try
		{
			response = httpclient.execute(new HttpGet(uri[0]));
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK)
			{
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();
			} else
			{

				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		}

		catch (ClientProtocolException e)
		{

		}

		catch (IOException e)
		{

		}
		
		return responseString;
	}

	@Override
	protected void onPostExecute(String result)
	{
		super.onPostExecute(result);
		try {
			nextScreen.runScreen(activity, result);	
		} catch (Exception e) {
			Log.i("oèekivano", ":)");
		}
		prgDlg.cancel();
	}
	
}
