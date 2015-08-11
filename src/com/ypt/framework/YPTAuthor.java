package com.ypt.framework;

public class YPTAuthor extends YPTElement
{
	private static final long serialVersionUID = 606808257895994214L;
	public static final String NAME = "name";
	public static final String USERNAME = "username";
	public static final String FIRST_NAME = "first_name";
	public static final String LAST_NAME = "last_name";
	
	protected String username;
	protected String firstName;
	protected String lastName;
	
	public YPTAuthor()
	{
		super(null);
	}
	
	public YPTAuthor(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public YPTElement clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int compareTo(YPTElement arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
