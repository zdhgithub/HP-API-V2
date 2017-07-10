package com.heipiao.api.v2.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.heipiao.api.v2.domain.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Integer> {
	
	/**
	 * 审核
	 * @param uid
	 * @param status
	 * @param date
	 */
	@Modifying
	@Query(value = "update t_business set f_status = ?2, f_audit_time = ?3 where f_uid = ?1 and f_status = 0", nativeQuery = true)
	Integer setStatus(int uid, int status, Date date);

	/**
	 * 根据Uid查舜微商状态
	 * @param uid
	 * @return
	 */
	@Query(value = "select f_status from t_business where f_uid = ?1", nativeQuery = true)
	Integer getStatusByUid(int uid);
	
	/**
	 * 根据Uid查舜微商
	 * @param uid
	 * @return
	 */
	Business getBusinessByUid(int uid);

}
