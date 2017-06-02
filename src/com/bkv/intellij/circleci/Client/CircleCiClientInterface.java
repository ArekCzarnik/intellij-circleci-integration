package com.bkv.intellij.circleci.Client;

import com.bkv.intellij.circleci.Build.RecentBuild;
import java.util.List;

public interface CircleCiClientInterface {
    public List<RecentBuild> getRecentBuilds();
}
