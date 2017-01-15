package com.yeoou.tour.service.impl;

import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.StringUtils;
import com.yeoou.tour.model.IPRouter;
import com.yeoou.tour.service.IIPRouterService;

public class IPRouterService extends HibernateEntityDao<IPRouter> implements
		IIPRouterService
{
	public Page getAllIPRouter(int start ,int limit,String sort,String dir)
	{
		StringBuilder sb = new StringBuilder("from IPRouter as ipr where 1=1");
		sb.append(" order by ipr.");
		sb.append(sort);
		sb.append(" ");
		sb.append(dir);
		return this.PageExtQuery(sb.toString(), start, limit, new Object[]{});
	}

	public String getAreaIdByIP(String ip)
	{
		// TODO Auto-generated method stub
		String hql = "from IPRouter as ipr where ipr.startIp <= ? and ipr.endIp >= ?";
		long add = StringUtils.getIPStringToLong(ip);
		List<IPRouter> ipList = this.find(hql, new Object[]{add,add});
		if(ipList.size()!=0) return ipList.get(0).getAreaId();
		return "402880e51a512576011a51310be90007";
	}
}
