package com.yeoou.tour.service;

import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Team;
/**
 * 开班计划相关业务接口
 * @author kensin
 *
 */
public interface ITeamService extends EntityDao
{
	/**
	 * 获取开班计划详细信息包括相关集合信息
	 * @param teamId  开班计划标识
	 * @return
	 */
	public Team getTeam(String teamId);
	/**
	 * 获取截止日期大于今天的相同组别的开班计划
	 * @param groupId	组别标识
	 * @return			返回开班计划列表
	 */
	public List<Team> getTeamByGroupId(String groupId);
	/**
	 * 获取一定数量地域相关开班计划
	 * @param areaId  	地域标识
	 * @param num   	获取个数
	 * @return  		开班计划列表
	 */
	public List<Team> getContactList(String areaId,int num);
	/**
	 * 获取类别标识为regionId的开班计划数量
	 * @param regionId
	 * @return			返回开班计划数量
	 */
	public int getCountByRegionId(String regionId);
	/**
	 * 获取一定数量的推荐开班计划
	 * @param num	获取数量
	 * @return		开班计划列表
	 */
	public List<Team> getTeamByTuijian(int num);
	/**
	 * 获取相关地域已上架的开班计划列表
	 * @param areaId	地域标识
	 * @return			开班计划列表
	 */
	public List<Team> getTeamByArea(String areaId);
	/**
	 * 获取相关类别的开班计划（出境，国内，周边......)
	 * @param flag  	 类别标识
	 * @return			 开班计划列表
	 */
	public List<Team> getTeamByFlag(int flag);
	/**
	 * 孙康说把前台页面特价换成新品 2009年3月24日
	 * 获取出发地为areaId的相关类别开班计划（国内，出境，周边......)
	 * @param flag		类别标识
	 * @param areaId	地域标识
	 * @return			开班计划列表
	 */
	public List<Team> getTeamByTejia(int flag,String areaId);
	/**
	 * 前台显示相关开班计划并提供搜索功能
	 * @param day			天数
	 * @param dayFlag		按日期搜索方式
	 * @param areaId		出发城市
	 * @param sort			排序字段
	 * @param dir			排序方式
	 * @param regionId		类别标识
	 * @param flag			开班类别（出境，周边，国内......)
	 * @param keyWord		开班计划名称
	 * @param dayStart  	搜索日期区间（开始）
	 * @param dayEnd		搜索日期区间（结束）
	 * @param priceStart	搜索价格区间（开始）
	 * @param priceEnd		搜索价格区间（结束）
	 * @param youhui		优惠开班计划
	 * @param tuijian		推荐开班计划
	 * @param tejia			特价开班计划
	 * @param sceneryId		相关景点开班计划
	 * @return				返回显示结果
	 */
	public List<Team> getAllClientTeam(int day,int dayFlag,String areaId,String sort,String dir,String regionId,int flag,String keyWord,int dayStart,int dayEnd,int priceStart,int priceEnd,String youhui,int tuijian,int tejia, String sceneryId);
	/**
	 * 后台显示相关开班计划并提供搜索功能
	 * @param start			分页页数
	 * @param limit			一页包含页数
	 * @param sort			排序字段
	 * @param dir			排序方式
	 * @param beginDate		开始日期
	 * @param endDate		结束日期
	 * @param simSearch     简单搜索
	 * @param isNet         门户上架
	 * @param isNew         门户新品
	 * @param isJNew        批发商新品
	 * @param isSpe         门户特价
	 * @param isJSpe		批发商特价
	 * @param isCom			门户推荐
	 * @param isJCom		批发商推荐
	 * @param teamName		开班计划名称
	 * @param regionId      类别标识
	 * @param jobberType    无效参数
	 * @param userId		开班计划归属者
	 * @param jobberName    批发商名
	 * @param subTitle      短标题（暂用作批发商名称搜索）
	 * @return				返回显示结果
	 */
	public Page getAllTeam(int start,int limit,String sort,String dir,String beginDate,String endDate,int simSearch,String isNet,String isNew,String isJNew,String isSpe,String isJSpe,String isCom,String isJCom,String teamName,String regionId,boolean jobberType,String userId,String jobberName,String subTitle);
	/**
	 * 由点击量获取的一个开班计划列表
	 * @param groupId	组别标识
	 * @return			返回开班计划列表
	 */
	public List<Team> getTeamListByHit(String groupId);
}
