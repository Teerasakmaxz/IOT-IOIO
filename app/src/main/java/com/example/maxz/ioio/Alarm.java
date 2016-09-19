package com.example.maxz.ioio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by maxz on 9/13/2016 AD.
 */
public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

//        Timesetting timesetting = Timesetting.instance();
//        timesetting.setAlarmText("Alarm! Wake up! Wake up!");
        MainActivity mainActivity = MainActivity.instance();
        mainActivity.kk = true;
        mainActivity.setAlarm("ปลุกแล้ว");

    }
}
