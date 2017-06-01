package com.gofar.basedev;

import com.gofar.basedev.base.BaseModel;
import com.gofar.basedev.base.BaseView;
import com.gofar.basedev.entity.BaseEntity;
import com.gofar.basedev.entity.UserEntity;

import io.reactivex.Observable;

/**
 * Author: lcf
 * Description:
 * Since: 1.0
 * Date: 2017/6/1 14:04
 */
public interface UserContract {
    interface View extends BaseView {
        void refreshCompleted();

        void loadMoreComplete();

        void returnData(UserEntity entity);
    }

    interface Model extends BaseModel {
        Observable<BaseEntity<UserEntity>> register();
    }

}
