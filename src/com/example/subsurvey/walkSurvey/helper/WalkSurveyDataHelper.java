package com.example.subsurvey.walkSurvey.helper;


import com.example.subsurvey.AppConfig;
import com.example.subsurvey.common.SubSurveyDBHelper;
import com.example.subsurvey.staySurvey.beans.StaySurveyEntity;
import com.example.subsurvey.walkSurvey.beans.WalkSurveyEntity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class WalkSurveyDataHelper {
	/**
	 * 根据表明删除表数据
	 * 
	 * @param context
	 * @param table
	 * @return
	 */
	public static boolean deleteByTableName(Context context, String table) {
		SQLiteDatabase db = SubSurveyDBHelper.getInstance(context)
				.getWritableDatabase();
		int num = db.delete(table, null, null);
		db.close();
		db = null;
		if (num != 0)
			return true;
		else
			return false;

	}
	
	public static void insertIntoWalkSurveyData(Context context,WalkSurveyEntity walkSurveyEntity){
		SQLiteDatabase db = null;
		try{
			//调用getWritableDatabase()时会判断指定的数据库是否存在，不存在则调SQLiteDatabase.create创建
			db=SubSurveyDBHelper.getInstance(context).getWritableDatabase();
			
			db.beginTransaction();
			ContentValues values = new ContentValues();
			values.put(WalkSurveyEntity._ID, walkSurveyEntity.getID()); //static去掉不知道有错否  主键 同时标记着记录的数量
			values.put(WalkSurveyEntity.TName, walkSurveyEntity.getName());
			values.put(WalkSurveyEntity.TDate, walkSurveyEntity.getDate());
			values.put(WalkSurveyEntity.TStation, walkSurveyEntity.getStation());
			values.put(WalkSurveyEntity.TLine, walkSurveyEntity.getLine());
			values.put(WalkSurveyEntity.TWeekDayIF, walkSurveyEntity.getWeekdayIf());
			values.put(WalkSurveyEntity.TTravelTime, walkSurveyEntity.getTravelTime());
			values.put(WalkSurveyEntity.TWalkType, walkSurveyEntity.getWalkType());
			values.put(WalkSurveyEntity.TAge, walkSurveyEntity.getAge());
			values.put(WalkSurveyEntity.TBagageType, walkSurveyEntity.getBagageType());
			values.put(WalkSurveyEntity.TWalkTool, walkSurveyEntity.getWalkTool());
			values.put(WalkSurveyEntity.TSex, walkSurveyEntity.getSex());
			values.put(WalkSurveyEntity.TTelConcern, walkSurveyEntity.getTelConcern());
			values.put(WalkSurveyEntity.TSpeed, walkSurveyEntity.getSpeed());
			values.put(WalkSurveyEntity.TGetOffLine, walkSurveyEntity.getGetOffLine());
			values.put(WalkSurveyEntity.TGetOffDire, walkSurveyEntity.getGetOffDire());
			values.put(WalkSurveyEntity.TOpenDoorTime1, walkSurveyEntity.getOpenDoorTime1());
			values.put(WalkSurveyEntity.TMachineLine, walkSurveyEntity.getMachineLine());
			values.put(WalkSurveyEntity.TMachineLoc, walkSurveyEntity.getMachineLoc());
			values.put(WalkSurveyEntity.TGotoPlatformTime, walkSurveyEntity.getGotoPlatformTime());
			values.put(WalkSurveyEntity.TGetOnLine, walkSurveyEntity.getGetOnLine());
			values.put(WalkSurveyEntity.TGetOnDire, walkSurveyEntity.getGetOnDire());
			values.put(WalkSurveyEntity.TArrivePlatformTime, walkSurveyEntity.getArrivePlatformTime());
			values.put(WalkSurveyEntity.TOpenDoorTime2, walkSurveyEntity.getOpenDoorTime2());
			values.put(WalkSurveyEntity.TGooutPlatformTime, walkSurveyEntity.getGooutPlatformTime());
			values.put(WalkSurveyEntity.TRounteNo, walkSurveyEntity.getRouteNo());
			
			db.replace(WalkSurveyEntity.TABLE_NAME_TWALKSURVEYINFO, null, values);
			
			// 设置事务标志为成功，当结束事务时就会提交事务
			db.setTransactionSuccessful();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.endTransaction();
			db.close();
		}
	}
	
	//用于编辑后的更新数据库
	public static void updateTime(Context context,String editTimeVal,int timeCode,int _ID){
		try {
  			SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
			String table = WalkSurveyEntity.TABLE_NAME_TWALKSURVEYINFO;
			ContentValues updates = new ContentValues();
			switch (timeCode){
				case AppConfig.WS_PER_OPENDOOR1_CODE:
					updates.put(WalkSurveyEntity.TOpenDoorTime1, editTimeVal);
					break;
				case AppConfig.WS_PER_GOTOPLAT_CODE:
					updates.put(WalkSurveyEntity.TGotoPlatformTime, editTimeVal);
					break;
				case AppConfig.WS_PER_ARRIVEPLAT_CODE:
					updates.put(WalkSurveyEntity.TArrivePlatformTime, editTimeVal);
					break;
				case AppConfig.WS_PER_OPENDOOR2_CODE:
					updates.put(WalkSurveyEntity.TOpenDoorTime2,editTimeVal);
					break;
				case AppConfig.WS_PER_GOOUTPLAT_CODE:
					updates.put(WalkSurveyEntity.TGooutPlatformTime, editTimeVal);
					break;
			}

			String where = WalkSurveyEntity._ID + "=" + _ID;
			db.update(table, updates, where, null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//查询数据库全部数据
	public static Cursor queryWalkSurveyData(Context context){
		SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
		String table = WalkSurveyEntity.TABLE_NAME_TWALKSURVEYINFO;
		String columns[]=new String[]{WalkSurveyEntity._ID,
				WalkSurveyEntity.TName,WalkSurveyEntity.TDate ,
				WalkSurveyEntity.TStation ,WalkSurveyEntity.TLine ,
				WalkSurveyEntity.TWeekDayIF ,WalkSurveyEntity.TTravelTime ,
				WalkSurveyEntity.TWalkType ,WalkSurveyEntity.TAge ,WalkSurveyEntity.TSex,
				WalkSurveyEntity.TBagageType ,WalkSurveyEntity.TTelConcern,WalkSurveyEntity.TWalkTool ,
				WalkSurveyEntity.TSpeed,WalkSurveyEntity.TGetOffLine,WalkSurveyEntity.TGetOffDire,
				WalkSurveyEntity.TOpenDoorTime1 ,WalkSurveyEntity.TMachineLine,WalkSurveyEntity.TMachineLoc,
				WalkSurveyEntity.TGotoPlatformTime ,WalkSurveyEntity.TGetOnLine,WalkSurveyEntity.TGetOnDire,
				WalkSurveyEntity.TArrivePlatformTime ,WalkSurveyEntity.TOpenDoorTime2 ,
				WalkSurveyEntity.TGooutPlatformTime,WalkSurveyEntity.TRounteNo
		};
		Cursor cursor = db.query(table, columns, null, null, null, null, null);
		return cursor;
	}
	
	//删除选中的调查数据
	public static void delWalkSurveyInfoByID(Context context,long id){
		SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
		String sql = "delete from t_walkSurveyInfo where _id = "+id;
		db.execSQL(sql);
	}
	
	//删除表中数据
	public static void delWalkSurveyInfo(Context context){
		SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
		String sql = "delete from t_walkSurveyInfo ";
		db.execSQL(sql);
	}
	
	public static void updateNo(Context context,WalkSurveyEntity walkSurveyEntity,int tempID){
		try{
			SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
			String table = WalkSurveyEntity.TABLE_NAME_TWALKSURVEYINFO;
			ContentValues updates = new ContentValues();
			
			updates.put(WalkSurveyEntity._ID, tempID-1);
			
			String where = WalkSurveyEntity._ID + "=" + tempID;
			db.update(table, updates, where, null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//删除最新一条数据_为撤销做准备
	public static void delWalkSurveyInfoByNewestID(Context context){
		SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
		String sql = " delete from t_walkSurveyInfo where _id  = ( select max(_id) from t_walkSurveyInfo)";
		db.execSQL(sql);
	}
}
