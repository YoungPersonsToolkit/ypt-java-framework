package com.ypt.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.ypt.framework.YPTElement;
import com.ypt.framework.YPTElementList;
import com.ypt.framework.YPTEvent;
import com.ypt.framework.YPTInfo;
import com.ypt.framework.YPTNewsItem;
import com.ypt.framework.YPTService;

public class CollectionParser 
{
	public static YPTElementList<YPTEvent> jsonToEventList(JsonParser jp)
	{
		
		try 
		{
			YPTElementList<YPTEvent> eventList = new YPTElementList<YPTEvent>();
			
			//die if the JSON isn't an array - we're only doing lists here.
			if (jp.nextToken() != JsonToken.START_ARRAY)
				return null;
			
			
			//this is the holder for the current element that is being processed
			YPTEvent event = null;
			
			//loop through the JSON
			while (jp.nextToken() != JsonToken.END_ARRAY)
			{
				if (jp.getCurrentToken() == JsonToken.START_ARRAY)
				{
					throw new IllegalArgumentException("There shouldn't be an array in here...");
				}
				if(jp.getCurrentToken() == JsonToken.START_OBJECT)
				{
					//create a new element in memory
					event = new YPTEvent();
					//advance to the first field of the element
					continue;
				}
				if(jp.getCurrentToken() == JsonToken.END_OBJECT)
				{
					//add the element to the array
					eventList.add(event); 
					continue;
				}
				
				//get the key
				String name = jp.getCurrentName();
				
				//based on the key, advance to the next token and get the value
				switch(name)
				{
					case YPTElement.ID:
						jp.nextToken();
						event.setId(jp.getValueAsString());
						break;
					case YPTElement.TITLE:
						jp.nextToken();
						event.setTitle(jp.getValueAsString());
						break;
					case YPTElement.TYPE:
						jp.nextToken();
						if(!YPTEvent.EVENT.equals(jp.getValueAsString()))
							throw new IllegalArgumentException("An element in the JSON array was not an Event");
						break;
					case YPTElement.DATE:
						jp.nextToken();
						event.setDate(jp.getValueAsString());
						break;
					case YPTElement.MODIFIED:
						jp.nextToken();
						event.setModified(jp.getValueAsString());
						break;
					case YPTElement.FEATURED_IMAGE_URL:
						jp.nextToken();
						event.setFeaturedImageURL(jp.getValueAsString());
						break;
					case YPTElement.AREA:
						jp.nextToken();
						event.setArea(jp.getValueAsString());
						break;
					case YPTEvent.EVENT_LOCATION:
						jp.nextToken();
						event.setEventLocation(jp.getValueAsString());
						break;
					case YPTEvent.EVENT_DATE:
						jp.nextToken();
						event.setEventDate(jp.getValueAsString());
						break;
					default:
						throw new IllegalArgumentException("Unexpected token. The JSON might have a nested array or object");
				}
			}
			return eventList;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null; 
		}

		
	}
	
	public static YPTElementList<YPTNewsItem> jsonToNewsList(JsonParser jp)
	{
		try 
		{
			YPTElementList<YPTNewsItem> newsList = new YPTElementList<YPTNewsItem>();

			//die if the JSON isn't an array - we're only doing lists here.
			if (jp.nextToken() != JsonToken.START_ARRAY)
				return null;
			
			
			//this is the holder for the current element that is being processed
			YPTNewsItem newsItem = null;
			
			//loop through the JSON
			while (jp.nextToken() != JsonToken.END_ARRAY)
			{
				if (jp.getCurrentToken() == JsonToken.START_ARRAY)
				{
					throw new IllegalArgumentException("There shouldn't be an array in here...");
				}
				if(jp.getCurrentToken() == JsonToken.START_OBJECT)
				{
					//create a new element in memory
					newsItem = new YPTNewsItem();
					//advance to the first field of the element
					continue;
				}
				if(jp.getCurrentToken() == JsonToken.END_OBJECT)
				{
					//add the element to the array
					newsList.add(newsItem); 
					continue;
				}
				
				//get the key
				String name = jp.getCurrentName();
				
				//based on the key, advance to the next token and get the value
				switch(name)
				{
					case YPTElement.ID:
						jp.nextToken();
						newsItem.setId(jp.getValueAsString());
						break;
					case YPTElement.TITLE:
						jp.nextToken();
						newsItem.setTitle(jp.getValueAsString());
						break;
					case YPTElement.TYPE:
						jp.nextToken();
						if(!YPTNewsItem.NEWS_ITEM.equals(jp.getValueAsString()))
							throw new IllegalArgumentException("An element in the JSON array was not a News Item");
						break;
					case YPTElement.DATE:
						jp.nextToken();
						newsItem.setDate(jp.getValueAsString());
						break;
					case YPTElement.MODIFIED:
						jp.nextToken();
						newsItem.setModified(jp.getValueAsString());
						break;
					case YPTElement.FEATURED_IMAGE_URL:
						jp.nextToken();
						newsItem.setFeaturedImageURL(jp.getValueAsString());
						break;
					case YPTElement.AREA:
						jp.nextToken();
						newsItem.setArea(jp.getValueAsString());
						break;
					default:
						throw new IllegalArgumentException("Unexpected token. The JSON might have a nested array or object, or wrong post type");
				}
			}
			return newsList;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null; 
		}

		
	}
	
