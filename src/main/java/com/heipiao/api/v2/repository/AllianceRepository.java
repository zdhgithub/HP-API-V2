package com.heipiao.api.v2.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.heipiao.api.v2.domain.Alliance;

@Repository
public interface AllianceRepository extends JpaRepository<Alliance, Integer> {
	
	/**
	 * 审核
	 * @param uid
	 * @param status
	 * @param date
	 */
	@Modifying
	@Query(value = "update t_mp_alliance set f_status = ?2, f_audit_time = ?3 where f_uid = ?1 and f_status = 0", nativeQuery = true)
	Integer setStatus(int uid, int status, Date date);

	/**
	 * 根据Uid查加盟商状态
	 * @param uid
	 * @return
	 */
	@Query(value = "select f_status from t_mp_alliance where f_uid = ?1", nativeQuery = true)
	Integer getStatusByUid(int uid);
	
	/**
	 * 根据Uid查加盟商
	 * @param uid
	 * @return
	 */
	Alliance getAllianceByUid(int uid);

}
