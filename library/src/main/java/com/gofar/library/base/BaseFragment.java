/*
 * Copyright (C) 2017 Gofar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gofar.library.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gofar.library.utils.ToastUtils;
import com.gofar.library.widget.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: lcf
 * Description: Fragment基类
 * Since: 1.0
 * Date: 2017/5/25 18:14
 */
public abstract class BaseFragment extends Fragment {

    private LoadingDialog mLoadingDialog;
    protected Unbinder mBinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setContentView(inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View parent = inflater.inflate(getLayoutId(), container, false);
        mBinder = ButterKnife.bind(this, parent);
        return parent;
    }

    /**
     * 设置布局文件
     *
     * @return LayoutRes
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    public void onDestroy() {
        if (mBinder != null) {
            mBinder.unbind();
        }
        super.onDestroy();
    }

    /**
     * 展示加载弹窗
     */
    public void showLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog.newInstance();
        }
        mLoadingDialog.show(getFragmentManager());
    }

    /**
     * 隐藏加载弹窗
     */
    public void hideLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    /**
     * Toast文本
     *
     * @param msg 文本
     */
    public void showToast(String msg) {
        if (getActivity() == null || !isAdded() || getActivity().isFinishing()) {
            return;
        }
        ToastUtils.showShort(getContext(), msg);
    }
}
