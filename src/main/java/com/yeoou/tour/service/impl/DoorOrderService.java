package com.yeoou.tour.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yeoou.common.context.Global;
import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.DateUtils;
import com.yeoou.tour.model.DoorOrder;
import com.yeoou.tour.service.IDoorOrderService;

public class DoorOrderService extends HibernateEntityDao<DoorOrder> implements
		IDoorOrderService
{

	public Page getAllOrder(int start, int limit, String dir, String sort, int orderNo, String startDate, String orderDate, String customName, String teamName, int status)
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("from DoorOrder as doorOrder where 1=1");
		List<Object> val = new ArrayList<Object>();
		if(orderNo!=-1)
		{
			sb.append(" and doorOrder.orderNo = ?");
			val.add(orderNo);
		}
		if(!startDate.equals(""))
		{
			Date date1 = new Date();
			date1 = DateUtils.parseDate(startDate);
			sb.append(" and doorOrder.startDate = ?");
			val.add(date1);
		}
		if(!orderDate.equals(""))
		{
			Date date2 = new Date();
			date2 = DateUtils.parseDate(orderDate);
			sb.append(" and doorOrder.orderDate = ?");
			val.add(date2);
		}
		if(!customName.equals(""))
		{
			sb.append(" and doorOrder.customName = ?");
			val.add(customName);
		}
		if(!teamName.equals(""))
		{
			sb.append(" and doorOrder.teamName = ?");
			val.add(teamName);
		}
		if(status!=-1)
		{
			sb.append(" and doorOrder.status = ?");
			val.add(status);
		}
		if(sort.equals(""))
		{
			sb.append(" order by doorOrder.orderDate DESC");
		}
		else
		{
			sb.append(" order by doorOrder.");
			if(sort.equals("startDateStr")) sort = "startDate";
			if(sort.equals("endDateStr")) sort = "endDate";
			if(sort.equals("orderDate")) sort = "orderDate";
			sb.append(sort);
			sb.append(" ");
			sb.append(dir);
		}
		Object[] values = new Object[val.size()];
		for(int i=0;i<val.size();i++)
		{
			values[i] = val.get(i);
		}
		return PageExtQuery(sb.toString(), start, limit, values);
	}

	public int getCountNewsOrder()
	{
		// TODO Auto-generated method stub
		String hql = "select Count(*) from DoorOrder as od where  od.isNew = ? and od.orderDate = ? ";
		int isNew = 1;
		Date today  = new Date();
		today = DateUtils.parseDate(DateUtils.formatDate(today));
		Long result = (Long)this.find(hql, new Object[]{isNew,today}).get(0);
		if(result==null) return 0;
		else return result.intValue();
	}

	public int getCountNonDisposeOrder()
	{
		// TODO Auto-generated method stub
		String hql = "select Count(*) from DoorOrder as od where od.status = ? ";
		Long result = (Long)this.find(hql, new Object[]{Global.DOORORDER_STATUS_NEWORDER}).get(0);
		if(result==null) return 0;
		else return result.intValue();
	}

}
