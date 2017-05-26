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

package com.gofar.basedev.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gofar.basedev.utils.ToastUtils;
import com.gofar.basedev.widget.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: lcf
 * Description: Activity基类
 * Since: 1.0
 * Date: 2017/5/25 18:03
 */
public abstract class BaseActivity extends AppCompatActivity {

    private LoadingDialog mLoadingDialog;
    protected Unbinder mBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentLayout(getLayoutId());

        initView();
        initData();
    }

    protected void setContentLayout(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mBinder = ButterKnife.bind(this);
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
    protected void onDestroy() {
        if (mBinder != null) {
            mBinder.unbind();
        }
        super.onDestroy();
    }

    /**
     * 展示加载弹窗
     */
    public void showLoadingDialog() {
        mLoadingDialog = LoadingDialog.newInstance();
        mLoadingDialog.show(getSupportFragmentManager());
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
        ToastUtils.showShort(this, msg);
    }
}
