package space.tanghy.common.utils.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author tanghy
 * @description
 * @date 2020-03-08 10:43 上午
 */
class HttpClientUtilTest {

    @Test
    void builder() throws IOException {
        HttpClientBuilder builder = HttpClientUtil.builder(5, 3000);
        CloseableHttpClient build = builder.build();
        HttpGet httpGet = new HttpGet("http://localhost:8080/test/long?name=5");
        HttpResponse response = build.execute(httpGet);
        String res = EntityUtils.toString(response.getEntity());
        System.out.println(res);


    }

    @Test
    void testBuilder() {
    }
}