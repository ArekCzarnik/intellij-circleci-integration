package com.bkv.intellij.circleci.Client;

import com.bkv.intellij.circleci.Build.BuildInterface;
import com.bkv.intellij.circleci.Build.RecentBuild;

import java.util.List;

public class CircleCiHttpClient implements CircleCiClientInterface {

    @Override
    public List<BuildInterface> getRecentBuilds() {
        return null;
    }
}
