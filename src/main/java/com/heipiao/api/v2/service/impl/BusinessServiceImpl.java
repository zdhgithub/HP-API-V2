package com.heipiao.api.v2.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.heipiao.api.v2.domain.Business;
import com.heipiao.api.v2.domain.PageInfo;
import com.heipiao.api.v2.exception.NotFoundException;
import com.heipiao.api.v2.exception.PreconditionException;
import com.heipiao.api.v2.mapper.BusinessMapper;
import com.heipiao.api.v2.repository.BusinessRepository;
import com.heipiao.api.v2.service.BusinessService;
import com.heipiao.api.v2.util.ExDateUtils;

@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {
	
	@Resource
	private BusinessRepository businessRepo;
	
	@Resource
	private BusinessMapper businessMapper;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void addBusiness(Business business) {
		Business _business = businessRepo.getBusinessByUid(business.getUid());
		if (_business != null) {
			throw new PreconditionException("已提交加盟申请");
		}
		
		business.setStock(null);
		business.setStatus(0);
		business.setAuditTime(null);
		business.setApplyTime(ExDateUtils.getDate());
		businessRepo.save(business);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void Auditing(int uid, int status) {
		Date date = ExDateUtils.getDate();
		Integer result = businessRepo.setStatus(uid, status, date);
		if (result == null || result.intValue() == 0) {
			throw new PreconditionException("舜微商不存在或已经审核过了");
		}
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, readOnly = false, rollbackFor = Exception.class)
	public void updateBusiness(int uid, Business business) {
		Business _business = getBusinessByUid(uid);
		
		if (_business.getStatus() != 1) {
			throw new PreconditionException("仅审核通过后才允许修改");
		}
		
		_business.setName(business.getName());
		_business.setAddress(business.getAddress());
		_business.setLongitude(business.getLongitude());
		_business.setLatitude(business.getLatitude());
		_business.setDelivery(business.getDelivery());
		_business.setStock(business.getStock());
		businessRepo.save(_business);
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, readOnly = false, rollbackFor = Exception.class)
	public void updateBusinessForApply(int uid, Business business) {
		Business _business = getBusinessByUid(uid);
		
		if (_business.getStatus() != -1) {
			throw new PreconditionException("仅审核未通过时才允许重新申请加盟");
		}
		
		_business.setName(business.getName());
		_business.setAddress(business.getAddress());
		_business.setLongitude(business.getLongitude());
		_business.setLatitude(business.getLatitude());
		_business.setStock(null); // 重新申请加盟时清空可能会提交过来的库存信息
		_business.setStatus(0); // 重置为待审核状态
		_business.setApplyTime(ExDateUtils.getDate());
		businessRepo.save(_business);
	}

	@Override
	public Integer getStatusByUid(int uid) {
		Integer status = businessRepo.getStatusByUid(uid);
		
		return status;
	}

	@Override
	public PageInfo<List<Business>> getBusinessList(Date regBegin,Date regEnd,String address,int start,int size) {
		List<Business> list = businessMapper.getAllBusinessList(regBegin,regEnd,address,start,size);
		Integer totalCount =businessMapper.getBusinessCountForPage(regBegin, regEnd, address);
		
		PageInfo<List<Business>> pageInfo = new PageInfo<List<Business>>(totalCount, list);
		return pageInfo;
	}

	@Override
	public Business getBusinessByUid(int uid) {// 查部分字段
		Business business = businessRepo.getBusinessByUid(uid);
		if (business == null) {
			throw new NotFoundException("找不到指定的加盟商");
		}
		
		return business;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteBusinessById(int id) {
		businessRepo.delete(id);
	}

}
