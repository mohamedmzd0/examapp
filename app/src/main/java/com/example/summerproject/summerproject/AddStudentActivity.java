package com.example.summerproject.summerproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class AddStudentActivity extends AppCompatActivity {
    private EditText username_edittext, password_edittext,confirm_password_edittext;
    private Button add_btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        username_edittext = (EditText) findViewById(R.id.username_edittext);
        password_edittext = (EditText) findViewById(R.id.password_edittext);
        add_btn= (Button) findViewById(R.id.add_btn);
        confirm_password_edittext= (EditText) findViewById(R.id.confrim_password_edittext);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username_edittext.getText().toString().isEmpty()) {
                    username_edittext.setError("Username Required");
                    return;
                }
                if (username_edittext.getText().toString().length()<4) {
                    username_edittext.setError("Username error");
                    return;
                }
                if (password_edittext.getText().toString().isEmpty()) {
                    password_edittext.setError("Password Required");
                    return;
                }
                if (password_edittext.getText().toString().length()<8 ) {
                    password_edittext.setError("password too week");
                    return;
                }
                if(!password_edittext.getText().toString().equals(confirm_password_edittext.getText().toString())) {
                    confirm_password_edittext.setError("password not match");
                    return;
                }
               add_student(username_edittext.getText().toString(),password_edittext.getText().toString());
            }
        });
    }

    private void add_student(String name, String pass) {
        LoginModel model = new LoginModel(name,pass);
        FirebaseDatabase.getInstance().getReference("student").push().setValue(model) ;
        Toast.makeText(this, "add sucessful", Toast.LENGTH_SHORT).show();
        finish();
    }
}
