package com.inha.incomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

    TextView ment;
    TextView person;

    Button title;

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

        title = (Button)findViewById(R.id.title);

        ment = (TextView)findViewById(R.id.ment);
        person = (TextView)findViewById(R.id.person);

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
                    return "type/korean";
                case 1:
                    return "type/japanese";
                case 2:
                    return "type/chinese";
                case 3:
                    return "type/western";
                case 4:
                    return "type/other";
            }
        }
        else if(state == TIME_STATE){
            switch (idx){
                case 0:
                    return "time/morning";
                case 1:
                    return "time/lunch";
                case 2:
                    return "time/dinner";
                case 3:
                    return "time/dawn";
            }
        }
        else if(state == SITUATION_STATE){
            switch (idx){
                case 0:
                    return "situation/fast";
                case 1:
                    return "situation/slow";
                case 2:
                    return "situation/alchol";

            }
        }
        else if(state == SOUP_STATE){
            switch (idx){
                case 0:
                    return "soup/yes_soup";
                case 1:
                    return "soup/no_soup";
            }
        }
        return "";
    }

    void btnSetting(Button b1, Button b2, Button b3, Button b4, Button b5){
        if(state == TYPE_STATE){
            ment.setText("악인은 먹고 마시기 위해서 살고, 선인은 살기위해 먹고 마신다.");
            person.setText("-소크라테스");
            title.setText("드시고 싶은 음식의 유형은 무엇인가요?");
            b1.setText("한식");
            b2.setText("일식");
            b3.setText("중식");
            b4.setText("양식");
            b5.setText("기타");
        }
        else if(state == TIME_STATE){
            ment.setText("잘못된 음식이란 것은 없다.");
            person.setText("-션 스튜어트");
            title.setText("음식을 먹으려는 시간대는 언제인가요?");
            b1.setText("아침");
            b2.setText("점심");
            b3.setText("저녁");
            b4.setText("야식");
            b5.setVisibility(View.GONE);
        }
        else if(state == SITUATION_STATE){
            ment.setText("좋은 음식은 좋은 대화로 끝이 난다.");
            person.setText("-조프리 네이어");
            title.setText("음식을 먹을 상황은 어떤가요?");
            b1.setText("빨리 먹어야 해요");
            b2.setText("느긋하게 먹어도 돼요");
            b3.setText("술안주가 필요해요");
            b4.setVisibility(View.GONE);
            b5.setVisibility(View.GONE);
        }
        else if(state == SOUP_STATE){
            ment.setText("음식에 대한 사랑보다 더 진실된 사랑은 없다.");
            person.setText("-조지 버나드 쇼");
            title.setText("국물을 드시고 싶나요?");
            b1.setText("국물이 있는게 좋아요");
            b2.setText("국물이 없는게 좋아요");
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