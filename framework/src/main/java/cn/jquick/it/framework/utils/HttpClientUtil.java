package cn.jquick.it.framework.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;

/** 
 * Httpclient通讯工具类
 * <功能详细描述> 
 * 
 * @author  ouxin 
 * @version  [版本号, 2016年3月20日] 
 */
public class HttpClientUtil
{
    //日志处理
    private static final Log log = LogFactory.getLog(HttpClientUtil.class);
    
    // 读取超时  
    private final static int SOCKET_TIMEOUT = 10000;
    
    // 连接超时  
    private final static int CONNECTION_TIMEOUT = 10000;
    
    // 每个HOST的最大连接数量  
    private final static int MAX_CONN_PRE_HOST = 20;
    
    // 连接池的最大连接数  
    private final static int MAX_CONN = 60;
    
    // 连接池  
    private final static HttpConnectionManager httpConnectionManager;
    
    static
    {
        httpConnectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = httpConnectionManager.getParams();
        params.setConnectionTimeout(CONNECTION_TIMEOUT);
        params.setSoTimeout(SOCKET_TIMEOUT);
        params.setDefaultMaxConnectionsPerHost(MAX_CONN_PRE_HOST);
        params.setMaxTotalConnections(MAX_CONN);
    }
    
    /** 
     * 发送主要方法,异常捕获 
     * @param post 
     * @param code 
     * @return 
     */
    @SuppressWarnings("deprecation")
    public static String doHttpRequest(String url, Object obj)
    {
        StringBuffer urlSb = new StringBuffer("http://localhost:8080/platform/services");
        url = urlSb.append(url).toString();
        PostMethod postMethod = new PostMethod(url);
        postMethod.setRequestHeader("Content-type", "application/json; charset=UTF-8");
        postMethod.setRequestBody(JSON.toJSONString(obj));
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        HttpClient httpClient = new HttpClient(httpConnectionManager);
        resetRequestHeader(httpClient, "127.0.0.1");
        BufferedReader in = null;
        String resultString = "";
        try
        {
            httpClient.executeMethod(postMethod);
            InputStream is = postMethod.getResponseBodyAsStream();
            if (null == is)
            {
                return null;
            }
            in = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream(), "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null)
            {
                buffer.append(line);
            }
            resultString = buffer.toString();
        }
        catch (SocketTimeoutException e)
        {
            log.error("连接超时" + e.toString());
            resultString = returnError("连接超时");
        }
        catch (HttpException e)
        {
            log.error("读取外部服务器数据失败" + e.toString());
            resultString = returnError("读取外部服务器数据失败");
        }
        catch (UnknownHostException e)
        {
            log.error("请求的主机地址无效" + e.toString());
            resultString = returnError("请求的主机地址无效");
        }
        catch (IOException e)
        {
            log.error("向外部接口发送数据失败" + e.toString());
            resultString = returnError("向外部接口发送数据失败");
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            postMethod.releaseConnection();
        }
        return resultString;
    }
    
    /** 
     * 设置一下返回错误的通用提示,可以自定义格式. 
     * @param reason 
     * @return 
     */
    public static String returnError(String reason)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        buffer.append("<Response>");
        buffer.append("<Success>false</Success>");
        buffer.append("<reason>");
        buffer.append(reason);
        buffer.append("</reason>");
        buffer.append("</Response>");
        return buffer.toString();
    }
    
    public final static String REQUEST_HEADER = "x-forwarded-for";
    
    /** 
     * 将客户IP写入请求头 
     * 这个设置可以伪装IP请求,注意使用 
     * @param client 
     * @param ip 
     * @return 
     */
    public static void resetRequestHeader(HttpClient client, String ip)
    {
        List<Header> headers = new ArrayList<Header>();
        headers.add(new Header(REQUEST_HEADER, ip));
        client.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
    }
}
