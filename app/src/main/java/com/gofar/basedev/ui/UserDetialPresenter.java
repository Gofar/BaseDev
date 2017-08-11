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

package com.gofar.basedev.ui;

import com.gofar.basedev.entity.UserEntity;
import com.gofar.basedev.http.RxHelper;
import com.gofar.basedev.mvp.extension.BaseDetailsPresenter;
import com.gofar.basedev.ui.UserDetailsContract;

import java.util.HashMap;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/8/11 16:41
 */
public class UserDetialPresenter extends BaseDetailsPresenter<UserDetailsContract.Model, UserDetailsContract.View, UserEntity> {
    public UserDetialPresenter(UserDetailsContract.View mView) {
        super(mView);
    }

    @Override
    public void load() {
        if (mParams == null) {
            mParams = new HashMap<>();
        }
        RxHelper.doRx1(mModel.getUserDetails(mParams), this, true);
    }
}
