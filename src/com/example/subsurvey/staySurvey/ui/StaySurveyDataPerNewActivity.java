package com.example.subsurvey.staySurvey.ui;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.AppContext;
import com.example.subsurvey.R;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.common.SurveyUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.odSurvey.helper.ODSurveyDataHelper;
import com.example.subsurvey.odSurvey.ui.ODSurveyTimeRecordedNewActivity;
import com.example.subsurvey.odSurvey.ui.ODWordActivity;
import com.example.subsurvey.personalSetting.UserEntity;
import com.example.subsurvey.staySurvey.beans.StaySurveyEntity;
import com.example.subsurvey.staySurvey.helper.StaySurveyDataHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StaySurveyDataPerNewActivity extends ActionBarActivity {
	
	@InjectView(R.id.ssPerGointoStationRL)
	RelativeLayout ssPerGointoStationRL;
	@InjectView(R.id.ssPerGointoStationTV)
	TextView ssPerGointoStationTV;
	@InjectView(R.id.ssPerGetoffRL)
	RelativeLayout ssPerGetoffRL;
	@InjectView(R.id.ssPerGetoffTV)
	TextView ssPerGetoffTV;
	@InjectView(R.id.ssPerArrivePlatformRL)
	RelativeLayout ssPerArrivePlatformRL;
	@InjectView(R.id.ssPerArrivePlatformTV)
	TextView ssPerArrivePlatformTV;
	@InjectView(R.id.ssPerOrignLineNumRL)
	RelativeLayout ssPerOrignLineNumRL;
	@InjectView(R.id.ssPerOrignLineNumTV)
	TextView ssPerOrignLineNumTV;
	
	@InjectView(R.id.ssPerGetOffNum1RL)
	RelativeLayout ssPerGetOffNum1RL;
	@InjectView(R.id.ssPerGetOffNum1TV)
	TextView ssPerGetOffNum1TV;
	@InjectView(R.id.ssPerGetOnNum1RL)
	RelativeLayout ssPerGetOnNum1RL;
	@InjectView(R.id.ssPerGetOnNum1TV)
	TextView ssPerGetOnNum1TV;
	@InjectView(R.id.ssPerTrainStartTime1RL)
	RelativeLayout ssPerTrainStartTime1RL;
	@InjectView(R.id.ssPerTrainStartTime1TV)
	TextView ssPerTrainStartTime1TV;
	
	@InjectView(R.id.ssPerGetOffNum2RL)
	RelativeLayout ssPerGetOffNum2RL;
	@InjectView(R.id.ssPerGetOffNum2TV)
	TextView ssPerGetOffNum2TV;
	@InjectView(R.id.ssPerGetOnNum2RL)
	RelativeLayout ssPerGetOnNum2RL;
	@InjectView(R.id.ssPerGetOnNum2TV)
	TextView ssPerGetOnNum2TV;
	@InjectView(R.id.ssPerTrainStartTime2RL)
	RelativeLayout ssPerTrainStartTime2RL;
	@InjectView(R.id.ssPerTrainStartTime2TV)
	TextView ssPerTrainStartTime2TV;
	
	@InjectView(R.id.ssPerGetOffNum3RL)
	RelativeLayout ssPerGetOffNum3RL;
	@InjectView(R.id.ssPerGetOffNum3TV)
	TextView ssPerGetOffNum3TV;
	@InjectView(R.id.ssPerGetOnNum3RL)
	RelativeLayout ssPerGetOnNum3RL;
	@InjectView(R.id.ssPerGetOnNum3TV)
	TextView ssPerGetOnNum3TV;
	@InjectView(R.id.ssPerTrainStartTime3RL)
	RelativeLayout ssPerTrainStartTime3RL;
	@InjectView(R.id.ssPerTrainStartTime3TV)
	TextView ssPerTrainStartTime3TV;
	
	@InjectView(R.id.ssPerGetOffNum4RL)
	RelativeLayout ssPerGetOffNum4RL;
	@InjectView(R.id.ssPerGetOffNum4TV)
	TextView ssPerGetOffNum4TV;
	@InjectView(R.id.ssPerGetOnNum4RL)
	RelativeLayout ssPerGetOnNum4RL;
	@InjectView(R.id.ssPerGetOnNum4TV)
	TextView ssPerGetOnNum4TV;
	@InjectView(R.id.ssPerTrainStartTime4RL)
	RelativeLayout ssPerTrainStartTime4RL;
	@InjectView(R.id.ssPerTrainStartTime4TV)
	TextView ssPerTrainStartTime4TV;
	
	@InjectView(R.id.ssPerGetOffNum5RL)
	RelativeLayout ssPerGetOffNum5RL;
	@InjectView(R.id.ssPerGetOffNum5TV)
	TextView ssPerGetOffNum5TV;
	@InjectView(R.id.ssPerGetOnNum5RL)
	RelativeLayout ssPerGetOnNum5RL;
	@InjectView(R.id.ssPerGetOnNum5TV)
	TextView ssPerGetOnNum5TV;
	@InjectView(R.id.ssPerTrainStartTime5RL)
	RelativeLayout ssPerTrainStartTime5RL;
	@InjectView(R.id.ssPerTrainStartTime5TV)
	TextView ssPerTrainStartTime5TV;
	
	@InjectView(R.id.ssPerGetOffNum6RL)
	RelativeLayout ssPerGetOffNum6RL;
	@InjectView(R.id.ssPerGetOffNum6TV)
	TextView ssPerGetOffNum6TV;
	@InjectView(R.id.ssPerGetOnNum6RL)
	RelativeLayout ssPerGetOnNum6RL;
	@InjectView(R.id.ssPerGetOnNum6TV)
	TextView ssPerGetOnNum6TV;
	@InjectView(R.id.ssPerTrainStartTime6RL)
	RelativeLayout ssPerTrainStartTime6RL;
	@InjectView(R.id.ssPerTrainStartTime6TV)
	TextView ssPerTrainStartTime6TV;
	
	
	private Context context;
	private AppContext appContext;
	private UserEntity user;
	private int surveyTypeNo,ssRowID,countStop;
	private Intent it ;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stay_survey_data_per_new);
		
		init();
		initView();
	}

	
	
	private void init() {
		context = getApplicationContext();
		appContext = (AppContext)getApplication();
		user = appContext.getUser();
		
		final ActionBar bar =getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
        bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		
		ButterKnife.inject(this);
		it = getIntent();
		ssRowID = it.getIntExtra("ssRowID", 1);
		countStop = it.getIntExtra("countStop", 1);
		surveyTypeNo = SurveyUtils.getSurveyTypeNo(user.getSsType(), AppConfig.ssTypeInfo);//进站为0换乘为1
	}



	private void initView() {
		initRelativeLayout(countStop,surveyTypeNo);
		fillDataIntoView(StaySurveyTimeRecordedNewActivity.staySurveyEntity);
	}
	
	@OnClick({R.id.ssPerGointoStationRL,R.id.ssPerGetoffRL,R.id.ssPerArrivePlatformRL,R.id.ssPerOrignLineNumRL,R.id.ssPerGetOffNum1RL,
		R.id.ssPerGetOnNum1RL,R.id.ssPerTrainStartTime1RL,R.id.ssPerGetOffNum2RL,R.id.ssPerGetOnNum2RL,R.id.ssPerTrainStartTime2RL,
		R.id.ssPerGetOffNum3RL,	R.id.ssPerGetOnNum3RL,R.id.ssPerTrainStartTime3RL,R.id.ssPerGetOffNum4RL,R.id.ssPerGetOnNum4RL,
		R.id.ssPerTrainStartTime4RL,R.id.ssPerGetOffNum5RL,	R.id.ssPerGetOnNum5RL,R.id.ssPerTrainStartTime5RL,R.id.ssPerGetOffNum6RL,
		R.id.ssPerGetOnNum6RL,R.id.ssPerTrainStartTime6RL})
	void onPerDataClick(View view){
		Intent it = new Intent();
		it.setClass(context, SSWordActivity.class);
		it.putExtra("ssRowID", ssRowID);
		switch(view.getId()){
		case R.id.ssPerGointoStationRL:
			it.putExtra("ssPerGointoStation",ssPerGointoStationTV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_GO_INTO_STATION_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_GO_INTO_STATION_CODE);
			break;
		case R.id.ssPerGetoffRL:
			it.putExtra("ssPerGetoff",ssPerGetoffTV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_GET_OFF_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_GET_OFF_CODE);
			break;
		case R.id.ssPerArrivePlatformRL:
			it.putExtra("ssPerArrivePlatform",ssPerArrivePlatformTV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_ARRIVE_PLATFORM_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_ARRIVE_PLATFORM_CODE);
			break;
		case R.id.ssPerOrignLineNumRL:
			it.putExtra("ssPerOrignLineNum",ssPerOrignLineNumTV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_ORIGN_LINE_NUM_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_ORIGN_LINE_NUM_CODE);
			break;
		case R.id.ssPerGetOffNum1RL:
			it.putExtra("ssPerGetOffNum1",ssPerGetOffNum1TV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_GET_OFF_NUM1_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_GET_OFF_NUM1_CODE);
			break;
		case R.id.ssPerGetOnNum1RL:
			it.putExtra("ssPerGetOnNum1",ssPerGetOnNum1TV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_GET_ON_NUM1_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_GET_ON_NUM1_CODE);
			break;
		case R.id.ssPerTrainStartTime1RL:
			it.putExtra("ssPerTrainStartTime1",ssPerTrainStartTime1TV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_TRAIN_START_TIME1_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_TRAIN_START_TIME1_CODE);
			break;
		case R.id.ssPerGetOffNum2RL:
			it.putExtra("ssPerGetOffNum2",ssPerGetOffNum2TV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_GET_OFF_NUM2_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_GET_OFF_NUM2_CODE);
			break;
		case R.id.ssPerGetOnNum2RL:
			it.putExtra("ssPerGetOnNum2",ssPerGetOnNum2TV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_GET_ON_NUM2_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_GET_ON_NUM2_CODE);
			break;
		case R.id.ssPerTrainStartTime2RL:
			it.putExtra("ssPerTrainStartTime2",ssPerTrainStartTime2TV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_TRAIN_START_TIME2_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_TRAIN_START_TIME2_CODE);
			break;
		case R.id.ssPerGetOffNum3RL:
			it.putExtra("ssPerGetOffNum3",ssPerGetOffNum3TV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_GET_OFF_NUM3_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_GET_OFF_NUM3_CODE);
			break;
		case R.id.ssPerGetOnNum3RL:
			it.putExtra("ssPerGetOnNum3",ssPerGetOnNum3TV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_GET_ON_NUM3_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_GET_ON_NUM3_CODE);
			break;
		case R.id.ssPerTrainStartTime3RL:
			it.putExtra("ssPerTrainStartTime3",ssPerTrainStartTime3TV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_TRAIN_START_TIME3_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_TRAIN_START_TIME3_CODE);
			break;
		case R.id.ssPerGetOffNum4RL:
			it.putExtra("ssPerGetOffNum4",ssPerGetOffNum4TV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_GET_OFF_NUM4_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_GET_OFF_NUM4_CODE);
			break;
		case R.id.ssPerGetOnNum4RL:
			it.putExtra("ssPerGetOnNum4",ssPerGetOnNum4TV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_GET_ON_NUM4_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_GET_ON_NUM4_CODE);
			break;
		case R.id.ssPerTrainStartTime4RL:
			it.putExtra("ssPerTrainStartTime4",ssPerTrainStartTime4TV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_TRAIN_START_TIME4_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_TRAIN_START_TIME4_CODE);
			break;
		case R.id.ssPerGetOffNum5RL:
			it.putExtra("ssPerGetOffNum5",ssPerGetOffNum5TV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_GET_OFF_NUM5_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_GET_OFF_NUM5_CODE);
			break;
		case R.id.ssPerGetOnNum5RL:
			it.putExtra("ssPerGetOnNum5",ssPerGetOnNum5TV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_GET_ON_NUM5_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_GET_ON_NUM5_CODE);
			break;
		case R.id.ssPerTrainStartTime5RL:
			it.putExtra("ssPerTrainStartTime5",ssPerTrainStartTime5TV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_TRAIN_START_TIME5_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_TRAIN_START_TIME5_CODE);
			break;
		case R.id.ssPerGetOffNum6RL:
			it.putExtra("ssPerGetOffNum6",ssPerGetOffNum6TV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_GET_OFF_NUM6_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_GET_OFF_NUM6_CODE);
			break;
		case R.id.ssPerGetOnNum6RL:
			it.putExtra("ssPerGetOnNum6",ssPerGetOnNum6TV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_GET_ON_NUM6_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_GET_ON_NUM6_CODE);
			break;
		case R.id.ssPerTrainStartTime6RL:
			it.putExtra("ssPerTrainStartTime6",ssPerTrainStartTime6TV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_TRAIN_START_TIME6_CODE);
			startActivityForResult(it, AppConfig.SS_DATA_TRAIN_START_TIME6_CODE);
			break;
		}
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data == null)
			return ;
		switch(requestCode){
		case AppConfig.SS_DATA_GO_INTO_STATION_CODE:
			String ssPerGointoStation = data.getStringExtra("ssPerGointoStation");
			ssPerGointoStationTV.setText(ssPerGointoStation);
			break;
		case AppConfig.SS_DATA_GET_OFF_CODE:
			String ssPerGetoff = data.getStringExtra("ssPerGetoff");
			ssPerGetoffTV.setText(ssPerGetoff);
			break;
		case AppConfig.SS_DATA_ARRIVE_PLATFORM_CODE:
			String ssPerArrivePlatform = data.getStringExtra("ssPerArrivePlatform");
			ssPerArrivePlatformTV.setText(ssPerArrivePlatform);
			break;
		case AppConfig.SS_DATA_ORIGN_LINE_NUM_CODE:
			String ssPerOrignLineNum = data.getStringExtra("ssPerOrignLineNum");
			ssPerOrignLineNumTV.setText(ssPerOrignLineNum);
			break;
		case AppConfig.SS_DATA_GET_OFF_NUM1_CODE:
			String ssPerGetOffNum1 = data.getStringExtra("ssPerGetOffNum1");
			ssPerGetOffNum1TV.setText(ssPerGetOffNum1);
			break;
		case AppConfig.SS_DATA_GET_ON_NUM1_CODE:
			String ssPerGetOnNum1 = data.getStringExtra("ssPerGetOnNum1");
			ssPerGetOnNum1TV.setText(ssPerGetOnNum1);
			break;
		case AppConfig.SS_DATA_TRAIN_START_TIME1_CODE:
			String ssPerTrainStartTime1 = data.getStringExtra("ssPerTrainStartTime1");
			ssPerTrainStartTime1TV.setText(ssPerTrainStartTime1);
			break;
		case AppConfig.SS_DATA_GET_OFF_NUM2_CODE:
			String ssPerGetOffNum2 = data.getStringExtra("ssPerGetOffNum2");
			ssPerGetOffNum2TV.setText(ssPerGetOffNum2);
			break;
		case AppConfig.SS_DATA_GET_ON_NUM2_CODE:
			String ssPerGetOnNum2 = data.getStringExtra("ssPerGetOnNum2");
			ssPerGetOnNum2TV.setText(ssPerGetOnNum2);
			break;
		case AppConfig.SS_DATA_TRAIN_START_TIME2_CODE:
			String ssPerTrainStartTime2 = data.getStringExtra("ssPerTrainStartTime2");
			ssPerTrainStartTime2TV.setText(ssPerTrainStartTime2);
			break;
		case AppConfig.SS_DATA_GET_OFF_NUM3_CODE:
			String ssPerGetOffNum3 = data.getStringExtra("ssPerGetOffNum3");
			ssPerGetOffNum3TV.setText(ssPerGetOffNum3);
			break;
		case AppConfig.SS_DATA_GET_ON_NUM3_CODE:
			String ssPerGetOnNum3 = data.getStringExtra("ssPerGetOnNum3");
			ssPerGetOnNum3TV.setText(ssPerGetOnNum3);
			break;
		case AppConfig.SS_DATA_TRAIN_START_TIME3_CODE:
			String ssPerTrainStartTime3 = data.getStringExtra("ssPerTrainStartTime3");
			ssPerTrainStartTime3TV.setText(ssPerTrainStartTime3);
			break;
		case AppConfig.SS_DATA_GET_OFF_NUM4_CODE:
			String ssPerGetOffNum4 = data.getStringExtra("ssPerGetOffNum4");
			ssPerGetOffNum4TV.setText(ssPerGetOffNum4);
			break;
		case AppConfig.SS_DATA_GET_ON_NUM4_CODE:
			String ssPerGetOnNum4 = data.getStringExtra("ssPerGetOnNum4");
			ssPerGetOnNum4TV.setText(ssPerGetOnNum4);
			break;
		case AppConfig.SS_DATA_TRAIN_START_TIME4_CODE:
			String ssPerTrainStartTime4 = data.getStringExtra("ssPerTrainStartTime4");
			ssPerTrainStartTime4TV.setText(ssPerTrainStartTime4);
			break;
		case AppConfig.SS_DATA_GET_OFF_NUM5_CODE:
			String ssPerGetOffNum5 = data.getStringExtra("ssPerGetOffNum5");
			ssPerGetOffNum5TV.setText(ssPerGetOffNum5);
			break;
		case AppConfig.SS_DATA_GET_ON_NUM5_CODE:
			String ssPerGetOnNum5 = data.getStringExtra("ssPerGetOnNum5");
			ssPerGetOnNum5TV.setText(ssPerGetOnNum5);
			break;
		case AppConfig.SS_DATA_TRAIN_START_TIME5_CODE:
			String ssPerTrainStartTime5 = data.getStringExtra("ssPerTrainStartTime5");
			ssPerTrainStartTime5TV.setText(ssPerTrainStartTime5);
			break;
		case AppConfig.SS_DATA_GET_OFF_NUM6_CODE:
			String ssPerGetOffNum6 = data.getStringExtra("ssPerGetOffNum6");
			ssPerGetOffNum6TV.setText(ssPerGetOffNum6);
			break;
		case AppConfig.SS_DATA_GET_ON_NUM6_CODE:
			String ssPerGetOnNum6 = data.getStringExtra("ssPerGetOnNum6");
			ssPerGetOnNum6TV.setText(ssPerGetOnNum6);
			break;
		case AppConfig.SS_DATA_TRAIN_START_TIME6_CODE:
			String ssPerTrainStartTime6 = data.getStringExtra("ssPerTrainStartTime6");
			ssPerTrainStartTime6TV.setText(ssPerTrainStartTime6);
			break;
		}
		
		StaySurveyTimeRecordedNewActivity.staySurveyEntity.setID(ssRowID-1);
		StaySurveyDataHelper.updateTimeAndNum(context, StaySurveyTimeRecordedNewActivity.staySurveyEntity);
		UIHelper.ToastMessage(context, "本组数据修改成功");
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stay_survey_data_per_new, menu);
		return true;
	}

	
	private void initRelativeLayout(int countStop,int surveyTypeNo) {
		//进站0、换乘1
		switch(surveyTypeNo){
		case 0:
			ssPerGetoffRL.setVisibility(View.GONE);
			break;
		case 1:
			ssPerGointoStationRL.setVisibility(View.GONE);
			break;
		}
		
		switch(countStop){
		case 1:
			ssPerGetOffNum2RL.setVisibility(View.GONE);
			ssPerGetOnNum2RL.setVisibility(View.GONE);
			ssPerTrainStartTime2RL.setVisibility(View.GONE);
			ssPerGetOffNum3RL.setVisibility(View.GONE);
			ssPerGetOnNum3RL.setVisibility(View.GONE);
			ssPerTrainStartTime3RL.setVisibility(View.GONE);
			ssPerGetOffNum4RL.setVisibility(View.GONE);
			ssPerGetOnNum4RL.setVisibility(View.GONE);
			ssPerTrainStartTime4RL.setVisibility(View.GONE);
			ssPerGetOffNum5RL.setVisibility(View.GONE);
			ssPerGetOnNum5RL.setVisibility(View.GONE);
			ssPerTrainStartTime5RL.setVisibility(View.GONE);
			ssPerGetOffNum6RL.setVisibility(View.GONE);
			ssPerGetOnNum6RL.setVisibility(View.GONE);
			ssPerTrainStartTime6RL.setVisibility(View.GONE);
			break;
		case 2:
			ssPerGetOffNum3RL.setVisibility(View.GONE);
			ssPerGetOnNum3RL.setVisibility(View.GONE);
			ssPerTrainStartTime3RL.setVisibility(View.GONE);
			ssPerGetOffNum4RL.setVisibility(View.GONE);
			ssPerGetOnNum4RL.setVisibility(View.GONE);
			ssPerTrainStartTime4RL.setVisibility(View.GONE);
			ssPerGetOffNum5RL.setVisibility(View.GONE);
			ssPerGetOnNum5RL.setVisibility(View.GONE);
			ssPerTrainStartTime5RL.setVisibility(View.GONE);
			ssPerGetOffNum6RL.setVisibility(View.GONE);
			ssPerGetOnNum6RL.setVisibility(View.GONE);
			ssPerTrainStartTime6RL.setVisibility(View.GONE);
			break;
		case 3:
			ssPerGetOffNum4RL.setVisibility(View.GONE);
			ssPerGetOnNum4RL.setVisibility(View.GONE);
			ssPerTrainStartTime4RL.setVisibility(View.GONE);
			ssPerGetOffNum5RL.setVisibility(View.GONE);
			ssPerGetOnNum5RL.setVisibility(View.GONE);
			ssPerTrainStartTime5RL.setVisibility(View.GONE);
			ssPerGetOffNum6RL.setVisibility(View.GONE);
			ssPerGetOnNum6RL.setVisibility(View.GONE);
			ssPerTrainStartTime6RL.setVisibility(View.GONE);
			break;
		case 4:
			ssPerGetOffNum5RL.setVisibility(View.GONE);
			ssPerGetOnNum5RL.setVisibility(View.GONE);
			ssPerTrainStartTime5RL.setVisibility(View.GONE);
			ssPerGetOffNum6RL.setVisibility(View.GONE);
			ssPerGetOnNum6RL.setVisibility(View.GONE);
			ssPerTrainStartTime6RL.setVisibility(View.GONE);
			break;
		case 5:
			ssPerGetOffNum6RL.setVisibility(View.GONE);
			ssPerGetOnNum6RL.setVisibility(View.GONE);
			ssPerTrainStartTime6RL.setVisibility(View.GONE);
			break;
		case 6:
			break;
		}
	}
	
	private void fillDataIntoView(StaySurveyEntity staySurveyEntity) {
		ssPerGointoStationTV.setText(staySurveyEntity.getGointoStationTime());
		ssPerGetoffTV.setText(staySurveyEntity.getGetoffTime());
		ssPerArrivePlatformTV.setText(staySurveyEntity.getArriveStationTime());
		ssPerOrignLineNumTV.setText(staySurveyEntity.getStartLineNum());
		ssPerGetOffNum1TV.setText(staySurveyEntity.getGetOffNum1());
		ssPerGetOnNum1TV.setText(staySurveyEntity.getGetOnNum1());
		ssPerTrainStartTime1TV.setText(staySurveyEntity.getTrainStartTime1());
		ssPerGetOffNum2TV.setText(staySurveyEntity.getGetOffNum2());
		ssPerGetOnNum2TV.setText(staySurveyEntity.getGetOnNum2());
		ssPerTrainStartTime2TV.setText(staySurveyEntity.getTrainStartTime2());
		ssPerGetOffNum3TV.setText(staySurveyEntity.getGetOffNum3());
		ssPerGetOnNum3TV.setText(staySurveyEntity.getGetOnNum3());
		ssPerTrainStartTime3TV.setText(staySurveyEntity.getTrainStartTime3());
		ssPerGetOffNum4TV.setText(staySurveyEntity.getGetOffNum4());
		ssPerGetOnNum4TV.setText(staySurveyEntity.getGetOnNum4());
		ssPerTrainStartTime4TV.setText(staySurveyEntity.getTrainStartTime4());
		ssPerGetOffNum5TV.setText(staySurveyEntity.getGetOffNum5());
		ssPerGetOnNum5TV.setText(staySurveyEntity.getGetOnNum5());
		ssPerTrainStartTime5TV.setText(staySurveyEntity.getTrainStartTime5());
		ssPerGetOffNum6TV.setText(staySurveyEntity.getGetOffNum6());
		ssPerGetOnNum6TV.setText(staySurveyEntity.getGetOnNum6());
		ssPerTrainStartTime6TV.setText(staySurveyEntity.getTrainStartTime6());
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
        System.out.println();
        switch(item.getItemId()){
        case android.R.id.home:
            Log.i("TAG", "=========选中返回键");
            this.finish();
            break;
        }
        return super.onOptionsItemSelected(item); 
    }
}