	public static YPTElementList<YPTService> jsonToServiceList(JsonParser jp)
	{
		try 
		{
			YPTElementList<YPTService> serviceList = new YPTElementList<YPTService>();
			
			//die if the JSON isn't an array - we're only doing lists here.
			if (jp.nextToken() != JsonToken.START_ARRAY)
				return null;
			
			
			//this is the holder for the current element that is being processed
			YPTService service = null;
			
			//loop through the JSON
			while (jp.nextToken() != JsonToken.END_ARRAY)
			{
				if (jp.getCurrentToken() == JsonToken.START_ARRAY)
				{
					throw new IllegalArgumentException("There shouldn't be an array in here...");
				}
				if(jp.getCurrentToken() == JsonToken.START_OBJECT)
				{
					//create a new element in memory
					service = new YPTService();
					//advance to the first field of the element
					continue;
				}
				if(jp.getCurrentToken() == JsonToken.END_OBJECT)
				{
					//add the element to the array
					serviceList.add(service); 
					continue;
				}
				
				//get the key
				String name = jp.getCurrentName();
				
				//based on the key, advance to the next token and get the value
				switch(name)
				{
					case YPTElement.ID:
						jp.nextToken();
						service.setId(jp.getValueAsString());
						break;
					case YPTElement.TITLE:
						jp.nextToken();
						service.setTitle(jp.getValueAsString());
						break;
					case YPTElement.TYPE:
						jp.nextToken();
						if(!YPTService.SERVICE.equals(jp.getValueAsString()))
							throw new IllegalArgumentException("An element in the JSON array was not a Service");
						break;
					case YPTElement.DATE:
						jp.nextToken();
						service.setDate(jp.getValueAsString());
						break;
					case YPTElement.MODIFIED:
						jp.nextToken();
						service.setModified(jp.getValueAsString());
						break;
					case YPTElement.FEATURED_IMAGE_URL:
						jp.nextToken();
						service.setFeaturedImageURL(jp.getValueAsString());
						break;
					case YPTElement.AREA:
						jp.nextToken();
						service.setArea(jp.getValueAsString());
						break;
						
						//ACTUALLY, I don't need this yet... use it for single
					case YPTService.PHONES:
						jp.nextToken(); //should be array token
						while(jp.nextToken() != JsonToken.END_ARRAY)
						{
							service.addPhone(jp.getValueAsString()); 
						}
						break;
					case YPTService.EMAILS:
						jp.nextToken(); //should be array token
						while(jp.nextToken() != JsonToken.END_ARRAY)
						{
							service.addEmail(jp.getValueAsString()); 
						}
						break;
					case YPTService.WEBSITES:
						jp.nextToken(); //should be array token
						while(jp.nextToken() != JsonToken.END_ARRAY)
						{
							service.addWebsite(jp.getValueAsString()); 
						}
						break;
					default:
						throw new IllegalArgumentException("Unexpected token. The JSON might have a nested array or object, or wrong post type");
				}
			}
			return serviceList;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null; 
		}

		
	}
	
	public static YPTElementList<YPTInfo> jsonToInfoList(JsonParser jp)
	{
		try 
		{
			YPTElementList<YPTInfo> infoList = new YPTElementList<YPTInfo>();
			
			//die if the JSON isn't an array - we're only doing lists here.
			if (jp.nextToken() != JsonToken.START_ARRAY)
				return null;
			
			
			//this is the holder for the current element that is being processed
			YPTInfo info = null;
			
			//loop through the JSON
			while (jp.nextToken() != JsonToken.END_ARRAY)
			{
				if (jp.getCurrentToken() == JsonToken.START_ARRAY)
				{
					throw new IllegalArgumentException("There shouldn't be an array in here...");
				}
				if(jp.getCurrentToken() == JsonToken.START_OBJECT)
				{
					//create a new element in memory
					info = new YPTInfo();
					//advance to the first field of the element
					continue;
				}
				if(jp.getCurrentToken() == JsonToken.END_OBJECT)
				{
					//add the element to the array
					infoList.add(info); 
					continue;
				}
				
				//get the key
				String name = jp.getCurrentName();
				
				//based on the key, advance to the next token and get the value
				switch(name)
				{
					case YPTElement.ID:
						jp.nextToken();
						info.setId(jp.getValueAsString());
						break;
					case YPTElement.TITLE:
						jp.nextToken();
						info.setTitle(jp.getValueAsString());
						break;
					case YPTElement.TYPE:
						jp.nextToken();
						if(!YPTInfo.INFO.equals(jp.getValueAsString()))
							throw new IllegalArgumentException("An element in the JSON array was not a Service");
						break;
					case YPTElement.DATE:
						jp.nextToken();
						info.setDate(jp.getValueAsString());
						break;
					case YPTElement.MODIFIED:
						jp.nextToken();
						info.setModified(jp.getValueAsString());
						break;
					case YPTElement.FEATURED_IMAGE_URL:
						jp.nextToken();
						info.setFeaturedImageURL(jp.getValueAsString());
						break;
					case YPTElement.AREA:
						jp.nextToken();
						info.setArea(jp.getValueAsString());
						break;
					default:
						throw new IllegalArgumentException("Unexpected token. The JSON might have a nested array or object, or wrong post type");
				}
			}
			return infoList;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null; 
		}

		
	}
}
