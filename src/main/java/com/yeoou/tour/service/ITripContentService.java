package com.yeoou.tour.service;

import java.util.List;
import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.TripContent;
/**
 * 页面资源内容相关业务接口
 * @author kensin
 *
 */
public interface ITripContentService extends EntityDao
{
	/**
	 * 统计页面类别为tripModel的资源数量
	 * @param tripModelId
	 * @return	页面内容数量
	 */
	public int getCountByTripModel(String tripModelId);
	/**
	 * 前台页面内容列表
	 * @param areaId	地域标识
	 * @param name		页面类别名称
	 * @return			页面内容列表
	 */
	public List<TripContent> getTripContentList(String areaId,String name);
	/**
	 * 后台显示页面列表
	 * @param start		页数
	 * @param limit		一页的数量
	 * @param sort		排序字段
	 * @param dir		排序方式
	 * @param classId	类别标识
	 * @return			后台页面列表
	 */
	public Page getAllTripContent(int start,int limit,String sort,String dir,String classId);
}
