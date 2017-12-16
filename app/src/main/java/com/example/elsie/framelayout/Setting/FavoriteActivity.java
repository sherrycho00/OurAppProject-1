package com.example.elsie.framelayout.Setting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.elsie.framelayout.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends Activity implements OnClickListener {
    private ImageButton backBtn;
    private ImageButton noteBtn;
    protected List<NoteDefine> noteList = new ArrayList<NoteDefine>();
    private TextView noNoteText;
    private ListView mListView;
    private NoteAdapter mAdapter;

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.notemain);

        backBtn = (ImageButton) findViewById(R.id.btn_back);
        noteBtn = (ImageButton) findViewById(R.id.note_fig);
        noteBtn.setVisibility(View.VISIBLE);
        mListView = (ListView) findViewById(R.id.note_listview);
        noNoteText = (TextView) findViewById(R.id.nonote);
        setListener();
        init();

    }

    private void init() {
        NoteInfo noteInfo = new NoteInfo(this);
        noteList = noteInfo.getAllDeviceInfo();
        if (noteList.size() == 0) {
            noNoteText.setVisibility(View.VISIBLE);
        } else {
            noNoteText.setVisibility(View.INVISIBLE);
        }
        mAdapter = new NoteAdapter(this, noteList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {

                final NoteDefine list = (NoteDefine) mListView
                        .getItemAtPosition(position);
                String noteExplain = list.getNoteExplain();
                String notetime = list.getNotetime();
                noteId = list.getNoteID();
                AlertDialog dialog = new AlertDialog.Builder(FavoriteActivity.this)

                        .setTitle("时间:" + notetime)

                        .setMessage("内容:" + noteExplain)

                        .setPositiveButton("取消", null)
                        .setNegativeButton("删除",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        NoteInfo noteInfo = new NoteInfo(
                                                FavoriteActivity.this);
                                        noteInfo.deleteNoteInfo(noteId);
                                        noteList.remove(position);
                                        mAdapter.notifyDataSetChanged();
                                    }
                                })

                        .show();
            }

        });

    }

    private void setListener() {
        backBtn.setOnClickListener(this);
        noteBtn.setOnClickListener(this);
    }

    public void onResume() {
        super.onResume();
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.note_fig:
                Intent intent = new Intent(FavoriteActivity.this, NoteNowActivity.class);
                startActivity(intent);
                this.overridePendingTransition(R.anim.push_lower_in,
                        R.anim.push_lower);

                break;
            case R.id.btn_back:
                FavoriteActivity.this.finish();
        }

    }

}
