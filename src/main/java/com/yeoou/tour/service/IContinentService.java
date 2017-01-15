package com.yeoou.tour.service;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Continent;
/**
 * 大洲相关业务接口
 * @author kensin
 * @see ICityService
 */
public interface IContinentService extends EntityDao
{
	public Page getAllContinent(int start,int limit,String dir,String sort,String name,String areaId);
	public Continent getContinentByArea(String areaId);
}
