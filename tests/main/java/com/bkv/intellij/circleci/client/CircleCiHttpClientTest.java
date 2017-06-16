package com.bkv.intellij.circleci.client;

import com.bkv.intellij.circleci.build.BuildInterface;
import com.bkv.intellij.circleci.build.RecentBuild;
import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CircleCiHttpClientTest extends TestCase {
    private HttpClientInterface httpClient;
    private CircleCiHttpClient subject;
    private String url;

    @Override
    public void setUp() throws Exception {
        this.url = "https://icons.com/api/v1.1/";
        this.httpClient = mock(HttpClientInterface.class);
        this.subject = new CircleCiHttpClient(this.httpClient, this.url, "token");
    }

    @Override
    public void tearDown() throws Exception {
        subject = null;
        url = null;
    }
    public void testGetRecentBuilds() throws Exception {
        URL expectedUrl = new URL(this.url + "recent-builds?circle-token=token");
        String response = getRecentBuildsResponse();
        when(this.httpClient.request("GET", expectedUrl)).thenReturn(response);

        List<BuildInterface> recentBuilds = this.subject.getRecentBuilds();

        assertEquals(1, recentBuilds.size());
        assertTrue(recentBuilds.get(0) instanceof RecentBuild);
    }

    private String getRecentBuildsResponse() throws Exception {
        return IOUtils.toString(
            getClass().getResourceAsStream("/recent-builds.json"),
            Charset.defaultCharset()
        );
    }
}
