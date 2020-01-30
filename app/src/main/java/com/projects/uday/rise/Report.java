package com.projects.uday.rise;

import android.app.Activity;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

import static java.lang.Math.round;

public class Report extends AppCompatActivity {

    private TextView happiness;
    private TextView focus;
    private TextView stability;
    private TextView sleep;
    private TextView eating_habits;
    private TextView calmness;
    private Button report_sub ;
    private TextView report_name;
    private TextView report_email;
    private TextView report_age;
    private RadioGroup radioGroup ;
    private RadioButton radioButton ;
    private FirebaseDatabase database;
    private DatabaseReference reference ;
    private ReportData reportData ;
    private LinearLayout proceed_ll ;
    private LinearLayout submit_ll ;
    private Button proceed ;
    private CardView cardView ;

    Intent intent ;
    int resList[] ;
    double inc = 0 ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);

        intent = getIntent() ;
        resList = intent.getIntArrayExtra("factor");
        reportData = new ReportData() ;

        happiness = (TextView)findViewById(R.id.happiness);
        focus = (TextView)findViewById(R.id.cognitive);
        stability = (TextView)findViewById(R.id.behavior);
        sleep = (TextView)findViewById(R.id.sleep) ;
        eating_habits = (TextView)findViewById(R.id.eating_habits) ;
        calmness = (TextView)findViewById(R.id.anxiety) ;

        report_sub = (Button)findViewById(R.id.report_submit);
        report_name = (TextView)findViewById(R.id.report_name);
        report_email = (TextView)findViewById(R.id.report_email);
        report_age = (TextView)findViewById(R.id.report_age);
        proceed_ll = (LinearLayout)findViewById(R.id.report_ll_proceed);
        submit_ll = (LinearLayout)findViewById(R.id.report_ll_submit) ;
        proceed = (Button)findViewById(R.id.report_proceed);
        radioGroup = (RadioGroup)findViewById(R.id.gender_radio_group);
        cardView = (CardView)findViewById(R.id.report_card_sent) ;

        database = FirebaseDatabase.getInstance() ;
        reference = database.getReference().child("Reports");




        for(int i = 0 ; i < 30 ; i = i + 5){

            inc = 0 ;

            inc = inc + (1 + (resList[i] - 1)*5.75) ;

            inc = inc + (1 + (resList[i+1] - 1)*5.75) ;

            inc = inc + (1 + (resList[i+2] - 1)*4.5) ;

            inc = inc + (1 + (resList[i+3] - 1)*3.25) ;

            inc = inc + (1 + (resList[i+4] - 1)*3.25) ;

            switch (i){

                case 0:
                    happiness.setText(String.valueOf(inc + " " + "%"));
                    reportData.setHappiness(String.valueOf(inc));
                    break ;

                case 5:
                    focus.setText(String.valueOf(inc+ " " + "%"));
                    reportData.setFocus(String.valueOf(inc));
                    break ;

                case 10:
                    stability.setText(String.valueOf(inc+ " " + "%"));
                    reportData.setStability(String.valueOf(inc));
                    break;

                case 15:
                    sleep.setText(String.valueOf(inc+ " " + "%"));
                    reportData.setSleep(String.valueOf(inc));
                    break;

                case 20:
                    eating_habits.setText(String.valueOf(inc + " " + "%"));
                    reportData.setEat(String.valueOf(inc));
                    break;

                case 25:
                    calmness.setText(String.valueOf(inc+ " " + "%"));
                    reportData.setCalmness(String.valueOf(inc));
                    break;


            }
        }


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed_ll.setVisibility(View.GONE);
                submit_ll.setVisibility(View.VISIBLE);
            }
        });



        report_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name  = report_name.getText().toString()  ;
                String email = report_email.getText().toString() ;
                String age   = report_age.getText().toString()   ;
                if(radioGroup.getCheckedRadioButtonId() == -1){

                    Toast.makeText(getApplicationContext(),"Please Select Gender !", Toast.LENGTH_SHORT).show();

                }
                else{
                    radioButton = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                    if(age.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Please Enter Age !", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        reportData.setName(name);
                        reportData.setEmail(email);
                        reportData.setAge(age);
                        reportData.setGender(String.valueOf(radioButton.getText()));
                        try{
                            reference.push().setValue(reportData);
                            cardView.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),"(: Thank you very much :)", Toast.LENGTH_SHORT).show();
                            InputMethodManager inputMethodManager =
                                    (InputMethodManager)getApplicationContext().getSystemService(
                                            Activity.INPUT_METHOD_SERVICE);
                            try{

                                inputMethodManager.hideSoftInputFromWindow(
                                        Report.this.getCurrentFocus().getWindowToken(), 0);

                            }


                            catch(Exception e) {
                                //nothing in here
                            }
                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                        }


                    }
                }



            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
