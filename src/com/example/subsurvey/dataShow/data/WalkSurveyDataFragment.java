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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;
import butterknife.ButterKnife;

import com.example.subsurvey.R;
import com.example.subsurvey.base.BaseFragment;
import com.example.subsurvey.common.UIHelper;
import com.example.subsurvey.old.WalkSurveySettingActivity;
import com.example.subsurvey.walkSurvey.adapter.WalkSurveyDataAdapter;
import com.example.subsurvey.walkSurvey.beans.WalkSurveyEntity;
import com.example.subsurvey.walkSurvey.helper.WalkSurveyDataHelper;
import com.example.subsurvey.walkSurvey.ui.WalkSurveyDataTotalActivity;
import com.example.subsurvey.walkSurvey.ui.WalkSurveyDataTotalActivity.onItemLongDeleImpl;

public class WalkSurveyDataFragment extends BaseFragment{
	
	public ListView walkSurveyDataLV ;
	public List<WalkSurveyEntity> wsInfo ;
	public WalkSurveyDataAdapter adapter;
	public Context context;
	private SharedPreferences preferences;
	private int wsRowID;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_walk_survey_data_total, null);
		
		context = getActivity();
		walkSurveyDataLV = (ListView)view.findViewById(R.id.walkSurveyDataLV);
		
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
		temp.setBagageType("行李类型");
		temp.setWalkTool("走行工具");
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
		
		wsInfo = queryAddWalkSurveyInfo(context, wsInfo);
		adapter = new WalkSurveyDataAdapter(context,wsInfo);
		walkSurveyDataLV.setAdapter(adapter);
		walkSurveyDataLV.setOnItemLongClickListener(new onItemLongDeleImpl());
		ButterKnife.inject(this, view);
        initView(view);
		return view;
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
				temp.setBagageType(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TBagageType )));
				temp.setWalkTool(cursor.getString(cursor.getColumnIndexOrThrow(WalkSurveyEntity.TWalkTool )));
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
//				UIHelper.ToastMessage(context, "选中的_id是第"+id+"个");
				Dialog dialog = new AlertDialog.Builder(getActivity()).setTitle("删除选中调查数据").setIcon(R.drawable.app_logo)
						.setMessage("确认删除吗？").setPositiveButton("确认", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								context = getApplication();
								WalkSurveyDataHelper.delWalkSurveyInfoByID(context, id);
								
								//删除后处理序号顺序 ssInfo.size()-1因为多一行没用的表头数据
								for(int i = position+1;i<=wsInfo.size()-1;i++){
									WalkSurveyDataHelper.updateNo(context, WalkSurveySettingActivity.walkSurveyEntity, i);
									wsInfo.get(i).rowID = (i-1)+"";
								}
								System.out.println(wsInfo);
								wsInfo.remove(position);
								//处理序号
								preferences = getActivity().getSharedPreferences("wsRowID",0);
								wsRowID = preferences.getInt("wsRowID", 1);//默认为第二个参数1
								wsRowID--;
								//通过sharedPreferences将主键保存起来
								Editor editor = preferences.edit();
								editor.putInt("wsRowID",wsRowID);
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
//	                          bfw.write(c.getString(j) + ',');  
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
