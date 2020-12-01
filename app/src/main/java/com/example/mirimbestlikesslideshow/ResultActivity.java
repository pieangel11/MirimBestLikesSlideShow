package com.example.mirimbestlikesslideshow;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class ResultActivity extends AppCompatActivity {
    Button btnStart, btnStop;
    ViewFlipper flipper;
    int[] imgRes = {R.drawable.r01, R.drawable.r02, R.drawable.r03, R.drawable.r04, R.drawable.r05, R.drawable.r06, R.drawable.r07, R.drawable.r08, R.drawable.r09};
    String[] imgNames = {"독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀", "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매", "피아노 레슨", "피아노 앞의 소녀들", "해변에서"};
    ImageView[] imgvs = new ImageView[imgNames.length];
    int[] imgvIds = {R.id.img1,R.id.img2,R.id.img3,R.id.img4,R.id.img5,R.id.img6,R.id.img7,R.id.img8,R.id.img9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnStart = findViewById(R.id.start_btn);
        btnStop = findViewById(R.id.stop_btn);
        btnStart.setOnClickListener(btnListener);
        btnStop.setOnClickListener(btnListener);
        flipper = findViewById(R.id.img);

        flipper.setFlipInterval(1000);

        Intent intent = getIntent();
        int[] voteCount = intent.getIntArrayExtra("voteCount");
        int temp1 = 0;
        int temp2 = 0;
        for(int i = 0;i < imgRes.length-1; i++) {
            for(int j = i+1; j < imgRes.length; j++) {
                if(voteCount[i] < voteCount[j]) {
                    temp1 = voteCount[i];
                    voteCount[i] = voteCount[j];
                    voteCount[j] = temp1;

                    temp2 = imgRes[i];
                    imgRes[i] = imgRes[j];
                    imgRes[j] = temp2;
                }
            }
        }

        for(int i=0; i<imgvIds.length; i++) {
            imgvs[i] = findViewById(imgvIds[i]);
            imgvs[i].setImageResource(imgRes[i]);
        }
    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.start_btn:
                    flipper.startFlipping();
                    break;
                case R.id.stop_btn:
                    flipper.stopFlipping();
                    break;
            }
        }
    };
}