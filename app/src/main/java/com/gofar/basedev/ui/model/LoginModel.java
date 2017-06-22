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

package com.gofar.basedev.ui.model;

import com.gofar.basedev.entity.BaseEntity;
import com.gofar.basedev.entity.UserEntity;
import com.gofar.basedev.http.ApiFactory;
import com.gofar.basedev.ui.contract.LoginContract;

import io.reactivex.Observable;

/**
 * Author: lcf
 * Description:
 * Since: .0
 * Date: 2017/6/7 16:08
 */
public class LoginModel implements LoginContract.Model {
    @Override
    public Observable<BaseEntity<UserEntity>> login(String userName, String password) {
        return ApiFactory.getUserApi().login(userName, password);
    }
}