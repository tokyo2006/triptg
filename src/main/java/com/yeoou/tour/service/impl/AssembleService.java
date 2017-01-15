package com.yeoou.tour.service.impl;

import java.io.Serializable;
import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Assemble;
import com.yeoou.tour.service.IAssembleService;

public class AssembleService extends HibernateEntityDao<Assemble> implements IAssembleService
{


	public Page getAllAssemble(Serializable userId,Serializable areaId,int start,int limit,String dir,String sort)
	{
		String hql="from Assemble as assemble where assemble.user.userId=? and assemble.area.areaId=?";
		hql = hql+" order by assemble."+sort+" "+dir;
		return this.PageExtQuery(hql, start, limit, new Object[]{userId,areaId});
	}
//	public int countTeamByAssemble(String assembleId)
//	{
//		String hql="select count(*) from Team as team where team.assemble.assembleId = ?";
//		Long result = (Long)this.find(hql, new Object[]{assembleId}).get(0);
//		return result.intValue();
//	}
	public List<Assemble> getAssemble(String userId, String areaId)
	{
		// TODO Auto-generated method stub
		String hql="from Assemble as assemble where assemble.user.userId=? and assemble.area.areaId=?";
		List<Assemble> assList = this.find(hql, new Object[]{userId,areaId});
		if(assList.size()!=0) return  assList;
		else return null;
	}


}
