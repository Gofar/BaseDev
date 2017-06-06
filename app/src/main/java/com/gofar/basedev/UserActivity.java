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

package com.gofar.basedev;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.gofar.basedev.base.BaseCompatActivity;
import com.gofar.basedev.entity.UserEntity;
import com.gofar.basedev.loading.LoadingAndRetryManager;
import com.gofar.basedev.loading.OnLoadingAndRetryListener;

import butterknife.BindView;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/5/27 16:53
 */
public class UserActivity extends BaseCompatActivity implements UserContract.View {
    UserPresenter mPersenter;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_mobile)
    TextView mTvMobile;

    private View mView;

    private LoadingAndRetryManager mLoadingAndRetryManager;

    @Override
    protected void initView() {
        mLoadingAndRetryManager = LoadingAndRetryManager.generate(mView, new OnLoadingAndRetryListener() {
            @Override
            public void setRetryEvent(View retryView) {
                showLoading();
                mPersenter.request();
            }
        });
    }

    @Override
    protected void initData() {
        showLoading();
        mPersenter.request();
    }

    @Override
    protected View getCustomView() {
        mView = LayoutInflater.from(this).inflate(R.layout.activity_user, null);
        return mView;
    }

    @Override
    public void showLoading() {
        mLoadingAndRetryManager.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingAndRetryManager.showContent();
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showEmpty() {
        mLoadingAndRetryManager.showEmpty();
    }

    @Override
    public void showRetry() {
        mLoadingAndRetryManager.showRetry();
    }

    @Override
    public void returnData(UserEntity entity) {
        mTvName.setText(entity.getName());
        mTvMobile.setText(entity.getMobile());
    }
}
