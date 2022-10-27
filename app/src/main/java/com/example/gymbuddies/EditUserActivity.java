package com.example.gymbuddies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

public class EditUserActivity extends AppCompatActivity {
    private FirebaseAuth mauth;
    private DatabaseReference myDatabase;
    private String userID,nameS,bioS,goalS,skillLevelS, MF;
    private Uri resultURI;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user_bio);








        EditText bio = (EditText) findViewById(R.id.bio);
        EditText goal = (EditText) findViewById(R.id.goal);
        EditText name = (EditText) findViewById(R.id.name);



        EditText skillLevel = (EditText) findViewById(R.id.skillLevel);
        ToggleButton MorF = (ToggleButton) findViewById(R.id.MorF);
        mauth = FirebaseAuth.getInstance();
        userID = mauth.getCurrentUser().getUid();
        myDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);



        String value = "";
        String [] interestList = {"running", "lifting", "yoga"};
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("userMatchName");
            //The key argument here must match that used in the other activity
        }

        Button Back = (Button) findViewById(R.id.back);
        Button Save = (Button) findViewById(R.id.save);



        TextView intro = (TextView) findViewById(R.id.intro);


        bio.setFilters(new InputFilter[] {new InputFilter.LengthFilter(120)});
        name.setFilters(new InputFilter[] {new InputFilter.LengthFilter(30)});
        goal.setFilters(new InputFilter[] {new InputFilter.LengthFilter(30)});
        skillLevel.setFilters(new InputFilter[] {new InputFilter.LengthFilter(16)});
     //   TextView interest = (TextView) findViewById(R.id.interest);

        ImageButton interest1b = (ImageButton) findViewById(R.id.interest1_b);
        ImageButton interest2b = (ImageButton) findViewById(R.id.interest2_b);
        ImageButton interest3b = (ImageButton) findViewById(R.id.interest3_b);

        TextView interest1t = (TextView) findViewById(R.id.interest1_t);
        TextView interest2t = (TextView) findViewById(R.id.interest2_t);
        TextView interest3t = (TextView) findViewById(R.id.interest3_t);

        String bioDesc = "I do not have a gym membership, I much prefer trails and hiking or playing rounds of disc golf, you know the lighter activities that doesn't always feel like working out, but more of being active. I am not opposed to finding a gym partner if that's your thing, but I have reached a plateau after losing 65 lbs in the last half year and I think having someone to be active with can help me turn the corner. Any ages or fitness levels are welcome as well as any gender. I work in Raleigh area and live in Sanford to give a better idea on a more local area.\n" +
                "\n";




        bio.setText(bioDesc.length() > 100 ? bioDesc.substring(0, 100) : bioDesc);
        goal.setText("Develop gym routine");
        skillLevel.setText("Beginner");
      //  interest.setText("Weightlifting" + " " + "Yoga");

        setIcon(interestList[0], interest1b, interest1t);
        setIcon(interestList[1], interest2b, interest2t);
        setIcon(interestList[2], interest3b, interest3t);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInfo(bio,name,skillLevel,goal,MorF);
            }
        });






        CardView card = findViewById(R.id.cardView);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void setIcon(String activity, ImageButton interestb, TextView interestt){
        switch(activity){
            case "sports":
                interestb.setImageResource(R.drawable.sport);
                interestt.setText("sports");
                break;
            case "cycling":
                interestb.setImageResource(R.drawable.bicycle);
                interestt.setText("bicycle");
                break;
            case "walking":
                interestb.setImageResource(R.drawable.footprint);
                interestt.setText("footprint");
                break;
            case "running":
                interestb.setImageResource(R.drawable.running);
                interestt.setText("running");
                break;
            case "dancing":
                interestb.setImageResource(R.drawable.mirror_ball);
                interestt.setText("sports");
                break;
            case "lifting":
                interestb.setImageResource(R.drawable.weight_lifting);
                interestt.setText("dancing");
                break;
            case "boxing":
                interestb.setImageResource(R.drawable.kickboxing);
                interestt.setText("boxing");
                break;
            case "yoga":
                interestb.setImageResource(R.drawable.yoga_mat);
                interestt.setText("yoga");
                break;
        }

    }
    private void saveUserInfo(EditText b, EditText n, EditText s, EditText g, ToggleButton M){
        bioS = b.getText().toString();
        nameS = n.getText().toString();
        skillLevelS = s.getText().toString();
        goalS = g.getText().toString();
        if(M.isChecked()){
            MF = "Female";
        }
        else{
            MF = "Male";
        }
        Map userInfo = new HashMap();
        userInfo.put("bio",bioS);
        userInfo.put("name",nameS);
        userInfo.put("skillLevel",skillLevelS);
        userInfo.put("goal",goalS);
        userInfo.put("sex",MF);
        myDatabase.updateChildren(userInfo);
        AlertDialog alertDialog = new AlertDialog.Builder(EditUserActivity.this).create();
        alertDialog.setTitle("Success!");
        alertDialog.setMessage("Your Profile was successfully updated");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}