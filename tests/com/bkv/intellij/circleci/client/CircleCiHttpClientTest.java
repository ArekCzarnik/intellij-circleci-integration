package com.bkv.intellij.circleci.client;

import junit.framework.TestCase;
import org.junit.Assert;

public class CircleCiHttpClientTest extends TestCase {

    private CircleCiHttpClient subject;

    @Override
    public void setUp() throws Exception {
        subject = new CircleCiHttpClient();
    }

    @Override
    public void tearDown() throws Exception {
        subject = null;
    }

    public void testGetRecentBuilds() throws Exception {
        this.subject.getRecentBuilds();
    }
}
