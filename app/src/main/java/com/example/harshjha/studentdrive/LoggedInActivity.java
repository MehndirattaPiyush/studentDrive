package com.example.harshjha.studentdrive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static android.os.Build.VERSION_CODES.N;

public class LoggedInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        // Find the View that shows the Notices category
        TextView notice = (TextView) findViewById(R.id.activity_notices);

        // Set a click listener on that View
        notice.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Notices View is clicked on.
            @Override
            public void onClick(View view) {
                Intent noticeIntent = new Intent(LoggedInActivity.this, NoticesActivity.class);
                startActivity(noticeIntent);
            }
        });

        // Find the View that shows the timetable category
        TextView timetable = (TextView) findViewById(R.id.activity_time_table);

        // Set a click listener on that View
        notice.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Timetable View is clicked on.
            @Override
            public void onClick(View view) {
                Intent timeTableIntent = new Intent(LoggedInActivity.this, TimeTable.class);
                startActivity(timeTableIntent);
            }
        });


        // Find the View that shows the Attendance category
        TextView attendance = (TextView) findViewById(R.id.activity_attendance);

        // Set a click listener on that View
        notice.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Attendance View is clicked on.
            @Override
            public void onClick(View view) {
                Intent attendanceIntent = new Intent(LoggedInActivity.this, AttendanceActivity.class);
                startActivity(attendanceIntent);
            }
        });

        // Find the View that shows the Assignment category
        TextView assignment = (TextView) findViewById(R.id.activity_assingment);

        // Set a click listener on that View
        notice.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Assignment View is clicked on.
            @Override
            public void onClick(View view) {
                Intent assignmentIntent = new Intent(LoggedInActivity.this, AssingmentActivity.class);
                startActivity(assignmentIntent);
            }
        });

    }
}
