package com.example.subsurvey.staySurvey.adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

import com.example.subsurvey.R;
import com.example.subsurvey.staySurvey.beans.StaySurveyEntity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StaySurveyDataAdapter extends BaseAdapter {

	class ViewHolder{
		TextView ssID;
		TextView ssName;
		TextView ssDate;
		TextView ssStation;
		TextView ssCarriageLoc;
		TextView ssPlatformLoc;
		TextView ssWeekday;
		TextView ssTravelTime;
		TextView ssWalkType;
		TextView ssSex;
		TextView ssAge;
		TextView ssBagageType;
		TextView ssGoinLoc;
		TextView ssGetoffLoc;
		TextView ssGotoPlatformTime;
		TextView ssGooutPlatformTime;
		TextView ssArrivePlatformTime;
		TextView ssOrignLineNumTV;
		TextView ssGetOnTimeNO1;//这没有
		TextView ssGetOnNumNO1;
		TextView ssGetOffNumNO1;
		TextView ssGetOnTimeNO2;
		TextView ssGetOnNumNO2;
		TextView ssGetOffNumNO2;
		TextView ssGetOnTimeNO3;
		TextView ssGetOnNumNO3;
		TextView ssGetOffNumNO3;
		TextView ssGetOnTimeNO4;
		TextView ssGetOnNumNO4;
		TextView ssGetOffNumNO4;
		TextView ssGetOnTimeNO5;
		TextView ssGetOnNumNO5;
		TextView ssGetOffNumNO5;
		TextView ssGetOnTimeNO6;
		TextView ssGetOnNumNO6;
		TextView ssGetOffNumNO6;
	} 
	
	public Context context = null;
	private List<StaySurveyEntity> staySurveyEntity = new ArrayList<StaySurveyEntity>();
	
	public StaySurveyDataAdapter(Context context,List<StaySurveyEntity> staySurveyEntity){
		this.context = context;
		this.staySurveyEntity = staySurveyEntity;
	}
	
	@Override
	public int getCount() {
		return staySurveyEntity.size();
	}

	@Override
	public Object getItem(int pos) {
		return staySurveyEntity.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.stay_data_total_style, null);
			holder.ssID = (TextView)convertView.findViewById(R.id.ssID);
			holder.ssName = (TextView)convertView.findViewById(R.id.ssName);
			holder.ssDate = (TextView)convertView.findViewById(R.id.ssDate);
			holder.ssStation = (TextView)convertView.findViewById(R.id.ssStation);
			holder.ssCarriageLoc = (TextView)convertView.findViewById(R.id.ssCarriageLoc);
			holder.ssPlatformLoc = (TextView)convertView.findViewById(R.id.ssPlatformLoc);
			holder.ssWeekday = (TextView)convertView.findViewById(R.id.ssWeekday);
			holder.ssTravelTime = (TextView)convertView.findViewById(R.id.ssTravelTime);
			holder.ssWalkType = (TextView)convertView.findViewById(R.id.ssWalkType);
			holder.ssSex = (TextView)convertView.findViewById(R.id.ssSex);
			holder.ssAge = (TextView)convertView.findViewById(R.id.ssAge);
			holder.ssBagageType = (TextView)convertView.findViewById(R.id.ssBagageType);
			holder.ssGoinLoc = (TextView)convertView.findViewById(R.id.ssGoinLoc);
			holder.ssGetoffLoc = (TextView)convertView.findViewById(R.id.ssGetoffLoc);
			holder.ssGotoPlatformTime = (TextView)convertView.findViewById(R.id.ssGotoPlatformTime);
			holder.ssGooutPlatformTime = (TextView)convertView.findViewById(R.id.ssGooutPlatformTime);
			holder.ssArrivePlatformTime = (TextView)convertView.findViewById(R.id.ssArrivePlatformTime);
			holder.ssOrignLineNumTV = (TextView)convertView.findViewById(R.id.ssOrignLineNumTV);
			holder.ssGetOnTimeNO1 = (TextView)convertView.findViewById(R.id.ssGetOnTimeNO1);
			holder.ssGetOnNumNO1 = (TextView)convertView.findViewById(R.id.ssGetOnNumNO1);
			holder.ssGetOffNumNO1 = (TextView)convertView.findViewById(R.id.ssGetOffNumNO1);
			holder.ssGetOnTimeNO2 = (TextView)convertView.findViewById(R.id.ssGetOnTimeNO2);
			holder.ssGetOnNumNO2 = (TextView)convertView.findViewById(R.id.ssGetOnNumNO2);
			holder.ssGetOffNumNO2 = (TextView)convertView.findViewById(R.id.ssGetOffNumNO2);
			holder.ssGetOnTimeNO3 = (TextView)convertView.findViewById(R.id.ssGetOnTimeNO3);
			holder.ssGetOnNumNO3 = (TextView)convertView.findViewById(R.id.ssGetOnNumNO3);
			holder.ssGetOffNumNO3 = (TextView)convertView.findViewById(R.id.ssGetOffNumNO3);
			holder.ssGetOnTimeNO4 = (TextView)convertView.findViewById(R.id.ssGetOnTimeNO4);
			holder.ssGetOnNumNO4 = (TextView)convertView.findViewById(R.id.ssGetOnNumNO4);
			holder.ssGetOffNumNO4 = (TextView)convertView.findViewById(R.id.ssGetOffNumNO4);
			holder.ssGetOnTimeNO5 = (TextView)convertView.findViewById(R.id.ssGetOnTimeNO5);
			holder.ssGetOnNumNO5 = (TextView)convertView.findViewById(R.id.ssGetOnNumNO5);
			holder.ssGetOffNumNO5 = (TextView)convertView.findViewById(R.id.ssGetOffNumNO5);
			holder.ssGetOnTimeNO6 = (TextView)convertView.findViewById(R.id.ssGetOnTimeNO6);
			holder.ssGetOnNumNO6 = (TextView)convertView.findViewById(R.id.ssGetOnNumNO6);
			holder.ssGetOffNumNO6 = (TextView)convertView.findViewById(R.id.ssGetOffNumNO6);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		
		String ssID = staySurveyEntity.get(position).rowID;
		holder.ssID.setText(ssID);
		holder.ssName.setText(staySurveyEntity.get(position).name);
		holder.ssDate.setText(staySurveyEntity.get(position).date);
		holder.ssStation.setText(staySurveyEntity.get(position).station);
		holder.ssCarriageLoc.setText(staySurveyEntity.get(position).carriageLoc);
		holder.ssPlatformLoc.setText(staySurveyEntity.get(position).surveyLoc);
		holder.ssWeekday.setText(staySurveyEntity.get(position).weekdayIf);
		holder.ssTravelTime.setText(staySurveyEntity.get(position).travelTime);
		holder.ssWalkType.setText(staySurveyEntity.get(position).walkType);
		holder.ssSex.setText(staySurveyEntity.get(position).sex);
		holder.ssAge.setText(staySurveyEntity.get(position).age);
		holder.ssBagageType.setText(staySurveyEntity.get(position).bagageType);
		holder.ssGoinLoc.setText(staySurveyEntity.get(position).goinLoc);
		holder.ssGetoffLoc.setText(staySurveyEntity.get(position).getoffLoc);
		holder.ssGotoPlatformTime.setText(staySurveyEntity.get(position).gointoStationTime);
		holder.ssGooutPlatformTime.setText(staySurveyEntity.get(position).getoffTime);
		holder.ssArrivePlatformTime.setText(staySurveyEntity.get(position).arriveStationTime);
		holder.ssOrignLineNumTV.setText(staySurveyEntity.get(position).startLineNum);
		holder.ssGetOnTimeNO1.setText(staySurveyEntity.get(position).getTrainStartTime1());
		holder.ssGetOnNumNO1.setText(staySurveyEntity.get(position).getOnNum1);
		holder.ssGetOffNumNO1.setText(staySurveyEntity.get(position).getOffNum1);
		holder.ssGetOnTimeNO2.setText(staySurveyEntity.get(position).getTrainStartTime2());
		holder.ssGetOnNumNO2.setText(staySurveyEntity.get(position).getOnNum2);
		holder.ssGetOffNumNO2.setText(staySurveyEntity.get(position).getOffNum2);
		holder.ssGetOnTimeNO3.setText(staySurveyEntity.get(position).getTrainStartTime3());
		holder.ssGetOnNumNO3.setText(staySurveyEntity.get(position).getOnNum3);
		holder.ssGetOffNumNO3.setText(staySurveyEntity.get(position).getOffNum3);
		holder.ssGetOnTimeNO4.setText(staySurveyEntity.get(position).getTrainStartTime4());
		holder.ssGetOnNumNO4.setText(staySurveyEntity.get(position).getOnNum4);
		holder.ssGetOffNumNO4.setText(staySurveyEntity.get(position).getOffNum4);
		holder.ssGetOnTimeNO5.setText(staySurveyEntity.get(position).getTrainStartTime5());
		holder.ssGetOnNumNO5.setText(staySurveyEntity.get(position).getOnNum5);
		holder.ssGetOffNumNO5.setText(staySurveyEntity.get(position).getOffNum5);
		holder.ssGetOnTimeNO6.setText(staySurveyEntity.get(position).getTrainStartTime6());
		holder.ssGetOnNumNO6.setText(staySurveyEntity.get(position).getOnNum6);
		holder.ssGetOffNumNO6.setText(staySurveyEntity.get(position).getOffNum6);
		
		if(position == 0){
			convertView.setBackgroundResource(R.color.head_bg);
			holder.ssID.setTextColor(Color.WHITE);
			holder.ssName.setTextColor(Color.WHITE);
			holder.ssDate.setTextColor(Color.WHITE);
			holder.ssStation.setTextColor(Color.WHITE);
			holder.ssCarriageLoc.setTextColor(Color.WHITE);
			holder.ssPlatformLoc.setTextColor(Color.WHITE);
			holder.ssWeekday.setTextColor(Color.WHITE);
			holder.ssTravelTime.setTextColor(Color.WHITE);
			holder.ssWalkType.setTextColor(Color.WHITE);
			holder.ssSex.setTextColor(Color.WHITE);
			holder.ssAge.setTextColor(Color.WHITE);
			holder.ssBagageType.setTextColor(Color.WHITE);
			holder.ssGoinLoc.setTextColor(Color.WHITE);
			holder.ssGetoffLoc.setTextColor(Color.WHITE);
			holder.ssGotoPlatformTime.setTextColor(Color.WHITE);
			holder.ssGooutPlatformTime.setTextColor(Color.WHITE);
			holder.ssArrivePlatformTime.setTextColor(Color.WHITE);
			holder.ssOrignLineNumTV.setTextColor(Color.WHITE);
			holder.ssGetOnTimeNO1.setTextColor(Color.WHITE);
			holder.ssGetOnNumNO1.setTextColor(Color.WHITE);
			holder.ssGetOffNumNO1.setTextColor(Color.WHITE);
			holder.ssGetOnTimeNO2.setTextColor(Color.WHITE);
			holder.ssGetOnNumNO2.setTextColor(Color.WHITE);
			holder.ssGetOffNumNO2.setTextColor(Color.WHITE);
			holder.ssGetOnTimeNO3.setTextColor(Color.WHITE);
			holder.ssGetOnNumNO3.setTextColor(Color.WHITE);
			holder.ssGetOffNumNO3.setTextColor(Color.WHITE);
			holder.ssGetOnTimeNO4.setTextColor(Color.WHITE);
			holder.ssGetOnNumNO4.setTextColor(Color.WHITE);
			holder.ssGetOffNumNO4.setTextColor(Color.WHITE);
			holder.ssGetOnTimeNO5.setTextColor(Color.WHITE);
			holder.ssGetOnNumNO5.setTextColor(Color.WHITE);
			holder.ssGetOffNumNO5.setTextColor(Color.WHITE);
			holder.ssGetOnTimeNO6.setTextColor(Color.WHITE);
			holder.ssGetOnNumNO6.setTextColor(Color.WHITE);
			holder.ssGetOffNumNO6.setTextColor(Color.WHITE);
		}else{
			if(position%2 != 0){
				convertView.setBackgroundColor(Color.argb(250 ,  255 ,  255 ,  255 )); 
			}else{
				convertView.setBackgroundColor(Color.argb(250 ,  224 ,  243 ,  250 ));    
			}
		}
		return convertView;
	}

}
