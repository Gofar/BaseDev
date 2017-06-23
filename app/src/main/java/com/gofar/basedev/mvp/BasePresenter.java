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

package com.gofar.basedev.mvp;

import org.greenrobot.eventbus.EventBus;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/6/7 11:34
 */
public abstract class BasePresenter<M extends BaseModel, V extends BaseView> {
    protected M mModel;
    protected V mView;

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
        onStart();
    }

    private void onStart() {
        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    protected void onDestroy() {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        mModel = null;
        mView = null;
    }

    protected boolean useEventBus() {
        return false;
    }

    protected abstract void handlerLoading();

    protected abstract void handlerError(Throwable e);
}
