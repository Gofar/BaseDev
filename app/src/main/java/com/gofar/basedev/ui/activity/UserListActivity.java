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

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gofar.basedev.R;
import com.gofar.basedev.UserAdapter;
import com.gofar.basedev.base.BaseCompatActivity;
import com.gofar.basedev.entity.UserEntity;
import com.gofar.basedev.loading.LoadingAndRetryManager;
import com.gofar.basedev.loading.OnLoadingAndRetryListener;
import com.gofar.basedev.ui.LoadType;
import com.gofar.basedev.ui.contract.UserListContract;
import com.gofar.basedev.ui.presenter.UserListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/6/7 15:51
 */
public class UserListActivity extends BaseCompatActivity implements UserListContract.View
        , SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    private View mView;
    private LoadingAndRetryManager mLoadingAndRetryManager;
    private UserAdapter mAdapter;
    private List<UserEntity> mList;

    private UserListPresenter mPresenter;

    @Override
    protected void initView() {
        mLoadingAndRetryManager = LoadingAndRetryManager.generate(mView, new OnLoadingAndRetryListener() {
            @Override
            public void setRetryEvent(View retryView) {

            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mAdapter = new UserAdapter();
        mAdapter.setNewData(mList);
        mRecyclerView.setAdapter(mAdapter);

        mPresenter = new UserListPresenter(this);
    }

    @Override
    protected void initData() {
        mPresenter.request();
    }

    @Override
    protected View getCustomView() {
        mView = LayoutInflater.from(this).inflate(R.layout.activity_user_list, null);
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
    public void refreshComplete() {
        mSwipeRefresh.setRefreshing(false);
        mAdapter.setEnableLoadMore(true);
    }

    @Override
    public void loadComplete() {
        mAdapter.loadMoreComplete();
        mSwipeRefresh.setEnabled(true);
    }

    @Override
    public void loadMoreEnd(boolean gone) {
        mAdapter.loadMoreEnd(gone);
        mSwipeRefresh.setEnabled(true);
    }

    @Override
    public void loadMoreFailed() {
        mAdapter.loadMoreFail();
        mSwipeRefresh.setEnabled(true);
    }

    @Override
    public void returnData(List<UserEntity> list) {
        mAdapter.addData(list);
    }

    @Override
    public void refreshData(List<UserEntity> list) {
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        mAdapter.setEnableLoadMore(false);
        mPresenter.request(LoadType.TYPE_REFRESH);
    }

    @Override
    public void onLoadMoreRequested() {
        mSwipeRefresh.setEnabled(false);
        mPresenter.request(LoadType.TYPE_LOAD_MORE);
    }
}
