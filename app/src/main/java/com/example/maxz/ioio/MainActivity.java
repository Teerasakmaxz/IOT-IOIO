package com.example.maxz.ioio;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.Calendar;
import java.util.Locale;
import ioio.lib.api.DigitalOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOActivity;



public class MainActivity extends IOIOActivity {

    private static MainActivity instance;
    //ประกาศตัวแปร
    ToggleButton toggleButtonLED1;
    ToggleButton toggleButtonMOTOR1;
    ToggleButton toggleButtonLED2;
    ToggleButton toggleButtonMOTOR2;

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButtonLED1 = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButtonMOTOR1 = (ToggleButton) findViewById(R.id.toggleButton2);
        toggleButtonLED2 = (ToggleButton) findViewById(R.id.toggleButton3);
        toggleButtonMOTOR2 = (ToggleButton) findViewById(R.id.toggleButton4);
        Button buttonSetting = (Button) findViewById(R.id.button4);
        textView = (TextView) findViewById(R.id.textView3);
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute1 = calendar.get(Calendar.MINUTE);


        buttonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar calendar1 = Calendar.getInstance(Locale.getDefault());
                        calendar1.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar1.set(Calendar.MINUTE, minute);
                        calendar1.set(Calendar.SECOND, 0);
                        calendar1.set(Calendar.MILLISECOND, 0);
                        textView.setText(hourOfDay+":"+minute);


                        setAlarm(calendar1);
                    }
                }, hour, minute1, true);
                timePickerDialog.show();
            }
        });
    }//main mainhod


    class Looper extends BaseIOIOLooper {

        private DigitalOutput LED1, MOTOR1, LED2, MOTOR2;

        @Override
        protected void setup() throws ConnectionLostException, InterruptedException {
            //super.setup();

            LED1 = ioio_.openDigitalOutput(1,true );
            MOTOR1 = ioio_.openDigitalOutput(2,true);
            LED2 = ioio_.openDigitalOutput(3,true);
            MOTOR2 = ioio_.openDigitalOutput(4,true);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "OK Connect", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void loop() throws ConnectionLostException, InterruptedException {
            //super.loop();


            LED1.write(!toggleButtonLED1.isChecked());
            MOTOR1.write(!toggleButtonMOTOR1.isChecked());
            LED2.write(!toggleButtonLED2.isChecked());
            MOTOR2.write(!toggleButtonMOTOR2.isChecked());
            LED1.write(!toggleButtonLED1.isChecked());

        }
    }//Looper

    protected IOIOLooper createIOIOLooper() {

        return new Looper();
    }

    public void setAlarmText(String alarmText) {
       textView.setText(alarmText);
    }

    public static MainActivity instance() {
        return instance;
    }

    @Override
    protected void onStart() {
        super.onStart();
        instance = this;
    }

    public void setAlarm(Calendar alarm) {
        final int id = (int) System.currentTimeMillis();
        Intent intent = new Intent(getBaseContext(), Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), id, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarm.getTimeInMillis(), pendingIntent);

    }
}//class main
