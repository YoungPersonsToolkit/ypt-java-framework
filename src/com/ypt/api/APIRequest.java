package com.ypt.api;

import java.util.ArrayList;
import java.util.List;

import com.ypt.framework.YPTTerm;

public class APIRequest 
{
	private static final String API_BASE = "http://192.168.225.128/wp-json/ypt";
	//private static final String API_BASE = "http://ypt.hpwebstudios.com.au/wp-json/ypt";
	public static final String EVENT_LIST = "/events";
	public static final String SERVICE_LIST = "/services";
	public static final String INFO_LIST = "/infos";
	public static final String NEWS_LIST = "/news";
	public static final String EVENT = "/event/";
	public static final String SERVICE = "/service/";
	public static final String INFO = "/info/";
	public static final String NEWS_ITEM = "/news-item/";
	
	private static final int POSTS_PER_PAGE = 1;
	
	private static final String PARAM_ADD = "&";
	private static final String PARAM_FILTER = "filter";
	private static final String PARAM_POSTS_PER_PAGE = PARAM_FILTER + "[posts_per_page]=";
	private static final String PARAM_CATEGORY = PARAM_FILTER + "[cat][]=";
	
	public static String makeUrl(String route, String postId, String[] catIds)
	{
		StringBuilder url = new StringBuilder(API_BASE);
		
		url.append(route);
		
		//if it's a single post request, add the id.
		if (postId != null && (	EVENT.equals(route) ||
								SERVICE.equals(route) ||
								INFO.equals(route) ||
								NEWS_ITEM.equals(route)))
		{
			url.append(postId);
		}
		
		//add the posts per page (only for collections)
		if (postId == null)
		{
			url.append("?");
			url.append(PARAM_POSTS_PER_PAGE + POSTS_PER_PAGE); 
		}
		
		//add the categories. Only if this is a collection and there are categories to get.
		if (postId == null && catIds.length > 0) 
		{
			for (int i = 0; i < catIds.length; i++)
			{
				url.append(PARAM_ADD + PARAM_CATEGORY + catIds[i]); 
			}
		}
		
		return url.toString();
	}
	
	public static String makeUrl(String route, String postId, List<String> catIds)
	{
		return makeUrl(route, postId, catIds.toArray(new String[catIds.size()])); 
	}
	
	public static String makeUrl (String route, String postId, YPTTerm[] cats)
	{
		if (cats.length == 0)
			return makeUrl(route, postId, new String[0]);
		ArrayList<String> c = new ArrayList<String>();
		for (int i = 0; i < cats.length; i++)
		{
			c.add(cats[i].getId());
		}
		return makeUrl(route, postId, c.toArray(new String[c.size()])); 
	}
	
	public static String makeUrl (String route, String postId)
	{
		return makeUrl(route, postId, new String[0]); 
	}
	
}