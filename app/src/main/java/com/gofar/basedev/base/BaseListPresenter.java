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

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;


/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/6/2 16:08
 */
public class BaseListPresenter<T extends BaseModel, R extends BaseListView<V>, V> extends BasePresenter<T, R> {

    public static final int TYPE_FIRST_LOAD = 0;
    public static final int TYPE_LOAD_MORE = 1;
    public static final int TYPE_REFRESH = 2;

    public BaseListPresenter(T mModel, R mView) {
        super(mModel, mView);
    }

    @IntDef({TYPE_FIRST_LOAD, TYPE_LOAD_MORE, TYPE_REFRESH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LoadType {

    }

    protected int mPage = 1;

    private int mLoadType;

    public void loadData() {
        loadData(TYPE_FIRST_LOAD);
    }

    public void loadData(@LoadType int loadType) {
        mLoadType = loadType;
        if (loadType == TYPE_REFRESH) {
            mPage = 1;
        }
    }

    protected void handlerLoad() {
        if (mLoadType == TYPE_FIRST_LOAD) {
            mView.showLoading();
        }
    }

    protected void handlerResult(List<V> data) {
        switch (mLoadType) {
            case TYPE_FIRST_LOAD:
                if (data == null || data.isEmpty()) {
                    mView.showEmpty();
                } else {
                    mPage++;
                    mView.returnData(data);
                    mView.hideLoading();
                    if (data.size() < 15) {  //
                        mView.loadMoreEnd(true);
                    }
                }
                break;
            case TYPE_LOAD_MORE:
                if (data == null || data.isEmpty()) {
                    mView.loadMoreEnd(false);
                } else {
                    mPage++;
                    mView.returnData(data);
                    mView.loadMoreComplete();
                }
                break;
            case TYPE_REFRESH:
                if (data == null || data.isEmpty()) {
                    mView.showEmpty();
                } else {
                    mPage++;
                    mView.refreshData(data);
                    mView.refreshCompleted();
                }
                break;
        }
    }

    protected void handlerError() {
        switch (mLoadType) {
            case TYPE_FIRST_LOAD:
                mView.showRetry();
                break;
            case TYPE_LOAD_MORE:
                mView.loadMoreFailed();
                break;
            case TYPE_REFRESH:
                break;
        }
    }
}
