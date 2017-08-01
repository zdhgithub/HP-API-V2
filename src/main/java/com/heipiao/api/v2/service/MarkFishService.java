package com.heipiao.api.v2.service;

import java.util.List;

import com.heipiao.api.v2.domain.MarkCode;
import com.heipiao.api.v2.domain.MarkFish;
import com.heipiao.api.v2.domain.MarkHaveFish333;

/**
 *标鱼
 * @author Duzh
 *
 */
public interface MarkFishService {
	
	List<MarkFish> getMarkFishList(Integer uid,Integer status);
	
	List<MarkHaveFish333> getMarkHaveFishList(Integer uid,Integer start);

	List<MarkCode> getMarkCode(Integer status);
	
	void addSiteMarkFish(MarkFish markFish);
	
	void updateSiteMarkFish(MarkFish markFish);
	
	void deleteMarkFishList(Integer id);
	
	List<MarkHaveFish333> getMarkHaveFishOfmarkNumList(String marknum,Integer start);
	
	void updateMarkFish(Integer uid,String updateMarkFish,Integer status);
	
	void updatemarkHaveFish(MarkHaveFish333 markHaveFish);
	
	void updateFishSiteBase(Integer uid);
	
}
