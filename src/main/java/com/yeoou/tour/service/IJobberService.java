package com.yeoou.tour.service;

import java.io.Serializable;
import java.util.List;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.tour.model.Jobber;
/**
 * 批发商相关业务接口
 * @author kensin
 *
 */
public interface IJobberService extends EntityDao
{
	public Jobber getJobberByUserId(Serializable userId);
	public List<Jobber> getAllJobber();
}
