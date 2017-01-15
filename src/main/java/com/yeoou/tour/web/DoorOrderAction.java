package com.yeoou.tour.web;

import java.util.List;

import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.utils.StringUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.AssembleContent;
import com.yeoou.tour.model.ChildPrice;
import com.yeoou.tour.model.DoorOrder;
import com.yeoou.tour.model.ManPrice;
import com.yeoou.tour.model.Team;
import com.yeoou.tour.service.IDoorOrderService;
/**
 * <p>
 * Title: 后台订单显示操作模块
 * </p>
 * <p>
 * Description:后台订单的显示，审核
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class DoorOrderAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private IDoorOrderService doorOrderService;
	private int orderNo = -1;
	private String startDate = "";
	private String orderDate = "";
	private String customName = "";
	private String teamName = "";
	private int status = -1;
	private int start = 0;
	private int limit = 15;
	private String sort = "";
	private String dir = "";
	private String jsonString = "";
	private String orderId="";
	private String customPhone_hao = "";
	private String customMobile= "";
	private int manNum = 0;
	private int childNum = 0;
	private String orderOper = "";
	private DoorOrder order;
	private AssembleContent assembleContent;
	private String errorMsg = "";
	private List<ManPrice> manPriceList;
	private List<ChildPrice> childPriceList;
	private int shouldCost = 0;
	/**
	 * 显示所有订单信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAllOrder()
	{
		Page page = new Page();
		page = doorOrderService.getAllOrder(start,limit,dir,sort,orderNo,startDate,orderDate,customName,teamName,status);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		if(page!=null)
		{
			List<DoorOrder> orderList = (List<DoorOrder>)page.getResult();
			StringBuilder sb = new StringBuilder("{success:true,totalCount:");
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(orderList, new String[]{"manPriceList","childPriceList"}, null, null));
			sb.append("}");
			jsonString = sb.toString();
		}
		else
		{
			jsonString="{success:true,totalCount:0,results:[]}";
		}
		return SUCCESS;
	}
	public String getPrintOrder()
	{
		if(orderId == null)
		{
			errorMsg="您无权查看该线路信息，请关闭创口";
			return ERROR;
		}
		order =(DoorOrder) doorOrderService.get(orderId);
	
		if (order.getOrderOper()==null){
			order.setOrderOper("");
		}
		
		order.setOrderOper(StringUtils.HtmlEncode(order.getOrderOper()));
		
		if(order==null)
		{
			errorMsg="您无权查看该线路信息，请关闭创口";
			return ERROR;
		}
		
		assembleContent = new AssembleContent();
		String[] asStr = order.getAssemble().split("---");
		this.assembleContent.setAddress(asStr[0].trim());
		this.assembleContent.setAsstime(asStr[1].trim());
		this.assembleContent.setPrice(asStr[2].trim());
		if(asStr.length==5)
		{
			this.assembleContent.setJiesong(asStr[4].trim());
		}
		else
		{
			assembleContent.setJiesong("含接送");
		}
		this.manPriceList = Team.getManPricePackeage(order.getManPriceList());
		this.childPriceList = Team.getChildPricePackeage(order.getChildrenPriceList());
		return SUCCESS;
	}
	/**
	 * 获取单一订单信息
	 * @return
	 */
	public String getSingleOrder()
	{
		DoorOrder doorOrder = new DoorOrder();
		doorOrder = (DoorOrder)doorOrderService.get(orderId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,doorOrder:");
		sb.append(jsonTool.getJsonString(doorOrder, null, new String[]{"class"}, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 审核订单信息
	 * @return
	 */
	public String updateOrder()
	{
		DoorOrder doorOrder = new DoorOrder();
		doorOrder = (DoorOrder) doorOrderService.get(orderId);
		doorOrder.setCustomName(customName);
		doorOrder.setCustomMobile(customMobile);
		doorOrder.setManNum(manNum);
		doorOrder.setChildNum(childNum);
		doorOrder.setStatus(status);
		doorOrder.setShouldCost(shouldCost);
		doorOrder.setOrderOper(orderOper);
		doorOrder.setIsNew(0);
		doorOrderService.merge(doorOrder);
		jsonString="{success:true}";
		return SUCCESS;
	}
	/**
	 * 获取新订单个数以及未处理订单个数
	 * @return
	 */
	public String getOrderNews()
	{
	    	int newCount = 0;
	    	int nonDispose = 0;
		    newCount = doorOrderService.getCountNewsOrder();
		    nonDispose = doorOrderService.getCountNonDisposeOrder();
		    jsonString = "{success:true,newCount:"+newCount+",nonDispose:"+nonDispose+"}";
	    	return SUCCESS;
	}
	public String getCustomName()
	{
		return customName;
	}
	public void setCustomName(String customName)
	{
		this.customName = customName;
	}
	public String getDir()
	{
		return dir;
	}
	public void setDir(String dir)
	{
		this.dir = dir;
	}
	public IDoorOrderService getDoorOrderService()
	{
		return doorOrderService;
	}
	public void setDoorOrderService(IDoorOrderService doorOrderService)
	{
		this.doorOrderService = doorOrderService;
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
	public String getOrderDate()
	{
		return orderDate;
	}
	public void setOrderDate(String orderDate)
	{
		this.orderDate = orderDate;
	}
	public int getOrderNo()
	{
		return orderNo;
	}
	public void setOrderNo(int orderNo)
	{
		this.orderNo = orderNo;
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
	public String getStartDate()
	{
		return startDate;
	}
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public String getTeamName()
	{
		return teamName;
	}
	public void setTeamName(String teamName)
	{
		this.teamName = teamName;
	}
	public int getChildNum()
	{
		return childNum;
	}
	public void setChildNum(int childNum)
	{
		this.childNum = childNum;
	}
	public String getCustomMobile()
	{
		return customMobile;
	}
	public void setCustomMobile(String customMobile)
	{
		this.customMobile = customMobile;
	}
	public String getCustomPhone_hao()
	{
		return customPhone_hao;
	}
	public void setCustomPhone_hao(String customPhone_hao)
	{
		this.customPhone_hao = customPhone_hao;
	}
	public int getManNum()
	{
		return manNum;
	}
	public void setManNum(int manNum)
	{
		this.manNum = manNum;
	}
	public String getOrderId()
	{
		return orderId;
	}
	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}
	public String getOrderOper()
	{
		return orderOper;
	}
	public void setOrderOper(String orderOper)
	{
		this.orderOper = orderOper;
	}
	public AssembleContent getAssembleContent()
	{
		return assembleContent;
	}
	public void setAssembleContent(AssembleContent assembleContent)
	{
		this.assembleContent = assembleContent;
	}
	public List<ChildPrice> getChildPriceList()
	{
		return childPriceList;
	}
	public void setChildPriceList(List<ChildPrice> childPriceList)
	{
		this.childPriceList = childPriceList;
	}
	public String getErrorMsg()
	{
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg)
	{
		this.errorMsg = errorMsg;
	}
	public List<ManPrice> getManPriceList()
	{
		return manPriceList;
	}
	public void setManPriceList(List<ManPrice> manPriceList)
	{
		this.manPriceList = manPriceList;
	}
	public DoorOrder getOrder()
	{
		return order;
	}
	public void setOrder(DoorOrder order)
	{
		this.order = order;
	}
	public int getShouldCost()
	{
		return shouldCost;
	}
	public void setShouldCost(int shouldCost)
	{
		this.shouldCost = shouldCost;
	}
}
