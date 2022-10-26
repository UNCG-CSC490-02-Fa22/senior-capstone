package com.example.gymbuddies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MatchBioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_bio);

        String value = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("userMatchName");
            //The key argument here must match that used in the other activity
        }
        TextView textView = (TextView) findViewById(R.id.editTextTextPersonName5);
        textView.setText(value);

        Button button = findViewById(R.id.bioButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}