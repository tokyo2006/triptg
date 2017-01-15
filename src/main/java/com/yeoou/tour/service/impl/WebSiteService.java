package com.yeoou.tour.service.impl;

import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.WebSite;
import com.yeoou.tour.service.IWebSiteService;

public class WebSiteService extends HibernateEntityDao<WebSite> implements
		IWebSiteService
{

	public Page getAllWebSite(int start, int limit, String dir, String sort)
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("from WebSite as webSite where 1=1");
		sb.append(" order by webSite.");
		sb.append(sort);
		sb.append(" ");
		sb.append(dir);
		return this.PageExtQuery(sb.toString(), start, limit, new Object[]{});
	}

	public WebSite getWebSiteByArea(String areaId)
	{
		// TODO Auto-generated method stub
		String hql = "from WebSite as webSite where webSite.area.areaId = ?";
		List<WebSite> ws = this.find(hql, new Object[]{areaId});
		if(ws.size()!=0)return ws.get(0);
		else return null;
	}

}
