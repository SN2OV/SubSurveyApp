package com.example.subsurvey.walkSurvey.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.R;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.common.ExportHandler;
import com.example.subsurvey.common.SurveyUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.old.StaySurveySettingActivity;
import com.example.subsurvey.old.WalkSurveySettingActivity;
import com.example.subsurvey.staySurvey.helper.StaySurveyDataHelper;
import com.example.subsurvey.transferSurvey.adapter.TransferSurveyTotalCountAdapter;
import com.example.subsurvey.walkSurvey.adapter.WalkSurveyDataAdapter;
import com.example.subsurvey.walkSurvey.beans.WalkSurveyEntity;
import com.example.subsurvey.walkSurvey.helper.WalkSurveyDataHelper;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Message;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class WalkSurveyDataTotalActivity extends ActionBarActivity {
	
	public ListView walkSurveyDataLV ;
	public List<WalkSurveyEntity> wsInfo ;
	public WalkSurveyDataAdapter adapter;
	public Context context;
	private SharedPreferences preferences;
	private int wsRowID,wsDatabaseRowID;
	private ExportHandler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_walk_survey_data_total);
	
		final ActionBar bar =getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标

		context = getApplicationContext();
		handler = new ExportHandler(context);
		walkSurveyDataLV = (ListView)findViewById(R.id.walkSurveyDataLV);

		initInfoArraylist();
		wsInfo = queryAddWalkSurveyInfo(context, wsInfo);
		adapter = new WalkSurveyDataAdapter(context,wsInfo);
		walkSurveyDataLV.setAdapter(adapter);
		walkSurveyDataLV.setOnItemLongClickListener(new onItemLongDeleImpl());
		
	}

	private void initInfoArraylist(){
		wsInfo = new ArrayList<WalkSurveyEntity>();
		WalkSurveyEntity temp = new WalkSurveyEntity();
		temp.setRowID("编号");
		temp.setName("姓名");
		temp.setDate("日期");
		temp.setStation("车站名称");
		temp.setLine("所属线路");
		temp.setWeekdayIf("是否工作日");
		temp.setTravelTime("走行时间段");
		temp.setWalkType("走行类型");
		temp.setAge("乘客年龄段");
		temp.setSex("乘客性别");
		temp.setBagageType("行李类型");
		temp.setTelConcern("手持设备");
		temp.setWalkTool("走行工具");
		temp.setSpeed("走行速度");
		temp.setGetOffLine("下车线路");
		temp.setGetOffDire("下车线路方向");
		temp.setOpenDoorTime1("列车开门时刻");
		temp.setMachineLine("闸机所属线路");
		temp.setMachineLoc("闸机位置");
		temp.setGotoPlatformTime("进站刷卡时刻");
		temp.setGetOnLine("上车线路");
		temp.setGetOnDire("上车线路方向");
		temp.setArrivePlatformTime("到达站台时刻");
		temp.setOpenDoorTime2("列车开门时刻");
		temp.setGooutPlatformTime("出站刷卡时刻");
		wsInfo.add(temp);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.walk_survey_data_export, menu);
		MenuItemCompat.setShowAsAction(menu.getItem(0), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		System.out.println();
		switch(item.getItemId()){
		case android.R.id.home:
			Log.i("TAG", "=========选中返回键");
			this.finish();
			break;
		case R.id.walk_survey_data_export:

			Dialog dialog = new AlertDialog.Builder(WalkSurveyDataTotalActivity.this).setTitle("导出数据").setIcon(R.drawable.app_logo)
					.setMessage("确认导出数据至SD卡并将本地数据删除吗？").setPositiveButton("导出并删除", new onWSExportPositveClickListener())
					.setNeutralButton("仅删除", new onWSExportNeutralClickListener())
					.setNegativeButton("取消", new onWSExportNegativeClickListener())
					.create();
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();

		}
		return super.onOptionsItemSelected(item); 
	}
	
	//查询数据库
	public ArrayList<WalkSurveyEntity> queryAddWalkSurveyInfo(Context context,List<WalkSurveyEntity> info){
		Cursor cursor = WalkSurveyDataHelper.queryWalkSurveyData(context);
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			WalkSurveyEntity temp = new WalkSurveyEntity();
			temp.setRowID(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
			temp.setName(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TName)));
			temp.setDate(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TDate)));
			temp.setStation(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TStation)));
			temp.setLine(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TLine )));
			temp.setWeekdayIf(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TWeekDayIF )));
			temp.setTravelTime(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TTravelTime )));
			temp.setWalkType(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TWalkType )));
			temp.setAge(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TAge )));
			temp.setSex(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TSex )));
			temp.setBagageType(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TBagageType )));
			temp.setTelConcern(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TTelConcern )));
			temp.setWalkTool(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TWalkTool )));
			temp.setSpeed(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TSpeed )));
			temp.setGetOffLine(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TGetOffLine )));
			temp.setGetOffDire(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TGetOffDire )));
			temp.setOpenDoorTime1(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TOpenDoorTime1 )));
			temp.setMachineLine(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TMachineLine )));
			temp.setMachineLoc(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TMachineLoc )));
			temp.setGotoPlatformTime(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TGotoPlatformTime )));
			temp.setGetOnLine(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TGetOnLine )));
			temp.setGetOnDire(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TGetOnDire )));
			temp.setArrivePlatformTime(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TArrivePlatformTime )));
			temp.setOpenDoorTime2(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TOpenDoorTime2 )));
			temp.setGooutPlatformTime(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TGooutPlatformTime )));
			info.add(temp);
		}
		return (ArrayList<WalkSurveyEntity>) info;
	}
	public class onItemLongDeleImpl implements OnItemLongClickListener{

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				final int position, final long id) {
//			UIHelper.ToastMessage(context, "选中的_id是第"+id+"个");
			Dialog dialog = new AlertDialog.Builder(WalkSurveyDataTotalActivity.this).setTitle("删除选中调查数据").setIcon(R.drawable.app_logo)
					.setMessage("确认删除吗？").setPositiveButton("确认", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							context = getApplicationContext();
							WalkSurveyDataHelper.delWalkSurveyInfoByID(context, id);
							
							//删除后处理序号顺序 ssInfo.size()-1因为多一行没用的表头数据
							for(int i = position+1;i<=wsInfo.size()-1;i++){
								WalkSurveyDataHelper.updateNo(context, WalkSurveySettingActivity.walkSurveyEntity, i);
								wsInfo.get(i).rowID = (i-1)+"";
							}
							System.out.println(wsInfo);
							wsInfo.remove(position);
							//处理序号
							preferences = getSharedPreferences("wsDatabaseRowID", MODE_WORLD_WRITEABLE);
							wsDatabaseRowID = preferences.getInt("wsDatabaseRowID", 1);//默认为第二个参数1
							wsDatabaseRowID--;
							//通过sharedPreferences将主键保存起来
							Editor editor = preferences.edit();
							editor.putInt("wsDatabaseRowID",wsDatabaseRowID);
							editor.commit();
							
							UIHelper.ToastMessage(context, "删除成功");
							adapter.notifyDataSetChanged();
							walkSurveyDataLV.setAdapter(adapter);
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
		
	}
	
	//删除+导出
	private class onWSExportPositveClickListener implements DialogInterface.OnClickListener{


		@Override
		public void onClick(DialogInterface dialog, int which) {

			//即时删除
			initInfoArraylist();
			WalkSurveyDataAdapter wsSurveyTotalAdapter = new WalkSurveyDataAdapter(context,wsInfo);
			walkSurveyDataLV.setAdapter(wsSurveyTotalAdapter);
			wsSurveyTotalAdapter.notifyDataSetChanged();

			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						//导出指定模板中的数据
						SurveyUtils.ExportToExcel(AppConfig.WALK_SURVEY,context);
					} catch (IOException e) {
						e.printStackTrace();
					}
					//导出csv原始数据
					SurveyUtils.exportToCSV("走行调查",context);
					SurveyUtils.delSurveyInfo("wsDatabaseRowID", context);

					Message msg = new Message();
					msg.what = AppConfig.WALK_SURVEY;
					Bundle bundle = new Bundle();
					bundle.putSerializable("wsInfo",(Serializable)wsInfo);
					msg.setData(bundle);
					handler.sendMessage(msg);
				}
			}).start();

//			try {
//				//导出指定模板中的数据
//				SurveyUtils.ExportToExcel(AppConfig.WALK_SURVEY,context);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//			//listView即时删除
//			initInfoArraylist();
//			adapter = new WalkSurveyDataAdapter(context,wsInfo);
//			walkSurveyDataLV.setAdapter(adapter);
//			adapter.notifyDataSetChanged();
//
//			SurveyUtils.exportToCSV("走行调查", context);
//			SurveyUtils.delSurveyInfo("wsDatabaseRowID",context);
		}
	}

	//取消
	private class onWSExportNegativeClickListener implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {

		}
	}

	//仅删除
	private class onWSExportNeutralClickListener implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {
			SurveyUtils.delSurveyInfo("wsDatabaseRowID", context);
			initInfoArraylist();
			//listView即时删除
			adapter = new WalkSurveyDataAdapter(context,wsInfo);
			walkSurveyDataLV.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
	}
    
    
}
