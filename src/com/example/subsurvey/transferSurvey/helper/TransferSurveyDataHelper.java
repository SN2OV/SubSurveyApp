package com.example.subsurvey.transferSurvey.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.subsurvey.common.SubSurveyDBHelper;
import com.example.subsurvey.odSurvey.beans.ODSurveyEntity;
import com.example.subsurvey.transferSurvey.beans.TransferSurveyEntity;

public class TransferSurveyDataHelper {

	/**
	 * 根据表名删除表数据
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
	
	public static void insertIntoTSSurveyData(Context context,TransferSurveyEntity transferSurveyEntity){
		SQLiteDatabase db = null;
		try{
			//调用getWritableDatabase()时会判断指定的数据库是否存在，不存在则调SQLiteDatabase.create创建
			db=SubSurveyDBHelper.getInstance(context).getWritableDatabase();
			db.beginTransaction();
			ContentValues values = new ContentValues();
			values.put(TransferSurveyEntity._ID, transferSurveyEntity.getID()); 
			values.put(TransferSurveyEntity.TName, transferSurveyEntity.getName());
			values.put(TransferSurveyEntity.TDate, transferSurveyEntity.getDate());
			values.put(TransferSurveyEntity.TStation, transferSurveyEntity.getStation());
			values.put(TransferSurveyEntity.TTimePeriod,transferSurveyEntity.getTimePeriod());
			//TODO 修改为位置
			values.put(TransferSurveyEntity.TDire, transferSurveyEntity.getDire());
			values.put(TransferSurveyEntity.TSurveyTime, transferSurveyEntity.getSurveyTime());
			values.put(TransferSurveyEntity.TCount, transferSurveyEntity.getCount());
			
			db.replace(TransferSurveyEntity.TABLE_NAME_TTRANSFERSURVEYINFO, null, values);
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
		public static Cursor queryTSSurveyData(Context context){
			SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
			String table = TransferSurveyEntity.TABLE_NAME_TTRANSFERSURVEYINFO;
			String columns[]=new String[]{TransferSurveyEntity._ID,
					TransferSurveyEntity.TDate,TransferSurveyEntity.TName,TransferSurveyEntity.TStation,TransferSurveyEntity.TTimePeriod,
					TransferSurveyEntity.TDire,TransferSurveyEntity.TSurveyTime,TransferSurveyEntity.TCount
					};
			Cursor cursor = db.query(table, columns, null, null, null, null, null);
			return cursor;
		}
		
	//删除选中的调查数据
	public static void delTransferSurveyInfoByID(Context context,long id){
		SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
		String sql = "delete from t_transferSurveyInfo where _id = "+id;
		db.execSQL(sql);
	}

	public static void updateNo(Context context,TransferSurveyEntity odSurveyEntity,int tempID){
		try{
			SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
			String table = TransferSurveyEntity.TABLE_NAME_TTRANSFERSURVEYINFO;
			ContentValues updates = new ContentValues();

			updates.put(TransferSurveyEntity._ID, tempID-1);

			String where = TransferSurveyEntity._ID + "=" + tempID;
			db.update(table, updates, where, null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


    //删除最新一条数据_为撤销做准备
    public static void delTransferSurveyInfoByNewestID(Context context){
        SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
        String sql = " delete from t_transferSurveyInfo where _id  = ( select max(_id) from t_transferSurveyInfo)";
        db.execSQL(sql);
    }

    //删除表中数据
    public static void delTransferSurveyInfo(Context context){
        SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
        String sql = "delete from t_transferSurveyInfo ";
        db.execSQL(sql);
    }
}
