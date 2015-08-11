package com.ypt.framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class YPTElementList<T extends YPTElement> extends YPTElement
{
	private static final long serialVersionUID = -6211183521444844513L;
	
	protected LinkedHashMap<String, T> map;
	
	public YPTElementList()
	{
		this(null);
	}
	
	public YPTElementList(String id) {
		super(id);
		this.map = new LinkedHashMap<String, T>();
	}

	@Override
	public YPTElementList<T> clone() {
		// TODO Auto-generated method stub
		return null; 
	}

	@Override
	public String toString() 
	{
		StringBuilder str = new StringBuilder();
		
		str.append("YPTElementList - ID: \"" + id + "\'\n{\n");
		for (int i = 0; i < size(); i++)
		{
			str.append(get(i).toString() + "\n"); 
		}
		str.append("}");
		
		return str.toString();
	}
	
	public LinkedHashMap<String, T> getMap()
	{
		return map;
	}
	
	public void setMap(LinkedHashMap<String, T> map)
	{
		this.map = map;
	}
	
	public void add(T element)
	{
		map.put(element.getId(), element);
	}
	
	public void remove(String id)
	{
		map.remove(id);
	}
	
	public int size()
	{
		return map.size();
	}
	
	public void join(YPTElementList<T> join)
	{
		map.putAll(join.getMap()); 
	}
	
	@SuppressWarnings("unchecked")
	public T get(int index)
	{
		return (T) map.values().toArray()[index];
	} 
	
	public T getById(String id)
	{
		return map.get(id);
	}
	
	public void sort()
	{
		//create a new list of all the key/value pairs in the map.
		List<Map.Entry<String, T>> entries = new ArrayList<Map.Entry<String, T>>(map.entrySet());
		
		//sort the list with a custom comparator. SmartElement implements Comparable.
		Collections.sort(entries, new Comparator<Map.Entry<String, T>>()
		{
			@Override
			public int compare(Entry<String, T> lhs, Entry<String, T> rhs)
			{
				//compare the values of each key/value pair in the map (from the list)
				return lhs.getValue().compareTo(rhs.getValue()); 
			}
	
		});
		
		//in order, put the elements back into a new map in order. LinkedHashMap retains order.
		LinkedHashMap<String, T> sortedMap = new LinkedHashMap<String, T>();
		for(Map.Entry<String, T> entry : entries)
		{
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		
		//repace the map with the new sorted map. The end
		map = sortedMap;
	}

	@Override
	public int compareTo(YPTElement arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
