package com.yeoou.tour.service;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.City;

/**
 * 城市信息相关业务接口
 * @author kensin
 *
 */
public interface ICityService extends EntityDao
{
	/**
	 * 后台显示城市信息列表
	 * @param start     页数
	 * @param limit		一页包含数量
	 * @param dir		排序方式
	 * @param sort		排序字段
	 * @param name		城市名
	 * @param areaId	地域标识
	 * @return			城市列表
	 */
	public Page getAllCity(int start,int limit,String dir,String sort,String name,String areaId);
	/**
	 * 获取相关地域的城市信息
	 * @param areaId	地域标识
	 * @return	城市信息
	 */
	public City getCityByArea(String areaId);
}
