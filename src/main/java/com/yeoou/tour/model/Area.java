package com.yeoou.tour.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
*
* <p>
* 标题：地域实体
* </p>
* <p>
* 描述: 关于地域信息的内容
* </p>
* 
* @author kensin
* @version 1.0
* @created 2008-6-2
*/
public class Area extends Genericmodel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3173254096754338785L;
	private String areaId;
	private String name;
	private int orderBy;
	private String headName;
	private int depth;
	private String ip;
	private String parent;
	private int isChina;
	private int isZhixia;
	private int isXinzhen;
	private List<Area> areaList = new ArrayList<Area>();
	private Set<Area> children = new HashSet<Area>();
	private Set<Line> lines = new HashSet<Line>();
	private Set<Team> teams = new HashSet<Team>();

	public Set<Line> getLines()
	{
		return lines;
	}
	public void setLines(Set<Line> lines)
	{
		this.lines = lines;
	}
	public String getAreaId()
	{
		return areaId;
	}
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}
	public Set<Area> getChildren()
	{
		return children;
	}
	public void setChildren(Set<Area> children)
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

	/**
	 * 调试用，对整个树进行显示
	 * @param lefstr
	 * @param append
	 * @return
	 */
	public String toString(String lefstr,String append)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(append+name);
		if(children != null)
	    {
			sb.append(" ("+this.children.size()+")");
	    }
		sb.append("<br />");
		if(children != null)
	    {
			int i=this.children.size()-1;
			for(Iterator it=this.children.iterator();it.hasNext();)
			{   	
				if(i!=0)
				{
					sb.append(lefstr+((Area)it.next()).toString(lefstr + "│", "├"));
					i--;
				}
				else
				{
					sb.append(lefstr+((Area)it.next()).toString(lefstr + "│", "└"));
				}
			}
			
	    }
		return sb.toString();
	}
	public String getParent()
	{
		return parent;
	}
	public void setParent(String parent)
	{
		this.parent = parent;
	}
    @Override
	public int hashCode()
	{
		final int PRIME = 31;
		int result = super.hashCode();
		result = PRIME * result + ((areaId == null) ? 0 : areaId.hashCode());
		return result;
	}
    @Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Area other = (Area) obj;
		if (areaId == null)
		{
			if (other.areaId != null)
				return false;
		} else if (!areaId.equals(other.areaId))
			return false;
		return true;
	}
	public Set<Team> getTeams()
	{
		return teams;
	}
	public void setTeams(Set<Team> teams)
	{
		this.teams = teams;
	}
	public int getIsChina()
	{
		return isChina;
	}
	public void setIsChina(int isChina)
	{
		this.isChina = isChina;
	}
	public int getIsXinzhen()
	{
		return isXinzhen;
	}
	public void setIsXinzhen(int isXinzhen)
	{
		this.isXinzhen = isXinzhen;
	}
	public int getIsZhixia()
	{
		return isZhixia;
	}
	public void setIsZhixia(int isZhixia)
	{
		this.isZhixia = isZhixia;
	}
	public List<Area> getAreaList()
	{
		return areaList;
	}
	public void setAreaList(List<Area> areaList)
	{
		this.areaList = areaList;
	}
}
