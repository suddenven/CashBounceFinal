package com.example.chanjinghong.cashbounce;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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


public class Flavours extends ActionBarActivity {

    Button button;
    ListView listView4;
    TextView textView3;
    String store;
    static List<String> list = new ArrayList<String>();
    static List<String> list2 = new ArrayList<String>();
    static List<String> list3 = new ArrayList<String>();
    static List<String> list4 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flavours);

        listView4 = (ListView)findViewById(R.id.listView4);
        textView3 = (TextView)findViewById(R.id.textView3);

        // Objects retrieve from User class
        ParseQuery<ParseUser> user = ParseUser.getQuery();
        user.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> List2, ParseException e) {
                list2.clear();
                list3.clear();
                for (int i = 0; i < List2.size(); i++) {
                    list2.add(List2.get(i).getUsername());
                    list3.add(List2.get(i).getObjectId());
                }
            }
        });

        ParseUser currentUser = ParseUser.getCurrentUser();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("owingsystem");
        query.whereEqualTo("ower", currentUser.getObjectId());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> List, ParseException e) {
                if (e == null) {
                    list.clear();
                    list4.clear();
                    for (int i = 0; i < List.size(); i++) {
                        list.add(List.get(i).getString("owe_me"));
                        list4.add(List.get(i).getObjectId());
                    }
                } else {
                    Log.d("owe_me", "Error: " + e.getMessage());
                }
            }
        });

        // List View
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView4.setAdapter(adapter);
        listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                view.setSelected(true);

                String Text = String.valueOf(((TextView) view).getText());
                for (int k = 0; k < list2.size(); k++) {
                    if (Objects.equals(Text, list3.get(k))) {
                        textView3.setText(list2.get(k));
                        store = list4.get(k);
                    }
                }
            }
        });

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (store != null) {
                    ParseObject.createWithoutData("owingsystem", store).deleteEventually();
                    Intent intent = new Intent(Flavours.this, whatUowePPl.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(Flavours.this, whatUowePPl.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flavours, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
