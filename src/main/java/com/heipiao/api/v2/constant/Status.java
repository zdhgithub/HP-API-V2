/**
 * 
 */
package com.heipiao.api.v2.constant;

/**
 * 
 * @author duzh
 * @date 2017年7月6日
 * @version 1.0
 */

public interface Status {

	/**
	 * 代表成功，其他则代表失败
	 */
	public static final int success = 0;

	/**
	 * http状态，403，不予响应
	 */
	public static final int FORBIDDEN = 403;

	// ==================================== 1
	// =======================================
	/**
	 * 已点赞
	 */
	public static final int ALREADY_LIKE = 100; 
}
