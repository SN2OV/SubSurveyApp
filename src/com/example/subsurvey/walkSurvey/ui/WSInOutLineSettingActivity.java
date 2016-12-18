package com.example.subsurvey.walkSurvey.ui;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.AppContext;
import com.example.subsurvey.R;
import com.example.subsurvey.R.id;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.common.SurveyUtils;
import com.example.subsurvey.old.WSLineSettingActivity.OnWLSClickListenerImpl;
import com.example.subsurvey.personalSetting.UserEntity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

public class WSInOutLineSettingActivity extends ActionBarActivity {

	private Context context;
	private Intent it;
	private UserEntity user;
	private AppContext appContext;
	
	@InjectView(R.id.wsLineStartGateLocRL)
	RelativeLayout wsLineStartGateLocRL;
	@InjectView(R.id.wsLineStartGateLocTV)
	TextView wsLineStartGateLocTV;
	@InjectView(R.id.wsLineGateLineRL)
	RelativeLayout wsLineGateLineRL;
	@InjectView(R.id.wsLineGateLineTV)
	TextView wsLineGateLineTV;
	@InjectView(R.id.wsLineEndGateLocRL)
	RelativeLayout wsLineEndGateLocRL;
	@InjectView(R.id.wsLineEndGateLocTV)
	TextView wsLineEndGateLocTV;
	@InjectView(R.id.wsLineDirStartToEndRL)
	RelativeLayout wsLineDirStartToEndRL;
	@InjectView(R.id.wsLineDirStartToEndTV)
	TextView wsLineDirStartToEndTV;
	@InjectView(R.id.wsLineTrainLineRL)
	RelativeLayout wsLineTrainLineRL;
	@InjectView(R.id.wsLineTrainLineTV)
	TextView wsLineTrainLineTV;
	@InjectView(R.id.wsLineDirEndToStartRL)
	RelativeLayout wsLineDirEndToStartRL;
	@InjectView(R.id.wsLineDirEndToStartTV)
	TextView wsLineDirEndToStartTV;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wsin_out_line_setting);
		
		ButterKnife.inject(this);
		context = getApplicationContext();
		init();
		initView();
	}

	public void init(){
		appContext = (AppContext)getApplication();
		user = appContext.getUser();
		it = getIntent();
		final ActionBar bar =getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
        bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
	}
	
	public void initView(){
		if(user!=null){
			wsLineStartGateLocTV.setText(user.getWslStartGLoc());
			wsLineGateLineTV.setText(user.getWslGLine());
			wsLineEndGateLocTV.setText(user.getWslEndGLoc());
			wsLineDirStartToEndTV.setText(user.getWslDireSToE());
			wsLineTrainLineTV.setText(user.getWslTrainLine());
			wsLineDirEndToStartTV.setText(user.getWslDireEToS());
		}
		
	}
	
	@OnClick({R.id.wsLineStartGateLocRL,R.id.wsLineGateLineRL,R.id.wsLineEndGateLocRL,
		R.id.wsLineDirStartToEndRL,R.id.wsLineTrainLineRL,R.id.wsLineDirEndToStartRL})
	void onWsLineClick(View view){
		Intent it = new Intent();
		it.setClass(context, WSWordActivity.class);
		switch(view.getId()){
		case R.id.wsLineStartGateLocRL:
			it.putExtra("wslStartGateLoc",wsLineStartGateLocTV.getText().toString());
			it.putExtra("type", AppConfig.WS_INOUT_LINE_START_GLOC_CODE);
			startActivityForResult(it, AppConfig.WS_INOUT_LINE_START_GLOC_CODE);
			break;
		case R.id.wsLineGateLineRL:
			it.putExtra("wslGateLine",wsLineGateLineTV.getText().toString());
			it.putExtra("type", AppConfig.WS_INOUT_LINE_GLINE_CODE);
			startActivityForResult(it, AppConfig.WS_INOUT_LINE_GLINE_CODE);
			break;
		case R.id.wsLineEndGateLocRL:
			it.putExtra("wslEndGateLoc",wsLineEndGateLocTV.getText().toString());
			it.putExtra("type", AppConfig.WS_INOUT_LINE_END_GLOC_CODE);
			startActivityForResult(it, AppConfig.WS_INOUT_LINE_END_GLOC_CODE);
			break;
		case R.id.wsLineDirStartToEndRL:
			it.putExtra("wslDirSToE",wsLineDirStartToEndTV.getText().toString());
			it.putExtra("type", AppConfig.WS_INOUT_LINE_DIR_STOE_CODE);
			startActivityForResult(it, AppConfig.WS_INOUT_LINE_DIR_STOE_CODE);
			break;
		case R.id.wsLineTrainLineRL:
			it.putExtra("wslTrainLine",wsLineTrainLineTV.getText().toString());
			it.putExtra("type", AppConfig.WS_INOUT_LINE_TRALINE_CODE);
			startActivityForResult(it, AppConfig.WS_INOUT_LINE_TRALINE_CODE);
			break;
		case R.id.wsLineDirEndToStartRL:
			it.putExtra("wslDirEToS",wsLineDirEndToStartTV.getText().toString());
			it.putExtra("type", AppConfig.WS_INOUT_LINE_DIR_ETOS_CODE);
			startActivityForResult(it, AppConfig.WS_INOUT_LINE_DIR_ETOS_CODE);
			break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wsin_out_line_setting, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data == null)
			return ;
		switch(requestCode){
		case AppConfig.WS_INOUT_LINE_START_GLOC_CODE:
			String startGLoc = data.getStringExtra("startGLoc");
			wsLineStartGateLocTV.setText(startGLoc);
			break;
		case AppConfig.WS_INOUT_LINE_GLINE_CODE:
			String gateLine = data.getStringExtra("gateLine");
			wsLineGateLineTV.setText(gateLine);
			break;
		case AppConfig.WS_INOUT_LINE_END_GLOC_CODE:
			String endGLoc = data.getStringExtra("endGLoc");
			wsLineEndGateLocTV.setText(endGLoc);
			break;
		case AppConfig.WS_INOUT_LINE_DIR_STOE_CODE:
			String direSToE = data.getStringExtra("direSToE");
			wsLineDirStartToEndTV.setText(direSToE);
			break;
		case AppConfig.WS_INOUT_LINE_TRALINE_CODE:
			String trainLine = data.getStringExtra("trainLine");
			wsLineTrainLineTV.setText(trainLine);
			break;
		case AppConfig.WS_INOUT_LINE_DIR_ETOS_CODE:
			String direEToS = data.getStringExtra("direEToS");
			wsLineDirEndToStartTV.setText(direEToS);
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case android.R.id.home:
			Log.i("TAG", "=========选中返回键");
			this.finish();
			break;
		case id.wsline_inout_info:
			Dialog dialog = new AlertDialog.Builder(WSInOutLineSettingActivity.this).setTitle("路线信息").setIcon(R.drawable.app_logo)
					.setMessage(SurveyUtils.getWSLineString(user,AppConfig.WS_ON_TO_OFF_NO))
					.setNegativeButton("确定", new onWSLineNegativeClickListener())
					.create();
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
			break;
		case id.wsline_inout_change:
			SurveyUtils.changeWSLine(user,AppConfig.WS_ON_TO_OFF_NO);
			appContext.setUser(user);
			appContext.saveUser();
			initView();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	//取消
	private class onWSLineNegativeClickListener implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {

		}
	}
	
}
