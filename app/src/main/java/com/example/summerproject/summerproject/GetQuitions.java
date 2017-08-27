package com.example.summerproject.summerproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GetQuitions extends AppCompatActivity {

    private ArrayList<QuizModel> quizModelArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_quitions);
        recyclerView = (RecyclerView) findViewById(R.id.exam_recyclerview);
        username = getIntent().getStringExtra("username");
        getquiz();
    }

    public void getquiz() {
        FirebaseDatabase.getInstance().getReference("questions").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                try {
                    QuizModel model = dataSnapshot.getValue(QuizModel.class);
                    quizModelArrayList.add(model);
                } catch (Exception e) {
                }
                recyclerView.setAdapter(new RecycleAdapter(getApplicationContext(), quizModelArrayList, username));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
