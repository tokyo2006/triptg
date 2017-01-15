package com.yeoou.tour.service;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.tour.model.TicketLog;
/**
 * 票务记录相关接口
 * @author kensin
 *
 */
public interface ITicketLogService extends EntityDao
{
	public TicketLog getTicketLogByIdAndDate(String ticketId, String realDate);
	public TicketLog getTicketLogByLogNo(String ticketId,String logNo);
}
