/*
 * Copyright (C) 2017 Gofar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicabe law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gofar.basedev.glide;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import java.io.File;

/**
 * Author: lcf
 * Description:图片加载Loader
 * Since: 1.0
 * Date: 2017/6/24 11:36
 */
public class ImageLoader {
    public static final String CACHE_NAME = "cache_network_images";
    public static final int DISK_CACHE_SIZE = 100 * 1024 * 1024;

    public static void load(Context context, String url, ImageView view) {
        GlideApp.with(context).load(url).into(view);
    }

    public static void load(Context context, Uri uri, ImageView view) {
        GlideApp.with(context).load(uri).into(view);
    }

    public static void load(Activity activity, String url, ImageView view) {
        GlideApp.with(activity).load(url).into(view);
    }

    public static void load(FragmentActivity activity, String url, ImageView view) {
        GlideApp.with(activity).load(url).into(view);
    }

    public static void load(Fragment fragment, String url, ImageView view) {
        GlideApp.with(fragment).load(url).into(view);
    }

    public static void load(android.app.Fragment fragment, String url, ImageView view) {
        GlideApp.with(fragment).load(url).into(view);
    }

    public static void load(Context context, String url, ImageView view, int placeHolderId) {
        GlideApp.with(context).load(url).placeholder(placeHolderId).error(placeHolderId).into(view);
    }

    public static void load(Context context, Uri uri, ImageView view, int placeHolderId) {
        GlideApp.with(context).load(uri).placeholder(placeHolderId).error(placeHolderId).into(view);
    }

    public static void load(Activity activity, String url, ImageView view, int placeHolderId) {
        GlideApp.with(activity).load(url).placeholder(placeHolderId).error(placeHolderId).into(view);
    }

    public static void load(FragmentActivity activity, String url, ImageView view, int placeHolderId) {
        GlideApp.with(activity).load(url).placeholder(placeHolderId).error(placeHolderId).into(view);
    }

    public static void load(Fragment fragment, String url, ImageView view, int placeHolderId) {
        GlideApp.with(fragment).load(url).placeholder(placeHolderId).error(placeHolderId).into(view);
    }

    public static void load(android.app.Fragment fragment, String url, ImageView view, int placeHolderId) {
        GlideApp.with(fragment).load(url).placeholder(placeHolderId).error(placeHolderId).into(view);
    }

    public static void loadCircle(Context context, String url, final ImageView view, int placeHolderId) {
        GlideApp.with(context).load(url).placeholder(placeHolderId).error(placeHolderId).dontAnimate().into(view);
    }

    public static void loadCircle(Context context, Uri uri, final ImageView view, int placeHolderId) {
        GlideApp.with(context).load(uri).placeholder(placeHolderId).error(placeHolderId).dontAnimate().into(view);
    }

    public static void loadCircle(Activity activity, String url, final ImageView view, int placeHolderId) {
        GlideApp.with(activity).load(url).placeholder(placeHolderId).error(placeHolderId).dontAnimate().into(view);
    }

    public static void loadCircle(FragmentActivity activity, String url, final ImageView view, int placeHolderId) {
        GlideApp.with(activity).load(url).placeholder(placeHolderId).error(placeHolderId).dontAnimate().into(view);
    }

    public static void loadCircle(Fragment fragment, String url, final ImageView view, int placeHolderId) {
        GlideApp.with(fragment).load(url).placeholder(placeHolderId).error(placeHolderId).dontAnimate().into(view);
    }

    public static void loadCircle(android.app.Fragment fragment, String url, final ImageView view, int placeHolderId) {
        GlideApp.with(fragment).load(url).placeholder(placeHolderId).error(placeHolderId).dontAnimate().into(view);
    }

    public static File getCacheDir(Context context) {
        return GlideApp.getPhotoCacheDir(context, CACHE_NAME);
    }

    public static void clearDiskCache(Context context) {
        GlideApp.get(context).clearDiskCache();
    }

    public static void clearMemory(Context context) {
        GlideApp.get(context).clearMemory();
    }

    public static void loadGif(Context context, String url, ImageView view) {
        GlideApp.with(context).asGif().load(url).into(view);
    }
}
