package com.bwei.qizhiwei20180109;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class IndexActivity extends AppCompatActivity {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String string = tvNum.getText().toString();
                    int num = Integer.parseInt(string);
                    if (num==0){
                        SharedPreferences.Editor edit = name.edit();
                        edit.putInt("zhuan",0);
                        edit.commit();
                        Intent intent = new Intent(IndexActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        handler.removeMessages(0);

                    }else {
                        num-=1;
                        tvNum.setText(""+num);
                        handler.sendEmptyMessageDelayed(0,1000);
                    }


                    break;
            }
        }
    };
    private TextView tvNum;
    private TextView tvTiao;
    private SharedPreferences name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNum = (TextView) findViewById(R.id.tv_num);
        tvTiao = (TextView) findViewById(R.id.tv_tiao);
        name = getSharedPreferences("name", 0);
        int zhuan = name.getInt("zhuan", 10);
        if (zhuan!=0){
           handler.sendEmptyMessageDelayed(0,1000);
         }else {
            Intent intent = new Intent(IndexActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        tvTiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IndexActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
