package com.example.subsurvey.dataShow.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemLongClick;

import com.example.subsurvey.R;
import com.example.subsurvey.base.BaseFragment;
import com.example.subsurvey.common.FileUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.odSurvey.adapter.ODSurveyDataAdapter;
import com.example.subsurvey.odSurvey.beans.ODSurveyEntity;
import com.example.subsurvey.odSurvey.helper.ODSurveyDataHelper;
import com.example.subsurvey.odSurvey.ui.ODSurveyDataTotalActivity;
import com.example.subsurvey.old.ODSurveySettingActivity;

public class ODDataFragment extends BaseFragment{

	@InjectView(R.id.odSurveyDataLV)
	ListView odSurveyDataLV;
	
	public ODSurveyDataAdapter odSurveyDataAdapter;
	public Context context = null;
	public List<ODSurveyEntity> odInfo;
	private SharedPreferences preferences;
	private int odRowID;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_odsurvey_data_total, null);
		ButterKnife.inject(this,view);
		context = getActivity();
		init();
		return view;
	}
	
	public void init(){
		odInfo = new ArrayList<ODSurveyEntity>();
		ODSurveyEntity temp = new ODSurveyEntity();
		temp.setRowID("序号");
		temp.setName("姓名");
		temp.setDate("日期");
		temp.setCardNum("卡号");
		temp.setIDNo("身份证号");
		temp.setGetinStation("车站");
		temp.setGetinStationLine("线路");
		temp.setStationCount("总站数");
		temp.setDistanceTotal("总距离");
		temp.setWeekdayIf("是否工作日");
		temp.setPeakIf("出行时间段");
		temp.setGetinStationTime("进站刷卡时刻");
		temp.setArriveStationTime1("到达站台时刻");
		temp.setTrainDire1("方向");
		temp.setShift1("班次");
		temp.setTrainStartingTime1("列车开行时刻");
		temp.setGetoffStation1("下车站");
		temp.setGetoffStationTime1("下车时刻");
		temp.setTransferLine1("换入线路");
		temp.setArriveStationTime2("到达站台时刻");
		temp.setTrainDire2("方向");
		temp.setShift2("班次");
		temp.setTrainStartingTime2("列车开行时刻");
		temp.setGetoffStation2("下车站");
		temp.setGetoffStationTime2("下车时刻");
		temp.setTransferLine2("换入线路");
		temp.setArriveStationTime3("到达站台时刻");
		temp.setTrainDire3("方向");
		temp.setShift3("班次");
		temp.setTrainStartingTime3("列车开行时刻");
		temp.setGetoffStation3("下车站");
		temp.setGetoffStationTime3("下车时刻");
		temp.setGetoutStationTime("出站刷卡时刻");
		odInfo.add(temp);
		
		odInfo = queryODSurveyInfo(context,odInfo);
		odSurveyDataAdapter = new ODSurveyDataAdapter(context, odInfo);
		odSurveyDataLV.setAdapter(odSurveyDataAdapter);
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
		Dialog dialog = new AlertDialog.Builder(getActivity()).setTitle("删除选中调查数据").setIcon(R.drawable.app_logo)
				.setMessage("确认删除吗？").setPositiveButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						context = getApplication();
						ODSurveyDataHelper.delStaySurveyInfoByID(context, id);
						//删除后处理序号顺序 odInfo.size()-1因为多一行没用的表头数据
						for(int i = position+1;i<=odInfo.size()-1;i++){
							ODSurveyDataHelper.updateNo(context, ODSurveySettingActivity.odSurveyEntity, i);
							odInfo.get(i).rowID = (i-1)+"";
						}
						System.out.println(odInfo);
						odInfo.remove(position);
						//处理序号
						preferences = getActivity().getSharedPreferences("odRowID", 0);
						odRowID = preferences.getInt("odRowID", 1);//默认为第二个参数1
						odRowID--;
						//通过sharedPreferences将主键保存起来
						Editor editor = preferences.edit();
						editor.putInt("odRowID",odRowID);
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

}
