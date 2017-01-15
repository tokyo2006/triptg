package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.model.Genericmodel;

public class PicuterUnit extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String breUrl;
	private String id;
	private String name;
	
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getBreUrl()
	{
		return breUrl;
	}

	public void setBreUrl(String breUrl)
	{
		this.breUrl = breUrl;
	}

	

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public static List<PicuterUnit> unpackagePicUnit(String content)
	{
		if(content!=null)
		{
			if(!content.equals(""))
			{
				List<PicuterUnit> puList = new ArrayList<PicuterUnit>();
				if(content.indexOf("#")<0)
				{
					String[] temp= content.split("@");
					PicuterUnit pu = new PicuterUnit();
					pu.setBreUrl(temp[0].trim());
					pu.setId(temp[1].trim());
					pu.setName(temp[2].trim());
					puList.add(pu);
				}
				else
				{
					String[] temp2 = content.split("#");
					for(int i=0;i<temp2.length;i++)
					{
						String[] temp= temp2[i].split("@");
						PicuterUnit pu = new PicuterUnit();
						pu.setBreUrl(temp[0].trim());
						pu.setId(temp[1].trim());
						pu.setName(temp[2].trim());
						puList.add(pu);
					}
				}
				return puList;
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
}
