package com.bkv.intellij.circleci.model;

import com.bkv.intellij.circleci.build.BuildInterface;

public interface BuildListenerInterface {
    void onBuildWasAdded(BuildInterface build);

    void onBuildStatusWasUpdated(BuildInterface build);
}
