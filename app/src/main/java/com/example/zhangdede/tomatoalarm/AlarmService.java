package com.example.zhangdede.tomatoalarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zhangdede on 2016/9/25.
 */
public class AlarmService extends Service {
    private SingalJob m_sinJob = null;
    private WorkTimeBinder m_workTimeBinder = new WorkTimeBinder();
    private SingalJob m_singalJob = null;

    private Boolean WorkLoopFlag = false;

    private Notification notifi = null;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return m_workTimeBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        WorkLoopFlag = false;
        if(m_singalJob == null)
            m_singalJob = new SingalJob();
        Log.i("onstartcmmand","create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!this.StartAlarm()){
            WorkLoopFlag = false;
            stopForeground(true);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    class WorkTimeBinder extends Binder {

        public void createTomatoTime(int WorkTimeMin,int RelaxTimeMin,String AlarmText){
            if(m_singalJob == null)
                m_singalJob = new SingalJob();
            m_singalJob.addSingalTomatoTime(WorkTimeMin,RelaxTimeMin,AlarmText);
            if(!WorkLoopFlag){
                StartAlarm();
                WorkLoopFlag = true;
            }
        }

        public SingalJob getSingalJob(){
            return m_singalJob;
        }
    }
    private Boolean StartAlarm()
    {
        TomatoTime tomato;
        if(( tomato= m_singalJob.getFirstTomatoTime()) != null){
            Log.i("alarm service","is not null");
            AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
            long time = tomato.getWorkTimeMill() + SystemClock.elapsedRealtime();
            Intent work = new Intent(AlarmService.this,WorkReciver.class);
            PendingIntent pi = PendingIntent.getBroadcast(AlarmService.this,0,work,0);
            alarm.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,time,pi);

            Intent notification = new Intent(this,AlarmService.class);
            PendingIntent pI = PendingIntent.getActivity(this,0,notification,0);
            notifi = new Notification.Builder(this).setContentIntent(pI)
                    .setContentText(tomato.getAlarmText())
                    .setSmallIcon(R.drawable.ic_launcher).build();
            startForeground(101,notifi);
            return true;
        }
        else
            return false;
    }
}
