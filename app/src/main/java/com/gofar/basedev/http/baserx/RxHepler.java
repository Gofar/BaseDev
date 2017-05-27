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


import com.gofar.basedev.base.BaseActivity;
import com.gofar.basedev.entity.BaseEntity;
import com.gofar.basedev.http.HttpResultFun;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/5/27 15:11
 */
public class RxHepler {

    public static <T> Observable<T> toSubscribe(Observable<BaseEntity<T>> observable, final RxAppCompatActivity activity) {
        return observable.subscribeOn(Schedulers.io())
                .map(new HttpResultFun<T>())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        if (activity instanceof BaseActivity) {
                            ((BaseActivity) activity).showLoadingDialog();
                        }
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(activity.<T>bindToLifecycle());
    }
}
