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

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gofar.basedev.base.BaseCompatActivity;
import com.gofar.basedev.base.BaseListPresenter;
import com.gofar.basedev.base.UserListModel;
import com.gofar.basedev.common.Constants;
import com.gofar.basedev.entity.UserEntity;
import com.gofar.basedev.loading.LoadingAndRetryManager;
import com.gofar.basedev.loading.OnLoadingAndRetryListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/6/1 16:58
 */
public class UserListActivity extends BaseCompatActivity implements UserListContract.View
        , SwipeRefreshLayout.OnRefreshListener
        , BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.lay_refresh)
    SwipeRefreshLayout mRefresh;

    private View mContentView;
    private LoadingAndRetryManager mLoadingAndRetryManager;
    private UserListPresenter mPresenter;
    private UserAdapter mAdapter;
    private List<UserEntity> mList;

    @Override
    protected void initView() {
        mLoadingAndRetryManager = LoadingAndRetryManager.generate(mContentView, new OnLoadingAndRetryListener() {
            @Override
            public void setRetryEvent(View retryView) {
                mPresenter.loadData();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mList = new ArrayList<>();
        mAdapter = new UserAdapter();
        mAdapter.setNewData(mList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                UserEntity entity = mList.get(position);
                Intent intent = new Intent(UserListActivity.this, UserActivity.class);
                intent.putExtra(Constants.EXTRAS_ID, entity.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter = new UserListPresenter(new UserListModel(), this);
        mPresenter.loadData(BaseListPresenter.TYPE_FIRST_LOAD);
    }

    @Override
    protected View getCustomView() {
        mContentView = LayoutInflater.from(this).inflate(R.layout.activity_user_list, null);
        return mContentView;
    }

    @Override
    public void onRefresh() {
        mAdapter.setEnableLoadMore(false);
        mPresenter.loadData(BaseListPresenter.TYPE_REFRESH);
    }

    @Override
    public void onLoadMoreRequested() {
        mRefresh.setEnabled(false);
        mPresenter.loadData(BaseListPresenter.TYPE_LOAD_MORE);
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
    public void refreshCompleted() {
        mRefresh.setRefreshing(false);
        mAdapter.setEnableLoadMore(true);
    }

    @Override
    public void loadMoreEnd(boolean gone) {
        mAdapter.loadMoreEnd(gone);
    }

    @Override
    public void loadMoreFailed() {
        mAdapter.loadMoreFail();
    }

    @Override
    public void loadMoreComplete() {
        mRefresh.setEnabled(true);
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
}
