package utils;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import interce.OkhttpInterce;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author:Created by QiZhiWei on 2017/12/19.
 */

public class OkhttpUtils {

    private static volatile OkhttpUtils instance;

    private static Handler handler=new Handler();
    public static OkhttpUtils getInstance(){
        if (instance==null){
            synchronized (OkhttpUtils.class){
                if (null==instance){
                    instance=new OkhttpUtils();
                }
            }
        }
        return instance;
    }
    public synchronized static void get(String path, final Class cls, final IUtils iUtils){
        OkHttpClient build = new OkHttpClient.Builder().addInterceptor(new OkhttpInterce()).build();
        Request build1 = new Request.Builder()
                .url(path)
                .get()
                .build();
        Call call = build.newCall(build1);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iUtils.onFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();

                handler.post(new Runnable() {
                    Object o = GsonUtils.getInstance().fromJson(string, cls);
                    @Override
                    public void run() {
                        iUtils.onSuccess(o);
                    }
                });

            }
        });
    }

        public synchronized static void post(String path, Map<String,String> map, final Class cls, final IUtils callBack){
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String,String> entry:map.entrySet()) {
                builder.add(entry.getKey(),entry.getValue());
            }
            OkHttpClient builder1 = new OkHttpClient.Builder()
                    .addInterceptor(new OkhttpInterce()).build();
            Request build = new Request.Builder()
                    .post(builder.build())
                    .url(path)
                    .build();
            Call call = builder1.newCall(build);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFailed(e);
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String string = response.body().string();
                    Log.e("", "-------请求到的数据--------" + string);
                    handler.post(new Runnable() {
                        Object o = GsonUtils.getInstance().fromJson(string, cls);

                        @Override
                        public void run() {
                            callBack.onSuccess(o);
                        }
                    });
                }

            });

        }



}
