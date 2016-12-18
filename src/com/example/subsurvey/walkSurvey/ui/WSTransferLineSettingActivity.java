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
import com.example.subsurvey.base.BaseActivity;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.personalSetting.UserEntity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WSTransferLineSettingActivity extends BaseActivity {

	@InjectView(R.id.wsLineGetOffDireRL)
	RelativeLayout wsLineGetOffDireRL;
	@InjectView(R.id.wsLineGetOffDireTV)
	TextView wsLineGetOffDireTV;
	@InjectView(R.id.wsLineGetOffLineRL)
	RelativeLayout wsLineGetOffLineRL;
	@InjectView(R.id.wsLineGetOffLineTV)
	TextView wsLineGetOffLineTV;
	@InjectView(R.id.wsLineGetOnDireRL)
	RelativeLayout wsLineGetOnDireRL;
	@InjectView(R.id.wsLineGetOnDireTV)
	TextView wsLineGetOnDireTV;
    @InjectView(R.id.wsLineGetOnLineRL)
    RelativeLayout wsLineGetOnLineRL;
    @InjectView(R.id.wsLineGetOnLineTV)
    TextView wsLineGetOnLineTV;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wstransfer_line_setting);
		ButterKnife.inject(this);
		context = getApplicationContext();
		init();
		initView();
	}

	private void init() {
		appContext = (AppContext)getApplication();
		user = appContext.getUser();
		final ActionBar bar =getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
        bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
	}

	private void initView() {
		if(user!=null){
			wsLineGetOffDireTV.setText(user.getWslOffDire());
			wsLineGetOffLineTV.setText(user.getWslOffLine());
			wsLineGetOnDireTV.setText(user.getWslOnDire());
			wsLineGetOnLineTV.setText(user.getWslOnLine());
		}
		
	}

	@OnClick({R.id.wsLineGetOffDireRL,R.id.wsLineGetOffLineRL,R.id.wsLineGetOnDireRL,R.id.wsLineGetOnLineRL})
	void onWSTLineClick(View view){
		Intent it = new Intent();
		it.setClass(context, WSWordActivity.class);
		switch(view.getId()){
		case R.id.wsLineGetOffDireRL:
			it.putExtra("wslOffDire",wsLineGetOffDireTV.getText().toString());
			it.putExtra("type", AppConfig.WS_TRANS_LINE_OFF_DIRE_CODE);
			startActivityForResult(it, AppConfig.WS_TRANS_LINE_OFF_DIRE_CODE);
			break;
		case R.id.wsLineGetOffLineRL:
			it.putExtra("wslOffLine",wsLineGetOffLineTV.getText().toString());
			it.putExtra("type", AppConfig.WS_TRANS_LINE_OFF_LINE_CODE);
			startActivityForResult(it, AppConfig.WS_TRANS_LINE_OFF_LINE_CODE);
			break;
		case R.id.wsLineGetOnDireRL:
			it.putExtra("wslOnDire",wsLineGetOnDireTV.getText().toString());
			it.putExtra("type", AppConfig.WS_TRANS_LINE_ON_DIRE_CODE);
			startActivityForResult(it, AppConfig.WS_TRANS_LINE_ON_DIRE_CODE);
			break;
		case R.id.wsLineGetOnLineRL:
			it.putExtra("wslOnLine",wsLineGetOnLineTV.getText().toString());
			it.putExtra("type", AppConfig.WS_TRANS_LINE_ON_LINE_CODE);
			startActivityForResult(it, AppConfig.WS_TRANS_LINE_ON_LINE_CODE);
			break;
		}
	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data == null)
			return ;
		switch(requestCode){
			case AppConfig.WS_TRANS_LINE_OFF_DIRE_CODE:
				String offDire = data.getStringExtra("offDire");
				wsLineGetOffDireTV.setText(offDire);
				break;
			case AppConfig.WS_TRANS_LINE_OFF_LINE_CODE:
				String offLine = data.getStringExtra("offLine");
				wsLineGetOffLineTV.setText(offLine);
				break;
			case AppConfig.WS_TRANS_LINE_ON_DIRE_CODE:
				String onDire = data.getStringExtra("onDire");
				wsLineGetOnDireTV.setText(onDire);
				break;
			case AppConfig.WS_TRANS_LINE_ON_LINE_CODE:
				String onLine = data.getStringExtra("onLine");
				wsLineGetOnLineTV.setText(onLine);
				break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.wstransfer_line_setting, menu);
		return true;
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
