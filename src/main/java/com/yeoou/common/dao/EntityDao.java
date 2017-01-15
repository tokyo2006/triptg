package com.yeoou.common.dao;

import java.io.Serializable;
import java.util.List;

import com.yeoou.common.dao.support.Page;

/**
 * <p>
 * 标题: 实体DAO接口
 * </p>
 * <p>
 * 描述: 为数据模型提供最底层的数据操作
 * </p>
 * 
 * @author kensin
 * @version 1.0
 * @created 2008-12-16
 */
public interface EntityDao<T> {

	/**
	 * 获取OID为id的对象
	 * @param id
	 * @return
	 */
	T get(Serializable id);

	/**
	 * 获取所有多项列表
	 * @return	对象列表
	 */
	List<T> getAll();
	/**
	 * 向数据库添加记录
	 * @param o	要添加的对象
	 */
	void save(Object o);

	/**
	 * 删除数据库记录
	 * @param o	要删除的对象
	 */
	void remove(Object o);
    /**
     * 更新数据库数据
     * @param o 要更新的对象
     */
	void merge(Object o);
	/**
	 * 更新数据库数据
	 * @param o	要更新的对象
	 */
    public void update(Object o);
    /**
     * 删除数据库记录
     * @param id	要删除对象的OID
     */
	void removeById(Serializable id);
    /**
     * 获取对象列表
     * @param orderBy	要排序的属性
     * @param isAsc     是否升序
     * @return          对象列表
     */
	public List<T> getAll(String orderBy, boolean isAsc);
	/**
	 * 对象是否唯一
	 * @param entity	实体对象
	 * @param uniquePropertyNames	对象的相关属性
	 * @return	是否唯一
	 */
	public boolean isUnique(Object entity, String uniquePropertyNames);
	/**
	 * Ext框架专用分页查询函数
	 * @param hql		hql查询语句
	 * @param start		起始查询index
	 * @param limit 	每页的页数
	 * @param values	查询的参数
	 * @return  		返回Page对象
	 */
	public Page PageExtQuery(String hql,int start,int limit,Object... values);
	/**
	 * 普通分页查询函数
	 * @param hql		hql查询语句
	 * @param start		起始查询页数
	 * @param limit 	每页的页数
	 * @param values	查询的参数
	 * @return  		返回Page对象
	 */
	public Page PageQuery(String hql,int pageNo,int limit,Object... values);
	
}
