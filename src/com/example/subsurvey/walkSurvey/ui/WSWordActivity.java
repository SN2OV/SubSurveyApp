package com.example.subsurvey.walkSurvey.ui;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.AppContext;
import com.example.subsurvey.R;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.personalSetting.UserEntity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WSWordActivity extends ActionBarActivity {

	@InjectView(R.id.ws_word_ET)
	EditText wsWordET;

	private Context context;
	private Intent it;
	private int type,wsRowIDgot;
	private AppContext appContext;
	private UserEntity user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wsword);
		init();
		initView();
	}

	private void init() {
		ButterKnife.inject(this);
		context = getApplicationContext();
		appContext = (AppContext)getApplication();
		user = appContext.getUser();
		
		it = getIntent();
		String surveyType =it.getStringExtra("type");
		
	}
	
	private void initView() {
		final ActionBar bar =getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		String value = null;
		type = it.getIntExtra("type", 0);
		switch(type){
		//走行：进站-出站
		case AppConfig.WS_INOUT_LINE_START_GLOC_CODE:
			value = it.getStringExtra("wslStartGateLoc");
			break;
		case AppConfig.WS_INOUT_LINE_GLINE_CODE:
			value = it.getStringExtra("wslGateLine");
			break;
		case AppConfig.WS_INOUT_LINE_END_GLOC_CODE:
			value = it.getStringExtra("wslEndGateLoc");
			break;
		case AppConfig.WS_INOUT_LINE_DIR_STOE_CODE:
			value = it.getStringExtra("wslDirSToE");
			break;
		case AppConfig.WS_INOUT_LINE_TRALINE_CODE:
			value = it.getStringExtra("wslTrainLine");
			break;
		case AppConfig.WS_INOUT_LINE_DIR_ETOS_CODE:
			value = it.getStringExtra("wslDirEToS");
			break;
		//换乘
		case AppConfig.WS_TRANS_LINE_OFF_DIRE_CODE:
			value = it.getStringExtra("wslOffDire");
			break;
		case AppConfig.WS_TRANS_LINE_OFF_LINE_CODE:
			value = it.getStringExtra("wslOffLine");
			break;
		case AppConfig.WS_TRANS_LINE_ON_DIRE_CODE:
			value = it.getStringExtra("wslOnDire");
			break;
		case AppConfig.WS_TRANS_LINE_ON_LINE_CODE:
			value = it.getStringExtra("wslOnLine");
			break;
//		换乘新
		case AppConfig.WS_TRANS_LINE_START_LINE_CODE:
			value = it.getStringExtra("wslStartLine");
			break;
		case AppConfig.WS_TRANS_LINE_START_LINE_START_DIRE_CODE:
			value = it.getStringExtra("wslStartLineStartDire");
			break;
		case AppConfig.WS_TRANS_LINE_START_LINE_END_DIRE_CODE:
			value = it.getStringExtra("wslStartLineEndDire");
			break;
		case AppConfig.WS_TRANS_LINE_END_LINE_CODE:
			value = it.getStringExtra("wslEndLine");
			break;
		case AppConfig.WS_TRANS_LINE_END_LINE_START_DIRE_CODE:
			value = it.getStringExtra("wslEndLineStartDire");
			break;
		case AppConfig.WS_TRANS_LINE_END_LINE_END_DIRE_CODE:
			value = it.getStringExtra("wslEndLineEndDire");
			break;


		}
		wsWordET.setText(value);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 MenuInflater inflater = getMenuInflater();
	     inflater.inflate(R.menu.data_edit, menu);
	     MenuItemCompat.setShowAsAction(menu.getItem(0), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
	     return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_finished:
			it = new Intent();
			String value = wsWordET.getText().toString();
			switch(type){
			case AppConfig.WS_INOUT_LINE_START_GLOC_CODE:
				it.putExtra("startGLoc", value);
				setResult(AppConfig.WS_INOUT_LINE_START_GLOC_CODE,it);
				user.setWslStartGLoc(value);
				break;
			case AppConfig.WS_INOUT_LINE_GLINE_CODE:
				it.putExtra("gateLine", value);
				setResult(AppConfig.WS_INOUT_LINE_GLINE_CODE,it);
				user.setWslGLine(value);
				break;
			case AppConfig.WS_INOUT_LINE_END_GLOC_CODE:
				it.putExtra("endGLoc", value);
				setResult(AppConfig.WS_INOUT_LINE_END_GLOC_CODE,it);
				user.setWslEndGLoc(value);
				break;
			case AppConfig.WS_INOUT_LINE_DIR_STOE_CODE:
				it.putExtra("direSToE", value);
				setResult(AppConfig.WS_INOUT_LINE_DIR_STOE_CODE,it);
				user.setWslDireSToE(value);
				break;
			case AppConfig.WS_INOUT_LINE_TRALINE_CODE:
				it.putExtra("trainLine", value);
				setResult(AppConfig.WS_INOUT_LINE_TRALINE_CODE,it);
				user.setWslTrainLine(value);
				break;
			case AppConfig.WS_INOUT_LINE_DIR_ETOS_CODE:
				it.putExtra("direEToS", value);
				setResult(AppConfig.WS_INOUT_LINE_DIR_ETOS_CODE,it);
				user.setWslDireEToS(value);
				break;
			//换乘路线
			case AppConfig.WS_TRANS_LINE_OFF_DIRE_CODE:
				it.putExtra("offDire", value);
				setResult(AppConfig.WS_TRANS_LINE_OFF_DIRE_CODE,it);
				user.setWslOffDire(value);
				break;
			case AppConfig.WS_TRANS_LINE_OFF_LINE_CODE:
				it.putExtra("offLine", value);
				setResult(AppConfig.WS_TRANS_LINE_OFF_LINE_CODE,it);
				user.setWslOffLine(value);
				break;
			case AppConfig.WS_TRANS_LINE_ON_DIRE_CODE:
				it.putExtra("onDire", value);
				setResult(AppConfig.WS_TRANS_LINE_ON_DIRE_CODE,it);
				user.setWslOnDire(value);
				break;
			case AppConfig.WS_TRANS_LINE_ON_LINE_CODE:
				it.putExtra("onLine", value);
				setResult(AppConfig.WS_TRANS_LINE_ON_LINE_CODE,it);
				user.setWslOnLine(value);
				break;
			//换乘新
			case AppConfig.WS_TRANS_LINE_START_LINE_CODE:
				it.putExtra("wslStartLine", value);
				setResult(AppConfig.WS_TRANS_LINE_ON_LINE_CODE,it);
				user.setWslStartLine(value);
				break;
			case AppConfig.WS_TRANS_LINE_START_LINE_START_DIRE_CODE:
				it.putExtra("wslStartLineStartDire", value);
				setResult(AppConfig.WS_TRANS_LINE_START_LINE_START_DIRE_CODE,it);
				user.setWslStartLineStartDire(value);
				break;
			case AppConfig.WS_TRANS_LINE_START_LINE_END_DIRE_CODE:
				it.putExtra("wslStartLineEndDire", value);
				setResult(AppConfig.WS_TRANS_LINE_START_LINE_END_DIRE_CODE,it);
				user.setWslStartLineEndDire(value);
				break;
			case AppConfig.WS_TRANS_LINE_END_LINE_CODE:
				it.putExtra("wslEndLine", value);
				setResult(AppConfig.WS_TRANS_LINE_END_LINE_CODE,it);
				user.setWslEndLine(value);
				break;
			case AppConfig.WS_TRANS_LINE_END_LINE_START_DIRE_CODE:
				it.putExtra("wslEndLineStartDire", value);
				setResult(AppConfig.WS_TRANS_LINE_END_LINE_START_DIRE_CODE,it);
				user.setWslEndLineStartDire(value);
				break;
			case AppConfig.WS_TRANS_LINE_END_LINE_END_DIRE_CODE:
				it.putExtra("wslEndLineEndDire", value);
				setResult(AppConfig.WS_TRANS_LINE_END_LINE_END_DIRE_CODE,it);
				user.setWslEndLineEndDire(value);
				break;


			}
			appContext.setUser(user);
			appContext.saveUser();
			finish();
			break;
		case android.R.id.home:
            Log.i("TAG", "=========选中返回键");
            this.finish();
            break;
		}
		return true;
	}
	
}
