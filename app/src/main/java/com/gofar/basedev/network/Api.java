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

package com.gofar.basedev.network;

import com.gofar.library.utils.FileUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: lcf
 * Description: 网路请求类
 * Since: 1.0
 * Date: 2017/4/24 15:19
 */

public class Api {
    private static final long CONNECT_TIMEOUT = 30L;
    private static final long READ_TIMEOUT = 30L;
    private static final long WRITE_TIMEOUT = 30L;
    private static final long MAX_CACHE_SIZE = 50 * 1024 * 1024;
    private static final String CACHE_NAME = "cache_network";
    private static final String PACKAGE_NAME = "com.byit.dataplatform";

    private static Api mInstance;
    private static Retrofit mRetrofit;


    public static synchronized Api getInstance() {
        if (mInstance == null) {
            mInstance = new Api();
        }
        return mInstance;
    }

    /**
     * 配置OkHttpClient
     *
     * @return
     */
    private OkHttpClient buildDefaultClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        builder.cache(new Cache(new File(getDefaultCacheDir()), MAX_CACHE_SIZE));
//        if (Constants.DEBUG) {
//            builder.addNetworkInterceptor(new StethoInterceptor()); // 用于chrome浏览器调试
//        }
        return builder.build();
    }


    /**
     * 配置Retrofit
     */
    private void buildRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiPath.BASE_URL)
                .client(buildDefaultClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private Retrofit getRetrofit() {
        if (mRetrofit == null) {
            buildRetrofit();
        }
        return mRetrofit;
    }

    private String getDefaultCacheDir() {
        return FileUtils.getRootDirectory() + "Android/data/" + PACKAGE_NAME + File.separator + CACHE_NAME;
    }

    public <T> T build(Retrofit retrofit, Class<T> cls) {
        if (retrofit == null) {
            throw new NullPointerException("Retrofit is null");
        }
        return retrofit.create(cls);
    }

    public <T> T build(Class<T> cls) {
        return build(getRetrofit(), cls);
    }
}
