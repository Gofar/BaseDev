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

package com.gofar.basedev.mn;

import com.gofar.basedev.mvp.BaseModel;
import com.gofar.basedev.mvp.BaseView;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/6/22 17:45
 */
public abstract class BasePresenter<M extends BaseModel, V extends BaseView, T> implements OnHandlerListener<T> {
    protected M mModel;
    protected V mView;

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
        onStart();
    }

    private void onStart() {

    }

    public void onDestroy() {
        mModel = null;
        mView = null;
    }

    public V getView() {
        return mView;
    }
}
