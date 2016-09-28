package com.example.zhangdede.tomatoalarm;

/**
 * Created by zhangdede on 2016/9/25.
 */
public class TomatoTime {
    private long m_WorkTimeMill = 0;
    private long m_RelaxTimeMill = 0;
    private String m_AlarmText = null;

    public TomatoTime(int WorkTimeMin,int RelaxTimeMin,String AlarmText){
  //      m_WorkTimeMill = WorkTimeMin * 60 * 1000;
  //      m_RelaxTimeMill = RelaxTimeMin * 60 * 1000;
        m_WorkTimeMill = WorkTimeMin ;
        m_RelaxTimeMill = RelaxTimeMin ;
        m_AlarmText = AlarmText;
    }
    public TomatoTime(){};

    public void setWorkTimeMin(int WorkTimeMin){
      //  m_WorkTimeMill = WorkTimeMin * 60 * 1000;
        m_WorkTimeMill = WorkTimeMin;
    }

    public void setRelaxTimeMin(int RelaxTimeMin){
      //  m_RelaxTimeMill = RelaxTimeMin * 60 * 1000;
        m_RelaxTimeMill = RelaxTimeMin;
    }

    public void setAlarmText(String text){
        m_AlarmText = text;
    }

    public long getWorkTimeMill(){
        return m_WorkTimeMill;
    }
    public long getRelaxTimeMill(){
        return m_RelaxTimeMill;
    }
    public String getAlarmText()
    {
        return m_AlarmText;
    }
}
