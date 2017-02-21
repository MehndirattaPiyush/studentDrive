package com.example.harshjha.studentdrive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Find the View that shows the  numbers category
        TextView signIn = (TextView) findViewById(R.id.signIn);

        // Set a click listener on that View
        signIn.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent signInIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(signInIntent);
            }
        });
    }

}