package com.example.hw_26_04.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PATCH;

public interface GitHubApiService {

    @PATCH("user")
    Call<GitHubUserResponse> updateBio(
            @Header("Authorization") String authorization,
            @Body BioUpdateRequest body
    );
}