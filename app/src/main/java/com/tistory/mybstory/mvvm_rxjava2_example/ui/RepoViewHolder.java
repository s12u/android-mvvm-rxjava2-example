package com.tistory.mybstory.mvvm_rxjava2_example.ui;

import android.view.View;

import com.tistory.mybstory.mvvm_rxjava2_example.databinding.ItemRepoBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RepoViewHolder extends RecyclerView.ViewHolder {

    public final ItemRepoBinding binding;

    public RepoViewHolder(ItemRepoBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
