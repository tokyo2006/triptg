package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.ThemeFlash;
import com.yeoou.tour.service.IThemeFlashService;

public class ThemeFlashService extends HibernateEntityDao<ThemeFlash> implements
		IThemeFlashService
{

	public Page getAllThemeFlash(int start, int limit, String sort, String dir,String typeId)
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("from ThemeFlash as tf where 1=1");
		List<Object> val = new ArrayList<Object>();
		if(!typeId.equals(""))
		{
			sb.append(" and tf.flashType.id = ?");
			val.add(typeId);
		}
		sb.append(" order by tf.");
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

	public int getCountByFlashType(String typeId)
	{
		// TODO Auto-generated method stub
		String hql = "select count(*) from ThemeFlash as tf where tf.flashType.id = ?";
		Long result = (Long)this.find(hql, new Object[]{typeId}).get(0);
		return result.intValue();
	}

	public List<ThemeFlash> getThemeByFlashType(String name)
	{
		// TODO Auto-generated method stub
		String hql = "from ThemeFlash as tf where tf.flashType.name = ?";
		List<ThemeFlash> tfList = this.find(hql, new Object[]{name});
		if(tfList.size()!=0) return tfList;
		else return null;
	}

}
