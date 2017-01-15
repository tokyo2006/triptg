package com.yeoou.tour.service;

import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Ship;
/**
 * 船票相关业务接口
 * @author kensin
 * @see IAirService;
 */
public interface IShipService extends EntityDao
{
	public int countTeamByShip(String shipId);
	public List<Ship> getShipByUserId(String userId);
	public Page getAllShip(String userId,String dir,String sort,int limit,int start,String name);
}
