package com.example.subsurvey.old;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import com.example.subsurvey.R;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.common.StringUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.staySurvey.beans.StaySurveyEntity;
import com.example.subsurvey.walkSurvey.beans.WalkSurveyEntity;

import android.os.Bundle;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.AdapterView.OnItemSelectedListener;

public class StaySurveySettingActivity extends ActionBarActivity {
	
	private ImageView stayCalendarImageView,staySurveyJumpIV = null;
	private EditText staySurveyNameET,staySurveyStationET,calendarEditText,staySurveyCarriageNoET,staySurveyDoorNoET,staySurveySubNoET,staySurveySubDireET = null;
	private Switch staySurveyWeekdaySwitch;
	private RadioButton TempRadioButton;
	private RadioGroup staySurveyTimeRadioGroup;
	private DatePickerDialog.OnDateSetListener listener = null;// 用于设置生日对话框
	private Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
	private SimpleDateFormat df; // 日期格式
	private Context context = null;
	private Spinner typeSpinner;
	private String surveyType;//走行类型
	public static String[] typeInfo = {"进站","换乘"};
	//定义走行调查实体，用于将各种数据保存
	public static StaySurveyEntity staySurveyEntity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stay_survey_setting);
		
		//这两行+onOptionsItemSelected+Manifest的android:theme组成了屏幕左上角的返回按钮
		final ActionBar bar =getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
				
		context = getApplicationContext();
		
		StaySurveySettingActivity.staySurveyEntity = new StaySurveyEntity();
		staySurveyNameET = (EditText)findViewById(R.id.staySurveyNameET);
		staySurveyNameET.addTextChangedListener(new textChangedListener(staySurveyNameET));
		staySurveyStationET = (EditText)findViewById(R.id.staySurveyStationET);
		staySurveyStationET.addTextChangedListener(new textChangedListener(staySurveyStationET));
		calendarEditText = (EditText)findViewById(R.id.staySurveyCalendarET);
		calendarEditText.addTextChangedListener(new textChangedListener(calendarEditText));
		staySurveyCarriageNoET = (EditText)findViewById(R.id.staySurveyCarriageNoET);
		staySurveyCarriageNoET.addTextChangedListener(new textChangedListener(staySurveyCarriageNoET));
		staySurveyDoorNoET = (EditText)findViewById(R.id.staySurveyDoorNoET);
		staySurveyDoorNoET.addTextChangedListener(new textChangedListener(staySurveyDoorNoET));
		staySurveySubNoET = (EditText)findViewById(R.id.staySurveySubNoET);
		staySurveySubNoET.addTextChangedListener(new textChangedListener(staySurveySubNoET));
		staySurveySubDireET = (EditText)findViewById(R.id.staySurveySubDireET);
		staySurveySubDireET.addTextChangedListener(new textChangedListener(staySurveySubDireET));
		
		staySurveyWeekdaySwitch =(Switch)findViewById(R.id.staySurveyWeekdaySwitch);
		staySurveyTimeRadioGroup = (RadioGroup)findViewById(R.id.staySurveyTimeRadioGroup);
		stayCalendarImageView = (ImageView)findViewById(R.id.staySurveyCalendarImageView);
		stayCalendarImageView.setOnClickListener(new calendarImageView());
		staySurveyJumpIV = (ImageView)findViewById(R.id.staySurveyJumpIV);
		typeSpinner = (Spinner)findViewById(R.id.staySurveyTypeSpinner);
		staySurveyJumpIV = (ImageView)findViewById(R.id.staySurveyJumpIV);
		staySurveyJumpIV.setOnClickListener(new onClickListenerGotoStayTimeRecorddedImpl());
		
		//配置调查类型下拉框
		ArrayAdapter<CharSequence> adapterType = null;  
		adapterType = new ArrayAdapter<CharSequence>(this,  
							android.R.layout.simple_spinner_dropdown_item, typeInfo);  
		//设置下拉框的数据适配器adapterType
		typeSpinner.setAdapter(adapterType); 
		//监听spinner
		typeSpinner.setOnItemSelectedListener(new OnItemWalkTypeSelectedListener());
		if(staySurveyWeekdaySwitch.isChecked())
			StaySurveySettingActivity.staySurveyEntity.setWeekdayIf("是");
		else
			StaySurveySettingActivity.staySurveyEntity.setWeekdayIf("否");
		//获取radioGroup
		TempRadioButton = (RadioButton)findViewById(staySurveyTimeRadioGroup.getCheckedRadioButtonId());
		StaySurveySettingActivity.staySurveyEntity.setTravelTime(TempRadioButton.getText().toString());
				
		listener = new DatePickerDialog.OnDateSetListener() { 
			@Override
			public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
				calendar.set(Calendar.YEAR, arg1);
				calendar.set(Calendar.MONTH, arg2);
				calendar.set(Calendar.DAY_OF_MONTH, arg3);
				updateDate();
			}
		};
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.stay_survey_setting, menu);
		return true;
	}
	
	//监听日历
	public class calendarImageView implements OnClickListener{

		@Override
		public void onClick(View v) {
			DatePickerDialog dialog = new DatePickerDialog(StaySurveySettingActivity.this, listener,
					calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH));  
	           DatePicker datePicker = dialog.getDatePicker(); 
	           datePicker.setMaxDate(System.currentTimeMillis());  
	           dialog.show();
		}
	}
		
	// 用于将calendar的日期转成“yyyy-MM--dd”的格式显示到界面上
	private void updateDate(){
		df = new SimpleDateFormat("yyyy-MM-dd");
		context = StaySurveySettingActivity.this.getApplicationContext();
		calendarEditText.setText(df.format(calendar.getTime()));
		StaySurveySettingActivity.staySurveyEntity.setDate(df.format(calendar.getTime()));
	}
		
	//跳转
	public class onClickListenerGotoStayTimeRecorddedImpl implements OnClickListener{
		@Override
		public void onClick(View v) {
			boolean isInfoEmpty = StringUtils.isEmpty(staySurveyNameET.getText().toString())||StringUtils.isEmpty(staySurveyStationET.getText().toString())
					||StringUtils.isEmpty(staySurveyCarriageNoET.getText().toString())||StringUtils.isEmpty(staySurveyDoorNoET.getText().toString())
					||StringUtils.isEmpty(staySurveySubDireET.getText().toString())||StringUtils.isEmpty(staySurveySubNoET.getText().toString())||StringUtils.isEmpty(calendarEditText.getText().toString());
			if(isInfoEmpty){
				UIHelper.ToastMessage(context, "请将所有数据补充完整");
				return;
			}
				Intent it = new Intent();
				it.setClass(context, StaySurveyTimeRecordedActivity.class);
				//将走行类型传过去
				it.putExtra("surveyType", surveyType);
				startActivity(it);
		}
	}
		
	//乘客走行类型
	public class OnItemWalkTypeSelectedListener implements OnItemSelectedListener{
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			surveyType = typeInfo[arg2];
			StaySurveySettingActivity.staySurveyEntity.setWalkType(surveyType);
					
			System.out.println("--------"+surveyType+"----------");
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {

		}
	}
				
	//根据ET的id来监听多个editText输入数据
		public class textChangedListener implements TextWatcher{
					
		private EditText EditId = null;
					
		public textChangedListener(EditText id){
			EditId = id;
		}

		@Override
		public void afterTextChanged(Editable s) {
			if(EditId==staySurveyNameET){
				StaySurveySettingActivity.staySurveyEntity.setName(s.toString());
			}else if(EditId==staySurveyStationET){
				StaySurveySettingActivity.staySurveyEntity.setStation(s.toString());
			}else if(EditId==staySurveyCarriageNoET){
				StaySurveySettingActivity.staySurveyEntity.setCarriageNo(s.toString());
			}else if(EditId==staySurveyDoorNoET){
				StaySurveySettingActivity.staySurveyEntity.setDoorNo(s.toString());
			}else if(EditId==staySurveySubNoET){
				StaySurveySettingActivity.staySurveyEntity.setSubNo(s.toString());
			}else if(EditId==staySurveySubDireET){
				StaySurveySettingActivity.staySurveyEntity.setSubDire(s.toString());
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
				
	//监听所有的CompoundButton控件          doClick在xml的 android:onClick="doClick"配置
	public void doClick(View view){
		boolean isChecked =((CompoundButton)view).isChecked();
		switch(view.getId()){
		case R.id.staySurveyWeekdaySwitch:
			if(isChecked)
				StaySurveySettingActivity.staySurveyEntity.setWeekdayIf("是");
			else
				StaySurveySettingActivity.staySurveyEntity.setWeekdayIf("否");
			break;
		case R.id.staySurveyTimeFrom7Radio:
			if(isChecked)
				StaySurveySettingActivity.staySurveyEntity.setTravelTime("07:00~09:00");
			break;
		case R.id.staySurveyTimeFrom10Radio:
			if(isChecked)
				StaySurveySettingActivity.staySurveyEntity.setTravelTime("10:00~12:00");
			break;
		default:
			break;
		}
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
