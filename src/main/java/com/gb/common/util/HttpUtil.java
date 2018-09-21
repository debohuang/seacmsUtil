package com.gb.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;


public final class HttpUtil {
	
	private HttpUtil() {}
	
	public static String get(String url) {
		return delegate.get(url);
	}
	
	public static String get(String url, Map<String, String> queryParas) {
		return delegate.get(url, queryParas);
	}
	
	public static String post(String url, String data) {
		return delegate.post(url, data);
	}
	
	public static String post(String url, String data, Map<String, String> header) {
		return delegate.post(url, data, header);
	}
	
	public static Map<String,String> bssPost(String url, String data, Map<String, String> header) {
		return delegate.bssPost(url, data, header);
	}
	
	public static String postSSL(String url, String data, String certPath, String certPass) {
		return delegate.postSSL(url, data, certPath, certPass);
	}
	
	/**
	 * http请求工具 委托
	 * 优先使用OkHttp 
	 * 最后使用JFinal HttpKit
	 */
	private interface HttpDelegate {
		String get(String url);
		String get(String url, Map<String, String> queryParas);
		
		String post(String url, String data);
		
		String post(String url, String data, Map<String, String> header);
		
		Map<String,String> bssPost(String url, String data, Map<String, String> header);
		
		String postSSL(String url, String data, String certPath, String certPass);
	}
	
	// http请求工具代理对象
	private static final HttpDelegate delegate;

	static {
		HttpDelegate delegateToUse = null;
		// okhttp3.OkHttpClient?
		if (ClassUtil.isPresent("okhttp3.OkHttpClient", HttpUtil.class.getClassLoader())) {
			delegateToUse = new OkHttp3Delegate();
		}
		// com.squareup.okhttp.OkHttpClient?
		else if (ClassUtil.isPresent("com.squareup.okhttp.OkHttpClient", HttpUtil.class.getClassLoader())) {
			delegateToUse = new OkHttpDelegate();
		}
		delegate = delegateToUse;
	}
	
	/**
	 * OkHttp2代理
	 */
	private static class OkHttpDelegate implements HttpDelegate {
		private final com.squareup.okhttp.OkHttpClient httpClient;
		private final com.squareup.okhttp.OkHttpClient httpsClient;
		
		Lock lock = new ReentrantLock();
		
		public OkHttpDelegate() {
			httpClient = new com.squareup.okhttp.OkHttpClient();
			// 分别设置Http的连接,写入,读取的超时时间
			httpClient.setConnectTimeout(10, TimeUnit.SECONDS);
			httpClient.setWriteTimeout(10, TimeUnit.SECONDS);
			httpClient.setReadTimeout(30, TimeUnit.SECONDS);
			
			httpsClient = httpClient.clone();
		}
		
		private static final com.squareup.okhttp.MediaType CONTENT_TYPE_FORM = 
				com.squareup.okhttp.MediaType.parse("application/x-www-form-urlencoded");
		
		private static final com.squareup.okhttp.MediaType CONTENT_TYPE_JSON = 
				com.squareup.okhttp.MediaType.parse("application/json");
		
