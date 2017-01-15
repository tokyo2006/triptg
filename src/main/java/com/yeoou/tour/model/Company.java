package com.yeoou.tour.model;

import java.util.HashSet;
import java.util.Set;


public class Company extends Genericmodel
{

	private static final long serialVersionUID = 1L;
	private String comId;
	private String name;
	private String desc;
	private String logoUrl;
	private String phone;
	private String manager;
	private String address;
	private String email;
	private String qq;
	private String webSite;
	private String msn;
	private int herfRegion;
	private Set<Area> areas = new HashSet<Area>(); 
	private Set<Area> pareas = new HashSet<Area>(); 
	private Set<Region> regions = new HashSet<Region>(); 
	private Set<Region> pregions = new HashSet<Region>(); 
	private String userId;
	private int check;
	private int themeStyle;
	public int getThemeStyle()
	{
		return themeStyle;
	}
	public void setThemeStyle(int themeStyle)
	{
		this.themeStyle = themeStyle;
	}
	public int getCheck()
	{
		return check;
	}
	public void setCheck(int check)
	{
		this.check = check;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public String getComId()
	{
		return comId;
	}
	public void setComId(String comId)
	{
		this.comId = comId;
	}
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getLogoUrl()
	{
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl)
	{
		this.logoUrl = logoUrl;
	}
	public String getManager()
	{
		return manager;
	}
	public void setManager(String manager)
	{
		this.manager = manager;
	}
	public String getMsn()
	{
		return msn;
	}
	public void setMsn(String msn)
	{
		this.msn = msn;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getQq()
	{
		return qq;
	}
	public void setQq(String qq)
	{
		this.qq = qq;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Set<Area> getAreas() {
		return areas;
	}
	public void setAreas(Set<Area> areas) {
		this.areas = areas;
	}
	public Set<Area> getPareas() {
		return pareas;
	}
	public void setPareas(Set<Area> pareas) {
		this.pareas = pareas;
	}
	public Set<Region> getPregions() {
		return pregions;
	}
	public void setPregions(Set<Region> pregions) {
		this.pregions = pregions;
	}
	public Set<Region> getRegions() {
		return regions;
	}
	public void setRegions(Set<Region> regions) {
		this.regions = regions;
	}
	public int getHerfRegion()
	{
		return herfRegion;
	}
	public void setHerfRegion(int herfRegion)
	{
		this.herfRegion = herfRegion;
	}
	public String getWebSite()
	{
		return webSite;
	}
	public void setWebSite(String webSite)
	{
		this.webSite = webSite;
	}

	
}
