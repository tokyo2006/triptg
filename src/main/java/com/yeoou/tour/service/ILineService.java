package com.yeoou.tour.service;

import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Line;
/**
 * 行程相关业务接口
 * @author kensin
 *
 */
public interface ILineService extends EntityDao
{
	/**
	 * 后台显示行程信息
	 * @param start		页数
	 * @param limit		一页包含数量
	 * @param dir		排序方式
	 * @param sort		排序字段
	 * @param title		行程标题
	 * @param regionId	所属类别
	 * @param userId	所属用户
	 * @param subTitle	短标题
	 * @return	行程信息列表
	 */
	public Page getAllLine(int start,int limit,String dir,String sort,String title,String regionId,String userId,String subTitle);
	/**
	 * 获取行程详细信息
	 * @param lineId	行程标识
	 * @return			详细行程对象
	 */
	public Line getLine(String lineId);
	/**
	 * 获取行程列表
	 * @param userId	所属用户
	 * @param regionId	所属类别
	 * @return			行程列表
	 */
	public List<Line> getLineByRegionId(String userId,String regionId);
}