		private String exec(com.squareup.okhttp.Request request) {
			try {
				com.squareup.okhttp.Response response = httpClient.newCall(request).execute();
				
				if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
				return response.body().string();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		private Map execResponse(com.squareup.okhttp.Request request) {
			Map<String,String> map=new HashMap<String,String>();
			com.squareup.okhttp.Response response=null;
			try {
				response = httpClient.newCall(request).execute();
				
				if (!response.isSuccessful()){
//					throw new RuntimeException("Unexpected code " + response);
				} 
				map.put("STATUS", Integer.toString(response.code()));
				map.put("BODY", response.body().string());
				return map;
			} catch (IOException e) {
//				throw new RuntimeException(e);
				if(response!=null){
					map.put("STATUS", Integer.toString(response.code()));
					try {
						map.put("BODY", response.body().string());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				System.out.println("###########"+response.code());
				return map;
			}
		}
		
		@Override
		public String get(String url) {
			com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder().url(url).get().build();
			return exec(request);
		}
		
		@Override
		public String get(String url, Map<String, String> queryParas) {
			com.squareup.okhttp.HttpUrl.Builder urlBuilder = com.squareup.okhttp.HttpUrl.parse(url).newBuilder();
			for (Entry<String, String> entry : queryParas.entrySet()) {
				urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
			}
			com.squareup.okhttp.HttpUrl httpUrl = urlBuilder.build();
			com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder().url(httpUrl).get().build();
			return exec(request);
		}
		
		@Override
		public String post(String url, String params) {
			com.squareup.okhttp.RequestBody body = com.squareup.okhttp.RequestBody.create(CONTENT_TYPE_FORM, params);
			com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()
				.url(url)
				.post(body)
				.build();
			return exec(request);
		}
		
		@Override
		public String postSSL(String url, String data, String certPath, String certPass) {
			com.squareup.okhttp.RequestBody body = com.squareup.okhttp.RequestBody.create(CONTENT_TYPE_FORM, data);
			com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()
				.url(url)
				.post(body)
				.build();
			
			InputStream inputStream = null;
			try {
				// 移动到最开始，certPath io异常unlock会报错
				lock.lock();
				
				KeyStore clientStore = KeyStore.getInstance("PKCS12");
				inputStream = new FileInputStream(certPath);
				clientStore.load(inputStream, certPass.toCharArray());
				
				KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
				kmf.init(clientStore, certPass.toCharArray());
				KeyManager[] kms = kmf.getKeyManagers();
				SSLContext sslContext = SSLContext.getInstance("TLSv1");
				
				sslContext.init(kms, null, new SecureRandom());
				
				httpsClient.setSslSocketFactory(sslContext.getSocketFactory());
				
				com.squareup.okhttp.Response response = httpsClient.newCall(request).execute();
				
				if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
				
				return response.body().string();
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				IOUtils.closeQuietly(inputStream);
				lock.unlock();
			}
		}

		@Override
		public String post(String url, String params, Map<String, String> header) {
			com.squareup.okhttp.RequestBody body = com.squareup.okhttp.RequestBody.create(CONTENT_TYPE_FORM, params);
			com.squareup.okhttp.Request.Builder builder = new com.squareup.okhttp.Request.Builder().url(url).post(body);
			for (Map.Entry<String, String> entry : header.entrySet()) {
				builder.addHeader(entry.getKey(), entry.getValue());
			}
			com.squareup.okhttp.Request request = builder.build();
			return exec(request);
		}

		@Override
		public Map<String, String> bssPost(String url, String params,
				Map<String, String> header) {
			
			com.squareup.okhttp.RequestBody body = com.squareup.okhttp.RequestBody.create(CONTENT_TYPE_JSON, params);
			com.squareup.okhttp.Request.Builder builder = new com.squareup.okhttp.Request.Builder().url(url).post(body);
			for (Map.Entry<String, String> entry : header.entrySet()) {
				builder.addHeader(entry.getKey(), entry.getValue());
			}
			com.squareup.okhttp.Request request = builder.build();
			return execResponse(request);
			
//			return null;
		}
	}
	
	/**
	 * OkHttp3代理
	 */
	private static class OkHttp3Delegate implements HttpDelegate {
		private okhttp3.OkHttpClient httpClient;
		
		public OkHttp3Delegate() {
			// 分别设置Http的连接,写入,读取的超时时间
			httpClient = new okhttp3.OkHttpClient().newBuilder()
					.connectTimeout(10, TimeUnit.SECONDS)
					.writeTimeout(10, TimeUnit.SECONDS)
					.readTimeout(30, TimeUnit.SECONDS)
					.build();
		}
		
		private static final okhttp3.MediaType CONTENT_TYPE_FORM = 
				okhttp3.MediaType.parse("application/x-www-form-urlencoded");
		
		private static final okhttp3.MediaType CONTENT_TYPE_JSON = 
				okhttp3.MediaType.parse("application/json");
		
		private String exec(okhttp3.Request request) {
			try {
				okhttp3.Response response = httpClient.newCall(request).execute();
				
				if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response.code());
				
				return response.body().string();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		private Map execResponse(okhttp3.Request request) {
			Map<String,String> map=new HashMap<String,String>();
			okhttp3.Response response=null;
			try {
				response = httpClient.newCall(request).execute();
				if (!response.isSuccessful()){
//				throw new RuntimeException("Unexpected code " + response.code());
				} 
				map.put("STATUS", Integer.toString(response.code()));
				map.put("BODY", response.body().string());
				return map;
			} catch (IOException e) {
//				throw new RuntimeException(e);
				if(response!=null){
					map.put("STATUS", Integer.toString(response.code()));
					try {
						map.put("BODY", response.body().string());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				return map;
			}
		}
		
		@Override
		public String get(String url) {
			okhttp3.Request request = new okhttp3.Request.Builder().url(url).get().build();
			return exec(request);
		}
		
		@Override
		public String get(String url, Map<String, String> queryParas) {
			okhttp3.HttpUrl.Builder urlBuilder = okhttp3.HttpUrl.parse(url).newBuilder();
			for (Entry<String, String> entry : queryParas.entrySet()) {
				urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
			}
			okhttp3.HttpUrl httpUrl = urlBuilder.build();
			okhttp3.Request request = new okhttp3.Request.Builder().url(httpUrl).get().build();
			return exec(request);
		}
		
		@Override
		public String post(String url, String params) {
			okhttp3.RequestBody body = okhttp3.RequestBody.create(CONTENT_TYPE_FORM, params);
			okhttp3.Request request = new okhttp3.Request.Builder()
				.url(url)
				.post(body)
				.build();
			return exec(request);
		}
		
		@SuppressWarnings("deprecation")
		@Override
		public String postSSL(String url, String data, String certPath, String certPass) {
			okhttp3.RequestBody body = okhttp3.RequestBody.create(CONTENT_TYPE_FORM, data);
			okhttp3.Request request = new okhttp3.Request.Builder()
				.url(url)
				.post(body)
				.build();
			
			InputStream inputStream = null;
			try {
				KeyStore clientStore = KeyStore.getInstance("PKCS12");
				inputStream = new FileInputStream(certPath);
				char[] passArray = certPass.toCharArray();
				clientStore.load(inputStream, passArray);
				
				KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
				kmf.init(clientStore, passArray);
				KeyManager[] kms = kmf.getKeyManagers();
				SSLContext sslContext = SSLContext.getInstance("TLSv1");
				
				sslContext.init(kms, null, new SecureRandom());
				
				okhttp3.OkHttpClient httpsClient = new okhttp3.OkHttpClient()
						.newBuilder()
						.connectTimeout(10, TimeUnit.SECONDS)
						.writeTimeout(10, TimeUnit.SECONDS)
						.readTimeout(30, TimeUnit.SECONDS)
						.sslSocketFactory(sslContext.getSocketFactory())
						.build();
				
				okhttp3.Response response = httpsClient.newCall(request).execute();
				
				if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
				
				return response.body().string();
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				IOUtils.closeQuietly(inputStream);
			}
		}

		@Override
		public String post(String url, String params, Map<String, String> header) {
			okhttp3.RequestBody body = okhttp3.RequestBody.create(CONTENT_TYPE_FORM, params);
			okhttp3.Request.Builder builder = new okhttp3.Request.Builder().url(url).post(body);
			for (Map.Entry<String, String> entry : header.entrySet()) {
				builder.addHeader(entry.getKey(), entry.getValue());
			}
			okhttp3.Request request = builder.build();
			return exec(request);
		}

		@Override
		public Map<String, String> bssPost(String url, String params,
				Map<String, String> header) {
			
			okhttp3.RequestBody body = okhttp3.RequestBody.create(CONTENT_TYPE_JSON, params);
			okhttp3.Request.Builder builder = new okhttp3.Request.Builder().url(url).post(body);
			for (Map.Entry<String, String> entry : header.entrySet()) {
				builder.addHeader(entry.getKey(), entry.getValue());
			}
			okhttp3.Request request = builder.build();
			return execResponse(request);
		}
		
	}
	
}
