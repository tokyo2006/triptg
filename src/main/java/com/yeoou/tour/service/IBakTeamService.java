package com.yeoou.tour.service;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.tour.model.BakTeam;
/**
 * 备份开班计划相关业务接口
 * @author kensin
 *
 */
public interface IBakTeamService extends EntityDao
{
	/**
	 * 获取备份开班计划信息
	 * @param teamId	开班标识
	 * @param shadowNum 唯一标识号
	 * @return	备份开班计划
	 */
	public BakTeam getBakTeamByTeamId(String teamId,String shadowNum);

}
