package com.yeoou.tour.service;

import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.common.dao.support.Page;
import com.yeoou.tour.model.Train;
/**
 * 火车票相关业务接口
 * @author kensin
 * @see IAirService
 */
public interface ITrainService extends EntityDao
{
	public int countTeamByTrain(String trainId);
	public List<Train> getTrainByUserId(String userId);
	public Page getAllTrain(String userId,String dir,String sort,int limit,int start,String name);
}
