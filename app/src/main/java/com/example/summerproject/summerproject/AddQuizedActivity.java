package com.example.summerproject.summerproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddQuizedActivity extends AppCompatActivity {

    private CheckBox true_false_btn;
    private Button addmcq_btn,submit_btn;
    private GridView ans_list;
    private EditText questions,mcq_edittext;
    private String currect_ans=null;
    private ArrayList<String>ans_array=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quized);
        true_false_btn= (CheckBox) findViewById(R.id.true_false_btn) ;
        questions= (EditText) findViewById(R.id.questions) ;
        addmcq_btn= (Button) findViewById(R.id.addmcq_btn);
        ans_list= (GridView) findViewById(R.id.ans_list) ;
        submit_btn= (Button) findViewById(R.id.submit_btn);
        mcq_edittext= (EditText) findViewById(R.id.mcq_edittext) ;
        true_false_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(true_false_btn.isChecked())
                {
                    addmcq_btn.setEnabled(false);
                    mcq_edittext.setEnabled(false);
                    ans_array.clear();
                    ans_array.add("true");
                    ans_array.add("false");
                    ans_list.setAdapter(new Answer_list_adapter(getApplicationContext(),ans_array));
                    if(ans_array.size()%2==0)
                    ans_list.getLayoutParams().height= ans_array.size()/2 * 70;
                    else
                        ans_list.getLayoutParams().height= (ans_array.size()+1)/2 * 70;
                }else
                {
                    addmcq_btn.setEnabled(true);
                    mcq_edittext.setEnabled(true);
                    ans_array.clear();
                    ans_list.setAdapter(new Answer_list_adapter(getApplicationContext(),ans_array));
                    if(ans_array.size()%2==0)
                        ans_list.getLayoutParams().height= ans_array.size()/2 * 70;
                    else
                        ans_list.getLayoutParams().height= (ans_array.size()+1)/2 * 70;
                }
            }
        });
        addmcq_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mcq_edittext.getText().toString().isEmpty())
                ans_array.add(mcq_edittext.getText().toString());
                ans_list.setAdapter(new Answer_list_adapter(getApplicationContext(),ans_array));
                if(ans_array.size()%2==0)
                    ans_list.getLayoutParams().height= ans_array.size()/2 * 70;
                else
                    ans_list.getLayoutParams().height= (ans_array.size()+1)/2 * 70;
            }
        });
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_valid_inputs();
            }
        });
        ans_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                currect_ans=ans_array.get(position) ;
                Toast.makeText(AddQuizedActivity.this, currect_ans+" is select as currect answer", Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }

    private void check_valid_inputs() {
        if(questions.getText().toString().isEmpty()) {
            questions.setError("questions is required");
        return;
        }
        if(ans_array.isEmpty() && !true_false_btn.isChecked())
        {
            Toast.makeText(this, "answer not found ..", Toast.LENGTH_SHORT).show();
            return;
        }
        if(currect_ans==null)
        {
            Toast.makeText(this, "select from list the correct answer ", Toast.LENGTH_SHORT).show();
            return;
        }
        QuizModel model =new QuizModel(questions.getText().toString(),ans_array,currect_ans);
        FirebaseDatabase.getInstance().getReference("questions").push().setValue(model) ;
        Toast.makeText(this, "question added sucessfully", Toast.LENGTH_SHORT).show();
        questions.setText("");
        ans_array.clear();
        currect_ans=null;
    }
}
