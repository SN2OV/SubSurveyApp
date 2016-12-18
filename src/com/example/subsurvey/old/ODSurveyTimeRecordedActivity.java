package com.example.subsurvey.old;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.R;
import com.example.subsurvey.R.id;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.common.StringUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.odSurvey.helper.ODSurveyDataHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ODSurveyTimeRecordedActivity extends ActionBarActivity {
	
	@InjectView(R.id.odSurveyTrainDireET)
	EditText odSurveyTrainDireET;
	@InjectView(R.id.odSurveyShiftET)
	EditText odSurveyShiftET;
	@InjectView(R.id.odSurveyGetoffStationET)
	EditText odSurveyGetoffStationET;
	@InjectView(R.id.odSurveyTransferLineET)
	EditText odSurveyTransferLineET;
	@InjectView(R.id.odSurveyTodoNextTV)
	TextView odSurveyTodoNextTV;
	@InjectView(R.id.odSurveyLastInfoTV)
	TextView odSurveyLastInfoTV;
	@InjectView(R.id.odSurveyStartImageView)
	ImageView odSurveyStartImageView;
	@InjectView(R.id.odSurveySearchImageView)
	ImageView odSurveySearchImageView;
	@InjectView(R.id.odSurveyTrainDireRL)
	RelativeLayout odSurveyTrainDireRL;
	@InjectView(R.id.odSurveyShiftRL)
	RelativeLayout odSurveyShiftRL;
	@InjectView(R.id.odSurveyGetoffStationRL)
	RelativeLayout odSurveyGetoffStationRL;
	@InjectView(R.id.odSurveyTransferLineRL)
	RelativeLayout odSurveyTransferLineRL;
	
	
	public static String[] todoHints = {"到达站台时刻","列车开行时刻","下车时刻"};
	private String pathType;
	private Context context = null;
	private int countFlag = 0,noFlag = 0,type,odRowID; //countFlag标志记录第n小组数据,noFlag记录
	private SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_odsurvey_time_recorded);
		
		context = getApplicationContext();
		Intent it = getIntent();
		pathType = it.getStringExtra("pathType");
		type = StringUtils.getSurveyTypeNo(pathType,AppConfig.pathTypeInfo);
		ButterKnife.inject(this);
		odSurveyTodoNextTV.setText("进站刷卡时刻");
		
		init();
		
	}
	
	void init(){
		
		final ActionBar bar =getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		
		odSurveyTrainDireRL.setVisibility(View.GONE);
		odSurveyShiftRL.setVisibility(View.GONE);
		odSurveyGetoffStationRL.setVisibility(View.GONE);
		odSurveyTransferLineRL.setVisibility(View.GONE);
		
		odSurveyTrainDireET.addTextChangedListener(new textChangedListener(odSurveyTrainDireET));
		odSurveyShiftET.addTextChangedListener(new textChangedListener(odSurveyShiftET));
		odSurveyGetoffStationET.addTextChangedListener(new textChangedListener(odSurveyGetoffStationET));
		odSurveyTransferLineET.addTextChangedListener(new textChangedListener(odSurveyTransferLineET));
		//默认值
		ODSurveySettingActivity.odSurveyEntity.setShift1("1");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.odsurvey_time_recorded, menu);
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
	
	//点击start开始计时
	@OnClick(R.id.odSurveyStartImageView)
	void startOnClick(){
		String time = StringUtils.getSystemTime();
		odSurveyLastInfoTV.setText(time);
		
		odSurveyTrainDireRL.setVisibility(View.GONE);
		odSurveyShiftRL.setVisibility(View.GONE);
		odSurveyGetoffStationRL.setVisibility(View.GONE);
		odSurveyTransferLineRL.setVisibility(View.GONE);
		
		/*
		 * 根据提示内容赋值
		 */
		if(odSurveyTodoNextTV.getText().equals("进站刷卡时刻"))
			ODSurveySettingActivity.odSurveyEntity.setGetinStationTime(time);
		if(odSurveyTodoNextTV.getText().equals("第1班车到达站台时刻"))
			ODSurveySettingActivity.odSurveyEntity.setArriveStationTime1(time);
		if(odSurveyTodoNextTV.getText().equals("第1班车列车开行时刻")){
			ODSurveySettingActivity.odSurveyEntity.setTrainStartingTime1(time);
			ODSurveySettingActivity.odSurveyEntity.setTrainDire1(odSurveyTrainDireET.getText().toString());
			ODSurveySettingActivity.odSurveyEntity.setShift1(odSurveyShiftET.getText().toString());
		}
		if(odSurveyTodoNextTV.getText().equals("第1班车下车时刻")){
			ODSurveySettingActivity.odSurveyEntity.setGetoffStation1(odSurveyGetoffStationET.getText().toString());
			ODSurveySettingActivity.odSurveyEntity.setGetoffStationTime1(time);
		}
		if(odSurveyTodoNextTV.getText().equals("出站刷卡时刻"))
			ODSurveySettingActivity.odSurveyEntity.setGetoutStationTime(time);
		if(odSurveyTodoNextTV.getText().equals("第2班车到达站台时刻")){
			ODSurveySettingActivity.odSurveyEntity.setArriveStationTime2(time);
			ODSurveySettingActivity.odSurveyEntity.setTransferLine1(odSurveyTransferLineET.getText().toString());
		}
		if(odSurveyTodoNextTV.getText().equals("第2班车列车开行时刻")){
			ODSurveySettingActivity.odSurveyEntity.setTrainStartingTime2(time);
			ODSurveySettingActivity.odSurveyEntity.setTrainDire2(odSurveyTrainDireET.getText().toString());
			ODSurveySettingActivity.odSurveyEntity.setShift2(odSurveyShiftET.getText().toString());
		}
		if(odSurveyTodoNextTV.getText().equals("第2班车下车时刻")){
			ODSurveySettingActivity.odSurveyEntity.setGetoffStationTime2(time);
			ODSurveySettingActivity.odSurveyEntity.setGetoffStation2(odSurveyGetoffStationET.getText().toString());
		}
		if(odSurveyTodoNextTV.getText().equals("第3班车到达站台时刻")){
			ODSurveySettingActivity.odSurveyEntity.setArriveStationTime3(time);
			ODSurveySettingActivity.odSurveyEntity.setTransferLine2(odSurveyTransferLineET.getText().toString());
		}
		if(odSurveyTodoNextTV.getText().equals("第3班车列车开行时刻")){
			ODSurveySettingActivity.odSurveyEntity.setTrainStartingTime3(time);
			ODSurveySettingActivity.odSurveyEntity.setTrainDire3(odSurveyTrainDireET.getText().toString());
			ODSurveySettingActivity.odSurveyEntity.setShift3(odSurveyShiftET.getText().toString());
		}
		if(odSurveyTodoNextTV.getText().equals("第3班车下车时刻")){
			ODSurveySettingActivity.odSurveyEntity.setGetoffStationTime3(time);
			ODSurveySettingActivity.odSurveyEntity.setGetoffStation3(odSurveyGetoffStationET.getText().toString());
		}
			
		if(noFlag==-1){
			UIHelper.ToastMessage(getApplicationContext(), "这组OD数据已调查完毕");
			//设置行号这一主键
			preferences = getSharedPreferences("odRowID", MODE_WORLD_WRITEABLE);
			odRowID = preferences.getInt("odRowID", 1);//默认为第二个参数1
			ODSurveySettingActivity.odSurveyEntity.setID(odRowID);
			ODSurveyDataHelper.insertIntoODSurveyData(context, ODSurveySettingActivity.odSurveyEntity);
			odRowID++;
			//通过sharedPreferences将主键保存起来
			Editor editor = preferences.edit();
			editor.putInt("odRowID",odRowID);
			editor.commit();
			//跳转到详情看刚才输入的数据
			Intent it = new Intent();
			it.setClass(context, ODSurveyDataPerActivity.class);
			it.putExtra("pathType", pathType);
			it.putExtra("odRowID", odRowID);
			startActivity(it);
			finish();
			return;
		}else if(noFlag==1){
			odSurveyTrainDireRL.setVisibility(View.VISIBLE);
			odSurveyShiftRL.setVisibility(View.VISIBLE);
		}else if(noFlag==2)
			odSurveyGetoffStationRL.setVisibility(View.VISIBLE);
		else if(noFlag == 3){
			switch(type){
				case 0:
					odSurveyTodoNextTV.setText("出站刷卡时刻");
					odSurveyTrainDireET.setText("");
					noFlag = -1;
					countFlag = 0;
					return;
				case 1:
				case 2:
					odSurveyTodoNextTV.setText(todoHints[0]);
					odSurveyTransferLineRL.setVisibility(View.VISIBLE);
					noFlag = 0;
					type--;
					break;
					
			}
			countFlag++;
		}
		
		odSurveyTodoNextTV.setText("第"+(countFlag+1)+"班车"+todoHints[noFlag]);
		noFlag++;
	}
	
	@OnClick(R.id.odSurveySearchImageView)
	void searchOnClick(){
		Intent it = new Intent();
		it.setClass(context, ODSurveyDataPerActivity.class);
		it.putExtra("pathType", pathType);
		startActivity(it);
	}
	
	//根据ET的id来监听多个editText输入数据
	public class textChangedListener implements TextWatcher{
		
		private EditText EditId = null;
		
		public textChangedListener(EditText id){
			EditId = id;
		}

		//监听变化立即改变
		@Override
		public void afterTextChanged(Editable s) {
			if(EditId==odSurveyTrainDireET){
				switch(countFlag){
				case 0:
					ODSurveySettingActivity.odSurveyEntity.setTrainDire1(s.toString());
					break;
				case 1:
					ODSurveySettingActivity.odSurveyEntity.setTrainDire2(s.toString());
					break;
				case 2:
					//未知bug，临时解决办法
					if(s.toString().equals("")){}
					else
						ODSurveySettingActivity.odSurveyEntity.setTrainDire3(s.toString());
					break;
				}
			}else if(EditId==odSurveyShiftET){
				switch(countFlag){
				case 0:
					ODSurveySettingActivity.odSurveyEntity.setShift1(s.toString());
					break;
				case 1:
					ODSurveySettingActivity.odSurveyEntity.setShift2(s.toString());
					break;
				case 2:
					ODSurveySettingActivity.odSurveyEntity.setShift3(s.toString());
					break;
				}
			}else if(EditId==odSurveyGetoffStationET){
				switch(countFlag){
				case 0:
					ODSurveySettingActivity.odSurveyEntity.setGetoffStation1(s.toString());
					break;
				case 1:
					ODSurveySettingActivity.odSurveyEntity.setGetoffStation2(s.toString());
					break;
				case 2:
					ODSurveySettingActivity.odSurveyEntity.setGetoffStation3(s.toString());
					break;
				}
			}else if(EditId == odSurveyTransferLineET){
				if(type==2&&countFlag==2)
					ODSurveySettingActivity.odSurveyEntity.setTransferLine2(s.toString());
				else
					ODSurveySettingActivity.odSurveyEntity.setTransferLine1(s.toString());
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			
		}
		
	}
	
}
