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

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gofar.basedev.entity.UserEntity;

import java.util.List;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/8/11 15:49
 */
public class UserItemAdapter extends BaseQuickAdapter<UserEntity,BaseViewHolder>{
    public UserItemAdapter(@Nullable List<UserEntity> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserEntity item) {

    }
}
