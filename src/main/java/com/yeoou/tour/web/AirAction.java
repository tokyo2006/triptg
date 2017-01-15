package com.yeoou.tour.web;

import java.util.List;
import com.yeoou.common.context.Global;
import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.DateUtils;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.rbac.model.User;
import com.yeoou.rbac.service.IUserService;
import com.yeoou.tour.model.Air;
import com.yeoou.tour.service.IAirService;
/**
 * <p>
 * Title: 后台机票业务操作模块
 * </p>
 * <p>
 * Description: 对机票进行相关业务操作
 * 新增机票信息，修改机票信息，删除机票信息
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class AirAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private IAirService airService;
	private IUserService userService;
	private String delData;
	private String airId;
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
	private int start = 0;
	private int limit = 15;
	/**
	 * 添加机票信息
	 * @return
	 */
	public String addAir()
	{
		user = userService.getUserByName(this.getGlobalUserName());
		Air air = new Air();
		air.setBegin(begin);
		air.setBeginDateStr(beginDateStr);
		air.setBeginDate(DateUtils.parseDate(beginDateStr));
		air.setBegintime(begintime);
		air.setBooked(0);
		air.setCompany(company);
		air.setConfirm(0);
		air.setDestination(destination);
		air.setName(name);
		air.setNumber(number);
		air.setPrice(price);
		air.setTotal(total);
		air.setUser(user);
		airService.save(air);
		jsonString = "{success:true}";
		return NONE;
	}
	/**
	 * 删除机票信息（如果有团队关联机票则无法删除该机票信息）
	 * @return
	 */
	public String delAir()
	{
		if(delData!=null)
			{
				if(this.delData.indexOf(",")<0)
				{
					int result = airService.countTeamByAir(delData.trim());
					if(result==0)
					{
						airService.removeById(delData.trim());
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
						int result = airService.countTeamByAir(id[j].trim());
						if(result==0)
						{
							airService.removeById(id[j].trim());
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
	/**
	 * 后台显示所有用户所属机票信息
	 * @return
	 */
	public String getAllAir()
	{		
//		need acegi support userId;
		Page page = new Page();
		String userId = userService.getUserByName(this.getGlobalUserName()).getUserId();
		page = airService.getAllAir(userId, dir, sort, limit, start, name);
		if(page!=null)
        {	
			List<Air> airList = (List<Air>)page.getResult();
        	int length = airList.size();
        	for(int i=0;i<length;i++)
        	{
        		int remain = 0;
        		if(airList.get(i).getTotal()-airList.get(i).getConfirm()>0)
        		 remain= airList.get(i).getTotal()-airList.get(i).getConfirm();
        		airList.get(i).setRemain(remain);
        		if(remain==0)
        		{
        			airList.get(i).setStatus(Global.SALE_OVER);
        		}
        		else
        		{
        			airList.get(i).setStatus(Global.SALE_BOOK);
        		}
        	}
        	FlexJsonUtils jsonTool = new FlexJsonUtils();
        	this.jsonString = "{success:true,totalCount:"+page.getTotalCount()+",results:"+jsonTool.getJsonString(airList, null, new String[]{"class","user"}, null)+"}";
        }
        else
        {
        	jsonString="{success:true,totalCount:0,results:[]}";
        }
		return SUCCESS;
	}
	/**
	 * 获取单一机票信息
	 * @return
	 */
    public String getSingleAir()
    {
    	Air air = new Air();
    	air = (Air)airService.get(airId);
    	FlexJsonUtils jsonTool = new FlexJsonUtils();
    	jsonString = "{success:true,totalCount:1,results:["+jsonTool.getJsonString(air, null, new String[]{"class","user"}, null)+"]}";
    	return SUCCESS;
    }
    /**
     * 更新机票信息
     * @return
     */
    public String updateAir()
    {
    	Air air = new Air();
    	air = (Air)airService.get(airId);
    	air.setBegin(begin);
		air.setBeginDateStr(beginDateStr);
		air.setBeginDate(DateUtils.parseDate(beginDateStr));
		air.setBegintime(begintime);
		airService.merge(air);
		jsonString = "{success:true}";
    	return NONE;
    }
	public String getAirId()
	{
		return airId;
	}
	public void setAirId(String airId)
	{
		this.airId = airId;
	}
	public IAirService getAirService()
	{
		return airService;
	}
	public void setAirService(IAirService airService)
	{
		this.airService = airService;
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
