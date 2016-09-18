/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.Security;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParamBean;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.pplive.ppcloud.utils.JsonUtils;

/**
 * @author chaogao
 *
 */
public class HttpClientManager {
	
	public static HttpClientManager getInstance() {
		return SingletonHolder.instance;
	}
	
	private static class SingletonHolder {
		private static HttpClientManager instance = new HttpClientManager(20000,50000);
	}
	
    private PoolingClientConnectionManager cm = new PoolingClientConnectionManager();
    private DefaultHttpClient httpClient;
    private static final String defaultCharsetStr = "UTF-8";
    private static final Charset defaultCharset = Charset.forName(defaultCharsetStr);
    private static final String jsonContentType = "application/json; charset=UTF-8";
    private static final Integer BUFFER_SIZE = Integer.valueOf(4048);
    private ScheduledExecutorService idleConnectionCloseExecutor = Executors.newSingleThreadScheduledExecutor();

    public HttpClientManager(int connectionTimeOut, int soTimeOut) {
    	SyncBasicHttpParams params = new SyncBasicHttpParams();
        DefaultHttpClient.setDefaultHttpParams(params);
        HttpConnectionParamBean paramsBean = new HttpConnectionParamBean(params);
        paramsBean.setConnectionTimeout(connectionTimeOut);
        paramsBean.setSoTimeout(soTimeOut);
        this.httpClient = new DefaultHttpClient(this.cm, params);
        this.httpClient.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy() {
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long keepalive = super.getKeepAliveDuration(response, context);
                if(keepalive == -1L) {
                    keepalive = 5000L;
                }

                return keepalive;
            }
        });
        this.httpClient.setReuseStrategy(new DefaultConnectionReuseStrategy() {
            public boolean keepAlive(HttpResponse response, HttpContext context) {
                boolean keekAlive = false;
                if(200 == response.getStatusLine().getStatusCode()) {
                    keekAlive = super.keepAlive(response, context);
                }

                return keekAlive;
            }
        });
        Security.setProperty("networkaddress.cache.ttl", "10");
    }

    public void closeIdleStart() {
        this.idleConnectionCloseExecutor.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                System.out.println("start to close expire and idle connections");
                HttpClientManager.this.cm.closeExpiredConnections();
                HttpClientManager.this.cm.closeIdleConnections(30L, TimeUnit.SECONDS);
            }
        }, 30L, 30L, TimeUnit.SECONDS);
        System.out.println("HttpManager close idel every 30 seconds");
    }

    public void setMaxTotal(int maxTotal) {
        this.cm.setMaxTotal(maxTotal);
        this.cm.setDefaultMaxPerRoute(maxTotal);
    }

    private void addContentType(HttpRequestBase request, String contentType) {
        request.addHeader("Content-Type", contentType);
    }

    public void destory() {
    	System.out.println("destory");
        this.idleConnectionCloseExecutor.shutdown();
        this.cm.shutdown();
    }
    
    public String execGetRequestWithParams(String url, Map<String, String> params) {
        Map<String, String> header = Collections.emptyMap();
        return this.execGetRequestWithParamsAndHeaders(url, params, header);
    }

    public String execGetRequestWithParamsAndHeaders(String url, Map<String, String> params, Map<String, String> heads) {
        HttpGet request = null;

        String res;
        try {
            if(!url.endsWith("?") && !url.contains("?")) {
                url = url + "?";
            }

            ArrayList<BasicNameValuePair> e = new ArrayList<BasicNameValuePair>();
            if(params != null && !params.isEmpty()) {
                Iterator<?> response = params.entrySet().iterator();

                while(response.hasNext()) {
                    Entry<?, ?> paramString = (Entry<?, ?>)response.next();
                    e.add(new BasicNameValuePair((String)paramString.getKey(), (String)paramString.getValue()));
                }
            }

            String paramString1 = URLEncodedUtils.format(e, defaultCharsetStr);
            url = url + paramString1;

            try {
                request = new HttpGet(new URI(url));
            } catch (URISyntaxException var18) {
                throw new RuntimeException("http get error. url: " + url, var18);
            }

            if(heads != null && !heads.isEmpty()) {
                Iterator<?> status = heads.entrySet().iterator();

                while(status.hasNext()) {
                    Entry<?, ?> response1 = (Entry<?, ?>)status.next();
                    request.addHeader((String)response1.getKey(), (String)response1.getValue());
                }
            }

            HttpResponse response2 = this.httpClient.execute(request);
            int status1 = response2.getStatusLine().getStatusCode();
            HttpEntity entity;
            if(status1 != 200) {
                entity = response2.getEntity();
                String reason = null;
                if(entity != null) {
                    reason = EntityUtils.toString(entity, defaultCharset);
                    System.out.println("get error, status:"+status1+", resonpse:"+reason+", url:"+url);
                } else {
                    System.out.println("get error, status:"+status1+", url:"+url);
                }

                throw new RuntimeException(String.format("http get error. url: %s, reason: %s", url, reason));
            }

            entity = response2.getEntity();
            res = EntityUtils.toString(entity, defaultCharset);
        } catch (IOException var19) {
            throw new RuntimeException("http get error. url: " + url, var19);
        } catch (RuntimeException var21) {
            throw new RuntimeException("http get error. url: " + url, var21);
        } finally {
            if(request != null) {
                request.abort();
            }

        }

        return res;
    }    

    public String execGetRequestWithHeader(String url, Map<String, String> header) {
        Map<String, String> param = Collections.emptyMap();
        return this.execGetRequestWithParamsAndHeaders(url, param, header);
    }

    public <T> String execPostRequestWithHeaders(URI uri, Map<String, String> headers, T obj) {
        HttpPost request = new HttpPost(uri);
        this.addContentType(request, jsonContentType);
        if(headers != null && !headers.isEmpty()) {
            Iterator<?> e = headers.entrySet().iterator();

            while(e.hasNext()) {
                Entry<?, ?> json = (Entry<?, ?>)e.next();
                request.addHeader((String)json.getKey(), (String)json.getValue());
            }
        }

        String json1 = JsonUtils.toJsonString(obj);

        String res;
        try {
            StringEntity e1 = new StringEntity(json1, defaultCharsetStr);
            request.setEntity(e1);
            HttpResponse response = this.httpClient.execute(request);
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if(status != 200) {
                String reason = null;
                if(entity != null) {
                    reason = EntityUtils.toString(entity, defaultCharset);
                    System.out.println("post error, status:"+status+", resonpse:"+reason+", url:"+uri+", requestcontent:"+json1);
                } else {
                    System.out.println("post error, status:"+status+", url:"+uri+", requestcontent:"+json1);
                }

                throw new RuntimeException(String.format("http post error. url: %s, reason: %s", uri, reason));
            }

            res = EntityUtils.toString(entity, defaultCharset);
        } catch (IOException var17) {
            throw new RuntimeException("http post error. url: " + uri, var17);
        } catch (RuntimeException var19) {
            throw new RuntimeException("http post error. url: " + uri, var19);
        } finally {
            request.abort();
        }

        return res;
    }

    public String execPostRequestWithParam(URI uri, Map<String, String> params, String charsetStr) {
        HttpPost request = new HttpPost(uri);
        ArrayList<BasicNameValuePair> postParameters = new ArrayList<BasicNameValuePair>(params.size());
        if(params != null && !params.isEmpty()) {
            Iterator<?> status = params.entrySet().iterator();

            while(status.hasNext()) {
                Entry<?, ?> e = (Entry<?, ?>)status.next();
                postParameters.add(new BasicNameValuePair((String)e.getKey(), (String)e.getValue()));
            }
        }

        String res;
        try {
            if(charsetStr != null && !charsetStr.equals("")) {
                request.setEntity(new UrlEncodedFormEntity(postParameters, charsetStr));
            } else {
                request.setEntity(new UrlEncodedFormEntity(postParameters));
            }

            HttpResponse e1 = this.httpClient.execute(request);
            int status1 = e1.getStatusLine().getStatusCode();
            HttpEntity entity = e1.getEntity();
            if(status1 != 200) {
                String reason = null;
                if(entity != null) {
                    reason = EntityUtils.toString(entity, defaultCharset);
                    System.out.println("post error, status:"+status1+", resonpse:"+reason+", url:"+uri);
                } else {
                	System.out.println("post error, status:"+status1+", url:"+uri);
                }

                throw new RuntimeException(String.format("http post error. url: %s, reason: %s", uri, reason));
            }

            res = EntityUtils.toString(entity, defaultCharsetStr);
        } catch (IOException var16) {
            throw new RuntimeException("http post error. url: " + uri, var16);
        } catch (RuntimeException var18) {
            throw new RuntimeException("http post error. url: " + uri, var18);
        } finally {
            request.abort();
        }

        return res;
    }

    public String execPostRequestWithParam(URI uri, Map<String, String> params) {
        return this.execPostRequestWithParam(uri, params, defaultCharsetStr);
    }

    public <T> String execPostTextRequestWithParamsAndHeaders(URI uri, Map<String, String> params, Map<String, String> headers, String text) {
        StringEntity myEntity = null;

        try {
			myEntity = new StringEntity(text, defaultCharsetStr);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("convert entity from json failed. text:" + text);
		}

        return this.execPostRequestWithParamsAndHeaders(uri, params, headers, myEntity, jsonContentType);
    }

    public <T> String execPostRequestWithParamsAndHeaders(URI uri, Map<String, String> params, Map<String, String> headers, StringEntity myEntity, String contentType) {
        HttpPost request = new HttpPost(uri);
        this.addContentType(request, contentType);
        Entry<?, ?> e;
        Iterator<?> status;
        if(params != null && !params.isEmpty()) {
            status = params.entrySet().iterator();

            while(status.hasNext()) {
                e = (Entry<?, ?>)status.next();
                request.getParams().setParameter((String)e.getKey(), e.getValue());
            }
        }

        if(headers != null && !headers.isEmpty()) {
            status = headers.entrySet().iterator();

            while(status.hasNext()) {
                e = (Entry<?, ?>)status.next();
                request.addHeader((String)e.getKey(), (String)e.getValue());
            }
        }

        String res;
        try {
            request.setEntity(myEntity);
            HttpResponse e1 = this.httpClient.execute(request);
            int status1 = e1.getStatusLine().getStatusCode();
            HttpEntity entity = e1.getEntity();
            if(status1 != 200) {
                if(entity != null) {
                    System.out.println("post error, status:"+status1+", resonpse:"+EntityUtils.toString(entity, defaultCharset)+", url:"+uri);
                } else {
                    System.out.println("post error, status:"+status1+", url:"+uri);
                }

                throw new RuntimeException("http post error. url: " + uri);
            }

            res = EntityUtils.toString(entity, defaultCharset);
        } catch (IOException var15) {
            throw new RuntimeException("http post error. url: " + uri, var15);
        } catch (RuntimeException var16) {
            throw new RuntimeException("http post error. url: " + uri, var16);
        } finally {
            request.abort();
        }

        return res;
    }

    public String execDeleteRequestWithContent(String url) {
        try {
            URI e = new URI(url);
            return this.execDeleteRequestWithContent(e, (String)null);
        } catch (URISyntaxException var3) {
            throw new RuntimeException(url + " delete error", var3);
        }
    }

    public String execDeleteRequestWithContent(URI uri, String cookie) {
        HttpDelete request = new HttpDelete(uri);
        if(!StringUtils.isEmpty(cookie)) {
            request.addHeader(new BasicHeader("Cookie", cookie));
        }

        String res;
        try {
            HttpResponse e = this.httpClient.execute(request);
            int status = e.getStatusLine().getStatusCode();
            HttpEntity entity;
            if(status != 200) {
                entity = e.getEntity();
                String reason = null;
                if(entity != null) {
                    reason = EntityUtils.toString(entity, defaultCharset);
                    System.out.println("get error, status:"+status+", resonpse:"+reason+", url:"+uri);
                } else {
                    System.out.println("get error, status:"+status+", url:"+uri);
                }

                throw new RuntimeException(String.format("http delete error. url: %s, reason: %s", uri, reason));
            }

            entity = e.getEntity();
            res = EntityUtils.toString(entity, defaultCharset);
        } catch (IOException var14) {
            throw new RuntimeException("http delete error. url: " + uri, var14);
        } catch (RuntimeException var16) {
            throw new RuntimeException("http delete error. url: " + uri, var16);
        } finally {
            request.abort();
        }

        return res;
    }
    
    public String execDeleteRequestWithParams(String url, Map<String, String> params) {
        Map<String, String> header = Collections.emptyMap();
        return this.execDeleteRequestWithParamsAndHeaders(url, params, header);
    }
    
    public String execDeleteRequestWithParamsAndHeaders(String url, Map<String, String> params, Map<String, String> heads) {
    	HttpDelete request = null;

        String res;
        try {
        	if(!url.endsWith("?") && !url.contains("?")) {
                url = url + "?";
            }
        	ArrayList<BasicNameValuePair> e = new ArrayList<BasicNameValuePair>();
            if(params != null && !params.isEmpty()) {
                Iterator<?> response = params.entrySet().iterator();

                while(response.hasNext()) {
                    Entry<?, ?> paramString = (Entry<?, ?>)response.next();
                    e.add(new BasicNameValuePair((String)paramString.getKey(), (String)paramString.getValue()));
                }
            }
            
            String paramString1 = URLEncodedUtils.format(e, defaultCharsetStr);
            url = url + paramString1;
            
            try {
                request = new HttpDelete(new URI(url));
            } catch (URISyntaxException var18) {
                throw new RuntimeException("http delete error. url: " + url, var18);
            }

            if(heads != null && !heads.isEmpty()) {
                Iterator<?> status = heads.entrySet().iterator();

                while(status.hasNext()) {
                    Entry<?, ?> response1 = (Entry<?, ?>)status.next();
                    request.addHeader((String)response1.getKey(), (String)response1.getValue());
                }
            }
        	
            HttpResponse response2 = this.httpClient.execute(request);
            int status = response2.getStatusLine().getStatusCode();
            HttpEntity entity;
            if(status != 200) {
                entity = response2.getEntity();
                String reason = null;
                if(entity != null) {
                    reason = EntityUtils.toString(entity, defaultCharset);
                    System.out.println("get delete, status:"+status+", resonpse:"+reason+", url:"+url);
                } else {
                    System.out.println("get delete, status:"+status+", url:"+url);
                }

                throw new RuntimeException(String.format("http delete error. url: %s, reason: %s", url, reason));
            }

            entity = response2.getEntity();
            res = EntityUtils.toString(entity, defaultCharset);
        } catch (IOException var14) {
            throw new RuntimeException("http delete error. url: " + url, var14);
        } catch (RuntimeException var16) {
            throw new RuntimeException("http delete error. url: " + url, var16);
        } finally {
            request.abort();
        }

        return res;
    }

    public DefaultHttpClient getHttpClient() {
        return this.httpClient;
    }

    public static String getDefaultcharsetstr() {
        return defaultCharsetStr;
    }

    public static Charset getDefaultcharset() {
        return defaultCharset;
    }

    public static Integer getBufferSize() {
        return BUFFER_SIZE;
    }
}
