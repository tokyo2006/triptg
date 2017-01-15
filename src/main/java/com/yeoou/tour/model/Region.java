package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * <p>
 * 标题：地域实体
 * </p>
 * <p>
 * 描述：线路类别信息
 * 说明：关于flag为定值： 1：周边游 2：国内长线 3：出境游 4.自由行 5.国内自由行
 * </P>
 * @author kensin
 * @version 1.0
 * @created 2008-6-9
 */
public class Region extends Genericmodel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2981672403354662092L;
    private String regionId;
    private String name;
    private String parent;
    private int orderBy;
    private String headName;
    private List<Region> childrenList = new ArrayList<Region>();
    private List<DoubleRegion> doubleList = new ArrayList<DoubleRegion>();
    private int depth;
    private int flag;
    private Area area;
    private String fullName;
    private int lineCnt;
    private Set<Region> children = new HashSet<Region>();
	public Set<Region> getChildren()
	{
		return children;
	}
	public void setChildren(Set<Region> children)
	{
		this.children = children;
	}
	public int getDepth()
	{
		return depth;
	}
	public void setDepth(int depth)
	{
		this.depth = depth;
	}
	public int getFlag()
	{
		return flag;
	}
	public void setFlag(int flag)
	{
		this.flag = flag;
	}
	public String getHeadName()
	{
		return headName;
	}
	public void setHeadName(String headName)
	{
		this.headName = headName;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getOrderBy()
	{
		return orderBy;
	}
	public void setOrderBy(int orderBy)
	{
		this.orderBy = orderBy;
	}

	public String getRegionId()
	{
		return regionId;
	}
	public void setRegionId(String regionId)
	{
		this.regionId = regionId;
	}
	public Area getArea()
	{
		return area;
	}
	public void setArea(Area area)
	{
		this.area = area;
	}
	public String getFullName()
	{
		return fullName;
	}
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}
	public String getParent()
	{
		return parent;
	}
	public void setParent(String parent)
	{
		this.parent = parent;
	}
	public List<Region> getChildrenList()
	{
		return childrenList;
	}
	public void setChildrenList(List<Region> childrenList)
	{
		this.childrenList = childrenList;
	}
	public List<DoubleRegion> getDoubleList()
	{
		return doubleList;
	}
	public void setDoubleList(List<DoubleRegion> doubleList)
	{
		this.doubleList = doubleList;
	}
	public int getLineCnt()
	{
		return lineCnt;
	}
	public void setLineCnt(int lineCnt)
	{
		this.lineCnt = lineCnt;
	}
}
