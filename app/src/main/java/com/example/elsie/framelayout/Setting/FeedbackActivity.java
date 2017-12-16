package com.example.elsie.framelayout.Setting;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.elsie.framelayout.R;

import cn.bmob.v3.listener.SaveListener;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        ImageButton back=(ImageButton)findViewById(R.id.fb_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedbackActivity.this.finish();
            }
        });

        Button fb=(Button)findViewById(R.id.fb_msg);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText advice=(EditText) findViewById(R.id.advice);
                Feedback Msg=new Feedback();
                Msg.setMsg(advice.getText().toString());
                Msg.save(FeedbackActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(FeedbackActivity.this,"提交成功，感谢你的反馈",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(FeedbackActivity.this,"保存失败",Toast.LENGTH_SHORT).show();
                    }
                });
                FeedbackActivity.this.finish();
            }
        });

    }
}
