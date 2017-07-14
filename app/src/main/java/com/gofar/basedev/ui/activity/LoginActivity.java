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

import android.graphics.Color;
import android.os.Build;

import com.gofar.basedev.base.BaseActivity;
import com.gofar.basedev.ui.contract.LoginContract;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/6/7 15:30
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void initData() {

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
        // to MainActivity
    }
}
