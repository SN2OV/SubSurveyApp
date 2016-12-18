package com.example.subsurvey.old;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.AppContext;
import com.example.subsurvey.R;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.base.BaseFragment;
import com.example.subsurvey.common.StringUtils;
import com.example.subsurvey.common.SurveyUtils;
import com.example.subsurvey.personalSetting.PSWordActivity;
import com.example.subsurvey.personalSetting.UserEntity;

import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WSLineSettingActivity extends  ActionBarActivity {
	
	private Context context;
	private Intent it;
	private UserEntity user;
	private AppContext appContext;
	
	@InjectView(R.id.wsLineSettingGetOffDireRL)
	RelativeLayout wsLineSettingGetOffDireRL;
	@InjectView(R.id.wsLineSettingTV_offDire)
	TextView wsLineSettingTV_offDire;
	@InjectView(R.id.wsLineSettingGetOffLineRL)
	RelativeLayout wsLineSettingGetOffLineRL;
	@InjectView(R.id.wsLineSettingTV_offLine)
	TextView wsLineSettingTV_offLine;
	
	@InjectView(R.id.wsLineSettingGetOnDireRL)
	RelativeLayout wsLineSettingGetOnDireRL;
	@InjectView(R.id.wsLineSettingTV_getOnDire)
	TextView wsLineSettingTV_getOnDire;
	@InjectView(R.id.wsLineSettingGetOnLineRL)
	RelativeLayout wsLineSettingGetOnLineRL;
	@InjectView(R.id.wsLineSettingTV_getOnLine)
	TextView wsLineSettingTV_getOnLine;
	
	@InjectView(R.id.wsLineSettingGateLocRL)
	RelativeLayout wsLineSettingGateLocRL;
	@InjectView(R.id.wsLineSettingTV_gateLoc)
	TextView wsLineSettingTV_gateLoc;
	@InjectView(R.id.wsLineSettingGateLineRL)
	RelativeLayout wsLineSettingGateLineRL;
	@InjectView(R.id.wsLineSettingTV_gateLine)
	TextView wsLineSettingTV_gateLine;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_walk_line_setting);
		
		ButterKnife.inject(this);
		context = getApplicationContext();
		init();
		initView();
	}
	

	public void init(){
		
		appContext = (AppContext)getApplication();
		user = appContext.getUser();
		
		wsLineSettingGateLocRL.setOnClickListener(new OnWLSClickListenerImpl());
		wsLineSettingGateLineRL.setOnClickListener(new OnWLSClickListenerImpl());
		wsLineSettingGetOffDireRL.setOnClickListener(new OnWLSClickListenerImpl());
		wsLineSettingGetOffLineRL.setOnClickListener(new OnWLSClickListenerImpl());
		wsLineSettingGetOnDireRL.setOnClickListener(new OnWLSClickListenerImpl());
		wsLineSettingGetOnLineRL.setOnClickListener(new OnWLSClickListenerImpl());
	}
	
	public void initView(){
		if(user!=null){
			wsLineSettingTV_offDire.setText(user.getWsOffDire());
			wsLineSettingTV_offLine.setText(user.getWsOffLine());
			wsLineSettingTV_getOnDire.setText(user.getWsOnDire());
			wsLineSettingTV_getOnLine.setText(user.getWsOnLine());
			wsLineSettingTV_gateLoc.setText(user.getWsGateLoc());
			wsLineSettingTV_gateLine.setText(user.getWsGateLine());
		}
		
		it = getIntent();
		String wsType = it.getStringExtra("wsType");
		int wsTypeNo = SurveyUtils.getSurveyTypeNo(wsType, AppConfig.wsTypeInfo);
		switch(wsTypeNo){
		case AppConfig.WS_GETON_NO:
			wsLineSettingGetOffDireRL.setVisibility(View.GONE);
			wsLineSettingGetOffLineRL.setVisibility(View.GONE);
			break;
		case AppConfig.WS_GETOFF_NO:
			wsLineSettingGetOnDireRL.setVisibility(View.GONE);
			wsLineSettingGetOnLineRL.setVisibility(View.GONE);
			break;
		case AppConfig.WS_TRANSFER_NO:
			wsLineSettingGateLocRL.setVisibility(View.GONE);
			wsLineSettingGateLineRL.setVisibility(View.GONE);
			break;
		}
	}
	
	public class OnWLSClickListenerImpl implements OnClickListener{
		
		@Override
		public void onClick(View v) {
			Intent it = new Intent();
			it.setClass(context, PSWordActivity.class);
			switch(v.getId()){
			case R.id.wsLineSettingGateLocRL:
				it.putExtra("wsGateLoc",wsLineSettingTV_gateLoc.getText().toString());
				it.putExtra("type", AppConfig.WS_GATE_LOC_CODE);
				startActivityForResult(it, AppConfig.WS_GATE_LOC_CODE);
				break;
			case R.id.wsLineSettingGateLineRL:
				it.putExtra("wsGateLine",wsLineSettingTV_gateLine.getText().toString());
				it.putExtra("type", AppConfig.WS_GATE_LINE_CODE);
				startActivityForResult(it, AppConfig.WS_GATE_LINE_CODE);
				break;
			case R.id.wsLineSettingGetOffDireRL:
				it.putExtra("wsOffDire",wsLineSettingTV_offDire.getText().toString());
				it.putExtra("type", AppConfig.WS_OFF_DIRE_CODE);
				startActivityForResult(it, AppConfig.WS_OFF_DIRE_CODE);
				break;
			case R.id.wsLineSettingGetOffLineRL:
				it.putExtra("wsOffLine",wsLineSettingTV_offLine.getText().toString());
				it.putExtra("type", AppConfig.WS_OFF_LINE_CODE);
				startActivityForResult(it, AppConfig.WS_OFF_LINE_CODE);
				break;
			case R.id.wsLineSettingGetOnDireRL:
				it.putExtra("wsOnDire",wsLineSettingTV_getOnDire.getText().toString());
				it.putExtra("type", AppConfig.WS_ON_DIRE_CODE);
				startActivityForResult(it, AppConfig.WS_ON_DIRE_CODE);
				break;
			case R.id.wsLineSettingGetOnLineRL:
				it.putExtra("wsOnLine",wsLineSettingTV_getOnLine.getText().toString());
				it.putExtra("type", AppConfig.WS_ON_LINE_CODE);
				startActivityForResult(it, AppConfig.WS_ON_LINE_CODE);
				break;
			}
		}
		
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data == null)
			return ;
		switch(requestCode){
		case AppConfig.WS_GATE_LOC_CODE:
			String gateLoc = data.getStringExtra("wsGateLocNew");
			wsLineSettingTV_gateLoc.setText(gateLoc);
			break;
		case AppConfig.WS_GATE_LINE_CODE:
			String gateLine = data.getStringExtra("wsGateLineNew");
			wsLineSettingTV_gateLine.setText(gateLine);
			break;
		case AppConfig.WS_OFF_DIRE_CODE:
			String offDire = data.getStringExtra("wsOffDireNew");
			wsLineSettingTV_offDire.setText(offDire);
			break;
		case AppConfig.WS_OFF_LINE_CODE:
			String offLine = data.getStringExtra("wsOffLineNew");
			wsLineSettingTV_offLine.setText(offLine);
			break;
		case AppConfig.WS_ON_DIRE_CODE:
			String onDire = data.getStringExtra("wsOnDireNew");
			wsLineSettingTV_getOnDire.setText(onDire);
			break;
		case AppConfig.WS_ON_LINE_CODE:
			String onLine = data.getStringExtra("wsOnLineNew");
			wsLineSettingTV_getOnLine.setText(onLine);
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
}
