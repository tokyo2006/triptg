package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yeoou.rbac.model.User;

/**
 * <p>
 * 标题：线路实体
 * </p>
 * <p>
 * 描述：线路信息
 * 说明：行程设置为：
 * </P>
 * @author kensin
 * @version 1.0
 * @created 2008-6-9
 */
public class Line extends Genericmodel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1288684819005630049L;
	private String lineId;
	private String title;
	private String subTitle;
	private String feeClude;
	private String feeUnclude;
	private String remark;
	private String safe;
	private long lineStar;
	private String content;
	private String contentHtml;
	private User user;
	private String feature;
	private String selfBuy;
    private int delFlag;
	private String purchase;
	private long hit;
	private String writer;
	private String strDate;
	private String theme;
	private Date createTime;
	private String picUrl;
	private String picArea;
	private List<Content> conList;
	private List<PicGroup> pgList;
	private Set<Area> areas = new HashSet<Area>();
	private Set<Region> regions = new HashSet<Region>();
	private Set<Scenery> scenerys = new HashSet<Scenery>();
	private Set<SceneryType> types = new HashSet<SceneryType>();
	public Set<Scenery> getScenerys()
	{
		return scenerys;
	}
	public void setScenerys(Set<Scenery> scenerys)
	{
		this.scenerys = scenerys;
	}
	public Set<Area> getAreas()
	{
		return areas;
	}
	public void setAreas(Set<Area> areas)
	{
		this.areas = areas;
	}
	public String getFeature()
	{
		return feature;
	}
	public void setFeature(String feature)
	{
		this.feature = feature;
	}

	public long getHit()
	{
		return hit;
	}
	public void setHit(long hit)
	{
		this.hit = hit;
	}
	public String getLineId()
	{
		return lineId;
	}
	public void setLineId(String lineId)
	{
		this.lineId = lineId;
	}
	public long getLineStar()
	{
		return lineStar;
	}
	public void setLineStar(long lineStar)
	{
		this.lineStar = lineStar;
	}

	public Set<Region> getRegions()
	{
		return regions;
	}
	public void setRegions(Set<Region> regions)
	{
		this.regions = regions;
	}
	public String getRemark()
	{
		return remark;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	public String getSafe()
	{
		return safe;
	}
	public void setSafe(String safe)
	{
		this.safe = safe;
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
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public String getPurchase()
	{
		return purchase;
	}
	public void setPurchase(String purchase)
	{
		this.purchase = purchase;
	}
	
	/**
	 * 获取打包数据并封装于Content类中
	 * @param content
	 * @return
	 */
	public List<Content> getContentPackeage(String content)
	{
		List<Content> contentList = new ArrayList<Content>();
		
		if(content != null)
		{
			if(content.indexOf("###")<0)
			{
				Content temp = new Content();
				String[] team = content.split("@@@");
				temp.setTitle(team[0]);
				temp.setEating(team[1]);
				temp.setLiving(team[2]);
				temp.setTrips(team[3]);
				contentList.add(temp);
			}
			else
			{
				String[] con = content.split("###");
				for(int i=0;i<con.length;i++)
				{
					Content temp = new Content();
					String[] team = con[i].split("@@@");
					temp.setTitle(team[0]);
					temp.setEating(team[1]);
					temp.setLiving(team[2]);
					temp.setTrips(team[3]);
					contentList.add(temp);
				}
			}
		}
		return contentList;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public int getDelFlag()
	{
		return delFlag;
	}
	public void setDelFlag(int delFlag)
	{
		this.delFlag = delFlag;
	}
	public String getWriter()
	{
		return writer;
	}
	public void setWriter(String writer)
	{
		this.writer = writer;
	}
	public Date getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
	public String getFeeClude() {
		return feeClude;
	}
	public void setFeeClude(String feeClude) {
		this.feeClude = feeClude;
	}
	public String getFeeUnclude() {
		return feeUnclude;
	}
	public void setFeeUnclude(String feeUnclude) {
		this.feeUnclude = feeUnclude;
	}
	public String getStrDate()
	{
		return strDate;
	}
	public void setStrDate(String strDate)
	{
		this.strDate = strDate;
	}
	public String getTheme()
	{
		return theme;
	}
	public void setTheme(String theme)
	{
		this.theme = theme;
	}
	public List<Content> getConList()
	{
		return conList;
	}
	public void setConList(List<Content> conList)
	{
		this.conList = conList;
	}
	public List<PicGroup> getPgList()
	{
		return pgList;
	}
	public void setPgList(List<PicGroup> pgList)
	{
		this.pgList = pgList;
	}
	public String getPicUrl()
	{
		return picUrl;
	}
	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}
	public String getContentHtml()
	{
		return contentHtml;
	}
	public void setContentHtml(String contentHtml)
	{
		this.contentHtml = contentHtml;
	}
	public String getPicArea()
	{
		return picArea;
	}
	public void setPicArea(String picArea)
	{
		this.picArea = picArea;
	}
	public Set<SceneryType> getTypes()
	{
		return types;
	}
	public void setTypes(Set<SceneryType> types)
	{
		this.types = types;
	}
    
}
