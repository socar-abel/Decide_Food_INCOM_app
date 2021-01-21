package com.inha.incomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "My Log";
    Map<String, Object> map1;
    Map<String, Object> map2;
    Map<String, String> map3 = new HashMap<String,String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //database (kim sang woo)
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference("situation/slow");
        DatabaseReference myRef2 = database.getReference("type/korean");

        // Read from the database
        myRef1.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                map1 = (Map<String, Object>) dataSnapshot.getValue();
                Log.d(TAG, "Value1 is: " + map1);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        myRef2.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                map2 = (Map<String, Object>) dataSnapshot.getValue();
                Log.d(TAG, "Value2 is: " + map2);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



        HashMap<String,Object> putMap = new HashMap<String,Object>();
        Log.e(TAG, "putMap is: " + putMap);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable()  {
            public void run() {
                // 시간 지난 후 실행할 코딩

                putMap.putAll(map1);
                Log.d(TAG, "putMap is: " + putMap);

                // 교집합 구하기
                Map<String,Object> copiedMap1 = new HashMap<String,Object>(map1);
                Log.d(TAG, "copiedMap1 is: " + copiedMap1);

                copiedMap1.entrySet().retainAll(map2.entrySet());
                Log.d(TAG, "교집합 is: " + copiedMap1);

            }
        }, 3000); // 3초


//
//        HashMap<String,Object> entryMap = new HashMap<String,Object>();
//        for(Map.Entry<String,Object> entry : map1.entrySet()){
//            entryMap.put(entry.getKey(),entry.getValue());
//        }
//        Log.d(TAG, "entryMap is: " + putMap);

    }
}