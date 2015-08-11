package com.ypt.framework;

public class YPTTerm 
{
	private String id;
	private String name;
	
	public YPTTerm(String id)
	{
		this.id = id;
		this.name = null;
	}
	
	public YPTTerm(String id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public YPTTerm()
	{
		this(null);
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
}
