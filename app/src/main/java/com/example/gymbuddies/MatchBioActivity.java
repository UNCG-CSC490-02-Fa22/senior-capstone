package com.example.gymbuddies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
        TextView skillLevel = (TextView) findViewById(R.id.skillLevel);
        TextView bio = (TextView) findViewById(R.id.bio);

        String bioDesc = "I do not have a gym membership, I much prefer trails and hiking or playing rounds of disc golf, you know the lighter activities that doesn't always feel like working out, but more of being active. I am not opposed to finding a gym partner if that's your thing, but I have reached a plateau after losing 65 lbs in the last half year and I think having someone to be active with can help me turn the corner. Any ages or fitness levels are welcome as well as any gender. I work in Raleigh area and live in Sanford to give a better idea on a more local area.\n" +
                "\n";
        textView.setText(value);
        bio.setText(bioDesc);
        skillLevel.setText("Beginner");

        CardView card = findViewById(R.id.cardView);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}