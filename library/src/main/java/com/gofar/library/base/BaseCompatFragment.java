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

package com.gofar.library.base;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.gofar.library.R;
import com.gofar.library.widget.TitleBar;

import butterknife.ButterKnife;

/**
 * Author: lcf
 * Description: 带ToolBar的Fragment基类
 * Since: 1.0
 * Date: 2017/5/25 18:15
 */
public abstract class BaseCompatFragment extends BaseFragment {

    private TitleBar mToolBar;
    private FrameLayout mContent;

    @Override
    protected View setContentView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View parent = inflater.inflate(getLayoutId(), container, false);
        mToolBar = (TitleBar) parent.findViewById(R.id.title_bar);
        mContent = (FrameLayout) parent.findViewById(R.id.layout_content);

        View customView = getCustomView();
        mBinder = ButterKnife.bind(this, customView);
        mContent.addView(customView, 0);

        initToolBar(mToolBar);

        return parent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.base_compat;
    }

    /**
     * 设置内容view
     *
     * @return view
     */
    protected abstract View getCustomView();

    /**
     * 配置ToolBar
     *
     * @param toolbar ToolBar
     */
    protected abstract void initToolBar(TitleBar toolbar);
}
