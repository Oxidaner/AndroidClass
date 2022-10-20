package com.example.android007_net_imageview;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android007_net_imageview.service.ImageService;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private EditText urlpath;
    private ImageView imageView;
    private Button button;
    private  int[]  arrayPicture=new int[]{
            R.drawable.a1,R.drawable.a2};
    private ImageSwitcher imageSwitcher;
    private int  index;
    private  float touchDownX;
    private  float touchUpX;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //获取url地址
        urlpath=(EditText) this.findViewById(R.id.urlpath);
        Button button=(Button) this.findViewById(R.id.button);
        imageView=(ImageView) this.findViewById(R.id.imageView);
        imageSwitcher=findViewById(R.id.imageswitch);

        //设置事件监听
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path=urlpath.getText().toString();

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
                    byte[] data = ImageService.getImage2(path);//已经测试完成的方法
                    int length = data.length;
                    //在界面中和imageView主键进行绑定操作
                    Bitmap bm= BitmapFactory.decodeByteArray(data, 0, length);
                    imageView.setImageBitmap(bm);

                } catch (Exception e) {
                    Log.i(TAG, e.toString());
                    Toast.makeText(MainActivity.this, "连接网络错误"+e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}