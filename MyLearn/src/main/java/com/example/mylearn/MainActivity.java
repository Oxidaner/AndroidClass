package com.example.mylearn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    private  int[]  arrayPicture=new int[]{
            R.drawable.a1,R.drawable.a2};
    private ImageSwitcher imageSwitcher;
    private int  index;
    private  float touchDownX;
    private  float touchUpX;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        imageSwitcher=findViewById(R.id.imageswitch);
        //设置视图工厂
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView  imageView=new ImageView(MainActivity.this);
                imageView.setImageResource(arrayPicture[index]);//设置显示图片（利用下标）
                return imageView;//返回图像视图
            }
        });
        //设置触摸监听器
        imageSwitcher.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                //判断动作是不是按下  获得按下时的X坐标
                if(event.getAction()==MotionEvent.ACTION_DOWN) {
                    touchDownX=event.getX();
                    return true;
                } else if(event.getAction()==MotionEvent.ACTION_UP) {
                    touchUpX=event.getX();
                    //判断是左滑动还是右滑动
                    if(touchUpX-touchDownX>100){
                        //判断是不是第一张图片 是就将索引变成最后一张图片索引，
                        // 不是则当前索引减一
                        index=index==0?arrayPicture.length-1:index-1;
                        //使用自带的淡入淡出
                        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.slide_in_left));
                        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.slide_out_right));
                        imageSwitcher.setImageResource(arrayPicture[index]);
                    }else if(touchDownX-touchUpX>100){
                        index=index==arrayPicture.length-1?0:index+1;//注意这里下标是从0开始的，所以应该是长度减1
                        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.fade_out));
                        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.fade_in));

                        imageSwitcher.setImageResource(arrayPicture[index]);

                    }
                    return true;
                }
                return false;
            }
        });
    }
}
