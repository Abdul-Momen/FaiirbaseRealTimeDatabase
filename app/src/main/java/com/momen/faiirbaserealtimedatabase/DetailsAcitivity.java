package com.momen.faiirbaserealtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailsAcitivity extends AppCompatActivity {
    private ListView listView;
    private List<Student> studentList;

    private Adapter adapter;

    DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_acitivity);

        listView=findViewById(R.id.listView);

        databaseReference = FirebaseDatabase.getInstance().getReference("students");
            studentList = new ArrayList<>();

            adapter= new Adapter(DetailsAcitivity.this,studentList);



    }


    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                studentList.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    Student student = dataSnapshot1.getValue(Student.class);
                    studentList.add(student);

                }

                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(DetailsAcitivity.this, "faild", Toast.LENGTH_SHORT).show();

            }
        });
        super.onStart();
    }
}
