package com.mai.util;

import java.io.InputStream;
import java.util.Properties;

import com.mai.framework.exception.BaseException;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import org.apache.log4j.Logger;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.qiniu.util.Auth;

public class ConfigUtil {
	
	private static final Logger logger = Logger.getLogger(ConfigUtil.class);
	private static Properties properties;
	private static Auth auth = null;
	static {
		// 加载属性文件
		try {
			InputStream inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties");
			try {
				properties = new Properties();
				properties.load(inputStream);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				inputStream.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	public static String getUpToken(String bucket,String key){
		auth = Auth.create(ConfigUtil.getProperty("qiniu.accessKey"), ConfigUtil.getProperty("qiniu.secretKey"));
		if(StringUtils.isBlank(key)){
			return auth.uploadToken(ConfigUtil.getProperty(bucket));
		}else{
			return auth.uploadToken(ConfigUtil.getProperty(bucket),key);
		}
	}

	public static Auth getQiniuAuth(String accessKey,String secretKey){
		auth = Auth.create(accessKey, secretKey);
		return auth;
	}

	/**
	 * 删除七牛文件，临时方法，不要在Controller中使用
	 * @param key
	 * @return
	 * @throws BaseException
	 */
	public static boolean deleteTestQiniuFile(String bucket,String key)throws BaseException {
		Auth dummyAuth = Auth.create(ConfigUtil.getProperty("qiniu.accessKey"), ConfigUtil.getProperty("qiniu.secretKey"));
		BucketManager bucketManager = new BucketManager(dummyAuth);
		try {
			bucketManager.delete(ConfigUtil.getProperty(bucket),key);
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时简单状态信息
			logger.error(r.toString());
			try {
				// 响应的文本信息
				logger.error(r.bodyString());
			} catch (QiniuException e1) {
				//ignore
			}
			throw new BaseException(e);
		}
		return true;
	}

	public static String getCardPhotoDomain(){
		return  ConfigUtil.getProperty("qiniu.qiniuDomainURL.card.app.photo");
	}

	public static String getCardAudioDomain(){
		return  ConfigUtil.getProperty("qiniu.qiniuDomainURL.card.app.audio");
	}

	public static String getPicdomain() {
		return  ConfigUtil.getProperty("qiniu.qiniuDomainURL.APP");
	}

	public static String getThum() {
		return ConfigUtil.getProperty("qiniu.ThumbnailParam");
	}

	public static String getthumLogo(){
		return ConfigUtil.getProperty("qiniu.ThumbnailParam.logo");
	}

	public static String getSystemDefaultIcon(){
		return ConfigUtil.getProperty("qiniu.sys.default.icon");
	}

	public static String getThumbnailParam(long filesize){
		long kb = 1024;
		if(filesize < kb*100){
			return ConfigUtil.getProperty("qiniu.ThumbnailParam");
		}else if(filesize < kb*200){
			return ConfigUtil.getProperty("qiniu.ThumbnailParam.200k");
		}else if(filesize < kb*500){
			return ConfigUtil.getProperty("qiniu.ThumbnailParam.500k");
		}else if(filesize < kb*1000){
			return ConfigUtil.getProperty("qiniu.ThumbnailParam.1000k");
		}else {
			return ConfigUtil.getProperty("qiniu.ThumbnailParam.2000k");
		}
	}

	public static String[] getTypeImages(){
		String namess = ConfigUtil.getProperty("category.image.names");
		if(namess!=null){
			String[] imageNames = namess.split(",");
			if(imageNames!=null && imageNames.length > 0){
				String[] imageURLs = new String[imageNames.length];
				for(int i=0;i<imageNames.length;i++){
					imageURLs[i] = ConfigUtil.getProperty("qiniu.qiniuDomainURL.static")+"/"+imageNames[i];
				}
				return imageURLs;
			}else{
				return new String[0];
			}
		}else{
			return new String[0];
		}
	}
}
