package com.example.gymbuddies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymbuddies.databinding.RegistrationPageBinding;


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
            Intent intent = new Intent(this, LoginOrRegisterActivity.class);
            startActivity(intent);
        }



    }







