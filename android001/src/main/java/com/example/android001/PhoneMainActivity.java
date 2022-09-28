package com.example.android001;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PhoneMainActivity extends AppCompatActivity {
    /** ActionBarActivity是Android提供的一个界面父类
     * 该类有一个特点: 可以绑定一个布局文件,用于界面的显示
     *  */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//创建一个Android的主界面
        setContentView(R.layout.activity_phone_main);//当前主界面绑定一个布局文件


        /****电话的拨号设计******/

        //拨号的设计
        //获取拨号按钮组件
        Button call=(Button)this.findViewById(R.id.call);//从布局文件中找到一个按钮

        //添加该按钮的事件监听,匿名类的书写方式



        call.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 点击的事件处理
                EditText phone=	(EditText) findViewById(R.id.phone);
                //获取文本框中的内容
                String mobile=phone.getText().toString();

                //调用系统的拨号程序
                Intent intent=new Intent();
                intent.setAction("android.intent.action.CALL");
                //拨号属性设置
                intent.setData(Uri.parse("tel:"+mobile));
                //开始拨号
                startActivity(intent);
            }
        });


        /****短信发送的设计******/

        //短信发送的设置
        Button send=(Button) this.findViewById(R.id.send);//获取短信的发送按钮
        send.setOnClickListener(new OnClickListener() { //注册短信发送的事件

			@Override
			public void onClick(View v) {
				String phone=	((EditText) findViewById(R.id.phone)).getText().toString();
				String message=((EditText) findViewById(R.id.message)).getText().toString();//获取短信的内容

				//调用手机底层信息的发送
				SmsManager manager=SmsManager.getDefault();//发送信息的管理器,由Android环境进行提供
				//信息的拆分
				ArrayList<String> megs = manager.divideMessage(message);//按照规范进行数据的拆分
				for (String msg: megs) { //遍历发送
					manager.sendTextMessage(phone, null, msg, null, null);
				}
				//提示框
				Toast.makeText(PhoneMainActivity.this, "发送信息成功", Toast.LENGTH_LONG).show();
			}
		});



    }

}
