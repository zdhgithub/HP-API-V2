package com.heipiao.api.v2.service;


import java.util.List;

import com.heipiao.api.v2.domain.DeliveryAddress;

/**
 * @author Duzh
 *
 */
public interface DeliveryAddressService {
	
	
	void addDeliveryAddress(DeliveryAddress deliveryAddress);
	
	void updateDeliveryAddress(DeliveryAddress deliveryAddress);
	
	List<DeliveryAddress> getDeliveryAddressByUid(Integer uid);
	
	void deleteDeliveryAddress(Integer id);
	
	void updateIsDefault(Integer id,Integer isDefault);
	
	DeliveryAddress getDeliveryAddressById(Integer id);
}
