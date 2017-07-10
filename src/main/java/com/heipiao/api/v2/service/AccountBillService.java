package com.heipiao.api.v2.service;

/**
 * @author wzw
 * @date 2016年10月17日
 * @version 1.0
 */
public interface AccountBillService {

	/**
	 * 具体查看 AccountBill 类
	 * 
	 * 此方法为总账单记录，所有与交易有关联的记录都会记录到此，
	 * 
	 * 分别以type字段为区分
	 * 
	 * @param uid
	 * @param orderId
	 * @param inOut 1:进账，2：出账
	 * @param type
	 * @param subType 1:第三方，2:漂币，3:存鱼
	 * @param tradeFee
	 * @param desc
	 */
	void addPojo(Long uid, String orderId, Integer inOut, Integer type,Integer subType,Double tradeFee, String desc);
	
}
