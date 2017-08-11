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

package com.gofar.basedev.mvp.extension;

import com.gofar.basedev.common.Constants;
import com.gofar.basedev.mvp.BaseModel;
import com.gofar.basedev.mvp.BasePresenter;

import java.util.List;
import java.util.Map;

/**
 * Author: lcf
 * Description: 列表基类Presenter
 * Since: 1.0
 * Date: 2017/7/14 15:08
 */
public abstract class BaseListPresenter<M extends BaseModel, V extends BaseListView<T>, T> extends BasePresenter<M, V, List<T>> {
    protected LoadListHelper mLoadHelper;
    protected Map<String, String> mParams; // 请求参数

    public BaseListPresenter(V mView) {
        super(mView);
        mLoadHelper = new LoadListHelper();
    }

    @Override
    public void onHandlerLoading() {
        if (mLoadHelper.mLoadType == LoadListHelper.FIRST_LOAD) {
            mView.showLoading();
        }
    }

    @Override
    public void onHandlerResult(List<T> ts) {
        if (mLoadHelper.mLoadType == LoadListHelper.FIRST_LOAD) {
            if (ts == null || ts.isEmpty()) {
                mView.showEmpty();
                mView.newData(null);
            } else {
                mLoadHelper.mPage++;
                mView.hideLoading();
                mView.newData(ts);
                if (ts.size() < Constants.LIMIT_NUM) { //只有一页，不显示footer
                    mView.loadMoreEnd(true);
                }
            }
        } else if (mLoadHelper.mLoadType == LoadListHelper.LOAD_MORE) {
            if (ts == null || ts.isEmpty()) {
                if (mLoadHelper.mPage == 2) { // 当前第二页请求
                    mView.loadMoreEnd(true);
                } else {
                    mView.loadMoreEnd(false);
                }
            } else {
                mLoadHelper.mPage++;
                mView.loadMoreCompleted();
                mView.addData(ts);
            }
        } else {
            if (ts == null || ts.isEmpty()) {
                mView.showEmpty();
                mView.newData(null);
            } else {
                mLoadHelper.mPage++;
                mView.refreshCompleted();
                mView.newData(ts);
            }
        }
    }

    @Override
    public void onHandlerError(Throwable e) {
        e.printStackTrace();
        if (mLoadHelper.mLoadType == LoadListHelper.FIRST_LOAD) {
            mView.showRetry();
        } else if (mLoadHelper.mLoadType == LoadListHelper.REFRESH) {
            mView.refreshCompleted();
            mView.showMessage(e.getMessage());
        } else {
            mView.loadMoreFailed();
        }
    }

    public abstract void loadData(@LoadListHelper.LoadType int type);

    /**
     * set request params
     *
     * @param params request params
     */
    public void setParams(Map<String, String> params) {
        this.mParams = params;
    }
}
