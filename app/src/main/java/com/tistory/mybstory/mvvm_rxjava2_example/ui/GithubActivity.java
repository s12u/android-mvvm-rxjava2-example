package com.tistory.mybstory.mvvm_rxjava2_example.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jakewharton.rxbinding3.widget.RxTextView;
import com.tistory.mybstory.mvvm_rxjava2_example.R;
import com.tistory.mybstory.mvvm_rxjava2_example.databinding.ActivityGithubBinding;
import com.tistory.mybstory.mvvm_rxjava2_example.model.Repo;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GithubActivity extends AppCompatActivity {

    private ActivityGithubBinding binding;
    private GithubViewModel viewModel;
    private ReposAdapter adapter = new ReposAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_github);
        binding.setGitHubViewModel(ViewModelProviders.of(this).get(GithubViewModel.class));
        viewModel = binding.getGitHubViewModel();

        binding.rvRepoList.setAdapter(adapter);
        observeRepoList();
        viewModel.observeTextChanges(binding.etUsername);
    }

    private void observeRepoList() {
        viewModel.getRepoList().observe(this, new Observer<List<Repo>>() {
            @Override
            public void onChanged(List<Repo> reposList) {
                adapter.setData(reposList);
                binding.tvEmptyResult.setVisibility(reposList.size() > 0 ? View.GONE : View.VISIBLE);
            }
        });
    }

}
