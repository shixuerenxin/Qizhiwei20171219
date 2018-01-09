package com.bwei.qizhiwei20171219.mobile;

import android.util.Log;

import com.bwei.qizhiwei20171219.bean.ShopBean;
import com.bwei.qizhiwei20171219.bean.User;

import java.util.HashMap;
import java.util.Map;

import utils.IUtils;
import utils.OkhttpUtils;
import utils.ProApi;

/**
 * author:Created by QiZhiWei on 2017/12/19.
 */

public class Mobile implements IMobile{

    private OnFinishLinsear onFinishLinsear;

    public void setOnFinishLinsear(OnFinishLinsear onFinishLinsear) {
        this.onFinishLinsear = onFinishLinsear;
    }

    public interface OnFinishLinsear{
        void onFinsh(ShopBean shopBean);
    }

    @Override
    public void login(User user) {
        Map<String,String > map = new HashMap<>();
        map.put("page",""+user.getPage());
        map.put("Keywords",""+user.getKeywords());
        OkhttpUtils.getInstance().post(ProApi.SHOPAPI, map, ShopBean.class, new IUtils() {
            @Override
            public void onSuccess(Object o) {
                if (o!=null){
                    ShopBean shopBean= (ShopBean) o;
                    onFinishLinsear.onFinsh(shopBean);
                }
            }

            @Override
            public void onFailed(Exception e) {
                Log.e("+++++++++",""+e);
            }
        });

    }
}
