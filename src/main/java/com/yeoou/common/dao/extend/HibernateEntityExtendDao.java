package com.yeoou.common.dao.extend;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import com.yeoou.common.dao.HibernateEntityDao;

/**
 * 加强版的entity dao.
 * <p>自动处理Undeletable Entity.<br>
 * Undeletable Entity 在删除时只把状态设为无效,不会真正执行删除.<br>
 * Undeletable Entity 可以通过annotation或接口两种形式来声明.<br>
 * 其中annotation模式不限制状态列的属性名必须为"status",可以用注释来确定任意属性为状态属性.<br>
 * </p>
 *
 * @author kensin
 * @see HibernateEntityDao
 * @see EntityInfo
 * @see UndeleteableEntityOperation
 * @see Undeletable
 * @see UndeletableEntity
 */
@SuppressWarnings("unchecked")
public class HibernateEntityExtendDao<T> extends HibernateEntityDao<T> implements UndeleteableEntityOperation<T> {
	/**
	 * 保存所管理的Entity的信息.
	 */
	protected EntityInfo entityInfo;

	/**
	 * 构造函数，初始化entity信息.
	 */
	public HibernateEntityExtendDao() {
		entityInfo = new EntityInfo(entityClass);
	}

	/**
	 * 取得所有状态为有效的对象.
	 *
	 * @see UndeleteableEntityOperation#getAllValid()
	 */
	public List<T> getAllValid() {
		Criteria criteria = createCriteria();
		if (entityInfo.isUndeletable)
			criteria.add(getUnDeletableCriterion());
		return criteria.list();
	}

	/**
	 * 获取过滤已删除对象的hql条件语句.
	 *
	 * @see UndeleteableEntityOperation#getUnDeletableHQL()
	 */
	public String getUnDeletableHQL() {
		return entityInfo.statusProperty + "<>" + UNVALID_VALUE;
	}

	/**
	 * 获取过滤已删除对象的Criterion条件语句.
	 *
	 * @see UndeleteableEntityOperation#
	 */
	public Criterion getUnDeletableCriterion() {
		return Restrictions.not(Restrictions.eq(entityInfo.statusProperty, UNVALID_VALUE));
	}

	/**
	 * 重载保存函数,在保存前先调用onValid(T),进行书名不重复等数据库相关的校验.
	 *
	 * @see #onValid(Object)
	 * @see HibernateEntityDao#save(Object)
	 */
	@Override
	public void save(Object entity) {
		Assert.isInstanceOf(getEntityClass(), entity);
		onValid((T) entity);
		super.save(entity);
	}

	/**
	 * 删除对象，如果是Undeleteable的entity,设置对象的状态而不是直接删除.
	 *
	 * @see HibernateEntityDao#remove(Object)
	 */
	@Override
	public void remove(Object entity) {
		if (entityInfo.isUndeletable) {
			try {
				PropertyUtils.setProperty(entity, entityInfo.statusProperty, UNVALID_VALUE);
				save(entity);
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
		} else
			super.remove(entity);
	}

	/**
	 * 与数据库相关的校验,比如判断名字在数据库里有没有重复, 在保存时被调用,在子类重载.
	 *
	 * @see #save(Object)
	 */
	protected void onValid(T entity) {
	}

	/**
	 * 根据Map中的条件的Criteria查询.
	 *
	 * @param map Map中仅包含条件名与条件值，默认全部相同,可重载。
	 */
	public List<T> find(Map map) {
		Criteria criteria = createCriteria();
		return find(criteria, map);
	}

	/**
	 * 根据Map中的条件的Criteria查询.
	 *
	 * @param map Map中仅包含条件名与条件值,默认全部相同,可重载.
	 */
	public List<T> find(Criteria criteria, Map map) {
		Assert.notNull(criteria);
		criteria.add(Restrictions.allEq(map));
		return criteria.list();
	}
}
