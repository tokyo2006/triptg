package com.yeoou.tour.service;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Province;
/**
 * 省份相关业务接口
 * @author kensin
 * @see	ICityService
 */
public interface IProvinceService extends EntityDao
{
	public Page getAllProvince(int start,int limit,String dir,String sort,String name,String areaId);
	public Province getProvinceByArea(String areaId);
}
