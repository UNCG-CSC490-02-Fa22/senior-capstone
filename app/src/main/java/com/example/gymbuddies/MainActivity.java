package com.example.gymbuddies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {




//    private ActivityMainBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_main);
//
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//     //   NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//     //   NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//      //  NavigationUI.setupWithNavController(binding.navView, navController);
//
//    }
    private UserCard[] userCards;
   // private ArrayList<String> al;
    private UserCardArrayAdapter arrayAdapter;
    private ListView listView;
    List<UserCard> rowItems;
    private int i;
    private FirebaseAuth mAuth;

    private String currentUid;
    private DatabaseReference usersDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usersDb = FirebaseDatabase.getInstance().getReference().child("Users");

        //mAuth = FirebaseAuth.getInstance();
        currentUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        checkUserSex();
        rowItems = new ArrayList<UserCard>();
//        al.add("php");
//        al.add("c");
//        al.add("python");
//        al.add("java");
//        al.add("html");
//        al.add("c++");
//        al.add("css");
//        al.add("javascript");
        String user1img = "https://images.unsplash.com/photo-1592194996308-7b43878e84a6?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80";
        String user2img = "https://images.unsplash.com/photo-1543852786-1cf6624b9987?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8Y2F0c3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60";
        String user3img = "https://images.unsplash.com/photo-1577023311546-cdc07a8454d9?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8Y2F0c3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60";
        String user4img = "https://images.unsplash.com/photo-1573865526739-10659fec78a5?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTd8fGNhdHN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60";
//        UserCard user1 = new UserCard("1234", "User1", user1img, "2");
//        UserCard user2 = new UserCard("1234", "User2", user2img, "10");
//        UserCard user3 = new UserCard("1234", "User3", user3img, "3");
//        UserCard user4 = new UserCard("1234", "User4", user4img, "7");
//
//        rowItems.add(user1);
//        rowItems.add(user2);
//        rowItems.add(user3);
//        rowItems.add(user4);



        arrayAdapter = new UserCardArrayAdapter(this, R.layout.item, rowItems);

//        UserCard user5 = new UserCard("12345", "User5", user4img, "13");
//        rowItems.add(user5);
//        arrayAdapter.notifyDataSetChanged();

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);


        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                rowItems.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                UserCard swipedUser = (UserCard) dataObject;
                //get just the uid
                String swipedUserUserId = swipedUser.getUserId();
                //we save this in the 'nope' of the user that was swapped on so that this current won't be shown to them
                //no reason to save it for current user because cards are not recycled
                usersDb.child(oppositeSex).child(swipedUserUserId).child("Connections").child("Pass").child(currentUid).setValue(true);
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                UserCard swipedUser = (UserCard) dataObject;
                String swipedUserUserId = swipedUser.getUserId();
                usersDb.child(oppositeSex).child(swipedUserUserId).child("Connections").child("Like").child(currentUid).setValue(true);
                isAMatch(swipedUserUserId);
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                //al.add("XML ".concat(String.valueOf(i)));
//                rowItems.add("", "newUser");
//                arrayAdapter.notifyDataSetChanged();
//                Log.d("LIST", "notified");
//                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                UserCard userMatch = (UserCard) dataObject;
                openBio(userMatch);
            }
        });

    }

    private void isAMatch(String swipedUserId) {
        DatabaseReference currentUserConnectionsDb = usersDb.child(userSex).child(currentUid).child("Connections").child("Like").child(swipedUserId);
        currentUserConnectionsDb.addListenerForSingleValueEvent(new ValueEventListener() {
            //above local data is los inside these listeners so we can no longer use swipeUserId
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Toast.makeText(MainActivity.this, "new connection", Toast.LENGTH_LONG).show();
                    usersDb.child(oppositeSex).child(snapshot.getKey())
                            .child("Connections").child("Matches").child(currentUid).setValue(true);
                    usersDb.child(userSex).child(currentUid)
                            .child("Connections").child("Matches").child(snapshot.getKey()).setValue(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void openBio(UserCard userMatch){
        Intent intent = new Intent(this, MatchBioActivity.class);
        intent.putExtra("userMatchName", userMatch.getName());
        startActivity(intent);
    }
    
      public void viewProfile(View view) {
            Intent intent = new Intent(MainActivity.this, EditUserActivity.class);
            startActivity(intent);
        }

    private String userSex;
    private String oppositeSex;
    //get sex of user and set opposite sex value based on that
    public void checkUserSex() {

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference maleDb = FirebaseDatabase.getInstance().getReference().child("Users").child("Male");
        maleDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //if new child/user is added here in the male db, then we know the sex
                if (snapshot.getKey().equals(user.getUid())) {
                    userSex = "Male";
                    oppositeSex = "Female";
                    getOppositeSexUsers();
                }
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
        DatabaseReference femaleDb = FirebaseDatabase.getInstance().getReference().child("Users").child("Female");
        femaleDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //if new child/user is added here in the male db, then we who the sex
                if (snapshot.getKey().equals(user.getUid())) {
                    userSex = "Female";
                    oppositeSex = "Male";
                    Log.d("LIST", userSex);
                    getOppositeSexUsers();
                }
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
    }
////        DatabaseReference userDb = usersDb.child(user.getUid());
////        userDb.addListenerForSingleValueEvent(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                if (dataSnapshot.exists()){
////                    if (dataSnapshot.child("sex").getValue() != null){
////                        userSex = dataSnapshot.child("sex").getValue().toString();
////                        switch (userSex){
////                            case "Male":
////                                oppositeSex = "Female";
////                                break;
////                            case "Female":
////                                oppositeSex = "Male";
////                                break;
////                        }
////                        getOppositeSexUsers();
////                    }
////                }
////            }
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////
////            }
////        });
//    }
//    //get all users in opp. sex db
    public void getOppositeSexUsers(){
        DatabaseReference oppositeSexDb = FirebaseDatabase.getInstance().getReference().child("Users").child(oppositeSex);
        oppositeSexDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //if new child/user is added here in the male db, then we who the sex
                //and user hasn't swipes at all on this already
                if(snapshot.exists() && !snapshot.child("Connections").child("Pass").hasChild(currentUid)&& !snapshot.child("Connections").child("Like").hasChild(currentUid)){
                    String user1img = "https://images.unsplash.com/photo-1592194996308-7b43878e84a6?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80";

                    rowItems.add(new UserCard(snapshot.getKey(), snapshot.child("name").getValue().toString(), user1img, "10"));
                    arrayAdapter.notifyDataSetChanged();
                }
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
    }
    public void getSameSexUsers(){
        DatabaseReference sameSexDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userSex);
        sameSexDb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //if new child/user is added here in the male db, then we who the sex
                if(snapshot.exists()){
                    String user1img = "https://images.unsplash.com/photo-1592194996308-7b43878e84a6?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80";

                    rowItems.add(new UserCard(snapshot.getKey(), snapshot.child("name").getValue().toString(), user1img, "10"));
                    arrayAdapter.notifyDataSetChanged();
                }
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
    }
//    //everytime the opposite sex db is updated, modify card stack/array list
//    public void getOppositeSexUsers(){
//        DatabaseReference oppositeSexDb = FirebaseDatabase.getInstance().getReference.child("Users").child(oppositeSex);
//        oppositeSexDb.addChildEventListener(new ChildEventListener(){
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                if(dataSnapshot.exists()){
//                    al.add(dataSnapshot.getKey().child("name").getValue().toString());
//                    arrayAdapter.notifyDataSetChanged();
//                }
//
//            }
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//            }
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//        usersDb.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                if()
//
//            }
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//            }
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//    }


    public void logoutUser(View view){
//        mAuth.signOut();
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginOrRegisterActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}
