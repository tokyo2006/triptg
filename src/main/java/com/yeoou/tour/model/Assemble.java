package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.rbac.model.User;

public class Assemble extends Genericmodel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5110029751045725091L;
	private String assembleId ;
	private String name;
	private Area area;
	private User user;
	
	public Area getArea()
	{
		return area;
	}
	public void setArea(Area area)
	{
		this.area = area;
	}
	public String getAssembleId()
	{
		return assembleId;
	}
	public void setAssembleId(String assembleId)
	{
		this.assembleId = assembleId;
	}
	
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public static  List<AssembleContent> getContentPackeage(String content)
	{
		List<AssembleContent> contentList = new ArrayList<AssembleContent>();
		
		if(content != null)
		{
			if(content.indexOf("###")<0)
			{
				AssembleContent temp = new AssembleContent();
				String[] team = content.split("@@@");
				if(team.length==4)
				{
					temp.setAddress(team[0].trim());
					temp.setAsstime(team[1].trim());
					temp.setPrice(team[2].trim());
					temp.setId(team[3].trim());
					temp.setJiesong("含接送");
				}
				else
				{
					temp.setAddress(team[0].trim());
					temp.setAsstime(team[1].trim());
					temp.setPrice(team[2].trim());
					temp.setId(team[3].trim());
					temp.setJiesong(team[4].trim());
				}
				contentList.add(temp);
			}
			else
			{
				String[] con = content.split("###");
				for(int i=0;i<con.length;i++)
				{
					AssembleContent temp = new AssembleContent();
					String[] team = con[i].split("@@@");
					if(team.length==4)
					{
						temp.setAddress(team[0].trim());
						temp.setAsstime(team[1].trim());
						temp.setPrice(team[2].trim());
						temp.setId(team[3].trim());
						temp.setJiesong("含接送");
					}
					else
					{
						temp.setAddress(team[0].trim());
						temp.setAsstime(team[1].trim());
						temp.setPrice(team[2].trim());
						temp.setId(team[3].trim());
						temp.setJiesong(team[4].trim());
					}
					contentList.add(temp);
				}
			}
		}
		return contentList;
	}
}
