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

package com.gofar.basedev.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/4/24 15:31
 */

public class FileUtils {

    public static boolean isSdcardExists() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static String getRootDirectory() {
        File sdCardDirectory = null;
        if (isSdcardExists()) {
            sdCardDirectory = Environment.getExternalStorageDirectory();
        }
        if (sdCardDirectory != null) {
            return sdCardDirectory.toString();
        } else {
            return "";
        }
    }

    /**
     * 复制assets下的资源到手机上
     * @param context 上下文
     * @param resName 资源名
     * @param path 路径
     * @param name 文件名
     * @return
     */
    public static File getVideoPath(Context context, String resName, String path, String name) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, name);
        if (!file.exists()) {
            AssetManager am = context.getAssets();
            InputStream in = null;
            FileOutputStream fo = null;
            try {
                in = am.open(resName);
                fo = new FileOutputStream(file);
                byte[] b = new byte[1024];
                int len = -1;
                while ((len = in.read(b)) != -1) {
                    fo.write(b, 0, len);
                }
                fo.flush();
                fo.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
