package com.yeoou.tour.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.FlexJsonUtils;
import com.yeoou.common.utils.SerialNoUtils;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.KeyWord;
import com.yeoou.tour.model.Region;
import com.yeoou.tour.model.Scenery;
import com.yeoou.tour.model.SceneryCount;
import com.yeoou.tour.model.SceneryPic;
import com.yeoou.tour.model.SceneryType;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.ICountSceneryService;
import com.yeoou.tour.service.IKeyWordService;
import com.yeoou.tour.service.IRegionService;
import com.yeoou.tour.service.ISceneryPicService;
import com.yeoou.tour.service.ISceneryService;
import com.yeoou.tour.service.ISceneryTypeService;
/**
 * <p>
 * Title: 后台景点相关操作模块
 * </p>
 * <p>
 * Description: 添加景点信息，更新景点信息，删除景点信息，<br/>
 * 添加景点图片，修改景点图片，生成景点关键字
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class SceneryAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private ISceneryService sceneryService;
	private ICountSceneryService countSceneryService;
	private String savePath;
	private File upload;
	private ISceneryPicService sceneryPicService;
	private String uploadFileName;
	private String uploadContentType;
	private IRegionService regionService;
	private IAreaService areaService;
	private ISceneryTypeService sceneryTypeService;
	private IKeyWordService keyWordService;
	private String dir;
	private String sort;
	private String kpData;
	private int flag = 0;
	private String jsonString;
	private List<SceneryPic> picList =  new ArrayList<SceneryPic>();
	private int start = 0;
	private int limit = 15;
	private String sceneryId = "";
	private String reRegion="";
	private String reArea = "";
	private String delData;
	private String name="";
	private int isTop = 0;
	private String typeId = "";
	private String ticketPrice = "";
	private String typeData ="";
	private String synopsis = "";
	private int level = 0;
	private String areaId = "";
	private String firstLetter = "";
	private String content = "";
	private String subTopic = "";
	private String node = "";
	private Scenery scenery;
	private String picUrl =  "";
	private String pictureId = "";
	private final String url = "http://www.tutu6.com/scenery/sceneryDetail/";

	/**
	 * 获取景点相关图片信息
	 * @return
	 */
	public String getSenceryPic()
	{
		picList = sceneryPicService.getPicListByScenery(sceneryId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,picList:");
		sb.append(jsonTool.getJsonString(picList, null, null, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
//	public String tempPic()
//	{
//		List<SceneryPic> picList = sceneryPicService.getAllPic();
//		int size = picList.size();
//		for(int i=0;i<size;i++)
//		{
//			Scenery sc = new Scenery();
//			sc = sceneryService.getSceneryInfo(picList.get(i).getScenery().getSceneryId());
//			List<Area> areas = new ArrayList<Area>(sc.getAreas());
//			picList.get(i).setAreas(new HashSet<Area>(areas));
//			sceneryPicService.merge(picList.get(i));
//		}
//		return SUCCESS;
//	}
	/**
	 * <p>添加景点相关图片，生成图片规格：原始图像，缩略图像（300x300)，迷你图（150x150）</p>
	 */
	public String addPic()
	{
		String fileName = "";
		String brUrl = "";
		String miniUrl = "";
		String fileType = "";
		if(!pictureId.equals(""))
		{
			sceneryPicService.delPic(pictureId);
		}
		savePath = ServletActionContext.getRequest().getRealPath(savePath)+"\\"+"image"+"\\"+"scenery";
		
		if(upload!=null)
		{
			fileType = uploadFileName.substring(uploadFileName.indexOf(".")+1, uploadFileName.length());
			fileName = SerialNoUtils.getSerialNo()+"."+fileType;
			picUrl = "upload"+"/"+"image"+"/"+"scenery"+"/"+fileName;
			brUrl = "upload"+"/"+"image"+"/"+"scenery"+"/"+"bre"+fileName;
			miniUrl = "upload"+"/"+"image"+"/"+"scenery"+"/"+"mini"+fileName;
		}
		String breSavePath = savePath + "\\"+"bre"+fileName;
		String miniSaveUrl = savePath + "\\"+"mini"+fileName;
		SceneryPic pic = new SceneryPic();
		Scenery sc = new Scenery();
		sc = sceneryService.getSceneryInfo(sceneryId);
		List<Area> areas = new ArrayList<Area>(sc.getAreas());
		pic.setAreas(new HashSet<Area>(areas));
		sc = new Scenery();
		sc = (Scenery)sceneryService.get(sceneryId);
		pic.setScenery(sc);
		pic.setName(name);
		pic.setBreviaryUrl(brUrl);
		pic.setUrl(picUrl);
		pic.setMiniUrl(miniUrl);
		sceneryPicService.addPic(pic,upload,savePath,breSavePath,miniSaveUrl,fileName,fileType);
		jsonString = "{success:true,\"url\":\""+brUrl+"\","+"\"name\":\""+name+"\"}";
		return SUCCESS;
	}
	/**
	 * 删除景点图片
	 * @return
	 */
	public String delPic()
	{
		if(delData!=null)
		{
			if(delData.indexOf(",")<0)
			{
				sceneryPicService.delPic(delData.trim());
			}
			else
			{
				String[] id= this.delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					sceneryPicService.delPic(id[i].trim());
				}
			}
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 获取景点信息列表
	 * @return
	 */
	public String getSceneryInfo()
	{
		List<Scenery> scList = new ArrayList<Scenery>();
		scList = sceneryService.getSceneryByArea(areaId);
		int length = scList.size();
		if(length!=0)
		{
			StringBuilder sb = new StringBuilder("[");
			for(int i=0;i<length-1;i++)
			{
				sb.append("[");
				sb.append("\'");
				sb.append(scList.get(i).getName());
				sb.append("\'");
				sb.append(",");
				sb.append("\'");
				sb.append(scList.get(i).getSceneryId());
				sb.append("\'");
				sb.append("]");
				sb.append(",");
			}
			sb.append("[");
			sb.append("\'");
			sb.append(scList.get(length-1).getName());
			sb.append("\'");
			sb.append(",");
			sb.append("\'");
			sb.append(scList.get(length-1).getSceneryId());
			sb.append("\'");
			sb.append("]");
			sb.append("]");
			jsonString = sb.toString();
		}
		else
		{
			jsonString = "[]";
		}
		return SUCCESS;
	}
	/**
	 * 添加景点类别信息
	 * @return
	 */
	public String addSceneryRegion()
	{
		Scenery sc = new Scenery();
		sc = sceneryService.getSceneryInfo(sceneryId);
		Region  region = new Region();
		List<Region> rList = new ArrayList<Region>();
		region = (Region)regionService.get(node);
		if(sc.getRegions()!=null)
		{
			rList = new ArrayList<Region>(sc.getRegions());
			rList.add(region);
		}
		else
		{
			rList.add(region);
		}
		sc.setRegions(new HashSet<Region>(rList));
		sceneryService.merge(sc);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 更新景点类别信息
	 * @return
	 */
	public String updateSceneryRegion()
	{
		if(this.delData!=null)
		{
			if(delData.indexOf(",")<0)
			{
				Scenery sc = new Scenery();
				sc = sceneryService.getSceneryInfo(delData.trim());
				Region  region = new Region();
				List<Region> rList = new ArrayList<Region>();
				region = (Region)regionService.get(node);
				if(sc.getRegions()!=null)
				{
					rList = new ArrayList<Region>(sc.getRegions());
					int length = rList.size();
					for(int i=0;i<length;i++)
					{
						if(rList.get(i).getRegionId().equals(node))
						{
							rList.remove(rList.get(i));
							length--;
							i=0;
						}
					}
				}
				sc.setRegions(new HashSet<Region>(rList));
				sceneryService.merge(sc);
				jsonString = "{success:true}";
			}
			else
			{
				String[] id = delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					Scenery sc = new Scenery();
					sc = sceneryService.getSceneryInfo(id[i].trim());
					Region  region = new Region();
					List<Region> rList = new ArrayList<Region>();
					region = (Region)regionService.get(node);
					if(sc.getRegions()!=null)
					{
						rList = new ArrayList<Region>(sc.getRegions());
						int length = rList.size();
						for(int j=0;j<length;j++)
						{
							if(rList.get(j).getRegionId().equals(node))
							{
								rList.remove(rList.get(j));
								length--;
								j=0;
							}
						}
					}
					sc.setRegions(new HashSet<Region>(rList));
					sceneryService.merge(sc);
				}
				jsonString = "{success:true}";
			}
		}
		return SUCCESS;
	}
	/**
	 * 获取景点类别信息列表
	 * @return
	 */
	public String getSceneryTypeInfo()
	{
		List<SceneryType> scList = new ArrayList<SceneryType>();
		scList = sceneryTypeService.getSceneryTypeList(flag);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,scList:");
		sb.append(jsonTool.getJsonString(scList, null, new String[]{"class"}, null));
		sb.append("}");
		jsonString = sb.toString();
//		List<SceneryType> scList = new ArrayList<SceneryType>();
//		scList = sceneryTypeService.getSceneryTypeList(flag);
//    	int length = scList.size();
//    	if(length!=0)
//    	{
//    		StringBuilder sb = new StringBuilder("[");
//    		for(int i=0;i<length-1;i++)
//    		{
//    			sb.append("[");
//				sb.append("\'");
//				sb.append(scList.get(i).getName());
//				sb.append("\'");
//				sb.append(",");
//				sb.append("\'");
//				sb.append(scList.get(i).getTypeId());
//				sb.append("\'");
//				sb.append("]");
//				sb.append(",");
//    		}
//    		sb.append("[");
//			sb.append("\'");
//			sb.append(scList.get(length-1).getName());
//			sb.append("\'");
//			sb.append(",");
//			sb.append("\'");
//			sb.append(scList.get(length-1).getTypeId());
//			sb.append("\'");
//			sb.append("]");
//			sb.append("]");
//			jsonString = sb.toString();
//    	}
//    	else
//    	{
//    		jsonString = "[]";
//    	}
		return SUCCESS;
	}
	/**
	 * 后台显示景点列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAllScenery()
	{
		Page page = new Page();
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,totalCount:");
		page = sceneryService.getAllScenery(dir,sort,start,limit,name,areaId,node);
		if(page!=null)
		{
			List<Scenery> picList = (List<Scenery>)page.getResult();
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(picList, null, new String[]{"class"}, null));
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
	 * 获取单一景点信息
	 * @return
	 */
	public String getSingleScenery()
	{
		Scenery sc = new Scenery();
		sc = sceneryService.getSceneryInfo(sceneryId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,sc:");
		sb.append(jsonTool.getJsonString(sc, new String []{"areas","regions","types"}, new String[]{"class","areas.class","regions.class","types.class"}, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 删除景点信息（如有景点图片则不允许删除）
	 * @return
	 */
	public String delScenery()
	{
		if(delData!=null)
		{
			if(delData.indexOf(",")<0)
			{
				int result = sceneryPicService.getCountPicByScenery(delData.trim());
				if(result==0)
				{
					SceneryCount sct = new SceneryCount();
					sct = countSceneryService.getSceneryCountBySceneryId(delData.trim());
					if(sct!=null)
					{
						countSceneryService.removeById(sct.getCountId());
					}
					sceneryService.removeById(delData.trim());
				}
				else
				{
					jsonString = "{success:false,msg:'有图片关联无法删除'}";
					return SUCCESS;
				}
			}
			else
			{
				String[] id = delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					int result = sceneryPicService.getCountPicByScenery(id[i].trim());
					if(result==0)
					{
						SceneryCount sct = new SceneryCount();
						sct = countSceneryService.getSceneryCountBySceneryId(id[i].trim());
						if(sct!=null)
						{
							countSceneryService.removeById(sct.getCountId());
						}
						sceneryService.removeById(id[i].trim());
					}
					else
					{
						jsonString = "{success:false,msg:'有图片关联无法删除'}";
						return SUCCESS;
					}
				}
			}
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 添加景点信息，景点地域回溯到大洲
	 * @return
	 */
	public String addScenery()
	{
		List<Area> areaList = new ArrayList<Area>();
		List<String> parentIdList = new ArrayList<String>();
		int p=0;
		String tempParentId;
		Area tempA = new Area();
		Area tempParentA = new Area();
		Scenery sc = new Scenery();
		List<SceneryType> typeList = new ArrayList<SceneryType>();
		if(!typeData.equals(""))
		{
			if(typeData.indexOf(",")<0)
			{
				SceneryType st = new SceneryType();
				st = (SceneryType) sceneryTypeService.get(typeData.trim());
				typeList.add(st);
			}
			else
			{
				String[] id = typeData.split(",");
				for(int i=0;i<id.length;i++)
				{
					SceneryType st = new SceneryType();
					st = (SceneryType) sceneryTypeService.get(id[i].trim());
					typeList.add(st);
				}
			}
		}
		sc.setTypes(new HashSet<SceneryType>(typeList));
		sc.setFirstLetter(firstLetter.toUpperCase());
		sc.setIsTop(isTop);
		sc.setSubTopic(subTopic);
		sc.setLevel(level);
		sc.setName(name);
		sc.setSynopsis(synopsis);
		sc.setTicketPrice(ticketPrice);
//		if(!reRegion.equals("")){
//			if(reRegion.indexOf(",")>0){
//				String[] tempStr = reRegion.split(",");
//				for(int i=0;i<tempStr.length;i++){
//					tempR=(Region)regionService.get(tempStr[i]);
//					tempParentR = (Region)regionService.get(tempR.getParent());
//					while(tempParentR.getDepth()>1){
//						tempParentId = tempParentR.getRegionId();
//						for(int h=0;h<(parentIdList.size());h++){
//								if(parentIdList.get(h).equals(tempParentId)){
//									p = 1;
//									break;
//								}
//						}
//						for(int h=0;h<(tempStr.length);h++){
//							if(tempStr[h].equals(tempParentId)){
//								p = 1;
//								break;
//							}
//						}
//						if(p==0){
//							parentIdList.add(tempParentId);
//						}
//						tempParentR = (Region)regionService.get(tempParentR.getParent());
//						p=0;
//					}
//					regionList.add(tempR);
//				}
//				for(int i=0;i<parentIdList.size();i++){
//					tempR=(Region)regionService.get(parentIdList.get(i));
//					regionList.add(tempR);
//				}
////			}
//		}
//		sc.setRegions(new HashSet<Region>(regionList));
		parentIdList = new ArrayList<String>();
		if(!reArea.equals("")){
//			if(reArea.indexOf(",")<0){
				String[] tempStr = reArea.split(",");
				for(int i=0;i<tempStr.length;i++){
					tempA=(Area)areaService.get(tempStr[i]);
					tempParentA = (Area)areaService.get(tempA.getParent());
					while(tempParentA.getDepth()>0){
						tempParentId = tempParentA.getAreaId();
						for(int h=0;h<(parentIdList.size());h++){
								if(parentIdList.get(h).equals(tempParentId)){
									p = 1;
									break;
								}
						}
						for(int h=0;h<(tempStr.length);h++){
							if(tempStr[h].equals(tempParentId)){
								p = 1;
								break;
							}
						}
						if(p==0){
							parentIdList.add(tempParentId);
						}
						tempParentA = (Area)areaService.get(tempParentA.getParent());
						p=0;
					}
					areaList.add(tempA);
				}
				for(int i=0;i<parentIdList.size();i++){
					tempA=(Area)areaService.get(parentIdList.get(i));
					areaList.add(tempA);
				}
//			}
		}
		sc.setAreas(new HashSet<Area>(areaList));
		sceneryService.save(sc);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 更新景点详细介绍
	 * @return
	 */
	public String updateContent()
	{
		scenery = (Scenery)sceneryService.get(sceneryId);
		scenery.setContent(content);
		sceneryService.merge(scenery);
		jsonString = "修改成功！";
		return SUCCESS;
	}
	/**
	 * 获取景点详细介绍内容
	 * @return
	 */
	public String getSceContent()
	{
		scenery = (Scenery)sceneryService.get(sceneryId);
		return SUCCESS;
	}
	/**
	 * 更新景点信息
	 * @return
	 */
	public String updateScenery()
	{
//		List<Region> regionList = new ArrayList<Region>();
		List<Area> areaList = new ArrayList<Area>();
		List<String> parentIdList = new ArrayList<String>();
		int p=0;
//		Region tempR = new Region();
//		Region tempParentR = new Region();
		String tempParentId;
		Area tempA = new Area();
		Area tempParentA = new Area();
		Scenery sc = new Scenery();
		sc = (Scenery)sceneryService.get(sceneryId);
		List<SceneryType> typeList = new ArrayList<SceneryType>();
		if(!typeData.equals(""))
		{
			if(typeData.indexOf(",")<0)
			{
				SceneryType st = new SceneryType();
				st = (SceneryType) sceneryTypeService.get(typeData.trim());
				typeList.add(st);
			}
			else
			{
				String[] id = typeData.split(",");
				for(int i=0;i<id.length;i++)
				{
					SceneryType st = new SceneryType();
					st = (SceneryType) sceneryTypeService.get(id[i].trim());
					typeList.add(st);
				}
			}
		}
		sc.setTypes(new HashSet<SceneryType>(typeList));
		sc.setFirstLetter(firstLetter.toUpperCase());
		sc.setIsTop(isTop);
		sc.setLevel(level);
		sc.setName(name);
		sc.setSynopsis(synopsis);
		sc.setTicketPrice(ticketPrice);
		sc.setSubTopic(subTopic);
//		if(!reRegion.equals("")){
////			if(reRegion.indexOf(",")>0){
//				String[] tempStr = reRegion.split(",");
//				for(int i=0;i<tempStr.length;i++){
//					tempR=(Region)regionService.get(tempStr[i]);
//					tempParentR = (Region)regionService.get(tempR.getParent());
//					while(tempParentR.getDepth()>1){
//						tempParentId = tempParentR.getRegionId();
//						for(int h=0;h<(parentIdList.size());h++){
//								if(parentIdList.get(h).equals(tempParentId)){
//									p = 1;
//									break;
//								}
//						}
//						for(int h=0;h<(tempStr.length);h++){
//							if(tempStr[h].equals(tempParentId)){
//								p = 1;
//								break;
//							}
//						}
//						if(p==0){
//							parentIdList.add(tempParentId);
//						}
//						tempParentR = (Region)regionService.get(tempParentR.getParent());
//						p=0;
//					}
//					regionList.add(tempR);
//				}
//				for(int i=0;i<parentIdList.size();i++){
//					tempR=(Region)regionService.get(parentIdList.get(i));
//					regionList.add(tempR);
//				}
////			}
//		}
//		sc.setRegions(new HashSet<Region>(regionList));
		parentIdList = new ArrayList<String>();
		if(!reArea.equals("")){
//			if(reArea.indexOf(",")>0){
				String[] tempStr = reArea.split(",");
				for(int i=0;i<tempStr.length;i++){
					tempA=(Area)areaService.get(tempStr[i]);
					tempParentA = (Area)areaService.get(tempA.getParent());
					while(tempParentA.getDepth()>0){
						tempParentId = tempParentA.getAreaId();
						for(int h=0;h<(parentIdList.size());h++){
								if(parentIdList.get(h).equals(tempParentId)){
									p = 1;
									break;
								}
						}
						for(int h=0;h<(tempStr.length);h++){
							if(tempStr[h].equals(tempParentId)){
								p = 1;
								break;
							}
						}
						if(p==0){
							parentIdList.add(tempParentId);
						}
						tempParentA = (Area)areaService.get(tempParentA.getParent());
						p=0;
					}
					areaList.add(tempA);
				}
				for(int i=0;i<parentIdList.size();i++){
					tempA=(Area)areaService.get(parentIdList.get(i));
					areaList.add(tempA);
				}
//			}
		}
		sc.setAreas(new HashSet<Area>(areaList));
		sceneryService.merge(sc);
		jsonString = "{success:true}";
		return SUCCESS;
	}
//	public String batUpdateScenery()
//	{
//		List<Scenery> scList = (List<Scenery>)sceneryService.getAll();
//		int size = scList.size();
//		for(int j=0;j<size;j++)
//		{
//			List<String> parentIdList = new ArrayList<String>();
//			int p=0;
//			String tempStr ="";
//			String tempParentId;
//			Area tempA = new Area();
//			Area tempParentA = new Area();
//			Scenery sc = new Scenery();
//			sc = sceneryService.getSceneryInfo(scList.get(j).getSceneryId());
//			List<Area> areaList = new ArrayList<Area>();
//			List<Area> scareaList = new ArrayList<Area>(sc.getAreas());
//			for(int i=0;i<scareaList.size();i++)
//			{
//				if(scareaList.get(i).getIsChina()==1)
//				{
//					if(scareaList.get(i).getDepth()==4)
//					{
//						tempStr = scareaList.get(i).getAreaId();
//					}
//				}
//				else
//				{
//					if(scareaList.get(i).getDepth()==3)
//					{
//						tempStr = scareaList.get(i).getAreaId();
//					}
//				}
//			}
//			parentIdList = new ArrayList<String>();
//			System.out.println(sc.getName());
//						tempA=(Area)areaService.get(tempStr);
//						tempParentA = (Area)areaService.get(tempA.getParent());
//						while(tempParentA.getDepth()>0)
//						{
//							tempParentId = tempParentA.getAreaId();
//							for(int h=0;h<(parentIdList.size());h++)
//							{
//									if(parentIdList.get(h).equals(tempParentId))
//									{
//										p = 1;
//										break;
//									}
//							}
//							if(tempStr.equals(tempParentId))
//							{
//										p = 1;
//										break;
//							}
//							if(p==0)
//							{
//								parentIdList.add(tempParentId);
//							}
//							tempParentA = (Area)areaService.get(tempParentA.getParent());
//							p=0;
//							areaList.add(tempA);
//					    }
//					for(int i=0;i<parentIdList.size();i++)
//					{
//						tempA=(Area)areaService.get(parentIdList.get(i));
//						areaList.add(tempA);
//					}
//			sc.setAreas(new HashSet<Area>(areaList));
//			sceneryService.merge(sc);
//		}
//		return SUCCESS;
//	}
	/**
	 * 更新景点关键字（没有则生成关键字，有则更新关键字）
	 */
	public String addKeyWordList()
	{
//		List<Scenery> scList = new ArrayList<Scenery>();
//		scList =(List<Scenery>) sceneryService.getAll();
//		int length = scList.size();
//		for(int i=0;i<length;i++)
//		{
//			KeyWord kw = new KeyWord();
//			Scenery sc = new Scenery();
//			String areaId = "";
//			int size = 0;
//			sc = sceneryService.getSceneryInfo(scList.get(i).getSceneryId());
//			List<Area> areaList = new ArrayList<Area>(sc.getAreas());
//			size  = areaList.size();
//			for(int j=0;j<size;j++)
//			{
//				if(areaList.get(j).getIsChina()==1)
//				{
//					if(areaList.get(j).getDepth()==4)
//					{
//						areaId = areaList.get(j).getAreaId();
//					}
//					if(areaList.get(j).getName().equals("台湾"))
//					{
//						areaId = areaList.get(j).getAreaId();
//					}
//				}
//				else
//				{
//					if(areaList.get(j).getDepth()==2)
//					{
//						areaId = areaList.get(j).getAreaId();
//					}
//				}
//			}
//			kw = keyWordService.getKeyWordBySceneryId(scList.get(i).getSceneryId());
//			if(kw==null)
//			{
//				kw = new KeyWord();
//				kw.setId(scList.get(i).getSceneryId());
//				kw.setName(scList.get(i).getName());
//				kw.setAreaId(areaId);
//				kw.setUrl(url+scList.get(i).getSceneryId()+".html");
//				keyWordService.save(kw);
//			}
//			else
//			{
//				kw.setId(scList.get(i).getSceneryId());
//				kw.setName(scList.get(i).getName());
//				kw.setAreaId(areaId);
//				kw.setUrl(url+scList.get(i).getSceneryId()+".html");
//				keyWordService.merge(kw);
//			}
//		}
		if(kpData!=null)
		{
			if(kpData.indexOf(",")<0)
			{
				String areaId = "";
				int size = 0;
				Scenery sc = new Scenery();
				sc = sceneryService.getSceneryInfo(kpData.trim());
				List<Area> areaList = new ArrayList<Area>(sc.getAreas());
				size  = areaList.size();
				for(int j=0;j<size;j++)
				{
					/**
					 * 如果是国家则保存depth为4的地域标识（台湾保存为台湾地域标识）<br/>
					 * 如果是外国则保存深度为2的地域标识
					 */
					if(areaList.get(j).getIsChina()==1)
					{
						if(areaList.get(j).getDepth()==4)
						{
							areaId = areaList.get(j).getAreaId();
						}
						if(areaList.get(j).getName().equals("台湾"))
						{
							areaId = areaList.get(j).getAreaId();
							break;
						}
					}
					else
					{
						if(areaList.get(j).getDepth()==2)
						{
							areaId = areaList.get(j).getAreaId();
						}
					}
				}
				KeyWord kw = new KeyWord();
				kw = keyWordService.getKeyWordBySceneryId(sc.getSceneryId());
				if(kw==null)
				{
					kw = new KeyWord();
					kw.setId(sc.getSceneryId());
					kw.setAreaId(areaId);
					kw.setName(sc.getName());
					kw.setUrl(url+sc.getSceneryId()+".html");
					keyWordService.save(kw);
				}
				else
				{
					kw.setId(sc.getSceneryId());
					kw.setName(sc.getName());
					kw.setAreaId(areaId);
					kw.setUrl(url+sc.getSceneryId()+".html");
					keyWordService.merge(kw);
				}
			}
			else
			{
				String[] id = kpData.split(",");
				for(int i=0;i<id.length;i++)
				{
					String areaId = "";
					Scenery sc = new Scenery();
					int size = 0;
					sc = sceneryService.getSceneryInfo(id[i].trim());
					KeyWord kw = new KeyWord();
					List<Area> areaList = new ArrayList<Area>(sc.getAreas());
					size  = areaList.size();
					for(int j=0;j<size;j++)
					{
						if(areaList.get(j).getIsChina()==1)
						{
							if(areaList.get(j).getDepth()==4)
							{
								areaId = areaList.get(j).getAreaId();
							}
							if(areaList.get(j).getName().equals("台湾"))
							{
								areaId = areaList.get(j).getAreaId();
								break;
							}
						}
						else
						{
							if(areaList.get(j).getDepth()==2)
							{
								areaId = areaList.get(j).getAreaId();
							}
						}
					}
					kw = keyWordService.getKeyWordBySceneryId(sc.getSceneryId());
					if(kw==null)
					{
						kw = new KeyWord();
						kw.setAreaId(areaId);
						kw.setId(sc.getSceneryId());
						kw.setName(sc.getName());
						kw.setUrl(url+sc.getSceneryId()+".html");
						keyWordService.save(kw);
					}
					else
					{
						kw.setId(sc.getSceneryId());
						kw.setAreaId(areaId);
						kw.setName(sc.getName());
						kw.setUrl(url+sc.getSceneryId()+".html");
						keyWordService.merge(kw);
					}
				}
			}
			jsonString = "{success:true}";
		}
		else
		{
			jsonString="{success:false,msg:'没有选择要添加的关键字'}";
		}
		return SUCCESS;
	}
	public String getAreaId()
	{
		return areaId;
	}
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}
	public IKeyWordService getKeyWordService()
	{
		return keyWordService;
	}
	public void setKeyWordService(IKeyWordService keyWordService)
	{
		this.keyWordService = keyWordService;
	}
	public String getKpData()
	{
		return kpData;
	}
	public String getSubTopic()
	{
		return subTopic;
	}
	public void setSubTopic(String subTopic)
	{
		this.subTopic = subTopic;
	}
	public void setKpData(String kpData)
	{
		this.kpData = kpData;
	}
	public String getTypeData()
	{
		return typeData;
	}
	public void setTypeData(String typeData)
	{
		this.typeData = typeData;
	}
	public String getSavePath()
	{
		return savePath;
	}
	public void setSavePath(String savePath)
	{
		this.savePath = savePath;
	}
	public ISceneryPicService getSceneryPicService()
	{
		return sceneryPicService;
	}
	public void setSceneryPicService(ISceneryPicService sceneryPicService)
	{
		this.sceneryPicService = sceneryPicService;
	}
	public File getUpload()
	{
		return upload;
	}
	public void setUpload(File upload)
	{
		this.upload = upload;
	}
	public String getUploadContentType()
	{
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType)
	{
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName()
	{
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName)
	{
		this.uploadFileName = uploadFileName;
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
	public String getFirstLetter()
	{
		return firstLetter;
	}
	public void setFirstLetter(String firstLetter)
	{
		this.firstLetter = firstLetter;
	}

	public int getFlag()
	{
		return flag;
	}
	public void setFlag(int flag)
	{
		this.flag = flag;
	}
	public int getIsTop()
	{
		return isTop;
	}
	public void setIsTop(int isTop)
	{
		this.isTop = isTop;
	}
	public Scenery getScenery()
	{
		return scenery;
	}
	public void setScenery(Scenery scenery)
	{
		this.scenery = scenery;
	}
	public String getJsonString()
	{
		return jsonString;
	}
	public void setJsonString(String jsonString)
	{
		this.jsonString = jsonString;
	}
	public int getLevel()
	{
		return level;
	}
	public void setLevel(int level)
	{
		this.level = level;
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
	public String getReArea()
	{
		return reArea;
	}
	public void setReArea(String reArea)
	{
		this.reArea = reArea;
	}
	public IRegionService getRegionService()
	{
		return regionService;
	}
	public void setRegionService(IRegionService regionService)
	{
		this.regionService = regionService;
	}
	public String getReRegion()
	{
		return reRegion;
	}
	public void setReRegion(String reRegion)
	{
		this.reRegion = reRegion;
	}
	public String getSceneryId()
	{
		return sceneryId;
	}
	public void setSceneryId(String sceneryId)
	{
		this.sceneryId = sceneryId;
	}
	public ISceneryService getSceneryService()
	{
		return sceneryService;
	}
	public void setSceneryService(ISceneryService sceneryService)
	{
		this.sceneryService = sceneryService;
	}
	public ISceneryTypeService getSceneryTypeService()
	{
		return sceneryTypeService;
	}
	public void setSceneryTypeService(ISceneryTypeService sceneryTypeService)
	{
		this.sceneryTypeService = sceneryTypeService;
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
	public String getSynopsis()
	{
		return synopsis;
	}
	public void setSynopsis(String synopsis)
	{
		this.synopsis = synopsis;
	}

	public String getTicketPrice()
	{
		return ticketPrice;
	}
	public void setTicketPrice(String ticketPrice)
	{
		this.ticketPrice = ticketPrice;
	}
	public String getTypeId()
	{
		return typeId;
	}
	public void setTypeId(String typeId)
	{
		this.typeId = typeId;
	}
	public List<SceneryPic> getPicList()
	{
		return picList;
	}
	public void setPicList(List<SceneryPic> picList)
	{
		this.picList = picList;
	}
	public String getPicUrl()
	{
		return picUrl;
	}
	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}
	public String getNode()
	{
		return node;
	}
	public void setNode(String node)
	{
		this.node = node;
	}
	public String getPictureId()
	{
		return pictureId;
	}
	public void setPictureId(String pictureId)
	{
		this.pictureId = pictureId;
	}
	public ICountSceneryService getCountSceneryService()
	{
		return countSceneryService;
	}
	public void setCountSceneryService(ICountSceneryService countSceneryService)
	{
		this.countSceneryService = countSceneryService;
	}
}
