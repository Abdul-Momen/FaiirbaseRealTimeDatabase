package com.momen.faiirbaserealtimedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button savebtn,loadBtn;
    private EditText nameet,ageet;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("students");

        savebtn = findViewById(R.id.save_button);
        loadBtn = findViewById(R.id.button_load);
        nameet = findViewById(R.id.editText);
        ageet = findViewById(R.id.editText2);

       savebtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               savedata();
           }
       });

       loadBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent =new Intent(MainActivity.this,DetailsAcitivity.class);
               startActivity(intent);
           }
       });
    }

    private void savedata() {

        String name= nameet.getText().toString().trim();
        String age= ageet.getText().toString().trim();


         String key =databaseReference.push().getKey();

        Student student = new Student (name,age);
        databaseReference.child(key).setValue(student);

        Toast.makeText(getApplicationContext(), "studient info is added", Toast.LENGTH_SHORT).show();


    }
}
