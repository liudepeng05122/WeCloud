package com.navinfo.wecloud.base.util;

import java.security.MessageDigest;

/**
 * <p>Title      : TODO[应用名]_[基础]</p>
 * <p>Description: TODO[安全认证工具类]</p>
 * <p>Copyright  : Copyright (c) 2016</p>
 * <p>Company    : 北京四维图新科技股份有限公司</p>
 * <p>Department : Telematics业务部</p>
 *
 * @author : wangyam
 * @version : 1.0
 */
public class SecurityUtils {

	/**
	  * 
	  * <p>Discription: TODO[MD5加密]</p>
	  * @param string
	 * @return String
	  * @author       : wangyam
	  * @update       :2016/4/25
	 */
	public static String md5(String string){
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] bytes = string.getBytes();
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(bytes);
			byte[] updateBytes = messageDigest.digest();
			int len = updateBytes.length;
			char myChar[] = new char[len * 2];
			int k = 0;
			for (int i = 0; i < len; i++) {
				byte byte0 = updateBytes[i];
				myChar[k++] = hexDigits[byte0 >>> 4 & 0x0f];
				myChar[k++] = hexDigits[byte0 & 0x0f];
			}
			return new String(myChar);
		} catch (Exception e) {
			return string;
		}
	}
}
