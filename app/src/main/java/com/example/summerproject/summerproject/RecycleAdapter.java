package com.example.summerproject.summerproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class RecycleAdapter extends RecyclerView.Adapter<RecycleHolder> {
    private ArrayList<QuizModel>quizModelArrayList ;
    private Context context;
    private int count=0;
    private String username ;
    private int marks =0 ;
    public RecycleAdapter(Context context ,ArrayList<QuizModel> quizModelArrayList,String username) {
        this.context=context;
        this.quizModelArrayList = quizModelArrayList;
        this.username=username ;
    }

    @Override
    public RecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_quistion_item,null);
        RecycleHolder holder =new RecycleHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecycleHolder holder, final int position) {
    holder.quiz.setText(quizModelArrayList.get(position).quiz);
        holder.ans.setAdapter(new Answer_list_adapter(context,quizModelArrayList.get(position).answer));
        if(quizModelArrayList.get(position).answer.size()%2==0)
            holder.ans.getLayoutParams().height= quizModelArrayList.get(position).answer.size()/2 * 65;
        else
            holder.ans.getLayoutParams().height= (quizModelArrayList.get(position).answer.size()+1)/2 * 65;
        holder.ans.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int p, long id) {
                holder.view.setVisibility(View.INVISIBLE);
                count++;
                Toast.makeText(context, "answer submitted", Toast.LENGTH_SHORT).show();
                if(quizModelArrayList.get(position).answer.get(p).equals(quizModelArrayList.get(position).currect_ans))
                   marks++;
                if(count==quizModelArrayList.size())
                    write_final_score();
                return false;
            }
        });
    }

    private void write_final_score() {
        MarksModel model =new MarksModel(username,marks) ;
        FirebaseDatabase.getInstance().getReference("marks").push().setValue(model) ;
        Toast.makeText(context, "your mark is "+marks, Toast.LENGTH_SHORT).show();
        System.exit(0);
    }

    @Override
    public int getItemCount() {
        return quizModelArrayList.size();
    }
}class RecycleHolder extends RecyclerView.ViewHolder
{

    TextView quiz ;
    GridView ans;
    View view;
    public RecycleHolder(View itemView) {
        super(itemView);
        view=itemView;
        quiz= (TextView) itemView.findViewById(R.id.quistion_textview) ;
        ans= (GridView) itemView.findViewById(R.id.currect_answer_list);
    }
}
