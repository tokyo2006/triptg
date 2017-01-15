package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Line;
import com.yeoou.common.context.Global;
import com.yeoou.tour.service.ILineService;

public class LineService extends HibernateEntityDao<Line> implements ILineService
{

	public Page getAllLine(int start, int limit, String dir, String sort, String title,String regionId,String userId,String subTitle)
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		List<Object> val = new ArrayList<Object>();
		if(regionId.equals(""))
		{
			sb.append("from Line as line where 1 = 1");
		}
		else
		{
			sb.append("select distinct line from Line as line left outer join line.regions as region where region.regionId = ?");
			val.add(regionId);
		}
		if(!title.equals(""))
		{
			sb.append(" and line.title like ?");
			title = "%"+title+"%";
			val.add(title);
		}
		if(!subTitle.equals(""))
		{
			sb.append(" and line.subTitle like ?");
			subTitle = "%"+subTitle+"%";
			val.add(subTitle);
		}
		if(!userId.equals(""))
		{
			sb.append(" and line.user.userId = ?");
			val.add(userId);
		}
		sb.append(" and line.delFlag = ?");
		val.add(Global.LINE_KEEP);
		sb.append(" order by line.");
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

	public Line getLine(String lineId)
	{
		// TODO Auto-generated method stub
		String hql = "from Line as line left outer join fetch line.areas left outer join fetch line.regions  left outer join fetch line.scenerys left outer join fetch line.types  where line.lineId = ?";
		List<Line> line = this.find(hql, new Object[]{lineId});
		if(line.size()!=0)return line.get(0);
		else return null;
	}

	public List<Line> getLineByRegionId(String userId,String regionId)
	{
		// TODO Auto-generated method stub
		String hql = "select distinct line from Line as line left outer join line.regions as regions where regions.regionId=? and line.user.userId = ? and line.delFlag=?";
		return this.find(hql, new Object[]{regionId,userId,Global.LINE_KEEP});
	}
}
