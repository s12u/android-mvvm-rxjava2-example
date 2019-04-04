package com.tistory.mybstory.mvvm_rxjava2_example.repo;

import com.tistory.mybstory.mvvm_rxjava2_example.model.RepoResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GitHubRepository {

    private final String BASE_URL = "https://api.github.com/";

    private static GitHubRepository mInstance = null;

    public static GitHubRepository getInstance() {
        if (mInstance == null) {
            mInstance = new GitHubRepository();
        }
        return mInstance;
    }

    private ApiService mApiService;

    private GitHubRepository () {
        mApiService = ApiClient.getClient(BASE_URL).create(ApiService.class);
    }

    public Observable<List<RepoResponse>> getRepoList(String userId) {
        return mApiService.requestRepoList(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
