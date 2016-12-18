package com.example.subsurvey.staySurvey.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.subsurvey.common.SubSurveyDBHelper;
import com.example.subsurvey.odSurvey.beans.ODSurveyEntity;
import com.example.subsurvey.staySurvey.beans.StaySurveyEntity;

public class StaySurveyDataHelper {
	/**
	 * 根据表名删除表数据
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
	
	public static void insertIntoStaySurveyData(Context context,StaySurveyEntity staySurveyEntity){
		SQLiteDatabase db = null;
		try{
			//调用getWritableDatabase()时会判断指定的数据库是否存在，不存在则调SQLiteDatabase.create创建
			db=SubSurveyDBHelper.getInstance(context).getWritableDatabase();
			
			db.beginTransaction();
			ContentValues values = new ContentValues();
			values.put(StaySurveyEntity._ID, staySurveyEntity.getID()); //static去掉不知道有错否  主键 同时标记着记录的数量
			values.put(StaySurveyEntity.TName, staySurveyEntity.getName());
			values.put(StaySurveyEntity.TDate, staySurveyEntity.getDate());
			values.put(StaySurveyEntity.TStation, staySurveyEntity.getStation());
			values.put(StaySurveyEntity.TCarriageLoc, staySurveyEntity.getCarriageLoc());//staySurveyEntity.getCarriageLoc为车厢编号
			values.put(StaySurveyEntity.TPlatformLoc,"去往"+staySurveyEntity.getGetOffLineNo()+"号线"+staySurveyEntity.getGetoffLineDire()+"方向");
			values.put(StaySurveyEntity.TWeekDayIF, staySurveyEntity.getWeekdayIf());
			values.put(StaySurveyEntity.TTravelTime, staySurveyEntity.getTravelTime());
			values.put(StaySurveyEntity.TSurveyType, staySurveyEntity.getWalkType());
			
			values.put(StaySurveyEntity.TSex, staySurveyEntity.getSex());
			values.put(StaySurveyEntity.TAge, staySurveyEntity.getAge());
			values.put(StaySurveyEntity.TBagageType, staySurveyEntity.getBagageType());
			//根据调查进站or换乘不同的显示
			if(staySurveyEntity.getWalkType().equals("进站")){
				values.put(StaySurveyEntity.TGotoPlatformLoc, staySurveyEntity.getGoinLineNo()+"号线"+staySurveyEntity.getGoinLineDire()+"口");
			}else if(staySurveyEntity.getWalkType().equals("换乘")){
				values.put(StaySurveyEntity.TTransferLoc,staySurveyEntity.getGoinLineNo()+"号线"+staySurveyEntity.getGoinLineDire()+"站(方向)");
			}
			values.put(StaySurveyEntity.TGotoPlatformTime, staySurveyEntity.getGointoStationTime());
			values.put(StaySurveyEntity.TArrivePlatformTime, staySurveyEntity.getArriveStationTime());
			values.put(StaySurveyEntity.TGetoffTransferTime, staySurveyEntity.getGetoffTime());
			
			values.put(StaySurveyEntity.TOrignLineNum, staySurveyEntity.getStartLineNum());
			values.put(StaySurveyEntity.TTrainStartNO1, staySurveyEntity.getTrainStartTime1());
			values.put(StaySurveyEntity.TGetonNumNO1, staySurveyEntity.getGetOnNum1());
			values.put(StaySurveyEntity.TGetoffNumNO1, staySurveyEntity.getGetOffNum1());
			values.put(StaySurveyEntity.TTrainStartNO2, staySurveyEntity.getTrainStartTime2());
			values.put(StaySurveyEntity.TGetonNumNO2, staySurveyEntity.getGetOnNum2());
			values.put(StaySurveyEntity.TGetoffNumNO2, staySurveyEntity.getGetOffNum2());
			values.put(StaySurveyEntity.TTrainStartNO3, staySurveyEntity.getTrainStartTime3());
			values.put(StaySurveyEntity.TGetonNumNO3, staySurveyEntity.getGetOnNum3());
			values.put(StaySurveyEntity.TGetoffNumNO3, staySurveyEntity.getGetOffNum3());
			values.put(StaySurveyEntity.TTrainStartNO4, staySurveyEntity.getTrainStartTime4());
			values.put(StaySurveyEntity.TGetonNumNO4, staySurveyEntity.getGetOnNum4());
			values.put(StaySurveyEntity.TGetoffNumNO4, staySurveyEntity.getGetOffNum4());
			values.put(StaySurveyEntity.TTrainStartNO5, staySurveyEntity.getTrainStartTime5());
			values.put(StaySurveyEntity.TGetonNumNO5, staySurveyEntity.getGetOnNum5());
			values.put(StaySurveyEntity.TGetoffNumNO5, staySurveyEntity.getGetOffNum5());
			values.put(StaySurveyEntity.TTrainStartNO6, staySurveyEntity.getTrainStartTime6());
			values.put(StaySurveyEntity.TGetonNumNO6, staySurveyEntity.getGetOnNum6());
			values.put(StaySurveyEntity.TGetoffNumNO6, staySurveyEntity.getGetOffNum6());
			
			db.replace(StaySurveyEntity.TABLE_NAME_TSTAYSURVEYINFO, null, values);
			
			// 设置事务标志为成功，当结束事务时就会提交事务
			db.setTransactionSuccessful();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.endTransaction();
			db.close();
		}
	}
	
	
	//查询数据库全部数据
	public static Cursor queryStaySurveyData(Context context){
		SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
		String table = StaySurveyEntity.TABLE_NAME_TSTAYSURVEYINFO;
		String columns[]=new String[]{StaySurveyEntity._ID,
				StaySurveyEntity.TName,StaySurveyEntity.TDate,StaySurveyEntity.TStation,StaySurveyEntity.TCarriageLoc,StaySurveyEntity.TPlatformLoc,
				StaySurveyEntity.TWeekDayIF,StaySurveyEntity.TTravelTime,StaySurveyEntity.TSurveyType,StaySurveyEntity.TSex,StaySurveyEntity.TAge,
				StaySurveyEntity.TBagageType,StaySurveyEntity.TGotoPlatformLoc,StaySurveyEntity.TTransferLoc,StaySurveyEntity.TGotoPlatformTime,
				StaySurveyEntity.TArrivePlatformTime,StaySurveyEntity.TGetoffTransferTime,StaySurveyEntity.TOrignLineNum,
				StaySurveyEntity.TTrainStartNO1,StaySurveyEntity.TGetonNumNO1,StaySurveyEntity.TGetoffNumNO1,StaySurveyEntity.TTrainStartNO2,StaySurveyEntity.TGetonNumNO2,StaySurveyEntity.TGetoffNumNO2,
				StaySurveyEntity.TTrainStartNO3,StaySurveyEntity.TGetonNumNO3,StaySurveyEntity.TGetoffNumNO3,StaySurveyEntity.TTrainStartNO4,StaySurveyEntity.TGetonNumNO4,StaySurveyEntity.TGetoffNumNO4,
				StaySurveyEntity.TTrainStartNO5,StaySurveyEntity.TGetonNumNO5,StaySurveyEntity.TGetoffNumNO5,StaySurveyEntity.TTrainStartNO6,StaySurveyEntity.TGetonNumNO6,StaySurveyEntity.TGetoffNumNO6
					
					};
		Cursor cursor = db.query(table, columns, null, null, null, null, null);
		return cursor;
	}
	
	//删除选中的调查数据
	public static void delStaySurveyInfoByID(Context context,long id){
		SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
		String sql = "delete from t_staySurveyInfo where _id = "+id;
		db.execSQL(sql);
	}
		
	//用于编辑后的更新数据库
	public static void updateTimeAndNum(Context context,StaySurveyEntity staySurveyEntity){
		try {
			SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
			String table = StaySurveyEntity.TABLE_NAME_TSTAYSURVEYINFO;
			ContentValues updates = new ContentValues();
			
			updates.put(StaySurveyEntity.TGotoPlatformTime, staySurveyEntity.getGointoStationTime());
			updates.put(StaySurveyEntity.TArrivePlatformTime, staySurveyEntity.getArriveStationTime());
			updates.put(StaySurveyEntity.TGetoffTransferTime, staySurveyEntity.getGetoffTime());
			updates.put(StaySurveyEntity.TOrignLineNum, staySurveyEntity.getStartLineNum());
			updates.put(StaySurveyEntity.TTrainStartNO1, staySurveyEntity.getTrainStartTime1());
			updates.put(StaySurveyEntity.TGetonNumNO1, staySurveyEntity.getGetOnNum1());
			updates.put(StaySurveyEntity.TGetoffNumNO1, staySurveyEntity.getGetOffNum1());
			updates.put(StaySurveyEntity.TTrainStartNO2, staySurveyEntity.getTrainStartTime2());
			updates.put(StaySurveyEntity.TGetonNumNO2, staySurveyEntity.getGetOnNum2());
			updates.put(StaySurveyEntity.TGetoffNumNO2, staySurveyEntity.getGetOffNum2());
			updates.put(StaySurveyEntity.TTrainStartNO3, staySurveyEntity.getTrainStartTime3());
			updates.put(StaySurveyEntity.TGetonNumNO3, staySurveyEntity.getGetOnNum3());
			updates.put(StaySurveyEntity.TGetoffNumNO3, staySurveyEntity.getGetOffNum3());
			updates.put(StaySurveyEntity.TTrainStartNO4, staySurveyEntity.getTrainStartTime4());
			updates.put(StaySurveyEntity.TGetonNumNO4, staySurveyEntity.getGetOnNum4());
			updates.put(StaySurveyEntity.TGetoffNumNO4, staySurveyEntity.getGetOffNum4());
			updates.put(StaySurveyEntity.TTrainStartNO5, staySurveyEntity.getTrainStartTime5());
			updates.put(StaySurveyEntity.TGetonNumNO5, staySurveyEntity.getGetOnNum5());
			updates.put(StaySurveyEntity.TGetoffNumNO5, staySurveyEntity.getGetOffNum5());
			updates.put(StaySurveyEntity.TTrainStartNO6, staySurveyEntity.getTrainStartTime6());
			updates.put(StaySurveyEntity.TGetonNumNO6, staySurveyEntity.getGetOnNum6());
			updates.put(StaySurveyEntity.TGetoffNumNO6, staySurveyEntity.getGetOffNum6());
			
			String where = StaySurveyEntity._ID + "=" + staySurveyEntity.getID();
			db.update(table, updates, where, null);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateNo(Context context,StaySurveyEntity staySurveyEntity,int tempID){
		try{
			SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
			String table = StaySurveyEntity.TABLE_NAME_TSTAYSURVEYINFO;
			ContentValues updates = new ContentValues();
			
			updates.put(StaySurveyEntity._ID, tempID-1);
			
			String where = StaySurveyEntity._ID + "=" + tempID;
			db.update(table, updates, where, null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void delStaySurveyInfo(Context context){
		SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
		String sql = "delete from t_staySurveyInfo ";
		db.execSQL(sql);
	}
	
}
