package com.heipiao.api.v2.mapper;

import org.springframework.stereotype.Service;

import com.heipiao.api.v2.domain.AccountBill;

/**
 * @author wzw
 * @date 2016年10月17日
 * @version 1.0
 */
@Service
public interface AccountBillMapper {

	void insertPojo(AccountBill pojo);
	
}
