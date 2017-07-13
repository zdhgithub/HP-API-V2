package com.heipiao.api.v2.service;

import java.util.Map;

import com.heipiao.api.v2.domain.OSSSign;

public interface OSSService {

	/**
	 * @param bucket
	 * @param dir
	 * @return
	 * @throws Exception 
	 */
	//OSSSign generateSign(String bucket, String dir);
	Map<String, String>  getTokenBySign(String bucket, String dir)throws Exception;

}
