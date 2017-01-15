package com.yeoou.tour.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;

import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.DateUtils;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.rbac.model.Group;
import com.yeoou.rbac.model.Role;
import com.yeoou.rbac.model.User;
import com.yeoou.rbac.service.IGroupService;
import com.yeoou.rbac.service.IRoleService;
import com.yeoou.rbac.service.IUserService;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.Corporation;
import com.yeoou.tour.model.Manager;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.IManagerService;
/**
 * <p>
 * Title: 后台管理员操作模块
 * </p>
 * <p>
 * Description: 添加管理员，删除管理员，更新管理员操作，修改自身密码
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class ManagerAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private IManagerService managerService;
	private IUserService userService;
	private IRoleService roleService;
	private IGroupService groupService;
	private IAreaService areaService;
	private String jsonString = "";
	private String reroleList = "";
	private String bossId = "";
	private String name = "";
	private String dir = "";
	private String sort = "";
	private int limit = 15;
	private int start = 0;
	private String userName = "";
	private String areaId = "";
	private String email = "";
	private String idCard = "";
	private String mobile = "";
	private String msn = "";
	private String phone = "";
	private String qq = "";
	private String password = "";
	private String repassword = "";
	private String valid = "";
	private String managerId = "";
	private String oldPassword = "";
	private String delData = "";
	/**
	 * 获取公司所经验范围的地域列表
	 * @return
	 */
	public String getAreaByCorpId()
	{
		Corporation corp = new Corporation();
		corp = this.getCorporationInfoByManager();
		if(corp!=null)
		{
			List<Area> areaList = new ArrayList<Area>(corp.getAreas());
			if(areaList.size()!=0)
			{
				StringBuilder sb = new StringBuilder("[");
				for(int i=0;i<areaList.size()-1;i++)
				{
					sb.append("[");
					sb.append("\'");
					sb.append(areaList.get(i).getAreaId());
					sb.append("\'");
					sb.append(",");
					sb.append("\'");
					sb.append(areaList.get(i).getName());
					sb.append("\'");
					sb.append("]");
					sb.append(",");
				}
				sb.append("[");
				sb.append("\'");
				sb.append(areaList.get(areaList.size()-1).getAreaId());
				sb.append("\'");
				sb.append(",");
				sb.append("\'");
				sb.append(areaList.get(areaList.size()-1).getName());
				sb.append("\'");
				sb.append("]");
				sb.append("]");
				jsonString = sb.toString();
			}
			else
			{
				jsonString = "[[]]";
			}
		}
		else
		{
			jsonString = "[[]]";
		}
		return SUCCESS;
	}
	/**
	 * 获取公司管理人员列表（不包括超级管理员）
	 * @return
	 */
	public String getAllManagerByBossId()
	{
		Page page = new Page();
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		if(manager!=null)
		{
			bossId = manager.getBossId();
		}
		page = managerService.getAllManagerByBoss(bossId, name, start, limit, sort, dir);
		if(page!=null)
		{
			
			StringBuilder sb = new StringBuilder("{success:true,totalCount:");
			FlexJsonUtils jsonTool = new FlexJsonUtils();
			List<Manager> managerList = new ArrayList<Manager>();
			managerList = (List<Manager>)page.getResult();
			int length = managerList.size();
			for(int i=0;i<length;i++)
			{
				
				managerList.get(i).setAreaName(((Area)areaService.get(managerList.get(i).getAreaId())).getName());
				
			}
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(managerList, null, new String[]{"class"}, null));
			sb.append("}");
			jsonString = sb.toString();
		}
		else
		{
			jsonString="{success:true,totalCount:0,results:[]}";
		}
		return SUCCESS;
	}
	/**
	 * 获取权限列表
	 * @return
	 */
	public String getRoles()
	{
		Group group = groupService.getGroupById("402881ff1f9242ed011f925eb0a5000e");
		List<Role> roleList = new ArrayList<Role>(group.getRoles());
		StringBuilder sb = new StringBuilder();
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		sb.append("{success:true,roleList:");
		sb.append(jsonTool.getJsonString(roleList, null, new String[]{"class"}, null));
		sb.append("}");
		jsonString = sb.toString();
		return  SUCCESS;
	}
	/**
	 * 获取单一管理员信息
	 * @return
	 */
	public String getSingleManager()
	{
		Manager manager = new Manager();
//		manager = this.getManagerInfo();
		manager = (Manager)managerService.get(managerId);
		User user = new User();
		user = userService.getUserById(manager.getUserId());
		Area area = new Area();
		area = (Area)areaService.get(manager.getAreaId());
		manager.setAreaName(area.getName());
		Group group = groupService.getGroupById("402881ff1f9242ed011f925eb0a5000e");
		List<Role> roleList = new ArrayList<Role>(group.getRoles());
		StringBuilder sb = new StringBuilder();
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		sb.append("{success:true,manager:");
		sb.append(jsonTool.getJsonString(manager, null, null, null));
		sb.append(",roleList:");
		sb.append(jsonTool.getJsonString(roleList, null, new String[]{"class"}, null));
		sb.append(",user:");
		sb.append(jsonTool.getJsonString(user, new String[]{"roles"}, new String[]{"class","loginName","password"}, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/*
	 * 获取自身管理员信息
	 */
	public String getSelfManagerInfo()
	{
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		StringBuilder sb = new StringBuilder();
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		sb.append("{success:true,jobber:");
		sb.append(jsonTool.getJsonString(manager, null, null, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 更新自身管理员信息（修改密码）
	 * @return
	 */
	public String updateSelfInfo()
	{
		User user = new User();
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		user = userService.getUserById(manager.getUserId());
		
		if(this.password.equals(this.repassword))
		{
			Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
            user.setPassword(passwordEncoder.encodePassword(password.trim(), null));
            userService.mergeUser(user);
            jsonString="{success:true}";
		}
		else
		{
			jsonString = "{success:false,msg:'两次输入密码不同，请重新输入'}";
		}
		return SUCCESS;
	}
	/**
	 * 添加管理员信息
	 * @return
	 */
	public String addManager()
	{
		User user = new User();
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		String bossId = manager.getBossId();
		Group group = groupService.getGroupById("402881ff1f9242ed011f925f2f2c000f");
		List<Group> groups = new ArrayList<Group>();
		groups.add(group);
		List<Role> roleList = new ArrayList<Role>(group.getRoles());
//		需要添加的角色
		if(reroleList.indexOf(",")<0)
		{
			roleList.add(roleService.getRoleById(reroleList.trim()));
		}
		else
		{
			String[] roleIds = this.reroleList.split(",");
			for(int i=0;i<roleIds.length;i++)
			{
				roleList.add(roleService.getRoleById(roleIds[i].trim()));
			}
		}
		if(userService.getUserByName(userName)==null)
		{
			if(this.password.equals(this.repassword))
			{
				user.setLoginName(userName.trim());
				Date date=new Date();
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				String time=df.format(date);
				user.setCreateTime(DateUtils.parseDate(time));
				if(valid.equals("1"))
				{user.setValid(1);}
				else
				{
					user.setValid(0);
				}
				Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
	            user.setPassword(passwordEncoder.encodePassword(password.trim(), null));
	            user.setGroups(new HashSet<Group>(groups));
	            user.setRoles(new HashSet<Role>(roleList));
	            Manager newManager = new Manager();
	            newManager.setAreaId(areaId);
	            newManager.setBossId(bossId);
	            newManager.setEmail(email);
	            newManager.setIdCard(idCard);
	            newManager.setMobile(mobile);
	            newManager.setMsn(msn);
	            newManager.setName(name);
	            newManager.setPhone(phone);
	            newManager.setQq(qq);
	            userService.addManager(newManager, user);
	            jsonString="{success:true}";
			}
			else
			{
				jsonString = "{success:false,msg:'两次输入密码不同，请重新输入'}";
			}
		}
		else
		{
			jsonString = "{success:false,msg:'用户名重复请重新输入'}";
		}
		return SUCCESS;
	}
	/**
	 * 更新管理员信息
	 * @return
	 */
	public String updateManager()
	{
		Manager manager = new Manager();
		manager = (Manager)managerService.get(managerId);
		User user = userService.getUserById(manager.getUserId());
		Group group = groupService.getGroupById("402881ff1f9242ed011f925f2f2c000f");
		List<Role> roleList = new ArrayList<Role>(group.getRoles());
		if(reroleList.indexOf(",")<0)
		{
			roleList.add(roleService.getRoleById(reroleList.trim()));
		}
		else
		{
			String[] roleIds = this.reroleList.split(",");
			for(int i=0;i<roleIds.length;i++)
			{
				roleList.add(roleService.getRoleById(roleIds[i].trim()));
			}
		}
		user.setRoles(new HashSet<Role>(roleList));
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		if(!password.trim().equals(""))
        {
			user.setPassword(passwordEncoder.encodePassword(password.trim(), null));
		}
		if(valid!=null&&valid.equals("1"))
		{
			user.setValid(1);
		}
		else
		{
			user.setValid(0);
		}
		manager.setEmail(email);
		manager.setAreaId(areaId);
		manager.setIdCard(idCard);
		manager.setMobile(mobile);
		manager.setMsn(msn);
		manager.setName(name);
		manager.setPhone(phone);
		manager.setQq(qq);
		userService.updateManager(manager, user);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 删除管理员操作
	 * @return
	 */
	public String delManager()
	{
		if(!delData.equals(""))
		{
			if(delData.indexOf(",")<0)
			{
				Manager manager = new Manager();
				User  user = new User();
				manager = (Manager)managerService.get(delData.trim());
				user = userService.getUserById(manager.getUserId());
				userService.delManager(manager, user);
				
			}
			else
			{
				String id[]=delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					Manager manager = new Manager();
					User  user = new User();
					manager = (Manager)managerService.get(id[i].trim());
					user = userService.getUserById(manager.getUserId());
					userService.delManager(manager, user);
				}
			}
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 修改自身密码
	 * @return
	 */
	public String changePassword()
	{
		User user = userService.getUserByName(this.getGlobalUserName());
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		oldPassword = passwordEncoder.encodePassword(oldPassword, null);
		if(oldPassword.equals(user.getPassword()))
		{
			if(password!=null&&!password.equals(""))
			{
				if(password.equals(repassword))
				{
					user.setPassword(passwordEncoder.encodePassword(password, null));
					userService.mergeUser(user);
				}
				else
				{
					jsonString="{success:false,msg:'两次输入密码不同，请重新输入'}";
				}
			}
			else
			{
				jsonString="{success:false,msg:'密码不能为空，请输入密码'}";
			}
			
		}
		else
		{
			jsonString="{success:false,msg:'旧密码输入错误，请重新输入'}";
		}
		jsonString ="{success:true}";
		return SUCCESS;
	}
	public IGroupService getGroupService()
	{
		return groupService;
	}
	public void setGroupService(IGroupService groupService)
	{
		this.groupService = groupService;
	}
	public IManagerService getManagerService()
	{
		return managerService;
	}
	public void setManagerService(IManagerService managerService)
	{
		this.managerService = managerService;
	}
	public IRoleService getRoleService()
	{
		return roleService;
	}
	public void setRoleService(IRoleService roleService)
	{
		this.roleService = roleService;
	}
	public IUserService getUserService()
	{
		return userService;
	}
	public void setUserService(IUserService userService)
	{
		this.userService = userService;
	}
	public String getAreaId()
	{
		return areaId;
	}
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}
	public IAreaService getAreaService()
	{
		return areaService;
	}
	public void setAreaService(IAreaService areaService)
	{
		this.areaService = areaService;
	}
	public String getBossId()
	{
		return bossId;
	}
	public void setBossId(String bossId)
	{
		this.bossId = bossId;
	}
	public String getDelData()
	{
		return delData;
	}
	public void setDelData(String delData)
	{
		this.delData = delData;
	}
	public String getDir()
	{
		return dir;
	}
	public void setDir(String dir)
	{
		this.dir = dir;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getIdCard()
	{
		return idCard;
	}
	public void setIdCard(String idCard)
	{
		this.idCard = idCard;
	}
	public String getJsonString()
	{
		return jsonString;
	}
	public void setJsonString(String jsonString)
	{
		this.jsonString = jsonString;
	}
	public int getLimit()
	{
		return limit;
	}
	public void setLimit(int limit)
	{
		this.limit = limit;
	}
	public String getManagerId()
	{
		return managerId;
	}
	public void setManagerId(String managerId)
	{
		this.managerId = managerId;
	}
	public String getMobile()
	{
		return mobile;
	}
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	public String getMsn()
	{
		return msn;
	}
	public void setMsn(String msn)
	{
		this.msn = msn;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getOldPassword()
	{
		return oldPassword;
	}
	public void setOldPassword(String oldPassword)
	{
		this.oldPassword = oldPassword;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getQq()
	{
		return qq;
	}
	public void setQq(String qq)
	{
		this.qq = qq;
	}
	public String getRepassword()
	{
		return repassword;
	}
	public void setRepassword(String repassword)
	{
		this.repassword = repassword;
	}
	public String getReroleList()
	{
		return reroleList;
	}
	public void setReroleList(String reroleList)
	{
		this.reroleList = reroleList;
	}
	public String getSort()
	{
		return sort;
	}
	public void setSort(String sort)
	{
		this.sort = sort;
	}
	public int getStart()
	{
		return start;
	}
	public void setStart(int start)
	{
		this.start = start;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getValid()
	{
		return valid;
	}
	public void setValid(String valid)
	{
		this.valid = valid;
	}
}
