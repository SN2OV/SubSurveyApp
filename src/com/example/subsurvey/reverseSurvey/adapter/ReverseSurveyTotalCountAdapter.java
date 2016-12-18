package com.example.subsurvey.reverseSurvey.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.subsurvey.R;
import com.example.subsurvey.reverseSurvey.beans.ReverseSurveyEntity;
import com.example.subsurvey.transferSurvey.beans.TransferSurveyEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by SN2OV on 2016/5/4.
 */
public class ReverseSurveyTotalCountAdapter extends BaseAdapter {

    private List<ReverseSurveyEntity> rsSurveyEntity = new ArrayList<ReverseSurveyEntity>();
    private Context context = null;

    class ViewHolder{
        @InjectView(R.id.rsTotal_IDTV)
        TextView rsTotal_IDTV;
        @InjectView(R.id.rsTotal_NameTV)
        TextView rsTotal_NameTV;
        @InjectView(R.id.rsTotal_DateTV)
        TextView rsTotal_DateTV;
        @InjectView(R.id.rsTotal_StationTV)
        TextView rsTotal_StationTV;
        //改过
        @InjectView(R.id.rsTotal_LineTV)
        TextView rsTotal_DireTV;
        @InjectView(R.id.rsTotal_TimeTV)
        TextView rsTotal_TimeTV;
        @InjectView(R.id.rsTotal_trainStartTimeTV)
        TextView rsTotal_trainStartTimeTV;
        @InjectView(R.id.rsTotal_CountTotalTV)
        TextView rsTotal_CountTotalTV;
        public ViewHolder(View view){
            ButterKnife.inject(this, view);
        }
    }

    public ReverseSurveyTotalCountAdapter(Context context,List<ReverseSurveyEntity> rsSurveyEntity){
        this.context = context;
        this.rsSurveyEntity = rsSurveyEntity;
    }

    @Override
    public int getCount() {
        return rsSurveyEntity.size();
    }

    @Override
    public Object getItem(int position) {
        return rsSurveyEntity.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.reverse_data_total_style, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.rsTotal_IDTV.setText(rsSurveyEntity.get(pos).getRowId());
        holder.rsTotal_NameTV.setText(rsSurveyEntity.get(pos).getName());
        holder.rsTotal_DateTV.setText(rsSurveyEntity.get(pos).getDate());
        holder.rsTotal_StationTV.setText(rsSurveyEntity.get(pos).getStation());
        holder.rsTotal_DireTV.setText(rsSurveyEntity.get(pos).getDire());
        holder.rsTotal_TimeTV.setText(rsSurveyEntity.get(pos).getSurveyTime());
        holder.rsTotal_CountTotalTV.setText(rsSurveyEntity.get(pos).getCountTotal());
        holder.rsTotal_trainStartTimeTV.setText(rsSurveyEntity.get(pos).getTrainStartTime());

        if(pos == 0){
            convertView.setBackgroundResource(R.color.head_bg);
            holder.rsTotal_IDTV.setTextColor(Color.WHITE);
            holder.rsTotal_NameTV.setTextColor(Color.WHITE);
            holder.rsTotal_DateTV.setTextColor(Color.WHITE);
            holder.rsTotal_StationTV.setTextColor(Color.WHITE);
            holder.rsTotal_DireTV.setTextColor(Color.WHITE);
            holder.rsTotal_TimeTV.setTextColor(Color.WHITE);
            holder.rsTotal_CountTotalTV.setTextColor(Color.WHITE);
            holder.rsTotal_trainStartTimeTV.setTextColor(Color.WHITE);
        }else{
            if(pos%2 !=0){
                convertView.setBackgroundColor(Color.argb(250 ,  255 ,  255 ,  255 ));
            }else{
                convertView.setBackgroundColor(Color.argb(250 ,  224 ,  243 ,  250 ));
            }
        }

        return convertView;
    }
}
