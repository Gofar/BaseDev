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
import com.gofar.basedev.http.ApiFactory;
import com.gofar.basedev.ui.contract.ChangePsdContract;

import io.reactivex.Observable;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/6/7 17:27
 */
public class ChangePsdModel implements ChangePsdContract.Model {
    @Override
    public Observable<BaseEntity> changePsd(String oldPsd, String newPsd) {
        return ApiFactory.getUserApi().changePsd(oldPsd, newPsd);
    }
}
