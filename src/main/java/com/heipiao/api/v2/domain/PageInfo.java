package com.heipiao.api.v2.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页实体类，不需要做DB映射
 * 
 * @author Chris
 *
 * @param <T>
 */
@ApiModel(description = "分页实体")
public class PageInfo<T> {
	
	@ApiModelProperty(dataType = "int", example = "76933", value = "当前分页信息总记录数")
	private int count;

	@ApiModelProperty(dataType = "T.class", value = "实体类")
	private T body;

	public PageInfo(int count) {
		super();
		this.count = count;
	}

	public PageInfo(T body) {
		super();
		this.body = body;
	}

	public PageInfo(int count, T body) {
		super();
		this.count = count;
		this.body = body;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "PageInfo [count=" + count + ", body=" + body + "]";
	}

}
