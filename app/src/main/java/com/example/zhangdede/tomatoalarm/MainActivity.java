package com.example.zhangdede.tomatoalarm;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button startServiceAndBind = null;
    private Button createTomato = null;
    private AlarmService.WorkTimeBinder workBinder;

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
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.startVibrate:
                Intent intent = new Intent(this,AlarmService.class);
                bindService(intent,connection,BIND_AUTO_CREATE);
                startService(intent);
                break;
            case R.id.createTomato:
                workBinder.createTomatoTime(2000,100,"zhang");
                break;
        }
    }
}
