package com.yeoou.rbac.service.impl;

import java.util.Iterator;
import java.util.List;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.lang.ArrayUtils;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.components.acegi.cache.AcegiCacheManager;
import com.yeoou.components.acegi.resource.ResourceDetails;
import com.yeoou.rbac.model.Role;
import com.yeoou.rbac.service.IRoleService;

public class RoleService extends HibernateEntityDao<Role> implements IRoleService
{
	private AcegiCacheManager acegiCacheManager;
	
	public void delRole(Role role)
	{
		removeAuthoritiesInCache(role.getName());
		this.remove(role);
	}
	public Role getRoleById(String roleId)
	{
		return (Role)this.get(roleId);
	}
	private void removeAuthoritiesInCache(String authority){
		GrantedAuthorityImpl auth = new GrantedAuthorityImpl(authority);
		List<String> rescs = acegiCacheManager.getAllResources();
		for (Iterator iter = rescs.iterator(); iter.hasNext();) {
			String str = (String) iter.next();
			ResourceDetails resc = acegiCacheManager.getResourceFromCache(str);
			GrantedAuthority[] auths = resc.getAuthorities();
			int idx = ArrayUtils.indexOf(auths, auth);
			if(idx>=0){
				auths = (GrantedAuthority[])ArrayUtils.remove(auths, idx);
				resc.setAuthorities(auths);
			}
		}
		
		List<String> users = acegiCacheManager.getAllUsers();
		for (Iterator iter = users.iterator(); iter.hasNext();) {
			String username = (String) iter.next();
			UserDetails user = acegiCacheManager.getUser(username);
			GrantedAuthority[] auths = user.getAuthorities();
			int idx = ArrayUtils.indexOf(auths, auth);
			if(idx>=0){
				auths = (GrantedAuthority[])ArrayUtils.remove(auths, idx);
				user = new org.acegisecurity.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true,
						auths);
				acegiCacheManager.addUser(user);
			}
		}
	}
	public AcegiCacheManager getAcegiCacheManager()
	{
		return acegiCacheManager;
	}
	public void setAcegiCacheManager(AcegiCacheManager acegiCacheManager)
	{
		this.acegiCacheManager = acegiCacheManager;
	}

}
