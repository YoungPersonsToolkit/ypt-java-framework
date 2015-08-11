package com.ypt.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.ypt.framework.YPTAuthor;
import com.ypt.framework.YPTElement;
import com.ypt.framework.YPTEvent;
import com.ypt.framework.YPTInfo;
import com.ypt.framework.YPTNewsItem;
import com.ypt.framework.YPTService;
import com.ypt.framework.YPTTerm;

public class SingleParser 
{
	public static YPTService jsonToService(JsonParser jp)
	{
		try 
		{
			YPTService element = new YPTService();
			
			//die if the JSON is an array - we're only doing single posts here.
			if (jp.nextToken() == JsonToken.START_ARRAY)
			{
				System.err.println("JSON reponse was a collection");
				return null;
			}
		
			//loop through the JSON object
			while (jp.nextToken() != JsonToken.END_OBJECT)
			{
				//get the key
				String name = jp.getCurrentName();
				
				//based on the key, advance to the next token and get the value
				switch(name)
				{
					case YPTElement.ID:
						jp.nextToken();
						element.setId(jp.getValueAsString());
						break;
					case YPTElement.TITLE:
						jp.nextToken();
						element.setTitle(jp.getValueAsString());
						break;
					case YPTElement.TYPE:
						jp.nextToken();
						if(!YPTService.SERVICE.equals(jp.getValueAsString()))
							throw new IllegalArgumentException("An element in the JSON array was not a Service");
						break;
					case YPTElement.AUTHOR:
						element.setAuthor(new YPTAuthor(null));
						jp.nextToken(); //opening bracket
						while(jp.nextToken() != JsonToken.END_OBJECT) //key
						{
							name = jp.getCurrentName();
							switch(name)
							{
								case YPTElement.ID:
									jp.nextToken(); //value
									element.getAuthor().setId(jp.getValueAsString());
									break;
								case YPTAuthor.USERNAME:
									jp.nextToken(); //value
									element.getAuthor().setUsername(jp.getValueAsString());
									break;
								case YPTAuthor.FIRST_NAME:
									jp.nextToken(); //value
									element.getAuthor().setFirstName(jp.getValueAsString());
									break;
								case YPTAuthor.LAST_NAME:
									jp.nextToken(); //value
									element.getAuthor().setLastName(jp.getValueAsString());
									break;
							}
						}
						break;
					case YPTElement.CONTENT:
						jp.nextToken();
						element.setContent(jp.getValueAsString());
						break;
					case YPTElement.LINK:
						jp.nextToken();
						element.setLink(jp.getValueAsString());
						break;
					case YPTElement.DATE:
						jp.nextToken();
						element.setDate(jp.getValueAsString());
						break;
					case YPTElement.MODIFIED:
						jp.nextToken();
						element.setModified(jp.getValueAsString());
						break;
					case YPTElement.TERMS:
						if (jp.nextToken() == JsonToken.START_ARRAY) //start obj/start arr
						{
							//no terms at all
							element.setTags(null);
							element.setCategories(null); 
							jp.nextToken(); //end array
						}
						else //we've got terms
						{
							while(jp.nextToken() != JsonToken.END_OBJECT) //key
							{
								if (YPTElement.POST_TAG.equals(jp.getCurrentName()))
								{
									//element.setTags(new ArrayList<YPTTerm>());
									jp.nextToken(); //array start
									YPTTerm tag = new YPTTerm();
									while(jp.nextToken() != JsonToken.END_ARRAY)
									{
										if (jp.getCurrentToken() == JsonToken.START_OBJECT)
										{
											tag = new YPTTerm();
											
										}
										else if (jp.getCurrentToken() == JsonToken.END_OBJECT)
										{
											element.addTag(tag);
										}
										else
										{
											if (YPTElement.ID.equals(jp.getCurrentName())) //key
											{
												jp.nextToken(); //value
												tag.setId(jp.getValueAsString());
											}
											else if (YPTElement.NAME.equals(jp.getCurrentName()))
											{
												jp.nextToken(); //value
												tag.setName(jp.getValueAsString());
											}
											else
											{
												System.err.println("TAG SHIT");
											}
										}
										
									}
								}
								else if (YPTElement.CATEGORY.equals(jp.getCurrentName()))
								{
									//element.setCategories(new ArrayList<YPTTerm>());
									jp.nextToken(); //array start
									YPTTerm category = new YPTTerm();
									while(jp.nextToken() != JsonToken.END_ARRAY)
									{
										if (jp.getCurrentToken() == JsonToken.START_OBJECT)
										{
											category = new YPTTerm();
											
										}
										else if (jp.getCurrentToken() == JsonToken.END_OBJECT)
										{
											element.addCategory(category);
										}
										else
										{
											if (YPTElement.ID.equals(jp.getCurrentName())) //key
											{
												jp.nextToken(); //value
												category.setId(jp.getValueAsString());
											}
											else if (YPTElement.NAME.equals(jp.getCurrentName()))
											{
												category.setName(jp.getValueAsString());
											}
											else
											{
												System.err.println("CAT SHIT");
											}
										}
										
									}
								}
							}
						}
						break;
					case YPTElement.FEATURED_IMAGE_URL:
						jp.nextToken();
						element.setFeaturedImageURL(jp.getValueAsString());
						break;
					case YPTElement.AREA:
						jp.nextToken();
						element.setArea(jp.getValueAsString());
						break;
					case YPTService.PHONES:
						jp.nextToken(); //should be array token
						while(jp.nextToken() != JsonToken.END_ARRAY)
						{
							element.addPhone(jp.getValueAsString()); 
						}
						break;
					case YPTService.EMAILS:
						jp.nextToken(); //should be array token
						while(jp.nextToken() != JsonToken.END_ARRAY)
						{
							element.addEmail(jp.getValueAsString()); 
						}
						break;
					case YPTService.WEBSITES:
						jp.nextToken(); //should be array token
						while(jp.nextToken() != JsonToken.END_ARRAY)
						{
							element.addWebsite(jp.getValueAsString()); 
						}
						break;
					default:
						throw new IllegalArgumentException("Unexpected token. The JSON might have a nested array or object, or wrong post type");
				}
			}
			return element;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null; 
		}
	}
	
