package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.NewsCount;
import com.yeoou.tour.service.INewsCountService;

public class NewsCountService extends HibernateEntityDao<NewsCount> implements
		INewsCountService
{

	public NewsCount getNewsCountByNewsId(String newsId)
	{
		// TODO Auto-generated method stub
		String hql = "from NewsCount as nc where nc.newsId = ?";
		List<NewsCount> nc = this.find(hql, new Object[]{newsId});
		if(nc.size()!=0) return nc.get(0);
		else return null;
	}

	public List<NewsCount> getNewsCountByHitForNum(int pageSize)
	{
		// TODO Auto-generated method stub
		String hql = "from NewsCount as nc order by nc.hit";
		Page page = new Page();
		page = this.PageQuery(hql, 1, pageSize, new Object[]{});
		if(page!=null)
		{
			List<NewsCount> ncList = new ArrayList<NewsCount>();
			ncList = (List<NewsCount>)page.getResult();
			if(ncList.size()==0) return null;
			else
			{
				return ncList;
			}
		}
		return null;
	}

}
