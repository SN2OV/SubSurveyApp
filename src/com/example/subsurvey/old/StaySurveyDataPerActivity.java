package com.example.subsurvey.old;

import com.example.subsurvey.R;
import com.example.subsurvey.R.id;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.staySurvey.helper.StaySurveyDataHelper;
import com.example.subsurvey.staySurvey.ui.StaySurveyDataTotalActivity;
import com.example.subsurvey.walkSurvey.helper.WalkSurveyDataHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class StaySurveyDataPerActivity extends ActionBarActivity {

	private EditText ssGoinStationTimeET,ssTransferTimeET,ssArrivePlatformET,ssLinePersonCountET,ssNo1StartTimeET,ssNo1GetonNumET,ssNo1GetoffNumET,
						ssNo2StartTimeET,ssNo2GetonNumET,ssNo2GetoffNumET,ssNo3StartTimeET,ssNo3GetonNumET,ssNo3GetoffNumET,
						ssNo4StartTimeET,ssNo4GetonNumET,ssNo4GetoffNumET,ssNo5StartTimeET,ssNo5GetonNumET,ssNo5GetoffNumET,
						ssNo6StartTimeET,ssNo6GetonNumET,ssNo6GetoffNumET;
	private ImageView ssGoinStationTimeReset,ssTransferTimeReset,ssArrivePlatformReset,ssNo1StartTimeReset,ssNo2StartTimeReset,ssNo3StartTimeReset,ssNo4StartTimeReset,
						ssNo5StartTimeReset,ssNo6StartTimeReset,saveSurveyDataIV,querySurveyDataIV;
	private RelativeLayout ssGoinStationTimeRL,ssTransferTimeRL,ssNo1StartTimeRL,ssNo1GetonNumRL,ssNo2StartTimeRL,ssNo2GetonNumRL,ssNo3StartTimeRL,ssNo3GetonNumRL,
						ssNo4StartTimeRL,ssNo4GetonNumRL,ssNo5StartTimeRL,ssNo5GetonNumRL,ssNo6StartTimeRL,ssNo6GetonNumRL;
	private Context context;
	public int rowIDgot;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stay_survey_data_per);
		
		context = getApplicationContext();
		final ActionBar bar =getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		//基本配置
		ssGoinStationTimeET = (EditText)findViewById(R.id.ssGoinStationTimeET);
		ssTransferTimeET = (EditText)findViewById(R.id.ssTransferTimeET);
		ssArrivePlatformET = (EditText)findViewById(R.id.ssArrivePlatformET);
		ssLinePersonCountET = (EditText)findViewById(R.id.ssLinePersonCountET);
		ssNo1StartTimeET = (EditText)findViewById(R.id.ssNo1StartTimeET);
		ssNo1GetonNumET = (EditText)findViewById(R.id.ssNo1GetonNumET);
		ssNo1GetoffNumET = (EditText)findViewById(R.id.ssNo1GetoffNumET);
		ssNo2StartTimeET = (EditText)findViewById(R.id.ssNo2StartTimeET);
		ssNo2GetonNumET = (EditText)findViewById(R.id.ssNo2GetonNumET);
		ssNo2GetoffNumET = (EditText)findViewById(R.id.ssNo2GetoffNumET);
		ssNo3StartTimeET = (EditText)findViewById(R.id.ssNo3StartTimeET);
		ssNo3GetonNumET = (EditText)findViewById(R.id.ssNo3GetonNumET);
		ssNo3GetoffNumET = (EditText)findViewById(R.id.ssNo3GetoffNumET);
		ssNo4StartTimeET = (EditText)findViewById(R.id.ssNo4StartTimeET);
		ssNo4GetonNumET = (EditText)findViewById(R.id.ssNo4GetonNumET);
		ssNo4GetoffNumET = (EditText)findViewById(R.id.ssNo4GetoffNumET);
		ssNo5StartTimeET = (EditText)findViewById(R.id.ssNo5StartTimeET);
		ssNo5GetonNumET = (EditText)findViewById(R.id.ssNo5GetonNumET);
		ssNo5GetoffNumET = (EditText)findViewById(R.id.ssNo5GetoffNumET);
		ssNo6StartTimeET = (EditText)findViewById(R.id.ssNo6StartTimeET);
		ssNo6GetonNumET = (EditText)findViewById(R.id.ssNo6GetonNumET);
		ssNo6GetoffNumET = (EditText)findViewById(R.id.ssNo6GetoffNumET);
		
		ssGoinStationTimeReset = (ImageView)findViewById(R.id.ssGoinStationTimeReset);
		ssTransferTimeReset = (ImageView)findViewById(R.id.ssTransferTimeReset);
		ssArrivePlatformReset = (ImageView)findViewById(R.id.ssArrivePlatformReset);
		ssNo1StartTimeReset = (ImageView)findViewById(R.id.ssNo1StartTimeReset);
		ssNo2StartTimeReset = (ImageView)findViewById(R.id.ssNo2StartTimeReset);
		ssNo3StartTimeReset = (ImageView)findViewById(R.id.ssNo3StartTimeReset);
		ssNo4StartTimeReset = (ImageView)findViewById(R.id.ssNo4StartTimeReset);
		ssNo5StartTimeReset = (ImageView)findViewById(R.id.ssNo5StartTimeReset);
		ssNo6StartTimeReset = (ImageView)findViewById(R.id.ssNo6StartTimeReset);
		ssGoinStationTimeReset.setOnClickListener(new onClickListenerResetImpl());
		ssTransferTimeReset.setOnClickListener(new onClickListenerResetImpl());
		ssArrivePlatformReset.setOnClickListener(new onClickListenerResetImpl());
		ssNo1StartTimeReset.setOnClickListener(new onClickListenerResetImpl());
		ssNo2StartTimeReset.setOnClickListener(new onClickListenerResetImpl());
		ssNo3StartTimeReset.setOnClickListener(new onClickListenerResetImpl());
		ssNo4StartTimeReset.setOnClickListener(new onClickListenerResetImpl());
		ssNo5StartTimeReset.setOnClickListener(new onClickListenerResetImpl());
		ssNo6StartTimeReset.setOnClickListener(new onClickListenerResetImpl());
		saveSurveyDataIV = (ImageView)findViewById(R.id.saveSurveyDataIV);
		querySurveyDataIV = (ImageView)findViewById(R.id.querySureyDataIV);
		saveSurveyDataIV.setOnClickListener(new onClickListenerSaveDataImpl());
		querySurveyDataIV.setOnClickListener(new onClickListenerQueryDataImpl());
		
		ssGoinStationTimeRL = (RelativeLayout)findViewById(R.id.ssGoinStationTimeRL);
		ssTransferTimeRL = (RelativeLayout)findViewById(R.id.ssTransferTimeRL);
		ssNo1StartTimeRL = (RelativeLayout)findViewById(R.id.ssNo1StartTimeRL);
		ssNo2StartTimeRL = (RelativeLayout)findViewById(R.id.ssNo2StartTimeRL);
		ssNo3StartTimeRL = (RelativeLayout)findViewById(R.id.ssNo3StartTimeRL);
		ssNo4StartTimeRL = (RelativeLayout)findViewById(R.id.ssNo4StartTimeRL);
		ssNo5StartTimeRL = (RelativeLayout)findViewById(R.id.ssNo5StartTimeRL);
		ssNo6StartTimeRL = (RelativeLayout)findViewById(R.id.ssNo6StartTimeRL);
		ssNo1GetonNumRL = (RelativeLayout)findViewById(R.id.ssNo1GetonNumRL);
		ssNo2GetonNumRL = (RelativeLayout)findViewById(R.id.ssNo2GetonNumRL);
		ssNo3GetonNumRL = (RelativeLayout)findViewById(R.id.ssNo3GetonNumRL);
		ssNo4GetonNumRL = (RelativeLayout)findViewById(R.id.ssNo4GetonNumRL);
		ssNo5GetonNumRL = (RelativeLayout)findViewById(R.id.ssNo5GetonNumRL);
		ssNo6GetonNumRL = (RelativeLayout)findViewById(R.id.ssNo6GetonNumRL);
		
		Intent it = getIntent();
		String surveyType =it.getStringExtra("surveyType");
		rowIDgot = it.getIntExtra("ssRowID", 0);
		//进站、换乘分别对应0、1
		int surveyTypeNo;
		for(surveyTypeNo=0;surveyTypeNo<2;surveyTypeNo++){
			if(WalkSurveyTimeRecordedActivity.typeInfo[surveyTypeNo].equals(surveyType))
						break;
		}
		if(surveyTypeNo==0)
			ssTransferTimeRL.setVisibility(View.GONE);
		else
			ssGoinStationTimeRL.setVisibility(View.GONE);
		//控件填充 StaySurveySettingActivity.staySurveyEntity
		ssGoinStationTimeET.setText(StaySurveySettingActivity.staySurveyEntity.getGointoStationTime());
		ssTransferTimeET.setText(StaySurveySettingActivity.staySurveyEntity.getGetoffTime());
		ssArrivePlatformET.setText(StaySurveySettingActivity.staySurveyEntity.getArriveStationTime());
		ssLinePersonCountET.setText(StaySurveySettingActivity.staySurveyEntity.getStartLineNum());//起始排队人数
		ssNo1StartTimeET.setText(StaySurveySettingActivity.staySurveyEntity.getTrainStartTime1());
		ssNo1GetonNumET.setText(StaySurveySettingActivity.staySurveyEntity.getGetOnNum1());
		ssNo1GetoffNumET.setText(StaySurveySettingActivity.staySurveyEntity.getGetOffNum1());
		
		int etArr[] = {R.id.ssNo2StartTimeET,R.id.ssNo2GetonNumET,R.id.ssNo2GetoffNumET,R.id.ssNo3StartTimeET,R.id.ssNo3GetonNumET,R.id.ssNo3GetoffNumET,
				R.id.ssNo4StartTimeET,R.id.ssNo4GetonNumET,R.id.ssNo4GetoffNumET,R.id.ssNo5StartTimeET,R.id.ssNo5GetonNumET,R.id.ssNo5GetoffNumET,
				R.id.ssNo6StartTimeET,R.id.ssNo6GetonNumET,R.id.ssNo6GetoffNumET};
		int rlArr[] = {R.id.ssNo2StartTimeRL,R.id.ssNo2GetonNumRL,R.id.ssNo3StartTimeRL,R.id.ssNo3GetonNumRL,
				R.id.ssNo4StartTimeRL,R.id.ssNo4GetonNumRL,R.id.ssNo5StartTimeRL,R.id.ssNo5GetonNumRL,
				R.id.ssNo6StartTimeRL,R.id.ssNo6GetonNumRL};
		//将第二辆车及以后的车输入框显示出来
		for(int i=1;i<StaySurveySettingActivity.staySurveyEntity.getCountStop();i++){
			RelativeLayout tempRL=(RelativeLayout)findViewById(rlArr[i*2-2]);
			RelativeLayout tempRL2=(RelativeLayout)findViewById(rlArr[i*2-1]);
			tempRL.setVisibility(View.VISIBLE);
			tempRL2.setVisibility(View.VISIBLE);
		}
		//根据countStop把第n班车的数据添加进去
		for(int i=1;i<StaySurveySettingActivity.staySurveyEntity.getCountStop();i++){
			EditText tempET = (EditText)findViewById(etArr[i*3-2]);
			EditText tempET2 = (EditText)findViewById(etArr[i*3-1]);
			EditText tempET3 = (EditText)findViewById(etArr[i*3-3]);
			switch(i){
			case 1:
				tempET.setText(StaySurveySettingActivity.staySurveyEntity.getGetOnNum2());
				tempET2.setText(StaySurveySettingActivity.staySurveyEntity.getGetOffNum2());
				tempET3.setText(StaySurveySettingActivity.staySurveyEntity.getTrainStartTime2());
				break;
			case 2:
				tempET.setText(StaySurveySettingActivity.staySurveyEntity.getGetOnNum3());
				tempET2.setText(StaySurveySettingActivity.staySurveyEntity.getGetOffNum3());
				tempET3.setText(StaySurveySettingActivity.staySurveyEntity.getTrainStartTime3());
				break;
			case 3:
				tempET.setText(StaySurveySettingActivity.staySurveyEntity.getGetOnNum4());
				tempET2.setText(StaySurveySettingActivity.staySurveyEntity.getGetOffNum4());
				tempET3.setText(StaySurveySettingActivity.staySurveyEntity.getTrainStartTime4());
				break;
			case 4:
				tempET.setText(StaySurveySettingActivity.staySurveyEntity.getGetOnNum5());
				tempET2.setText(StaySurveySettingActivity.staySurveyEntity.getGetOffNum5());
				tempET3.setText(StaySurveySettingActivity.staySurveyEntity.getTrainStartTime5());
				break;
			case 5:
				tempET.setText(StaySurveySettingActivity.staySurveyEntity.getGetOnNum6());
				tempET2.setText(StaySurveySettingActivity.staySurveyEntity.getGetOffNum6());
				tempET3.setText(StaySurveySettingActivity.staySurveyEntity.getTrainStartTime6());
				break;
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stay_survey_data_per, menu);
		return true;
	}
	
	//配置各种reset图标的监听
		public class onClickListenerResetImpl implements OnClickListener{

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.ssGoinStationTimeReset:
					ssGoinStationTimeET.setText("");
					break;
				case R.id.ssTransferTimeReset:
					ssTransferTimeET.setText("");
					break;
				case R.id.ssArrivePlatformReset:
					ssArrivePlatformET.setText("");
					break;
				case R.id.ssNo1StartTimeReset:
					ssNo1StartTimeET.setText("");
					break;
				case R.id.ssNo2StartTimeReset:
					ssNo2StartTimeET.setText("");
					break;
				case R.id.ssNo3StartTimeReset:
					ssNo3StartTimeET.setText("");
					break;
				case R.id.ssNo4StartTimeReset:
					ssNo3StartTimeET.setText("");
					break;
				case R.id.ssNo5StartTimeReset:
					ssNo3StartTimeET.setText("");
					break;
				case R.id.ssNo6StartTimeReset:
					ssNo3StartTimeET.setText("");
					break;
				default:
					break;
				}
			}
			
		}
		
		//更改所记录的数据
		public class onClickListenerSaveDataImpl implements OnClickListener{
			@Override
			public void onClick(View v) {
				//如果满了，WalkSurveyTimeRecordedActivity.fullflag==1,因为前面数据已入库因此这里必须再次入库，id也可以获取。否则不用
				StaySurveySettingActivity.staySurveyEntity.setGointoStationTime(ssGoinStationTimeET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setArriveStationTime(ssArrivePlatformET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetoffTime(ssTransferTimeET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setStartLineNum(ssLinePersonCountET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setTrainStartTime1(ssNo1StartTimeET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOnNum1(ssNo1GetonNumET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOffNum1(ssNo1GetoffNumET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setTrainStartTime2(ssNo2StartTimeET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOnNum2(ssNo2GetonNumET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOffNum2(ssNo2GetoffNumET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setTrainStartTime3(ssNo3StartTimeET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOnNum3(ssNo3GetonNumET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOffNum3(ssNo3GetoffNumET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setTrainStartTime4(ssNo4StartTimeET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOnNum4(ssNo4GetonNumET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOffNum4(ssNo4GetoffNumET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setTrainStartTime5(ssNo5StartTimeET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOnNum5(ssNo5GetonNumET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOffNum5(ssNo5GetoffNumET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setTrainStartTime6(ssNo6StartTimeET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOnNum6(ssNo6GetonNumET.getText().toString());
				StaySurveySettingActivity.staySurveyEntity.setGetOffNum6(ssNo6GetoffNumET.getText().toString());
				
//				if(WalkSurveyTimeRecordedActivity.fullFlag==1){
					StaySurveySettingActivity.staySurveyEntity.setID(rowIDgot-1);//保存完一组数据后rowID++，因此这里需要-1，以根据ID更新数据
					StaySurveyDataHelper.updateTimeAndNum(context, StaySurveySettingActivity.staySurveyEntity);
//				}
				UIHelper.ToastMessage(context, "本组数据保存成功");
			}
			
		}
		
		public class onClickListenerQueryDataImpl implements OnClickListener{

			@Override
			public void onClick(View v) {
				Intent it = new Intent();
				it.setClass(context, StaySurveyDataTotalActivity.class);
				startActivity(it);
			}
			
		}

}
