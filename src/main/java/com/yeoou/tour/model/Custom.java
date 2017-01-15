package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;

public class Custom extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String name;
	private String sex;
	private String no;
	private String contact;
	public static  List<Custom> getContentPackeage(String content)
	{
		List<Custom> contentList = new ArrayList<Custom>();
		
		if(content != null)
		{
			if(content.indexOf("@@@")<0)
			{
				Custom temp = new Custom();
				String[] team = content.split("###");
				temp.setName(team[0]);
				temp.setSex(team[1]);
				temp.setNo(team[2]);
				temp.setContact(team[3]);
				contentList.add(temp);
			}
			else
			{
				String[] con = content.split("@@@");
				for(int i=0;i<con.length;i++)
				{
					Custom temp = new Custom();
					String[] team = con[i].split("###");
					temp.setName(team[0]);
					temp.setSex(team[1]);
					temp.setNo(team[2]);
					temp.setContact(team[3]);
					contentList.add(temp);
				}
			}
		}
		return contentList;
	}
	public String getContact()
	{
		return contact;
	}
	public void setContact(String contact)
	{
		this.contact = contact;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getNo()
	{
		return no;
	}
	public void setNo(String no)
	{
		this.no = no;
	}
	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}
}
