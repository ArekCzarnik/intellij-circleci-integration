package com.bkv.intellij.circleci.client;

import com.bkv.intellij.circleci.build.BuildInterface;
import com.bkv.intellij.circleci.build.RecentBuild;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CircleCiHttpClient implements CircleCiClientInterface {
    private HttpClientInterface client;
    private String url;
    private String token;

    public CircleCiHttpClient(HttpClientInterface client, String url, String token) {
        this.client = client;
        this.url = url;
        this.token = token;
    }

    @Override
    public List<BuildInterface> getRecentBuilds() {
        List<BuildInterface> recentBuilds = new ArrayList<>();
        if (this.token.equals("api_key")) {
            return recentBuilds;
        }
        try {
            URL url = new URL(this.url + "recent-builds?circle-token=" + this.token);
            String response = this.client.request("GET", url);

            Gson gson = new Gson();
            recentBuilds = gson.fromJson(response, new TypeToken<List<RecentBuild>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return recentBuilds;
    }
}
