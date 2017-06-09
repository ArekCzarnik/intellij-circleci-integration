package com.bkv.intellij.circleci.build.event;

import com.bkv.intellij.circleci.build.BuildEventInterface;
import com.bkv.intellij.circleci.build.BuildInterface;

public class BuildWasAdded implements BuildEventInterface {
    private BuildInterface build;

    public BuildWasAdded(BuildInterface build) {
        this.build = build;
    }

    @Override
    public BuildInterface getBuild() {
        return this.build;
    }
}
