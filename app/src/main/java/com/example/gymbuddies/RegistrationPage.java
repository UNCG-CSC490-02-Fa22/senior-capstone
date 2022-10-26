package com.example.gymbuddies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.gymbuddies.MainActivity;
import com.example.gymbuddies.R;
import com.example.gymbuddies.databinding.RegistrationPageBinding;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;



public class RegistrationPage extends AppCompatActivity{

        private RegistrationPageBinding binding;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            binding = RegistrationPageBinding.inflate(getLayoutInflater());
            setContentView(R.layout.registration_page);


            Button button3;
            Button button4;

            button4 = (Button) findViewById(R.id.button4);
            button3 = (Button) findViewById(R.id.button3);
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openNewActivity();
                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openNewActivity();
                }
            });

          //  BottomNavigationView navView = findViewById(R.id.nav_view);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
           // AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
             //       R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
               //     .build();
            //   NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
            //   NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            //  NavigationUI.setupWithNavController(binding.navView, navController);
        }
        public void openNewActivity(){
            Intent intent = new Intent(this, com.example.gymbuddies.MainActivity.class);
            startActivity(intent);
        }



    }







