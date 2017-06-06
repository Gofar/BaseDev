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

package com.gofar.basedev.common;


import com.gofar.basedev.BuildConfig;

/**
 * Author: lcf
 * Description: 常量
 * Since: 1.0
 * Date: 2017/4/27 10:26
 */

public interface Constants {
    /**
     * 发版时将其置为false true为开发模式 false为外网正式
     */
    boolean DEBUG = BuildConfig.LOG_DEBUG;

    // Intent、Bundle传递数据的key
    String EXTRAS_ID = "id";
    String EXTRAS_TYPE = "type";
    String EXTRAS_DATA = "data";
    String EXTRAS_FROM = "from";
    String EXTRAS_TITLE = "title";

    /**
     * 分页请求数量
     */
    int LIMIT_NUM=10;

}
