package com.bkv.intellij.circleci.client;

import java.io.IOException;
import java.net.URL;

public interface HttpClientInterface {
    public String request(String method, URL url) throws IOException;
}
