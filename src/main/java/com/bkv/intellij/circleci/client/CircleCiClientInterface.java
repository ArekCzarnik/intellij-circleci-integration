package com.bkv.intellij.circleci.client;

import com.bkv.intellij.circleci.build.BuildInterface;

import java.io.IOException;
import java.util.List;

public interface CircleCiClientInterface {

    public List<BuildInterface> getRecentBuilds();
}
