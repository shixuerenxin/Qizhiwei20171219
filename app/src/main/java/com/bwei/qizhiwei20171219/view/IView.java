package com.bwei.qizhiwei20171219.view;

import com.bwei.qizhiwei20171219.bean.ShopBean;

/**
 * author:Created by QiZhiWei on 2017/12/19.
 */

public interface IView {
    void onSuccess(ShopBean shopBean);
    void onFailed();
}
