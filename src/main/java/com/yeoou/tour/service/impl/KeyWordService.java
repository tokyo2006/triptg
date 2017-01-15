package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.KeyWord;
import com.yeoou.tour.service.IKeyWordService;

public class KeyWordService extends HibernateEntityDao<KeyWord> implements
		IKeyWordService
{

	public Page getAllKeyWord(int start, int limit, String dir, String sort, String name)
	{
		// TODO Auto-generated method stub
		List<Object> val = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder("from KeyWord as keyWord where 1=1");
		if(!name.equals(""))
		{
			name = "%"+name+"%";
			sb.append(" and keyWord.name like ?");
			val.add(name);
		}
		sb.append(" order by keyWord.");
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
	public KeyWord getKeyWordByName(String name)
	{
		String hql = "from KeyWord as kw where kw.name = ?";
		List<KeyWord> kwList = this.find(hql,new Object[]{name});
		if(kwList.size()!=0) return kwList.get(0);
		else return null;
	}
	public KeyWord getKeyWordBySceneryId(String sceneryId)
	{
		// TODO Auto-generated method stub
		String hql = "from KeyWord as kw where kw.id = ?";
		List<KeyWord> kwList = this.find(hql,new Object[]{sceneryId});
		if(kwList.size()!=0) return kwList.get(0);
		else return null;
	}
	
}
