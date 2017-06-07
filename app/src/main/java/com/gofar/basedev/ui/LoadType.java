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

/**
 * Author: lcf
 * Description: 加载类型
 * Since: 1.0
 * Date: 2017/6/7 16:04
 */
public interface LoadType {
    /**
     * 第一次加载（包括重试）
     */
    int TYPE_FIRST_LOAD = 0;
    /**
     * 加载更多
     */
    int TYPE_LOAD_MORE = 1;
    /**
     * 刷新
     */
    int TYPE_REFRESH = 2;
}
