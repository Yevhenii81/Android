package com.example.hw_26_04.network;

import com.google.gson.annotations.SerializedName;

public class GitHubUserResponse {
    private String login;
    private String bio;

    @SerializedName("html_url")
    private String htmlUrl;

    public String getLogin() { return login; }
    public String getBio() { return bio; }
    public String getHtmlUrl() { return htmlUrl; }
}