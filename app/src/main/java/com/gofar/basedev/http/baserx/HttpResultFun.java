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

import com.gofar.basedev.entity.BaseEntity;
import com.gofar.basedev.http.ApiException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/5/27 15:25
 */
public class HttpResultFun<T> implements Function<BaseEntity<T>, ObservableSource<T>> {

    @Override
    public ObservableSource<T> apply(@NonNull final BaseEntity<T> baseEntity) throws Exception {
        if (baseEntity.getCode() == 0) {
            return Observable.create(new ObservableOnSubscribe<T>() {
                @Override
                public void subscribe(@NonNull ObservableEmitter<T> e) throws Exception {
                    e.onNext(baseEntity.getData());
                    e.onComplete();
                }
            });
        } else {
            return Observable.error(new ApiException(baseEntity.getCode()));
        }
    }
}
