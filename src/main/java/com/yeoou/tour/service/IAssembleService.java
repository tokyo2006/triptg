package com.yeoou.tour.service;

import java.io.Serializable;
import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Assemble;
/**
 * 集合地相关业务接口
 * @author kensin
 *
 */
public interface IAssembleService extends EntityDao
{
	/**
	 * 获取用户所属地域所属的集合地信息
	 * @param userId	填写集合地所属者
	 * @param areaId	集合地所属地域
	 * @return			集合地列表
	 */
	public List<Assemble> getAssemble(String userId,String areaId);
	/**
	 * 后台显示集合地列表
	 * @param userId	填写集合地所属者
	 * @param areaId	集合地所属地域
	 * @param start		页数
	 * @param limit		一页显示条数
	 * @param dir		排序方式
	 * @param sort		排序字段
	 * @return
	 */
	public Page getAllAssemble(Serializable userId,Serializable areaId,int start,int limit,String dir,String sort);
}
