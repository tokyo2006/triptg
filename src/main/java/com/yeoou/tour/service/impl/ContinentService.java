package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Continent;
import com.yeoou.tour.service.IContinentService;

public class ContinentService extends HibernateEntityDao<Continent> implements
		IContinentService
{

	public Page getAllContinent(int start, int limit, String dir, String sort, String name,String areaId)
	{
		List<Object> val = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder("from Continent as con where 1=1");
		if(!name.equals(""))
		{
			name = "%"+name+"%";
			sb.append(" and con.area.name like ?");
			val.add(name);
		}
		if(!areaId.equals(""))
		{
			sb.append(" and con.area.parent = ?");
			val.add(areaId);
		}
		if(sort.equals("isTop")||sort.equals("isZone")||sort.equals("content")||sort.equals("synopsis")||sort.equals("gloze")||sort.equals("hit")||sort.equals("mapUrl")||sort.equals("mapTopic"))
		{
			sb.append(" order by con.");
		}
		else
		{
			sb.append(" order by con.area.");
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

	public Continent getContinentByArea(String areaId)
	{
		// TODO Auto-generated method stub
		String hql = "from Continent as con where con.area.areaId = ?";
		List<Continent> con = this.find(hql, new Object[]{areaId});
		if(con.size()!=0) return con.get(0);
		else return null;
	}

}
