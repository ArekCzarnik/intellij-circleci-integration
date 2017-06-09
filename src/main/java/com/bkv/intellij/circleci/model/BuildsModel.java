package com.bkv.intellij.circleci.model;

import com.bkv.intellij.circleci.build.BuildInterface;
import com.bkv.intellij.circleci.client.CircleCiClientInterface;
import com.bkv.intellij.circleci.client.CircleCiHttpClient;
import com.bkv.intellij.circleci.client.HttpClient;
import com.intellij.ide.util.PropertiesComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuildsModel {

    private CircleCiClientInterface client;
    private List<BuildListenerInterface> buildListeners;
    private List<BuildInterface> builds;
    private static BuildsModel instance;

    private BuildsModel(CircleCiClientInterface client)
    {
        this.client = client;
        buildListeners = new ArrayList<>();
        builds = new ArrayList<>();
    }

    public static BuildsModel getInstance()
    {
        if (instance == null) {
            PropertiesComponent component = PropertiesComponent.getInstance();
            String token = component.getValue("com.bkv.intellij.circleci.api_key");

            instance = new BuildsModel(new CircleCiHttpClient(new HttpClient(), "https://circleci.com/api/v1.1/", token));
        }
        return instance;
    }

    public static BuildsModel getInstance(CircleCiClientInterface client)
    {
        instance = new BuildsModel(client);
        return instance;
    }

    public static void resetInstance()
    {
        instance = null;
    }

    public void refresh()
    {
        List<BuildInterface> builds = null;
        try {
            builds = client.getRecentBuilds();
        } catch (IOException e) {
            e.printStackTrace();
        }
        applyBuilds(builds);
    }

    private void applyBuilds(List<BuildInterface> builds)
    {
        for (BuildInterface build: builds) {
            if (hasBuild(build)) {
                updateBuild(build, builds);
            } else {
                addBuild(build);
            }
        }
    }

    private boolean hasBuild(BuildInterface build) {
        return this.builds.contains(build);
    }

    private void addBuild(BuildInterface build) {
        builds.add(build);
        notifyOnBuildWasAdded(build);
    }

    private void updateBuild(BuildInterface build, List<BuildInterface> builds) {
        int index = builds.indexOf(build);
        builds.set(index, build);
        notifyOnBuildStatusWasUpdated(build);
    }

    private void notifyOnBuildWasAdded(BuildInterface build) {
        for (BuildListenerInterface buildListener: this.buildListeners) {
            buildListener.onBuildWasAdded(build);
        }
    }

    private void notifyOnBuildStatusWasUpdated(BuildInterface build) {
        for (BuildListenerInterface buildListener: this.buildListeners) {
            buildListener.onBuildStatusWasUpdated(build);
        }
    }

    public void addBuildListener(BuildListenerInterface listener) {
        buildListeners.add(listener);
    }

    public boolean hasBuildListener(BuildListenerInterface listener)
    {
        return buildListeners.contains(listener);
    }

    public void removeBuildListener(BuildListenerInterface listener)
    {
        buildListeners.remove(listener);
    }
}
