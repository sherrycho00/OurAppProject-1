package com.example.elsie.framelayout.Chat;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.elsie.framelayout.R;

/**
 * Created by Lenovo on 2017/12/6.
 */

public class CreateChatDialog extends Dialog {

    Activity context;

    private Button send;

    public EditText content;

    private int lab;

    public int getLab() {
        return lab;
    }

    public void setLab(int lab) {
        this.lab = lab;
    }

    private View.OnClickListener mClickListener;

    public CreateChatDialog(Activity context) {
        super(context);
        this.context = context;
    }

    public CreateChatDialog(Activity context, int theme,View.OnClickListener clickListener ) {
        super(context, theme);
        this.context = context;
        this.mClickListener = clickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        this.setContentView(R.layout.create_chat_dialog);

        content = (EditText) findViewById(R.id.input_text);
  /*
   * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
   * 对象,这样这可以以同样的方式改变这个Activity的属性.
   */
        Window dialogWindow = this.getWindow();

        WindowManager m = context.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.8
        dialogWindow.setAttributes(p);

        // 根据id在布局中找到控件对象
        send = (Button) findViewById(R.id.send_chat);

        // 为按钮绑定点击事件监听器
        send.setOnClickListener(mClickListener);

        this.setCancelable(true);
    }
}
