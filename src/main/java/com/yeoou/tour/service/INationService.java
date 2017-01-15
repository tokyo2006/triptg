package com.yeoou.tour.service;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Nation;
/**
 * 国家相关业务接口
 * @author kensin
 * @see ICityService
 */
public interface INationService extends EntityDao
{
	public Page getAllNation(int start,int limit,String dir,String sort,String name,String areaId);
	public Nation getNationByArea(String areaId);
}
