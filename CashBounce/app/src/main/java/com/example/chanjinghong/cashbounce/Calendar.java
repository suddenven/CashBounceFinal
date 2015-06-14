package com.example.chanjinghong.cashbounce;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;

/**
 * Created by chanjinghong on 6/13/15.
 */
public class Calendar extends Activity {

    CalendarView calendar;
    TextView reminder;
    Button back;

    String owerName;
    Date dueDate;
    String oweAmount;

    int day;
    int month;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender);

        //Parse.enableLocalDatastore(this);
        Parse.initialize(this, "VghWoCbQhGiE0trOxpOrdoP6ORhuU8H5gW8Kjbqd", "aWM8bh8JM7gu1L5kMlqaqRQeKfMTYhxKN7fU1918");

        reminder = (TextView) findViewById(R.id.reminderText);

        calendar = (CalendarView) findViewById(R.id.calendarView);
        calendar.setSelected(true);

        getUserData();

//        day = dueDate.getDay();
//        month = dueDate.getMonth();
//        year = dueDate.getYear();

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                showReminder(view, year, month, dayOfMonth);
            }
        });

        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Calendar.this, Menucehck.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void displayMessage(String msg) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(msg);
        alert.create().show();
    }

    private void showReminder(CalendarView view, int year, int month, int dayOfMonth) {

        if (year == this.year && month == this.month && dayOfMonth == day) {
            displayMessage("You owe " + owerName + " " + oweAmount + " on this day!");
        }
    }

    private void getUserData() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("owingsystem");
        query.getInBackground("lUAr9GEBmO", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null) {
                    // Retrieve the user data, and updates the local variables
                    oweAmount = parseObject.getString("ower_item");
                    owerName = parseObject.getString("ower");
                    dueDate = parseObject.getDate("due_date");
                    displayMessage("Hello" + dueDate);
                } else {
                    displayMessage("An error occured.");
                }
            }
        });
    }

}
