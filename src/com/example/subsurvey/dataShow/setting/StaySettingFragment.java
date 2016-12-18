package com.example.subsurvey.dataShow.setting;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.AppContext;
import com.example.subsurvey.R;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.base.BaseFragment;
import com.example.subsurvey.common.SurveyUtils;
import com.example.subsurvey.odSurvey.ui.ODWordActivity;
import com.example.subsurvey.personalSetting.UserEntity;
import com.example.subsurvey.staySurvey.ui.SSWordActivity;
import com.example.subsurvey.staySurvey.ui.SSLineSettingActivity;
import com.example.subsurvey.staySurvey.ui.StaySurveyDataTotalActivity;
import com.example.subsurvey.staySurvey.ui.StaySurveyTimeRecordedNewActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StaySettingFragment extends BaseFragment {
	
	@InjectView(R.id.staySettingCarriageLocRL)
	RelativeLayout staySettingCarriageLocRL;
	@InjectView(R.id.staySettingCarriageLocTV)
	TextView staySettingCarriageLocTV;
	@InjectView(R.id.staySettingTimePeriodRL)
	RelativeLayout staySettingTimePeriodRL;
	@InjectView(R.id.staySettingTimePeriodTV)
	TextView staySettingTimePeriodTV;
	@InjectView(R.id.staySettingLineRL)
	RelativeLayout staySettingLineRL;
	@InjectView(R.id.staySettingLineTV)
	TextView staySettingLineTV;
	@InjectView(R.id.staySettingTypeRL)
	RelativeLayout staySettingTypeRL;
	@InjectView(R.id.staySettingTypeTV)
	TextView staySettingTypeTV;
	@InjectView(R.id.staySettingDataRL)
	RelativeLayout staySettingDataRL;
	
	private Context context;
	private AppContext appContext;
	private UserEntity user;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_stay_setting, null);
		ButterKnife.inject(this,view);
		context = getActivity();
		init();
		initView();
		return view;
	}
	
	private void init() {
		appContext = (AppContext)getActivity().getApplication();
	}

	private void initView() {
		
	}
	
	@Override
	public void onResume() {
		user = appContext.getUser();
		staySettingCarriageLocTV.setText(user.getSsCarriageLoc());
		staySettingTimePeriodTV.setText(user.getSsTimePeriod());
		staySettingLineTV.setText(user.getSsFromLoc()+"-"+user.getSsToLoc());
		staySettingTypeTV.setText(user.getSsType());
		StaySurveyTimeRecordedNewActivity.staySurveyEntity.setTravelTime(staySettingTimePeriodTV.getText().toString());
		StaySurveyTimeRecordedNewActivity.staySurveyEntity.setWalkType(staySettingTypeTV.getText().toString());
		StaySurveyTimeRecordedNewActivity.staySurveyEntity.setCarriageLoc(staySettingCarriageLocTV.getText().toString());
		super.onResume();
	}
	
	@OnClick({R.id.staySettingCarriageLocRL,R.id.staySettingTimePeriodRL,R.id.staySettingLineRL,R.id.staySettingTypeRL,R.id.staySettingDataRL})
	void onStaySurveySettingClick(View v){
		Intent it = new Intent();
		user = appContext.getUser();
		switch(v.getId()){
		case R.id.staySettingCarriageLocRL:
			it.putExtra("carriageLoc",staySettingCarriageLocTV.getText().toString());
			it.putExtra("type", AppConfig.SS_DATA_SETTING_CARRIAGE_LOC_CODE);
			it.setClass(context, SSWordActivity.class);
			getParentFragment().startActivityForResult(it, AppConfig.SS_DATA_SETTING_CARRIAGE_LOC_CODE);
			break;
		case R.id.staySettingTimePeriodRL:
			SurveyUtils.onClickChangeArray(staySettingTimePeriodTV,R.array.ssTimePeriod,context);
			user.setSsTimePeriod(staySettingTimePeriodTV.getText().toString());
			StaySurveyTimeRecordedNewActivity.staySurveyEntity.setTravelTime(staySettingTimePeriodTV.getText().toString());
			appContext.setUser(user);
			appContext.saveUser();
			break;
		case R.id.staySettingLineRL:
			//待改
			it.setClass(context, SSLineSettingActivity.class);
			startActivity(it);
			break;
		case R.id.staySettingTypeRL:
			SurveyUtils.onClickChangeArray(staySettingTypeTV,R.array.ssType,context);
			StaySurveyTimeRecordedNewActivity.staySurveyEntity.setWalkType(staySettingTypeTV.getText().toString());
			user.setSsType(staySettingTypeTV.getText().toString());
			appContext.setUser(user);
			appContext.saveUser();
			break;
		case R.id.staySettingDataRL:
			it.setClass(context, StaySurveyDataTotalActivity.class);
			startActivity(it);
			break;
		}
	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data == null)
			return ;
		switch(requestCode){
		case AppConfig.SS_DATA_SETTING_CARRIAGE_LOC_CODE:
			String carriageLoc = data.getStringExtra("carriageLoc");
			staySettingCarriageLocTV.setText(carriageLoc);
			StaySurveyTimeRecordedNewActivity.staySurveyEntity.setTravelTime(carriageLoc);
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
