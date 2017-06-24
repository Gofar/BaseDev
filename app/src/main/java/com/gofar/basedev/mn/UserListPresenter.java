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

package com.gofar.basedev.mn;

import com.gofar.basedev.entity.UserEntity;
import com.gofar.basedev.http.ApiFactory;
import com.gofar.basedev.http.baserx.HandlerObserver;
import com.gofar.basedev.http.baserx.RxHelper;
import com.gofar.basedev.mvp.BaseModel;
import com.gofar.basedev.mvp.BaseView;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/6/23 16:16
 */
public class UserListPresenter extends BasePresenter<BaseModel, BaseView, List<UserEntity>> {
    private LoadHelper mHelper;

    public UserListPresenter(BaseModel mModel, BaseView mView) {
        super(mModel, mView);
        mHelper = new LoadHelper();
    }

    @Override
    public void onHandlerLoading() {

    }

    @Override
    public void onHandlerResult(List<UserEntity> list) {

    }

    @Override
    public void onHandlerError(Throwable e) {

    }

    public void loadData(@LoadHelper.LoadType int loadType) {
        mHelper.setLoadType(loadType);
        ApiFactory.getUserApi().getUserList(mHelper.mPage, 10)
                .compose(new RxHelper.SubscribeOnTransformer<List<UserEntity>>())
                .compose(new RxHelper.LoadingTransformer<List<UserEntity>>(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        onHandlerLoading();
                    }
                }))
                .compose(new RxHelper.ObserverOnTransformer<List<UserEntity>>(mView))
                .subscribe(new HandlerObserver<List<UserEntity>>() {
                    @Override
                    public void onNext(@NonNull List<UserEntity> list) {
                        onHandlerResult(list);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        onHandlerError(e);
                    }
                });


        RxHelper.doRx2(ApiFactory.getUserApi().getUserList(mHelper.mPage, 10));
        RxHelper.doRx(ApiFactory.getUserApi().changePsd("", ""), this, false);
    }
}
