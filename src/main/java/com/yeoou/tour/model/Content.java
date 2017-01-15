package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;

public class Content extends Genericmodel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8442283462132639288L;
	private String title;
	private String eating;
	private String living;
	private String trips;
	private List<PicuterUnit> puList = new ArrayList<PicuterUnit>();
	public List<PicuterUnit> getPuList()
	{
		return puList;
	}
	public void setPuList(List<PicuterUnit> puList)
	{
		this.puList = puList;
	}
	public String getEating()
	{
		return eating;
	}
	public void setEating(String eating)
	{
		this.eating = eating;
	}
	public String getLiving()
	{
		return living;
	}
	public void setLiving(String living)
	{
		this.living = living;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getTrips()
	{
		return trips;
	}
	public void setTrips(String trips)
	{
		this.trips = trips;
	}
	public static List<Content> unpackageContentUnit(String content)
	{
		if(content!=null)
		{
			List<Content> conList = new ArrayList<Content>();
			if(!content.equals(""))
			{
				if(content.indexOf("#@#")<0)
				{
					Content con = new Content();
					if(PicuterUnit.unpackagePicUnit(content)!=null)
					{
						con.setPuList(PicuterUnit.unpackagePicUnit(content));
					}
					conList.add(con);
				}
				else
				{
					String[] temp2 = content.split("#@#");
					for(int i=0;i<temp2.length;i++)
					{
						Content con = new Content();
						if(PicuterUnit.unpackagePicUnit(temp2[i])!=null)
						{
							con.setPuList(PicuterUnit.unpackagePicUnit(temp2[i]));
						}
						conList.add(con);
					}
				}
				return conList;
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	public static List<Content> unpackageContent(String content)
	{
		List<Content> conList = new ArrayList<Content>();
		if(content!=null&&!content.equals(""))
		{
			if(content.indexOf("###")<0)
			{
				String[] temp = content.split("@@@");
				Content con = new Content();
				con.setTitle(temp[0].trim());
				con.setEating(temp[1].trim());
				con.setLiving(temp[2].trim());
				con.setTrips(temp[3].trim());
				conList.add(con);
			}
			else
			{
				String[] temp2 = content.split("###");
				for(int i=0;i<temp2.length;i++)
				{
					String[] temp = temp2[i].split("@@@");
					Content con = new Content();
					con.setTitle(temp[0].trim());
					con.setEating(temp[1].trim());
					con.setLiving(temp[2].trim());
					con.setTrips(temp[3].trim());
					conList.add(con);
				}
			}
		}
		else
		{
			return null;
		}
		return conList;
	}
}
