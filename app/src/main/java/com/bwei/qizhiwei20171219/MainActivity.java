package com.bwei.qizhiwei20171219;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwei.qizhiwei20171219.adapter.GlidAdapter;
import com.bwei.qizhiwei20171219.adapter.ShopAdapter;
import com.bwei.qizhiwei20171219.bean.ShopBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.IUtils;
import utils.OkhttpUtils;

public class MainActivity extends AppCompatActivity {

    private int page=1;
    private String keywords="手机";
    private ImageView zhuan;
    private EditText etSou;
    private Button butSou;
    private XRecyclerView rlvShow;
    private List<ShopBean.DataBean> list;
    private ShopAdapter shopAdapter;
    private int huan=0;
    private GlidAdapter glid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        list = new ArrayList<>();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        rlvShow.setLayoutManager(linearLayoutManager);
        shopAdapter = new ShopAdapter(MainActivity.this, list);
        rlvShow.setAdapter(shopAdapter);
        butSou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                shopAdapter.notifyDataSetChanged();
                if (etSou.getText().toString().trim().isEmpty()){
                    Toast.makeText(MainActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    keywords=etSou.getText().toString().trim();
                    getData();
                }


            }
        });
        zhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (huan==0){
                   /* LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
                    rlvShow.setLayoutManager(linearLayoutManager);
                    shopAdapter = new ShopAdapter(MainActivity.this, list);
                    rlvShow.setAdapter(shopAdapter);*/
                    huan=1;
                    zhuan.setImageResource(R.drawable.grid_icon);
                }else {
                    /*GridLayoutManager gridLayoutManager=new GridLayoutManager(MainActivity.this,2);
                    rlvShow.setLayoutManager(gridLayoutManager);
                    glid = new GlidAdapter(MainActivity.this, list);
                    rlvShow.setAdapter(shopAdapter);*/
                    huan=0;
                    zhuan.setImageResource(R.drawable.lv_icon);
                }
            }
        });

        rlvShow.setPullRefreshEnabled(true);
        rlvShow.setLoadingMoreEnabled(true);
        rlvShow.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                shopAdapter.notifyDataSetChanged();
                page=1;
                getData();
                rlvShow.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                list.clear();
                shopAdapter.notifyDataSetChanged();
                page+=1;
                getData();
                rlvShow.loadMoreComplete();
            }
        });
        getData();

    }

    private void initData() {
        zhuan = (ImageView) findViewById(R.id.iv_zhuan);
        etSou = (EditText) findViewById(R.id.et_sou);
        butSou = (Button) findViewById(R.id.but_sou);
        rlvShow = (XRecyclerView) findViewById(R.id.rlv_show);

    }

    private void getData() {
        OkhttpUtils instance = OkhttpUtils.getInstance();
        Map<String,String> map = new HashMap<>();
        map.put("page",""+page);
        map.put("keywords",""+keywords);
        OkhttpUtils.post("http://120.27.23.105/product/searchProducts", map, ShopBean.class, new IUtils() {
            @Override
            public void onSuccess(Object o) {
                ShopBean shopBean= (ShopBean) o;
                List<ShopBean.DataBean> data = shopBean.getData();
                list.addAll(data);
                shopAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(Exception e) {
                Toast.makeText(MainActivity.this,""+e,Toast.LENGTH_SHORT).show();
            }
        });

    }


}
