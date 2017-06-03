package com.bkv.intellij.circleci.build.event;

import com.bkv.intellij.circleci.build.BuildEventInterface;
import com.bkv.intellij.circleci.build.BuildInterface;

public class BuildStatusWasUpdated implements BuildEventInterface {
    private BuildInterface build;
    private String oldStatus;

    public BuildStatusWasUpdated(BuildInterface build, String oldStatus) {
        this.build = build;
        this.oldStatus = oldStatus;
    }

    @Override
    public BuildInterface getBuild() {
        return this.build;
    }

    public String getOldStatus() {
        return oldStatus;
    }

    public String getNewStatus() {
        return this.build.getStatus();
    }
}
