package com.heipiao.api.v2.service.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heipiao.api.v2.domain.AccountBill;
import com.heipiao.api.v2.mapper.AccountBillMapper;
import com.heipiao.api.v2.service.AccountBillService;
import com.heipiao.api.v2.util.ExDateUtils;

/**
 * @author wzw
 * @date 2016年10月17日
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class AccountBillServiceImpl implements AccountBillService {

	@Resource
	private AccountBillMapper accountBillMapper;

	/* (non-Javadoc)
	 * @see cn.heipiao.api.service.AccountBillService#addPojo(cn.heipiao.api.pojo.AccountBill)
	 */
	@Override
	@Transactional(readOnly = false,rollbackFor = {Exception.class})
	public void addPojo(Long uid,String orderId,Integer inOut,Integer type,Integer subType,Double tradeFee,String desc) {
		AccountBill pojo = new AccountBill();
		pojo.setCreateTime(new Timestamp(ExDateUtils.getCalendar().getTimeInMillis()));
		pojo.setDesc(desc);
		pojo.setInOut(inOut);
		pojo.setOrderId(orderId);
		pojo.setType(type);
		pojo.setSubType(subType);
		pojo.setTradeFee(tradeFee);
		pojo.setUid(uid);
		accountBillMapper.insertPojo(pojo);
	}
	
}
