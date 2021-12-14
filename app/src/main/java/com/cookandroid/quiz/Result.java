package com.cookandroid.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultLabel = (TextView)findViewById(R.id.resultLabel);
        TextView totalScore = (TextView)findViewById(R.id.totalScore);

        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);

        // 데이터를 저장하기 위해 SharedPreferences 사용
        SharedPreferences settings = getSharedPreferences("quiz", Context.MODE_PRIVATE);
        int total = settings.getInt("total", 0);
        total = score;

        resultLabel.setText(score + " / 5");
        totalScore.setText("전체 점수 : " + total);

        // 전체 점수 저장
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("total", total);
        editor.commit();

    }
}