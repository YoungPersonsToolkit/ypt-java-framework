package com.ypt.framework;

import java.util.ArrayList;

public class YPTService extends YPTElement
{
	private static final long serialVersionUID = -8032769522037532465L;
	public static final String SERVICE = "service";
	public static final String PHONES = "phones";
	public static final String EMAILS = "emails";
	public static final String WEBSITES = "websites";
	
	protected ArrayList<String> phones;
	protected ArrayList<String> emails;
	protected ArrayList<String> websites;
	
	
	public YPTService(String id) 
	{
		super(id);
		phones = new ArrayList<String>();
		emails = new ArrayList<String>();
		websites = new ArrayList<String>();
	}

	public YPTService() {
		// TODO Auto-generated constructor stub
		this(null);
	}

	@Override
	public YPTElement clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(YPTElement arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder(super.toString());
		
		for (int i = 0; i < phones.size(); i++)
		{
			str.append("Phone: \"" + phones.get(i) + "\"\n");
		}
		for (int i = 0; i < emails.size(); i++)
		{
			str.append("Email: \"" + emails.get(i) + "\"\n");
		}
		for (int i = 0; i < websites.size(); i++)
		{
			str.append("Website: \"" + websites.get(i) + "\"\n");
		}
		
		return str.toString();
	}

	public void addPhone(String phone)
	{
		phones.add(phone);
	}
	
	public void addEmail(String email)
	{
		emails.add(email);
	}
	public void addWebsite(String website)
	{
		websites.add(website);
	}
	
	public ArrayList<String> getPhones() {
		return phones;
	}
	
	public String[] getPhonesAsArray()
	{
		return phones.toArray(new String[phones.size()]); 
	}

	public ArrayList<String> getEmails() {
		return emails;
	}
	
	public String[] getEmailsAsArray()
	{
		return emails.toArray(new String[emails.size()]); 
	}

	public ArrayList<String> getWebsites() {
		return websites;
	}
	
	public String[] getWebsitesAsArray()
	{
		return websites.toArray(new String[websites.size()]); 
	}

	public void setPhones(ArrayList<String> phones) {
		this.phones = phones;
	}

	public void setEmails(ArrayList<String> emails) {
		this.emails = emails;
	}

	public void setWebsites(ArrayList<String> websites) {
		this.websites = websites;
	}

}
