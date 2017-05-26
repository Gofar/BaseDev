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

package com.gofar.basedev.base;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.FrameLayout;

import com.gofar.basedev.R;
import com.gofar.basedev.widget.TitleBar;

import butterknife.ButterKnife;

/**
 * Author: lcf
 * Description: 带ToolBar的Activity基类
 * Since: 1.0
 * Date: 2017/5/25 18:05
 */
public abstract class BaseCompatActivity extends BaseActivity {
    private TitleBar mToolBar;
    private FrameLayout mContent;

    @Override
    protected void setContentLayout(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mToolBar = (TitleBar) findViewById(R.id.title_bar);
        mContent = (FrameLayout) findViewById(R.id.layout_content);

        View customView = getCustomView();
        mBinder = ButterKnife.bind(this, customView);
        mContent.addView(customView, 0);

        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initToolBar(mToolBar);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.base_compat;
    }

    /**
     * 设置内容view
     * @return view
     */
    protected abstract View getCustomView();

    /**
     * ToolBar默认配置
     * 可重写此方法完成自定义配置
     * @param toolbar ToolBar
     */
    protected void initToolBar(TitleBar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
