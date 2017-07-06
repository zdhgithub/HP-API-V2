package com.heipiao.api.v2.constant;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author duzh
 * @date 2017年7月6日
 * @version 1.0
 */
public class RespMessage {

	private static final Logger logger  = LoggerFactory.getLogger(RespMessage.class);
	
	private static Map<Integer, String> respMsgMaps = new HashMap<Integer, String>();

	
	/**
	 * 初始化响应message;
	 * @throws Exception
	 */
	public static void initMap() throws Exception {
		URL url = new URL("file:" + RespMessage.class.getResource("").getPath()+"../../../../" + "resp.xml");
		System.out.println(url);
		SAXReader reader = new SAXReader();
		reader.setValidation(true);
		reader.setEncoding("UTF-8");
		Document doc = reader.read(url);
		logger.debug("url:{}",url);
		Element root = doc.getRootElement();
		List<?> elements = root.elements();
		for (Object obj : elements) {
			if (!(obj instanceof Object))
				continue;
			
			Element e = (Element) obj;
			respMsgMaps.put(Integer.valueOf(e.element("status").getTextTrim()), e.element("msg").getTextTrim());
		}
	}

	public static String getRespMsg(int key) {
		return respMsgMaps.get(key) == null ? "" : respMsgMaps.get(key);
	}

	
	public static void main(String[] args) {
		System.out.println(respMsgMaps);
	}
}
