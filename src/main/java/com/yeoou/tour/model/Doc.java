package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;

public class Doc extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String name;
	private String url;
	public static List<Doc> getPackageDoc(String docName,String docUrl)
	{
		List<Doc> docList = new ArrayList<Doc>();
		if(docName.equals("")||docUrl.equals(""))
		{
			return null;
		}
		if(docName.indexOf("@@@")<0)
		{
			Doc doc = new Doc();
			doc.setName(docName.trim());
			doc.setUrl(docUrl.trim());
			docList.add(doc);
		}
		else
		{
			String[] name = docName.split("@@@");
			String[] url = docUrl.split("@@@");
			if(name.length!=url.length)
			{
				return null;
			}
			for(int i=0;i<name.length;i++)
			{
				Doc doc = new Doc();
				doc.setName(name[i].trim());
				doc.setUrl(url[i].trim());
				docList.add(doc);
			}
		}
		return docList;
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
