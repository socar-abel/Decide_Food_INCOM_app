package com.inha.incomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener{

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
                intent= new Intent(SelectActivity.this, ResultActivity.class);
                startActivity(intent);
                break;
        }
    }
}