package com.inha.incomapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "My Log";
    Map<String, Object> map0;
    Map<String, Object> map1;
    Map<String, Object> map2;
    Map<String, Object> map3;
    //Map<String, String> map3 = new HashMap<String,String>();
    String comment = "Loading..";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String[] data = intent.getStringArrayExtra("data");
        Log.d("DATA",data[0] + ", " + data[1] + ", " + data[2] + ", " + data[3]);

        ImageView img = (ImageView) findViewById(R.id.img);
        Glide.with(this).load(R.drawable.loading).into(img);

        //database (kim sang woo)
        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef1 = database.getReference("situation/slow");
//        DatabaseReference myRef2 = database.getReference("type/korean");

        DatabaseReference myRef[] = new DatabaseReference[4];
        for(int i=0;i<4;i++){   //0:type, 1:time, 2:situation, 3:soup
            myRef[i] = database.getReference(data[i]);
        }

        // 수정
        myRef[0].addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                map0 = (Map<String, Object>) dataSnapshot.getValue();
                Log.d(TAG, "Value0 is: " + map0);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //수정<


        // Read from the database
        myRef[1].addValueEventListener(new ValueEventListener() {

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

        myRef[2].addValueEventListener(new ValueEventListener() {

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

        myRef[3].addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                map3 = (Map<String, Object>) dataSnapshot.getValue();
                Log.d(TAG, "Value3 is: " + map3);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable()  {
            public void run() {
                // 시간 지난 후 실행할 코딩
                // 교집합 구하기
                Map<String,Object> copiedMap1 = new HashMap<String,Object>(map0);
                copiedMap1.entrySet().retainAll(map1.entrySet());
                Log.d(TAG, "0과1의 교집합 is: " + copiedMap1);

//                Map<String,Object> copiedMap2 = new HashMap<String,Object>(copiedMap1);
//                copiedMap1.entrySet().retainAll(map2.entrySet());
//                Log.d(TAG, "최종 교집합 is: " + copiedMap2);

                Map<String,Object> copiedMap2 = new HashMap<String,Object>(map2);
                copiedMap1.entrySet().retainAll(map3.entrySet());
                Log.d(TAG, "2와3의 교집합B is: " + copiedMap1);

                Map<String,Object> copiedMap3 = new HashMap<String,Object>(copiedMap1);
                copiedMap1.entrySet().retainAll(copiedMap2.entrySet());
                Log.d(TAG, "최종, A와 B의 교집합 is: " + copiedMap3);


                //copiedMap3 에서 image 띄우기

                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageReference = storage.getReference();

                //String 형 배열에 값 담기
                String[] resultMenu = new String[copiedMap3.size()];
                int random = (int) ( Math.random()*copiedMap3.size() );
                String key = "";

                int i=0;
                for (String mapkey : copiedMap3.keySet()){

                    if(i==random){
                       key=mapkey;
                        DatabaseReference comRef = FirebaseDatabase.getInstance().getReference();
                        String s = "comment";
                        comRef = database.getReference(s);

                        comRef.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // This method is called once with the initial value and again
                                // whenever data at this location is updated.
                                Map<String,Object> map = (Map<String, Object>) dataSnapshot.getValue();
                                comment = map.get(mapkey).toString();
                                TextView cmt = (TextView)findViewById(R.id.comment);
                                cmt.setText(comment);
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                // Failed to read value
                                Log.w(TAG, "Failed to read value.", error.toException());
                            }


                        });

                    }

                    resultMenu[i]=copiedMap3.get(mapkey).toString();
                    System.out.println("Key:"+mapkey+", Value:"+copiedMap3.get(mapkey));
                    Log.d(TAG, "Key:"+mapkey+", Value:"+copiedMap3.get(mapkey));
                    i++;


                }






                Log.d(TAG, "resultMenu is: " + resultMenu);

                //resultMenu에서 random으로 1개 선택

                String imageName = resultMenu[random]+".jpg";

                Log.d(TAG, "imageName is: " + imageName);

                //image 띄우기
                storageReference.child(imageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(getApplicationContext())
                                .load(uri)
                                .into(img);
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                    }
                });


                //Text 설정
                TextView txt = (TextView) findViewById(R.id.txt);
                txt.setText(resultMenu[random]);

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