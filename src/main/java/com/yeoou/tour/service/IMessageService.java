package com.yeoou.tour.service;


import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
/**
 * 留言相关业务接口
 * @author kensin
 *
 */
public interface IMessageService extends EntityDao
{
	/**
	 * 后台显示留言列表
	 * @param reciver	回复者
	 * @param start		页数
	 * @param limit		一页包含数量
	 * @param dir		排序方式
	 * @param sort		排序字段
	 * @return			留言列表
	 */
	public Page getAllMessage(String reciver,int start,int limit,String dir,String sort);
	/**
	 * 针对开班计划的留言列表显示
	 * @param groupId	开班组别
	 * @param start		页数
	 * @param pageNo	显示数量
	 * @param sort		排序字段
	 * @param dir		排序类别
	 * @return			留言列表
	 * 关于Page参见{@link}Page
	 */
	public Page getMessagesByTeam(String groupId,int start,int pageNo,String sort,String dir);
}
