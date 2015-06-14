package com.example.chanjinghong.cashbounce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class Company extends ActionBarActivity {

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        backButton = (Button) findViewById(R.id.backBtnC);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
    }

    private void goHome() {
        Intent intent = new Intent(this, Menucehck.class);
        startActivity(intent);
    }


}
