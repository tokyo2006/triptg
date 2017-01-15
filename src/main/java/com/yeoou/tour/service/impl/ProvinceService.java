package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Province;
import com.yeoou.tour.service.IProvinceService;

public class ProvinceService extends HibernateEntityDao<Province> implements
		IProvinceService
{

	public Page getAllProvince(int start, int limit, String dir, String sort, String name,String areaId)
	{
		List<Object> val = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder("from Province as pro where 1=1");
		if(!name.equals(""))
		{
			name = "%"+name+"%";
			sb.append(" and pro.area.name like ?");
			val.add(name);
		}
		if(!areaId.equals(""))
		{
			sb.append(" and pro.area.parent = ?");
			val.add(areaId);
		}
		if(sort.equals("isTop")||sort.equals("isZone")||sort.equals("content")||sort.equals("synopsis")||sort.equals("gloze")||sort.equals("hit"))
		{
			sb.append(" order by pro.");
		}
		else
		{
			sb.append(" order by pro.area.");
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

	public Province getProvinceByArea(String areaId)
	{
		// TODO Auto-generated method stub
		String hql = "from Province as pro where pro.area.areaId = ?";
		List<Province> pro = this.find(hql, new Object[]{areaId});
		if(pro.size()!=0) return pro.get(0);
		else return null;
	}

}
