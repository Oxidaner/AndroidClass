package com.example.android003_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android003_sharedpreferences.Service.saveMethod;
import com.example.android003_sharedpreferences.pojo.User;

import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    /**
     * 属性文件的使用(RW的公共文件):
     * 存放在手机的容器中,可以被所有的App的应用程序共享使用的资源文件
     * 文件的存放的格式,采用xml的方式进行存放(所以也成为资源参数文件)
     * 该文件的的主要的作用: 存放共享性的资源文件,包括参数书的设定,参数的配置信息(通讯录保存的人员的电话信息)
     * 属性文件 的信息的存放的格式 key-value方式进行信息的保存
     */

    //创建接受页面参数的组件 --文本框
    private EditText nameText;
    private EditText ageText;
    RadioGroup mSexCheck;
    RadioButton mBoy, mGirl;
    CheckBox mLRS, mSGS, mMSTT;
    String name, psd, sex, userposition;
    private TextView textview;
    Spinner mSpCheak;

    StringBuffer hobby = new StringBuffer();
    static int num = 1;
    static int max;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] userPosition = {"董事长", "总经理", "项目主管", "组员"};
        initview();
        //获取事件按钮
        Button save = (Button) findViewById(R.id.save);
        Button resume = (Button) findViewById(R.id.resume);
        Button next = (Button) findViewById(R.id.next);
        Button last = (Button) findViewById(R.id.last);
        //事件的注册操作
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, userPosition);
        mSpCheak.setAdapter(arrayAdapter);
        //参数信息的保存
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //从文本框中获取输入的信息
                name = nameText.getText().toString();
                String age = ageText.getText().toString();
                if (mLRS.isChecked()) {
                    hobby.append(mLRS.getText().toString());
                }
                if (mSGS.isChecked()) {
                    hobby.append(mSGS.getText().toString());
                }
                if (mMSTT.isChecked()) {
                    hobby.append(mMSTT.getText().toString());
                }

                userposition = mSpCheak.getSelectedItem().toString();
                String text = textview.getText().toString();
                //使用系统中的属性资源管理器(写入方式)
//                SharedPreferences preferences=getSharedPreferences("param", Context.MODE_PRIVATE);
//                Map<String, Object> map = (Map<String, Object>) preferences.getAll();
//                Set<String> keys = map.keySet();
//                max = keys.size();
                /*
                 * arg1: 要去创建的共享文件的文件名,不要后缀
                 * mode:该属性文件访问的模式设定
                 * */
                //创建该文件的编辑器
//                SharedPreferences.Editor editor = preferences.edit(); //类似于map集合
                //信息的存放的操作
                User user = new User(name, new Integer(age), sex, hobby.toString(), userposition, text);
                try {

                    saveMethod.saveUser(MainActivity.this, "param", String.valueOf(num), user);
                    num++;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //文件的保存
//                editor.commit();
                //清空
                Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                max += 1;

            }
        });


        //资源文件信息的恢复
        resume.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //直接获取属性文件管理器(读取方式)
//                SharedPreferences preferences=getSharedPreferences("param", Context.MODE_PRIVATE);
//				 * 整个的获取属性文件的内容

                User user = saveMethod.getUser(MainActivity.this, "param", String.valueOf(num));
                System.out.println(user);
                show(user);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num + 1 > max) {
                    System.out.println("123");
                    Toast.makeText(MainActivity.this, "没有下一个了", Toast.LENGTH_SHORT).show();
                } else {
                    num += 1;
                    User user = saveMethod.getUser(MainActivity.this, "param", String.valueOf(num - 1));
                    show(user);
                }
            }
        });

        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num - 1 <= 0) {
                    Toast.makeText(MainActivity.this, "没有上一个了", Toast.LENGTH_SHORT).show();
                } else {
                    num -= 1;
                    User user = saveMethod.getUser(MainActivity.this, "param", String.valueOf(num - 1));
                    show(user);
                }
            }
        });

        mSexCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtboy:
                        sex = mBoy.getText().toString();
                        break;
                    case R.id.rbtGirl:
                        sex = mGirl.getText().toString();
                        break;
                    default:
                        sex = "";
                        break;
                }
            }
        });
    }

    public void show(User user) {
        nameText.setText(user.getName());
        ageText.setText(String.valueOf(user.getAge()));
        textview.setText(user.getText());

        if (user.getSex().equals("男")) {
            mBoy.setChecked(true);
        } else {
            mGirl.setChecked(true);
        }
        if (user.getHobby().contains("跳绳")) {
            mLRS.setChecked(true);
        } else {
            mLRS.setChecked(false);
        }
        if (user.getHobby().contains("跑步")) {
            mSGS.setChecked(true);
        } else {
            mSGS.setChecked(false);
        }
        if (user.getHobby().contains("射箭")) {
            mLRS.setChecked(true);
        } else {
            mLRS.setChecked(false);
        }
    }

    public void initview() {
        //获取文本框组件
        nameText = (EditText) this.findViewById(R.id.name);
        ageText = (EditText) this.findViewById(R.id.age);
        mSexCheck = findViewById(R.id.groupSex);

        mBoy = findViewById(R.id.rbtboy);
        mGirl = findViewById(R.id.rbtGirl);

        mLRS = findViewById(R.id.chbLRS);
        mSGS = findViewById(R.id.chbSGS);
        mMSTT = findViewById(R.id.chbMSTT);

        textview = findViewById(R.id.textView);
        mSpCheak = findViewById(R.id.spcheak);

        SharedPreferences preferences=getSharedPreferences("param", Context.MODE_PRIVATE);
        Map<String, Object> map = (Map<String, Object>) preferences.getAll();
        Set<String> keys = map.keySet();
        max = keys.size();
        System.out.println(max);
        System.out.println(num);
    }

    public void clean() {
        nameText.setText(null);
        ageText.setText(null);
        mSexCheck.clearCheck();
        mLRS.setChecked(false);
        mSGS.setChecked(false);
        mMSTT.setChecked(false);
        hobby.setLength(0);
        textview.setText(null);
        mSpCheak.setSelection(3);
    }
}