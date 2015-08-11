package com.ypt.framework;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class YPTElement
	implements Serializable, Comparable<YPTElement>, Cloneable
{
	private static final long serialVersionUID = 7346031612521221172L;
	
	public static final String ID = "ID";
	public static final String TITLE = "title";
	public static final String TYPE = "type";
	public static final String AUTHOR = "author";
	public static final String CONTENT = "content";
	public static final String LINK = "link";
	public static final String DATE = "date";
	public static final String MODIFIED = "modified";
	public static final String FEATURED_IMAGE_URL = "featured_image_url";
	public static final String AREA = "area";
	public static final String TERMS = "terms";
	public static final String POST_TAG = "post_tag";
	public static final String CATEGORY = "category";
	public static final String NAME = "name"; 
	
	protected String id;
	protected String title;
	protected YPTAuthor author;
	protected String content;
	protected String link;
	protected String date;
	protected String modified;
	protected String featuredImageURL;
	protected String area;
	protected ArrayList<YPTTerm> tags;
	protected ArrayList<YPTTerm> categories;
	
	public YPTElement(String id) {
		super();
		this.id = id;
		this.author = null;
		tags = new ArrayList<YPTTerm>();
		categories = new ArrayList<YPTTerm>();
	}

	public abstract YPTElement clone();
	
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		
		str.append("ID: \"" + id + "\"\n");
		str.append("Title: \"" + title + "\"\n");
		str.append("Author: \"" + author + "\"\n");
		str.append("Content: \"" + content + "\"\n");
		str.append("Link: \"" + link + "\"\n");
		str.append("Date: \"" + date + "\"\n");
		str.append("Modified: \"" + modified + "\"\n");
		str.append("FeaturedImageURL: \"" + featuredImageURL + "\"\n");
		str.append("Area: \"" + area + "\"\n");
		str.append("Categories: ");
		if (categories != null)
		{
			for (int i = 0; i < categories.size(); i++)
			{
				str.append("\n\tID: " + categories.get(i).getId() +
							", Name: " + categories.get(i).getName());
			}
		}
		str.append("\nTags: \n");
		if(tags != null)
		{
			for (int i = 0; i < tags.size(); i++)
			{
				str.append("\tID: " + tags.get(i).getId() +
							", Name: " + tags.get(i).getName() + "\n");
			}
		}
		return str.toString();
	}
	
	@Override
	public abstract int compareTo(YPTElement arg0);

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public YPTAuthor getAuthor() {
		return author;
	}

	public String getContent() {
		return content;
	}

	public String getLink() {
		return link;
	}

	public String getDate() {
		return date;
	}

	public String getModified() {
		return modified;
	}

	public String getFeaturedImageURL() {
		return featuredImageURL;
	}

	public String getArea() {
		return area;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(YPTAuthor author) {
		this.author = author;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public void setFeaturedImageURL(String featuredImageURL) {
		this.featuredImageURL = featuredImageURL;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public ArrayList<YPTTerm> getTags() {
		return tags;
	}

	public ArrayList<YPTTerm> getCategories() {
		return categories;
	}

	public void setTags(ArrayList<YPTTerm> tags) {
		this.tags = tags;
	}

	public void setCategories(ArrayList<YPTTerm> categories) {
		this.categories = categories;
	}
	
	public void addTag(YPTTerm tag)
	{
		if (tags == null)
			tags = new ArrayList<YPTTerm>();
		tags.add(tag);
	}
	
	public void addCategory(YPTTerm category)
	{
		if (categories == null)
			categories = new ArrayList<YPTTerm>();
		categories.add(category);
	}
	
	public YPTTerm[] getTagsAsArray()
	{
		return tags.toArray(new YPTTerm[tags.size()]);
	}
	
	public YPTTerm[] getCategoriesAsArray()
	{
		return categories.toArray(new YPTTerm[categories.size()]);
	}

	
}
