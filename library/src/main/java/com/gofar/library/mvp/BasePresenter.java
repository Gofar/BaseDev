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

package com.gofar.library.mvp;

import com.gofar.library.utils.ReflectUtils;

import org.greenrobot.eventbus.EventBus;


/**
 * Author: lcf
 * Description: Presenter基类
 * Since: 1.0
 * Date: 2017/7/14 11:32
 */
public abstract class BasePresenter<M extends BaseModel, V extends BaseView, T> {
    protected M mModel;
    protected V mView;

    public BasePresenter(V mView) {
        this.mView = mView;
        this.mModel = ReflectUtils.create(this, 0); // 反射创建Model
        onStart();
    }

    /**
     * start
     */
    private void onStart() {
        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * destroy
     */
    public void onDestroy() {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        mModel = null;
        mView = null;
    }

    /**
     * boolean user EventBus
     *
     * @return true or false
     */
    protected boolean useEventBus() {
        return false;
    }

    /**
     * get the mView
     *
     * @return mView
     */
    public V getView() {
        return mView;
    }

    /**
     * show loading view
     */
    public abstract void onHandlerLoading();

    /**
     * handler result
     *
     * @param t data from network
     */
    public abstract void onHandlerResult(T t);

    /**
     * handler Error
     *
     * @param e Throwable
     */
    public abstract void onHandlerError(Throwable e);

    /**
     * save data if need
     *
     * @param needCache true or false
     * @param t         data
     */
    public void cacheData(boolean needCache, T t) {
        if (!needCache) {
            return;
        }
        // cache some data,like use SharedPreference
    }
}
