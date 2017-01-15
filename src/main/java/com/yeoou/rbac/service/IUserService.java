package com.yeoou.rbac.service;




import com.yeoou.common.dao.EntityDao;
import com.yeoou.rbac.model.User;
import com.yeoou.tour.model.Corporation;
import com.yeoou.tour.model.Manager;


public interface IUserService extends EntityDao
{
	public User getUserById(String userId);
	public void addManager(Manager manager,User user);
//	public void addShopper(Shopper shopper,User user);
	public void delManager(Manager manager,User user);
//	public void delShopper(Shopper shopper,User user);
	public void updateManager(Manager manager,User user);
//	public void updateShopper(Shopper shopper,User user);
	public User getUserByName(String name);
	public Manager getManagerByUserId(String userId);
	public Corporation getCorpByUserId(String userId);
	public void mergeUser(User user);
	public void updateUser(User user);
	public void addUser(User user);
	public void delUser(User user);
	public boolean isUnique(User user);
}
