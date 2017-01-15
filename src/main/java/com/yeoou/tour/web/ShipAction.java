package com.yeoou.tour.web;

import java.util.List;
import com.yeoou.common.context.Global;
import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.DateUtils;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.rbac.model.User;
import com.yeoou.rbac.service.IUserService;
import com.yeoou.tour.model.Manager;
import com.yeoou.tour.model.Ship;
import com.yeoou.tour.service.IShipService;

/**
 * <p>
 * Title: 后台船票业务操作模块
 * </p>
 * <p>
 * Description: 对船票进行相关业务操作
 * 新增船票信息，修改船票信息，删除船票信息
 * @see AirAction
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class ShipAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private IShipService shipService;
	private IUserService userService;
	private String delData;
	private String shipId;
	private User user;
	private String name="";
	private String number="";
	private String begin="";
	private String company="";
	private String destination="";
	private String beginDateStr="";
	private String begintime="";
	private int price=0;
	private int total=0;
    private String jsonString;
	private String sort;
	private String dir;
	private int start;
	private int limit;
	public String addShip()
	{
//		need acegi support userId;
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		user = userService.getUserById(manager.getBossId());
		Ship ship = new Ship();
		ship.setBegin(begin);
		ship.setBeginDateStr(beginDateStr);
		ship.setBeginDate(DateUtils.parseDate(beginDateStr));
		ship.setBegintime(begintime);
		ship.setBooked(0);
		ship.setCompany(company);
		ship.setConfirm(0);
		ship.setDestination(destination);
		ship.setName(name);
		ship.setNumber(number);
		ship.setPrice(price);
		ship.setTotal(total);
		ship.setUser(user);
		shipService.save(ship);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	public String getAllShip()
	{		
//		need acegi support userId;
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		String userId = manager.getBossId();
		Page page = new Page();
		page = shipService.getAllShip(userId, dir, sort, limit, start, name);
        if(page!=null)
        {
        	this.setTotalCount(page.getTotalCount());
        	List<Ship> shipList = (List<Ship>)page.getResult();
        	int length = shipList.size();
        	for(int i=0;i<length;i++)
        	{
        		int remain = 0;
        		if(shipList.get(i).getTotal()-shipList.get(i).getConfirm()>0)
        		 remain= shipList.get(i).getTotal()-shipList.get(i).getConfirm();
        		shipList.get(i).setRemain(remain);
        		if(remain==0)
        		{
        			shipList.get(i).setStatus(Global.SALE_OVER);
        		}
        		else
        		{
        			shipList.get(i).setStatus(Global.SALE_BOOK);
        		}
        	}
        	FlexJsonUtils jsonTool = new FlexJsonUtils();
        	this.jsonString = "{success:true,totalCount:"+this.getTotalCount()+",results:"+jsonTool.getJsonString(shipList, null, new String[]{"class","user"}, null)+"}";
        }
        else
        {
        	jsonString="{success:true,totalCount:0,results:[]}";
        }
		return SUCCESS;
	}
	public String getSingleShip()
    {
    	Ship ship = new Ship();
    	ship = (Ship)shipService.get(shipId);
    	FlexJsonUtils jsonTool = new FlexJsonUtils();
    	jsonString = "{success:true,totalCount:1,results:["+jsonTool.getJsonString(ship, null, new String[]{"class","user"}, null)+"]}";
    	return SUCCESS;
    }
	public String updateShip()
    {
    	Ship ship = new Ship();
    	ship = (Ship)shipService.get(shipId);
    	ship.setBegin(begin);
    	ship.setBeginDateStr(beginDateStr);
		ship.setBeginDate(DateUtils.parseDate(beginDateStr));
		ship.setBegintime(begintime);
		ship.setBooked(0);
		ship.setCompany(company);
		ship.setConfirm(0);
		ship.setDestination(destination);
		ship.setName(name);
		ship.setNumber(number);
		ship.setPrice(price);
		ship.setTotal(total);
		shipService.merge(ship);
		jsonString = "{success:true}";
    	return SUCCESS;
    }
	public String delShip()
	{
		if(delData!=null)
			{
				if(this.delData.indexOf(",")<0)
				{
					int result = shipService.countTeamByShip(delData.trim());
					if(result==0)
					{
						shipService.removeById(delData.trim());
						jsonString="{success:true}";
					}
					else
					{
						jsonString="{success:false,msg:'此票务关联有效团队，无法删除!'}";
					}
				}
				else
				{
					String[] id= this.delData.split(",");
					for(int j=0;j<id.length;j++)
					{
						int result = shipService.countTeamByShip(id[j].trim());
						if(result==0)
						{
							shipService.removeById(id[j].trim());
							jsonString="{success:true}";
						}
						else
						{
							jsonString="{success:false,msg:'此票务关联有效团队，无法删除!'}";
						}
					}
				}
				
			}
		return SUCCESS;
	}
	public String getBegin()
	{
		return begin;
	}
	public void setBegin(String begin)
	{
		this.begin = begin;
	}
	public String getBeginDateStr()
	{
		return beginDateStr;
	}
	public void setBeginDateStr(String beginDateStr)
	{
		this.beginDateStr = beginDateStr;
	}
	public String getBegintime()
	{
		return begintime;
	}
	public void setBegintime(String begintime)
	{
		this.begintime = begintime;
	}
	public String getCompany()
	{
		return company;
	}
	public void setCompany(String company)
	{
		this.company = company;
	}
	public String getDelData()
	{
		return delData;
	}
	public void setDelData(String delData)
	{
		this.delData = delData;
	}
	public String getDestination()
	{
		return destination;
	}
	public void setDestination(String destination)
	{
		this.destination = destination;
	}
	public String getDir()
	{
		return dir;
	}
	public void setDir(String dir)
	{
		this.dir = dir;
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
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getNumber()
	{
		return number;
	}
	public void setNumber(String number)
	{
		this.number = number;
	}
	public int getPrice()
	{
		return price;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
	public String getShipId()
	{
		return shipId;
	}
	public void setShipId(String shipId)
	{
		this.shipId = shipId;
	}
	public IShipService getShipService()
	{
		return shipService;
	}
	public void setShipService(IShipService shipService)
	{
		this.shipService = shipService;
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
	public int getTotal()
	{
		return total;
	}
	public void setTotal(int total)
	{
		this.total = total;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public IUserService getUserService()
	{
		return userService;
	}
	public void setUserService(IUserService userService)
	{
		this.userService = userService;
	}
	
}
