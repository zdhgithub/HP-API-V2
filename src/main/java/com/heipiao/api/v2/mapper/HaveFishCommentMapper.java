package com.heipiao.api.v2.mapper;



import org.apache.ibatis.annotations.Mapper;

import com.heipiao.api.v2.domain.HaveFishComment;

/**
 * 活动相关
 * 
 * @author Duh
 * @version 3.0
 * @date 2017.07.13
 *
 */
@Mapper
public interface HaveFishCommentMapper {

	void addHaveFishComment(HaveFishComment haveFishComment);

}
