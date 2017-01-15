package com.yeoou.tour.service;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.WebSite;
/**
 * 网站信息相关业务接口
 * @author kensin
 *
 */
public interface IWebSiteService extends EntityDao
{
	/**
	 * 后台显示网站信息
	 * @param start	页数
	 * @param limit	一页数量
	 * @param dir	排序方式
	 * @param sort	排序字段
	 * @return	后台网站信息显示
	 */
	public Page getAllWebSite(int start,int limit,String dir,String sort);
	/**
	 * 根据地域不同获取不同的网站设置
	 * @param areaId	地域标识
	 * @return			网站对象
	 */
	public WebSite getWebSiteByArea(String areaId);
}
