package com.yeoou.rbac.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.acegisecurity.GrantedAuthority;

import com.yeoou.common.dao.HibernateEntityDao;
import com.yeoou.components.acegi.AuthenticationHelper;
import com.yeoou.components.acegi.cache.AcegiCacheManager;
import com.yeoou.rbac.model.User;
import com.yeoou.rbac.service.IUserService;
import com.yeoou.tour.model.Corporation;
import com.yeoou.tour.model.Manager;
import com.yeoou.tour.service.IManagerService;

public class UserService extends HibernateEntityDao<User> implements IUserService
{
	private AcegiCacheManager acegiCacheManager;
	private IManagerService managerService;
//	private IShopperService shopperService;
	private void saveUserInCache(User user){
		GrantedAuthority[] authorities = AuthenticationHelper.convertToGrantedAuthority(user.getRoles(), "name");
		boolean enabled;
		if(user.getValid()==1)enabled=true;else enabled=false;
		acegiCacheManager.addUser(user.getLoginName(), user.getPassword(), enabled, true, true, true, authorities);
	}
	
	public void removeUserInCache(String username){
		acegiCacheManager.removeUser(username);
	}
	public AcegiCacheManager getAcegiCacheManager()
	{
		return acegiCacheManager;
	}
	public void setAcegiCacheManager(AcegiCacheManager acegiCacheManager)
	{
		this.acegiCacheManager = acegiCacheManager;
	}
	public void addUser(User user)
	{
		this.save(user);
		this.saveUserInCache(user);
	}
	public void updateUser(User user)
	{
		this.update(user);
		removeUserInCache(user.getLoginName());
		saveUserInCache(user);
	}
	public void delUser(User user)
	{
		removeUserInCache(user.getLoginName());
		this.remove(user);
	}
	public void mergeUser(User user)
	{
		this.merge(user);
		removeUserInCache(user.getLoginName());
		saveUserInCache(user);
	}
	public boolean isUnique(User user)
	{
		return this.isUnique(user, "loginName");	
	}

	@SuppressWarnings("unchecked")
	public User getUserById(String userId)
	{
		// TODO Auto-generated method stub
		String hql="from User as user left outer join fetch user.roles where user.userId=?";
		List<User> user = this.find(hql, new Object[]{userId});
		if(user.size()!=0)
		{
			return user.get(0);
		}
		else return null;
	}

	@SuppressWarnings("unchecked")
	public User getUserByName(String name)
	{
		// TODO Auto-generated method stub
		String hql="from User as user left outer join fetch user.roles where user.loginName=?";
		List<User> user = this.find(hql, new Object[]{name});
		if(user.size()!=0)
		{
			return user.get(0);
		}
		else return null;
	}
	public void addManager(Manager manager, User user)
	{
		// TODO Auto-generated method stub
		this.addUser(user);
		manager.setUserId(this.getUserByName(user.getLoginName()).getUserId());
		managerService.save(manager);
	}
//
//	public void addShopper(Shopper shopper, User user)
//	{
//		// TODO Auto-generated method stub
//		this.addUser(user);
//		shopper.setUserId(this.getUserByName(user.getLoginName()).getUserId());
//		shopperService.save(shopper);
//	}
//
	public IManagerService getManagerService()
	{
		return managerService;
	}

	public void setManagerService(IManagerService managerService)
	{
		this.managerService = managerService;
	}
//
//	public IShopperService getShopperService()
//	{
//		return shopperService;
//	}
//
//	public void setShopperService(IShopperService shopperService)
//	{
//		this.shopperService = shopperService;
//	}
//
	public void delManager(Manager manager, User user)
	{
		// TODO Auto-generated method stub
		managerService.remove(manager);
		this.delUser(user);
	}
//
//	public void delShopper(Shopper shopper, User user)
//	{
//		// TODO Auto-generated method stub
//		shopperService.remove(shopper);
//		this.delUser(user);
//	}
//
	public void updateManager(Manager manager, User user)
	{
		// TODO Auto-generated method stub
		managerService.merge(manager);
		this.mergeUser(user);
	}
//
//	public void updateShopper(Shopper shopper, User user)
//	{
//		// TODO Auto-generated method stub
//		shopperService.update(shopper);
//		this.update(user);
//	}

	public Manager getManagerByUserId(String userId)
	{
		// TODO Auto-generated method stub
		String hql="from Manager as manager where manager.userId=?";
		List<Manager> manager = new ArrayList<Manager>();
		manager = this.find(hql, new Object[]{userId});
		if(manager.size()!=0) return manager.get(0);
		else return null;
	}

	public Corporation getCorpByUserId(String userId)
	{
		// TODO Auto-generated method stub
		String hql="select com from Corporation as com left outer join fetch com.areas where com.userId=?";
		List<Corporation> corp = new ArrayList<Corporation>();
		corp = this.find(hql, new Object[]{userId});
		if(corp.size()!=0) return corp.get(0);
		else return null;
	}
}
