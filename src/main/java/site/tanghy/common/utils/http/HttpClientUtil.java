package site.tanghy.common.utils.http;

import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.client.DefaultServiceUnavailableRetryStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import site.tanghy.common.utils.ObjectUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 * 创建可重试请求对象
 * @author tanghy
 */
public class HttpClientUtil {

    private HttpClientUtil() {

    }

    public HttpClientBuilder builder(int retryNum, int interval) {
        HttpClientBuilder builder = builder(retryNum, interval, true);
        return builder;
    }

    public HttpClientBuilder builder(int retryNum, int interval, boolean isPool) {

        Charset charset = StandardCharsets.UTF_8;
        ConnectionConfig.Builder connBuilder = ConnectionConfig.custom().setCharset(charset);

        RequestConfig.Builder reqBuilder = RequestConfig.custom();
//        reqBuilder.setExpectContinueEnabled(false);
        reqBuilder.setSocketTimeout(10 * 1000);
        reqBuilder.setConnectTimeout(10 * 1000);
        reqBuilder.setConnectionRequestTimeout(10 * 1000);
//        reqBuilder.setMaxRedirects(10);

        ServiceUnavailableRetryStrategy retryStrategy = new DefaultServiceUnavailableRetryStrategy(retryNum, interval);

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(1000);
        cm.setDefaultMaxPerRoute(1000);

        HttpClientBuilder builder = HttpClients.custom();
        builder.setServiceUnavailableRetryStrategy(retryStrategy);
        builder.setDefaultConnectionConfig(connBuilder.build());
        builder.setDefaultRequestConfig(reqBuilder.build());
        //是否使用连接池
        if (isPool)
            builder.setConnectionManager(cm);

        return builder;
    }

    public HttpClientBuilder builder(int retryNum, int interval, PoolingHttpClientConnectionManager cm, RequestConfig requestConfig, ConnectionConfig connectionConfig) {

        ServiceUnavailableRetryStrategy retryStrategy = new DefaultServiceUnavailableRetryStrategy(retryNum, interval);

        HttpClientBuilder builder = HttpClients.custom();
        builder.setServiceUnavailableRetryStrategy(retryStrategy);
        builder.setDefaultConnectionConfig(connectionConfig);
        builder.setDefaultRequestConfig(requestConfig);
        //是否使用连接池
        if (ObjectUtil.isNotEmpty(cm))
            builder.setConnectionManager(cm);

        return builder;
    }


}
