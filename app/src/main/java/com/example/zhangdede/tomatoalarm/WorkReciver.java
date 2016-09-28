package com.example.zhangdede.tomatoalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by zhangdede on 2016/9/28.
 */
public class WorkReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("reciver","on REcive is exted!");
        Intent in = new Intent(context,AlarmService.class);
        context.startService(in);
    }
}
