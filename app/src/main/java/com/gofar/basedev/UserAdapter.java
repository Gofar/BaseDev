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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gofar.basedev.entity.UserEntity;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/6/2 17:18
 */
public class UserAdapter extends BaseQuickAdapter<UserEntity, BaseViewHolder> {
    public UserAdapter() {
        super(R.layout.item_user);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserEntity item) {
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_mobile, item.getMobile());
    }
}
