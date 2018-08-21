import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static junit.framework.Assert.assertEquals;


public class WireMockJUnitTest {

    CloseableHttpClient httpClient = HttpClients.createDefault();

    @Before
    public void setUp() throws Exception {

    }

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(WireMockConfiguration.options().port(8888));

    // Apache http client
    @Test
    public void should_get_hello() throws Exception{
        wireMockRule.stubFor(get(urlEqualTo("/hello")).willReturn(aResponse().withBody("hello world")));

        HttpGet request = new HttpGet("http://localhost:8888/hello");
        HttpResponse httpResponse = httpClient.execute(request);

       int code = httpResponse.getStatusLine().getStatusCode();
       assertEquals(200, code);
    }
}