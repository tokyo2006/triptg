package com.yeoou.components.acegi.resource;

import org.acegisecurity.GrantedAuthority;
import org.springframework.util.Assert;

/**
 * ResourceDetails的实现类
 * resString 资源串， 如Url资源串 /admin/index.jsp, Method资源串 com.abc.service.userManager.save 等
 * resType 资源类型，如URL, METHOD 等
 * authorities 该资源所拥有的权限
 * @author cac
 */
public class Resource implements ResourceDetails {
	
	private static final long serialVersionUID = 4640497640533200574L;

	public static final String RESOURCE_TYPE_URL = "URL";

	public static final String RESOURCE_TYPE_METHOD = "METHOD";

	public static final String RESOURCE_TYPE_TAG = "TAG";

	private String resString;

	private String resType;

	private GrantedAuthority[] authorities;

	public Resource(String resString, String resType, GrantedAuthority[] authorities) {
		Assert.notNull(resString,"Cannot pass null or empty values to resource string");
		Assert.notNull(resType,"Cannot pass null or empty values to resource type");
		this.resString = resString;
		this.resType = resType;
		setAuthorities(authorities);
	}

	@Override
	public boolean equals(Object rhs) {
		if (!(rhs instanceof Resource))
			return false;
		Resource resauth = (Resource) rhs;
		if(!getResString().equals(resauth.getResString()))
			return false;
		if(!getResType().equals(resauth.getResType()))
			return false;
		if (resauth.getAuthorities().length != getAuthorities().length)
			return false;
		for (int i = 0; i < getAuthorities().length; i++) {
			if (!getAuthorities()[i].equals(resauth.getAuthorities()[i]))
				return false;
		}
		return  true ;
	}

	@Override
	public int hashCode() {
		int code = 168;
		if (getAuthorities() != null) {
			for (int i = 0; i < getAuthorities().length; i++)
				code *= getAuthorities()[i].hashCode() % 7;
		}
		if (getResString() != null)
			code *= getResString().hashCode() % 7;
		return code;
	}

	public String getResString() {
		return resString;
	}

	public void setResString(String resString) {
		this.resString = resString;
	}

	public GrantedAuthority[] getAuthorities() {
		return authorities;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public void setAuthorities(GrantedAuthority[] authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority array");
		for (int i = 0; i < authorities.length; i++) {
			Assert.notNull(authorities[i],
					"Granted authority element " + i + " is null - GrantedAuthority[] cannot contain any null elements");
		}
		this.authorities = authorities;	
	}

}
