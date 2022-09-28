package com.example.android002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android002.service.FileService;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText mUserNmae,mUserPassword;
    RadioGroup mSexCheck;
    RadioButton mBoy,mGirl;
    CheckBox mLRS,mSGS,mMSTT;
    Button mRegister,mCancel;
    Spinner mSpCheak;

    String name,psd,sex,userposition;

    StringBuffer hobby = new StringBuffer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] userPosition = {"董事长","总经理","项目主管","组员"};
        final FileService service = new FileService(this);

        initview();
        try {
            HashMap<String, String> hashmap = new HashMap<>();
            String s = service.readFile("message.txt");
            String[] s1 = s.split(" ");
            for (int i = 0; i < s1.length; i++) {
                String[] split = s1[i].split(":");
                hashmap.put(split[0],split[1]);
            }
            System.out.println(hashmap);
            mUserNmae.setText(hashmap.get("用户名"));
            mUserPassword.setText(hashmap.get("密码"));
            if(hashmap.get("性别") == "男"){
                mBoy.setChecked(true);
            }else{
                mGirl.setChecked(true);
            }
//            for (String position : userPosition) {
//                if (hashmap.get("职位") == position){
//                    mSpCheak.
//                }
//            }


//            hobby.setText(hashmap.get("爱好"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建适配器
        //第一个参数 页面
        //第二个参数 布局文件
        //第三个参数 数据源
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, userPosition);
        mSpCheak.setAdapter(arrayAdapter);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                name = mUserNmae.getText().toString();
                psd  = mUserPassword.getText().toString();

                if(mLRS.isChecked()){
                    hobby.append(mLRS.getText().toString());
                }
                if(mSGS.isChecked()){
                    hobby.append(mSGS.getText().toString());
                }
                if(mMSTT.isChecked()){
                    hobby.append(mMSTT.getText().toString());
                }

                userposition =  mSpCheak.getSelectedItem().toString();
                //Toast.makeText(DemoOne.this,userposition,Toast.LENGTH_SHORT).show();
                //Toast.makeText(DemoOne.this,name+psd+sex+hobby+userposition,Toast.LENGTH_SHORT).show();

                Toast.makeText(MainActivity.this,"用户名"+name+ '\n' +"密码"+psd + '\n' +"性别"+sex + '\n' +"爱好"+hobby + '\n' +"职位"+userposition,Toast.LENGTH_SHORT).show();
                String name = "message.txt";
                String content= "用户名:"+name+" "+"密码:"+psd+" "+"性别:"+sex+" "+"爱好:"+hobby+" "+"职位:"+userposition;

                try {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        //是否有SD卡
                        service.save(name, content);
                        Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "SD卡不存在", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                hobby.delete(0,hobby.length());

            }
        });

        mSexCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbtboy:
                        sex=mBoy.getText().toString();
                        break;
                    case R.id.rbtGirl:
                        sex=mGirl.getText().toString();
                        break;
                    default:
                        sex="";
                        break;
                }
            }
        });


        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserNmae.setText(null);
                mUserPassword.setText(null);
                mSexCheck.clearCheck();
                mLRS.setChecked(false);
                mSGS.setChecked(false);
                mMSTT.setChecked(false);
                mSpCheak.setSelection(3);
            }
        });


    }


    public void initview(){
        mUserNmae = findViewById(R.id.etName);
        mUserPassword = findViewById(R.id.etPassword);

        mSexCheck = findViewById(R.id.groupSex);

        mBoy = findViewById(R.id.rbtboy);
        mGirl = findViewById(R.id.rbtGirl);

        mLRS = findViewById(R.id.chbLRS);
        mSGS = findViewById(R.id.chbSGS);
        mMSTT = findViewById(R.id.chbMSTT);

        mRegister = findViewById(R.id.btnRegister);
        mCancel   = findViewById(R.id.btnCancel);
        mSpCheak = findViewById(R.id.spcheak);
    }

}

