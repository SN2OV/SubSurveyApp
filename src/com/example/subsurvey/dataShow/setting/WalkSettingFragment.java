package com.example.subsurvey.dataShow.setting;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.AppContext;
import com.example.subsurvey.R;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.base.BaseFragment;
import com.example.subsurvey.common.SurveyUtils;
import com.example.subsurvey.dataShow.setting.TransferSettingFragment.OnClickListenerImpl;
import com.example.subsurvey.personalSetting.PSWordActivity;
import com.example.subsurvey.personalSetting.UserEntity;
import com.example.subsurvey.transferSurvey.ui.TransferSurveyDataTotalActivity;
import com.example.subsurvey.walkSurvey.ui.WSInOutLineSettingActivity;
import com.example.subsurvey.walkSurvey.ui.WSTransferLineSettingActivity;
import com.example.subsurvey.walkSurvey.ui.WSTransferLineSettingNewActivity;
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
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WalkSettingFragment extends BaseFragment {
	
	private Context context;
	private AppContext appContext;
	private UserEntity user;
	
	@InjectView(R.id.wsSettingTimeRL)
	RelativeLayout wsSettingTimeRL;
	@InjectView(R.id.wsSettingTV_time)
	TextView wsSettingTV_time;
	@InjectView(R.id.wsSettingTypeRL)
	RelativeLayout wsSettingTypeRL;
	@InjectView(R.id.wsSettingTV_type)
	TextView wsSettingTV_type;
	@InjectView(R.id.wsSettingLineRL)
	RelativeLayout wsSettingLineRL;
	@InjectView(R.id.wsSettingTV_line)
	TextView wsSettingTV_line;
	@InjectView(R.id.wsSettingDataRL)
	RelativeLayout wsSettingDataRL;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_walk_setting, null);
		ButterKnife.inject(this,view);
		context = getActivity();
		init();
		initView();
		return view;
	}

	private void init() {
		appContext = (AppContext)getActivity().getApplication();
		wsSettingTimeRL.setOnClickListener(new OnClickListenerWSSettingImpl());
		wsSettingTypeRL.setOnClickListener(new OnClickListenerWSSettingImpl());
		wsSettingDataRL.setOnClickListener(new OnClickListenerWSSettingImpl());
		wsSettingLineRL.setOnClickListener(new OnClickListenerWSSettingImpl());
	}
	
	private void initView(){
		user = appContext.getUser();
		if(user!=null){
			wsSettingTV_time.setText(user.getWsTime());
			wsSettingTV_type.setText(user.getWsType());
			SurveyUtils.onClickUpdateWalkLine(wsSettingTV_type, wsSettingTV_line, user);
		}
	}

	
	
	@Override
	public void onResume() {
		user = appContext.getUser();
		SurveyUtils.onClickUpdateWalkLine(wsSettingTV_type, wsSettingTV_line, appContext.getUser());
		WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setTravelTime(wsSettingTV_time.getText().toString());
		WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setWalkType(wsSettingTV_type.getText().toString());
		super.onResume();
	}



	public class OnClickListenerWSSettingImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent it = new Intent();
			user = appContext.getUser();
			switch(v.getId()){
			case R.id.wsSettingTimeRL:
				SurveyUtils.onClickChangeArray(wsSettingTV_time,AppConfig.wsTimeInfo);
				user.setWsTime(wsSettingTV_time.getText().toString());
				WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setTravelTime(wsSettingTV_time.getText().toString());
				break;
			case R.id.wsSettingTypeRL:
				SurveyUtils.onClickChangeArray(wsSettingTV_type,AppConfig.wsTypeInfo);
				SurveyUtils.onClickUpdateWalkLine(wsSettingTV_type, wsSettingTV_line, user);
				user.setWsType(wsSettingTV_type.getText().toString());
				WalkSurveyNewTimeRecordeActivity.walkSurveyEntity.setWalkType(wsSettingTV_type.getText().toString());
				break;
			case R.id.wsSettingDataRL:
				it.setClass(context, WalkSurveyDataTotalActivity.class);
				startActivity(it);
				break;
			case R.id.wsSettingLineRL:
				int wsTypeNo = SurveyUtils.getSurveyTypeNo(user.getWsType(), AppConfig.wsTypeInfo);
				switch(wsTypeNo){
				case AppConfig.WS_ON_TO_OFF_NO:
					it.setClass(context, WSInOutLineSettingActivity.class);
					break;
				case AppConfig.WS_TRANSF_NO:
					it.setClass(context, WSTransferLineSettingNewActivity.class);
					break;
				}
				it.putExtra("wsType", wsSettingTV_type.getText().toString());
				startActivity(it);
			}
			appContext.setUser(user);
			appContext.saveUser();
		}
	}

}
