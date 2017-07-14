package com.heipiao.api.v2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.heipiao.api.v2.domain.DeliveryAddress;

/**
 * 活动相关
 * 
 * @author Duh
 * @version 3.0
 * @date 2017.07.13
 *
 */
@Mapper
public interface DeliveryAddressMapper {

	
	/**
	 * 查询收货地址列表
	 */
	public List<DeliveryAddress> getDeliveryAddressList(@Param("uid") Integer uid);
	
	DeliveryAddress getDefaultByUid(@Param("uid") Integer uid,@Param("isDefault") Integer isDdfault);

    void addDeliveryAddress(DeliveryAddress deliveryAddress);
    
    void updateDeliveryAddress(DeliveryAddress deliveryAddress);
    
    void deleteDeliveryAddress(@Param("id")Integer id);
    
    DeliveryAddress getOneDeliveryAddress(@Param("id") Integer id);
	
}
