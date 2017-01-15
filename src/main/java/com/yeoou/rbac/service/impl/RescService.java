package com.yeoou.rbac.service.impl;

import org.acegisecurity.GrantedAuthority;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.components.acegi.AuthenticationHelper;
import com.yeoou.components.acegi.cache.AcegiCacheManager;
import com.yeoou.components.acegi.resource.ResourceDetails;
import com.yeoou.rbac.model.Resource;
import com.yeoou.rbac.service.IRescService;

public class RescService extends HibernateEntityDao<Resource> implements IRescService
{
	
	private AcegiCacheManager acegiCacheManager;
	
	public AcegiCacheManager getAcegiCacheManager()
	{
		return acegiCacheManager;
	}

	public void setAcegiCacheManager(AcegiCacheManager acegiCacheManager)
	{
		this.acegiCacheManager = acegiCacheManager;
	}
    
	public void delResc(Resource resc)
	{
		this.removeRescInCache(resc.getRescString());
		this.remove(resc);
		
	}
	public void addResc(Resource resc)
	{
		this.save(resc);
		this.saveRescInCache(resc);
	}
	public void updateResc(Resource resc)
	{
		this.merge(resc);
		this.removeRescInCache(resc.getRescString());
		this.saveRescInCache(resc);
	}
	private void saveRescInCache(Resource resc){
		GrantedAuthority[] authorities = AuthenticationHelper.convertToGrantedAuthority(resc.getRoles(), "name");
		ResourceDetails rd = new com.yeoou.components.acegi.resource.Resource(resc.getRescString(),resc.getRescType(),authorities);
		acegiCacheManager.addResource(rd);
	} 
	
	private void removeRescInCache(String resString){
		acegiCacheManager.removeResource(resString);
	}
	
}
