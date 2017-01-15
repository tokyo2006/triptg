package com.yeoou.common.dao.extend;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识商业对象不能被删除,只能被设为无效的Annoation.
 * <p/>
 * 相比inferface的标示方式，annotation 方式更少侵入性,可以定义任意属性代表status,而默认为status属性.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Undeletable {
	String status() default UndeleteableEntityOperation.STATUS;
}
