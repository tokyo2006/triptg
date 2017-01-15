package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.model.Genericmodel;

public class PicuterFour extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String url;
	private String breUrl;
	private String name;
	private String Id;
	public static List<PicuterFour> unpackagePicFour(String content)
	{
		if(content!=null)
		{
			if(!content.equals(""))
			{
				List<PicuterFour> picList = new ArrayList<PicuterFour>();
				if(content.indexOf("###")<0)
				{
					String[] temp = content.split("@");
					PicuterFour pf = new PicuterFour();
					pf.setUrl(temp[0].trim());
					pf.setBreUrl(temp[1].trim());
					pf.setId(temp[2].trim());
					picList.add(pf);
				}
				else
				{
					String[] temp2 = content.split("###");
					for(int i=0;i<temp2.length;i++)
					{
						String[] temp = temp2[i].split("@");
						PicuterFour pf = new PicuterFour();
						pf.setUrl(temp[0].trim());
						pf.setBreUrl(temp[1].trim());
						pf.setId(temp[2].trim());
						picList.add(pf);
					}
					
				}
				return picList;
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
	public String getBreUrl()
	{
		return breUrl;
	}
	public void setBreUrl(String breUrl)
	{
		this.breUrl = breUrl;
	}
	public String getId()
	{
		return Id;
	}
	public void setId(String id)
	{
		Id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
}
