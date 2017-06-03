package com.bkv.intellij.circleci.build.event;

import com.bkv.intellij.circleci.build.BuildEventInterface;
import com.bkv.intellij.circleci.build.BuildInterface;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

public class BuildWasAddedTest extends TestCase {
    private BuildWasAdded event;
    private BuildInterface build;

    @Override
    protected void setUp() throws Exception {
        this.build = mock(BuildInterface.class);
        this.event = new BuildWasAdded(this.build);
    }

    public void testImplementsCorrectInterface() throws Exception {
        assertTrue(this.event instanceof BuildEventInterface);
    }

    public void testGetBuild() throws Exception {
        assertEquals(this.build, this.event.getBuild());
    }
}
