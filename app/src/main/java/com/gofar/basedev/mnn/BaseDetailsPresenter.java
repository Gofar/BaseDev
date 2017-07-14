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

package com.gofar.basedev.mnn;

import com.gofar.basedev.mvp.BaseModel;

/**
 * Author: lcf
 * Description: 详情页基类Presenter
 * Since: 1.0
 * Date: 2017/7/14 11:46
 */
public abstract class BaseDetailsPresenter<M extends BaseModel, V extends BaseDetailsView<T>, T> extends BasePresenter<M, V,T> {
    public BaseDetailsPresenter(M mModel, V mView) {
        super(mModel, mView);
    }

    @Override
    protected void onHandlerLoading() {
        mView.showLoading();
    }

    @Override
    protected void onHandlerResult(T t) {
        if (t == null) {
            mView.showEmpty();
        } else {
            mView.hideLoading();
            mView.returnData(t);
        }
    }

    @Override
    protected void onHandlerError(Throwable e) {
        e.printStackTrace();
        mView.hideLoading();
        mView.showRetry();
    }
}
