package com.example.gymbuddies;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public class EditUserActivity extends AppCompatActivity {
    private FirebaseAuth mauth;
    private DatabaseReference myDatabase;
    private String userID,nameS,bioS,goalS,skillLevelS, MF;
    private Uri resultURI;
    private UserCardArrayAdapter arrayAdapter;
    private ListView listView;
    List<UserCard> rowItems;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user_bio);
        rowItems = new ArrayList<UserCard>();








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
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditUserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });




                name.setText(getName(MorF));
    
       // name.setText("myName");
        CardView card = findViewById(R.id.cardView);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
   
    //public void getName(){
      //  DatabaseReference sameSexDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userSex);
      //  sameSexDb.addChildEventListener(new ChildEventListener() {
      //      @Override
       //     public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
         //       //if new child/user is added here in the male db, then we who the sex
           //     if(snapshot.exists()){
             //       String user1img = "https://images.unsplash.com/photo-1592194996308-7b43878e84a6?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80";

               //     rowItems.add(new UserCard(snapshot.getKey(), snapshot.child("name").getValue().toString(), user1img, "10"));
                //    arrayAdapter.notifyDataSetChanged();
               // }
           // }
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
            public String setName(ToggleButton MoF,EditText n){
                final String[] RR = {""};
                 if (MoF.isChecked()) {
                     MF = "Female";
                 } else {
                     MF = "Male";
                 }

                 DatabaseReference mDB = FirebaseDatabase.getInstance().getReference().child("Users").child(MF).child(userID);
                //    RR[0] ="WTF" ;
                 mDB.addListenerForSingleValueEvent(new ValueEventListener()

                 {
                     @Override
                        
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    RR[0] = "Moose9999";
                               if(dataSnapshot.exists()){


                                       
                             
                               }
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {

                     }
                 });

                return RR[0];
            }
             public String getName(ToggleButton MoF){
                    if (MoF.isChecked()) {
                        MF = "Female";
                    } else {
                        MF = "Male";
                    }
                 final String[] RSLT = new String[1];
                 DatabaseReference sameSexDb = FirebaseDatabase.getInstance().getReference().child("Users").child("Male").child(userID);
                 sameSexDb.addChildEventListener(new ChildEventListener() {
                     @Override
                     public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                         //if new child/user is added here in the male db, then we who the sex
                         Log.d("LIST", String.valueOf(snapshot.exists()));
                         if(snapshot.exists()) {


                             String user1img = "https://images.unsplash.com/photo-1592194996308-7b43878e84a6?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80";
                              rowItems.add(new UserCard(snapshot.getKey(), snapshot.child("name").getValue().toString(), user1img, "10"));
                              arrayAdapter.notifyDataSetChanged();

                       //      RSLT[0] = snapshot.child("name").getValue().toString();
                                 //    arrayAdapter.notifyDataSetChanged();

                         }
                           //    rowItems.add(new UserCard(snapshot.getKey(), snapshot.child("name").getValue().toString(), user1img, "10"));
                       //      arrayAdapter.notifyDataSetChanged();
                         }


                     @Override
                     public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
           
                }
           
                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
           
                }
           
                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
           
                }
           
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
           
                }
                 });
                // return rowItems.get(0).getName().toString();
                //  ;
                 return RSLT[0];
                }























    private ArrayList<String> MatchResults = new ArrayList<String>()   ;
    //private List<String> getDataSetMatches() {return MatchResults} ;
    private void saveUserInfo(EditText b, EditText n, EditText s, EditText g, ToggleButton M) {
                bioS = b.getText().toString();
                nameS = n.getText().toString();
                skillLevelS = s.getText().toString();
                goalS = g.getText().toString();
                if (M.isChecked()) {
                    MF = "Female";
                } else {
                    MF = "Male";
                }
                Map userInfo = new HashMap();
                userInfo.put("bio", bioS);
                userInfo.put("name", nameS);
                userInfo.put("skillLevel", skillLevelS);
                userInfo.put("goal", goalS);
                DatabaseReference currentUserDb = FirebaseDatabase.getInstance()
                        .getReference().child("Users").child(MF).child(userID);
                currentUserDb.updateChildren(userInfo);


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