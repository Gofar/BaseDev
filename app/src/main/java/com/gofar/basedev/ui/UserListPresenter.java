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

import android.text.TextUtils;

import com.gofar.basedev.entity.UserEntity;
import com.gofar.library.common.Constants;
import com.gofar.library.http.RxHelper;
import com.gofar.library.mvp.extension.BaseListPresenter;
import com.gofar.library.mvp.extension.LoadListHelper;

import java.util.HashMap;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/8/11 15:05
 */
public class UserListPresenter extends BaseListPresenter<UserListContract.Model, UserListContract.View, UserEntity> {
    public UserListPresenter(UserListContract.View mView) {
        super(mView);
    }

    @Override
    public void loadData(@LoadListHelper.LoadType int type) {
        mLoadHelper.setLoadType(type);
        if (mParams == null) {
            mParams = new HashMap<>();
        }
        if (TextUtils.isEmpty(mParams.get(Constants.LIMIT_TEXT))) { // 加入默认请求个数
            mParams.put(Constants.LIMIT_TEXT, Integer.toString(Constants.LIMIT_NUM));
        }
        mParams.put(Constants.PAGE_TEXT, Integer.toString(mLoadHelper.mPage)); // add page num
        RxHelper.doRx1(mModel.getUserList(mParams), this);
    }
}
