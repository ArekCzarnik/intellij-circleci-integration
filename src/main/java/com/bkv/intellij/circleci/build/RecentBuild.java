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
        String vcsRevision = "unknown";
        if (this.vcsRevision != null) {
            vcsRevision = this.vcsRevision;
        }
        return vcsRevision;
    }

    @Override
    public String getCommitterName() {
        String committerName = "unknown";
        if (this.committerName != null) {
            committerName = this.committerName;
        }
        return committerName;
    }

    @Override
    public String getCommitterDate() {
        String committerDate = "unknown date";
        if (this.committerDate != null) {
            committerDate = this.committerDate.toString();
        }
        return committerDate;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getSubject() {
        String subject = "-";
        if (this.subject != null) {
            subject = this.subject;
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecentBuild that = (RecentBuild) o;

        return buildNumber.equals(that.buildNumber);
    }

    @Override
    public int hashCode() {
        return buildNumber.hashCode();
    }
}

