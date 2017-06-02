package com.bkv.intellij.circleci.client;

import com.bkv.intellij.circleci.build.BuildInterface;

import java.util.List;

public class CircleCiHttpClient implements CircleCiClientInterface {

    @Override
    public List<BuildInterface> getRecentBuilds() {
        return null;
    }
}
