package com.example.subsurvey.dataShow.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;

import com.example.subsurvey.R;
import com.example.subsurvey.base.BaseFragment;
import com.example.subsurvey.common.StringUtils;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.old.StaySurveySettingActivity;
import com.example.subsurvey.staySurvey.adapter.StaySurveyDataAdapter;
import com.example.subsurvey.staySurvey.beans.StaySurveyEntity;
import com.example.subsurvey.staySurvey.helper.StaySurveyDataHelper;
import com.example.subsurvey.staySurvey.ui.StaySurveyDataTotalActivity;
import com.example.subsurvey.staySurvey.ui.StaySurveyDataTotalActivity.onItemLongDeleImpl;

public class StaySurveyDataFragment extends BaseFragment{

	public ListView staySurveyDataLV ;
	public List<StaySurveyEntity> ssInfo ;
	public StaySurveyDataAdapter adapter;
	public Context context;
	private SharedPreferences preferences;
	private int ssRowID;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_stay_survey_data_total, null);
		
		staySurveyDataLV = (ListView)view.findViewById(R.id.staySurveyDataLV);
		ssInfo = new ArrayList<StaySurveyEntity>();
		StaySurveyEntity temp = new StaySurveyEntity();
		temp.setRowID("编号");
		temp.setName("调查员");
		temp.setDate("调查日期");
		temp.setStation("车站名称");
		temp.setCarriageLoc("车厢位置");
		temp.setSurveyLoc("调查站台位置");
		temp.setWeekdayIf("是否工作日");
		temp.setTravelTime("出行时间段");
		temp.setWalkType("调查类型");
		temp.setSex("乘客性别");
		temp.setAge("年龄");
		temp.setBagageType("行李类型");
		temp.setGoinLoc("进站闸机位置");
		temp.setGetoffLoc("换乘下车位置");
		temp.setGointoStationTime("进站刷卡时刻");
		temp.setArriveStationTime("到达站台时刻");
		temp.setGetoffTime("换乘下车时刻");
		temp.setStartLineNum("起始排队人数");
		temp.setTrainStartTime1("第一班车开车时刻");
		temp.setGetOnNum1("上车人数1");
		temp.setGetOffNum1("下车人数1");
		temp.setGetOnNum2("上车人数2");
		temp.setGetOffNum2("下车人数2");
		temp.setTrainStartTime2("第二班车开车时刻");
		temp.setGetOnNum3("上车人数3");
		temp.setGetOffNum3("下车人数3");
		temp.setTrainStartTime3("第三班车开车时刻");
		temp.setGetOnNum4("上车人数4");
		temp.setGetOffNum4("下车人数4");
		temp.setTrainStartTime4("第四班车开车时刻");
		temp.setGetOnNum5("上车人数5");
		temp.setGetOffNum5("下车人数5");
		temp.setTrainStartTime5("第五班车开车时刻");
		temp.setGetOnNum6("上车人数6");
		temp.setGetOffNum6("下车人数6");
		temp.setTrainStartTime6("第六班车开车时刻");
		ssInfo.add(temp);
		
		ssInfo = queryStaySurveyInfo(context, ssInfo);
		adapter = new StaySurveyDataAdapter(context, ssInfo);
		staySurveyDataLV.setAdapter(adapter);
		staySurveyDataLV.setOnItemLongClickListener(new onItemLongDeleImpl());
		return view;
		
	}

	//将查询到的信息存放到类里面
	public ArrayList<StaySurveyEntity> queryStaySurveyInfo(Context context,List<StaySurveyEntity> info){
		Cursor cursor = StaySurveyDataHelper.queryStaySurveyData(context);
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			StaySurveyEntity temp = new StaySurveyEntity();
			temp.setRowID(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
			temp.setName(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TName)));
			temp.setDate(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TDate)));
			temp.setStation(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TStation)));
			temp.setCarriageLoc(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TCarriageLoc)));
			temp.setSurveyLoc(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TPlatformLoc)));
			temp.setWeekdayIf(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TWeekDayIF)));
			temp.setTravelTime(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TTravelTime)));
			temp.setWalkType(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TSurveyType)));
			temp.setSex(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TSex)));
			temp.setAge(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TAge)));
			temp.setBagageType(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TBagageType)));
			temp.setGoinLoc(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TGotoPlatformLoc)));
			temp.setGetoffLoc(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TTransferLoc)));
			temp.setGointoStationTime(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TGotoPlatformTime)));
			temp.setArriveStationTime(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TArrivePlatformTime)));
			temp.setGetoffTime(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TGetoffTransferTime)));
			temp.setStartLineNum(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TOrignLineNum)));
			temp.setTrainStartTime1(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TTrainStartNO1)));
			temp.setGetOnNum1(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TGetonNumNO1)));
			temp.setGetOffNum1(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TGetoffNumNO1)));
			temp.setTrainStartTime2(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TTrainStartNO2)));
			temp.setGetOnNum2(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TGetonNumNO2)));
			temp.setGetOffNum2(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TGetoffNumNO2)));
			temp.setTrainStartTime3(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TTrainStartNO3)));
			temp.setGetOnNum3(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TGetonNumNO3)));
			temp.setGetOffNum3(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TGetoffNumNO3)));
			temp.setTrainStartTime4(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TTrainStartNO4)));
			temp.setGetOnNum4(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TGetonNumNO4)));
			temp.setGetOffNum4(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TGetoffNumNO4)));
			temp.setTrainStartTime5(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TTrainStartNO5)));
			temp.setGetOnNum5(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TGetonNumNO5)));
			temp.setGetOffNum5(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TGetoffNumNO5)));
			temp.setTrainStartTime6(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TTrainStartNO6)));
			temp.setGetOnNum6(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TGetonNumNO6)));
			temp.setGetOffNum6(cursor.getString(cursor.getColumnIndexOrThrow(StaySurveyEntity.TGetoffNumNO6)));
			info.add(temp);
		}
		return (ArrayList<StaySurveyEntity>) info;
	}

	public class onItemLongDeleImpl implements OnItemLongClickListener{

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				final int position, final long id) {
			Dialog dialog = new AlertDialog.Builder(getActivity()).setTitle("删除选中调查数据").setIcon(R.drawable.app_logo)
					.setMessage("确认删除吗？").setPositiveButton("确认", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							context = getApplication();
							StaySurveyDataHelper.delStaySurveyInfoByID(context, id);
							
							//删除后处理序号顺序 ssInfo.size()-1因为多一行没用的表头数据
							for(int i = position+1;i<=ssInfo.size()-1;i++){
								StaySurveyDataHelper.updateNo(context, StaySurveySettingActivity.staySurveyEntity, i);
								ssInfo.get(i).rowID = (i-1)+"";
							}
							System.out.println(ssInfo);
							ssInfo.remove(position);
							//处理序号
							preferences = getActivity().getSharedPreferences("ssRowID", 0);
							ssRowID = preferences.getInt("ssRowID", 1);//默认为第二个参数1
							ssRowID--;
							//通过sharedPreferences将主键保存起来
							Editor editor = preferences.edit();
							editor.putInt("ssRowID",ssRowID);
							editor.commit();
							
							UIHelper.ToastMessage(context, "删除成功");
							adapter.notifyDataSetChanged();
							staySurveyDataLV.setAdapter(adapter);
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
	
	//数据导出为csv
    public void ExportToCSV(Cursor c, String fileName) {  
        
        int rowCount = 0;  
        int colCount = 0;  
        FileWriter fw;  
        BufferedWriter bfw;  
        File sdCardDir = new File(Environment.getExternalStorageDirectory()+"/客流调查") ; 
        if (!sdCardDir.exists()) {
        	sdCardDir.mkdirs();
        }
        File saveFile = new File(sdCardDir, fileName);  
        try {  
            rowCount = c.getCount();  
            colCount = c.getColumnCount();  
            fw = new FileWriter(saveFile);
            bfw = new BufferedWriter(fw);  
            if (rowCount > 0) {  
                c.moveToFirst();  
                // 写入表头  
                for (int i = 0; i < colCount; i++) {  
                    if (i != colCount - 1)  
                    	bfw.write(c.getColumnName(i) + ','); 
                    else  
                       bfw.write(c.getColumnName(i));  
                }  
                // 写好表头后换行  
                bfw.newLine(); 
                // 写入数据  
                for (int i = 0; i < rowCount; i++) {  
                    c.moveToPosition(i);  
                    Log.v("导出数据", "正在导出第" + (i + 1) + "条");  
                    for (int j = 0; j < colCount; j++) {  
                        if (j != colCount - 1)  {
                        	String temp =new String((c.getString(j) + ',').getBytes("utf-8"));
                        	bfw.write(temp);
//                          bfw.write(c.getString(j) + ',');  
                        }
                        else  
                           bfw.write(c.getString(j)+"");  //不加""的话，容易造成空指针错误，因为数据库有得项没有
                    }  
                    // 写好每条记录后换行  
                    bfw.newLine();  
                }  
            }  
            // 将缓存数据写入文件  
            bfw.flush();  
            // 释放缓存  
            bfw.close();  
            // Toast.makeText(mContext, "导出完毕！", Toast.LENGTH_SHORT).show();  
            Log.v("导出数据", "导出完毕！");  
        } catch (IOException e) {  
            e.printStackTrace();
        } finally {  
            c.close();  
        }  
    }  

	
}
