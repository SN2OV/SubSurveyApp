package com.example.subsurvey.transferSurvey.ui;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.LogRecord;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemLongClick;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.AppContext;
import com.example.subsurvey.R;
import com.example.subsurvey.R.layout;
import com.example.subsurvey.R.menu;
import com.example.subsurvey.common.ExportHandler;
import com.example.subsurvey.common.FileUtils;
import com.example.subsurvey.common.SurveyUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.odSurvey.beans.ODSurveyEntity;
import com.example.subsurvey.odSurvey.helper.ODSurveyDataHelper;
import com.example.subsurvey.odSurvey.ui.ODSurveyDataTotalActivity;
import com.example.subsurvey.old.ODSurveySettingActivity;
import com.example.subsurvey.personalSetting.UserEntity;
import com.example.subsurvey.transferSurvey.adapter.TransferSurveyDataPerAdapter;
import com.example.subsurvey.transferSurvey.adapter.TransferSurveyTotalCountAdapter;
import com.example.subsurvey.transferSurvey.beans.TransferSurveyEntity;
import com.example.subsurvey.transferSurvey.helper.TransferSurveyDataHelper;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class TransferSurveyDataTotalActivity extends ActionBarActivity {

	@InjectView(R.id.tsSurveyDataTotalLV)
	ListView tsSurveyDataTotalLV;
	
	public TransferSurveyTotalCountAdapter tsSurveyTotalCountAdapter;
	public Context context = null;
	public List<TransferSurveyEntity> tsInfo;
	private SharedPreferences preferences;
	private int tsRowID;
	private ExportHandler handler;
	private AssetManager mAssets;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transfer_survey_data_total);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.walk_survey_data_export, menu);
		MenuItemCompat.setShowAsAction(menu.getItem(0), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
		return true;
	}
	
	@SuppressLint("SimpleDateFormat")
	public boolean onOptionsItemSelected(MenuItem item){
		System.out.println();
		switch(item.getItemId()){
		case android.R.id.home:
			Log.i("TAG", "=========选中返回键");
			this.finish();
			break;
		case R.id.walk_survey_data_export:
			Dialog dialog = new AlertDialog.Builder(TransferSurveyDataTotalActivity.this).setTitle("导出数据").setIcon(R.drawable.app_logo)
					.setMessage("确认导出数据至SD卡并将本地数据删除吗？").setPositiveButton("导出并删除", new onTSExportPositveClickListener())
					.setNeutralButton("仅删除", new onTSExportNeutralClickListener())
					.setNegativeButton("取消", new onTSExportNegativeClickListener())
					.create();
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();
		}
		return super.onOptionsItemSelected(item); 
	}

	public void init(){
		final ActionBar bar =getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);//有小箭头，图标可以点击
		bar.setDisplayShowHomeEnabled(false);//左上角不显示程序图标
		
		ButterKnife.inject(this);
		context = getApplicationContext();
		handler = new ExportHandler(context);
		initInfoArraylist();
		tsInfo = queryTSSurveyInfo(context, tsInfo);
		tsSurveyTotalCountAdapter = new TransferSurveyTotalCountAdapter(context, tsInfo);
		tsSurveyDataTotalLV.setAdapter(tsSurveyTotalCountAdapter);

		mAssets = this.getAssets();
	}

	//初始化表头
	private void initInfoArraylist(){
		tsInfo = new ArrayList<TransferSurveyEntity>();
		TransferSurveyEntity temp = new TransferSurveyEntity();
		temp.setRowId("序号");
		temp.setName("姓名");
		temp.setDate("日期");
		temp.setTimePeriod("调查时段");
		temp.setStation("车站");
		temp.setDire("调查方向");
		temp.setSurveyTime("时间");
		temp.setCountTotal("人数");
		tsInfo.add(temp);
	}

	//将查询到的信息存放到类里面
	public ArrayList<TransferSurveyEntity> queryTSSurveyInfo(Context context,List<TransferSurveyEntity> info){
		Cursor cursor = TransferSurveyDataHelper.queryTSSurveyData(context);		
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			TransferSurveyEntity temp = new TransferSurveyEntity();
			temp.setRowId(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
			temp.setName(cursor.getString(cursor.getColumnIndex(TransferSurveyEntity.TName)));
			temp.setDate(cursor.getString(cursor.getColumnIndex(TransferSurveyEntity.TDate)));
			temp.setTimePeriod(cursor.getString(cursor.getColumnIndex(TransferSurveyEntity.TTimePeriod)));
			temp.setStation(cursor.getString(cursor.getColumnIndex(TransferSurveyEntity.TStation)));
			temp.setDire(cursor.getString(cursor.getColumnIndex(TransferSurveyEntity.TDire)));
			temp.setSurveyTime(cursor.getString(cursor.getColumnIndex(TransferSurveyEntity.TSurveyTime)));
			temp.setCountTotal(cursor.getString(cursor.getColumnIndex(TransferSurveyEntity.TCount)));
			info.add(temp);
		}
		return (ArrayList<TransferSurveyEntity>)info;
	}
	
	@OnItemLongClick(R.id.tsSurveyDataTotalLV)
	public boolean onItemLongClick(final int position,final long id){
		Dialog dialog = new AlertDialog.Builder(TransferSurveyDataTotalActivity.this).setTitle("删除选中调查数据").setIcon(R.drawable.app_logo)
				.setMessage("确认删除吗？").setPositiveButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						context = getApplicationContext();
						TransferSurveyDataHelper.delTransferSurveyInfoByID(context, id);
						//删除后处理序号顺序 odInfo.size()-1因为多一行没用的表头数据
						for(int i = position+1;i<=tsInfo.size()-1;i++){
							TransferSurveyDataHelper.updateNo(context, TransferSurveyTimeRecordedNewActivity.tsEntity, i);
							tsInfo.get(i).rowId = (i-1)+"";
						}
						System.out.println(tsInfo);
						tsInfo.remove(position);
						//处理序号
						preferences = getSharedPreferences("tsRowID", MODE_WORLD_WRITEABLE);
						tsRowID = preferences.getInt("tsRowID", 1);//默认为第二个参数1
						tsRowID--;
						//通过sharedPreferences将主键保存起来
						Editor editor = preferences.edit();
						editor.putInt("tsRowID",tsRowID);
						editor.commit();
						
						UIHelper.ToastMessage(context, "删除成功");
						tsSurveyTotalCountAdapter.notifyDataSetChanged();
						tsSurveyDataTotalLV.setAdapter(tsSurveyTotalCountAdapter);
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
	private class onTSExportPositveClickListener implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {

			//即时删除
			initInfoArraylist();
			TransferSurveyTotalCountAdapter tsSurveyTotalCountAdapter = new TransferSurveyTotalCountAdapter(context,tsInfo);
			tsSurveyDataTotalLV.setAdapter(tsSurveyTotalCountAdapter);
			tsSurveyTotalCountAdapter.notifyDataSetChanged();

			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						//导出指定模板中的数据
						SurveyUtils.ExportToExcel(AppConfig.TRANSFER_SURVEY,context);
					} catch (IOException e) {
						e.printStackTrace();
					}
					//导出csv原始数据
					SurveyUtils.exportToCSV("换乘量调查",context);
					//listView即时删除
					SurveyUtils.delSurveyInfo("tsRowID", context);

					Message msg = new Message();
					msg.what = AppConfig.TRANSFER_SURVEY;
					Bundle bundle = new Bundle();
					bundle.putSerializable("tsInfo",(Serializable)tsInfo);
					msg.setData(bundle);
					handler.sendMessage(msg);
				}
			}).start();

		}
	}

	//取消
	private class onTSExportNegativeClickListener implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {
			Log.d("s","ss");
		}
	}

	//仅删除
	private class onTSExportNeutralClickListener implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {
			SurveyUtils.delSurveyInfo("tsRowID", context);
			initInfoArraylist();
			//listView即时删除
			tsSurveyTotalCountAdapter = new TransferSurveyTotalCountAdapter(context,tsInfo);
			tsSurveyDataTotalLV.setAdapter(tsSurveyTotalCountAdapter);
			tsSurveyTotalCountAdapter.notifyDataSetChanged();
		}
	}

}
