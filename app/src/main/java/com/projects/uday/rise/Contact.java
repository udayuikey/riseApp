package com.projects.uday.rise;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Contact extends AppCompatActivity {

    private TextView aboutDoc ;
    private TextView docWebsite ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);

        aboutDoc = (TextView)findViewById(R.id.contact_tv_about_doc) ;
        docWebsite = (TextView)findViewById(R.id.contact_tv_doc_website) ;

        aboutDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Contact.this, Docswords.class)  ;
                startActivity(intent);
                }
        });

        docWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://myadvisorfriend.com/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

    }
}
