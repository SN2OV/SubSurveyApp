package com.example.subsurvey.reverseSurvey.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.subsurvey.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by SN2OV on 2016/5/4.
 */
public class ReverseSurveyDataPerAdapter extends BaseAdapter {

    private HashMap<String, String> perData = new HashMap<String, String>();
    private ArrayList<HashMap<String, String>> totalData = new ArrayList<HashMap<String,String>>();
    private Context context = null;

    public ReverseSurveyDataPerAdapter(
            ArrayList<HashMap<String, String>> totalData,Context context) {
        super();
        this.totalData = totalData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return totalData.size()<12?totalData.size():11;
    }

    @Override
    public Object getItem(int pos) {
        if(pos == 0)
            return totalData.get(pos);
        else
            return totalData.get(getCount()-pos-1);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    class ViewHolder{
        @InjectView(R.id.rsIDTV)
        TextView rsIDTV;
        @InjectView(R.id.rsTimeTV)
        TextView rsTimeTV;
        @InjectView(R.id.rsCountPerTV)
        TextView rsCountPerTV;
        @InjectView(R.id.rsCountTotalTV)
        TextView rsCountTotalTV;
        public ViewHolder(View view){
            ButterKnife.inject(this, view);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.reverse_data_per_style, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        if(position == 0){
            convertView.setBackgroundResource(R.color.purple);
            holder.rsIDTV.setTextColor(Color.WHITE);
            holder.rsTimeTV.setTextColor(Color.WHITE);
            holder.rsCountPerTV.setTextColor(Color.WHITE);
            holder.rsCountTotalTV.setTextColor(Color.WHITE);
            holder.rsIDTV.setText(totalData.get(position).get("ID"));
            holder.rsTimeTV.setText(totalData.get(position).get("Time"));
            holder.rsCountPerTV.setText(totalData.get(position).get("perCount"));
            holder.rsCountTotalTV.setText(totalData.get(position).get("totalCount"));
        }else{
            //数据倒序显示
            holder.rsIDTV.setText(totalData.get(totalData.size() - position).get("ID"));
            holder.rsTimeTV.setText(totalData.get(totalData.size()-position).get("Time"));
            holder.rsCountPerTV.setText(totalData.get(totalData.size()-position).get("perCount"));
            holder.rsCountTotalTV.setText(totalData.get(totalData.size()-position).get("totalCount"));
        }

        return convertView;
    }
}
