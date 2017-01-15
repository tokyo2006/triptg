package com.yeoou.tour.service;

import com.yeoou.common.dao.EntityDao;
import com.yeoou.tour.model.Company;
/**
 * 批发商公司信息相关业务接口
 * @author kensin
 *
 */
public interface ICompanyService extends EntityDao
{
	public Company getCompanyByUserId(String userId);
}
