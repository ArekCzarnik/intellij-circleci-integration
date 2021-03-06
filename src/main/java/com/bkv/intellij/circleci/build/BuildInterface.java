package com.bkv.intellij.circleci.build;

import java.util.Date;

public interface BuildInterface {
    Integer getBuildNumber();

    String getVcsRevision();

    String getCommitterName();

    String getCommitterDate();

    String getStatus();

    String getProject();

    String getSubject();

    String getOutcome();

    String getBuildUrl();
}
