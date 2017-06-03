package com.bkv.intellij.circleci.build.event;

import com.bkv.intellij.circleci.build.BuildEventInterface;
import com.bkv.intellij.circleci.build.BuildInterface;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuildStatusWasUpdatedTest extends TestCase {
    private BuildStatusWasUpdated event;
    private BuildInterface build;
    private static String oldStatus = "failed";
    private static String newStatus = "success";

    @Override
    protected void setUp() throws Exception {
        this.build = mock(BuildInterface.class);
        this.event = new BuildStatusWasUpdated(this.build, oldStatus);
    }

    public void testImplementsCorrectInterface() throws Exception {
        assertTrue(this.event instanceof BuildEventInterface);
    }

    public void testGetBuild() throws Exception {
        assertEquals(this.build, this.event.getBuild());
    }

    public void testGetOldStatus() throws Exception {
        assertEquals(oldStatus, this.event.getOldStatus());
    }

    public void testGetNewStatus() throws Exception {
        when(this.build.getStatus()).thenReturn(newStatus);
        assertEquals(newStatus, this.event.getNewStatus());
    }
}
