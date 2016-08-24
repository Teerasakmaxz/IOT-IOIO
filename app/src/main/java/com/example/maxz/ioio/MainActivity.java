package com.example.maxz.ioio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.ToggleButton;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOActivity;

public class MainActivity extends IOIOActivity {

    //ประกาศตัวแปร
    ToggleButton toggleButtonLED1,toggleButtonMOTOR1,toggleButtonLED2,toggleButtonMOTOR2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButtonLED1 = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButtonMOTOR1 = (ToggleButton) findViewById(R.id.toggleButton2);
        toggleButtonLED2 = (ToggleButton) findViewById(R.id.toggleButton3);
        toggleButtonMOTOR2 = (ToggleButton) findViewById(R.id.toggleButton4);


    }//main mainhod

    class Looper extends BaseIOIOLooper {

        private DigitalOutput LED1,MOTOR1,LED2,MOTOR2;
        @Override
        protected void setup() throws ConnectionLostException, InterruptedException {
            //super.setup();

            LED1 =ioio_.openDigitalOutput(1,false);
            MOTOR1 = ioio_.openDigitalOutput(2, false);
            LED2 = ioio_.openDigitalOutput(3, false);
            MOTOR2 = ioio_.openDigitalOutput(4, false);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this,"OK Connect",Toast.LENGTH_SHORT).show();
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
        }
    }//Looper

    protected IOIOLooper createIOIOLooper() {

        return new Looper();
    }

}//class main
