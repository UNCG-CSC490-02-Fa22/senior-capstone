package com.example.gymbuddies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.gymbuddies.databinding.ActivityMainBinding;

public class LoginOrRegisterActivity extends AppCompatActivity {

    private Button mLogin, mReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_or_register_activity);

        mLogin = (Button) findViewById(R.id.login);
        mReg = (Button) findViewById(R.id.reg);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginOrRegisterActivity.this, Login.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginOrRegisterActivity.this, RegistrationPage.class);
                startActivity(intent);
                finish();
                return;
            }
        });


    }
}

