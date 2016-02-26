package com.mai.util;

import com.mai.framework.exception.BaseException;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.util.Auth;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommonUtil {
	private static final Logger logger = Logger.getLogger(CommonUtil.class);

	private static Auth auth = null;

	private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',

        'B', 'C', 'D', 'E', 'F' };

	private static char toHex(int nibble) {
		return hexDigit[(nibble & 0xF)];
	}

	public static boolean isNumeric(String str){
		if(str!=null){
			Pattern pattern = Pattern.compile("[0-9]*");
		    return pattern.matcher(str).matches(); 
		}else{
			return false;
		}
	 } 
	
	public static String[] array_unique(String[] a) {
        List<String> list = new LinkedList<String>();
        for(int i = 0; i < a.length; i++) {
            if(!list.contains(a[i])) {
                list.add(a[i]);
            }
        }
        return (String[])list.toArray(new String[list.size()]);
    }
	
	public static String arrayToStr(String[] a,boolean isstr){
		StringBuffer sb = new StringBuffer("");
		for(String _a : a){
			if(isstr){
				sb.append("'" + _a + "',");
			}else{
				sb.append(_a + ",");
			}
		}
		String rstr = sb.toString();
		if(!"".equals(rstr))
			return rstr.substring(0, sb.toString().length() - 1);
		else
			return rstr;
		
	}
	
	public static String in_array(String[] a,String b){
		if(a != null){
			String rb = null;
			for(String _a : a){
				if(_a.equals(b)){
					rb = b;
					break;
				}
			}
			return rb;
		}else{
			return null;
		}
	}
	
	public static String getIpAddr(HttpServletRequest request){  
        String ip = request.getHeader("X-Real-IP");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
        	ip = request.getHeader("X-Forwarded-For"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	ip = request.getRemoteAddr();
        }
        
        if(ip!=null){
        	if(ip.indexOf(",")>-1){
        		ip = ip.split(",")[0].trim();
        		int _tempend = ip.indexOf(":");
        		if(_tempend>-1){
        			ip = ip.substring(0, _tempend);
        		}
        	}
//        	if(isboolIp(ip)){
//            	return ip;
//            }
        }
        
        
        
        return ip;
    }
	
	public static Cookie getCookie(HttpServletRequest request,String cookiename){
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		if(cookies!=null)
		for(Cookie c : cookies){
			if(cookiename.equals(c.getName())){
				cookie = c;
				break;
			}
		}
		return cookie;
	}
	
	public static Map<String,String> getCookieValues(HttpServletRequest request,String cookiename){
		Cookie[] cookies = request.getCookies();
		Map<String,String> m = new HashMap<String,String>();
		if(cookies!=null)
		for(Cookie c : cookies){
			if(c.getName().startsWith(cookiename)){
				m.put(c.getName(), c.getValue());
			}
		}
		return m;
	}
	
	public static String getCookieValue(HttpServletRequest request,String cookiename){
		Cookie[] cookies = request.getCookies();
		String value = "";
		for(Cookie c : cookies){
			if(cookiename.equals(c.getName())){
				value = c.getValue();
				break;
			}
		}
		return value;
	}
	
	public static String chinaToUnicode(String str){
		int len = str.length();
        int bufLen = len * 2;
        if (bufLen < 0) {
            bufLen = Integer.MAX_VALUE;
        }
        StringBuffer outBuffer = new StringBuffer(bufLen);
        
	      for (int i = 0; i < str.length(); i++){  
	            int chr1 = (char) str.charAt(i);  
	            if(chr1>=19968 && chr1<=171941){//汉字范围 \u4e00-\u9fa5 (中文)  
	                outBuffer.append("\\u" + Integer.toHexString(chr1));
	            }else{  
	                outBuffer.append(str.charAt(i));
	            }  
	      }  
	        return outBuffer.toString();  
	    }
	
	//isSlash true 保留 theString中的"\"替换为"\\"
	public static String toUnicode(String theString,boolean isSlash){
		int len = theString.length();
        int bufLen = len * 2;
        if (bufLen < 0) {
            bufLen = Integer.MAX_VALUE;
        }
        StringBuffer outBuffer = new StringBuffer(bufLen);
        for (int x = 0; x < len; x++) {
            char aChar = theString.charAt(x);
            if ((aChar > 61) && (aChar < 127)) {
            	if(isSlash && aChar == '\\'){
            		 outBuffer.append('\\');
                     outBuffer.append('\\');
                     continue;
            	}
                outBuffer.append(aChar);
                continue;
            }
            switch (aChar) {
            case ' ':
                if (x == 0) outBuffer.append('\\');
                outBuffer.append(' ');
                break;
            case '\t':
                outBuffer.append('\\');
                outBuffer.append('t');
                break;
            case '\n':
                outBuffer.append('\\');
                outBuffer.append('n');
                break;
            case '\r':
                outBuffer.append('\\');
                outBuffer.append('r');
                break;
            case '\f':
                outBuffer.append('\\');
                outBuffer.append('f');
            	break;
            case '=': outBuffer.append(aChar);break;
            case ':': outBuffer.append(aChar);break;
            case '#': outBuffer.append(aChar);break;
            case '!':
                outBuffer.append(aChar);
                break;
            default:
                if ((aChar < 0x0020) || (aChar > 0x007e)) {
                    // 每个unicode有16位，每四位对应的16进制从高位保存到低位
                    outBuffer.append('\\');
                    outBuffer.append('u');
                    outBuffer.append(toHex((aChar >> 12) & 0xF));
                    outBuffer.append(toHex((aChar >> 8) & 0xF));
                    outBuffer.append(toHex((aChar >> 4) & 0xF));
                    outBuffer.append(toHex(aChar & 0xF));
                } else {
                    outBuffer.append(aChar);
                }
            }
        }
        return outBuffer.toString();  
    }
	
	private static MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
	
	static{
		connectionManager.getParams().setMaxTotalConnections(50);
		connectionManager.getParams().setConnectionTimeout(2000);
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(10);
		connectionManager.getParams().setSoTimeout(2000);
	}
	
	private static HttpClient client = new HttpClient(connectionManager);

	public static String post(String url,List<NameValuePair> params){
		PostMethod method = null;
		byte[] responseBody = null;
		String html= null;
		System.out.println(url);
		try {
		  method = new PostMethod(url);
		  method.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
		  method.addParameters(params.toArray(new NameValuePair[0]));
		  int statuscode = client.executeMethod(method);
//		  if(statuscode == HttpStatus.SC_OK){
		  responseBody = method.getResponseBody();
		  html = new String(responseBody);
//		  }
		} catch (HttpException e) {
		  e.printStackTrace();
		} catch (IOException e) {    
		  e.printStackTrace();
		} catch (Exception e){
		  e.printStackTrace();
		}finally{
		  method.releaseConnection();    
		}
		return html;
	}
	
	public static NameValuePair setNameValuePair(String key,Object value){
				if(!StringUtils.isBlank(key) && !StringUtils.isBlank(String.valueOf(value))){
					//URLEncoder.encode(String.valueOf(value), "UTF-8")
					return new NameValuePair(key,String.valueOf(value));
				}else{
					return new NameValuePair(key,null);
				}
	}

	public static String get(String url){
//		HttpClient client = null;   
		HttpMethod method = null;
		String data = "";
		try {
//		  client = new HttpClient(connectionManager);
//		  client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
//		  client.getHttpConnectionManager().getParams().setSoTimeout(10000);
		  method = new GetMethod(url);
		  int statuscode = client.executeMethod(method);
		  if(statuscode == HttpStatus.SC_OK){
			 data = method.getResponseBodyAsString();
		  }
		} catch (HttpException e) {    
		  e.printStackTrace();
		} catch (IOException e) {    
		  e.printStackTrace();
		} catch(Exception e){
		  e.printStackTrace();
		}finally{
		  method.releaseConnection();    
//		  client.getHttpConnectionManager().closeIdleConnections(0);
		}
		return data;
	}
	
	public static InputStream wgetForInputStream(String url){
//		HttpClient client = null;   
		HttpMethod method = null;
		InputStream is = null;
		try {
//		  client = new HttpClient(connectionManager);
//		  client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
//		  client.getHttpConnectionManager().getParams().setSoTimeout(10000);
		  method = new GetMethod(url);
		  int statuscode = client.executeMethod(method);
		  if(statuscode == HttpStatus.SC_OK){
			 is = method.getResponseBodyAsStream();
		  }
		} catch (HttpException e) {    
		  e.printStackTrace();
		} catch (IOException e) {    
		  e.printStackTrace();
		} catch(Exception e){
		  e.printStackTrace();
		}finally{
		  //method.releaseConnection();    
//		  client.getHttpConnectionManager().closeIdleConnections(0);
		}
		return is;
	}


	public static boolean isboolIp(String ipAddress)  
	{  
	       String ip = "([1-9]|[1-9]//d|1//d{2}|2[0-4]//d|25[0-5])(//.(//d|[1-9]//d|1//d{2}|2[0-4]//d|25[0-5])){3}";   
	       Pattern pattern = Pattern.compile(ip);   
	       Matcher matcher = pattern.matcher(ipAddress);   
	       return matcher.matches();   
	}

	public static String md5(String string){
		StringBuilder builder = new StringBuilder("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(string.getBytes());
			int i;
			byte b[] = md.digest();
			for(int offset=0;offset<b.length;offset++) {
				i = b[offset];
				if(i<0) i+= 256;
				if(i<16) builder.append("0");
				builder.append(Integer.toHexString(i));
			}
		}
		catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	/**
	 * 删除七牛文件
	 * @param key
	 * @return
	 * @throws BaseException
	 */
	public static boolean deleteQiniuFile(Auth dummyAuth, String bucket,String key)throws BaseException {
		BucketManager bucketManager = new BucketManager(dummyAuth);
		try {
			bucketManager.delete(bucket,key);
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

	public static Auth getQiniuAuth(String accessKey,String secretKey){
		auth = Auth.create(accessKey, secretKey);
		return auth;
	}

}
