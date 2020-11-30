package example.demo.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Map.Entry;

/**
 * @Author Yangshuixiang'_'
 * @date 2019/10/18 10:49
 * @Email shuixiang.yang@tcl.com
 * 发起get/post请求
 */

public class HttpClientUtil {

	private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

	public static String doGet(String url) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		String result = "";
		try {
			// 通过址默认配置创建一个httpClient实例
			httpClient = HttpClients.createDefault();
			// 创建httpGet远程连接实例
			HttpGet httpGet = new HttpGet(url);
			// 设置请求头信息，鉴权
			//httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
			// 设置配置请求参数
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
					.setConnectionRequestTimeout(35000)// 请求超时时间
					.setSocketTimeout(60000)// 数据读取超时时间
					.build();
			// 为httpGet实例设置配置
			httpGet.setConfig(requestConfig);
			// 执行get请求得到返回对象
			response = httpClient.execute(httpGet);
			// 通过返回对象获取返回数据
			HttpEntity entity = response.getEntity();
			// 字符串
			result = EntityUtils.toString(entity,"UTF-8");
		} catch (Exception e) {
			log.error("httpClient执行get请求错误!{}",e.getMessage());
		} finally {
			// 关闭资源
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					log.error("httpResponse关闭错误!{}",e.getMessage());
				}
			}
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					log.error("httpClient关闭错误!{}",e.getMessage());
				}
			}
		}
		return result;
	}

	public static String doPost(String url, Map<String, Object> paramMap) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		String result = "";
		// 创建httpClient实例
		httpClient = HttpClients.createDefault();
		// 创建httpPost远程连接实例
		HttpPost httpPost = new HttpPost(url);
		// 配置请求参数实例
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
				.setConnectionRequestTimeout(35000)// 设置连接请求超时时间
				.setSocketTimeout(60000)// 设置读取数据连接超时时间
				.build();
		// 为httpPost实例设置配置
		httpPost.setConfig(requestConfig);
		// 设置请求头
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		// 封装post请求参数
		if (null != paramMap && paramMap.size() > 0) {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			// 通过map集成entrySet方法获取entity
			Set<Entry<String, Object>> entrySet = paramMap.entrySet();
			// 循环遍历，获取迭代器
			Iterator<Entry<String, Object>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> mapEntry = iterator.next();
				nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
			}

			// 为httpPost设置封装好的请求参数
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				log.error("httpPost设置封装好的请求参数错误!{}",e.getMessage());
			}
		}
		try {
			// httpClient对象执行post请求,并返回响应参数对象
			httpResponse = httpClient.execute(httpPost);
			// 从响应对象中获取响应内容
			HttpEntity entity = httpResponse.getEntity();

			result = EntityUtils.toString(entity);
		} catch (Exception e) {
			log.error("httpClient执行post请求错误!{}",e.getMessage());

		} finally {
			// 关闭资源
			if (null != httpResponse) {
				try {
					httpResponse.close();
				} catch (IOException e) {
					log.error("httpResponse关闭错误!{}",e.getMessage());
				}
			}
			if (null != httpClient) {
				try {
					httpClient.close();
				} catch (IOException e) {
					log.error("httpClient关闭错误!{}",e.getMessage());
				}
			}
		}
		return result;
	}

	public static String doPostJson(String url, Map<String, Object> headMap, String json) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//设置请求超时时间
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(10000)
				.setConnectionRequestTimeout(10000)
				.setSocketTimeout(10000)
				.build();

		CloseableHttpResponse response = null;
		String resultString = null;
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			//添加请求头
			if(headMap != null){
				Set<String> set = headMap.keySet();
				for(String key : set){
					httpPost.addHeader(key, headMap.get(key).toString());
				}
			}
			// 创建请求内容，发送json数据需要设置contentType
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			if(response == null){
				return null;
			}
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				resultString = EntityUtils.toString(response.getEntity(), "utf-8");
			}
			else{
				return null;
			}
		} catch (Exception e) {
			log.error("执行doPostJson方法错误!{}",e.getMessage());
		} finally {
			try {
				if(response != null){
					response.close();
				}
			} catch (IOException e) {
				log.error("response关闭错误!{}",e.getMessage());
			}
			log.debug(resultString);
			return resultString;
		}
	}
}
