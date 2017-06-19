package com.bkv.intellij.circleci.build.model;

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
            String token = component.getValue("com.bkv.intellij.icons.api_key", "api_key");

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
        builds = client.getRecentBuilds();
    }

    public List<BuildInterface> getRecentBuilds() {
        return builds;
    }
}
