package com.heipiao.api.v2.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heipiao.api.v2.domain.DeliveryAddress;

import com.heipiao.api.v2.mapper.DeliveryAddressMapper;
import com.heipiao.api.v2.service.DeliveryAddressService;



@Service
@Transactional(readOnly = true)
public class DeliveryAddressServiceImpl implements DeliveryAddressService{

	@Resource
	private DeliveryAddressMapper deliveryAddressMapper;
	
	@Override
	@Transactional(readOnly = false,rollbackFor = {Exception.class})
	public void addDeliveryAddress(DeliveryAddress deliveryAddress) {
		deliveryAddressMapper.addDeliveryAddress(deliveryAddress);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor = {Exception.class})
	public void updateDeliveryAddress(DeliveryAddress deliveryAddress) {
		deliveryAddressMapper.updateDeliveryAddress(deliveryAddress);
		
	}

	@Override
	public List<DeliveryAddress> getDeliveryAddressByUid(Integer uid) {
		List<DeliveryAddress> list = deliveryAddressMapper.getDeliveryAddressList(uid);
		return list;
	}

	@Override
	@Transactional(readOnly = false,rollbackFor = {Exception.class})
	public void deleteDeliveryAddress(Integer id) {
		deliveryAddressMapper.deleteDeliveryAddress(id);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor = {Exception.class})
	public void updateIsDefault(Integer id,Integer uid) {
		Integer isDefault = 1;
		DeliveryAddress defaultaddress = deliveryAddressMapper.getDefaultByUid(uid, isDefault);
		if(defaultaddress!=null){
			defaultaddress.setIsDefault(0);
			deliveryAddressMapper.updateDeliveryAddress(defaultaddress);
		}
		DeliveryAddress deliveryAddress = deliveryAddressMapper.getOneDeliveryAddress(id);
		deliveryAddress.setIsDefault(isDefault);
		deliveryAddressMapper.updateDeliveryAddress(deliveryAddress);
	}

	@Override
	public DeliveryAddress getDeliveryAddressById(Integer id) {
		
		return deliveryAddressMapper.getOneDeliveryAddress(id);
	}
	

}
