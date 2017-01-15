package com.yeoou.tour.service;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
/**
 * IP跳转相关业务接口
 * @author kensin
 *
 */
public interface IIPRouterService extends EntityDao
{
	/**
	 * 后台显示列表
	 * @param start  页数
	 * @param limit  总数
	 * @param sort	 排序字段
	 * @param dir	 排序方式
	 * @return
	 */
	public Page getAllIPRouter(int start ,int limit,String sort,String dir);
	/**
	 * 获取ip的地域标识
	 * @param ip  ip地址
	 * @return	  地域标识
	 */
	public String getAreaIdByIP(String ip);
}
