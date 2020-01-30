package com.projects.uday.rise;

import android.content.Context;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.ConversationActions;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import static android.view.View.GONE;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<TestData> data;
    private RadioGroup lastCheckedRadioGroup = null;
    private Context context ;
    private int resList[] ;
    private OnItemClick callback;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView question;
        public RadioButton rarely, often;


        public RadioGroup radioGroup;

        public MyViewHolder(final View view) {
            super(view);
            resList = new int[data.size()] ;
            question = (TextView) view.findViewById(R.id.recycler_question);
            radioGroup = (RadioGroup)view.findViewById(R.id.radiogroup) ;
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                        resList[getAdapterPosition()] =  radioGroup.getCheckedRadioButtonId() ;
                        increment(resList[getAdapterPosition()], getAdapterPosition()) ;

                        rarely=(RadioButton)itemView.findViewById(resList[getAdapterPosition()]);
                        Toast.makeText(context,rarely.getText(), Toast.LENGTH_SHORT).show();

                        notifyDataSetChanged();

        }

            });
                    }
    }


    public MyAdapter(List<TestData> data, Context context, OnItemClick onItemClick) {
        this.data = data;
        this.context = context ;
        this.callback = onItemClick ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_questions, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.setIsRecyclable(false);
            TestData testData = data.get(position);
            holder.question.setText(testData.getQuestion());
            holder.radioGroup.setTag(position);


    }

    @Override
    public int getItemCount() {
        return data.size();
   }

    public void increment(int value, int position){
        callback.onClick(value, position);
    }


}