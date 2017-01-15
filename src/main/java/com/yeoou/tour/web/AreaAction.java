package com.yeoou.tour.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import net.sf.json.JSONArray;
import com.yeoou.common.dao.support.Page;
import com.yeoou.common.utils.*;
import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.tour.model.Area;
import com.yeoou.tour.model.City;
import com.yeoou.tour.model.Continent;
import com.yeoou.tour.model.Nation;
import com.yeoou.tour.model.Province;
import com.yeoou.tour.model.TreeNode;
import com.yeoou.tour.service.IAreaService;
import com.yeoou.tour.service.ICityService;
import com.yeoou.tour.service.IContinentService;
import com.yeoou.tour.service.INationService;
import com.yeoou.tour.service.IProvinceService;
/**
 * <p>
 * Title: 后台地域系统操作模块
 * </p>
 * <p>
 * Description: 对地域，大洲，国家，省份，城市进行相关业务操作
 * 新增地域信息，修改地域信息，删除地域信息，更新大洲详细信息，国家相信信息，<br/>省份详细信息，城市详细信息
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2009-04-07
 */
public class AreaAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private IAreaService areaService;
	private ICityService cityService;
	private IProvinceService provinceService;
	private INationService nationService;
	private IContinentService continentService;
	private String parent;
	private String headName = "";
	private String ip = "";
	private int isChina;
	private String name = "";
	private int limit = 20;
	private int start;
	private int depth = 10;
	private String dir;
	private String sort;
	private String areaId = "";
	private String jsonString;
	private String cityId = "";
	private String provinceId = "";
	private String nationId = "";
	private String continentId = "";
	private String content = "";
	private String gloze = "";
	private String isTopStr = "";
	private String isZoneStr = "";
	private City city;
	private Province pro;
	private Nation nation;
	private Continent con;
	private String synopsis = "";
	private String node;
	private String delData;
	private String savePath;
	private File upload;
	private String uploadFileName;
	private String mapUrl;
	private String mapTopic;
	
	public String getMapTopic()
	{
		return mapTopic;
	}
	public void setMapTopic(String mapTopic)
	{
		this.mapTopic = mapTopic;
	}
    /**
     * 添加地域模块，添加地域的同时，添加好相应的大洲，国家，省份和城市
     * @return
     */
	public String addArea()
	{
		Area area = new Area();
		Area parea = new Area();
		parea = (Area) areaService.get(parent);
		area.setDepth(parea.getDepth()+1);
		area.setHeadName(headName);
		area.setIp(ip);
		area.setIsChina(isChina);
		area.setName(name);
		area.setParent(parent);
		areaService.addArea(area);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 上传国家，大洲的地图图片模块（与更新大洲，国家信息配合使用）
	 * @return
	 */
	public String uploadPic()
	{
		String fileName = "";
		String url = "";
		String fileType = "";
		savePath = ServletActionContext.getRequest().getRealPath(savePath)+"\\"+"image"+"\\"+"map";
		if(upload!=null)
		{
			fileType = uploadFileName.substring(uploadFileName.indexOf(".")+1, uploadFileName.length());
			fileName = SerialNoUtils.getSerialNo()+"."+fileType;
			url = "upload"+"/"+"image"+"/"+"map"+"/"+fileName;
		}
		areaService.addPic(upload,savePath,fileName);
		jsonString = "{success:true,\"mapUrl\":\""+url+"\"}";
		return SUCCESS;
	}
	/**
	 * 获取单一地域信息
	 * @return
	 */
	public String getSingleArea()
	{
		Area area = new Area();
		Area parea = new Area();
		area = (Area) areaService.get(areaId);
		parea = (Area)areaService.get(area.getParent());
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder();
		sb.append("{success:true,area:");
		sb.append(jsonTool.getJsonString(area, null, new String[]{"class"}, null));
		sb.append(",parea:");
		sb.append(jsonTool.getJsonString(parea, null, new String[]{"class"}, null));
		sb.append("}");
		jsonString = sb.toString();
		return SUCCESS;
	}
	/**
	 * 获取城市信息
	 * @return
	 */
	public String getSingleCity()
	{
		this.city =(City) cityService.get(cityId);
		return SUCCESS;
	}
	/**
	 * 获取省份信息
	 * @return
	 */
	public String getSingleProvince()
	{
		pro = (Province) provinceService.get(provinceId);
		return SUCCESS;
	}
	/**
	 * 获取国家信息
	 * @return
	 */
	public String getSingleNation()
	{
		nation = (Nation) nationService.get(nationId);
		return SUCCESS;
	}
	/**
	 * 获取大洲信息
	 * @return
	 */
	public String getSingleContinent()
	{
		con = (Continent)continentService.get(continentId);
		return SUCCESS;
	}
	/**
	 * 更新地域信息
	 * @return
	 */
	public String updateArea()
	{
		Area area = new Area();
		Area parea = new Area();
		area = (Area)areaService.get(areaId);
		parea = (Area) areaService.get(parent);
		area.setDepth(parea.getDepth()+1);
		area.setHeadName(headName);
		area.setIp(ip);
		area.setIsChina(isChina);
		area.setName(name);
		area.setParent(parent);
		areaService.merge(area);
		jsonString = "{success:true}";
		return SUCCESS;
	}
	/**
	 * 更新城市信息
	 * @return
	 */
	public String updateCity()
	{
		City city = new City();
		city = (City)cityService.get(cityId);
		city.setContent(content);
		city.setGloze(gloze);
		city.setMapUrl(mapUrl);
		if(isTopStr.equals("false")){city.setIsTop(0);}
		else city.setIsTop(1);
		if(isZoneStr.equals("false")){city.setIsZone(0);}
		else city.setIsZone(1);
		city.setSynopsis(synopsis);
		cityService.merge(city);
		jsonString = "修改数据成功！";
		return SUCCESS;
	}
	/**
	 * 更新省份信息
	 * @return
	 */
	public String updateProvince()
	{
		Province pro = new Province();
		pro = (Province)provinceService.get(provinceId);
		pro.setContent(content);
		pro.setGloze(gloze);
		pro.setSynopsis(synopsis);
		pro.setMapUrl(mapUrl);
		if(isTopStr.equals("false"))
		{
			pro.setIsTop(0);
		}
		else
		{
			pro.setIsTop(1);
		}
		provinceService.merge(pro);
		jsonString = "修改数据成功！";
		return SUCCESS;
	}
	/**
	 * 更新大洲信息
	 * @return
	 */
	public String updateContinent()
	{
		Continent con = new Continent();
		con = (Continent) continentService.get(continentId);
		con.setContent(content);
		con.setGloze(gloze);
		con.setMapTopic(mapTopic);
		con.setMapUrl(mapUrl);
		if(isTopStr.equals("false"))
		{
			con.setIsTop(0);
		}
		else
		{
			con.setIsTop(1);
		}
		con.setSynopsis(synopsis);
		continentService.merge(con);
		jsonString = "修改数据成功！";
		return SUCCESS;
	}
	/**
	 * 更新国家信息
	 * @return
	 */
	public String updateNation()
	{
		Nation nation = new Nation();
		nation = (Nation)nationService.get(nationId);
		nation.setContent(content);
		nation.setMapTopic(mapTopic);
		nation.setMapUrl(mapUrl);
		nation.setGloze(gloze);
		if(isTopStr.equals("false"))
		{
			nation.setIsTop(0);
		}
		else
		{
			nation.setIsTop(1);
		}
		nation.setSynopsis(synopsis);
		nationService.merge(nation);
		jsonString = "修改数据成功！";
		return SUCCESS;
	}
	/**
	 * 后台显示所有地域信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAllArea()
	{
		Page page = new Page();
		page = areaService.getAllArea(start, limit, dir, sort, name, depth,areaId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,totalCount:");
		if(page!=null)
		{
			List<Area> areaList = (List<Area>)page.getResult();
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(areaList, null, new String[]{"class"}, null));
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
	 * 后台显示所有城市信息
	 * @return
	 */
	public String getAllCity()
	{
		Page page = new Page();
		page = cityService.getAllCity(start, limit, dir, sort,name,areaId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,totalCount:");
		if(page!=null)
		{
			List<City> cityList = (List<City>)page.getResult();
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(cityList, null, new String[]{"class"}, null));
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
	 * 后台显示所有省份信息
	 * @return
	 */
	public String getAllProvince()
	{
		Page page = new Page();
		page = provinceService.getAllProvince(start, limit, dir, sort,name,areaId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,totalCount:");
		if(page!=null)
		{
			List<Province> proList = (List<Province>)page.getResult();
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(proList, null, new String[]{"class"}, null));
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
     * 显示所有国家信息
     * @return
     */
	public String getAllNation()
	{
		Page page = new Page();
		page = nationService.getAllNation(start,limit,dir,sort,name,areaId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,totalCount:");
		if(page!=null)
		{
			List<Nation> nationList = (List<Nation>)page.getResult();
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(nationList, null, new String[]{"class"}, null));
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
	 * 后台显示所有大洲信息
	 * @return
	 */
	public String getAllContinent()
	{
		Page page = new Page();
		page = continentService.getAllContinent(start, limit, dir, sort, name,areaId);
		FlexJsonUtils jsonTool = new FlexJsonUtils();
		StringBuilder sb = new StringBuilder("{success:true,totalCount:");
		if(page!=null)
		{
			List<Continent> conList = (List<Continent>)page.getResult();
			sb.append(page.getTotalCount());
			sb.append(",results:");
			sb.append(jsonTool.getJsonString(conList, null, new String[]{"class"}, null));
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
	 * 构件后台地域树形菜单显示
	 * @return
	 */
	public String getAreaChildrenByNode()
	{
		List<TreeNode> childlist = new ArrayList<TreeNode>();
		Area area = new Area();
		if(node.equals("-1"))
		{
			area = areaService.getAreaChildren("402880e51a5122c2011a5123cb160001");
			childlist = areaService.setAreaTreeNodes(area);
		}
		else
		{
			area = areaService.getAreaChildren(node.trim());
			childlist = areaService.setAreaTreeNodes(area);
		}
		
		JSONArray jsarray = JSONArray.fromObject(childlist);
		jsonString=jsarray.toString();
		return SUCCESS;
	}
	/**
	 *  构件后台地域树形菜单显示
	 * @return
	 */
	public String getAreaChildrenByNodeAndDepth()
	{
		List<TreeNode> childlist = new ArrayList<TreeNode>();
		Area area = new Area();
		if(node.equals("-1"))
		{
			area = areaService.getAreaChildren("402880e51a5122c2011a5123cb160001");
			childlist = areaService.setAreaTreeNodes(area,3);
		}
		else
		{
			area = areaService.getAreaChildren(node.trim());
			childlist = areaService.setAreaTreeNodes(area,3);
		}
		
		JSONArray jsarray = JSONArray.fromObject(childlist);
		jsonString=jsarray.toString();
		return SUCCESS;
	}
	/**
	 * 删除地域信息，删除地域信息的同时删除相关的大洲信息，国家信息，省份信息，城市信息
	 * @return
	 */
	public String delArea()
	{
		if(delData!=null)
		{
			if(delData.indexOf(",")<0)
			{
				
				List<Area> al = areaService.getChildren(delData.trim());
				if(al!=null)
				{
					jsonString="{success:false,msg:'此地点有关联地点不允许删除!'}";
					return SUCCESS;
				}
				else
				{
					areaService.delArea(delData.trim());
					jsonString="{success:true}";
				}
			}
			else
			{
				String[] id= this.delData.split(",");
				for(int i=0;i<id.length;i++)
				{
					List<Area> al = areaService.getChildren(id[i].trim());
					if(al!=null)
					{
						jsonString="{success:false,msg:'此地点有关联地点不允许删除!'}";
						return SUCCESS;
					}
					else
					{
						areaService.delArea(id[i].trim());
						jsonString="{success:true}";
					}
				}
			}
		}
		return SUCCESS;
	}
//	public String temp()
//	{
//		List<Area> areaList = new ArrayList<Area>();
//		areaList = (List<Area>)areaService.getAll();
//		int size = areaList.size();
//		for(int i=0;i<size;i++)
//		{
//			depth = areaList.get(i).getDepth();
//			if(depth==1)
//			{
//				Continent con = new Continent();
//				con.setArea(areaList.get(i));
//				continentService.save(con);
//			}
//			else if(depth == 2)
//			{
//				Nation nat = new Nation();
//				nat.setArea(areaList.get(i));
//				nationService.save(nat);
//			}
//			else if(depth == 3)
//			{
//				Province pro = new Province();
//				pro.setArea(areaList.get(i));
//				provinceService.save(pro);
//				
//			}
//			else if(depth == 4 || depth == 5)
//			{
//				City ci = new City();
//				ci.setArea(areaList.get(i));
//				cityService.save(ci);
//			}
//		}
//		return SUCCESS;
//	}
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
	public ICityService getCityService()
	{
		return cityService;
	}
	public void setCityService(ICityService cityService)
	{
		this.cityService = cityService;
	}
	public IContinentService getContinentService()
	{
		return continentService;
	}
	public void setContinentService(IContinentService continentService)
	{
		this.continentService = continentService;
	}
	public int getDepth()
	{
		return depth;
	}
	public void setDepth(int depth)
	{
		this.depth = depth;
	}
	public String getDir()
	{
		return dir;
	}
	public void setDir(String dir)
	{
		this.dir = dir;
	}
	public String getHeadName()
	{
		return headName;
	}
	public void setHeadName(String headName)
	{
		this.headName = headName;
	}
	public String getIp()
	{
		return ip;
	}
	public void setIp(String ip)
	{
		this.ip = ip;
	}
	public int getIsChina()
	{
		return isChina;
	}
	public void setIsChina(int isChina)
	{
		this.isChina = isChina;
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
	public INationService getNationService()
	{
		return nationService;
	}
	public void setNationService(INationService nationService)
	{
		this.nationService = nationService;
	}
	public String getParent()
	{
		return parent;
	}
	public void setParent(String parent)
	{
		this.parent = parent;
	}
	public IProvinceService getProvinceService()
	{
		return provinceService;
	}
	public void setProvinceService(IProvinceService provinceService)
	{
		this.provinceService = provinceService;
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
	public String getCityId()
	{
		return cityId;
	}
	public void setCityId(String cityId)
	{
		this.cityId = cityId;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getContinentId()
	{
		return continentId;
	}
	public void setContinentId(String continentId)
	{
		this.continentId = continentId;
	}
	public String getDelData()
	{
		return delData;
	}
	public void setDelData(String delData)
	{
		this.delData = delData;
	}
	public String getGloze()
	{
		return gloze;
	}
	public void setGloze(String gloze)
	{
		this.gloze = gloze;
	}

	public String getNationId()
	{
		return nationId;
	}
	public void setNationId(String nationId)
	{
		this.nationId = nationId;
	}
	public String getNode()
	{
		return node;
	}
	public void setNode(String node)
	{
		this.node = node;
	}
	public String getProvinceId()
	{
		return provinceId;
	}
	public void setProvinceId(String provinceId)
	{
		this.provinceId = provinceId;
	}
	public String getSynopsis()
	{
		return synopsis;
	}
	public void setSynopsis(String synopsis)
	{
		this.synopsis = synopsis;
	}
	public City getCity()
	{
		return city;
	}
	public void setCity(City city)
	{
		this.city = city;
	}
	public String getIsTopStr()
	{
		return isTopStr;
	}
	public void setIsTopStr(String isTopStr)
	{
		this.isTopStr = isTopStr;
	}
	public String getIsZoneStr()
	{
		return isZoneStr;
	}
	public void setIsZoneStr(String isZoneStr)
	{
		this.isZoneStr = isZoneStr;
	}
	public Continent getCon()
	{
		return con;
	}
	public void setCon(Continent con)
	{
		this.con = con;
	}
	public Nation getNation()
	{
		return nation;
	}
	public void setNation(Nation nation)
	{
		this.nation = nation;
	}
	public Province getPro()
	{
		return pro;
	}
	public void setPro(Province pro)
	{
		this.pro = pro;
	}
	public String getSavePath()
	{
		return savePath;
	}
	public void setSavePath(String savePath)
	{
		this.savePath = savePath;
	}
	public File getUpload()
	{
		return upload;
	}
	public void setUpload(File upload)
	{
		this.upload = upload;
	}
	public String getUploadFileName()
	{
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName)
	{
		this.uploadFileName = uploadFileName;
	}
	public String getMapUrl()
	{
		return mapUrl;
	}
	public void setMapUrl(String url)
	{
		this.mapUrl = url;
	}
}
