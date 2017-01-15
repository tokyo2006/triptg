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
import com.yeoou.tour.model.Train;
import com.yeoou.tour.service.ITrainService;
/**
 * <p>
 * Title: 后台火车票业务操作模块
 * </p>
 * <p>
 * Description: 对火车票进行相关业务操作
 * 新增火车票信息，修改火车票信息，删除火车票信息
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class TrainAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private ITrainService trainService;
	private IUserService userService;
	private String delData;
	private String trainId;
	private User user;
	private String name="";
	private String number="";
	private String begin="";
	private String destination="";
	private String beginDateStr="";
	private String begintime="";
	private int price=0;
	private int total=0;
    private String jsonString;
	private String sort;
	private String dir;
	private int start = 0;
	private int limit = 15;
	public String addTrain()
	{
//		need acegi support userId;
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		String bossId = manager.getBossId();
		user = userService.getUserById(bossId);
		Train train = new Train();
		train.setBegin(begin);
		train.setBeginDateStr(beginDateStr);
		train.setBeginDate(DateUtils.parseDate(beginDateStr));
		train.setBegintime(begintime);
		train.setBooked(0);
		train.setConfirm(0);
		train.setDestination(destination);
		train.setName(name);
		train.setNumber(number);
		train.setPrice(price);
		train.setTotal(total);
		train.setUser(user);
		trainService.save(train);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	public String getAllTrain()
	{		
//		need acegi support userId;
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		String bossId = manager.getBossId();
		Page page = new Page();
		page = trainService.getAllTrain(bossId, dir, sort, limit, start, name);
        if(page!=null)
        {
        	this.setTotalCount(page.getTotalCount());
        	List<Train> trainList = (List<Train>)page.getResult();
        	int length = trainList.size();
        	for(int i=0;i<length;i++)
        	{
        		int remain = 0;
        		if(trainList.get(i).getTotal()-trainList.get(i).getConfirm()>0)
        		 remain= trainList.get(i).getTotal()-trainList.get(i).getConfirm();
        		trainList.get(i).setRemain(remain);
        		if(remain==0)
        		{
        			trainList.get(i).setStatus(Global.SALE_OVER);
        		}
        		else
        		{
        			trainList.get(i).setStatus(Global.SALE_BOOK);
        		}
        	}
        	FlexJsonUtils jsonTool = new FlexJsonUtils();
        	this.jsonString = "{success:true,totalCount:"+this.getTotalCount()+",results:"+jsonTool.getJsonString(trainList, null, new String[]{"class","user"}, null)+"}";
        }
        else
        {
        	jsonString="{success:true,totalCount:0,results:[]}";
        }
		return SUCCESS;
	}
	 public String getSingleTrain()
	 {
	    	Train train = new Train();
	    	train = (Train)trainService.get(trainId);
	    	FlexJsonUtils jsonTool = new FlexJsonUtils();
	    	jsonString = "{success:true,totalCount:1,results:["+jsonTool.getJsonString(train, null, new String[]{"class","user"}, null)+"]}";
	    	return SUCCESS;
	  }
	 public String updateTrain()
	 {
	    	Train train = new Train();
	    	train = (Train)trainService.get(trainId);
	    	train.setBegin(begin);
	    	train.setBeginDateStr(beginDateStr);
			train.setBeginDate(DateUtils.parseDate(beginDateStr));
			train.setBegintime(begintime);
			train.setConfirm(0);
			train.setDestination(destination);
			train.setName(name);
			train.setNumber(number);
			train.setPrice(price);
			train.setTotal(total);
			trainService.merge(train);
			jsonString = "{success:true}";
	    	return SUCCESS;
	 }
	 public String delTrain()
	 {
			if(delData!=null)
				{
					if(this.delData.indexOf(",")<0)
					{
						int result = trainService.countTeamByTrain(delData.trim());
						if(result==0)
						{
							trainService.removeById(delData.trim());
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
							int result = trainService.countTeamByTrain(id[j].trim());
							if(result==0)
							{
								trainService.removeById(id[j].trim());
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
	public String getTrainId()
	{
		return trainId;
	}
	public void setTrainId(String trainId)
	{
		this.trainId = trainId;
	}
	public ITrainService getTrainService()
	{
		return trainService;
	}
	public void setTrainService(ITrainService trainService)
	{
		this.trainService = trainService;
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
