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

package com.gofar.basedev.base.extension;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gofar.basedev.R;
import com.gofar.basedev.base.BaseFragment;
import com.gofar.basedev.loading.LoadingAndRetryManager;
import com.gofar.basedev.loading.OnLoadingAndRetryListener;
import com.gofar.basedev.mvp.extension.BaseListPresenter;
import com.gofar.basedev.mvp.extension.BaseListView;
import com.gofar.basedev.mvp.extension.LoadListHelper;

import java.util.List;

import butterknife.BindView;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/8/9 17:10
 */
public abstract class BaseListFragment<T> extends BaseFragment implements BaseListView<T>, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.layout_root)
    LinearLayout mLayoutRoot;

    private LoadingAndRetryManager mLoadingAndRetryManager;

    protected BaseQuickAdapter<T, ? extends BaseViewHolder> mAdapter;
    protected BaseListPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.base_list_layout;
    }

    @Override
    protected void initView() {
        // init loading view
        mLoadingAndRetryManager = LoadingAndRetryManager.generate(mSwipeRefresh, new OnLoadingAndRetryListener() {
            @Override
            public void setRetryEvent(View retryView) {
                setRetry(retryView);
            }
        });

        initRecyclerView(mRecyclerView);
        mAdapter = getAdapter();
        if (mAdapter == null) {
            throw new NullPointerException("Adapter is null");
        } else {
            mRecyclerView.setAdapter(mAdapter);
            mSwipeRefresh.setOnRefreshListener(this);
        }
    }

    @Override
    protected void initData() {
        initPresenter();
        if (mPresenter != null) {
            mPresenter.loadData(LoadListHelper.FIRST_LOAD);
        }else{
            showEmpty();
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
    public void showEmpty() {
        mLoadingAndRetryManager.showEmpty();
    }

    @Override
    public void showRetry() {
        mLoadingAndRetryManager.showRetry();
    }

    @Override
    public void loadMoreFailed() {
        mAdapter.loadMoreFail();
    }

    @Override
    public void loadMoreEnd(boolean gone) {
        mAdapter.loadMoreEnd(gone);
    }

    @Override
    public void loadMoreCompleted() {
        mAdapter.loadMoreComplete();
    }

    @Override
    public void refreshCompleted() {
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void newData(List<T> data) {
        mAdapter.setNewData(data);
    }

    @Override
    public void addData(List<T> data) {
        mAdapter.addData(data);
    }

    @Override
    public void onRefresh() {
        mPresenter.loadData(LoadListHelper.REFRESH);
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
                mPresenter.loadData(LoadListHelper.FIRST_LOAD);
            }
        });
    }

    /**
     * init RecyclerView
     */
    protected void initRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * get adapter
     *
     * @return
     */
    protected abstract BaseQuickAdapter<T, ? extends BaseViewHolder> getAdapter();

    /**
     * init presenter
     *
     * @return
     */
    protected abstract void initPresenter();
}
