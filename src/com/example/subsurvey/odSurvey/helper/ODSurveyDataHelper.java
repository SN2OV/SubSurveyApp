package com.example.subsurvey.odSurvey.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.subsurvey.common.SubSurveyDBHelper;
import com.example.subsurvey.odSurvey.beans.ODSurveyEntity;

public class ODSurveyDataHelper {
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
	
	public static void insertIntoODSurveyData(Context context,ODSurveyEntity odSurveyEntity){
		SQLiteDatabase db = null;
		try{
			//调用getWritableDatabase()时会判断指定的数据库是否存在，不存在则调SQLiteDatabase.create创建
			db=SubSurveyDBHelper.getInstance(context).getWritableDatabase();
			db.beginTransaction();
			ContentValues values = new ContentValues();
			values.put(ODSurveyEntity._ID, odSurveyEntity.getID()); //static去掉不知道有错否  主键 同时标记着记录的数量
			values.put(ODSurveyEntity.TName, odSurveyEntity.getName());
			values.put(ODSurveyEntity.TDate, odSurveyEntity.getDate());
			values.put(ODSurveyEntity.TCardNum, odSurveyEntity.getCardNum());
			values.put(ODSurveyEntity.TIDNo, odSurveyEntity.getIDNo());
			values.put(ODSurveyEntity.TGetinStation, odSurveyEntity.getStation());
			values.put(ODSurveyEntity.TGetinStationLine, odSurveyEntity.getGetinStationLine());
			values.put(ODSurveyEntity.TStationCount, odSurveyEntity.getStationCount());
			values.put(ODSurveyEntity.TTransferCount, odSurveyEntity.getTransferCount());
			values.put(ODSurveyEntity.TDistanceTotal, odSurveyEntity.getDistanceTotal());
			values.put(ODSurveyEntity.TWeekdayIf, odSurveyEntity.getWeekdayIf());
			values.put(ODSurveyEntity.TPathType, odSurveyEntity.getPathType());
			values.put(ODSurveyEntity.TPeakIf, odSurveyEntity.getPeakIf());
			values.put(ODSurveyEntity.TGetinStationTime, odSurveyEntity.getGetinStationTime());
			values.put(ODSurveyEntity.TArriveStationTime1, odSurveyEntity.getArriveStationTime1());
			values.put(ODSurveyEntity.TTrainDire1, odSurveyEntity.getTrainDire1());
			values.put(ODSurveyEntity.TShift1, odSurveyEntity.getShift1());
			values.put(ODSurveyEntity.TTrainStartingTime1, odSurveyEntity.getTrainStartingTime1());
			values.put(ODSurveyEntity.TGetoffStation1, odSurveyEntity.getGetoffStation1());
			values.put(ODSurveyEntity.TGetoffStationTime1, odSurveyEntity.getGetoffStationTime1());
			values.put(ODSurveyEntity.TTransferLine1, odSurveyEntity.getTransferLine1());
			values.put(ODSurveyEntity.TArriveStationTime2, odSurveyEntity.getArriveStationTime2());
			values.put(ODSurveyEntity.TTrainDire2, odSurveyEntity.getTrainDire2());
			values.put(ODSurveyEntity.TShift2, odSurveyEntity.getShift2());
			values.put(ODSurveyEntity.TTrainStartingTime2, odSurveyEntity.getTrainStartingTime2());
			values.put(ODSurveyEntity.TGetoffStation2, odSurveyEntity.getGetoffStation2());
			values.put(ODSurveyEntity.TGetoffStationTime2, odSurveyEntity.getGetoffStationTime2());
			values.put(ODSurveyEntity.TTransferLine2, odSurveyEntity.getTransferLine2());
			values.put(ODSurveyEntity.TArriveStationTime3, odSurveyEntity.getArriveStationTime3());
			values.put(ODSurveyEntity.TTrainDire3, odSurveyEntity.getTrainDire3());
			values.put(ODSurveyEntity.TShift3, odSurveyEntity.getShift3());
			values.put(ODSurveyEntity.TTrainStartingTime3, odSurveyEntity.getTrainStartingTime3());
			values.put(ODSurveyEntity.TGetoffStation3, odSurveyEntity.getGetoffStation3());
			values.put(ODSurveyEntity.TGetoffStationTime3, odSurveyEntity.getGetoffStationTime3());
			values.put(ODSurveyEntity.TGetoutStationTime, odSurveyEntity.getGetoutStationTime());
			
			db.replace(ODSurveyEntity.TABLE_NAME_TODSURVEYINFO, null, values);
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
	public static Cursor queryODSurveyData(Context context){
		SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
		String table = ODSurveyEntity.TABLE_NAME_TODSURVEYINFO;
		String columns[]=new String[]{ODSurveyEntity._ID,
				ODSurveyEntity.TDate,ODSurveyEntity.TName,ODSurveyEntity.TDate,ODSurveyEntity.TCardNum,ODSurveyEntity.TIDNo,
				ODSurveyEntity.TGetinStation,ODSurveyEntity.TGetinStationLine,ODSurveyEntity.TTransferCount,ODSurveyEntity.TStationCount,ODSurveyEntity.TDistanceTotal,ODSurveyEntity.TWeekdayIf,
				ODSurveyEntity.TPathType,ODSurveyEntity.TPeakIf,ODSurveyEntity.TGetinStationTime,ODSurveyEntity.TArriveStationTime1,ODSurveyEntity.TTrainDire1,
				ODSurveyEntity.TShift1,ODSurveyEntity.TTrainStartingTime1,ODSurveyEntity.TGetoffStation1,ODSurveyEntity.TGetoffStationTime1,ODSurveyEntity.TTransferLine1,
				ODSurveyEntity.TArriveStationTime2,ODSurveyEntity.TTrainDire2,ODSurveyEntity.TShift2,ODSurveyEntity.TTrainStartingTime2,ODSurveyEntity.TGetoffStation2,
				ODSurveyEntity.TGetoffStationTime2,ODSurveyEntity.TTransferLine2,ODSurveyEntity.TArriveStationTime3,ODSurveyEntity.TTrainDire3,ODSurveyEntity.TShift3,
				ODSurveyEntity.TTrainStartingTime3,ODSurveyEntity.TGetoffStation3,ODSurveyEntity.TGetoffStationTime3,ODSurveyEntity.TGetoutStationTime
				};
		Cursor cursor = db.query(table, columns, null, null, null, null, null);
		return cursor;
	}
	
	//删除选中的调查数据
	public static void delStaySurveyInfoByID(Context context,long id){
		SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
		String sql = "delete from t_odSurveyInfo where _id = "+id;
		db.execSQL(sql);
	}
	
	//用于编辑后的更新数据库
	public static void updateTime(Context context,ODSurveyEntity odSurveyEntity){
		try {
			SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
			String table = ODSurveyEntity.TABLE_NAME_TODSURVEYINFO;
			ContentValues updates = new ContentValues();
			
			updates.put(ODSurveyEntity.TGetinStationTime, odSurveyEntity.getGetinStationTime());
			updates.put(ODSurveyEntity.TArriveStationTime1, odSurveyEntity.getArriveStationTime1());
			updates.put(ODSurveyEntity.TTrainStartingTime1, odSurveyEntity.getTrainStartingTime1());
			updates.put(ODSurveyEntity.TGetoffStationTime1, odSurveyEntity.getGetoffStationTime1());
			updates.put(ODSurveyEntity.TArriveStationTime2, odSurveyEntity.getArriveStationTime2());
			updates.put(ODSurveyEntity.TTrainStartingTime2, odSurveyEntity.getTrainStartingTime2());
			updates.put(ODSurveyEntity.TGetoffStationTime2, odSurveyEntity.getGetoffStationTime2());
			updates.put(ODSurveyEntity.TArriveStationTime3, odSurveyEntity.getArriveStationTime3());
			updates.put(ODSurveyEntity.TTrainStartingTime3, odSurveyEntity.getTrainStartingTime3());
			updates.put(ODSurveyEntity.TGetoffStationTime3, odSurveyEntity.getGetoffStationTime3());
			updates.put(ODSurveyEntity.TGetoutStationTime, odSurveyEntity.getGetoutStationTime());
			
			String where = ODSurveyEntity._ID + "=" + odSurveyEntity.getID();
			db.update(table, updates, where, null);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static void updateNo(Context context,ODSurveyEntity odSurveyEntity,int tempID){
		try{
			SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
			String table = ODSurveyEntity.TABLE_NAME_TODSURVEYINFO;
			ContentValues updates = new ContentValues();
			
			updates.put(ODSurveyEntity._ID, tempID-1);
			
			String where = ODSurveyEntity._ID + "=" + tempID;
			db.update(table, updates, where, null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//删除表中数据
	public static void delODSurveyInfo(Context context){
		SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
		String sql = "delete from t_odSurveyInfo ";
		db.execSQL(sql);
	}
	
}
