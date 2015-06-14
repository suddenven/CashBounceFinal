package com.example.chanjinghong.cashbounce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseUser;

public class registeration extends ActionBarActivity {

    EditText username;
    EditText password;
    EditText retype;
    EditText email;
    EditText address;
    Button register;

    String usernametxt;
    String passwordtxt;
    String retypetxt;
    String emailtxt;
    String addresstxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        Parse.initialize(this, "VghWoCbQhGiE0trOxpOrdoP6ORhuU8H5gW8Kjbqd", "aWM8bh8JM7gu1L5kMlqaqRQeKfMTYhxKN7fU1918");
        // Parse.enableLocalDatastore(this);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        retype = (EditText)findViewById(R.id.retype);
        email = (EditText)findViewById(R.id.email);
        address = (EditText)findViewById(R.id.address);
        register = (Button)findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser user = new ParseUser();
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();
                retypetxt = retype.getText().toString();
                emailtxt = email.getText().toString();
                addresstxt = address.getText().toString();

                user.setUsername(usernametxt);
                user.setEmail(emailtxt);
                user.put("address", addresstxt);
                user.setPassword(passwordtxt);

                user.signUpInBackground();
                user.saveInBackground();

                Intent intent = new Intent(registeration.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
