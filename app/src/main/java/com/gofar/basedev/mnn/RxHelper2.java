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

import com.gofar.basedev.entity.BaseEntity;
import com.gofar.basedev.http.baserx.HandlerObserver;
import com.gofar.basedev.http.baserx.RxHelper;
import com.gofar.basedev.mvp.BaseModel;
import com.gofar.basedev.mvp.BaseView;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/7/14 15:35
 */
public class RxHelper2 {

    public static void doRx(Observable<BaseEntity> observable, final BasePresenter<? extends BaseModel, ? extends BaseView, BaseEntity> presenter) {
        observable.compose(new RxHelper.SubscribeOnTransformer1())
                .compose(new RxHelper.LoadingTransformer<BaseEntity>(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        presenter.onHandlerLoading();
                    }
                }))
                .compose(new RxHelper.ObserverOnTransformer<BaseEntity>(presenter.getView()))
                .subscribe(new HandlerObserver<BaseEntity>() {
                    @Override
                    public void onNext(@NonNull BaseEntity entity) {
                        presenter.onHandlerResult(entity);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        presenter.onHandlerError(e);
                    }
                });
    }

    public static <T> void doRx2(Observable<BaseEntity<T>> observable, final BasePresenter presenter, final boolean needCache) {
        observable.compose(new RxHelper.SubscribeOnTransformer<T>())
                .compose(new RxHelper.LoadingTransformer<T>(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        presenter.onHandlerLoading();
                    }
                }))
                .compose(new RxHelper.CacheTransformer<>(new Consumer<T>() {
                    @Override
                    public void accept(@NonNull T t) throws Exception {
                        presenter.cacheData(needCache, t);
                    }
                }))
                .compose(new RxHelper.ObserverOnTransformer<T>(presenter.getView()))
                .subscribe(new HandlerObserver<T>() {
                    @Override
                    public void onNext(@NonNull T t) {
                        presenter.onHandlerResult(t);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        presenter.onHandlerError(e);
                    }
                });
    }

    public static <T> void doRx2(Observable<BaseEntity<T>> observable, BasePresenter presenter) {
        doRx2(observable, presenter, false);
    }

    public static <T> void doRx3(Observable<BaseEntity<T>> observable, final BasePresenter<? extends BaseModel,? extends BaseView,T> presenter, final boolean needCache) {
        observable.compose(new RxHelper.SubscribeOnTransformer<T>())
                .compose(new RxHelper.LoadingTransformer<T>(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        presenter.onHandlerLoading();
                    }
                }))
                .compose(new RxHelper.CacheTransformer<>(new Consumer<T>() {
                    @Override
                    public void accept(@NonNull T t) throws Exception {
                        presenter.cacheData(needCache, t);
                    }
                }))
                .compose(new RxHelper.ObserverOnTransformer<T>(presenter.getView()))
                .subscribe(new HandlerObserver<T>() {
                    @Override
                    public void onNext(@NonNull T t) {
                        presenter.onHandlerResult(t);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        presenter.onHandlerError(e);
                    }
                });
    }
}
