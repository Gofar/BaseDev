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
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/5/27 16:11
 */
public abstract class ResultObserver<T> implements Observer<T> {
    private WeakReference<RxAppCompatActivity> mActivity;

    public ResultObserver(RxAppCompatActivity activity) {
        this.mActivity = new WeakReference<>(activity);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
        RxAppCompatActivity activity = mActivity.get();
        if (activity != null) {
            if (activity instanceof BaseActivity) {
                ((BaseActivity) activity).showToast(e.getMessage());
            }
        }
    }

    @Override
    public void onComplete() {

    }
}
