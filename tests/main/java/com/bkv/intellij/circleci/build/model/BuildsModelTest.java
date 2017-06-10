package com.bkv.intellij.circleci.build.model;

import com.bkv.intellij.circleci.build.BuildInterface;
import com.bkv.intellij.circleci.build.RecentBuild;
import com.bkv.intellij.circleci.client.CircleCiClientInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuildsModelTest {

    CircleCiClientInterface client;
    BuildsModel model;

    @Before
    public void setUp() throws IOException {
        client = mock(CircleCiClientInterface.class);
        when(client.getRecentBuilds()).thenReturn(createMockBuilds());
        model = BuildsModel.getInstance(client);
    }

    @After
    public void tearDown()
    {
        BuildsModel.resetInstance();
        client = null;
        model = null;
    }

    @Test
    public void canConstructUsingCircleCIClient()
    {
        Assert.assertThat(model, CoreMatchers.isA(BuildsModel.class));
    }

    @Test
    public void testGetRecentBuilds()
    {
        model.refresh();
        List<BuildInterface> builds = model.getRecentBuilds();
        Assert.assertThat(builds, CoreMatchers.isA(List.class));
    }


    private List<BuildInterface> createMockBuilds() throws IOException {
        String json = IOUtils.toString(
                getClass().getResourceAsStream("/recent-builds.json"),
                Charset.defaultCharset()
        );
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<RecentBuild>>() {}.getType());
    }
}
