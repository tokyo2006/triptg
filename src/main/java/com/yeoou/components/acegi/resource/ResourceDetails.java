package com.yeoou.components.acegi.resource;

import java.io.Serializable;

import org.acegisecurity.GrantedAuthority;

/**
 * 提供资源信息
 *
 * @author cac
 */
public interface ResourceDetails extends Serializable {

	/**
	 * 资源串
	 */
	public String getResString();

	/**
	 * 资源类型,如URL,FUNCTION
	 */
	public String getResType();

	/**
	 * 返回属于该resource的authorities
	 */
	public GrantedAuthority[] getAuthorities();
	
	public void setAuthorities(GrantedAuthority[] authorities);
}
