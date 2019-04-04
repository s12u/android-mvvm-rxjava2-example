package com.tistory.mybstory.mvvm_rxjava2_example.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tistory.mybstory.mvvm_rxjava2_example.R;
import com.tistory.mybstory.mvvm_rxjava2_example.databinding.ItemRepoBinding;
import com.tistory.mybstory.mvvm_rxjava2_example.model.Repo;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ReposAdapter extends RecyclerView.Adapter<RepoViewHolder> {

    private LayoutInflater mInflater = null;
    private List<Repo> reposList = new ArrayList<>();

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }

        ItemRepoBinding binding = DataBindingUtil
                .inflate(mInflater, R.layout.item_repo, parent, false);
        return new RepoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        Repo repo = reposList.get(position);
        holder.binding.tvRepoName.setText(repo.getName());
        holder.binding.tvRepoDesc.setText(repo.getDescription());
    }

    @Override
    public int getItemCount() {
        return reposList.size();
    }

    public void setData(List<Repo> reposList) {
        this.reposList = reposList;
        notifyDataSetChanged();
    }
}
