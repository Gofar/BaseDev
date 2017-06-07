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

package com.gofar.basedev.ui.presenter;


import com.gofar.basedev.entity.UserEntity;
import com.gofar.basedev.http.baserx.HandlerObserver;
import com.gofar.basedev.http.baserx.RxHelper;
import com.gofar.basedev.mvp.BasePresenter;
import com.gofar.basedev.ui.LoadType;
import com.gofar.basedev.ui.contract.UserListContract;
import com.gofar.basedev.ui.model.UserListModel;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/6/7 15:59
 */
public class UserListPresenter extends BasePresenter<UserListContract.Model, UserListContract.View> {

    private int mLoadType;
    private int mPage;

    public UserListPresenter(UserListContract.View mView) {
        super(new UserListModel(), mView);
    }

    @Override
    protected void handlerLoading() {
        if (mLoadType == LoadType.TYPE_FIRST_LOAD) {
            mView.showLoading();
        }
    }

    @Override
    protected void handlerError(Throwable e) {
        switch (mLoadType) {
            case LoadType.TYPE_FIRST_LOAD:
                mView.showRetry();
                break;
            case LoadType.TYPE_LOAD_MORE:
                mView.loadMoreFailed();
                break;
            case LoadType.TYPE_REFRESH:
                mView.refreshComplete();
                mView.showMessage(e.getMessage());
                break;
        }
    }

    private void handlerResult(List<UserEntity> entities) {
        switch (mLoadType) {
            case LoadType.TYPE_FIRST_LOAD:
                if (entities == null || entities.isEmpty()) {
                    mView.showEmpty();
                } else {
                    mView.hideLoading();
                    mView.returnData(entities);
                }
                break;
            case LoadType.TYPE_LOAD_MORE:
                if (entities == null || entities.isEmpty()) {
                    mView.loadMoreEnd(false);
                } else {
                    mView.loadComplete();
                    mView.returnData(entities);
                    mPage++;
                }
                break;
            case LoadType.TYPE_REFRESH:
                mView.refreshComplete();
                mView.refreshData(entities);
                if (entities == null || entities.isEmpty()) {
                    mView.showEmpty();
                }
                break;
        }
    }

    public void request() {
        this.request(LoadType.TYPE_FIRST_LOAD);
    }

    public void request(int loadType) {
        mLoadType = loadType;
        if (mLoadType == LoadType.TYPE_FIRST_LOAD) {
            mPage = 1;
        }
        mModel.getUserList(mPage)
                .compose(new RxHelper.SubscribeOnTransformer<List<UserEntity>>())
                .compose(new RxHelper.LoadingTransformer<List<UserEntity>>(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        handlerLoading();
                    }
                }))
                .compose(new RxHelper.ObserverOnTransformer<List<UserEntity>>(mView))
                .subscribe(new HandlerObserver<List<UserEntity>>() {
                    @Override
                    public void onNext(@NonNull List<UserEntity> list) {
                        handlerResult(list);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        handlerError(e);
                    }
                });
    }
}
