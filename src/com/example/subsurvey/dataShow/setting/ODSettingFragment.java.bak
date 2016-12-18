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
import com.example.subsurvey.odSurvey.ui.ODLineSettingActivity;
import com.example.subsurvey.odSurvey.ui.ODSurveyDataTotalActivity;
import com.example.subsurvey.odSurvey.ui.ODWordActivity;
import com.example.subsurvey.personalSetting.UserEntity;
import com.example.subsurvey.walkSurvey.ui.WalkSurveyDataTotalActivity;
import com.example.subsurvey.walkSurvey.ui.WalkSurveyNewTimeRecordeActivity;

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

public class ODSettingFragment extends BaseFragment {

	private Context context;
	private AppContext appContext;
	private UserEntity user;
	
	@InjectView(R.id.odSettingCardNumRL)
	RelativeLayout odSettingCardNumRL;
	@InjectView(R.id.odSettingTV_cardNum)
	TextView odSettingTV_cardNum;
	@InjectView(R.id.odSettingIDNumRL)
	RelativeLayout odSettingIDNumRL;
	@InjectView(R.id.odSettingTV_IDNum)
	TextView odSettingTV_IDNum;
	@InjectView(R.id.odSettingDistanceRL)
	RelativeLayout odSettingDistanceRL;
	@InjectView(R.id.odSettingTV_distance)
	TextView odSettingTV_distance;
	@InjectView(R.id.odSettingTypeRL)
	RelativeLayout odSettingTypeRL;
	@InjectView(R.id.odSettingTV_type)
	TextView odSettingTV_type;
	@InjectView(R.id.odSettingLineRL)
	RelativeLayout odSettingLineRL;
	@InjectView(R.id.odSettingTV_lineTV)
	TextView odSettingTV_lineTV;
	@InjectView(R.id.odSettingDataRL)
	RelativeLayout odSettingDataRL;
	@InjectView(R.id.odSettingStationCountRL)
	RelativeLayout odSettingStationCountRL;
	@InjectView(R.id.odSettingStationCountTV)
	TextView odSettingStationCountTV;
	@InjectView(R.id.odSettingTimePeriodRL)
	RelativeLayout odSettingTimePeriodRL;
	@InjectView(R.id.odSettingTimePeriodTV)
	TextView odSettingTimePeriodTV;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_od_setting, null);
		ButterKnife.inject(this,view);
		context = getActivity();
		init();
		initView();
		return view;
	}


	@Override
	public void onResume() {
		user = appContext.getUser();
		odSettingTV_cardNum.setText(user.getODCardNum());
		odSettingTV_IDNum.setText(user.getODIDNum());
		odSettingTV_distance.setText(user.getODDistance());
		odSettingTV_type.setText(user.getOdType());
		odSettingStationCountTV.setText(user.getOdStationCount());
		odSettingTimePeriodTV.setText(user.getOdTimePeriod());
		SurveyUtils.onClickUpdateODLine(odSettingTV_type, odSettingTV_lineTV, appContext.getUser());
		super.onResume();
	}
	private void init() {
		appContext = (AppContext)getActivity().getApplication();
	}
	
	private void initView() {
		
	}

	@OnClick({R.id.odSettingCardNumRL,R.id.odSettingIDNumRL,R.id.odSettingDistanceRL,
		R.id.odSettingTypeRL,R.id.odSettingLineRL,R.id.odSettingDataRL,R.id.odSettingStationCountRL,R.id.odSettingTimePeriodRL})
	void onODSettingClick(View v){
		Intent it = new Intent();
		it.setClass(context, ODWordActivity.class);
		user = appContext.getUser();
		switch(v.getId()){
		case R.id.odSettingCardNumRL:
			it.putExtra("cardNum",odSettingTV_cardNum.getText().toString());
			it.putExtra("type", AppConfig.OD_CARD_NUM_CODE);
			getParentFragment().startActivityForResult(it, AppConfig.OD_CARD_NUM_CODE);
			break;
		case R.id.odSettingIDNumRL:
			it.putExtra("IDNum",odSettingTV_IDNum.getText().toString());
			it.putExtra("type", AppConfig.OD_ID_NUM_CODE);
			getParentFragment().startActivityForResult(it, AppConfig.OD_ID_NUM_CODE);
			break;
		case R.id.odSettingDistanceRL:
			it.putExtra("distance",odSettingTV_distance.getText().toString());
			it.putExtra("type", AppConfig.OD_DISTANCE_CODE);
			getParentFragment().startActivityForResult(it, AppConfig.OD_DISTANCE_CODE);
			break;
		case R.id.odSettingStationCountRL:
			it.putExtra("stationCount",odSettingStationCountTV.getText().toString());
			it.putExtra("type", AppConfig.OD_STATION_COUNT_CODE);
			getParentFragment().startActivityForResult(it, AppConfig.OD_STATION_COUNT_CODE);
			break;
		case R.id.odSettingTimePeriodRL:
			SurveyUtils.onClickChangeArray(odSettingTimePeriodTV,R.array.odTimePeriod,context);
			user.setOdTimePeriod(odSettingTimePeriodTV.getText().toString());
			appContext.setUser(user);
			appContext.saveUser();
			break;
		case R.id.odSettingTypeRL:
			SurveyUtils.onClickChangeArray(odSettingTV_type,R.array.odType,context);
			SurveyUtils.onClickUpdateODLine(odSettingTV_type, odSettingTV_lineTV, appContext.getUser());
			user.setOdType(odSettingTV_type.getText().toString());
			appContext.setUser(user);
			appContext.saveUser();
			break;
		case R.id.odSettingLineRL:
			it.setClass(context, ODLineSettingActivity.class);
			it.putExtra("odType", odSettingTV_type.getText());
			startActivity(it);
			break;
		case R.id.odSettingDataRL:
			it.setClass(context, ODSurveyDataTotalActivity.class);
			startActivity(it);
			break;
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data == null)
			return ;
		switch(requestCode){
		case AppConfig.OD_CARD_NUM_CODE:
			String cardNum = data.getStringExtra("cardNum");
			odSettingTV_cardNum.setText(cardNum);
			break;
		case AppConfig.OD_ID_NUM_CODE:
			String IDNum = data.getStringExtra("IDNum");
			odSettingTV_IDNum.setText(IDNum);
			break;
		case AppConfig.OD_DISTANCE_CODE:
			String distance = data.getStringExtra("distance");
			odSettingTV_distance.setText(distance);
			break;
		case AppConfig.OD_STATION_COUNT_CODE:
			String stationCount = data.getStringExtra("stationCount");
			odSettingStationCountTV.setText(stationCount);
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
}
