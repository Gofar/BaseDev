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

package com.gofar.basedev.http.baserx;


import com.gofar.basedev.base.BaseView;
import com.gofar.basedev.entity.BaseEntity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/5/27 15:11
 */
public class RxHelper {

    public static <T> Observable<T> toSubscribe(Observable<BaseEntity<T>> observable, final BaseView baseView) {
        return observable.flatMap(new HttpResultFun<T>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        baseView.showLoading();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        baseView.hideLoading();
                    }
                })
                .compose(RxUtils.<T>bindToLifecycle(baseView));
    }


    public static <T> Observable<T> toSubscribeOn(Observable<BaseEntity<T>> observable, final BaseView baseView) {
        return observable.flatMap(new HttpResultFun<T>())
                .subscribeOn(Schedulers.io());
    }

    public static <T> Observable<T> toCache(Observable<T> observable, Consumer<T> consumer) {
        return observable.observeOn(Schedulers.io())
                .doOnNext(consumer);
    }

    public static <T> Observable<T> toShowLoading(Observable<T> observable, final BaseView view) {
        return observable.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(@NonNull Disposable disposable) throws Exception {
                view.showLoading();
            }
        }).subscribeOn(AndroidSchedulers.mainThread());
    }

    public static <T> Observable<T> toObserveOn(Observable<T> observable, final BaseView view) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        view.hideLoading();
                    }
                }).compose(RxUtils.<T>bindToLifecycle(view));
    }

    /**
     * 指定事件发生在io线程
     * 处理返回结果
     *
     * @param <T>
     */
    public static class SubscribeOnTransformer<T> implements ObservableTransformer<BaseEntity<T>, T> {

        @Override
        public ObservableSource<T> apply(@NonNull Observable<BaseEntity<T>> upstream) {
            return upstream.flatMap(new HttpResultFun<T>())
                    .subscribeOn(Schedulers.io());
        }
    }

    /**
     * 缓存数据
     *
     * @param <T>
     */
    public static class CacheTransformer<T> implements ObservableTransformer<T, T> {

        private Consumer<T> consumer;

        public CacheTransformer(Consumer<T> consumer) {
            this.consumer = consumer;
        }

        @Override
        public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
            return upstream.observeOn(Schedulers.io())
                    .doOnNext(consumer);
        }
    }

    /**
     * 显示加载loading
     *
     * @param <T>
     */
    public static class LoadingTransformer<T> implements ObservableTransformer<T, T> {
        private Consumer<Disposable> consumer;

        public LoadingTransformer(Consumer<Disposable> consumer) {
            this.consumer = consumer;
        }

        @Override
        public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
            return upstream.doOnSubscribe(consumer).subscribeOn(AndroidSchedulers.mainThread());
        }
    }

    /**
     * 指定事件消费在main线程
     * 使用RxLifecycle关联生命周期
     * 隐藏加载loading
     *
     * @param <T>
     */
    public static class ObserverOnTransformer<T> implements ObservableTransformer<T, T> {
        private BaseView baseView;

        public ObserverOnTransformer(BaseView baseView) {
            this.baseView = baseView;
        }

        @Override
        public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
            return upstream.observeOn(AndroidSchedulers.mainThread())
//                    .doOnTerminate(new Action() {
//                        @Override
//                        public void run() throws Exception {
//                            baseView.hideLoading();
//                        }
//                    })
                    .compose(RxUtils.<T>bindToLifecycle(baseView));
        }
    }
}
