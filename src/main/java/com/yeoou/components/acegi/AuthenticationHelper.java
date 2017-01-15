package com.yeoou.components.acegi;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.ConfigAttributeEditor;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.apache.commons.beanutils.BeanUtils;

public class AuthenticationHelper {
	
	/**
	 * 由GrantedAuthority Collection 转为 GrantedAuthority 数组
	 * @param auths
	 * @return
	 */
	public static GrantedAuthority[] convertToGrantedAuthority(Collection<GrantedAuthorityImpl> auths){
		return (GrantedAuthority[]) auths.toArray(new GrantedAuthority[auths.size()]);
	}
	
	/**
	 * 把Bean中的某个属性的值转化为GrantedAuthority
	 * @param list Collection 一组Bean
	 * @param propertyName 属性名
	 * @return GrantedAuthority[] GrantedAuthority数组
	 */
	public static GrantedAuthority[] convertToGrantedAuthority(Collection list, String propertyName){
		Set<GrantedAuthorityImpl> authorities = new HashSet<GrantedAuthorityImpl>();
		try {
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				String authority = (String) BeanUtils.getProperty(iter.next(), propertyName) ;
				authorities.add(new GrantedAuthorityImpl(authority));
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		return convertToGrantedAuthority(authorities);
	}
	
	/**
	 * 把权限组转为 ConfigAttributeDefinition
	 * @param authorities 权限
	 * @param isProtectAllResource 是否保护所有资源，true，则所有资源默认为受保护， false则只有声明了并且与权限挂钩了的资源才会受保护 
	 * @return
	 */
	public static ConfigAttributeDefinition getCadByAuthorities(Collection<GrantedAuthority> authorities, boolean isProtectAllResource ){
		
		if (authorities ==null || authorities.size() == 0){
			if(isProtectAllResource){
				return new ConfigAttributeDefinition();
			}else{
				return null;
			}
		}
		
		ConfigAttributeEditor configAttrEditor = new ConfigAttributeEditor();
		String authoritiesStr = " ";

		for (Iterator iter = authorities.iterator(); iter.hasNext();) {
			GrantedAuthority authority = (GrantedAuthority) iter.next();
			authoritiesStr += authority.getAuthority() + ",";
		}

		String authStr = authoritiesStr.substring(0, authoritiesStr.length() - 1);
		configAttrEditor.setAsText(authStr);

		return (ConfigAttributeDefinition)configAttrEditor.getValue();
	}
	
}
