package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Visa;
import com.yeoou.tour.service.IVisaService;

public class VisaService extends HibernateEntityDao<Visa> implements IVisaService
{

	public Page getAllVisa(String sort, String dir, int start, int limit, String name, String nationId)
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("from Visa as visa where 1=1");
		List<Object> val = new ArrayList<Object>();
		if(!name.equals(""))
		{
			sb.append(" and visa.name = ?");
			val.add(name);
		}
		if(!nationId.equals(""))
		{
			sb.append(" and visa.nation.areaId = ?");
			val.add(nationId);
		}
		sb.append(" order by visa.");
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

	public List<Visa> getVisaByNation(String areaId)
	{
		// TODO Auto-generated method stub
		String hql = "from Visa as visa where visa.nation.areaId = ?";
		List<Visa> vs = this.find(hql, new Object[]{areaId});
		if(vs.size()!=0)return vs;
		else return null;
	}

	public List<Visa> getVisaForNum(int pageSize) {
		// TODO Auto-generated method stub
		String hql = "from Visa as visa order by visa.isTopic DESC";
		Page page = new Page();
		page = this.PageQuery(hql, 1, pageSize, new Object[]{});
		if(page!=null)
		{
			List<Visa> visas = new ArrayList<Visa>();
			visas = (List<Visa>)page.getResult();
			if(visas.size()==0)return null;
			else
			{
				return visas;
			}
		}
		else return null;
	}

	public Page getVisaList(int start, int limit)
	{
		// TODO Auto-generated method stub
		String hql = "from Visa as visa order by visa.isTopic DESC";
		return this.PageQuery(hql, start, limit, new Object[]{});
	}

}
