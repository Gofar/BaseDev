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

package com.gofar.basedev.ui.activity;

import android.view.View;

import com.gofar.basedev.base.BaseCompatActivity;
import com.gofar.basedev.entity.UserEntity;
import com.gofar.basedev.loading.LoadingAndRetryManager;
import com.gofar.basedev.loading.OnLoadingAndRetryListener;
import com.gofar.basedev.ui.contract.UserDetailsContract;
import com.gofar.basedev.ui.presenter.UserDetailsPresenter;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/6/7 17:35
 */
public class UserDetailsActivity extends BaseCompatActivity implements UserDetailsContract.View {
    private View mView;
    private LoadingAndRetryManager mLoadingAndRetryManager;
    private UserDetailsPresenter mPresenter;

    @Override
    protected void initView() {
        mLoadingAndRetryManager = LoadingAndRetryManager.generate(mView, new OnLoadingAndRetryListener() {
            @Override
            public void setRetryEvent(View retryView) {
                mPresenter.request(1);
            }
        });

        mPresenter = new UserDetailsPresenter(this);
    }

    @Override
    protected void initData() {
        mPresenter.request(1);
    }

    @Override
    protected View getCustomView() {
        return null;
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
        showToast(msg);
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
    public void returnData(UserEntity userEntity) {

    }
}
