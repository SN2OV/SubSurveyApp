package com.example.subsurvey.odSurvey.ui;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemLongClick;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.R;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.common.ExportHandler;
import com.example.subsurvey.common.FileUtils;
import com.example.subsurvey.common.SurveyUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.odSurvey.adapter.ODSurveyDataAdapter;
import com.example.subsurvey.odSurvey.beans.ODSurveyEntity;
import com.example.subsurvey.odSurvey.helper.ODSurveyDataHelper;
import com.example.subsurvey.old.ODSurveySettingActivity;
import com.example.subsurvey.staySurvey.adapter.StaySurveyDataAdapter;
import com.example.subsurvey.staySurvey.helper.StaySurveyDataHelper;
import com.example.subsurvey.staySurvey.ui.StaySurveyDataTotalActivity;
import com.example.subsurvey.transferSurvey.adapter.TransferSurveyTotalCountAdapter;
import com.example.subsurvey.walkSurvey.helper.WalkSurveyDataHelper;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class ODSurveyDataTotalActivity extends ActionBarActivity {
	
	@InjectView(R.id.odSurveyDataLV)
	ListView odSurveyDataLV;
	
	public ODSurveyDataAdapter odSurveyDataAdapter;
	public Context context = null;
	public List<ODSurveyEntity> odInfo;
	private SharedPreferences preferences;
	private int odRowID;
	private ExportHandler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_odsurvey_data_total);
		setupActionBar();
		
		context = getApplicationContext();
		init();
	}

	public void init(){
		//menu
		final ActionBar bar =getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标


		initInfoArraylist();
		odInfo = queryODSurveyInfo(context,odInfo);
		odSurveyDataAdapter = new ODSurveyDataAdapter(context, odInfo);
		odSurveyDataLV.setAdapter(odSurveyDataAdapter);
		handler = new ExportHandler(context);
	}

	private void initInfoArraylist(){
		ButterKnife.inject(this);
		odInfo = new ArrayList<ODSurveyEntity>();
		ODSurveyEntity temp = new ODSurveyEntity();
		temp.setRowID("序号");
		temp.setName("姓名");
		temp.setDate("日期");
		temp.setCardNum("卡号");
		temp.setIDNo("身份证号");
		temp.setGetinStation("车站");
		temp.setGetinStationLine("线路");
		temp.setTransferCount("总换乘数");
		temp.setStationCount("总站数");
		temp.setDistanceTotal("总距离");
		temp.setWeekdayIf("是否工作日");
		temp.setPeakIf("出行时间段");
		temp.setGetinStationTime("进站刷卡时刻");
		temp.setArriveStationTime1("①到达站台时刻");
		temp.setTrainDire1("①方向");
		temp.setShift1("①班次");
		temp.setTrainStartingTime1("①列车开行时刻");
		temp.setGetoffStation1("①下车站");
		temp.setGetoffStationTime1("①下车时刻");
		temp.setTransferLine1("②换入线路");
		temp.setArriveStationTime2("②到达站台时刻");
		temp.setTrainDire2("②方向");
		temp.setShift2("②班次");
		temp.setTrainStartingTime2("②列车开行时刻");
		temp.setGetoffStation2("②下车站");
		temp.setGetoffStationTime2("②下车时刻");
		temp.setTransferLine2("③换入线路");
		temp.setArriveStationTime3("③到达站台时刻");
		temp.setTrainDire3("③方向");
		temp.setShift3("③班次");
		temp.setTrainStartingTime3("③列车开行时刻");
		temp.setGetoffStation3("③下车站");
		temp.setGetoffStationTime3("③下车时刻");
		temp.setGetoutStationTime("出站刷卡时刻");
		odInfo.add(temp);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.walk_survey_data_export, menu);
		MenuItemCompat.setShowAsAction(menu.getItem(0), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Log.i("TAG", "=========选中返回键");
			this.finish();
			break;
		case R.id.walk_survey_data_export:
			Dialog dialog = new AlertDialog.Builder(ODSurveyDataTotalActivity.this).setTitle("导出数据").setIcon(R.drawable.app_logo)
					.setMessage("确认导出数据至SD卡并将本地数据删除吗？").setPositiveButton("导出并删除", new onODExportPositveClickListener())
					.setNeutralButton("仅删除", new onODExportNeutralClickListener())
					.setNegativeButton("取消", new onODExportNegativeClickListener())
					.create();
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
		}
		return super.onOptionsItemSelected(item);
	}

	//将查询到的信息存放到类里面
	public ArrayList<ODSurveyEntity> queryODSurveyInfo(Context context,List<ODSurveyEntity> info){
		Cursor cursor = ODSurveyDataHelper.queryODSurveyData(context);		
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			ODSurveyEntity temp = new ODSurveyEntity();
			temp.setRowID(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
			temp.setName(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TName)));
			temp.setDate(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TDate)));
			temp.setCardNum(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TCardNum)));
			temp.setIDNo(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TIDNo)));
			temp.setGetinStation(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TGetinStation)));
			temp.setGetinStationLine(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TGetinStationLine)));
			//有错
			temp.setTransferCount(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TTransferCount)));
			
			temp.setStationCount(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TStationCount)));
			temp.setDistanceTotal(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TDistanceTotal)));
			temp.setWeekdayIf(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TWeekdayIf)));
			temp.setPathType(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TPathType)));
			temp.setPeakIf(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TPeakIf)));
			temp.setGetinStationTime(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TGetinStationTime)));
			temp.setArriveStationTime1(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TArriveStationTime1)));
			temp.setTrainDire1(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TTrainDire1)));
			temp.setShift1(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TShift1)));
			temp.setTrainStartingTime1(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TTrainStartingTime1)));
			temp.setGetoffStation1(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TGetoffStation1)));
			temp.setGetoffStationTime1(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TGetoffStationTime1)));
			temp.setTransferLine1(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TTransferLine1)));
			
			temp.setArriveStationTime2(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TArriveStationTime2)));
			temp.setTrainDire2(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TTrainDire2)));
			temp.setShift2(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TShift2)));
			temp.setTrainStartingTime2(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TTrainStartingTime2)));
			temp.setGetoffStation2(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TGetoffStation2)));
			temp.setGetoffStationTime2(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TGetoffStationTime2)));
			temp.setTransferLine2(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TTransferLine2)));
			
			temp.setArriveStationTime3(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TArriveStationTime3)));
			temp.setTrainDire3(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TTrainDire3)));
			temp.setShift3(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TShift3)));
			temp.setTrainStartingTime3(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TTrainStartingTime3)));
			temp.setGetoffStation3(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TGetoffStation3)));
			temp.setGetoffStationTime3(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TGetoffStationTime3)));
			temp.setGetoutStationTime(cursor.getString(cursor.getColumnIndex(ODSurveyEntity.TGetoutStationTime)));
			info.add(temp);
		}
		return (ArrayList<ODSurveyEntity>)info;
	}
	
	@OnItemLongClick(R.id.odSurveyDataLV)
	public boolean onItemLongClick(final int position,final long id){
		Dialog dialog = new AlertDialog.Builder(ODSurveyDataTotalActivity.this).setTitle("删除选中调查数据").setIcon(R.drawable.app_logo)
				.setMessage("确认删除吗？").setPositiveButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						context = getApplicationContext();
						ODSurveyDataHelper.delStaySurveyInfoByID(context, id);
						//删除后处理序号顺序 odInfo.size()-1因为多一行没用的表头数据
						for(int i = position+1;i<=odInfo.size()-1;i++){
							ODSurveyDataHelper.updateNo(context, ODSurveySettingActivity.odSurveyEntity, i);
							odInfo.get(i).rowID = (i-1)+"";
						}
						System.out.println(odInfo);
						odInfo.remove(position);
						//处理序号
						preferences = getSharedPreferences("odDatabaseID", MODE_WORLD_WRITEABLE);
						odRowID = preferences.getInt("odDatabaseID", 1);//默认为第二个参数1
						odRowID--;
						//通过sharedPreferences将主键保存起来
						Editor editor = preferences.edit();
						editor.putInt("odDatabaseID",odRowID);
						editor.commit();
						
						UIHelper.ToastMessage(context, "删除成功");
						odSurveyDataAdapter.notifyDataSetChanged();
						odSurveyDataLV.setAdapter(odSurveyDataAdapter);
					}
				}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				}).create();
		//点击dialog以外地方不消失
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
		return true;
	}

	//删除+导出
	private class onODExportPositveClickListener implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {

			//listView即时删除
			initInfoArraylist();
			odSurveyDataAdapter = new ODSurveyDataAdapter(context,odInfo);
			odSurveyDataLV.setAdapter(odSurveyDataAdapter);
			odSurveyDataAdapter.notifyDataSetChanged();

			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						//导出指定模板中的数据
						SurveyUtils.ExportToExcel(AppConfig.OD_SURVEY,context);
					} catch (IOException e) {
						e.printStackTrace();
					}
					//导出csv原始数据
					SurveyUtils.exportToCSV("OD调查", context);
					SurveyUtils.delSurveyInfo("odDatabaseID",context);

					Message msg = new Message();
					msg.what = AppConfig.OD_SURVEY;
					handler.sendMessage(msg);
				}
			}).start();
		}
	}

	//取消
	private class onODExportNegativeClickListener implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {

		}
	}

	//仅删除
	private class onODExportNeutralClickListener implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {
			SurveyUtils.delSurveyInfo("odDatabaseID", context);

			//listView即时删除
			initInfoArraylist();
			odSurveyDataAdapter = new ODSurveyDataAdapter(context,odInfo);
			odSurveyDataLV.setAdapter(odSurveyDataAdapter);
			odSurveyDataAdapter.notifyDataSetChanged();
		}
	}
}
