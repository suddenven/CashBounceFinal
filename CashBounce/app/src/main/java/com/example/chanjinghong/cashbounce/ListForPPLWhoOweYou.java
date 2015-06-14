package com.example.chanjinghong.cashbounce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class ListForPPLWhoOweYou extends ActionBarActivity {

    ListView listView;
    Button add;
    Button refresh;
    Button back;
    static List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_for_pplwho_owe_you);

        Parse.initialize(this, "VghWoCbQhGiE0trOxpOrdoP6ORhuU8H5gW8Kjbqd", "aWM8bh8JM7gu1L5kMlqaqRQeKfMTYhxKN7fU1918");

        listView = (ListView)findViewById(R.id.listView);

        ParseUser currentUser = ParseUser.getCurrentUser();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("owingsystem");
        query.whereEqualTo("owe_me", currentUser.getObjectId());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> List, ParseException e) {
                if (e == null) {
                    list.clear();
                    for (int i = 0; i < List.size(); i++) {
                        list.add(List.get(i).getString("ower"));
                    }
                    Toast.makeText(ListForPPLWhoOweYou.this, "You Have " + list.size() + " Ower", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("ower", "Error: " + e.getMessage());
                }
            }
        });

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListForPPLWhoOweYou.this, DueDate.class);
                startActivity(intent);
                finish();
            }
        });

        refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListForPPLWhoOweYou.this, ListForPPLWhoOweYou.class);
                startActivity(intent);
                finish();
            }
        });

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListForPPLWhoOweYou.this, Menucehck.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
