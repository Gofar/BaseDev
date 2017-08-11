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

package com.gofar.library.http;

/**
 * Author: lcf
 * Description: 自定义错误类
 * Since: 1.0
 * Date: 2017/5/27 15:07
 */
public class ApiException extends RuntimeException {

    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(int code) {
        this(getMsg(code));
    }

    /**
     * 将code转为错误信息
     * @param code code
     * @return
     */
    private static String getMsg(int code) {
        String msg = "";
        switch (code) {
            default:
                msg = "网络错误";
                break;
        }
        return msg;
    }
}
