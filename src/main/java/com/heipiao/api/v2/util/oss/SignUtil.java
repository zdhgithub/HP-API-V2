package com.heipiao.api.v2.util.oss;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.SimpleTimeZone;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.heipiao.api.v2.domain.OSSSign;
import com.heipiao.api.v2.oss.OSSConfig;

/**
 * oss url签名授权
 * @author Chris
 *
 */
@Component
public class SignUtil {
	
	@Resource
	private OSSConfig ossConfig;

	/* Prototype of the Mac instance. */
	private static Mac macInstance;
	
	private static final String CHARSET = "UTF-8";
    
	private static final Object LOCK = new Object();
    
    /* Signature method. */
    private static final String ALGORITHM = "HmacSHA1";

	private static final Logger logger  = LoggerFactory.getLogger(SignUtil.class);

	public OSSSign generate(String bucket, String dir) throws Exception {
		String host = "https://" + bucket + "." + ossConfig.getEndpoint();
		long expireTime = 300;
		long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
		Date expiration = new Date(expireEndTime);
		String signStr = String.format(
				"{\"expiration\":\"%s\",\"conditions\":[[\"content-length-range\",0,%s],[\"starts-with\",\"$key\",\"%s\"]]}",
				getIso8601DateFormat().format(expiration), 1048576000, dir);
		byte[] binaryData = signStr.getBytes(CHARSET);
		String encodedPolicy = Base64.encodeBase64String(binaryData);
		
		String akSecretStr = ossConfig.getAccessKeySecret();
		
		byte[] akSecretBytes = akSecretStr.getBytes(CHARSET);
		byte[] policyBytes = encodedPolicy.getBytes(CHARSET);
		byte[] signBytes = sign(akSecretBytes, policyBytes);
		String postSignature = Base64.encodeBase64String(signBytes);
		
		OSSSign sign = new OSSSign(ossConfig.getAccessKeyId(), encodedPolicy, postSignature
				, dir, host, String.valueOf(expireEndTime / 1000));
		
		logger.debug("sign:" + sign.toString());

		return sign;
	}

	private static DateFormat getIso8601DateFormat() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
		df.setTimeZone(new SimpleTimeZone(0, "GMT"));
		return df;
	}

	private static byte[] sign(byte[] key, byte[] data) throws NoSuchAlgorithmException, InvalidKeyException {
		// Because Mac.getInstance(String) calls a synchronized method, it
		// could block on
		// invoked concurrently, so use prototype pattern to improve perf.
		if (macInstance == null) {
			synchronized (LOCK) {
//				if (macInstance == null) {
					macInstance = Mac.getInstance(ALGORITHM);
//				}
			}
		}

		Mac mac = null;
		try {
			mac = (Mac) macInstance.clone();
		} catch (CloneNotSupportedException e) {
			// If it is not clonable, create a new one.
			mac = Mac.getInstance(ALGORITHM);
		}
		
		mac.init(new SecretKeySpec(key, ALGORITHM));
		
		return mac.doFinal(data);
	}

}
