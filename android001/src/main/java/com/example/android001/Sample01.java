package com.example.android001;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Sample01 extends Activity {
    int[] numButtonIds={//数字按钮的ID数组
            R.id.Button00,R.id.Button01,R.id.Button02,
            R.id.Button03,R.id.Button04,R.id.Button05,
            R.id.Button06,R.id.Button07,R.id.Button08,
            R.id.Button09
    };
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_main);
        Button bDel=(Button)this.findViewById(R.id.Button_del);
        bDel.setOnClickListener(//为删除按钮添加监听器
                //OnClickListener为View的内部接口，其实现者负责监听鼠标点击事件
                new View.OnClickListener(){
                    public void onClick(View v){
                        EditText et=(EditText)findViewById(R.id.EditText01);
                        String num=et.getText().toString();
                        num=(num.length()>1)?num.substring(0,num.length()-1):"";
                        et.setText(num);//组织字符创
                    }
                });
        Button bDial=(Button)this.findViewById(R.id.Button_dial);
        bDial.setOnClickListener(//为拨号按钮添加监听器
                //OnClickListener为View的内部接口，其实现者负责监听鼠标点击事件
                new View.OnClickListener(){
                    public void onClick(View v){
                        //获取输入的电话号码
                        EditText et=(EditText)findViewById(R.id.EditText01);
                        String num=et.getText().toString();
                        //根据获取的电话号码创建Intent拨号
                        Intent dial = new Intent();
                        dial.setAction("android.intent.action.CALL");
                        dial.setData(Uri.parse("tel://"+num));
                        startActivity(dial);  //激活打电话的Activity
                    }
                }
        );
        Button bCancel=(Button)this.findViewById(R.id.Button_cancel);
        bCancel.setOnClickListener(//为退出按钮添加监听器
                //OnClickListener为View的内部接口，其实现者负责监听鼠标点击事件
                new View.OnClickListener(){
                    public void onClick(View v){
                        Sample01.this.finish();//是否窗口
                    }
                }
        );
        View.OnClickListener numListener=new  View.OnClickListener(){
            public void onClick(View v){ //为0-9数字按钮创建监听器
                Button tempb=(Button)v;//得到按钮的引用
                EditText et=(EditText)findViewById(R.id.EditText01);//得到EditText的引用
                et.append(tempb.getText());//组织字符串
            }
        };
        for(int id:numButtonIds){//为所有数字按钮添加监听器
            Button tempb=(Button)this.findViewById(id);//得到按钮
            tempb.setOnClickListener(numListener);//添加监听
        }
    }
}
