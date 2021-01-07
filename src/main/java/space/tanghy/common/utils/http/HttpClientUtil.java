package space.tanghy.common.utils.http;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import space.tanghy.common.utils.ObjectUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 创建可重试请求对象
 *
 * @author tanghy
 */
public class HttpClientUtil {

    private HttpClientUtil() {

    }

    /**
     * @param retryNum
     * @param interval
     * @return
     */
    public static HttpClientBuilder builder(int retryNum, int interval) {
        HttpClientBuilder builder = builder(retryNum, interval, true, null);
        return builder;
    }

    /**
     * @param retryNum
     * @param interval
     * @param isPool
     * @param requestConfig
     * @return
     */
    public static HttpClientBuilder builder(int retryNum, int interval, boolean isPool, RequestConfig requestConfig) {

        RequestConfig.Builder reqBuilder = RequestConfig.custom();
        reqBuilder.setExpectContinueEnabled(true);
        HttpClientBuilder builder = getBuilder(retryNum, interval, isPool);
        builder.setDefaultRequestConfig(reqBuilder.build());
        if (ObjectUtil.isNotEmpty(requestConfig))
            builder.setDefaultRequestConfig(requestConfig);

        return builder;
    }


    public static HttpClientBuilder builder(int retryNum, int interval, boolean isPool, int socketTimeout, int connectTimeout, int requestTimeout) {

        RequestConfig.Builder reqBuilder = RequestConfig.custom();
        reqBuilder.setSocketTimeout(socketTimeout);
        reqBuilder.setConnectTimeout(connectTimeout);
        reqBuilder.setConnectionRequestTimeout(requestTimeout);
        reqBuilder.setExpectContinueEnabled(true);

        HttpClientBuilder builder = getBuilder(retryNum, interval, isPool);
        builder.setDefaultRequestConfig(reqBuilder.build());

        return builder;
    }

    /**
     * @param retryNum
     * @param interval
     * @param cm
     * @param requestConfig
     * @param connectionConfig
     * @return
     */
    public static HttpClientBuilder builder(int retryNum, int interval, PoolingHttpClientConnectionManager cm, RequestConfig requestConfig, ConnectionConfig connectionConfig) {

        HttpClientBuilder builder = setRetryHandler(retryNum, interval);

        if (ObjectUtil.isNotEmpty(connectionConfig)) {
            builder.setDefaultConnectionConfig(connectionConfig);
        }
        if (ObjectUtil.isNotEmpty(requestConfig)) {
            builder.setDefaultRequestConfig(requestConfig);
        }
        if (ObjectUtil.isNotEmpty(cm)) {
            builder.setConnectionManager(cm);
        }

        return builder;
    }

    /**
     * @param retryNum
     * @param interval
     * @param isPool
     * @return
     */
    private static HttpClientBuilder getBuilder(int retryNum, int interval, boolean isPool) {

        Charset charset = StandardCharsets.UTF_8;
        ConnectionConfig.Builder connBuilder = ConnectionConfig.custom().setCharset(charset);

        HttpClientBuilder builder = setRetryHandler(retryNum, interval);

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(1000);
        cm.setDefaultMaxPerRoute(1000);

        builder.setDefaultConnectionConfig(connBuilder.build());
        //是否使用连接池
        if (isPool) {
            builder.setConnectionManager(cm);
        }

        return builder;
    }

    private static HttpClientBuilder setRetryHandler(int retryNum, int interval) {

        HttpRequestRetryHandler httpRequestRetryHandler = (e, executeNum, httpContext) -> {

            if (ObjectUtil.isNotEmpty(e)) {
                if (executeNum > retryNum) {
                    return false;
                } else {
                    System.out.println("重试第：" + executeNum + " 次。");
                    return true;
                }
            } else {
                return false;
            }

        };

        ServiceUnavailableRetryStrategy retryStrategy = new ServiceUnavailableRetryStrategy() {
            @Override
            public boolean retryRequest(HttpResponse httpResponse, int executeNum, HttpContext httpContext) {

                StatusLine statusLine = httpResponse.getStatusLine();
                if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                    return false;
                } else if (executeNum > retryNum) {
                    return false;
                } else {
                    System.out.println("超时重试第：" + executeNum + " 次。");
                    return true;
                }
            }

            @Override
            public long getRetryInterval() {
                return interval;
            }
        };

        HttpClientBuilder builder = HttpClients.custom();
        builder.setRetryHandler(httpRequestRetryHandler);
        builder.setServiceUnavailableRetryStrategy(retryStrategy);

        return builder;
    }


}
