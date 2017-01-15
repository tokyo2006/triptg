package com.yeoou.tour.service;

import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Air;
/**
 * 机票相关业务接口
 * @author kensin
 *
 */
public interface IAirService extends EntityDao
{
	/**
	 * 与此机票相关联的开班计划个数
	 * @param airId		地域标识
	 * @return			开班计划个数
	 */
	public int countTeamByAir(String airId);
	/**
	 * 获取相关用户填写机票信息
	 * @param userId	用户标识
	 * @return			机票相关列表
	 */
	public List<Air> getAirByUserId(String userId);
	/**
	 * 后台显示机票列表
	 * @param userId   所属用户
	 * @param dir      排序方式
	 * @param sort	   排序字段
	 * @param limit    一页所含条数
	 * @param start    开始页数
	 * @param name     机票名称	（搜索条件）
	 * @return         机票列表
	 */
	public Page getAllAir(String userId,String dir,String sort,int limit,int start,String name);
}
