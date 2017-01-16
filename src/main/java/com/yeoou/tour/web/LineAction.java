package com.yeoou.tour.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import net.sf.json.JSONArray;
import com.yeoou.common.context.Global;
import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.DateUtils;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.utils.StringUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.rbac.model.User;
import com.yeoou.rbac.service.IUserService;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.Company;
import com.yeoou.tour.model.Content;
import com.yeoou.tour.model.KeyWord;
import com.yeoou.tour.model.Line;
import com.yeoou.tour.model.Manager;
import com.yeoou.tour.model.Region;
import com.yeoou.tour.model.Scenery;
import com.yeoou.tour.model.SceneryPic;
import com.yeoou.tour.model.SceneryType;
import com.yeoou.tour.model.TreeNode;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.ICompanyService;
import com.yeoou.tour.service.IKeyWordService;
import com.yeoou.tour.service.ILineService;
import com.yeoou.tour.service.IRegionService;
import com.yeoou.tour.service.ISceneryPicService;
import com.yeoou.tour.service.ISceneryService;


/**
 * <p>
 * Title: 后台行程相关展示操作模块
 * </p>
 * <p>
 * Description: 添加行程，删除行程，更新行程操作
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class LineAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private ILineService lineService;
	private IUserService userService;
	private ICompanyService companyService;
	private ISceneryService sceneryService;
	private ISceneryPicService sceneryPicService;
	private IKeyWordService keyWordService;
	private IRegionService regionService;
	private IAreaService areaService;
	private int start = 0;
	private int limit = 15;
	private int searchType = 0;
	private String sort = "";
	private String dir =  "";
	private String lineId = "";
	private String reregionList;
	private String recityList;
	private String reregion = "";
	private String content = "";
	private String feature = "";
	private String feeClude = "";
	private String theme = "";
	private String feeUnclude = "";
	private String purchase = "";
	private String safe = "";
	private String remark = "";
	private String selfBuy = "";
	private String title = "";
	private int flag = 0;
	private String subTitle = "";
	private String regionId = "";
	private String areaId = "";
	private String jsonString = "";
	private String node="";
	private String copyData = "";
	private String delData;
	private String userId = "";
	private Line line;
	private List<Content> returnLineContent;
	private List<Content> simpleContent;
	private String contentHtml;
	public List<Content> getSimpleContent()
	{
		return simpleContent;
	}
	public void setSimpleContent(List<Content> simpleContent)
	{
		this.simpleContent = simpleContent;
	}
	public Line getLine()
	{
		return line;
	}
	public void setLine(Line line)
	{
		this.line = line;
	}
	public ICompanyService getCompanyService()
	{
		return companyService;
	}
	public void setCompanyService(ICompanyService companyService)
	{
		this.companyService = companyService;
	}
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	/**
	 * 打印行程信息
	 * @return
	 */
	public String printSingelLine()
	{
		line =(Line) lineService.get(lineId);
		line.setFeature(StringUtils.HtmlEncode(line.getFeature()));
		line.setFeeClude(StringUtils.HtmlEncode(line.getFeeClude()));
		line.setFeeUnclude(StringUtils.HtmlEncode(line.getFeeUnclude()));
		line.setRemark(StringUtils.HtmlEncode(line.getRemark()));
		line.setPurchase(StringUtils.HtmlEncode(line.getPurchase()));
		line.setSelfBuy(StringUtils.HtmlEncode(line.getSelfBuy()));
		line.setSafe(StringUtils.HtmlEncode(line.getSafe()));
		List<Content>tempList = new ArrayList<Content>();
		this.simpleContent = Content.unpackageContent(StringUtils.HtmlEncode(line.getContent()));
		tempList = Content.unpackageContentUnit(line.getPicUrl());
		returnLineContent=Content.unpackageContent(StringUtils.HtmlEncode(line.getContentHtml()));
		if(tempList!=null)
		{	
			for(int i=0;i<tempList.size();i++)
			{
				if(tempList.get(i).getPuList()!=null)
				{
					returnLineContent.get(i).setPuList(tempList.get(i).getPuList());
				}
				
			}
		}
		
		return SUCCESS;
	}
	/**
	 * 获取类型菜单
	 */
	public String getRegionMenuByUser()
	{
//		need acegi support userId;
		String parentAreaId = "";
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		String areaId = manager.getAreaId();
		Area area = new Area();
		area = (Area)areaService.get(areaId);
		if(area.getName().equals("北京")||area.getName().equals("重庆")||area.getName().equals("天津")||area.getName().equals("上海")||area.getName().equals("香港")||area.getName().equals("澳门"))
		{
			parentAreaId = areaId;
		}
		else
		{
			parentAreaId = area.getParent();
		}
		FlexJsonUtils jsonTools = new FlexJsonUtils();
		Region region = new Region();
		region = regionService.getRegionByAreaId(parentAreaId, 1);
		List<Region>menuRegion = new ArrayList<Region>();
		menuRegion.add(region);
//		List<Region> menuRegion = regionService.getRegionsByParent(region.getRegionId(), "flag", true);
		jsonString = "{success:true,menuRegion:"+jsonTools.getJsonString(menuRegion, new String[]{"children"}, new String[]{"class","children.class","area","children.area"}, null)+"}";
		return SUCCESS;
	}
	/**
	 * 获取省份信息
	 * @return
	 */
	public String getProvinceandregion()
	{
//		need acegi support userId;
		Area area = new Area();
		List<Area> areaList = new ArrayList<Area>();
		
		if(flag==3||flag==4)
		{
			areaList = areaService.getState();
		}
		else
		{
			areaList = areaService.getProvince();
			area = areaService.getAreaByName("香港");
			areaList.remove(area);
			area = areaService.getAreaByName("澳门");
			areaList.remove(area);
			area = areaService.getAreaByName("台湾");
			areaList.remove(area);
		}
		Region region = new Region();
		region = regionService.getRegionChildren(regionId);//userid
		List<Region> regionList= new ArrayList<Region>(region.getChildren());
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,");
		sb.append("areaList:");
		sb.append(jsonTool.getJsonString(areaList, null, new String[]{"children","class","user"}, null));
		sb.append(",regionList:");
		sb.append(jsonTool.getJsonString(regionList, null, new String[]{"children","class","user"}, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 获取类型儿子信息
	 * @return
	 */
	public String getChildrenRegion()
	{
//		need acegi support userId;
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		List<Region> regionList = regionService.getChildren(regionId);
		StringBuilder sb = new StringBuilder("{success:true,regionList:");
		sb.append(jsonTool.getJsonString(regionList, null, new String[]{"children","class","user"}, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 获取省份的城市信息
	 * @return
	 */
	public String getCityByProvince()
	{
		
		Area area = new Area();
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		area = areaService.getAreaChildren(areaId);
		List<Area>areaList = new ArrayList<Area>(area.getChildren());
		if(area.getName().equals("亚洲"))
		{
			
			Area temparea = new Area();
			temparea = areaService.getAreaByName("中国");
			areaList.remove(temparea);
			temparea = areaService.getAreaByName("香港",4);
			areaList.add(temparea);
			temparea = areaService.getAreaByName("澳门",4);
			areaList.add(temparea);
			temparea = areaService.getAreaByName("台湾",3);
			areaList.add(temparea);
		}
		
		StringBuilder sb = new StringBuilder("{success:true,cityList:");
		sb.append(jsonTool.getJsonString(areaList, null, new String[]{"children","class","user"}, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 树形菜单获取相应节点信息
	 * @return
	 */
	public String getChildrenByNode()
	{
//		need acegi support userId;
		List<TreeNode> childlist = new ArrayList<TreeNode>();
		@SuppressWarnings("unused")
		Region proxyRegion = new Region();
		if(node.equals("-1"))
		{
			proxyRegion = regionService.getRegionChildren(node.trim());//userId
			childlist = regionService.setRegionTreeNodes(proxyRegion);
		}
		else
		{
			proxyRegion = regionService.getRegionChildren(node.trim());//userId
			childlist = regionService.setRegionTreeNodes(proxyRegion);
		}
		
		JSONArray jsarray = JSONArray.fromObject(childlist);
		jsonString=jsarray.toString();
		return SUCCESS;
	}
	/**
	 * 获取批发商列表
	 * @return
	 */
	public String jobberInfo()
	{
		List<Company> comList = new ArrayList<Company>();
		comList = (List<Company>)companyService.getAll();
		int length = comList.size();
		StringBuilder sb = new StringBuilder("[");
		for(int i=0;i<length-1;i++)
		{
			sb.append("[");
			sb.append("\'");
			sb.append(comList.get(i).getUserId());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(comList.get(i).getName());
			sb.append("\'");
			sb.append("]");
			sb.append(",");
			
		}
		sb.append("[");
		sb.append("\'");
		sb.append(comList.get(length-1).getUserId());
		sb.append("\'");
		sb.append(",");
		sb.append("\'");
		sb.append(comList.get(length-1).getName());
		sb.append("\'");
		sb.append("]");
		sb.append("]");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 复制批发商行程
	 * @return
	 */
	public String copyJobberLineList()
	{
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		User user = userService.getUserById(manager.getBossId());
		Line line = new Line();
		Line newLine = new Line();
		if(copyData!=null)
		{
			if(copyData.indexOf(",")<0)
			{
				line = lineService.getLine(copyData.trim());
				newLine.setContent(line.getContent());
				newLine.setContentHtml(line.getContentHtml());
			    newLine.setPicArea(line.getPicArea());
			    newLine.setPicUrl(line.getPicUrl());
				newLine.setDelFlag(line.getDelFlag());
				newLine.setFeature(line.getFeature());
				newLine.setFeeClude(line.getFeeClude());
				newLine.setFeeUnclude(line.getFeeUnclude());
				newLine.setPurchase(line.getPurchase());
				List<Scenery> scList = new ArrayList<Scenery>(line.getScenerys());
				newLine.setScenerys(new HashSet<Scenery>(scList));
				List<Region> regionList = new ArrayList<Region>(line.getRegions());
				newLine.setRegions(new HashSet<Region>(regionList));
				List<Area> tempList = new ArrayList<Area>(line.getAreas());
				newLine.setAreas(new HashSet<Area>(tempList));
				List<SceneryType> stList = new ArrayList<SceneryType>(line.getTypes());
				newLine.setTypes(new HashSet<SceneryType>(stList));
				newLine.setRemark(line.getRemark());
				newLine.setSafe(line.getSafe());
				newLine.setSelfBuy(line.getSelfBuy());
				newLine.setSubTitle(line.getSubTitle());
				newLine.setTitle(line.getTitle());
				newLine.setWriter(line.getWriter());
				newLine.setUser(user);
		        Date date=new Date();
		        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		        SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
		        String time=df.format(date);
		        String time1 = df1.format(date);
		        newLine.setStrDate(time);
		        newLine.setCreateTime(DateUtils.parseDate(time1));
		        lineService.save(newLine);
			}
			else
			{
				String[] id = copyData.split(",");
				for(int i=0;i<id.length;i++)
				{
					line = new Line();
					newLine = new Line();
					line = lineService.getLine(id[i].trim());
					newLine.setContent(line.getContent());
					newLine.setContentHtml(line.getContentHtml());
				    newLine.setPicArea(line.getPicArea());
				    newLine.setPicUrl(line.getPicUrl());
					newLine.setDelFlag(line.getDelFlag());
					newLine.setFeature(line.getFeature());
					newLine.setFeeClude(line.getFeeClude());
					newLine.setFeeUnclude(line.getFeeUnclude());
					newLine.setPurchase(line.getPurchase());
					List<SceneryType> stList = new ArrayList<SceneryType>(line.getTypes());
					newLine.setTypes(new HashSet<SceneryType>(stList));
					List<Scenery> scList = new ArrayList<Scenery>(line.getScenerys());
					newLine.setScenerys(new HashSet<Scenery>(scList));
					List<Region> regionList = new ArrayList<Region>(line.getRegions());
					newLine.setRegions(new HashSet<Region>(regionList));
					List<Area> tempList = new ArrayList<Area>(line.getAreas());
					newLine.setAreas(new HashSet<Area>(tempList));
					newLine.setRemark(line.getRemark());
					newLine.setSafe(line.getSafe());
					newLine.setSelfBuy(line.getSelfBuy());
					newLine.setSubTitle(line.getSubTitle());
					newLine.setTitle(line.getTitle());
					newLine.setWriter(line.getWriter());
					newLine.setUser(user);
			        Date date=new Date();
			        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			        SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
			        String time=df.format(date);
			        String time1 = df1.format(date);
			        newLine.setStrDate(time);
			        newLine.setCreateTime(DateUtils.parseDate(time1));
			        lineService.save(newLine);
				}
			}
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 复制当前行程
	 * @return
	 */
	public String copyLineList()
	{
		Line line = new Line();
		Line newLine = new Line();
		if(copyData!=null)
		{
			if(copyData.indexOf(",")<0)
			{
				line = lineService.getLine(copyData.trim());
				newLine.setContent(line.getContent());
				newLine.setContentHtml(line.getContentHtml());
			    newLine.setPicArea(line.getPicArea());
			    newLine.setPicUrl(line.getPicUrl());
				newLine.setDelFlag(line.getDelFlag());
				newLine.setFeature(line.getFeature());
				newLine.setFeeClude(line.getFeeClude());
				newLine.setFeeUnclude(line.getFeeUnclude());
				newLine.setPurchase(line.getPurchase());
				List<Scenery> scList = new ArrayList<Scenery>(line.getScenerys());
				newLine.setScenerys(new HashSet<Scenery>(scList));
				List<Region> regionList = new ArrayList<Region>(line.getRegions());
				newLine.setRegions(new HashSet<Region>(regionList));
				List<Area> tempList = new ArrayList<Area>(line.getAreas());
				newLine.setAreas(new HashSet<Area>(tempList));
				List<SceneryType> stList = new ArrayList<SceneryType>(line.getTypes());
				newLine.setTypes(new HashSet<SceneryType>(stList));
				newLine.setRemark(line.getRemark());
				newLine.setSafe(line.getSafe());
				newLine.setSelfBuy(line.getSelfBuy());
				newLine.setSubTitle(line.getSubTitle());
				newLine.setTitle(line.getTitle());
				newLine.setWriter(line.getWriter());
				newLine.setUser(line.getUser());
		        Date date=new Date();
		        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		        SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
		        String time=df.format(date);
		        String time1 = df1.format(date);
		        newLine.setStrDate(time);
		        newLine.setCreateTime(DateUtils.parseDate(time1));
		        lineService.save(newLine);
			}
			else
			{
				String[] id = copyData.split(",");
				for(int i=0;i<id.length;i++)
				{
					line = lineService.getLine(id[i].trim());
					newLine.setContent(line.getContent());
					newLine.setContentHtml(line.getContentHtml());
				    newLine.setPicArea(line.getPicArea());
				    newLine.setPicUrl(line.getPicUrl());
					newLine.setDelFlag(line.getDelFlag());
					newLine.setFeature(line.getFeature());
					newLine.setFeeClude(line.getFeeClude());
					newLine.setFeeUnclude(line.getFeeUnclude());
					newLine.setPurchase(line.getPurchase());
					List<SceneryType> stList = new ArrayList<SceneryType>(line.getTypes());
					newLine.setTypes(new HashSet<SceneryType>(stList));
					List<Scenery> scList = new ArrayList<Scenery>(line.getScenerys());
					newLine.setScenerys(new HashSet<Scenery>(scList));
					List<Region> regionList = new ArrayList<Region>(line.getRegions());
					newLine.setRegions(new HashSet<Region>(regionList));
					List<Area> tempList = new ArrayList<Area>(line.getAreas());
					newLine.setAreas(new HashSet<Area>(tempList));
					newLine.setRemark(line.getRemark());
					newLine.setSafe(line.getSafe());
					newLine.setSelfBuy(line.getSelfBuy());
					newLine.setSubTitle(line.getSubTitle());
					newLine.setTitle(line.getTitle());
					newLine.setWriter(line.getWriter());
					newLine.setUser(line.getUser());
			        Date date=new Date();
			        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			        SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
			        String time=df.format(date);
			        String time1 = df1.format(date);
			        newLine.setStrDate(time);
			        newLine.setCreateTime(DateUtils.parseDate(time1));
			        lineService.save(newLine);
				}
			}
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 添加行程，对行程内容进行景点关键字进行替换并添加相应的景点图片<br/>
	 * 如果此行程没有景点关键字关联那么在相关城市中取得相关图片进行关联
	 * @return
	 */
	public String addLine()
	{
//		need acegi support userId;
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		User user = userService.getUserById(manager.getBossId());
		List<Scenery> sceneryList = new ArrayList<Scenery>();
		Line line = new Line();
		List<Content> conList = new ArrayList<Content>();
		List<KeyWord> kwList = new ArrayList<KeyWord>();
		List<String> cityId = new ArrayList<String>();
		List<String> pacityId = new ArrayList<String>();
		List<String> regionsId = new ArrayList<String>();
		List<String> paregionsId = new ArrayList<String>();
		kwList = (List<KeyWord>)keyWordService.getAll();
		conList = Content.unpackageContent(content);
		String picUrl = "";
		String conTemp = "";
		String compare = "";
		conTemp = content;
		int size = kwList.size();
		
//		分解城市
		if(recityList!=null)
		{
			if(recityList.indexOf(",")<0)
			{
				String[] temp = recityList.split("@@@");
				cityId.add(temp[0]);
				pacityId.add(temp[1]);
			}
			else
			{
				String[] temp = recityList.split(",");
				for(int i=0;i<temp.length;i++)
				{
					String[] temp1 = temp[i].split("@@@");
					cityId.add(temp1[0]);
					pacityId.add(temp1[1]);
					
				}
			}
		}
		int length = pacityId.size();
		for(int i=0;i<length;i++)
		{
			if(!pacityId.get(i).equals("@"))
			{
				for(int j=i+1;j<length;j++)
				{
					if(pacityId.get(i).equals(pacityId.get(j)))
					{
						pacityId.set(j, "@");
					}
				}
			}
		}
		while(length>0)
		{
			if(pacityId.get(length-1).equals("@"))
			{
				pacityId.remove(length-1);
			}
			length=length-1;
		}
//		分解分类
		if(reregionList!=null)
		{
			if(reregionList.indexOf(",")<0)
			{
				String[] temp = reregionList.split("@@@");
				regionsId.add(temp[0]);
				paregionsId.add(temp[1]);
			}
			else
			{
				String[] temp = reregionList.split(",");
				for(int i=0;i<temp.length;i++)
				{
					String[] temp1 = temp[i].split("@@@");
					regionsId.add(temp1[0]);
					paregionsId.add(temp1[1]);
				}
			}
		}
		length = paregionsId.size();
		for(int k=0;k<length;k++)
		{
			if(!paregionsId.get(k).equals("@"))
			{
				for(int l=k+1;l<length;l++)
				{
					if(paregionsId.get(k).equals(paregionsId.get(l)))
					{
						paregionsId.set(l, "@");
					}
				}
			}
		}
		while(length>0)
		{
			if(paregionsId.get(length-1).equals("@"))
			{
				paregionsId.remove(length-1);
			}
			length=length-1;
		}
//		添加城市
		length = cityId.size();
		List<Area> tempareaList = new ArrayList<Area>();
		for(int i=0;i<length;i++)
		{
			tempareaList.add((Area)areaService.get(cityId.get(i).trim()));
		}
		Random rd = new Random();
		Area rdArea = new Area();
		int cnt = tempareaList.size();
		if(cnt==0)
		{
			rdArea = tempareaList.get(0);
		}
		else
		{
			rdArea = tempareaList.get(rd.nextInt(cnt));
		}
		length = pacityId.size();
		for(int i=0;i<length;i++)
		{
			tempareaList.add((Area)areaService.get(pacityId.get(i).trim()));
		}
		String parArea = ((Area)areaService.get(pacityId.get(0).trim())).getParent();
		tempareaList.add((Area)areaService.get(parArea));
//		添加类别
		length = regionsId.size();
		List<Region> tempregionList = new ArrayList<Region>();
		for(int i=0;i<length;i++)
		{
			tempregionList.add((Region)regionService.get(regionsId.get(i).trim()));
		}
		length = paregionsId.size();
		for(int i=0;i<length;i++)
		{
			tempregionList.add((Region)regionService.get(paregionsId.get(i).trim()));
		}
		tempregionList.add((Region)regionService.get(reregion.trim()));
		List<KeyWord> realKeyWord = new ArrayList<KeyWord>();
		for(int i=0;i<size;i++)
		{
			if(kwList.get(i).getSame()==0)
			{
				compare = conTemp.replaceAll(kwList.get(i).getName(), "<A target=\"_blank\" href=\""+kwList.get(i).getUrl()+"\">"+kwList.get(i).getName()+"</A>");
				if(!compare.equals(conTemp))
				{
					realKeyWord.add(kwList.get(i));
				}
//				conTemp = compare;
			}
		}
		List<KeyWord> lastKeyWord = new ArrayList<KeyWord>();
		for(int i=0;i<realKeyWord.size();i++)
		{
			int count = 0;
			for(int j=0;j<cityId.size();j++)
			{
				if(realKeyWord.get(i).getAreaId().equals(cityId.get(j).trim()))
				{
					count++;
				}
			}
			if(count!=0)
			{
				Scenery sc = new Scenery();
				lastKeyWord.add(realKeyWord.get(i));
				sc = (Scenery)sceneryService.get(realKeyWord.get(i).getId());
				if(sc!=null)
				{
					sceneryList.add(sc);
				}
			}
		}
		
		conTemp = "";
		compare = "";
		conTemp = content;
		for(int i=0;i<lastKeyWord.size();i++)
		{
				compare = conTemp.replaceAll(lastKeyWord.get(i).getName(), "<A target=\"_blank\" href=\""+lastKeyWord.get(i).getUrl()+"\">"+lastKeyWord.get(i).getName()+"</A>");
				conTemp = compare;
		}
		line.setContentHtml(conTemp);
		size = conList.size();
		List<String> picFour = new ArrayList<String>();
		if(sceneryList.size()!=0)
		{
			List<SceneryType> stList = new ArrayList<SceneryType>();
			for(int i=0;i<sceneryList.size();i++)
			{
				List<SceneryType> tempList = new ArrayList<SceneryType>(sceneryService.getSceneryInfo(sceneryList.get(i).getSceneryId()).getTypes());
				for(int j=0;j<tempList.size();j++)
				{
					stList.add(tempList.get(j));
				}
			}
			line.setTypes(new HashSet<SceneryType>(stList));
			for(int i=0;i<size-1;i++)
			{
				List<String> picStrList = new ArrayList<String>();
				String cp = "";
				String ct = conList.get(i).getTrips();
				int lsize = sceneryList.size();
				for(int j=0;j<lsize;j++)
				{
					cp = ct.replace(sceneryList.get(j).getName(), "@@@");
					if(!cp.equals(ct))
					{
						List<SceneryPic> picList = sceneryPicService.getPicListByScenery(sceneryList.get(j).getSceneryId());
						if(picList!=null)
						{
							Random rnd = new Random();
							if(picList.size()==1)
							{
								String temp1 = picList.get(0).getMiniUrl()+"@"+picList.get(0).getBreviaryUrl()+"@"+sceneryList.get(j).getSceneryId()+"@"+sceneryList.get(j).getName();
								String temp2 = picList.get(0).getBreviaryUrl()+"@"+sceneryList.get(j).getSceneryId()+"@"+sceneryList.get(j).getName();
								if(picFour.size()<4)
								{
									picFour.add(temp1);
								}
								picStrList.add(temp2);
							}
							else
							{
								int l = picList.size();
								int o = rnd.nextInt(l);
								String temp1 = picList.get(o).getMiniUrl()+"@"+picList.get(o).getBreviaryUrl()+"@"+sceneryList.get(j).getSceneryId()+"@"+sceneryList.get(j).getName();
								o = rnd.nextInt(l);
								String temp2 = picList.get(o).getBreviaryUrl()+"@"+sceneryList.get(j).getSceneryId()+"@"+sceneryList.get(j).getName();
								if(picFour.size()<4)
								{
									picFour.add(temp1);
								}
								picStrList.add(temp2);
							}
						}
					}
					ct = cp;
				}
				if(picStrList.size()!=0)
				{
					for(int j=0;j<picStrList.size()-1;j++)
					{
						picUrl = picUrl+picStrList.get(j)+"#";
					}
					picUrl = picUrl+picStrList.get(picStrList.size()-1)+"#@#";
				}
				else
				{
					picUrl = picUrl+"#@#";
				}
			}
			List<String> picStrList = new ArrayList<String>();
			String cp = "";
			String ct = conList.get(size-1).getTrips();
			int lsize = sceneryList.size();
			for(int j=0;j<lsize;j++)
			{
				cp = ct.replace(sceneryList.get(j).getName(), "@@@");
				if(!cp.equals(ct))
				{
					List<SceneryPic> picList = sceneryPicService.getPicListByScenery(sceneryList.get(j).getSceneryId());
					if(picList!=null)
					{
						Random rnd = new Random();
						if(picList.size()==1)
						{
							String temp1 = picList.get(0).getMiniUrl()+"@"+picList.get(0).getBreviaryUrl()+"@"+sceneryList.get(j).getSceneryId()+"@"+sceneryList.get(j).getName();
							String temp2 = picList.get(0).getBreviaryUrl()+"@"+sceneryList.get(j).getSceneryId()+"@"+sceneryList.get(j).getName();
							if(picFour.size()<4)
							{
								picFour.add(temp1);
							}
							picStrList.add(temp2);
						}
						else
						{
							int l = picList.size();
							int o = rnd.nextInt(l);
							String temp1 = picList.get(o).getMiniUrl()+"@"+picList.get(o).getBreviaryUrl()+"@"+sceneryList.get(j).getSceneryId()+"@"+sceneryList.get(j).getName();
							o = rnd.nextInt(l);
							String temp2 = picList.get(o).getBreviaryUrl()+"@"+sceneryList.get(j).getSceneryId()+"@"+sceneryList.get(j).getName();
							if(picFour.size()<4)
							{
								picFour.add(temp1);
							}
							picStrList.add(temp2);
						}
					}
				}
				ct = cp;
			}
			if(picStrList.size()!=0)
			{
				for(int j=0;j<picStrList.size()-1;j++)
				{
					picUrl = picUrl+picStrList.get(j)+"#";
				}
				picUrl = picUrl+picStrList.get(picStrList.size()-1);
			}
			else
			{
				picUrl = picUrl+"#@#";
			}
			line.setScenerys(new HashSet<Scenery>(sceneryList));
			String picArea = "";
			if(picFour.size()!=0)
			{
				for(int i=0;i<picFour.size()-1;i++)
				{
					picArea = picArea+picFour.get(i)+"###";
				}
				picArea = picArea+picFour.get(picFour.size()-1);
			}
			else
			{
				picArea = "";
			}
			line.setPicArea(picArea);
			if(sceneryList.size()<4)
			{
				List<SceneryPic> picList = new ArrayList<SceneryPic>();
				picArea = "";
				picList = sceneryPicService.getPicListByArea(rdArea.getAreaId());
				if(picList!=null)
				{
					for(int k=0;k<picList.size();k++)
					{
							String temp1 = picList.get(k).getMiniUrl()+"@"+picList.get(k).getBreviaryUrl()+"@"+"1"+"@"+picList.get(0).getName();
							if(picFour.size()<4)
							{
								picFour.add(temp1);
							}
					}
					if(picFour.size()!=0)
					{
						for(int i=0;i<picFour.size()-1;i++)
						{
							picArea = picArea+picFour.get(i)+"###";
						}
						picArea = picArea+picFour.get(picFour.size()-1);
					}
					else
					{
						picArea = "";
					}
				}
				line.setPicArea(picArea);
			}
			line.setPicUrl(picUrl);
			
		}
		else
		{
			List<SceneryPic> picList = new ArrayList<SceneryPic>();
			String picArea = "";
			picList = sceneryPicService.getPicListByArea(rdArea.getAreaId());
			if(picList!=null)
			{
				for(int k=0;k<picList.size();k++)
				{
						String temp1 = picList.get(k).getMiniUrl()+"@"+picList.get(k).getBreviaryUrl()+"@"+"1"+"@"+picList.get(0).getName();
						if(picFour.size()<4)
						{
							picFour.add(temp1);
						}
				}
				
				if(picFour.size()!=0)
				{
					for(int i=0;i<picFour.size()-1;i++)
					{
						picArea = picArea+picFour.get(i)+"###";
					}
					picArea = picArea+picFour.get(picFour.size()-1);
				}
				else
				{
					picArea = "";
				}
			}
			line.setContentHtml(this.content);
			line.setPicArea(picArea);
			line.setPicUrl("");
		}
		
		line.setWriter(manager.getName());
		line.setAreas(new HashSet<Area>(tempareaList));
		line.setContent(content);
		
		line.setDelFlag(Global.LINE_KEEP);
		line.setFeature(feature);
		line.setFeeClude(feeClude);
		if(theme.equals("请选择一个主题")||theme.equals("无主题"))
		{
			line.setTheme("");
		}
		else
		{
			line.setTheme(theme);
		}
		line.setFeeUnclude(feeUnclude);
		line.setPurchase(purchase);
		line.setRegions(new HashSet<Region>(tempregionList));
		line.setSafe(safe);
		line.setRemark(remark);
		line.setSelfBuy(selfBuy);
		line.setTitle(title.trim());
		line.setSubTitle(subTitle.trim());
		line.setUser(user);
		Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
        String time=df.format(date);
        String time1 = df1.format(date);
        line.setCreateTime(DateUtils.parseDate(time1));
        line.setStrDate(time);
        lineService.save(line);
        jsonString="{success:true}";
		return SUCCESS;
	}
	/**
	 * 获取批发商线路信息列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAllJobberLine()
	{
		
		Page page = new Page();
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		page = lineService.getAllLine(start, limit, dir, sort, title, node, userId,"");
		if(page!=null)
		{
			List<Line> lineList = (List<Line>)page.getResult();
			StringBuilder sb = new StringBuilder("{success:true,totalCount:");
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(lineList, null, new String[]{"class","user"}, null));
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
	 * 获取当前用户的行程列表并附带相关的搜索功能
	 * @return
	 */
	public String getAllLine()
	{
//		need acegi support userId;
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		String userId = manager.getBossId();
		Page page = new Page();
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		String subTitle = "";
		if(this.searchType == 0)
		{
			subTitle = "";
		}
		else
		{
			subTitle = title;
			title = "";
		}
		page = lineService.getAllLine(start, limit, dir, sort, title, node, userId,subTitle);
		if(page!=null)
		{
			List<Line> lineList = (List<Line>)page.getResult();
			StringBuilder sb = new StringBuilder("{success:true,totalCount:");
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(lineList, null, new String[]{"class","user"}, null));
			sb.append("}");
			jsonString = sb.toString();
		}
		else
		{
			jsonString="{success:true,totalCount:0,results:[]}";
		}
		return SUCCESS;
	}
	public String getSingleLine2()
	{
		line = (Line)lineService.getLine(lineId);
		return SUCCESS;
	}
	public String updateContentHtml()
	{
		line = (Line)lineService.getLine(lineId);
		line.setContentHtml(contentHtml);
		lineService.merge(line);
		jsonString = "修改成功";
		return SUCCESS;
	}
	/**
	 * 获取单一行程信息
	 * @return
	 */
	public String getSingleLine()
	{
//		need acegi support userId;
		Line line = lineService.getLine(lineId);
		Area area = new Area();
		List<Area> areaList = new ArrayList<Area>();
		if(flag==3||flag==4)
		{
			areaList = areaService.getState();
		}
		else
		{
			areaList = areaService.getProvince();
			area = areaService.getAreaByName("香港");
			areaList.remove(area);
			area = areaService.getAreaByName("澳门");
			areaList.remove(area);
			area = areaService.getAreaByName("台湾");
			areaList.remove(area);
		}
		Region region = new Region();
		region = regionService.getRegionChildren(regionId);//userid
		List<Region> regionList= new ArrayList<Region>(region.getChildren());
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,");
		sb.append("areaList:");
		sb.append(jsonTool.getJsonString(areaList, null, new String[]{"children","class"}, null));
		sb.append(",regionList:");
		sb.append(jsonTool.getJsonString(regionList, null, new String[]{"children","class","area","user"}, null));
		sb.append(",line:");
		sb.append(jsonTool.getJsonString(line, new String[]{"areas","regions"}, new String[]{"class","areas.class","regions.class","regions.area","regions.user"}, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 更新行程信息，并替换更新的景点关键字信息
	 * @return
	 */
	public String updateLine()
	{
//		need acegi support userId;
		Manager manager = new Manager();
		manager = this.getManagerInfo();
		User user = (User)userService.get(manager.getBossId());
		List<Scenery> sceneryList = new ArrayList<Scenery>();
		Line line = new Line();
		line = (Line)lineService.get(lineId);
		List<Content> conList = new ArrayList<Content>();
		List<KeyWord> kwList = new ArrayList<KeyWord>();
		List<String> cityId = new ArrayList<String>();
		List<String> pacityId = new ArrayList<String>();
		List<String> regionsId = new ArrayList<String>();
		List<String> paregionsId = new ArrayList<String>();
		kwList = (List<KeyWord>)keyWordService.getAll();
		conList = Content.unpackageContent(content);
		String picUrl = "";
		String conTemp = "";
		String compare = "";
		conTemp = content;
		int size = kwList.size();
//		分解城市
		if(recityList!=null)
		{
			if(recityList.indexOf(",")<0)
			{
				String[] temp = recityList.split("@@@");
				cityId.add(temp[0]);
				pacityId.add(temp[1]);
			}
			else
			{
				String[] temp = recityList.split(",");
				for(int i=0;i<temp.length;i++)
				{
					String[] temp1 = temp[i].split("@@@");
					cityId.add(temp1[0]);
					pacityId.add(temp1[1]);
					
				}
			}
		}
		int length = pacityId.size();
		for(int i=0;i<length;i++)
		{
			if(!pacityId.get(i).equals("@"))
			{
				for(int j=i+1;j<length;j++)
				{
					if(pacityId.get(i).equals(pacityId.get(j)))
					{
						pacityId.set(j, "@");
					}
				}
			}
		}
		while(length>0)
		{
			if(pacityId.get(length-1).equals("@"))
			{
				pacityId.remove(length-1);
			}
			length=length-1;
		}
//		分解分类
		if(reregionList!=null)
		{
			if(reregionList.indexOf(",")<0)
			{
				String[] temp = reregionList.split("@@@");
				regionsId.add(temp[0]);
				paregionsId.add(temp[1]);
			}
			else
			{
				String[] temp = reregionList.split(",");
				for(int i=0;i<temp.length;i++)
				{
					String[] temp1 = temp[i].split("@@@");
					regionsId.add(temp1[0]);
					paregionsId.add(temp1[1]);
				}
			}
		}
		length = paregionsId.size();
		for(int k=0;k<length;k++)
		{
			if(!paregionsId.get(k).equals("@"))
			{
				for(int l=k+1;l<length;l++)
				{
					if(paregionsId.get(k).equals(paregionsId.get(l)))
					{
						paregionsId.set(l, "@");
					}
				}
			}
		}
		while(length>0)
		{
			if(paregionsId.get(length-1).equals("@"))
			{
				paregionsId.remove(length-1);
			}
			length=length-1;
		}
//		添加城市
		length = cityId.size();
		List<Area> tempareaList = new ArrayList<Area>();
		for(int i=0;i<length;i++)
		{
			tempareaList.add((Area)areaService.get(cityId.get(i).trim()));
		}
		Random rd = new Random();
		Area rdArea = new Area();
		int cnt = tempareaList.size();
		if(cnt==0)
		{
			rdArea = tempareaList.get(0);
		}
		else
		{
			rdArea = tempareaList.get(rd.nextInt(cnt));
		}
		length = pacityId.size();
		for(int i=0;i<length;i++)
		{
			tempareaList.add((Area)areaService.get(pacityId.get(i).trim()));
		}
		String parArea = ((Area)areaService.get(pacityId.get(0).trim())).getParent();
		tempareaList.add((Area)areaService.get(parArea));
//		添加类别
		length = regionsId.size();
		List<Region> tempregionList = new ArrayList<Region>();
		for(int i=0;i<length;i++)
		{
			tempregionList.add((Region)regionService.get(regionsId.get(i).trim()));
		}
		length = paregionsId.size();
		for(int i=0;i<length;i++)
		{
			tempregionList.add((Region)regionService.get(paregionsId.get(i).trim()));
		}
		tempregionList.add((Region)regionService.get(reregion.trim()));
		
		List<KeyWord> realKeyWord = new ArrayList<KeyWord>();
		for(int i=0;i<size;i++)
		{
			if(kwList.get(i).getSame()==0)
			{
				compare = conTemp.replaceAll(kwList.get(i).getName(), "<A target=\"_blank\" href=\""+kwList.get(i).getUrl()+"\">"+kwList.get(i).getName()+"</A>");
				if(!compare.equals(conTemp))
				{
					realKeyWord.add(kwList.get(i));
				}
//				conTemp = compare;
			}
		}
		List<KeyWord> lastKeyWord = new ArrayList<KeyWord>();
		for(int i=0;i<realKeyWord.size();i++)
		{
			int count = 0;
			for(int j=0;j<cityId.size();j++)
			{
				if(realKeyWord.get(i).getAreaId().equals(cityId.get(j).trim()))
				{
					count++;
				}
			}
			if(count!=0)
			{
				Scenery sc = new Scenery();
				lastKeyWord.add(realKeyWord.get(i));
				sc = (Scenery)sceneryService.get(realKeyWord.get(i).getId());
				if(sc!=null)
				{
					sceneryList.add(sc);
				}
			}
		}
		
		conTemp = "";
		compare = "";
		conTemp = content;
		for(int i=0;i<lastKeyWord.size();i++)
		{
				compare = conTemp.replaceAll(lastKeyWord.get(i).getName(), "<A target=\"_blank\" href=\""+lastKeyWord.get(i).getUrl()+"\">"+lastKeyWord.get(i).getName()+"</A>");
				conTemp = compare;
			
		}
		line.setContentHtml(conTemp);
		size = conList.size();
		List<String> picFour = new ArrayList<String>();
		if(sceneryList.size()!=0)
		{
			List<SceneryType> stList = new ArrayList<SceneryType>();
			for(int i=0;i<sceneryList.size();i++)
			{
				List<SceneryType> tempList = new ArrayList<SceneryType>(sceneryService.getSceneryInfo(sceneryList.get(i).getSceneryId()).getTypes());
				for(int j=0;j<tempList.size();j++)
				{
					stList.add(tempList.get(j));
				}
			}
			line.setTypes(new HashSet<SceneryType>(stList));
			for(int i=0;i<size-1;i++)
			{
				List<String> picStrList = new ArrayList<String>();
				String cp = "";
				String ct = conList.get(i).getTrips();
				int lszie = sceneryList.size();
				for(int j=0;j<lszie;j++)
				{
					cp = ct.replace(sceneryList.get(j).getName(), "@@@");
					if(!cp.equals(ct))
					{
						List<SceneryPic> picList = sceneryPicService.getPicListByScenery(sceneryList.get(j).getSceneryId());
						if(picList!=null)
						{
							Random rnd = new Random();
							if(picList.size()==1)
							{
								String temp1 = picList.get(0).getMiniUrl()+"@"+picList.get(0).getBreviaryUrl()+"@"+sceneryList.get(j).getSceneryId()+"@"+sceneryList.get(j).getName();
								String temp2 = picList.get(0).getBreviaryUrl()+"@"+sceneryList.get(j).getSceneryId()+"@"+sceneryList.get(j).getName();
								if(picFour.size()<4)
								{
									picFour.add(temp1);
								}
								picStrList.add(temp2);
							}
							else
							{
								int l = picList.size();
								int o = rnd.nextInt(l);
								String temp1 = picList.get(o).getMiniUrl()+"@"+picList.get(o).getBreviaryUrl()+"@"+sceneryList.get(j).getSceneryId()+"@"+sceneryList.get(j).getName();
								o = rnd.nextInt(l);
								String temp2 = picList.get(o).getBreviaryUrl()+"@"+sceneryList.get(j).getSceneryId()+"@"+sceneryList.get(j).getName();
								if(picFour.size()<4)
								{
									picFour.add(temp1);
								}
								picStrList.add(temp2);
							}
						}
					}
					ct = cp;
				}
				if(picStrList.size()!=0)
				{
					for(int j=0;j<picStrList.size()-1;j++)
					{
						picUrl = picUrl+picStrList.get(j)+"#";
					}
					picUrl = picUrl+picStrList.get(picStrList.size()-1)+"#@#";
				}
				else
				{
					picUrl = picUrl+"#@#";
				}
			}
			List<String> picStrList = new ArrayList<String>();
			String cp = "";
			String ct = conList.get(size-1).getTrips();
			int lszie = sceneryList.size();
			for(int j=0;j<lszie;j++)
			{
				cp = ct.replace(sceneryList.get(j).getName(), "@@@");
				if(!cp.equals(ct))
				{
					List<SceneryPic> picList = sceneryPicService.getPicListByScenery(sceneryList.get(j).getSceneryId());
					if(picList!=null)
					{
						Random rnd = new Random();
						if(picList.size()==1)
						{
							
							String temp1 = picList.get(0).getMiniUrl()+"@"+picList.get(0).getBreviaryUrl()+"@"+sceneryList.get(j).getSceneryId()+"@"+sceneryList.get(j).getName();
							String temp2 = picList.get(0).getBreviaryUrl()+"@"+sceneryList.get(j).getSceneryId()+"@"+sceneryList.get(j).getName();
							if(picFour.size()<4)
							{
								picFour.add(temp1);
							}
							picStrList.add(temp2);
						}
						else
						{
							int l=picList.size();
							int o = rnd.nextInt(l);
							String temp1 = picList.get(o).getMiniUrl()+"@"+picList.get(o).getBreviaryUrl()+"@"+sceneryList.get(j).getSceneryId()+"@"+sceneryList.get(j).getName();
							o = rnd.nextInt(l);
							String temp2 = picList.get(o).getBreviaryUrl()+"@"+sceneryList.get(j).getSceneryId()+"@"+sceneryList.get(j).getName();
							if(picFour.size()<4)
							{
								picFour.add(temp1);
							}
							picStrList.add(temp2);
						}
					}
				}
				ct = cp;
			}
			if(picStrList.size()!=0)
			{
				for(int j=0;j<picStrList.size()-1;j++)
				{
					picUrl = picUrl+picStrList.get(j)+"#";
				}
				picUrl = picUrl+picStrList.get(picStrList.size()-1);
			}
			else
			{
				picUrl = picUrl+"#@#";
			}
			line.setScenerys(new HashSet<Scenery>(sceneryList));
			String picArea = "";
			if(picFour.size()!=0)
			{
				for(int i=0;i<picFour.size()-1;i++)
				{
					picArea = picArea+picFour.get(i)+"###";
				}
				picArea = picArea+picFour.get(picFour.size()-1);
			}
			else
			{
				picArea = "";
			}
			line.setPicArea(picArea);
			if(sceneryList.size()<4)
			{
				List<SceneryPic> picList = new ArrayList<SceneryPic>();
				picArea = "";
				picList = sceneryPicService.getPicListByArea(rdArea.getAreaId());
				if(picList!=null)
				{
					for(int k=0;k<picList.size();k++)
					{
							String temp1 = picList.get(k).getMiniUrl()+"@"+picList.get(k).getBreviaryUrl()+"@"+"1"+"@"+picList.get(0).getName();
							if(picFour.size()<4)
							{
								picFour.add(temp1);
							}
					}
					if(picFour.size()!=0)
					{
						for(int i=0;i<picFour.size()-1;i++)
						{
							picArea = picArea+picFour.get(i)+"###";
						}
						picArea = picArea+picFour.get(picFour.size()-1);
					}
					else
					{
						picArea = "";
					}
				}
				line.setPicArea(picArea);
			}
			line.setPicUrl(picUrl);
			
		}
		else
		{
			List<SceneryPic> picList = new ArrayList<SceneryPic>();
			String picArea = "";
			picList = sceneryPicService.getPicListByArea(rdArea.getAreaId());
			if(picList!=null)
			{
				for(int k=0;k<picList.size();k++)
				{
						String temp1 = picList.get(k).getMiniUrl()+"@"+picList.get(k).getBreviaryUrl()+"@"+"1"+"@"+picList.get(0).getName();
						if(picFour.size()<4)
						{
							picFour.add(temp1);
						}
				}
				
				if(picFour.size()!=0)
				{
					for(int i=0;i<picFour.size()-1;i++)
					{
						picArea = picArea+picFour.get(i)+"###";
					}
					picArea = picArea+picFour.get(picFour.size()-1);
				}
				else
				{
					picArea = "";
				}
			}
			line.setContentHtml(content);
			line.setPicArea(picArea);
			line.setPicUrl("");
		}
		
		
		line.setWriter(manager.getName());
		line.setAreas(new HashSet<Area>(tempareaList));
		line.setContent(content);
		
		line.setDelFlag(Global.LINE_KEEP);
		line.setFeature(feature);
		line.setFeeClude(feeClude);
		if(theme.equals("请选择一个主题")||theme.equals("无主题"))
		{
			line.setTheme("");
		}
		else
		{
			line.setTheme(theme);
		}
		line.setFeeUnclude(feeUnclude);
		line.setPurchase(purchase);
		line.setRegions(new HashSet<Region>(tempregionList));
		line.setSafe(safe);
		line.setRemark(remark);
		line.setSelfBuy(selfBuy);
		line.setTitle(title.trim());
		line.setSubTitle(subTitle.trim());
		line.setUser(user);
		Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
        String time=df.format(date);
        String time1 = df1.format(date);
        line.setCreateTime(DateUtils.parseDate(time1));
        line.setStrDate(time);
        lineService.merge(line);
        jsonString="{success:true}";
		return SUCCESS;
	}
	/**
	 * 隐藏行程信息
	 * @return
	 */
	public String delLine()
	{
		if(delData!=null)
		{
			if(delData.indexOf(",")<0)
			{
				Line line = new Line();
				line = (Line)lineService.get(delData.trim());
				line.setDelFlag(Global.LINE_JOBBER_DELETE);
				lineService.merge(line);
			}
			else
			{
				String[] id = delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					Line line = new Line();
					line = (Line)lineService.get(id[i].trim());
					line.setDelFlag(Global.LINE_JOBBER_DELETE);
					lineService.merge(line);
				}
				
			}
			jsonString = "{success:true}";
		}
		else
		{
			jsonString="{success:false,msg:'没有选择要删除的线路'}";
		}
		return SUCCESS;
	}
	public IAreaService getAreaService()
	{
		return areaService;
	}
	public void setAreaService(IAreaService areaService)
	{
		this.areaService = areaService;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getFeature()
	{
		return feature;
	}
	public void setFeature(String feature)
	{
		this.feature = feature;
	}
	public String getFeeClude()
	{
		return feeClude;
	}
	public void setFeeClude(String feeClude)
	{
		this.feeClude = feeClude;
	}
	public String getFeeUnclude()
	{
		return feeUnclude;
	}
	public void setFeeUnclude(String feeUnclude)
	{
		this.feeUnclude = feeUnclude;
	}
	public String getJsonString()
	{
		return jsonString;
	}
	public void setJsonString(String jsonString)
	{
		this.jsonString = jsonString;
	}
	public IKeyWordService getKeyWordService()
	{
		return keyWordService;
	}
	public void setKeyWordService(IKeyWordService keyWordService)
	{
		this.keyWordService = keyWordService;
	}
	public ILineService getLineService()
	{
		return lineService;
	}
	public void setLineService(ILineService lineService)
	{
		this.lineService = lineService;
	}
	public String getPurchase()
	{
		return purchase;
	}
	public void setPurchase(String purchase)
	{
		this.purchase = purchase;
	}
	public String getRecityList()
	{
		return recityList;
	}
	public void setRecityList(String recityList)
	{
		this.recityList = recityList;
	}
	public IRegionService getRegionService()
	{
		return regionService;
	}
	public void setRegionService(IRegionService regionService)
	{
		this.regionService = regionService;
	}
	public String getRemark()
	{
		return remark;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	public String getReregion()
	{
		return reregion;
	}
	public void setReregion(String reregion)
	{
		this.reregion = reregion;
	}
	public String getReregionList()
	{
		return reregionList;
	}
	public void setReregionList(String reregionList)
	{
		this.reregionList = reregionList;
	}
	public String getSafe()
	{
		return safe;
	}
	public void setSafe(String safe)
	{
		this.safe = safe;
	}
	public ISceneryPicService getSceneryPicService()
	{
		return sceneryPicService;
	}
	public void setSceneryPicService(ISceneryPicService sceneryPicService)
	{
		this.sceneryPicService = sceneryPicService;
	}
	public ISceneryService getSceneryService()
	{
		return sceneryService;
	}
	public void setSceneryService(ISceneryService sceneryService)
	{
		this.sceneryService = sceneryService;
	}
	public String getSelfBuy()
	{
		return selfBuy;
	}
	public void setSelfBuy(String selfBuy)
	{
		this.selfBuy = selfBuy;
	}
	public String getSubTitle()
	{
		return subTitle;
	}
	public void setSubTitle(String subTitle)
	{
		this.subTitle = subTitle;
	}
	public String getTheme()
	{
		return theme;
	}
	public void setTheme(String theme)
	{
		this.theme = theme;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public IUserService getUserService()
	{
		return userService;
	}
	public void setUserService(IUserService userService)
	{
		this.userService = userService;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getCopyData() {
		return copyData;
	}
	public void setCopyData(String copyData) {
		this.copyData = copyData;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getDir()
	{
		return dir;
	}
	public void setDir(String dir)
	{
		this.dir = dir;
	}
	public int getLimit()
	{
		return limit;
	}
	public void setLimit(int limit)
	{
		this.limit = limit;
	}
	public String getLineId()
	{
		return lineId;
	}
	public void setLineId(String lineId)
	{
		this.lineId = lineId;
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
	public String getDelData()
	{
		return delData;
	}
	public void setDelData(String delData)
	{
		this.delData = delData;
	}
	public List<Content> getReturnLineContent()
	{
		return returnLineContent;
	}
	public void setReturnLineContent(List<Content> returnLineContent)
	{
		this.returnLineContent = returnLineContent;
	}
	public String getContentHtml()
	{
		return contentHtml;
	}
	public void setContentHtml(String contentHtml)
	{
		this.contentHtml = contentHtml;
	}
	public int getSearchType()
	{
		return searchType;
	}
	public void setSearchType(int searchType)
	{
		this.searchType = searchType;
	}
}
