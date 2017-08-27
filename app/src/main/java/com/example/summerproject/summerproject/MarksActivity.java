package com.example.summerproject.summerproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MarksActivity extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private ArrayList<MarksModel>marksModelArrayList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);
        recyclerView= (RecyclerView) findViewById(R.id.recycleview_marks);
        getmarks();
    }

    public void getmarks() {
        FirebaseDatabase.getInstance().getReference("marks").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if(dataSnapshot!=null)
                {
                    MarksModel model =dataSnapshot.getValue(MarksModel.class);
                    marksModelArrayList.add(model);
                    recyclerView.setAdapter(new MarksAdapter(marksModelArrayList,getApplicationContext()));
                }
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
