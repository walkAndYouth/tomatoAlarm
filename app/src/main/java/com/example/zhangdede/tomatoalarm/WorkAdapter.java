package com.example.zhangdede.tomatoalarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by zhangdede on 2016/9/29.
 */
public class WorkAdapter extends BaseAdapter {
    private Context m_context;
    private LayoutInflater listLayout;
    public WorkAdapter(Context context){
        super();
        m_context = context;
        listLayout = LayoutInflater.from(m_context);
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = listLayout.inflate(R.layout.work_layout,null);
        TextView view1= (TextView)convertView.findViewById(R.id.textview1);
        view1.setText("zhangdexioa");
        return convertView;
    }
}
