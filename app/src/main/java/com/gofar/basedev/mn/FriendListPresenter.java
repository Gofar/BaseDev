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

package com.gofar.basedev.mn;

import com.gofar.basedev.entity.UserEntity;
import com.gofar.basedev.mvp.BaseModel;

import java.util.List;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/7/11 17:55
 */
public class FriendListPresenter extends BaseListPresenter<BaseModel, BaseListView<UserEntity>, UserEntity> {
    public FriendListPresenter(BaseModel mModel, BaseListView<UserEntity> mView) {
        super(mModel, mView);
    }

    @Override
    public void storeToDisk(List<UserEntity> list) {

    }

    private void loadData(@LoadHelper.LoadType int loadType){
        mLoadHelper.setLoadType(loadType);
        //RxHelper.doRx5(ApiFactory.getUserApi().getUserList(mLoadHelper.mPage, Constants.LIMIT_NUM),this,false);
    }
}
