package com.bkv.intellij.circleci.build;

import com.google.gson.Gson;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.Date;

public class RecentBuildTest extends TestCase {
    private RecentBuild build;

    public void testCanDeserialize() {
        Assert.assertEquals(new Integer(10), build.getBuildNumber());
        Assert.assertEquals("a09323cd987", build.getVcsRevision());
        Assert.assertEquals("Tinus Tester", build.getCommitterName());
        Assert.assertEquals("Sat Jan 01 00:00:00 CET 2000", build.getCommitterDate());
        Assert.assertEquals("success", build.getStatus());
        Assert.assertEquals("Test subject", build.getSubject());
        Assert.assertEquals("success", build.getOutcome());
        Assert.assertEquals("http://url.com", build.getBuildUrl());
    }

    protected void setUp() {
        Gson gson = new Gson();
        build = gson.fromJson("{\"build_num\":\"10\", \"vcs_revision\":\"a09323cd987\"," +
                        "\"committer_name\": \"Tinus Tester\", \"committer_date\": " +
                        "\"2000-01-01\", \"status\": \"success\", \"subject\": \"Test subject\"," +
                        "\"outcome\": \"success\", \"build_url\": \"http://url.com\"}",
                RecentBuild.class
        );
    }
}
