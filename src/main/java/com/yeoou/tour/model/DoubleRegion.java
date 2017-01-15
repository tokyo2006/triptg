package com.yeoou.tour.model;

public class DoubleRegion extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private RegionLation leftregion;
	private RegionLation rightregion;
	public RegionLation getLeftregion()
	{
		return leftregion;
	}
	public void setLeftregion(RegionLation leftregion)
	{
		this.leftregion = leftregion;
	}
	public RegionLation getRightregion()
	{
		return rightregion;
	}
	public void setRightregion(RegionLation rightregion)
	{
		this.rightregion = rightregion;
	}

	
}
