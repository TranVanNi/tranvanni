package com.example.myapplication2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication2.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    Unbinder unbinder;
    LayoutAnimationController layoutAnimationController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this,root);
        init();
        return root;

    }
    private  void init(){
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_item_from_left);
    }
}

