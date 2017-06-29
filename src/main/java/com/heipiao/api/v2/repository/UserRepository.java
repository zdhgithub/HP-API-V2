package com.heipiao.api.v2.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.heipiao.api.v2.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * 审核
	 * @param uid
	 * @param status
	 * @param date
	 */
	@Modifying
	@Query(value = "update t_mp_alliance set f_status = ?2, f_audit_time = ?3 where f_uid = ?1 and f_status = 0", nativeQuery = true)
	Integer setStatus(int uid, int status, Date date);
	
}
