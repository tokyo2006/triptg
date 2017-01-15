package com.yeoou.tour.service.impl;

import java.util.List;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.tour.model.TicketLog;
import com.yeoou.tour.service.ITicketLogService;

public class TicketLogService extends HibernateEntityDao<TicketLog> implements
		ITicketLogService
{
	public TicketLog getTicketLogByIdAndDate(String ticketId, String realDate)
	{
		// TODO Auto-generated method stub
		String hql = "from TicketLog as log where log.ticketId=? and log.date = ?";
		List<TicketLog> ticketLog = this.find(hql, new Object[]{ticketId,realDate});
		if(ticketLog.size()!=0) return ticketLog.get(0);
		else return null;
	}
	public TicketLog getTicketLogByLogNo(String ticketId,String logNo)
	{
		String hql = "from TicketLog as log where log.ticketId=? and log.logNo = ?";
		List<TicketLog> ticketLog = this.find(hql, new Object[]{ticketId,logNo});
		if(ticketLog.size()!=0) return ticketLog.get(0);
		else return null;
	}
}
