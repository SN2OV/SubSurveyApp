package com.example.subsurvey.transferSurvey.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.subsurvey.R;
import com.example.subsurvey.transferSurvey.beans.TransferSurveyEntity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TransferSurveyTotalCountAdapter extends BaseAdapter{

	private List<TransferSurveyEntity> tsSurveyEntity = new ArrayList<TransferSurveyEntity>();
	private Context context = null; 
	
	class ViewHolder{
		@InjectView(R.id.tsTotal_IDTV)
		TextView tsTotal_IDTV;
		@InjectView(R.id.tsTotal_NameTV)
		TextView tsTotal_NameTV;
		@InjectView(R.id.tsTotal_DateTV)
		TextView tsTotal_DateTV;
		@InjectView(R.id.tsTotal_TimePeriodTV)
		TextView tsTotal_TimePeriodTV;
		@InjectView(R.id.tsTotal_StationTV)
		TextView tsTotal_StationTV;
		//改过
		@InjectView(R.id.tsTotal_LineTV)
		TextView tsTotal_DireTV;
		@InjectView(R.id.tsTotal_TimeTV)
		TextView tsTotal_TimeTV;
		@InjectView(R.id.tsTotal_CountTotalTV)
		TextView tsTotal_CountTotalTV;
		public ViewHolder(View view){
			ButterKnife.inject(this,view);
		}
	}
	
	public TransferSurveyTotalCountAdapter(Context context,List<TransferSurveyEntity> tsSurveyEntity){
		this.context = context;
		this.tsSurveyEntity = tsSurveyEntity;
	}
	
	@Override
	public int getCount() {
		return tsSurveyEntity.size();
	}

	@Override
	public Object getItem(int pos) {
		return tsSurveyEntity.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		
		ViewHolder holder = null;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.transfer_data_total_style, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		
		holder.tsTotal_IDTV.setText(tsSurveyEntity.get(pos).getRowId());
		holder.tsTotal_NameTV.setText(tsSurveyEntity.get(pos).getName());
		holder.tsTotal_DateTV.setText(tsSurveyEntity.get(pos).getDate());
		holder.tsTotal_TimePeriodTV.setText(tsSurveyEntity.get(pos).getTimePeriod());
		holder.tsTotal_StationTV.setText(tsSurveyEntity.get(pos).getStation());
		holder.tsTotal_DireTV.setText(tsSurveyEntity.get(pos).getDire());
		holder.tsTotal_TimeTV.setText(tsSurveyEntity.get(pos).getSurveyTime());
		holder.tsTotal_CountTotalTV.setText(tsSurveyEntity.get(pos).getCountTotal());
		
		if(pos == 0){
			convertView.setBackgroundResource(R.color.head_bg);
			holder.tsTotal_IDTV.setTextColor(Color.WHITE);
			holder.tsTotal_NameTV.setTextColor(Color.WHITE);
			holder.tsTotal_DateTV.setTextColor(Color.WHITE);
			holder.tsTotal_TimePeriodTV.setTextColor(Color.WHITE);
			holder.tsTotal_StationTV.setTextColor(Color.WHITE);
			holder.tsTotal_DireTV.setTextColor(Color.WHITE);
			holder.tsTotal_TimeTV.setTextColor(Color.WHITE);
			holder.tsTotal_CountTotalTV.setTextColor(Color.WHITE);
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
