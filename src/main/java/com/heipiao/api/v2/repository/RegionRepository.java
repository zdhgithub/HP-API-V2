package com.heipiao.api.v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heipiao.api.v2.domain.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
	
	public Region getRegionByRegionName(String name);
	
}
