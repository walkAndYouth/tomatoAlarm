package com.example.zhangdede.tomatoalarm;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button startServiceAndBind = null;
    private Button createTomato = null;
    private AlarmService.WorkTimeBinder workBinder;
    private ListView list = null;
    private WorkAdapter workAdapter= null;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            workBinder = (AlarmService.WorkTimeBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startServiceAndBind = (Button)findViewById(R.id.startVibrate);
        startServiceAndBind.setOnClickListener(this);
        createTomato  = (Button)findViewById(R.id.createTomato);
        createTomato.setOnClickListener(this);

        list = (ListView)findViewById(R.id.WorkJob);
        workAdapter = new WorkAdapter(this);

        list.setAdapter(workAdapter);
        Intent intent = new Intent(this,AlarmService.class);
        startService(intent);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.startVibrate:
                workAdapter.notifyDataSetChanged();
                break;
            case R.id.createTomato:
                workBinder.createTomatoTime(2000,100,"zhang");
                break;
        }
    }

    private Boolean serviceIsRunning(){
        ActivityManager serviceManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service:serviceManager.getRunningServices(Integer.MAX_VALUE)){
            if(AlarmService.class.getName().equals(service.service.getClassName()))return true;
        }
        return false;
    }
}
