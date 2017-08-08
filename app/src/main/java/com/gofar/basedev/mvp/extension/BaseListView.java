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

package com.gofar.basedev.mvp.extension;

import com.gofar.basedev.mvp.BaseView;

import java.util.List;

/**
 * Author: lcf
 * Description: 列表基类View
 * Since: 1.0
 * Date: 2017/7/14 15:03
 */
public interface BaseListView<T> extends BaseView {
    void showEmpty();

    void showRetry();

    void loadMoreFailed();

    void loadMoreEnd(boolean gone);

    void loadMoreCompleted();

    void refreshCompleted();

    void newData(List<T> data);

    void addData(List<T> data);
}
