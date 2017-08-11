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

package com.gofar.basedev.ui;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gofar.basedev.entity.UserEntity;
import com.gofar.library.base.extension.BaseListFragment;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/8/11 15:47
 */
public class UserListFragment extends BaseListFragment<UserEntity> implements UserListContract.View {
    private UserItemAdapter mAdapter;
    @Override
    protected BaseQuickAdapter<UserEntity, ? extends BaseViewHolder> getAdapter() {
        mAdapter=new UserItemAdapter(null);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        return mAdapter;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new UserListPresenter(this);
    }

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        super.initRecyclerView(recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }
}
