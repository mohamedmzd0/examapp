package com.example.summerproject.summerproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SelectUserActivity extends AppCompatActivity {

    private boolean ADMIN = false;
    private Button student_btn, admin_btn, register_btn, change_password_btn, add_quistion_btn, add_student_btn, marks_btn;
    private EditText username_edittext, password_edittext;
    private ProgressBar progress_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        student_btn = (Button) findViewById(R.id.student_btn);
        admin_btn = (Button) findViewById(R.id.admin_btn);
        register_btn = (Button) findViewById(R.id.register_btn);
        username_edittext = (EditText) findViewById(R.id.username_edittext);
        password_edittext = (EditText) findViewById(R.id.password_edittext);
        progress_login = (ProgressBar) findViewById(R.id.progress_login);
        change_password_btn = (Button) findViewById(R.id.change_password_btn);
        add_quistion_btn = (Button) findViewById(R.id.add_quistion_btn);
        add_student_btn = (Button) findViewById(R.id.add_student_btn);
        marks_btn = (Button) findViewById(R.id.marks_bttn);
/// if user or admin button select hide linearyout that has buttons and show linear that has login
        student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ADMIN = false;
                findViewById(R.id.button_linearlayout).setVisibility(View.GONE);
                findViewById(R.id.register_layout).setVisibility(View.VISIBLE);
            }
        });

        ///and so on
        admin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ADMIN = true;
                findViewById(R.id.button_linearlayout).setVisibility(View.GONE);
                findViewById(R.id.register_layout).setVisibility(View.VISIBLE);
            }
        });
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check username ,password inputs not empty
                if (username_edittext.getText().toString().isEmpty()) {
                    username_edittext.setError("Username Required");
                    return;
                }
                if (password_edittext.getText().toString().isEmpty()) {
                    password_edittext.setError("Password Required");
                    return;
                }
                //if data entered show progressbar and check data
                progress_login.setVisibility(View.VISIBLE);
                check_login(username_edittext.getText().toString(), password_edittext.getText().toString());
            }
        });
        change_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///this is dialog activity implemented in manifest
                startActivity(new Intent(getApplicationContext(), ChangeAdminPassword.class));
            }
        });
        add_quistion_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddQuizedActivity.class));
            }
        });
        add_student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///this is dialog activity implemented in manifest
                startActivity(new Intent(getApplicationContext(), AddStudentActivity.class));
            }
        });
        marks_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),MarksActivity.class));
            }
        });
    }

    private void check_login(final String name, final String password) {

        if (ADMIN) {
            FirebaseDatabase.getInstance().getReference("admin").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //here i can get data
                    if (dataSnapshot != null) {
                        LoginModel loginModel = dataSnapshot.getValue(LoginModel.class); //get data that matches with this class
                        if (loginModel.username.equals(name) && loginModel.password.equals(password)) {
                            findViewById(R.id.admin_linearlayout).setVisibility(View.VISIBLE);
                            findViewById(R.id.register_layout).setVisibility(View.GONE);
                        }
                    }
                    progress_login.setVisibility(View.INVISIBLE);
                }
                //close progressbase finally

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
            FirebaseDatabase.getInstance().getReference("student").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    if (dataSnapshot != null) {
                        LoginModel loginModel = dataSnapshot.getValue(LoginModel.class); //get data that matches with this class
                        if (loginModel.username.equals(name) && loginModel.password.equals(password)) {
                            Intent intent=new Intent(getApplicationContext(), GetQuitions.class);
                            intent.putExtra("username",name) ; //send student name to store the mark with the name
                            startActivity(intent);
                            finish();
                        }
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
}
