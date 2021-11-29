package com.example.remindapp_phamvanthien;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DetailEventActivity extends Activity{
    Button btnSetdate;
    Button btnSavedate;
    TextView editDate;
    EditText editTitle, editDes;
    int yearCurrent, monthCurrent, dayCurrent;
    int gday;
    int gyear;
    int gmonth;
    int countdate;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_event_activity);
        mapping();
        eventforbtnSetdate();
        btnSavedate();
    }

    private void mapping() {
        btnSetdate =(Button) findViewById(R.id.btnSetdate);
        editDate = (TextView) findViewById(R.id.editDate);
        btnSavedate = (Button) findViewById(R.id.btnSavedate);
        editTitle = (EditText)  findViewById(R.id.editTitle);
        editDes = (EditText) findViewById(R.id.editDes);
    }

    private  void btnSavedate(){
        btnSavedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countdate = (gyear - yearCurrent)*365 + (gmonth - monthCurrent)*30 +(gday-dayCurrent);
                event event = new event(editTitle.getText().toString(),editDes.getText().toString(),String.valueOf(countdate + " days remaining"));
                Intent intent1 = new Intent();
                intent1.putExtra("event",event);
                setResult(RESULT_OK,intent1);
                finish();
            }
        });
    }
    private void eventforbtnSetdate(){
        btnSetdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(editDate);
            }
        });
    }
    private void showDateTimeDialog(TextView editDate) {
        Calendar calendar = Calendar.getInstance();
         yearCurrent = calendar.get(Calendar.YEAR);
         monthCurrent = calendar.get(Calendar.MONTH);
         dayCurrent = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
                        String dateInEvent = simpleDateFormat.format(calendar.getTime());
                        String currentDate = simpleDateFormat.format(new Date());

                        gyear = calendar.get(Calendar.YEAR);
                        gmonth = calendar.get(Calendar.MONTH);
                         gday = calendar.get(Calendar.DAY_OF_MONTH);

                        editDate.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(DetailEventActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
            }
        };

        new DatePickerDialog(DetailEventActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    }
