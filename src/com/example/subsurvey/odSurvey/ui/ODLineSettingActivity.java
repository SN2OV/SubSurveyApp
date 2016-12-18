package com.example.subsurvey.odSurvey.ui;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.AppContext;
import com.example.subsurvey.R;
import com.example.subsurvey.common.SurveyUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.personalSetting.UserEntity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
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

public class ODLineSettingActivity extends ActionBarActivity {

	private Context context;
	private Intent it;
	private UserEntity user;
	private AppContext appContext;
	private String odType;
	private int typeNo;
	
	@InjectView(R.id.odlSettingDir1RL)
	RelativeLayout odlSettingDir1RL;
	@InjectView(R.id.odlSettingDir1TV)
	TextView odlSettingDir1TV;
	@InjectView(R.id.odlSettingOffStation1RL)
	RelativeLayout odlSettingOffStation1RL;
	@InjectView(R.id.odlSettingOffStation1TV)
	TextView odlSettingOffStation1TV;
	@InjectView(R.id.odlSettingTranLine2RL)
	RelativeLayout odlSettingTranLine2RL;
	@InjectView(R.id.odlSettingTranLine2TV)
	TextView odlSettingTranLine2TV;
	@InjectView(R.id.odlSettingDire2RL)
	RelativeLayout odlSettingDire2RL;
	@InjectView(R.id.odlSettingDire2TV)
	TextView odlSettingDire2TV;
	@InjectView(R.id.odlSettingOffStation2RL)
	RelativeLayout odlSettingOffStation2RL;
	@InjectView(R.id.odlSettingOffStation2TV)
	TextView odlSettingOffStation2TV;
	@InjectView(R.id.odlSettingTransLine3RL)
	RelativeLayout odlSettingTransLine3RL;
	@InjectView(R.id.odlSettingTransLine3TV)
	TextView odlSettingTransLine3TV;
	@InjectView(R.id.odlSettingDire3RL)
	RelativeLayout odlSettingDire3RL;
	@InjectView(R.id.odlSettingDire3TV)
	TextView odlSettingDire3TV;
	@InjectView(R.id.odlSettingOffStation3RL)
	RelativeLayout odlSettingOffStation3RL;
	@InjectView(R.id.odlSettingOffStation3TV)
	TextView odlSettingOffStation3TV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_odline_setting);
		ButterKnife.inject(this);
		context = getApplicationContext();
		init();
		initView();
	}

	private void init() {
		
		final ActionBar bar =getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
        bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		
		appContext = (AppContext)getApplication();
		it = getIntent();
		odType = it.getStringExtra("odType");
		typeNo = SurveyUtils.getSurveyTypeNo(odType, AppConfig.odTypeInfo);
	}

	private void initView() {
		user = appContext.getUser();
		if(user!=null){
			odlSettingDir1TV.setText(user.getOdlDire1());
			odlSettingOffStation1TV.setText(user.getOdlOffStation1());
			odlSettingTranLine2TV.setText(user.getOdlTransLine2());
			odlSettingDire2TV.setText(user.getOdlDire2());
			odlSettingOffStation2TV.setText(user.getOdlOffStation2());
			odlSettingTransLine3TV.setText(user.getOdlTransLine3());
			odlSettingDire3TV.setText(user.getOdlDire3());
			odlSettingOffStation3TV.setText(user.getOdlOffStation3());
		}
		 switch(typeNo){
		 case AppConfig.OD_TRANSFER_NONE:
			 odlSettingTranLine2RL.setVisibility(View.GONE);
			 odlSettingDire2RL.setVisibility(View.GONE);
			 odlSettingOffStation2RL.setVisibility(View.GONE);
			 break;
		 case AppConfig.OD_TRANSFER_ONCE:
			 break;
		 case AppConfig.OD_TRANSFER_TWICE:
			 odlSettingTransLine3RL.setVisibility(View.VISIBLE);
			 odlSettingDire3RL.setVisibility(View.VISIBLE);
			 odlSettingOffStation3RL.setVisibility(View.VISIBLE);
			 break;
		 }
	}

	@Override
	protected void onResume() {
		user = appContext.getUser();
		if(user!=null){
			odlSettingDir1TV.setText(user.getOdlDire1());
			odlSettingOffStation1TV.setText(user.getOdlOffStation1());
			odlSettingTranLine2TV.setText(user.getOdlTransLine2());
			odlSettingDire2TV.setText(user.getOdlDire2());
			odlSettingOffStation2TV.setText(user.getOdlOffStation2());
			odlSettingTransLine3TV.setText(user.getOdlTransLine3());
			odlSettingDire3TV.setText(user.getOdlDire3());
			odlSettingOffStation3TV.setText(user.getOdlOffStation3());
		}
		super.onResume();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.odline_setting, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data == null)
			return ;
		switch(requestCode){
		case AppConfig.OD_LINE_DIRE1_CODE:
			String odlDire1 = data.getStringExtra("odlDire1");
			odlSettingDir1TV.setText(odlDire1);
			break;
		case AppConfig.OD_LINE_OFF_STATION1_CODE:
			String odlOffStation1 = data.getStringExtra("odlOffStation1");
			odlSettingOffStation1TV.setText(odlOffStation1);
			break;
		case AppConfig.OD_LINE_TRANS_LINE2_CODE:
			String odlTransLine2 = data.getStringExtra("odlTransLine2");
			odlSettingTranLine2TV.setText(odlTransLine2);
			break;
		case AppConfig.OD_LINE_DIRE2_CODE:
			String odlDire2 = data.getStringExtra("odlDire2");
			odlSettingDire2TV.setText(odlDire2);
			break;
		case AppConfig.OD_LINE_OFF_STATION2_CODE:
			String odlOffStation2 = data.getStringExtra("odlOffStation2");
			odlSettingOffStation2TV.setText(odlOffStation2);
			break;
		case AppConfig.OD_LINE_TRANS_LINE3_CODE:
			String odlTransLine3 = data.getStringExtra("odlTransLine3");
			odlSettingTransLine3TV.setText(odlTransLine3);
			break;
		case AppConfig.OD_LINE_DIRE3_CODE:
			String odlDire3 = data.getStringExtra("odlDire3");
			odlSettingDire3TV.setText(odlDire3);
			break;
		case AppConfig.OD_LINE_OFF_STATION3_CODE:
			String odlOffStation3 = data.getStringExtra("odlOffStation3");
			odlSettingOffStation3TV.setText(odlOffStation3);
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	@OnClick({R.id.odlSettingDir1RL,R.id.odlSettingOffStation1RL,R.id.odlSettingTranLine2RL,
		R.id.odlSettingDire2RL,R.id.odlSettingOffStation2RL,R.id.odlSettingTransLine3RL,R.id.odlSettingDire3RL,R.id.odlSettingOffStation3RL})
	void onODLineClick(View view){
		Intent it = new Intent();
		it.setClass(context, ODWordActivity.class);
		switch(view.getId()){
		case R.id.odlSettingDir1RL:
			it.putExtra("odlDire1",odlSettingDir1TV.getText().toString());
			it.putExtra("type", AppConfig.OD_LINE_DIRE1_CODE);
			startActivityForResult(it, AppConfig.OD_LINE_DIRE1_CODE);
			break;
		case R.id.odlSettingOffStation1RL:
			it.putExtra("odlOffStation1",odlSettingOffStation1TV.getText().toString());
			it.putExtra("type", AppConfig.OD_LINE_OFF_STATION1_CODE);
			startActivityForResult(it, AppConfig.OD_LINE_OFF_STATION1_CODE);
			break;
		case R.id.odlSettingTranLine2RL:
			it.putExtra("odlTransLine2",odlSettingTranLine2TV.getText().toString());
			it.putExtra("type", AppConfig.OD_LINE_TRANS_LINE2_CODE);
			startActivityForResult(it, AppConfig.OD_LINE_TRANS_LINE2_CODE);
			break;
		case R.id.odlSettingDire2RL:
			it.putExtra("odlDire2",odlSettingDire2TV.getText().toString());
			it.putExtra("type", AppConfig.OD_LINE_DIRE2_CODE);
			startActivityForResult(it, AppConfig.OD_LINE_DIRE2_CODE);
			break;
		case R.id.odlSettingOffStation2RL:
			it.putExtra("odlOffStation2",odlSettingOffStation2TV.getText().toString());
			it.putExtra("type", AppConfig.OD_LINE_OFF_STATION2_CODE);
			startActivityForResult(it, AppConfig.OD_LINE_OFF_STATION2_CODE);
			break;
		case R.id.odlSettingTransLine3RL:
			it.putExtra("odlTransLine3",odlSettingTransLine3TV.getText().toString());
			it.putExtra("type", AppConfig.OD_LINE_TRANS_LINE3_CODE);
			startActivityForResult(it, AppConfig.OD_LINE_TRANS_LINE3_CODE);
			break;
		case R.id.odlSettingDire3RL:
			it.putExtra("odlDire3",odlSettingDire3TV.getText().toString());
			it.putExtra("type", AppConfig.OD_LINE_DIRE3_CODE);
			startActivityForResult(it, AppConfig.OD_LINE_DIRE3_CODE);
			break;
		case R.id.odlSettingOffStation3RL:
			it.putExtra("odlOffStation3",odlSettingOffStation3TV.getText().toString());
			it.putExtra("type", AppConfig.OD_LINE_OFF_STATION3_CODE);
			startActivityForResult(it, AppConfig.OD_LINE_OFF_STATION3_CODE);
			break;
		}
		
	}

	public boolean onOptionsItemSelected(MenuItem item){
		int typeNo;
		if(odType.equals("无换乘"))
			typeNo = AppConfig.OD_TRANSFER_NONE;
		else if(odType.equals("换乘一次"))
			typeNo = AppConfig.OD_TRANSFER_ONCE;
		else
			typeNo = AppConfig.OD_TRANSFER_TWICE;
        switch(item.getItemId()){
        case android.R.id.home:
            Log.i("TAG", "=========选中返回键");
            this.finish();
            break;
		case R.id.odline_info:
			Dialog dialog = new AlertDialog.Builder(ODLineSettingActivity.this).setTitle("路线信息").setIcon(R.drawable.app_logo)
					.setMessage(SurveyUtils.getODLineString(user,typeNo))
					.setNegativeButton("确定", new onODLineNegativeClickListener())
					.create();
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			break;
		case R.id.odline_change:
			SurveyUtils.changeODLine(user,typeNo);
			appContext.setUser(user);
			appContext.saveUser();
			initView();
			UIHelper.ToastMessage(context,"由于方向更改，请重新输入OD调查方向信息");
			break;
        }
        return super.onOptionsItemSelected(item); 
    }

	//取消
	private class onODLineNegativeClickListener implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {

		}
	}
}
