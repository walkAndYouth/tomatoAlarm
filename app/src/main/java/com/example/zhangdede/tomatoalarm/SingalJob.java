package com.example.zhangdede.tomatoalarm;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangdede on 2016/9/25.
 */
public class SingalJob{
    private ArrayList<TomatoTime> m_TomatoTimeSet= null;
    public SingalJob()
    {
        m_TomatoTimeSet = new ArrayList<TomatoTime>();
    }
    public void addMulTomatoTimes(int WorkMin,int RelaxMin,String text,int count){
        for( int i = 0; i < count;i++){
            TomatoTime temp = new TomatoTime(WorkMin,RelaxMin,text);
            if(temp != null);
               m_TomatoTimeSet.add(temp);
        }
    }
    public TomatoTime getFirstTomatoTime(){
        TomatoTime temp = null;
        if(!m_TomatoTimeSet.isEmpty()) {
            temp = m_TomatoTimeSet.get(0);
            m_TomatoTimeSet.remove(0);
        }
        return temp;
    }

    public void addSingalTomatoTime(int WorkMin,int RelaxMin,String text){
        TomatoTime temp = new TomatoTime(WorkMin,RelaxMin,text);
        m_TomatoTimeSet.add(temp);
    }

    public int getSingalJobCount()
    {
        return m_TomatoTimeSet.size();
    }
}
