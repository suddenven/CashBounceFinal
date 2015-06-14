package com.example.chanjinghong.cashbounce;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by chanjinghong on 6/13/15.
 */
public class DueDate extends Activity {

    ListView listView2;
    static List<String> list = new ArrayList<String>();
    static List<String> list2 = new ArrayList<String>();
    static List<String> list3 = new ArrayList<String>();
    Button button;
    TextView nametext;
    String store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duedate);

        listView2 = (ListView)findViewById(R.id.listView2);
        nametext = (TextView)findViewById(R.id.nametext);

        ParseQuery<ParseUser> user = ParseUser.getQuery();
        user.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> List2, ParseException e) {
                if (list2.isEmpty()) {
                    for (int i = 0; i < List2.size(); i++) {
                        list2.add(List2.get(i).getUsername());
                        list3.add(List2.get(i).getObjectId());
                    }
                }
            }
        });

        ParseUser currentUser = ParseUser.getCurrentUser();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("owingsystem");
        query.whereEqualTo("owe_me", currentUser.getObjectId());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> List, ParseException e) {
                if (e == null) {
                    if (list.isEmpty()) {
                        for (int i = 0; i < List.size(); i++) {
                            list.add(List.get(i).getString("ower"));
                        }
                    }
                } else {
                    Log.d("ower", "Error: " + e.getMessage());
                }
            }
        });

        // List View
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView2.setAdapter(adapter);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                view.setSelected(true);

                String Text = String.valueOf(((TextView)view).getText());
                for (int k = 0; k < list2.size(); k++) {
                    if (Objects.equals(Text, list3.get(k))) {
                        nametext.setText(list2.get(k));
                        store = list3.get(k);
                    }
                }
            }
        });

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser currentUser = ParseUser.getCurrentUser();

                if (store != null) {
                    ParseObject gameScore = new ParseObject("owingsystem");
                    gameScore.put("owe_me", currentUser.getObjectId());
                    gameScore.put("ower", store);
                    gameScore.saveInBackground();
                    Intent intent = new Intent(DueDate.this, ListForPPLWhoOweYou.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(DueDate.this, ListForPPLWhoOweYou.class);
                    startActivity(intent);
                    finish();
                }


            }
        });

    }
}
