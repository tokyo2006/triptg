package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Nation;
import com.yeoou.tour.service.INationService;

public class NationService extends HibernateEntityDao<Nation> implements INationService
{

	public Page getAllNation(int start, int limit, String dir, String sort, String name,String areaId)
	{
		List<Object> val = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder("from Nation as nation where 1=1");
		if(!name.equals(""))
		{
			name = "%"+name+"%";
			sb.append(" and nation.area.name like ?");
			val.add(name);
		}
		if(!areaId.equals(""))
		{
			sb.append(" and nation.area.parent = ?");
			val.add(areaId);
		}
		if(sort.equals("isTop")||sort.equals("isZone")||sort.equals("content")||sort.equals("synopsis")||sort.equals("gloze")||sort.equals("hit"))
		{
			sb.append(" order by nation.");
		}
		else
		{
			sb.append(" order by nation.area.");
		}
		if(sort.equals("name"))
		{
			sort = "headName";
		}
		sb.append(sort);
		sb.append(" ");
		sb.append(dir);
		Object[] values = new Object[val.size()];
		for(int i=0;i<val.size();i++)
		{
			values[i] = val.get(i);
		}
		return this.PageExtQuery(sb.toString(), start, limit, values);
	}

	public Nation getNationByArea(String areaId)
	{
		// TODO Auto-generated method stub
		String hql = "from Nation as nt where nt.area.areaId = ?";
		List<Nation> nt = this.find(hql, new Object[]{areaId});
		if(nt.size()!=0) return nt.get(0);
		else return null;
	}

}
