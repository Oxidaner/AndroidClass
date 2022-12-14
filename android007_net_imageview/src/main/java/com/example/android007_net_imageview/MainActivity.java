package com.example.android007_net_imageview;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.android007_net_imageview.service.ImageService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import kotlin.reflect.KVariance;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private EditText urlpath;
    private ImageView imageView;
    private Button button;
    private Bitmap[] arrayPicture = new Bitmap[2];
    private ImageSwitcher imageSwitcher;
    private int index;
    private float touchDownX;
    private float touchUpX;
    String path = "https://qiniu.oxidaner.top/img/5ea424f173d2f736e5f980a51e0abf4.jpg";
    String path2 = "https://qiniu.oxidaner.top/img/52a3c24f1eb0b85e507155c3e80bedb.jpg";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //??????url??????
        urlpath = (EditText) this.findViewById(R.id.urlpath);
        Button button = (Button) this.findViewById(R.id.button);
        imageView = (ImageView) this.findViewById(R.id.imageView);
        imageSwitcher = findViewById(R.id.imageswitch);

        ArrayList<String> strings = new ArrayList<>();
        strings.add(path);
        strings.add(path2);

//                OkHttpClient client = new OkHttpClient.Builder().build();
//                Request request = new Request.Builder().get().url(path).build();
//                Call call = client.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                        Log.e(TAG, "onFailure: " + e.toString());
//                    }
//
//                    @Override
//                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                        InputStream inputStream = response.body().byteStream();
//                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                imageView.setImageBitmap(bitmap);
//                            }
//                        });
//                    }
//                });


//
        try {
            for (int i = 0; i < strings.size(); i++) {
                byte[] data = ImageService.getImage2(strings.get(i));//???????????????????????????
                int length = data.length;
                //???????????????imageView????????????????????????
                Bitmap bm = BitmapFactory.decodeByteArray(data, 0, length);
                arrayPicture[i] = bm;
            }
//                    byte[] data = ImageService.getImage2(path);//???????????????????????????
//                    int length = data.length;
//                    //???????????????imageView????????????????????????
//                    Bitmap bm= BitmapFactory.decodeByteArray(data, 0, length);
//                    imageView.setImageBitmap(arrayPicture2[0]);

        } catch (Exception e) {
            Log.i(TAG, e.toString());
            Toast.makeText(MainActivity.this, "??????????????????" + e.toString(), Toast.LENGTH_LONG).show();
        }
        //??????????????????
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                String path=urlpath.getText().toString();
//                ArrayList<String> strings = new ArrayList<>();
//                strings.add(path);
//                strings.add(path2);
//
////                OkHttpClient client = new OkHttpClient.Builder().build();
////                Request request = new Request.Builder().get().url(path).build();
////                Call call = client.newCall(request);
////                call.enqueue(new Callback() {
////                    @Override
////                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
////                        Log.e(TAG, "onFailure: " + e.toString());
////                    }
////
////                    @Override
////                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
////                        InputStream inputStream = response.body().byteStream();
////                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
////                        runOnUiThread(new Runnable() {
////                            @Override
////                            public void run() {
////                                imageView.setImageBitmap(bitmap);
////                            }
////                        });
////                    }
////                });
//
//
////
//                try {
//                    for (int i = 0; i < strings.size(); i++) {
//                        byte[] data = ImageService.getImage2(strings.get(i));//???????????????????????????
//                        int length = data.length;
//                        //???????????????imageView????????????????????????
//                        Bitmap bm = BitmapFactory.decodeByteArray(data, 0, length);
//                        arrayPicture2[i] = bm;
//                    }
////                    byte[] data = ImageService.getImage2(path);//???????????????????????????
////                    int length = data.length;
////                    //???????????????imageView????????????????????????
////                    Bitmap bm= BitmapFactory.decodeByteArray(data, 0, length);
////                    imageView.setImageBitmap(arrayPicture2[0]);
//
//                } catch (Exception e) {
//                    Log.i(TAG, e.toString());
//                    Toast.makeText(MainActivity.this, "??????????????????" + e.toString(), Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        //??????????????????
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
//                imageView.setImageResource(arrayPicture[index]);//????????????????????????????????????
                imageView.setImageBitmap(arrayPicture[index]);//????????????????????????????????????
                return imageView;//??????????????????
            }
        });
        //?????????????????????
        imageSwitcher.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                //???????????????????????????  ??????????????????X??????
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    touchDownX = event.getX();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchUpX = event.getX();
                    //?????????????????????????????????
                    if (touchUpX - touchDownX > 100) {
                        //?????????????????????????????? ????????????????????????????????????????????????
                        // ???????????????????????????
                        index = index == 0 ? arrayPicture.length - 1 : index - 1;
                        //???????????????????????????
                        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left));
                        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_out_right));
                        Drawable drawable =new BitmapDrawable(getResources(),arrayPicture[index]);
                        imageSwitcher.setImageDrawable(drawable);
                    } else if (touchDownX - touchUpX > 100) {
                        index = index == arrayPicture.length - 1 ? 0 : index + 1;//????????????????????????0????????????????????????????????????1
                        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out));
                        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in));

                        Drawable drawable =new BitmapDrawable(getResources(),arrayPicture[index]);
                        imageSwitcher.setImageDrawable(drawable);

                    }
                    return true;
                }
                return false;
            }
        });

    }
}