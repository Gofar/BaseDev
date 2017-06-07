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

package com.gofar.basedev.ui.activity;

import android.view.View;

import com.gofar.basedev.base.BaseCompatActivity;
import com.gofar.basedev.ui.contract.ChangePsdContract;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/6/7 17:17
 */
public class ChangePsdActivity extends BaseCompatActivity implements ChangePsdContract.View{
    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected View getCustomView() {
        return null;
    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void showMessage(String msg) {
        showToast(msg);
    }

    @Override
    public void success() {
        showToast("修改成功");
    }
}
