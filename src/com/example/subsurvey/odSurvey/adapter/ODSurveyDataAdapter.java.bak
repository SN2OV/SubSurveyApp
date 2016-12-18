package com.example.subsurvey.odSurvey.adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.example.subsurvey.R;
import com.example.subsurvey.odSurvey.beans.ODSurveyEntity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ODSurveyDataAdapter extends BaseAdapter {
	
	class ViewHolder{
		@InjectView(R.id.odIDTV)
		TextView odIDTV;
		@InjectView(R.id.odNameTV)
		TextView odNameTV;
		@InjectView(R.id.odDateTV)
		TextView odDateTV;
		@InjectView(R.id.odCardNumTV)
		TextView odCardNumTV;
		@InjectView(R.id.odIDNoTV)
		TextView odIDNoTV;
		@InjectView(R.id.odStationTV)
		TextView odStationTV;
		@InjectView(R.id.odGetinStationLineTV)
		TextView odGetinStationLineTV;
		@InjectView(R.id.odTransferCountTV)
		TextView odTransferCountTV;
		@InjectView(R.id.odStationCountTV)
		TextView odStationCountTV;
		@InjectView(R.id.odDistanceTotalTV)
		TextView odDistanceTotalTV;
		@InjectView(R.id.odWeekdayIfTV)
		TextView odWeekdayIfTV;
		@InjectView(R.id.odPeakIfTV)
		TextView odPeakIfTV;
		@InjectView(R.id.odGetinStationTimeTV)
		TextView odGetinStationTimeTV;
		@InjectView(R.id.odArriveStationTime1TV)
		TextView odArriveStationTime1TV;
		@InjectView(R.id.odTrainDire1TV)
		TextView odTrainDire1TV;
		@InjectView(R.id.odShift1TV)
		TextView odShift1TV;
		@InjectView(R.id.odTrainStartingTime1TV)
		TextView odTrainStartingTime1TV;
		@InjectView(R.id.odGetoffStation1TV)
		TextView odGetoffStation1TV;
		@InjectView(R.id.odGetoffStationTime1TV)
		TextView odGetoffStationTime1TV;
		@InjectView(R.id.odTransferLine1TV)
		TextView odTransferLine1TV;
		@InjectView(R.id.odArriveStationTime2TV)
		TextView odArriveStationTime2TV;
		@InjectView(R.id.odTrainDire2TV)
		TextView odTrainDire2TV;
		@InjectView(R.id.odShift2TV)
		TextView odShift2TV;
		@InjectView(R.id.odTrainStartingTime2TV)
		TextView odTrainStartingTime2TV;
		@InjectView(R.id.odGetoffStation2TV)
		TextView odGetoffStation2TV;
		@InjectView(R.id.odGetoffStationTime2TV)
		TextView odGetoffStationTime2TV;
		@InjectView(R.id.odTransferLine2TV)
		TextView odTransferLine2TV;
		@InjectView(R.id.odArriveStationTime3TV)
		TextView odArriveStationTime3TV;
		@InjectView(R.id.odTrainDire3TV)
		TextView odTrainDire3TV;
		@InjectView(R.id.odShift3TV)
		TextView odShift3TV;
		@InjectView(R.id.odTrainStartingTime3TV)
		TextView odTrainStartingTime3TV;
		@InjectView(R.id.odGetoffStation3TV)
		TextView odGetoffStation3TV;
		@InjectView(R.id.odGetoffStationTime3TV)
		TextView odGetoffStationTime3TV;
		@InjectView(R.id.odGetoutStationTimeTV)
		TextView odGetoutStationTimeTV;
		public ViewHolder(View view){
			ButterKnife.inject(this,view);
		}
	}

	public Context context = null;
	private List<ODSurveyEntity> odSurveyEntity = new ArrayList<ODSurveyEntity>();
	
	public ODSurveyDataAdapter(Context context,List<ODSurveyEntity> odSurveyEntity){
		this.context = context;
		this.odSurveyEntity = odSurveyEntity;
	}
	
	@Override
	public int getCount() {
		return odSurveyEntity.size();
	}

	@Override
	public Object getItem(int pos) {
		return odSurveyEntity.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.od_data_total_style, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		holder.odIDTV.setText(odSurveyEntity.get(position).rowID);
		holder.odNameTV.setText(odSurveyEntity.get(position).name);
		holder.odDateTV.setText(odSurveyEntity.get(position).date);
		holder.odCardNumTV.setText(odSurveyEntity.get(position).cardNum);
		holder.odIDNoTV.setText(odSurveyEntity.get(position).IDNo);
		holder.odStationTV.setText(odSurveyEntity.get(position).getinStation);
		holder.odGetinStationLineTV.setText(odSurveyEntity.get(position).getinStationLine);
		holder.odTransferCountTV.setText(odSurveyEntity.get(position).transferCount);
		holder.odStationCountTV.setText(odSurveyEntity.get(position).stationCount);
		holder.odDistanceTotalTV.setText(odSurveyEntity.get(position).distanceTotal);
		holder.odWeekdayIfTV.setText(odSurveyEntity.get(position).weekdayIf);
		holder.odPeakIfTV.setText(odSurveyEntity.get(position).peakIf);
		holder.odGetinStationTimeTV.setText(odSurveyEntity.get(position).getinStationTime);
		holder.odArriveStationTime1TV.setText(odSurveyEntity.get(position).arriveStationTime1);
		holder.odTrainDire1TV.setText(odSurveyEntity.get(position).trainDire1);
		holder.odShift1TV.setText(odSurveyEntity.get(position).shift1);
		holder.odTrainStartingTime1TV.setText(odSurveyEntity.get(position).trainStartingTime1);
		holder.odGetoffStation1TV.setText(odSurveyEntity.get(position).getoffStation1);
		holder.odGetoffStationTime1TV.setText(odSurveyEntity.get(position).getoffStationTime1);
		holder.odTransferLine1TV.setText(odSurveyEntity.get(position).transferLine1);
		holder.odArriveStationTime2TV.setText(odSurveyEntity.get(position).arriveStationTime2);
		holder.odTrainDire2TV.setText(odSurveyEntity.get(position).trainDire2);
		holder.odShift2TV.setText(odSurveyEntity.get(position).shift2);
		holder.odTrainStartingTime2TV.setText(odSurveyEntity.get(position).trainStartingTime2);
		holder.odGetoffStation2TV.setText(odSurveyEntity.get(position).getoffStation2);
		holder.odGetoffStationTime2TV.setText(odSurveyEntity.get(position).getoffStationTime2);
		holder.odTransferLine2TV.setText(odSurveyEntity.get(position).transferLine2);
		holder.odArriveStationTime3TV.setText(odSurveyEntity.get(position).arriveStationTime3);
		holder.odTrainDire3TV.setText(odSurveyEntity.get(position).trainDire3);
		holder.odShift3TV.setText(odSurveyEntity.get(position).shift3);
		holder.odTrainStartingTime3TV.setText(odSurveyEntity.get(position).trainStartingTime3);
		holder.odGetoffStation3TV.setText(odSurveyEntity.get(position).getoffStation3);
		holder.odGetoffStationTime3TV.setText(odSurveyEntity.get(position).getoffStationTime3);
		holder.odGetoutStationTimeTV.setText(odSurveyEntity.get(position).getoutStationTime);
		
		if(position == 0){
			convertView.setBackgroundResource(R.color.head_bg);
			holder.odIDTV.setTextColor(Color.WHITE);
			holder.odNameTV.setTextColor(Color.WHITE);
			holder.odDateTV.setTextColor(Color.WHITE);
			holder.odCardNumTV.setTextColor(Color.WHITE);
			holder.odIDNoTV.setTextColor(Color.WHITE);
			holder.odStationTV.setTextColor(Color.WHITE);
			holder.odGetinStationLineTV.setTextColor(Color.WHITE);
			holder.odTransferCountTV.setTextColor(Color.WHITE);
			holder.odStationCountTV.setTextColor(Color.WHITE);
			holder.odDistanceTotalTV.setTextColor(Color.WHITE);
			holder.odWeekdayIfTV.setTextColor(Color.WHITE);
			holder.odPeakIfTV.setTextColor(Color.WHITE);
			holder.odGetinStationTimeTV.setTextColor(Color.WHITE);
			holder.odArriveStationTime1TV.setTextColor(Color.WHITE);
			holder.odTrainDire1TV.setTextColor(Color.WHITE);
			holder.odShift1TV.setTextColor(Color.WHITE);
			holder.odTrainStartingTime1TV.setTextColor(Color.WHITE);
			holder.odGetoffStation1TV.setTextColor(Color.WHITE);
			holder.odGetoffStationTime1TV.setTextColor(Color.WHITE);
			holder.odTransferLine1TV.setTextColor(Color.WHITE);

			holder.odArriveStationTime2TV.setTextColor(Color.WHITE);
			holder.odTrainDire2TV.setTextColor(Color.WHITE);
			holder.odShift2TV.setTextColor(Color.WHITE);
			holder.odTrainStartingTime2TV.setTextColor(Color.WHITE);
			holder.odGetoffStation2TV.setTextColor(Color.WHITE);
			holder.odGetoffStationTime2TV.setTextColor(Color.WHITE);
			holder.odTransferLine2TV.setTextColor(Color.WHITE);
			
			holder.odArriveStationTime3TV.setTextColor(Color.WHITE);
			holder.odTrainDire3TV.setTextColor(Color.WHITE);
			holder.odShift3TV.setTextColor(Color.WHITE);
			holder.odTrainStartingTime3TV.setTextColor(Color.WHITE);
			holder.odGetoffStation3TV.setTextColor(Color.WHITE);
			holder.odGetoffStationTime3TV.setTextColor(Color.WHITE);
			holder.odGetoutStationTimeTV.setTextColor(Color.WHITE);
			
		}else{
			if(position%2 !=0){
				convertView.setBackgroundColor(Color.argb(250 ,  255 ,  255 ,  255 )); 
			}else{
				convertView.setBackgroundColor(Color.argb(250 ,  224 ,  243 ,  250 ));    
			}
		}
		return convertView;
	}

}
