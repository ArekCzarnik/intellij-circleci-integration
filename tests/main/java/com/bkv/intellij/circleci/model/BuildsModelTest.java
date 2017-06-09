package com.bkv.intellij.circleci.model;

import com.bkv.intellij.circleci.client.CircleCiClientInterface;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class BuildsModelTest {

    @Test
    public void canConstructUsingCircleCIClient()
    {
        CircleCiClientInterface client = mock(CircleCiClientInterface.class);
        BuildsModel model = new BuildsModel(client);
    }
}
