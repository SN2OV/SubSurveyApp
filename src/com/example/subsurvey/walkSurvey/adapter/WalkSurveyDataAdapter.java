package com.example.subsurvey.walkSurvey.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.subsurvey.R;
import com.example.subsurvey.walkSurvey.beans.WalkSurveyEntity;
import com.example.subsurvey.walkSurvey.ui.WalkSurveyDataTotalActivity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WalkSurveyDataAdapter extends BaseAdapter {
	
	class ViewHolder{
		TextView wsID;
		TextView wsName;
		TextView wsDate;
		TextView wsStation;
		TextView wsLine;
		TextView wsWeekday;
		TextView wsTravelTime;
		TextView wsWalkType;
		TextView wsAge;
		TextView wsSex;
		TextView wsBagagetype;
		TextView wsTelConcern;
		TextView wsWalkTool;
		TextView wsSpeed;
		TextView wsGetOffLine;
		TextView wsGetOffDire;
		TextView wsOpenDoorTime1;
		TextView wsMachineLine;
		TextView wsMachineLoc;
		TextView wsGotoPlatformTime;
		TextView wsGetOnLine;
		TextView wsGetOnDire;
		TextView wsArrivePlatformTime;
		TextView wsOpenDoorTime2;
		TextView wsGooutPlatformTime;
	}

	public Context context = null;
	private List<WalkSurveyEntity> walkSurveyEntity = new ArrayList<WalkSurveyEntity>();
	
	public WalkSurveyDataAdapter(Context context,List<WalkSurveyEntity> walkSurveyEntity) {
		this.context = context;
		this.walkSurveyEntity = walkSurveyEntity;
	}
	
	
	@Override
	public int getCount() {
		return walkSurveyEntity.size();
	}

	@Override
	public Object getItem(int pos) {
		return walkSurveyEntity.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.walk_data_total_style, null);
			holder.wsID = (TextView)convertView.findViewById(R.id.wsID);
			holder.wsName = (TextView)convertView.findViewById(R.id.wsName);
			holder.wsDate = (TextView)convertView.findViewById(R.id.wsDate);
			holder.wsStation = (TextView)convertView.findViewById(R.id.wsStation);
			holder.wsLine = (TextView)convertView.findViewById(R.id.wsLine);
			holder.wsWeekday = (TextView)convertView.findViewById(R.id.wsWeekday);
			holder.wsTravelTime = (TextView)convertView.findViewById(R.id.wsTravelTime);
			holder.wsWalkType = (TextView)convertView.findViewById(R.id.wsWalkType);
			holder.wsAge = (TextView)convertView.findViewById(R.id.wsAge);
			holder.wsSex = (TextView)convertView.findViewById(R.id.wsSex);
			holder.wsBagagetype = (TextView)convertView.findViewById(R.id.wsBagagetype);
			holder.wsTelConcern = (TextView)convertView.findViewById(R.id.wsTelConcern);
			holder.wsWalkTool = (TextView)convertView.findViewById(R.id.wsWalkTool);
			holder.wsSpeed = (TextView)convertView.findViewById(R.id.wsSpeed);
			holder.wsGetOffLine = (TextView)convertView.findViewById(R.id.wsGetOffLine);
			holder.wsGetOffDire = (TextView)convertView.findViewById(R.id.wsGetOffDire);
			holder.wsOpenDoorTime1 = (TextView)convertView.findViewById(R.id.wsOpenDoorTime1);
			holder.wsMachineLine = (TextView)convertView.findViewById(R.id.wsMachineLine);
			holder.wsMachineLoc = (TextView)convertView.findViewById(R.id.wsMachineLoc);
			holder.wsGotoPlatformTime = (TextView)convertView.findViewById(R.id.wsGotoPlatformTime);
			holder.wsGetOnLine = (TextView)convertView.findViewById(R.id.wsGetOnLine);
			holder.wsGetOnDire = (TextView)convertView.findViewById(R.id.wsGetOnDire);
			holder.wsArrivePlatformTime = (TextView)convertView.findViewById(R.id.wsArrivePlatformTime);
			holder.wsOpenDoorTime2 = (TextView)convertView.findViewById(R.id.wsOpenDoorTime2);
			holder.wsGooutPlatformTime = (TextView)convertView.findViewById(R.id.wsGooutPlatformTime);
			convertView.setTag(holder);
			
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		
		String wsID=walkSurveyEntity.get(position).getRowID()+"";
		holder.wsID.setText(wsID);
		holder.wsName.setText(walkSurveyEntity.get(position).name+"");
		holder.wsDate.setText(walkSurveyEntity.get(position).date+"");
		holder.wsStation.setText(walkSurveyEntity.get(position).station+"");
		holder.wsLine.setText(walkSurveyEntity.get(position).line+"");
		holder.wsWeekday.setText(walkSurveyEntity.get(position).weekdayIF+"");
		holder.wsTravelTime.setText(walkSurveyEntity.get(position).travelTime+"");
		holder.wsWalkType.setText(walkSurveyEntity.get(position).walkType+"");
		holder.wsAge.setText(walkSurveyEntity.get(position).age+"");
		holder.wsSex.setText(walkSurveyEntity.get(position).sex+"");
		holder.wsBagagetype.setText(walkSurveyEntity.get(position).bagageType+"");
		holder.wsTelConcern.setText(walkSurveyEntity.get(position).telConcern+"");
		holder.wsWalkTool.setText(walkSurveyEntity.get(position).walkTool+"");
		holder.wsSpeed.setText(walkSurveyEntity.get(position).speed+"");
		holder.wsGetOffLine.setText(walkSurveyEntity.get(position).getOffLine+"");
		holder.wsGetOffDire.setText(walkSurveyEntity.get(position).getOffDire+"");
		holder.wsOpenDoorTime1.setText(walkSurveyEntity.get(position).openDoorTime1+"");
		holder.wsMachineLine.setText(walkSurveyEntity.get(position).MachineLine+"");
		holder.wsMachineLoc.setText(walkSurveyEntity.get(position).MachineLoc+"");
		holder.wsGotoPlatformTime.setText(walkSurveyEntity.get(position).gotoPlatformTime+"");
		holder.wsGetOnLine.setText(walkSurveyEntity.get(position).getOnLine+"");
		holder.wsGetOnDire.setText(walkSurveyEntity.get(position).getOnDire+"");
		holder.wsArrivePlatformTime.setText(walkSurveyEntity.get(position).arrivePlatformTime+"");
		holder.wsOpenDoorTime2.setText(walkSurveyEntity.get(position).openDoorTime2+"");
		holder.wsGooutPlatformTime.setText(walkSurveyEntity.get(position).gooutPlatformTime+"");
		
		if(position == 0){
			convertView.setBackgroundResource(R.color.head_bg);
			holder.wsName.setTextColor(Color.WHITE);
			holder.wsID.setTextColor(Color.WHITE);
			holder.wsDate.setTextColor(Color.WHITE);
			holder.wsStation.setTextColor(Color.WHITE);
			holder.wsWalkType.setTextColor(Color.WHITE);
			holder.wsLine.setTextColor(Color.WHITE);
			holder.wsWeekday.setTextColor(Color.WHITE);
			holder.wsTravelTime.setTextColor(Color.WHITE);
			holder.wsAge.setTextColor(Color.WHITE);
			holder.wsSex.setTextColor(Color.WHITE);
			holder.wsBagagetype.setTextColor(Color.WHITE);
			holder.wsTelConcern.setTextColor(Color.WHITE);
			holder.wsWalkTool.setTextColor(Color.WHITE);
			holder.wsSpeed.setTextColor(Color.WHITE);
			holder.wsGetOffLine.setTextColor(Color.WHITE);
			holder.wsGetOffDire.setTextColor(Color.WHITE);
			holder.wsOpenDoorTime1.setTextColor(Color.WHITE);
			holder.wsMachineLine.setTextColor(Color.WHITE);
			holder.wsMachineLoc.setTextColor(Color.WHITE);
			holder.wsGotoPlatformTime.setTextColor(Color.WHITE);
			holder.wsGetOnLine.setTextColor(Color.WHITE);
			holder.wsGetOnDire.setTextColor(Color.WHITE);
			holder.wsArrivePlatformTime.setTextColor(Color.WHITE);
			holder.wsOpenDoorTime2.setTextColor(Color.WHITE);
			holder.wsGooutPlatformTime.setTextColor(Color.WHITE);
			
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
