package com.ypt.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConnector 
{
	private static final String USER_AGENT = "Mozilla/5.0";
	
	public InputStream HttpGet(String url)
	{		
		try 
		{
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
			
//			int responseCode = con.getResponseCode();
//			System.out.println("\nSending GET request to URL...");
//			System.out.println("Response Code: " + responseCode);
										
			return con.getInputStream();
		} 
		catch (MalformedURLException e) 
		{
			System.err.println("Something wrong with the URL syntax");
			e.printStackTrace();
			return null;
		} catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
}