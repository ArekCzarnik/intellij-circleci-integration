package com.bkv.intellij.circleci.model;

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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BuildsModelTest {

    CircleCiClientInterface client;
    BuildsModel model;

    @Before
    public void setUp()
    {
        client = mock(CircleCiClientInterface.class);
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
    public void canRegisterBuildListener()
    {
        BuildListenerInterface listener = mock(BuildListenerInterface.class);
        model.addBuildListener(listener);
        Assert.assertTrue(model.hasBuildListener(listener));
        model.removeBuildListener(listener);
        Assert.assertFalse(model.hasBuildListener(listener));
    }


    public void canApplyBuilds() throws IOException {
        List<BuildInterface> builds = createMockBuilds();
        when(client.getRecentBuilds()).thenReturn(builds);

        BuildListenerInterface listener = mock(BuildListenerInterface.class);

        model.addBuildListener(listener);
        model.refresh();

        verify(listener).onBuildWasAdded(builds.get(0));
    }


    public void applyBuildsCannotAddSameBuildTwice() throws IOException {
        List<BuildInterface> builds = createMockBuilds();
        when(client.getRecentBuilds()).thenReturn(builds);

        BuildListenerInterface listener = mock(BuildListenerInterface.class);

        model.addBuildListener(listener);
        model.refresh();

        verify(listener).onBuildWasAdded(builds.get(0));
    }


    public void canApplyBuildsCanUpdateABuildStatus() throws IOException {
        List<BuildInterface> builds = createMockBuilds();
        when(client.getRecentBuilds()).thenReturn(builds);

        BuildListenerInterface listener = mock(BuildListenerInterface.class);

        model.addBuildListener(listener);
        model.refresh();

        verify(listener).onBuildStatusWasUpdated(builds.get(0));
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