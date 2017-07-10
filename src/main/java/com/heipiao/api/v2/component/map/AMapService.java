package com.heipiao.api.v2.component.map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.heipiao.api.v2.domain.Location;
import com.heipiao.api.v2.exception.BadRequestException;
import com.heipiao.api.v2.exception.ServiceException;
import com.heipiao.api.v2.util.HttpUtils;

/**
 * 高德地图服务
 * @author Chris
 *
 */
@Component()
public class AMapService {
	
	@Resource
	private HttpUtils httpUtils;
	
	@Value("${amap.key}")
	private String key;

	private static final String URL = "http://restapi.amap.com/v3/geocode/regeo?output=json&location=#{location}&key=#{key}&radius=1000&extensions=base";
	
	private static final Logger logger = LoggerFactory.getLogger(AMapService.class);
	
	/**
	 * 逆地理编码
	 * @param lng 经度
	 * @param lat 纬度
	 * @return
	 */
	public Location geocode_regeo(double lng, double lat) {
		try {
			StringBuilder position = new StringBuilder();
			position.append(String.valueOf(lng)).append(",").append(lat);
			String url = URL.replace("#{location}", position.toString()).replace("#{key}", key);
			logger.debug(url);
			String result =	httpUtils.execute(url, "get", null);
			logger.debug(result);
			
			JSONObject resultJson = JSONObject.parseObject(result);
			
			int status = resultJson.getInteger("status");
			int infocode = resultJson.getIntValue("infocode");
			if (status != 1 || infocode != 10000) {
				throw new BadRequestException(resultJson.getString("info"));
			}
			
			JSONObject regeocode = resultJson.getJSONObject("regeocode");
			JSONObject addressComponent = regeocode.getJSONObject("addressComponent");
			String province = addressComponent.getString("province");
			String city = addressComponent.getString("city");
			
			Location location = new Location();
			location.setProvince(province);
			location.setCity(city);
			return location;
		} catch (Exception e) {
			logger.error("调用高德地图接口异常", e);
			throw new ServiceException("调用高德地图接口异常");
		}
	}

}
