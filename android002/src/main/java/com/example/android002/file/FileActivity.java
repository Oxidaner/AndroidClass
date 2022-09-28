package com.example.android002.file;


import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.example.android002.R;

public class FileActivity extends Activity {
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //如判断手机的SDCard是否可以进行读写的操作
        try {
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                //判断SDCard是否可以进行读写的操作
                //FileService的创建
            }else{
                Toast.makeText(FileActivity.this, "SD卡不可用", Toast.LENGTH_SHORT).show();

            }



        } catch (Exception e) {
            // TODO: handle exception
        }




    }
}