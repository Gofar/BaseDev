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

package com.gofar.library.mvp.extension;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Author: lcf
 * Description: 加载列表Helper
 * Since: 1.0
 * Date: 2017/6/7 16:04
 */
public class LoadListHelper {
    /**
     * 第一次加载（包括重试）
     */
    public static final int FIRST_LOAD = 0;
    /**
     * 加载更多
     */
    public static final int LOAD_MORE = 1;
    /**
     * 刷新
     */
    public static final int REFRESH = 2;

    @IntDef({FIRST_LOAD, LOAD_MORE, REFRESH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LoadType {
    }

    public int mLoadType = FIRST_LOAD;
    public int mPage = 1;

    public void setLoadType(@LoadType int mLoadType) {
        this.mLoadType = mLoadType;
        if (mLoadType == FIRST_LOAD || mLoadType == REFRESH) {
            mPage = 1;
        }
    }
}
