package com.example.summerproject.summerproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;


public class MarksAdapter extends RecyclerView.Adapter<Holder> {
    private ArrayList<MarksModel> marksModelArrayList ;
    private Context context;

    public MarksAdapter(ArrayList<MarksModel> marksModelArrayList, Context context) {
        this.marksModelArrayList = marksModelArrayList;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.marks_recycle_item,null);
        Holder holder =new Holder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.name.setText(marksModelArrayList.get(position).studentName);
        holder.marks.setText(""+marksModelArrayList.get(position).mark);
    }

    @Override
    public int getItemCount() {
        return marksModelArrayList.size();
    }

}class Holder extends RecyclerView.ViewHolder
{

    TextView name ;
    TextView marks ;

    public Holder(View itemView) {
        super(itemView);
        name= (TextView) itemView.findViewById(R.id.name) ;
        marks= (TextView) itemView.findViewById(R.id.mark);
    }
}