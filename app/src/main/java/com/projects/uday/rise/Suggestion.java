package com.projects.uday.rise;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Suggestion extends AppCompatActivity {

    private Button suggSubmit;
    private EditText suggName ;
    private EditText suggEmail;
    private EditText sugg ;
    private String sugg_name = "" ;
    private String sugg_email = "";
    private String suggest = "";
    private FirebaseDatabase database;
    private DatabaseReference reference ;
    private SuggData suggData ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestions);
        suggSubmit = (Button)findViewById(R.id.but_sugg_sub) ;
        suggName = (EditText)findViewById(R.id.ed_name) ;
        suggEmail = (EditText)findViewById(R.id.ed_email) ;
        sugg = (EditText)findViewById(R.id.ed_sugg);
        suggName.requestFocus() ;
        database = FirebaseDatabase.getInstance() ;
        reference = database.getReference().child("Suggestions");
        suggData = new SuggData();

        suggSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sugg_name = suggName.getText().toString() ;
                sugg_email = suggEmail.getText().toString() ;
                suggest = sugg.getText().toString() ;

                if(!suggest.isEmpty()){
                    suggData.setName(sugg_name);
                    suggData.setEmail(sugg_email);
                    suggData.setSuggestion(suggest);
                    try{
                    reference.push().setValue(suggData);
                    Toast.makeText(getApplicationContext(), "(: Thank you :)",Toast.LENGTH_SHORT).show();
                    finish();
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please enter valid information!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


}
