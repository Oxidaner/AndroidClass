package com.example.android001;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class AndroidSee extends AppCompatActivity {
    private ProgressBar pb_progressBar1;
    private int progress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);

        Button btnStart = (Button) this.findViewById(R.id.btn_start);
        Button btnStop = (Button) this.findViewById(R.id.btn_stop);

        pb_progressBar1 = (ProgressBar) findViewById(R.id.pb_progressBar1);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (progress <= 100) {
                    progress = pb_progressBar1.getProgress();
                    progress += (int) (100 * 0.2);
                    pb_progressBar1.setProgress(progress);
                } else progress = 100;

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (progress != 0) {
                    progress = pb_progressBar1.getProgress();
                    progress -= (int) (100 * 0.2);
                    pb_progressBar1.setProgress(progress);
                } else progress = 0;
            }
        });

    }

}


