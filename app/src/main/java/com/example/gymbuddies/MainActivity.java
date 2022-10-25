package com.example.gymbuddies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.gymbuddies.RegistrationPage;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.gymbuddies.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

<<<<<<< Updated upstream
    private ActivityMainBinding binding2;
=======
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

>>>>>>> Stashed changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding2 = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);


        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        //  NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //  NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //  NavigationUI.setupWithNavController(binding.navView, navController);
    }
        public void openNewActivity(){

           Intent intent = new Intent(this, com.example.gymbuddies.RegistrationPage.class);
            startActivity(intent);

        }



    }


