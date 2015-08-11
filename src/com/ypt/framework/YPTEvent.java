package com.ypt.framework;

public class YPTEvent extends YPTElement
{
	private static final long serialVersionUID = 2586037517492994654L;
	
	public static final String EVENT = "event";
	public static final String EVENT_DATE = "event_date";
	public static final String EVENT_LOCATION = "event_location";
	
	protected String eventDate;
	protected String eventLocation;
	
	public YPTEvent()
	{
		super(null);
	}
	
	public YPTEvent(String id) {
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
		StringBuilder str = new StringBuilder(super.toString());
		
		str.append("EventDate: \"" + eventDate + "\"\n");
		str.append("EventLocation: \"" + eventLocation + "\"\n");
		
		return str.toString();
	}

	public String getEventDate() {
		return eventDate;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	@Override
	public int compareTo(YPTElement arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
