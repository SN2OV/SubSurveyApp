package com.example.subsurvey.odSurvey.ui;

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
import com.example.subsurvey.old.ODSurveySettingActivity;
import com.example.subsurvey.personalSetting.UserEntity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ODSurveyDataPerNewActivity extends ActionBarActivity {
	
	@InjectView(R.id.odPerGotoPlatform1RL)
	RelativeLayout odPerGotoPlatform1RL;
	@InjectView(R.id.odPerGotoPlatform1TV)
	TextView odPerGotoPlatform1TV;
	@InjectView(R.id.odPerArrivePlatform1RL)
	RelativeLayout odPerArrivePlatform1RL;
	@InjectView(R.id.odPerArrivePlatform1TV)
	TextView odPerArrivePlatform1TV;
	@InjectView(R.id.odPerTrainStart1RL)
	RelativeLayout odPerTrainStart1RL;
	@InjectView(R.id.odPerTrainStart1TV)
	TextView odPerTrainStart1TV;
	@InjectView(R.id.odPerGetOff1RL)
	RelativeLayout odPerGetOff1RL;
	@InjectView(R.id.odPerGetOff1TV)
	TextView odPerGetOff1TV;
	
	@InjectView(R.id.odPerArrivePlatform2RL)
	RelativeLayout odPerArrivePlatform2RL;
	@InjectView(R.id.odPerArrivePlatform2TV)
	TextView odPerArrivePlatform2TV;
	@InjectView(R.id.odPerTrainStart2RL)
	RelativeLayout odPerTrainStart2RL;
	@InjectView(R.id.odPerTrainStart2TV)
	TextView odPerTrainStart2TV;
	@InjectView(R.id.odPerGetOff2RL)
	RelativeLayout odPerGetOff2RL;
	@InjectView(R.id.odPerGetOff2TV)
	TextView odPerGetOff2TV;
	
	@InjectView(R.id.odPerArrivePlatform3RL)
	RelativeLayout odPerArrivePlatform3RL;
	@InjectView(R.id.odPerArrivePlatform3TV)
	TextView odPerArrivePlatform3TV;
	@InjectView(R.id.odPerTrainStart3RL)
	RelativeLayout odPerTrainStart3RL;
	@InjectView(R.id.odPerTrainStart3TV)
	TextView odPerTrainStart3TV;
	@InjectView(R.id.odPerGetOff3RL)
	RelativeLayout odPerGetOff3RL;
	@InjectView(R.id.odPerGetOff3TV)
	TextView odPerGetOff3TV;
	@InjectView(R.id.odPerGoOutPlatFormRL)
	RelativeLayout odPerGoOutPlatFormRL;
	@InjectView(R.id.odPerGoOutPlatFormTV)
	TextView odPerGoOutPlatFormTV;
	
	
	private Context context;
	private AppContext appContext;
	private UserEntity user;
	private int surveyTypeNo,odDatabaseID;
	private Intent it ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_odsurvey_data_per_new);
		
		init();
		initView();
	}

	private void init() {
		final ActionBar bar =getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
        bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		ButterKnife.inject(this);
		context = getApplicationContext();
		appContext = (AppContext)getApplication();
		user = appContext.getUser();
		surveyTypeNo = SurveyUtils.getSurveyTypeNo(user.getOdType(), AppConfig.odTypeInfo);
		it = getIntent();
		odDatabaseID = it.getIntExtra("odDatabaseID",1);
	}

	private void initView() {
		//共通部分
		odPerGotoPlatform1TV.setText(ODSurveyTimeRecordedNewActivity.odSurveyEntity.getGetinStationTime());
		odPerArrivePlatform1TV.setText(ODSurveyTimeRecordedNewActivity.odSurveyEntity.getArriveStationTime1());
		odPerTrainStart1TV.setText(ODSurveyTimeRecordedNewActivity.odSurveyEntity.getTrainStartingTime1());
		odPerGetOff1TV.setText(ODSurveyTimeRecordedNewActivity.odSurveyEntity.getGetoffStationTime1());
		odPerGoOutPlatFormTV.setText(ODSurveyTimeRecordedNewActivity.odSurveyEntity.getGetoutStationTime());
		
		switch(surveyTypeNo){
		
		case AppConfig.OD_TRANSFER_NONE:
			odPerArrivePlatform2RL.setVisibility(View.GONE);
			odPerTrainStart2RL.setVisibility(View.GONE);
			odPerGetOff2RL.setVisibility(View.GONE);
			break;
		case AppConfig.OD_TRANSFER_ONCE:
			odPerArrivePlatform2TV.setText(ODSurveyTimeRecordedNewActivity.odSurveyEntity.getArriveStationTime2());
			odPerTrainStart2TV.setText(ODSurveyTimeRecordedNewActivity.odSurveyEntity.getTrainStartingTime2());
			odPerGetOff2TV.setText(ODSurveyTimeRecordedNewActivity.odSurveyEntity.getGetoffStationTime2());
			break;
		case AppConfig.OD_TRANSFER_TWICE:
			odPerArrivePlatform3RL.setVisibility(View.VISIBLE);
			odPerTrainStart3RL.setVisibility(View.VISIBLE);
			odPerGetOff3RL.setVisibility(View.VISIBLE);
			odPerArrivePlatform2TV.setText(ODSurveyTimeRecordedNewActivity.odSurveyEntity.getArriveStationTime2());
			odPerTrainStart2TV.setText(ODSurveyTimeRecordedNewActivity.odSurveyEntity.getTrainStartingTime2());
			odPerGetOff2TV.setText(ODSurveyTimeRecordedNewActivity.odSurveyEntity.getGetoffStationTime2());
			odPerArrivePlatform3TV.setText(ODSurveyTimeRecordedNewActivity.odSurveyEntity.getArriveStationTime3());
			odPerTrainStart3TV.setText(ODSurveyTimeRecordedNewActivity.odSurveyEntity.getTrainStartingTime3());
			odPerGetOff3TV.setText(ODSurveyTimeRecordedNewActivity.odSurveyEntity.getGetoffStationTime3());
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.odsurvey_data_per_new, menu);
		return true;
	}
	
	@OnClick({R.id.odPerGotoPlatform1RL,R.id.odPerArrivePlatform1RL,R.id.odPerTrainStart1RL,R.id.odPerGetOff1RL,R.id.odPerArrivePlatform2RL,
		R.id.odPerTrainStart2RL,R.id.odPerGetOff2RL,R.id.odPerArrivePlatform3RL,R.id.odPerTrainStart3RL,R.id.odPerGetOff3RL,R.id.odPerGoOutPlatFormRL})
	void onODPerDataClick(View view){
		Intent it = new Intent();
		it.setClass(context, ODWordActivity.class);
		it.putExtra("odDatabaseID", odDatabaseID);
		switch(view.getId()){
		case R.id.odPerGotoPlatform1RL:
			it.putExtra("odPerGotoPlatform1",odPerGotoPlatform1TV.getText().toString());
			it.putExtra("type", AppConfig.OD_DATA_GEI_IN_TIME_CODE);
			startActivityForResult(it, AppConfig.OD_DATA_GEI_IN_TIME_CODE);
			break;
		case R.id.odPerArrivePlatform1RL:
			it.putExtra("odPerArrivePlatform1",odPerArrivePlatform1TV.getText().toString());
			it.putExtra("type", AppConfig.OD_DATA_ARRIVE_PLATFORM1_TIME_CODE);
			startActivityForResult(it, AppConfig.OD_DATA_ARRIVE_PLATFORM1_TIME_CODE);
			break;
		case R.id.odPerTrainStart1RL:
			it.putExtra("odPerTrainStart1",odPerTrainStart1TV.getText().toString());
			it.putExtra("type", AppConfig.OD_DATA_TRAIN_START_TIME1_CODE);
			startActivityForResult(it, AppConfig.OD_DATA_TRAIN_START_TIME1_CODE);
			break;
		case R.id.odPerGetOff1RL:
			it.putExtra("odPerGetOff1",odPerGetOff1TV.getText().toString());
			it.putExtra("type", AppConfig.OD_DATA_GET_OFF_TIME1_CODE);
			startActivityForResult(it, AppConfig.OD_DATA_GET_OFF_TIME1_CODE);
			break;
		case R.id.odPerArrivePlatform2RL:
			it.putExtra("odPerArrivePlatform2",odPerArrivePlatform2TV.getText().toString());
			it.putExtra("type", AppConfig.OD_DATA_ARRIVE_PLATFORM2_TIME_CODE);
			startActivityForResult(it, AppConfig.OD_DATA_ARRIVE_PLATFORM2_TIME_CODE);
			break;
		case R.id.odPerTrainStart2RL:
			it.putExtra("odPerTrainStart2",odPerTrainStart2TV.getText().toString());
			it.putExtra("type", AppConfig.OD_DATA_TRAIN_START_TIME2_CODE);
			startActivityForResult(it, AppConfig.OD_DATA_TRAIN_START_TIME2_CODE);
			break;
		case R.id.odPerGetOff2RL:
			it.putExtra("odPerGetOff2",odPerGetOff2TV.getText().toString());
			it.putExtra("type", AppConfig.OD_DATA_GET_OFF_TIME2_CODE);
			startActivityForResult(it, AppConfig.OD_DATA_GET_OFF_TIME2_CODE);
			break;
		case R.id.odPerArrivePlatform3RL:
			it.putExtra("odPerArrivePlatform3",odPerArrivePlatform3TV.getText().toString());
			it.putExtra("type", AppConfig.OD_DATA_ARRIVE_PLATFORM3_TIME_CODE);
			startActivityForResult(it, AppConfig.OD_DATA_ARRIVE_PLATFORM3_TIME_CODE);
			break;
		case R.id.odPerTrainStart3RL:
			it.putExtra("odPerTrainStart3",odPerTrainStart3TV.getText().toString());
			it.putExtra("type", AppConfig.OD_DATA_TRAIN_START_TIME3_CODE);
			startActivityForResult(it, AppConfig.OD_DATA_TRAIN_START_TIME3_CODE);
			break;
		case R.id.odPerGetOff3RL:
			it.putExtra("odPerGetOff3",odPerGetOff3TV.getText().toString());
			it.putExtra("type", AppConfig.OD_DATA_GET_OFF_TIME3_CODE);
			startActivityForResult(it, AppConfig.OD_DATA_GET_OFF_TIME3_CODE);
			break;
		case R.id.odPerGoOutPlatFormRL:
			it.putExtra("odPerGoOutPlatForm", odPerGoOutPlatFormTV.getText().toString());
			it.putExtra("type", AppConfig.OD_DATA_GET_OUT_TIME_CODE);
			startActivityForResult(it, AppConfig.OD_DATA_GET_OUT_TIME_CODE);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data == null)
			return ;
		switch(requestCode){
		case AppConfig.OD_DATA_GEI_IN_TIME_CODE:
			String odPerGotoPlatform1 = data.getStringExtra("odPerGotoPlatform1");
			odPerGotoPlatform1TV.setText(odPerGotoPlatform1);
			break;
		case AppConfig.OD_DATA_ARRIVE_PLATFORM1_TIME_CODE:
			String odPerArrivePlatform1 = data.getStringExtra("odPerArrivePlatform1");
			odPerArrivePlatform1TV.setText(odPerArrivePlatform1);
			break;
		case AppConfig.OD_DATA_TRAIN_START_TIME1_CODE:
			String odPerTrainStart1 = data.getStringExtra("odPerTrainStart1");
			odPerTrainStart1TV.setText(odPerTrainStart1);
			break;
		case AppConfig.OD_DATA_GET_OFF_TIME1_CODE:
			String odPerGetOff1 = data.getStringExtra("odPerGetOff1");
			odPerGetOff1TV.setText(odPerGetOff1);
			break;
		case AppConfig.OD_DATA_ARRIVE_PLATFORM2_TIME_CODE:
			String odPerArrivePlatform2 = data.getStringExtra("odPerArrivePlatform2");
			odPerArrivePlatform2TV.setText(odPerArrivePlatform2);
			break;
		case AppConfig.OD_DATA_TRAIN_START_TIME2_CODE:
			String odPerTrainStart2 = data.getStringExtra("odPerTrainStart2");
			odPerTrainStart2TV.setText(odPerTrainStart2);
			break;
		case AppConfig.OD_DATA_GET_OFF_TIME2_CODE:
			String odPerGetOff2 = data.getStringExtra("odPerGetOff2");
			odPerGetOff2TV.setText(odPerGetOff2);
			break;
		case AppConfig.OD_DATA_GET_OFF_TIME3_CODE:
			String odPerGetOff3 = data.getStringExtra("odPerGetOff3");
			odPerGetOff3TV.setText(odPerGetOff3);
			break;
		case AppConfig.OD_DATA_ARRIVE_PLATFORM3_TIME_CODE:
			String odPerArrivePlatform3 = data.getStringExtra("odPerArrivePlatform3");
			odPerArrivePlatform3TV.setText(odPerArrivePlatform3);
			break;
		case AppConfig.OD_DATA_TRAIN_START_TIME3_CODE:
			String odPerTrainStart3 = data.getStringExtra("odPerTrainStart3");
			odPerTrainStart3TV.setText(odPerTrainStart3);
			break;
		case AppConfig.OD_DATA_GET_OUT_TIME_CODE:
			String odPerGoOutPlatForm = data.getStringExtra("odPerGoOutPlatForm");
			odPerGoOutPlatFormTV.setText(odPerGoOutPlatForm);
			break;
		}
		ODSurveyTimeRecordedNewActivity.odSurveyEntity.setID(odDatabaseID-1);
		ODSurveyDataHelper.updateTime(context, ODSurveyTimeRecordedNewActivity.odSurveyEntity);
		UIHelper.ToastMessage(context, "本组数据修改成功");
		super.onActivityResult(requestCode, resultCode, data);
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
