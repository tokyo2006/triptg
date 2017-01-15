package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.model.Genericmodel;

public class WebSite extends Genericmodel
{

	private static final long serialVersionUID = 1L;
	private String siteId;
	private String siteName;
	private String logo;
	private String title;
	private String address;
	private String zbphone;
	private String gnphone;
	private String cjphone;
	private String zjphone;
	private String domainName;
	private String bannerzb;
	private String bannergn;
	private String bannercj;
	private String bannerzyx;
	private String bannerLogo;
	private String desc;
	private String smallLogo;
	private String keywords;
	private List<PhoneInfo> addList = new ArrayList<PhoneInfo>();
	private List<PhoneInfo> zbList = new ArrayList<PhoneInfo>();
	private List<PhoneInfo> gnList = new ArrayList<PhoneInfo>();
	private List<PhoneInfo> cjList = new ArrayList<PhoneInfo>();
	private Area area;
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public Area getArea()
	{
		return area;
	}
	public void setArea(Area area)
	{
		this.area = area;
	}
	public String getCjphone()
	{
		return cjphone;
	}
	public void setCjphone(String cjphone)
	{
		this.cjphone = cjphone;
	}
	public String getGnphone()
	{
		return gnphone;
	}
	public void setGnphone(String gnphone)
	{
		this.gnphone = gnphone;
	}
	public String getLogo()
	{
		return logo;
	}
	public void setLogo(String logo)
	{
		this.logo = logo;
	}
	public String getSiteId()
	{
		return siteId;
	}
	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}
	public String getSiteName()
	{
		return siteName;
	}
	public void setSiteName(String siteName)
	{
		this.siteName = siteName;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getZbphone()
	{
		return zbphone;
	}
	public void setZbphone(String zbphone)
	{
		this.zbphone = zbphone;
	}
	public String getZjphone()
	{
		return zjphone;
	}
	public void setZjphone(String zjphone)
	{
		this.zjphone = zjphone;
	}
	public static List<PhoneInfo> unpackagePhoneInfo(String content)
	{
		List<PhoneInfo> piList = new ArrayList<PhoneInfo>();
		if(content==null||content.equals(""))
		{
			return null;
		}
		if(content.indexOf("@@@")<0)
		{
			String[] temp = content.split("###");
			PhoneInfo pi = new PhoneInfo();
			pi.setRemark(temp[0].trim());
			pi.setPhone(temp[1].trim());
			piList.add(pi);
		}
		else
		{
			String[] temp2 = content.split("@@@");
			for(int i=0;i<temp2.length;i++)
			{
				String[] temp = temp2[i].split("###");
				PhoneInfo pi = new PhoneInfo();
				pi.setRemark(temp[0].trim());
				pi.setPhone(temp[1].trim());
				piList.add(pi);
			}
		}
		return piList;
	}
	public String getDomainName()
	{
		return domainName;
	}
	public void setDomainName(String domainName)
	{
		this.domainName = domainName;
	}
	public List<PhoneInfo> getAddList()
	{
		return addList;
	}
	public void setAddList(List<PhoneInfo> addList)
	{
		this.addList = addList;
	}
	public List<PhoneInfo> getCjList()
	{
		return cjList;
	}
	public void setCjList(List<PhoneInfo> cjList)
	{
		this.cjList = cjList;
	}
	public List<PhoneInfo> getGnList()
	{
		return gnList;
	}
	public void setGnList(List<PhoneInfo> gnList)
	{
		this.gnList = gnList;
	}
	public List<PhoneInfo> getZbList()
	{
		return zbList;
	}
	public void setZbList(List<PhoneInfo> zbList)
	{
		this.zbList = zbList;
	}
	public String getBannercj()
	{
		return bannercj;
	}
	public void setBannercj(String bannercj)
	{
		this.bannercj = bannercj;
	}
	public String getBannergn()
	{
		return bannergn;
	}
	public void setBannergn(String bannergn)
	{
		this.bannergn = bannergn;
	}
	public String getBannerzb()
	{
		return bannerzb;
	}
	public void setBannerzb(String bannerzb)
	{
		this.bannerzb = bannerzb;
	}
	public String getBannerzyx()
	{
		return bannerzyx;
	}
	public void setBannerzyx(String bannerzyx)
	{
		this.bannerzyx = bannerzyx;
	}
	public String getBannerLogo()
	{
		return bannerLogo;
	}
	public void setBannerLogo(String bannerLogo)
	{
		this.bannerLogo = bannerLogo;
	}
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public String getKeywords()
	{
		return keywords;
	}
	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}
	public String getSmallLogo()
	{
		return smallLogo;
	}
	public void setSmallLogo(String smallLogo)
	{
		this.smallLogo = smallLogo;
	}
}
