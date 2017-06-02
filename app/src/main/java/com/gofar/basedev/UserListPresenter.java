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

import com.gofar.basedev.base.BaseListPresenter;
import com.gofar.basedev.entity.UserEntity;
import com.gofar.basedev.http.baserx.HandlerObserver;
import com.gofar.basedev.http.baserx.RxHelper;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/6/2 11:22
 */
public class UserListPresenter extends BaseListPresenter<UserListContract.Model, UserListContract.View, UserEntity> {
    public UserListPresenter(UserListContract.Model mModel, UserListContract.View mView) {
        super(mModel, mView);
    }

    @Override
    public void loadData(@LoadType int loadType) {
        super.loadData(loadType);
        mModel.getUserList(mPage)
                .compose(new RxHelper.SubscribeOnTransformer<List<UserEntity>>())
                .compose(new RxHelper.LoadingTransformer<List<UserEntity>>(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        handlerLoad();
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
                        handlerError();
                    }
                });
    }
}
