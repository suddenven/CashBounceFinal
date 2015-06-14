package com.example.chanjinghong.cashbounce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by chanjinghong on 6/13/15.
 */
public class ProfilePage extends Activity {

    TextView username;
    TextView emailview;
    TextView phoneview;
    TextView addressview;
    Button option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepage);

        Parse.initialize(this, "VghWoCbQhGiE0trOxpOrdoP6ORhuU8H5gW8Kjbqd", "aWM8bh8JM7gu1L5kMlqaqRQeKfMTYhxKN7fU1918");

        username = (TextView)findViewById(R.id.username);
        emailview = (TextView)findViewById(R.id.emailview);
        phoneview = (TextView)findViewById(R.id.phoneview);
        addressview = (TextView)findViewById(R.id.addressview);

        ParseUser currentUser = ParseUser.getCurrentUser();

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.getInBackground(currentUser.getObjectId(), new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser user, com.parse.ParseException e) {
                if (e == null) {
                    // The query was successful.
                    username.setText(user.getUsername());
                    emailview.setText(user.getEmail());
                    phoneview.setText(user.getString("phone"));
                    addressview.setText(user.getString("address"));
                } else {
                    // Something went wrong.
                }
            }
        });

        option = (Button)findViewById(R.id.option);
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilePage.this, Menucehck.class);
                startActivity(intent);
                finish();
            }
        });
    }
}