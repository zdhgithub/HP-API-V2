package com.heipiao.api.v2.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.heipiao.api.v2.domain.Alliance;
import com.heipiao.api.v2.exception.NotFoundException;
import com.heipiao.api.v2.exception.PreconditionException;
import com.heipiao.api.v2.mapper.AllianceMapper;
import com.heipiao.api.v2.repository.AllianceRepository;
import com.heipiao.api.v2.service.AllianceService;
import com.heipiao.api.v2.util.ExDateUtils;

@Service
@Transactional
public class AllianceServiceImpl implements AllianceService {
	
	@Resource
	private AllianceRepository allianceRepo;
	
	@Resource
	private AllianceMapper allianceMapper;

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void addAlliance(Alliance alliance) {
		Alliance _alliance = allianceRepo.getAllianceByUid(alliance.getUid());
		if (_alliance != null) {
			throw new PreconditionException("已提交加盟申请");
		}
		
		alliance.setStock(null);
		alliance.setStatus(0);
		alliance.setAuditTime(null);
		alliance.setApplyTime(ExDateUtils.getDate());
		allianceRepo.save(alliance);
	}

	@Override
	public List<Alliance> getTopAllianceList(int count, double longitude, double latitude) {
		List<Alliance> list = allianceMapper.getTopAllianceList(count, longitude, latitude);
		return list;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void Auditing(int uid, int status) {
		Date date = ExDateUtils.getDate();
		Integer result = allianceRepo.setStatus(uid, status, date);
		if (result == null || result.intValue() == 0) {
			throw new PreconditionException("加盟商不存在或已经审核过了");
		}
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, readOnly = false, rollbackFor = Exception.class)
	public void updateAlliance(int uid, Alliance alliance) {
		Alliance _alliance = getAllianceByUid(uid);
		
		if (_alliance.getStatus() != 1) {
			throw new PreconditionException("仅审核通过后才允许修改");
		}
		
		_alliance.setShopName(alliance.getShopName());
		_alliance.setName(alliance.getName());
		_alliance.setAddress(alliance.getAddress());
		_alliance.setLongitude(alliance.getLongitude());
		_alliance.setLatitude(alliance.getLatitude());
		_alliance.setStock(alliance.getStock());
		allianceRepo.save(_alliance);
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, readOnly = false, rollbackFor = Exception.class)
	public void updateAllianceForApply(int uid, Alliance alliance) {
		Alliance _alliance = getAllianceByUid(uid);
		
		if (_alliance.getStatus() != -1) {
			throw new PreconditionException("仅审核未通过时才允许重新申请加盟");
		}
		
		_alliance.setShopName(alliance.getShopName());
		_alliance.setName(alliance.getName());
		_alliance.setAddress(alliance.getAddress());
		_alliance.setLongitude(alliance.getLongitude());
		_alliance.setLatitude(alliance.getLatitude());
		_alliance.setStock(null); // 重新申请加盟时清空可能会提交过来的库存信息
		_alliance.setStatus(0); // 重置为待审核状态
		_alliance.setApplyTime(ExDateUtils.getDate());
		allianceRepo.save(_alliance);
	}

	@Override
	public Integer getStatusByUid(int uid) {
		Integer status = allianceRepo.getStatusByUid(uid);
		
		return status;
	}

	@Override
	public List<Alliance> getAllianceList() {
		return allianceMapper.getAllianceList();
	}

	@Override
	public Alliance getAllianceByUid(int uid) {// 查部分字段
		Alliance alliance = allianceRepo.getAllianceByUid(uid);
		if (alliance == null) {
			throw new NotFoundException("找不到指定的加盟商");
		}
		
		return alliance;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteAllianceById(int id) {
		allianceRepo.delete(id);
	}

}
