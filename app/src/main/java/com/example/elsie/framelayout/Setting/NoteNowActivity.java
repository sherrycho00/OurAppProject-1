package com.example.elsie.framelayout.Setting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elsie.framelayout.R;

/**
 * Created by sherry on 17-12-13.
 */

public class NoteNowActivity extends Activity implements OnClickListener,
        OnItemClickListener {
    private TextView note_expain;
    private TextView nowTime;
    private Button  notedone;
    private ImageButton btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.note_now);
        findViewById();
        setListener();
        init();
    }

    private void findViewById() {
        note_expain = (TextView) findViewById(R.id.note_expain);
        btn_back = (ImageButton) findViewById(R.id.btn_back);
        nowTime = (TextView) findViewById(R.id.now_time);
        notedone = (Button) findViewById(R.id.note_done);
    }

    private void init() {
        notedone.setVisibility(View.VISIBLE);
        String time = MyApplication.getInstances().getSystemDataATime();
        nowTime.setText(time);
    }

    private void setListener() {
        btn_back.setOnClickListener(this);
        notedone.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                this.overridePendingTransition(R.anim.push_lower,
                        R.anim.push_lower_out);
                break;
            case R.id.note_done:
                submitData();
                break;
        }

    }

    private void submitData() {

        String noteexplain = note_expain.getText().toString();
        if (TextUtils.isEmpty(noteexplain)) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(
                    NoteNowActivity.this);
            dialog.setTitle("内容为空")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setMessage("请输入内容......")
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog,
                                                    int which) {

                                }
                            }).create().show();
        } else {
            String time = MyApplication.getInstances().getSystemDataATime();
            NoteInfo noteInfo = new NoteInfo(this);
            noteInfo.insertNoteInfo(noteexplain, time);
            Toast toast = Toast.makeText(getApplicationContext(), "记录成功",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            finish();
            this.overridePendingTransition(R.anim.push_lower,
                    R.anim.push_lower_out);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

    }

}


