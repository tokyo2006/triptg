package com.yeoou.tour.service;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
/**
 * 旅游产品订单相关业务接口
 * @author kensin
 *
 */
public interface IDoorOrderService extends EntityDao
{
	/**
	 * 后台显示订单信息
	 * @param start			页数
	 * @param limit			一页包含数量
	 * @param dir			排序方式
	 * @param sort			排序字段
	 * @param orderNo		订单号	（搜索条件）
	 * @param startDate		开始日期	（搜索条件）
	 * @param orderDate		下单日期	（搜索条件）
	 * @param customName	客户名	（搜索条件）
	 * @param teamName		开班计划名（搜索条件）
	 * @param status		订单状态	（搜索条件）
	 * @return				订单列表
	 */
	public Page getAllOrder(int start,int limit,String dir,String sort,int orderNo,String startDate,String orderDate,String customName,String teamName,int status);
	/**
	 * 统计新订单个数
	 * @return	新订单个数
	 */
	public int getCountNewsOrder();
	/**
	 * 未处理订单个数
	 * @return	未处理订单个数
	 */
	public int getCountNonDisposeOrder();
}
