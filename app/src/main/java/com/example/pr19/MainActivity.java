package com.example.pr19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.text.format.DateUtils;
import android.view.View;

import android.widget.TextView;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
     TextView timePick;
     Button btnTime,btnDate,btn;
     Calendar dateAndTime=Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePick=findViewById(R.id.time_pick);
        btnDate=findViewById(R.id.button_date);
        btnTime=findViewById(R.id.button_time);
        btnTime.setOnClickListener(this);
        btnDate.setOnClickListener(this);
        btn = findViewById(R.id.perehod);
        btn.setOnClickListener(this);
        setInitialDateTime();
    }

    private void setInitialDateTime() {
        timePick.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE|DateUtils.FORMAT_SHOW_YEAR
                        |DateUtils.FORMAT_SHOW_TIME));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_date) {
            new DatePickerDialog(MainActivity.this, d,
                    dateAndTime.get(Calendar.YEAR),
                    dateAndTime.get(Calendar.MONTH),
                    dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
        }
        else if(view.getId() ==  R.id.button_time) {
                new TimePickerDialog(MainActivity.this, t,
                        dateAndTime.get(Calendar.HOUR_OF_DAY),
                        dateAndTime.get(Calendar.MINUTE), true).show();
            }
        else if (view.getId() == R.id.perehod) {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        }
    }
    TimePickerDialog.OnTimeSetListener t= (view, hourofDay, minute) -> {
        dateAndTime.set(Calendar.HOUR_OF_DAY, hourofDay);
        dateAndTime.set(Calendar.MINUTE, minute);
        setInitialDateTime();
    };
    DatePickerDialog.OnDateSetListener d= (view, year, monthofYear, dayofMonth) -> {
                dateAndTime.set(Calendar.YEAR, year);
                dateAndTime.set(Calendar.MONTH, monthofYear);
                dateAndTime.set(Calendar.DAY_OF_MONTH,dayofMonth);
                setInitialDateTime();
    };
}