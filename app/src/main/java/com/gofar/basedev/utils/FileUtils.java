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

import android.os.Environment;

import java.io.File;

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
}
