package com.example.subsurvey.transferSurvey.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.example.subsurvey.R;
import com.example.subsurvey.old.ODSurveySettingActivity;
import com.example.subsurvey.old.ODSurveyTimeRecordedActivity;
import com.example.subsurvey.old.TransferSurveyTimeRecordedActivity;
import com.example.subsurvey.transferSurvey.beans.TransferSurveyEntity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

public class TransferSurveySettingActivity extends ActionBarActivity {

	private DatePickerDialog.OnDateSetListener listener = null;// 用于设置生日对话框
	private Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
	private SimpleDateFormat df; // 日期格式
	private Context context = null;
	public static TransferSurveyEntity tsEntity;
	
	@InjectView (R.id.transferSurveyNameET)
	EditText transferSurveyNameET;
	@InjectView (R.id.transferSurveyTimeET)
	EditText transferSurveyTimeET;
	@InjectView (R.id.transferSurveyStationET)
	EditText transferSurveyStationET;
	@InjectView (R.id.transferSurveyDirET)
	EditText transferSurveyDirET;
	
	@InjectView (R.id.TSCalendarImageView)
	ImageView tsCalendarImageView;
	@InjectView (R.id.transferSurveyInImageView)
	ImageView transferSurveyInImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		context = getApplicationContext();
		
		setContentView(R.layout.activity_transfer_survey_setting);
		ButterKnife.inject(this);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.transfer_survey_setting, menu);
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
	
	void init(){
		final ActionBar bar =getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		
		tsEntity = new TransferSurveyEntity();
		listener = new DatePickerDialog.OnDateSetListener() { 
			@Override
			public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
				calendar.set(Calendar.YEAR, arg1);
				calendar.set(Calendar.MONTH, arg2);
				calendar.set(Calendar.DAY_OF_MONTH, arg3);
				updateDate();
			}
		};
		
		//设置editText监听
		transferSurveyNameET.addTextChangedListener(new textChangedListener(transferSurveyNameET));
		transferSurveyTimeET.addTextChangedListener(new textChangedListener(transferSurveyTimeET));
		transferSurveyStationET.addTextChangedListener(new textChangedListener(transferSurveyStationET));
		transferSurveyDirET.addTextChangedListener(new textChangedListener(transferSurveyDirET));
		
	}
	//根据ET的id来监听多个editText输入数据
	public class textChangedListener implements TextWatcher{
		
		private EditText EditId = null;
		
		public textChangedListener(EditText id){
			EditId = id;
		}

		@Override
		public void afterTextChanged(Editable s) {
			if(EditId==transferSurveyNameET){
				TransferSurveySettingActivity.tsEntity.setName(s.toString());
			}else if(EditId==transferSurveyTimeET){
				TransferSurveySettingActivity.tsEntity.setDate(s.toString());
			}else if(EditId==transferSurveyStationET){
				TransferSurveySettingActivity.tsEntity.setStation(s.toString());
			}else if(EditId==transferSurveyDirET){
				TransferSurveySettingActivity.tsEntity.setDire(s.toString());
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
	
	
	// 用于将calendar的日期转成“yyyy-MM--dd”的格式显示到界面上
		@SuppressLint("SimpleDateFormat")
		private void updateDate(){
			df = new SimpleDateFormat("yyyy-MM-dd");
			context = TransferSurveySettingActivity.this.getApplicationContext();
			transferSurveyTimeET.setText(df.format(calendar.getTime()));
			TransferSurveySettingActivity.tsEntity.setDate(df.format(calendar.getTime()));
		}
		
		@OnClick(R.id.transferSurveyInImageView)
		void onClick(){
			Intent it = new Intent();
			it.setClass(context, TransferSurveyTimeRecordedActivity.class);
			startActivity(it);
		}
	
		//监听日历
		@OnClick(R.id.TSCalendarImageView)
		//不一定是onClick,名字可以任意定义
		void onCalendarClick(){
			DatePickerDialog dialog = new DatePickerDialog(TransferSurveySettingActivity.this, listener,
					calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));  
	           DatePicker datePicker = dialog.getDatePicker(); 
	           datePicker.setMaxDate(System.currentTimeMillis());  
	           dialog.show();
		}
}
