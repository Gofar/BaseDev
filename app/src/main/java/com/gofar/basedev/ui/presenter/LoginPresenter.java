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

import com.gofar.basedev.entity.BaseEntity;
import com.gofar.basedev.entity.UserEntity;
import com.gofar.basedev.http.baserx.HandlerObserver;
import com.gofar.basedev.http.baserx.HttpResultFun;
import com.gofar.basedev.http.baserx.RxHelper;
import com.gofar.basedev.mvp.BasePresenter;
import com.gofar.basedev.ui.contract.LoginContract;
import com.gofar.basedev.ui.model.LoginModel;
import com.google.gson.annotations.Expose;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.R.attr.handle;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/6/7 14:48
 */
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    public LoginPresenter(LoginContract.View mView) {
        super(new LoginModel(), mView);
    }

    public void login(String userName, String password) {
        mModel.login(userName, password)
                .compose(new RxHelper.SubscribeOnTransformer<UserEntity>())
                .compose(new RxHelper.LoadingTransformer<UserEntity>(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        handlerLoading();
                    }
                }))
                .compose(new RxHelper.ObserverOnTransformer<UserEntity>(mView))
                .subscribe(new HandlerObserver<UserEntity>() {
                    @Override
                    public void onNext(@NonNull UserEntity entity) {
                        handlerResult();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        handlerError(e);
                    }
                });
    }

    @Override
    protected void handlerLoading() {
        mView.showLoading();
    }

    @Override
    protected void handlerError(Throwable e) {
        mView.hideLoading();
        mView.showMessage(e.getMessage());
    }

    private void handlerResult() {
        mView.hideLoading();
        mView.success();
    }
}