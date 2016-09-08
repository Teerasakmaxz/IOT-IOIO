package com.example.maxz.ioio;


import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

public class Timesetting extends AppCompatActivity {

    TextView myTextView, myTextView2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timesetting);


      Wiget();






    }//main method

    public void Wiget() {
        myTextView = (TextView) findViewById(R.id.textView3);
        myTextView2 = (TextView) findViewById(R.id.textView4);

    }//Wiget method

    public void BtnOpen(View view) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute1 = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(Timesetting.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                if (minute < 10) {
                    myTextView.setText(" 0" + hourOfDay + ":0" + minute);

                } else if (hourOfDay < 10) {
                    myTextView.setText(" 0" + hourOfDay + ":" + minute);
                } else {
                    myTextView.setText(" " + hourOfDay + ":" + minute);
                }


            }
        }, hour, minute1, true);
        timePickerDialog.show();

    }//BtnOpen

    public void BtnCloes(View view) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute1 = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog1 = new TimePickerDialog(Timesetting.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                if (minute < 10) {
                    myTextView2.setText(" 0" + hourOfDay + ":0" + minute);

                } else if (hourOfDay < 10) {
                    myTextView2.setText(" 0" + hourOfDay + ":" + minute);
                } else {
                    myTextView2.setText(" " + hourOfDay + ":" + minute);
                }


            }
        }, hour, minute1, true);
        timePickerDialog1.show();

    }//BtnCloes

    public void BtnSave(View view) {
        myDialog(Timesetting.this,"SAVE","บันทึกแล้วครับ");

    }//BtnSave

    public void myDialog (Context context,String strtitle,String strmessage) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle(strtitle);
        builder.setMessage(strmessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Timesetting.this, MainActivity.class);
                startActivity(intent);

                dialog.dismiss();
            }
        });
        builder.show();
    }


}//main class
