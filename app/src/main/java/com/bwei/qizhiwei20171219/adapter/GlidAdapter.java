package com.bwei.qizhiwei20171219.adapter;


import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.qizhiwei20171219.R;
import com.bwei.qizhiwei20171219.bean.ShopBean;

import java.util.List;

/**
 * author:Created by QiZhiWei on 2017/12/19.
 */

public class GlidAdapter extends RecyclerView.Adapter{

    private Context context;
    private List<ShopBean.DataBean> list;

    public GlidAdapter(Context context, List<ShopBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LinearLayout.inflate(context, R.layout.layout_wang, null);
        MViewHolder myViewHolder = new MViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MViewHolder myViewHolder= (MViewHolder) holder;
        String images = list.get(position).getImages();
        String[] split = images.split("!");
        Glide.with(context).load(split[0]).placeholder(R.mipmap.ic_arrow_down).into(myViewHolder.ivShow);
        myViewHolder.tvTitle.setText("原价："+list.get(position).getTitle());
        myViewHolder.oldPrice.setText(list.get(position).getPrice()+"");
        myViewHolder.oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        myViewHolder.newPrice.setText("折扣价："+list.get(position).getBargainPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MViewHolder extends RecyclerView.ViewHolder{

        private final ImageView ivShow;
        private final TextView oldPrice;
        private final TextView newPrice;
        private final TextView tvTitle;

        public MViewHolder(View itemView) {
            super(itemView);
            ivShow = (ImageView)itemView.findViewById(R.id.iv_show_wang);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_title_wang);
            oldPrice = (TextView)itemView.findViewById(R.id.tv_oldprice_wang);
            newPrice = (TextView)itemView.findViewById(R.id.tv_newprice_wang);

        }
    }

}
