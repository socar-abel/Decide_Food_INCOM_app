package com.inha.incomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{

    int state = TYPE_STATE;
    static final int TYPE_STATE = 0;
    static final int TIME_STATE = 1;
    static final int SITUATION_STATE = 2;
    static final int SOUP_STATE = 3;

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;

    String[] results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        results = new String[5];


        btn1 = (Button)findViewById(R.id.btn_q1);
        btn2 = (Button)findViewById(R.id.btn_q2);
        btn3 = (Button)findViewById(R.id.btn_q3);
        btn4 = (Button)findViewById(R.id.btn_q4);
        btn5 = (Button)findViewById(R.id.btn_q5);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);

        btnSetting(btn1,btn2,btn3,btn4,btn5);


    }
    String choice(int idx){
        if(state == TYPE_STATE){
            switch (idx){
                case 0:
                    return "type_korean";
                case 1:
                    return "type_japanese";
                case 2:
                    return "type_chinese";
                case 3:
                    return "type_western";
                case 4:
                    return "type_other";
            }
        }
        else if(state == TIME_STATE){
            switch (idx){
                case 0:
                    return "time_morning";
                case 1:
                    return "time_lunch";
                case 2:
                    return "time_dinner";
                case 3:
                    return "time_dawn";
            }
        }
        else if(state == SITUATION_STATE){
            switch (idx){
                case 0:
                    return "situation_fast";
                case 1:
                    return "situation_slow";
                case 2:
                    return "situation_alchol";
                case 3:
                    return "situation_many";
            }
        }
        else if(state == SOUP_STATE){
            switch (idx){
                case 0:
                    return "soup";
                case 1:
                    return "no_soup";
            }
        }
        return "";
    }

    void btnSetting(Button b1, Button b2, Button b3, Button b4, Button b5){
        if(state == TYPE_STATE){
            b1.setText("한식");
            b2.setText("일식");
            b3.setText("중식");
            b4.setText("양식");
            b5.setText("아무거나");
        }
        else if(state == TIME_STATE){
            b1.setText("아침");
            b2.setText("점심");
            b3.setText("저녁");
            b4.setText("새벽");
            b5.setVisibility(View.GONE);
        }
        else if(state == SITUATION_STATE){
            b1.setText("빠르게");
            b2.setText("천천히");
            b3.setText("술안주");
            b4.setText("양 많이");
            b5.setVisibility(View.GONE);
        }
        else if(state == SOUP_STATE){
            b1.setText("국물");
            b2.setText("국물 없음");
            b3.setVisibility(View.GONE);
            b4.setVisibility(View.GONE);
            b5.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_q1:
                results[state] = choice(0);
                break;
            case R.id.btn_q2:
                results[state] = choice(1);
                break;
            case R.id.btn_q3:
                results[state] = choice(2);
                break;
            case R.id.btn_q4:
                results[state] = choice(3);
                break;
            case R.id.btn_q5:
                results[state] = choice(4);
                break;
        }
        state++;
        if(state <= SOUP_STATE)
            btnSetting(btn1,btn2,btn3,btn4,btn5);
        else {
            Intent intent= new Intent(QuestionActivity.this, ResultActivity.class);
            intent.putExtra("data",results);
            startActivity(intent);
            finish();
        }
    }
}