	public static YPTNewsItem jsonToNewsItem(JsonParser jp)
	{
		try 
		{
			YPTNewsItem element = new YPTNewsItem();
			
			//die if the JSON is an array - we're only doing single posts here.
			if (jp.nextToken() == JsonToken.START_ARRAY)
			{
				System.err.println("JSON reponse was a collection");
				return null;
			}			
			//loop through the JSON object
			while (jp.nextToken() != JsonToken.END_OBJECT)
			{
				//get the key
				String name = jp.getCurrentName();
				
				//based on the key, advance to the next token and get the value
				switch(name)
				{
					case YPTElement.ID:
						jp.nextToken();
						element.setId(jp.getValueAsString());
						break;
					case YPTElement.TITLE:
						jp.nextToken();
						element.setTitle(jp.getValueAsString());
						break;
					case YPTElement.TYPE:
						jp.nextToken();
						if(!YPTNewsItem.NEWS_ITEM.equals(jp.getValueAsString()))
							throw new IllegalArgumentException("An element in the JSON array was not a Service");
						break;
					case YPTElement.AUTHOR:
						element.setAuthor(new YPTAuthor(null));
						jp.nextToken(); //opening bracket
						while(jp.nextToken() != JsonToken.END_OBJECT) //key
						{
							name = jp.getCurrentName();
							switch(name)
							{
								case YPTElement.ID:
									jp.nextToken(); //value
									element.getAuthor().setId(jp.getValueAsString());
									break;
								case YPTAuthor.USERNAME:
									jp.nextToken(); //value
									element.getAuthor().setUsername(jp.getValueAsString());
									break;
								case YPTAuthor.FIRST_NAME:
									jp.nextToken(); //value
									element.getAuthor().setFirstName(jp.getValueAsString());
									break;
								case YPTAuthor.LAST_NAME:
									jp.nextToken(); //value
									element.getAuthor().setLastName(jp.getValueAsString());
									break;
							}
						}
						break;
					case YPTElement.CONTENT:
						jp.nextToken();
						element.setContent(jp.getValueAsString());
						break;
					case YPTElement.LINK:
						jp.nextToken();
						element.setLink(jp.getValueAsString());
						break;
					case YPTElement.DATE:
						jp.nextToken();
						element.setDate(jp.getValueAsString());
						break;
					case YPTElement.MODIFIED:
						jp.nextToken();
						element.setModified(jp.getValueAsString());
						break;
					case YPTElement.TERMS:
						if (jp.nextToken() == JsonToken.START_ARRAY) //start obj/start arr
						{
							//no terms at all
							element.setTags(null);
							element.setCategories(null); 
							jp.nextToken(); //end array
						}
						else //we've got terms
						{
							while(jp.nextToken() != JsonToken.END_OBJECT) //key
							{
								if (YPTElement.POST_TAG.equals(jp.getCurrentName()))
								{
									//element.setTags(new ArrayList<YPTTerm>());
									jp.nextToken(); //array start
									YPTTerm tag = new YPTTerm();
									while(jp.nextToken() != JsonToken.END_ARRAY)
									{
										if (jp.getCurrentToken() == JsonToken.START_OBJECT)
										{
											tag = new YPTTerm();
											
										}
										else if (jp.getCurrentToken() == JsonToken.END_OBJECT)
										{
											element.addTag(tag);
										}
										else
										{
											if (YPTElement.ID.equals(jp.getCurrentName())) //key
											{
												jp.nextToken(); //value
												tag.setId(jp.getValueAsString());
											}
											else if (YPTElement.NAME.equals(jp.getCurrentName()))
											{
												jp.nextToken(); //value
												tag.setName(jp.getValueAsString());
											}
											else
											{
												System.err.println("TAG SHIT");
											}
										}
										
									}
								}
								else if (YPTElement.CATEGORY.equals(jp.getCurrentName()))
								{
									//element.setCategories(new ArrayList<YPTTerm>());
									jp.nextToken(); //array start
									YPTTerm category = new YPTTerm();
									while(jp.nextToken() != JsonToken.END_ARRAY)
									{
										if (jp.getCurrentToken() == JsonToken.START_OBJECT)
										{
											category = new YPTTerm();
											
										}
										else if (jp.getCurrentToken() == JsonToken.END_OBJECT)
										{
											element.addCategory(category);
										}
										else
										{
											if (YPTElement.ID.equals(jp.getCurrentName())) //key
											{
												jp.nextToken(); //value
												category.setId(jp.getValueAsString());
											}
											else if (YPTElement.NAME.equals(jp.getCurrentName()))
											{
												category.setName(jp.getValueAsString());
											}
											else
											{
												System.err.println("CAT SHIT");
											}
										}
										
									}
								}
							}
						}
						break;
					case YPTElement.FEATURED_IMAGE_URL:
						jp.nextToken();
						element.setFeaturedImageURL(jp.getValueAsString());
						break;
					case YPTElement.AREA:
						jp.nextToken();
						element.setArea(jp.getValueAsString());
						break;
					default:
						throw new IllegalArgumentException("Unexpected token. The JSON might have a nested array or object, or wrong post type");
				}
			}
			return element;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null; 
		}

		
	}
	
