package com.inha.incomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener{

    static final int TYPE_STATE = 0;
    static final int TIME_STATE = 1;
    static final int SITUATION_STATE = 2;
    static final int SOUP_STATE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Button select = (Button)findViewById(R.id.b_select);
        Button rand = (Button)findViewById(R.id.b_rand);

        select.setOnClickListener(this);
        rand.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.b_select:
                intent = new Intent(SelectActivity.this, QuestionActivity.class);
                startActivity(intent);
                break;
            case R.id.b_rand:
                String[] results = new String[5];
                results[TYPE_STATE] = choice(TYPE_STATE, new Random().nextInt(5));
                results[TIME_STATE] = choice(TIME_STATE, new Random().nextInt(4));
                results[SITUATION_STATE] = choice(SITUATION_STATE, new Random().nextInt(4));
                results[SOUP_STATE] = choice(SOUP_STATE, new Random().nextInt(2));
                intent = new Intent(SelectActivity.this, ResultActivity.class);
                intent.putExtra("data",results);
                startActivity(intent);
                break;
        }
    }

    String choice(int state, int idx){
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
}