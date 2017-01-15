package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.model.Genericmodel;

public class HelpInfo extends Genericmodel
{

	private static final long serialVersionUID = 1L;
	private NewsType nt;
	private List<News> news = new ArrayList<News>();
	public List<News> getNews()
	{
		return news;
	}
	public void setNews(List<News> news)
	{
		this.news = news;
	}
	public NewsType getNt()
	{
		return nt;
	}
	public void setNt(NewsType nt)
	{
		this.nt = nt;
	}
}
