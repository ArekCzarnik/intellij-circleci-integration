package com.bkv.intellij.circleci.Build;

import java.util.Date;

public interface BuildInterface {
    Integer getBuildNumber();

    String getVcsRevision();

    String getCommitterName();

    Date getCommitterDate();

    String getStatus();

    String getSubject();

    String getOutcome();

    String getBuildUrl();
}