	public static YPTInfo jsonToInfo(JsonParser jp)
	{
		try 
		{
			YPTInfo element = new YPTInfo();
			
			//die if the JSON is an array - we're only doing single posts here.
			if (jp.nextToken() == JsonToken.START_ARRAY)
			{
				System.err.println("JSON reponse was a collection");
				return null;
			}			
			//loop through the JSON object
			while (jp.nextToken() != JsonToken.END_OBJECT)
			{
				//get the key
				String name = jp.getCurrentName();
				//based on the key, advance to the next token and get the value
				switch(name)
				{
					case YPTElement.ID:
						jp.nextToken();
						element.setId(jp.getValueAsString());
						break;
					case YPTElement.TITLE:
						jp.nextToken();
						element.setTitle(jp.getValueAsString());
						break;
					case YPTElement.TYPE:
						jp.nextToken();
						if(!YPTInfo.INFO.equals(jp.getValueAsString()))
							throw new IllegalArgumentException("An element in the JSON array was not an Info");
						break;
					case YPTElement.AUTHOR:
						element.setAuthor(new YPTAuthor(null));
						jp.nextToken(); //opening bracket
						while(jp.nextToken() != JsonToken.END_OBJECT) //key
						{
							name = jp.getCurrentName();
							switch(name)
							{
								case YPTElement.ID:
									jp.nextToken(); //value
									element.getAuthor().setId(jp.getValueAsString());
									break;
								case YPTAuthor.USERNAME:
									jp.nextToken(); //value
									element.getAuthor().setUsername(jp.getValueAsString());
									break;
								case YPTAuthor.FIRST_NAME:
									jp.nextToken(); //value
									element.getAuthor().setFirstName(jp.getValueAsString());
									break;
								case YPTAuthor.LAST_NAME:
									jp.nextToken(); //value
									element.getAuthor().setLastName(jp.getValueAsString());
									break;
							}
						}
						break;
					case YPTElement.CONTENT:
						jp.nextToken();
						element.setContent(jp.getValueAsString());
						break;
					case YPTElement.LINK:
						jp.nextToken();
						element.setLink(jp.getValueAsString());
						break;
					case YPTElement.DATE:
						jp.nextToken();
						element.setDate(jp.getValueAsString());
						break;
					case YPTElement.MODIFIED:
						jp.nextToken();
						element.setModified(jp.getValueAsString());
						break;
					case YPTElement.TERMS:
						if (jp.nextToken() == JsonToken.START_ARRAY) //start obj/start arr
						{
							//no terms at all
							element.setTags(null);
							element.setCategories(null); 
							jp.nextToken(); //end array
						}
						else //we've got terms
						{
							while(jp.nextToken() != JsonToken.END_OBJECT) //key
							{
								if (YPTElement.POST_TAG.equals(jp.getCurrentName()))
								{
									//element.setTags(new ArrayList<YPTTerm>());
									jp.nextToken(); //array start
									YPTTerm tag = new YPTTerm();
									while(jp.nextToken() != JsonToken.END_ARRAY)
									{
										if (jp.getCurrentToken() == JsonToken.START_OBJECT)
										{
											tag = new YPTTerm();
											
										}
										else if (jp.getCurrentToken() == JsonToken.END_OBJECT)
										{
											element.addTag(tag);
										}
										else
										{
											if (YPTElement.ID.equals(jp.getCurrentName())) //key
											{
												jp.nextToken(); //value
												tag.setId(jp.getValueAsString());
											}
											else if (YPTElement.NAME.equals(jp.getCurrentName()))
											{
												jp.nextToken(); //value
												tag.setName(jp.getValueAsString());
											}
											else
											{
												System.err.println("TAG SHIT");
											}
										}
										
									}
								}
								else if (YPTElement.CATEGORY.equals(jp.getCurrentName()))
								{
									//element.setCategories(new ArrayList<YPTTerm>());
									jp.nextToken(); //array start
									YPTTerm category = new YPTTerm();
									while(jp.nextToken() != JsonToken.END_ARRAY)
									{
										if (jp.getCurrentToken() == JsonToken.START_OBJECT)
										{
											category = new YPTTerm();
											
										}
										else if (jp.getCurrentToken() == JsonToken.END_OBJECT)
										{
											element.addCategory(category);
										}
										else
										{
											if (YPTElement.ID.equals(jp.getCurrentName())) //key
											{
												jp.nextToken(); //value
												category.setId(jp.getValueAsString());
											}
											else if (YPTElement.NAME.equals(jp.getCurrentName()))
											{
												category.setName(jp.getValueAsString());
											}
											else
											{
												System.err.println("CAT SHIT");
											}
										}
										
									}
								}
							}
						}
						break;
					case YPTElement.FEATURED_IMAGE_URL:
						jp.nextToken();
						element.setFeaturedImageURL(jp.getValueAsString());
						break;
					case YPTElement.AREA:
						jp.nextToken();
						element.setArea(jp.getValueAsString());
						break;
					default:
						throw new IllegalArgumentException("Unexpected token. The JSON might have a nested array or object, or wrong post type");
				}
			}
			return element;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null; 
		}
	}
	
