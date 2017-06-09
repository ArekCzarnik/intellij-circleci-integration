package com.bkv.intellij.circleci.model;

import com.bkv.intellij.circleci.client.CircleCiClientInterface;

public class BuildsModel {

    private CircleCiClientInterface client;

    public BuildsModel(CircleCiClientInterface client)
    {
        this.client = client;
    }

}
