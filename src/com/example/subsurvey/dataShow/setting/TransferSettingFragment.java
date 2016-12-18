package com.example.subsurvey.dataShow.setting;

import java.util.Calendar;

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
import com.example.subsurvey.personalSetting.PSWordActivity;
import com.example.subsurvey.personalSetting.UserEntity;
import com.example.subsurvey.transferSurvey.ui.TransferSurveyDataTotalActivity;
import com.example.subsurvey.transferSurvey.ui.TransferSurveySettingActivity;
import com.example.subsurvey.walkSurvey.ui.WalkSurveyNewTimeRecordeActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TransferSettingFragment extends BaseFragment {

	@InjectView(R.id.tsSettingLocRL)
	RelativeLayout tsSettingLocRL;
	@InjectView(R.id.tsSettingDataRL)
	RelativeLayout tsSettingDataRL;
	@InjectView(R.id.tsSettingTV_loc)
	TextView tsSettingTV_loc;
	@InjectView(R.id.tsSettingRL_timeperiod)
	RelativeLayout tsSettingRL_timeperiod;
	@InjectView(R.id.tsSettingTV_timeperiod)
	TextView tsSettingTV_timeperiod;

	private Context context;
	private AppContext appContext;
	private UserEntity user = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_transfer_setting, null);
		ButterKnife.inject(this,view);
		context = getActivity();
		init();
		initView();
		return view;
	}
	
	public void init(){
		appContext = (AppContext)getActivity().getApplication();
		tsSettingLocRL.setOnClickListener(new OnClickListenerImpl());
		tsSettingDataRL.setOnClickListener(new OnClickListenerImpl());
		tsSettingRL_timeperiod.setOnClickListener(new OnClickListenerImpl());
		user = appContext.getUser();
		if(user!=null){
			tsSettingTV_loc.setText(user.getTsLoc());
			tsSettingTV_timeperiod.setText(user.getTsTimePeriod());
		}
	}

	private void initView(){
		user = appContext.getUser();
		if(user!=null){
			tsSettingTV_timeperiod.setText(user.getTsTimePeriod());
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	public class OnClickListenerImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent it = new Intent();
			switch(v.getId()){
			case R.id.tsSettingLocRL:
				it.putExtra("loc",tsSettingTV_loc.getText().toString());
				it.putExtra("type", AppConfig.TS_LOC_CODE);
				it.setClass(context, PSWordActivity.class);
//				startActivityForResult(it, AppConfig.TS_LOC_CODE);
				getParentFragment().startActivityForResult(it, AppConfig.TS_LOC_CODE);
				break;
			case R.id.tsSettingRL_timeperiod:
				SurveyUtils.onClickChangeArray(tsSettingTV_timeperiod,AppConfig.tsTimeInfo);
				user.setTsTimePeriod(tsSettingTV_timeperiod.getText().toString());
				appContext.setUser(user);
				appContext.saveUser();
				break;
			case R.id.tsSettingDataRL:
				it.setClass(context, TransferSurveyDataTotalActivity.class);
				startActivity(it);
				break;
			}
		}
	}

	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data == null)
			return ;
		switch(requestCode){
		case AppConfig.TS_LOC_CODE:
			String name = data.getStringExtra("loc_new");
			tsSettingTV_loc.setText(name);
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	
}
