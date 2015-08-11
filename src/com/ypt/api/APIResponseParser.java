package com.ypt.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.ypt.framework.YPTElementList;
import com.ypt.framework.YPTEvent;
import com.ypt.framework.YPTInfo;
import com.ypt.framework.YPTNewsItem;
import com.ypt.framework.YPTService;

public class APIResponseParser 
{
	//LISTS
	
	public static YPTElementList<YPTEvent> jsonToEventList(String json)
	{
		JsonParser jp = createJsonParserFromString(json);
		if (null == jp) return null;
		return CollectionParser.jsonToEventList(jp);
	}
	public static YPTElementList<YPTEvent> jsonToEventList(File json)
	{
		JsonParser jp = createJsonParserFromFile(json);
		if (null == jp) return null;
		return CollectionParser.jsonToEventList(jp);
	}
	public static YPTElementList<YPTEvent> jsonToEventList(InputStream json)
	{
		JsonParser jp = createJsonParserFromStream(json);
		if (null == jp) return null;
		return CollectionParser.jsonToEventList(jp);
	}
	
	public static YPTElementList<YPTNewsItem> jsonToNewsList(String json)
	{
		JsonParser jp = createJsonParserFromString(json);
		if (null == jp) return null;
		return CollectionParser.jsonToNewsList(jp);
	}
	public static YPTElementList<YPTNewsItem> jsonToNewsList(File json)
	{
		JsonParser jp = createJsonParserFromFile(json);
		if (null == jp) return null;
		return CollectionParser.jsonToNewsList(jp);
	}
	public static YPTElementList<YPTNewsItem> jsonToNewsList(InputStream json)
	{
		JsonParser jp = createJsonParserFromStream(json);
		if (null == jp) return null;
		return CollectionParser.jsonToNewsList(jp);
	}
	
	public static YPTElementList<YPTService> jsonToServiceList(String json)
	{
		JsonParser jp = createJsonParserFromString(json);
		if (null == jp) return null;
		return CollectionParser.jsonToServiceList(jp);
	}
	public static YPTElementList<YPTService> jsonToServiceList(File json)
	{
		JsonParser jp = createJsonParserFromFile(json);
		if (null == jp) return null;
		return CollectionParser.jsonToServiceList(jp);
	}
	public static YPTElementList<YPTService> jsonToServiceList(InputStream json)
	{
		JsonParser jp = createJsonParserFromStream(json);
		if (null == jp) return null;
		return CollectionParser.jsonToServiceList(jp);
	}
	
	public static YPTElementList<YPTInfo> jsonToInfoList(String json)
	{
		JsonParser jp = createJsonParserFromString(json);
		if (null == jp) return null;
		return CollectionParser.jsonToInfoList(jp);
	}
	public static YPTElementList<YPTInfo> jsonToInfoList(File json)
	{
		JsonParser jp = createJsonParserFromFile(json);
		if (null == jp) return null;
		return CollectionParser.jsonToInfoList(jp);
	}
	public static YPTElementList<YPTInfo> jsonToInfoList(InputStream json)
	{
		JsonParser jp = createJsonParserFromStream(json);
		if (null == jp) return null;
		return CollectionParser.jsonToInfoList(jp);
	}
	
	//SINGULAR POSTS
	
	public static YPTService jsonToService(String json)
	{
		JsonParser jp = createJsonParserFromString(json);
		if (null == jp) return null;
		return SingleParser.jsonToService(jp);
	}
	public static YPTService jsonToService(File json)
	{
		JsonParser jp = createJsonParserFromFile(json);
		if (null == jp) return null;
		return SingleParser.jsonToService(jp);
	}
	public static YPTService jsonToService(InputStream json)
	{
		JsonParser jp = createJsonParserFromStream(json);
		if (null == jp) return null;
		return SingleParser.jsonToService(jp);
	}
	
	public static YPTNewsItem jsonToNewsItem(String json)
	{
		JsonParser jp = createJsonParserFromString(json);
		if (null == jp) return null;
		return SingleParser.jsonToNewsItem(jp);
	}
	public static YPTNewsItem jsonToNewsItem(File json)
	{
		JsonParser jp = createJsonParserFromFile(json);
		if (null == jp) return null;
		return SingleParser.jsonToNewsItem(jp);
	}
	public static YPTNewsItem jsonToNewsItem(InputStream json)
	{
		JsonParser jp = createJsonParserFromStream(json);
		if (null == jp) return null;
		return SingleParser.jsonToNewsItem(jp);
	}
	
	public static YPTInfo jsonToInfo(String json)
	{
		JsonParser jp = createJsonParserFromString(json);
		if (null == jp) return null;
		return SingleParser.jsonToInfo(jp);
	}
	public static YPTInfo jsonToInfo(File json)
	{
		JsonParser jp = createJsonParserFromFile(json);
		if (null == jp) return null;
		return SingleParser.jsonToInfo(jp);
	}
	public static YPTInfo jsonToInfo(InputStream json)
	{
		JsonParser jp = createJsonParserFromStream(json);
		if (null == jp) return null;
		return SingleParser.jsonToInfo(jp);
	}
	
	public static YPTEvent jsonToEvent(String json)
	{
		JsonParser jp = createJsonParserFromString(json);
		if (null == jp) return null;
		return SingleParser.jsonToEvent(jp);
	}
	public static YPTEvent jsonToEvent(File json)
	{
		JsonParser jp = createJsonParserFromFile(json);
		if (null == jp) return null;
		return SingleParser.jsonToEvent(jp);
	}
	public static YPTEvent jsonToEvent(InputStream json)
	{
		JsonParser jp = createJsonParserFromStream(json);
		if (null == jp) return null;
		return SingleParser.jsonToEvent(jp);
	}
	
	private static JsonParser createJsonParserFromString(String json)
	{
		try 
		{
			JsonParser jp = new JsonFactory().createParser(json);
			return jp;
		} 
		catch (IOException e) 
		{
			System.err.println("Error parsing the initial JSON. Something wrong with the syntax.");
			e.printStackTrace();
			return null;
		}
	}
	
	private static JsonParser createJsonParserFromFile(File json)
	{
		try 
		{
			JsonParser jp = new JsonFactory().createParser(json);
			return jp;
		} 
		catch (IOException e) 
		{
			System.err.println("Error parsing the initial JSON. Something wrong with the syntax.");
			e.printStackTrace();
			return null;
		}
	}
	
	private static JsonParser createJsonParserFromStream(InputStream json)
	{
		try 
		{
			JsonParser jp = new JsonFactory().createParser(json);
			return jp;
		} 
		catch (IOException e) 
		{
			System.err.println("Error parsing the initial JSON. Something wrong with the syntax.");
			e.printStackTrace();
			return null;
		}
	}
}
