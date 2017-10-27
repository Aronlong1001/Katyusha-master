package com.katyusha.aron.demo.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by aron on 2017/7/13.
 */

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment{

    protected T binding;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(initLayout(), null, false);
        binding = DataBindingUtil.bind(v);
        init(binding);
        return v;
    }

    protected abstract @LayoutRes int initLayout();
    protected abstract void init(T binding);
}
