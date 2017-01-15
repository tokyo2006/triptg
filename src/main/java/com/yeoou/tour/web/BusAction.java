package com.yeoou.tour.web;

import java.util.List;

import com.yeoou.common.context.Global;
import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.DateUtils;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.rbac.model.User;
import com.yeoou.rbac.service.IUserService;
import com.yeoou.tour.model.Bus;
import com.yeoou.tour.model.Manager;
import com.yeoou.tour.service.IBusService;
/**
 * <p>
 * Title: 后台汽车票业务操作模块
 * </p>
 * <p>
 * Description: 对汽车票进行相关业务操作
 * 新增汽车票信息，修改汽车票信息，删除汽车票信息
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class BusAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private IUserService userService;
	private IBusService busService;
	private String delData;
	private String busId;
	private User user;
	private String name="";
	private String number="";
	private String begin="";
	private String destination="";
	private String company="";
	private String beginDateStr="";
	private String begintime="";
	private int price=0;
	private int total=0;
    private String jsonString;
	private String sort;
	private String dir;
	private int start;
	private int limit;
	private int busType;
	
	public String addBus()
	{
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		user = userService.getUserById(manager.getBossId());
		Bus bus = new Bus();
		bus.setBegin(begin);
		bus.setBeginDateStr(beginDateStr);
		bus.setBeginDate(DateUtils.parseDate(beginDateStr));
		bus.setBegintime(begintime);
		bus.setBooked(0);
		bus.setBusType(busType);
		bus.setCompany(company);
		bus.setConfirm(0);
		bus.setDestination(destination);
		bus.setName(name);
		bus.setNumber(number);
		bus.setPrice(price);
		bus.setTotal(total);
		bus.setUser(user);
		busService.save(bus);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	@SuppressWarnings("unchecked")
	public String getAllBus()
	{		
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		String userId=manager.getBossId();
		Page page = new Page();
		page = busService.getAllBus(userId, dir, sort, limit, start, name);
        if(page!=null)
        {
        	this.setTotalCount(page.getTotalCount());
        	List<Bus> busList = (List<Bus>)page.getResult();
        	int length = busList.size();
        	for(int i=0;i<length;i++)
        	{
        		int remain = 0;
        		if(busList.get(i).getTotal()-busList.get(i).getConfirm()>0)
        		 remain= busList.get(i).getTotal()-busList.get(i).getConfirm();
        		busList.get(i).setRemain(remain);
        		if(remain==0)
        		{
        			busList.get(i).setStatus(Global.SALE_OVER);
        		}
        		else
        		{
        			busList.get(i).setStatus(Global.SALE_BOOK);
        		}
        	}
        	FlexJsonUtils jsonTool = new FlexJsonUtils();
        	this.jsonString = "{success:true,totalCount:"+this.getTotalCount()+",results:"+jsonTool.getJsonString(busList, null, null, null)+"}";
        }
        else
        {
        	jsonString="{success:true,totalCount:0,results:[]}";
        }
		return SUCCESS;
	}
	public String getSingleBus()
    {
		
    	Bus bus = new Bus();
    	bus = (Bus)busService.get(busId);
    	FlexJsonUtils jsonTool = new FlexJsonUtils();
    	jsonString = "{success:true,totalCount:1,results:["+jsonTool.getJsonString(bus, null, new String[]{"class","user"}, null)+"]}";
    	return SUCCESS;
    }
    public String updateBus()
    {
    	Bus bus = new Bus();
    	bus = (Bus)busService.get(busId);
    	bus.setBegin(begin);
    	bus.setBeginDateStr(beginDateStr);
		bus.setBeginDate(DateUtils.parseDate(beginDateStr));
		bus.setBegintime(begintime);
		bus.setCompany(company);
		bus.setConfirm(0);
		bus.setBusType(busType);
		bus.setDestination(destination);
		bus.setName(name);
		bus.setNumber(number);
		bus.setPrice(price);
		bus.setTotal(total);
		busService.merge(bus);
		jsonString = "{success:true}";
    	return SUCCESS;
    }
    public String delBus()
	{
		if(delData!=null)
			{
				if(this.delData.indexOf(",")<0)
				{
					int result = busService.countTeamByBus(delData.trim());
					if(result != 0)
					{
						jsonString="{success:false,msg:'此票务关联有效团队，无法删除!'}";
					}
					else
					{
						busService.removeById(delData.trim());
						jsonString = "{success:true}";
					}
				}
				else
				{
					String[] id= this.delData.split(",");
					int result = 0;
					for(int i=0;i<id.length;i++)
					{
						result = busService.countTeamByBus(id[i].trim());
						if(result != 0)
						{
							jsonString="{success:false,msg:'此票务关联有效团队，无法删除!'}";
						}
						else
						{
							busService.removeById(id[i].trim());
							jsonString = "{success:true}";
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
	public String getBusId()
	{
		return busId;
	}
	public void setBusId(String busId)
	{
		this.busId = busId;
	}
	public IBusService getBusService()
	{
		return busService;
	}
	public void setBusService(IBusService busService)
	{
		this.busService = busService;
	}
	public int getBusType()
	{
		return busType;
	}
	public void setBusType(int busType)
	{
		this.busType = busType;
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
