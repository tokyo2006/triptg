package com.yeoou.rbac.common;

import com.yeoou.common.exception.BusinessException;
/**
 * 为认证抛出相关异常
 * @author kensin
 */
public class RBACException extends BusinessException
{
	private static final long serialVersionUID = 1135872021628495419L;
	public RBACException(String s) {
		super(s);
	}

	public RBACException(Throwable throwable) {
		super(throwable);
	}

	public RBACException() {
	}
}
