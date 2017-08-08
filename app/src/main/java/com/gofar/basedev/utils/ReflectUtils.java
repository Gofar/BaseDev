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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Author: lcf
 * Description: 反射工具类
 * Since: 1.0
 * Date: 2017/8/8 16:00
 */
public class ReflectUtils {
    /**
     * 反射实例化类的泛型
     *
     * @param o     类的实体
     * @param index 泛型参数index
     * @param <M>   泛型类型
     * @return
     */
    public static <M> M create(Object o, int index) {
        try {
            Type type = o.getClass().getGenericSuperclass();
            Type[] genericTye = ((ParameterizedType) type).getActualTypeArguments();
            Class<M> cls = (Class<M>) genericTye[index];
            return cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }
}
