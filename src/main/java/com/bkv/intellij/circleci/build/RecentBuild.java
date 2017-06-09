package com.bkv.intellij.circleci.build;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class RecentBuild implements BuildInterface {
    @SerializedName("build_num")
    private Integer buildNumber;

    @SerializedName("vcs_revision")
    private String vcsRevision;

    @SerializedName("committer_name")
    private String committerName;

    @SerializedName("committer_date")
    private Date committerDate;

    @SerializedName("status")
    private String status;

    @SerializedName("subject")
    private String subject;

    @SerializedName("outcome")
    private String outcome;

    @SerializedName("build_url")
    private String buildUrl;

    public RecentBuild(
            int buildNumber,
            String vcsRevision,
            String committerName,
            Date committerDate,
            String status,
            String subject,
            String outcome,
            String buildUrl
    ) {
        this.buildNumber = buildNumber;
        this.vcsRevision = vcsRevision;
        this.committerName = committerName;
        this.committerDate = committerDate;
        this.status = status;
        this.subject = subject;
        this.outcome = outcome;
        this.buildUrl = buildUrl;
    }

    @Override
    public Integer getBuildNumber() {
        return buildNumber;
    }

    @Override
    public String getVcsRevision() {
        return vcsRevision;
    }

    @Override
    public String getCommitterName() {
        return committerName;
    }

    @Override
    public Date getCommitterDate() {
        return committerDate;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getOutcome() {
        return outcome;
    }

    @Override
    public String getBuildUrl() {
        return buildUrl;
    }
}
