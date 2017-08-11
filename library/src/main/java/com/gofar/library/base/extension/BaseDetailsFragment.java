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

package com.gofar.library.base.extension;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.gofar.library.R;
import com.gofar.library.R2;
import com.gofar.library.base.BaseFragment;
import com.gofar.library.loading.LoadingAndRetryManager;
import com.gofar.library.loading.OnLoadingAndRetryListener;
import com.gofar.library.mvp.extension.BaseDetailsPresenter;
import com.gofar.library.mvp.extension.BaseDetailsView;

import butterknife.BindView;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/8/11 16:16
 */
public abstract class BaseDetailsFragment<T> extends BaseFragment implements BaseDetailsView<T> {
    @BindView(R2.id.content)
    FrameLayout mContent;
    @BindView(R2.id.layout_root)
    LinearLayout mLayoutRoot;

    private LoadingAndRetryManager mLoadingAndRetryManager;

    private BaseDetailsPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.base_details_layout;
    }

    @Override
    protected void initView() {
        // init loading view
        mLoadingAndRetryManager = LoadingAndRetryManager.generate(mContent, new OnLoadingAndRetryListener() {
            @Override
            public void setRetryEvent(View retryView) {
                setRetry(retryView);
            }
        });
    }

    @Override
    protected void initData() {
        initPresenter();
        if (mPresenter != null) {
            mPresenter.load();
        }
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
    public void returnData(T t) {

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
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    /**
     * set retry
     *
     * @param retryView retryView
     */
    private void setRetry(View retryView) {
        retryView.findViewById(R.id.id_btn_retry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.load();
            }
        });
    }

    /**
     * init presenter
     *
     * @return
     */
    protected abstract void initPresenter();
}
