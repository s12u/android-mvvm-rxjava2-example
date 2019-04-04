package com.tistory.mybstory.mvvm_rxjava2_example.ui;

import android.widget.EditText;

import com.jakewharton.rxbinding3.widget.RxTextView;
import com.tistory.mybstory.mvvm_rxjava2_example.model.Repo;
import com.tistory.mybstory.mvvm_rxjava2_example.model.RepoResponse;
import com.tistory.mybstory.mvvm_rxjava2_example.repo.GitHubRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class GithubViewModel extends ViewModel {

    private GitHubRepository mGitHubRepository;
    private CompositeDisposable mCompositeDisposable;

    private MutableLiveData<List<Repo>> mReposLiveData;

    public GithubViewModel() {
        init();
    }

    @Override
    protected void onCleared() {
        mGitHubRepository = null;
        mCompositeDisposable.clear();
        super.onCleared();
    }

    private void init() {
        mGitHubRepository = GitHubRepository.getInstance();
        mCompositeDisposable = new CompositeDisposable();
        mReposLiveData = new MutableLiveData<>();
    }

    public void fetchRepoList(String userId) {

        mCompositeDisposable.add(mGitHubRepository.getRepoList(userId).map(new Function<List<RepoResponse>, List<Repo>>() {
            @Override
            public List<Repo> apply(List<RepoResponse> repoResponses) throws Exception {
                List<Repo> reposList = new ArrayList<>();
                for (RepoResponse response : repoResponses) {
                    reposList.add(new Repo(response.getName(), response.getDescription()));
                }
                return reposList;
            }
        }).subscribe(new Consumer<List<Repo>>() {
            @Override
            public void accept(List<Repo> repos) throws Exception {
                mReposLiveData.postValue(repos);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mReposLiveData.postValue(Collections.emptyList());
            }
        }));
    }

    public LiveData<List<Repo>> getRepoList() {
        return mReposLiveData;
    }

    public void observeTextChanges(EditText editText) {
        mCompositeDisposable.add(RxTextView.textChanges(editText)
                .map(CharSequence::toString)
                .filter(text -> text.length() > 0)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String userId) throws Exception {
                        fetchRepoList(userId);
                    }
                }));
    }
}