	public static YPTEvent jsonToEvent(JsonParser jp)
	{
		try 
		{
			YPTEvent element = new YPTEvent();
			
			//die if the JSON is an array - we're only doing single posts here.
			if (jp.nextToken() == JsonToken.START_ARRAY)
			{
				System.err.println("JSON reponse was a collection");
				return null;
			}			
			//loop through the JSON object
			while (jp.nextToken() != JsonToken.END_OBJECT)
			{
				//get the key
				String name = jp.getCurrentName();
				//based on the key, advance to the next token and get the value
				switch(name)
				{
					case YPTElement.ID:
						jp.nextToken();
						element.setId(jp.getValueAsString());
						break;
					case YPTElement.TITLE:
						jp.nextToken();
						element.setTitle(jp.getValueAsString());
						break;
					case YPTElement.TYPE:
						jp.nextToken();
						if(!YPTEvent.EVENT.equals(jp.getValueAsString()))
							throw new IllegalArgumentException("An element in the JSON array was not an Info");
						break;
					case YPTElement.AUTHOR:
						element.setAuthor(new YPTAuthor(null));
						jp.nextToken(); //opening bracket
						while(jp.nextToken() != JsonToken.END_OBJECT) //key
						{
							name = jp.getCurrentName();
							switch(name)
							{
								case YPTElement.ID:
									jp.nextToken(); //value
									element.getAuthor().setId(jp.getValueAsString());
									break;
								case YPTAuthor.USERNAME:
									jp.nextToken(); //value
									element.getAuthor().setUsername(jp.getValueAsString());
									break;
								case YPTAuthor.FIRST_NAME:
									jp.nextToken(); //value
									element.getAuthor().setFirstName(jp.getValueAsString());
									break;
								case YPTAuthor.LAST_NAME:
									jp.nextToken(); //value
									element.getAuthor().setLastName(jp.getValueAsString());
									break;
							}
						}
						break;
					case YPTElement.CONTENT:
						jp.nextToken();
						element.setContent(jp.getValueAsString());
						break;
					case YPTElement.LINK:
						jp.nextToken();
						element.setLink(jp.getValueAsString());
						break;
					case YPTElement.DATE:
						jp.nextToken();
						element.setDate(jp.getValueAsString());
						break;
					case YPTElement.MODIFIED:
						jp.nextToken();
						element.setModified(jp.getValueAsString());
						break;
					case YPTElement.TERMS:
						if (jp.nextToken() == JsonToken.START_ARRAY) //start obj/start arr
						{
							//no terms at all
							element.setTags(null);
							element.setCategories(null); 
							jp.nextToken(); //end array
						}
						else //we've got terms
						{
							while(jp.nextToken() != JsonToken.END_OBJECT) //key
							{
								if (YPTElement.POST_TAG.equals(jp.getCurrentName()))
								{
									//element.setTags(new ArrayList<YPTTerm>());
									jp.nextToken(); //array start
									YPTTerm tag = new YPTTerm();
									while(jp.nextToken() != JsonToken.END_ARRAY)
									{
										if (jp.getCurrentToken() == JsonToken.START_OBJECT)
										{
											tag = new YPTTerm();
											
										}
										else if (jp.getCurrentToken() == JsonToken.END_OBJECT)
										{
											element.addTag(tag);
										}
										else
										{
											if (YPTElement.ID.equals(jp.getCurrentName())) //key
											{
												jp.nextToken(); //value
												tag.setId(jp.getValueAsString());
											}
											else if (YPTElement.NAME.equals(jp.getCurrentName()))
											{
												jp.nextToken(); //value
												tag.setName(jp.getValueAsString());
											}
											else
											{
												System.err.println("TAG SHIT");
											}
										}
										
									}
								}
								else if (YPTElement.CATEGORY.equals(jp.getCurrentName()))
								{
									//element.setCategories(new ArrayList<YPTTerm>());
									jp.nextToken(); //array start
									YPTTerm category = new YPTTerm();
									while(jp.nextToken() != JsonToken.END_ARRAY)
									{
										if (jp.getCurrentToken() == JsonToken.START_OBJECT)
										{
											category = new YPTTerm();
											
										}
										else if (jp.getCurrentToken() == JsonToken.END_OBJECT)
										{
											element.addCategory(category);
										}
										else
										{
											if (YPTElement.ID.equals(jp.getCurrentName())) //key
											{
												jp.nextToken(); //value
												category.setId(jp.getValueAsString());
											}
											else if (YPTElement.NAME.equals(jp.getCurrentName()))
											{
												category.setName(jp.getValueAsString());
											}
											else
											{
												System.err.println("CAT SHIT");
											}
										}
										
									}
								}
							}
						}
						break;
					case YPTElement.FEATURED_IMAGE_URL:
						jp.nextToken();
						element.setFeaturedImageURL(jp.getValueAsString());
						break;
					case YPTElement.AREA:
						jp.nextToken();
						element.setArea(jp.getValueAsString());
						break;
					case YPTEvent.EVENT_LOCATION:
						jp.nextToken();
						element.setEventLocation(jp.getValueAsString());
						break;
					case YPTEvent.EVENT_DATE:
						jp.nextToken();
						element.setEventDate(jp.getValueAsString());
						break;
					default:
						throw new IllegalArgumentException("Unexpected token. The JSON might have a nested array or object, or wrong post type");
				}
			}
			return element;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null; 
		}
	}
}


