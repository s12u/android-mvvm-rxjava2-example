package com.tistory.mybstory.mvvm_rxjava2_example.ui;

import com.tistory.mybstory.mvvm_rxjava2_example.databinding.ItemRepoBinding;

import androidx.recyclerview.widget.RecyclerView;

public class RepoViewHolder extends RecyclerView.ViewHolder {

    public final ItemRepoBinding binding;

    public RepoViewHolder(ItemRepoBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
