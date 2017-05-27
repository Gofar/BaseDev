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

import android.view.View;

import com.gofar.basedev.base.BaseCompatActivity;
import com.gofar.basedev.entity.UserEntity;
import com.gofar.basedev.http.ApiFactory;
import com.gofar.basedev.http.baserx.ResultObserver;
import com.gofar.basedev.http.baserx.RxHepler;

import io.reactivex.annotations.NonNull;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/5/27 16:53
 */
public class UserActivity extends BaseCompatActivity {
    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        RxHepler.toSubscribe(ApiFactory.getUserApi().register(), this)
                .subscribe(new ResultObserver<UserEntity>(this) {
                    @Override
                    public void onNext(@NonNull UserEntity userEntity) {

                    }
                });
    }

    @Override
    protected View getCustomView() {
        return null;
    }
}
