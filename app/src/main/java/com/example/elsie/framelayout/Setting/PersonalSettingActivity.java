package com.example.elsie.framelayout.Setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.elsie.framelayout.BaseActivity;
import com.example.elsie.framelayout.R;
import com.example.elsie.framelayout.Utils.ImageUtils;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import static com.example.elsie.framelayout.LoginActivity.muserid;
import static com.example.elsie.framelayout.Setting.SettingFragment.mheader_img;
import static com.example.elsie.framelayout.Setting.SettingFragment.mnick_name;

public class PersonalSettingActivity extends BaseActivity {

    private static final String TAG = "PersonalSettingActivity";
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    private static int username_flag=0;
    private int userimg_flag=0;//修改头像标志位
    protected static Uri tempUri;
    public static ImageView mimage_user;
    private EditText user_name;
    private EditText user_mail;
    public static String mn_name;
    public static Bitmap mphoto;
    public static String inputText1;
    public static BmobFile bmobFile;
    public  String imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting);



        /*Connector.getDatabase();*/
        user_name=(EditText)findViewById(R.id.usr_name);
        mimage_user = (ImageView) findViewById(R.id.usr_img);


        Button save=(Button)findViewById(R.id.save);
        if(!TextUtils.isEmpty(inputText1)){
            user_name.setText(inputText1);
            user_name.setSelection(inputText1.length());
            mn_name=user_name.getText().toString();
        }


            mimage_user.setImageResource(R.drawable.userimg);

        BmobQuery<MyUser> query=new BmobQuery<MyUser>();
        query.addWhereEqualTo("User_name",muserid);
        query.findObjects(this, new FindListener<MyUser>() {
            @Override
            public void onSuccess(List<MyUser> list) {
                for(MyUser myUser:list){
                    if(myUser.getHead_url()!=null){
                        Bitmap bitmap = BitmapFactory.decodeFile(myUser.getHead_url());
                        mimage_user.setImageBitmap(bitmap);}
                }
            }

            @Override
            public void onError(int i, String s) {
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText1=user_name.getText().toString();
                MyUser Student=new MyUser();
                Student.setUser_name(muserid);
                Student.setNickname(inputText1);
                Student.setHead_url(imagePath);
                Student.save(PersonalSettingActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(PersonalSettingActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(PersonalSettingActivity.this,"保存失败",Toast.LENGTH_SHORT).show();
                    }
                });

                mnick_name.setText(Student.getNickname());
                user_name.setText(Student.getNickname());
                //mimage_user.setImageBitmap(Student.getPhoto());
                username_flag=1;
                Toast.makeText(PersonalSettingActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                PersonalSettingActivity.this.finish();
            }
        });
        Button btn_change = (Button) findViewById(R.id.btn_change);

        btn_change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showChoosePicDialog();
            }
        });

        Button exit=(Button)findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.elsie.framelayout.OFFLINE");
                sendBroadcast(intent);
            }
        });
        ImageButton back=(ImageButton)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonalSettingActivity.this.finish();
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        String inputText1=user_name.getText().toString();
    }

    /**
     * 显示修改头像的对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = { "选择本地照片", "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            mphoto = extras.getParcelable("data");
            Log.d(TAG,"setImageToView:"+mphoto);
            mphoto = ImageUtils.toRoundBitmap(mphoto); // 处理后的图片
            mimage_user.setImageBitmap(mphoto);
            mheader_img.setImageBitmap(mphoto);
            userimg_flag=1;
            uploadPic(mphoto);
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // 把Bitmap转换成file，然后得到file的url，做文件上传操作
        imagePath = ImageUtils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.e("imagePath", imagePath+"");

        if(imagePath != null){
            Log.d(TAG,"imagePath:"+imagePath);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
            // 没有获取到权限，重新请求，或者关闭app
            Toast.makeText(this, "需要存储权限", Toast.LENGTH_SHORT).show();
        }
    }

}
