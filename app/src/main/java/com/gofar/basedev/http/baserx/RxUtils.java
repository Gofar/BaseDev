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

package com.gofar.basedev.http.baserx;

import com.gofar.basedev.mvp.BaseView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/5/31 10:12
 */
public class RxUtils {

    public static <T> LifecycleTransformer<T> bindToLifecycle(BaseView baseView) {
        if (baseView instanceof RxAppCompatActivity) {
            return ((RxAppCompatActivity) baseView).bindToLifecycle();
        } else if (baseView instanceof RxFragment) {
            return ((RxFragment) baseView).bindToLifecycle();
        } else {
            throw new IllegalArgumentException("view isn't activity or fragment");
        }

    }
}
