package com.yeoou.tour.service;

import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Bus;
import com.yeoou.tour.service.impl.AirService;
/**
 * 车票相关业务接口
 * @author kensin
 * @see IAirService
 */
public interface IBusService extends EntityDao
{
	public int countTeamByBus(String busId);
	public List<Bus> getBusByUserId(String userId);
	public Page getAllBus(String userId,String dir,String sort,int limit,int start,String name);
}
