package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.City;
import com.yeoou.tour.service.ICityService;

public class CityService extends HibernateEntityDao<City> implements ICityService
{

	public Page getAllCity(int start, int limit, String dir, String sort, String name,String areaId)
	{
		List<Object> val = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder("from City as city where 1=1");
		if(!areaId.equals(""))
		{
			sb.append(" and city.area.parent = ?");
			val.add(areaId);
		}
		if(!name.equals(""))
		{
			name = "%"+name+"%";
			sb.append(" and city.area.name like ?");
			val.add(name);
		}
		if(sort.equals("isTop")||sort.equals("isZone")||sort.equals("content")||sort.equals("synopsis")||sort.equals("gloze")||sort.equals("hit")||sort.equals("mapUrl"))
		{
			sb.append(" order by city.");
		}
		else
		{
			sb.append(" order by city.area.");
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
	@SuppressWarnings("unchecked")
	public City getCityByArea(String areaId)
	{
		// TODO Auto-generated method stub
		String hql = "from City as city where city.area.areaId = ?";
		List<City> city = this.find(hql, new Object[]{areaId});
		if(city.size()!=0) return city.get(0);
		else return null;
	}

}
