package com.example.chanjinghong.cashbounce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseUser;


public class MainActivity extends FragmentActivity {

    EditText name;
    EditText password;

    String nametxt;
    String passwordtxt;

    Button login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect to Database
        // Parse.enableLocalDatastore(this);
        Parse.initialize(this, "VghWoCbQhGiE0trOxpOrdoP6ORhuU8H5gW8Kjbqd", "aWM8bh8JM7gu1L5kMlqaqRQeKfMTYhxKN7fU1918");

        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);

        // Login Button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nametxt = name.getText().toString();
                passwordtxt = password.getText().toString();

                ParseUser.logInInBackground(nametxt, passwordtxt, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, com.parse.ParseException e) {
                        if (user != null) {
                            Intent intent = new Intent(MainActivity.this, ProfilePage.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        // Register Button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, registeration.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
