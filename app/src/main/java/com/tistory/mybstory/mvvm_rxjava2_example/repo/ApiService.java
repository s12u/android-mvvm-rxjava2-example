package com.tistory.mybstory.mvvm_rxjava2_example.repo;

import com.tistory.mybstory.mvvm_rxjava2_example.model.RepoResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("users/{userId}/repos")
    Observable<List<RepoResponse>> requestRepoList(@Path("userId") String username);
}
