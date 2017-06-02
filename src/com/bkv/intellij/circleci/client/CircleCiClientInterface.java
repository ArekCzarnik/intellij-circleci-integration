package com.bkv.intellij.circleci.client;

import com.bkv.intellij.circleci.build.RecentBuild;

import java.util.List;

public interface CircleCiClientInterface {
    public List<RecentBuild> getRecentBuilds();
}
