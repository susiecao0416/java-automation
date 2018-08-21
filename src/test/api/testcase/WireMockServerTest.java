package api.testcase;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static junit.framework.Assert.assertEquals;


public class WireMockServerTest {

    int port = 9999;
    WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(port));
    CloseableHttpClient httpClient = HttpClients.createDefault();

    @Before
    public void setUp() throws Exception {

        wireMockServer.start();
        configureFor("localhost", port);
    }

    @Test
    public void should_get_hello() throws Exception{
        stubFor(get(urlEqualTo("/hello")).willReturn(aResponse().withBody("hello world")));

        HttpGet request = new HttpGet("http://localhost:9999/hello");
        HttpResponse httpResponse = httpClient.execute(request);

        int code = httpResponse.getStatusLine().getStatusCode();
        assertEquals(200, code);

//        InputStream responseStream = httpResponse.getEntity().getContent();
//        Scanner scanner = new Scanner(responseStream, "UTF-8");
//        String responseString = scanner.useDelimiter("\\Z").next();
//        scanner.close();
    }

    @After
    public void teardown() throws Exception {

        wireMockServer.stop();
    }
}