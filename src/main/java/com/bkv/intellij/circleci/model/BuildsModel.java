package com.bkv.intellij.circleci.model;

import com.bkv.intellij.circleci.build.BuildInterface;
import com.bkv.intellij.circleci.client.CircleCiClientInterface;

import java.util.ArrayList;
import java.util.List;

public class BuildsModel {

    private CircleCiClientInterface client;
    private List<BuildListenerInterface> buildListeners;
    private List<BuildInterface> builds;

    public BuildsModel(CircleCiClientInterface client)
    {
        this.client = client;
        buildListeners = new ArrayList<>();
        builds = new ArrayList<>();
    }

    public void applyBuilds(List<BuildInterface> builds)
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
