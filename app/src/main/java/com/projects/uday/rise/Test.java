package com.projects.uday.rise;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test extends AppCompatActivity implements OnItemClick {

    private RecyclerView recyclerView ;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<TestData> data = new ArrayList<>();
    private Button submit ;
    private int resList[] ;   
    private int factor[] ;
    private String sa  = "";
    private RadioButton radioButton ;
    private boolean[] alter = {false,true,false,true,false  ,true,true,true,false,false  ,true,true,true,true,true   ,false,true,true,true,false  ,false,true,false,true,true   ,true,true,true,false,true    ,true,true,true,true,true} ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        submit = (Button)findViewById(R.id.test_but_submit) ;
        recyclerView =(RecyclerView)findViewById(R.id.test_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(data, getApplicationContext(),this);
        prepareQuestionData() ;

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        resList = new int[mAdapter.getItemCount()];
        factor = new int[mAdapter.getItemCount()];

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ArrayUtils.contains(resList , 0)){
                    String s = "Please Answer Questions - >" ;
                    for(int i = 0 ; i < mAdapter.getItemCount() ; i++){

                        if(resList[i] == 0){
                            s = s + (i+1) + " ; " ;
                        }

                    }

                    Toast.makeText(getApplicationContext(), s , Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Its Done!!!" , Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Test.this, Report.class) ;
                    intent.putExtra("factor", factor) ;
                    startActivity(intent);
                    finish();
                }

            }
        });


    }

    private void prepareQuestionData() {

    TestData testData = new TestData(QuestionSet.ques1);
    data.add(testData) ;

        testData = new TestData(QuestionSet.ques2);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques3);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques4);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques5);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques6);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques7);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques8);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques9);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques10);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques11);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques12);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques13);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques14);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques15);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques16);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques17);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques18);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques19);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques20);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques21);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques22);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques23);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques24);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques25);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques26);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques27);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques28);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques29);
        data.add(testData) ;

        testData = new TestData(QuestionSet.ques30);
        data.add(testData) ;


        mAdapter.notifyDataSetChanged();


    }


    @Override
    public void onClick(int value, int position) {

        resList[position] = value ;
        radioButton = (RadioButton)findViewById(value) ;
        sa = radioButton.getText().toString();

        if(alter[position] == false){

            switch (sa){

                case "Never":
                    factor[position] = 1 ;
                    break ;

                case "Rarely":
                    factor[position] = 2 ;
                    break ;

                case "Several Days":
                    factor[position] = 3 ;
                    break ;

                case "Often":
                    factor[position] = 4 ;
                    break ;

                case "Very Often":
                    factor[position] = 5 ;
                    break ;



            }

        }else{

            switch (sa){

                case "Never":
                    factor[position] = 5 ;
                    break ;

                case "Rarely":
                    factor[position] = 4 ;
                    break ;

                case "Several Days":
                    factor[position] = 3 ;
                    break ;

                case "Often":
                    factor[position] = 2 ;
                    break ;

                case "Very Often":
                    factor[position] = 1 ;
                    break ;

            }
        }



            }
}
