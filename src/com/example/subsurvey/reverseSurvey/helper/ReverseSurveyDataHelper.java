package com.example.subsurvey.reverseSurvey.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.subsurvey.common.SubSurveyDBHelper;
import com.example.subsurvey.reverseSurvey.beans.ReverseSurveyEntity;
import com.example.subsurvey.staySurvey.beans.StaySurveyEntity;
import com.example.subsurvey.transferSurvey.beans.TransferSurveyEntity;

/**
 * Created by SN2OV on 2016/5/5.
 */
public class ReverseSurveyDataHelper {

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

    public static void insertIntoRSSurveyData(Context context,ReverseSurveyEntity reverseSurveyEntity){
        SQLiteDatabase db = null;
        try{
            //调用getWritableDatabase()时会判断指定的数据库是否存在，不存在则调SQLiteDatabase.create创建
            db=SubSurveyDBHelper.getInstance(context).getWritableDatabase();
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put(ReverseSurveyEntity._ID, reverseSurveyEntity.getID());
            values.put(ReverseSurveyEntity.TName, reverseSurveyEntity.getName());
            values.put(ReverseSurveyEntity.TDate, reverseSurveyEntity.getDate());
            values.put(ReverseSurveyEntity.TSurveyTime, reverseSurveyEntity.getSurveyTime());
            values.put(ReverseSurveyEntity.TStation, reverseSurveyEntity.getStation());
            values.put(ReverseSurveyEntity.TDire, reverseSurveyEntity.getDire());
            values.put(ReverseSurveyEntity.TTrainStartTime,reverseSurveyEntity.getTrainStartTime());
            values.put(ReverseSurveyEntity.TCount, reverseSurveyEntity.getCount());

            db.replace(ReverseSurveyEntity.TABLE_NAME_REVERSE_SURVEYINFO, null, values);
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
    public static Cursor queryRSSurveyData(Context context){
        SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
        String table = ReverseSurveyEntity.TABLE_NAME_REVERSE_SURVEYINFO;
        String columns[]=new String[]{ReverseSurveyEntity._ID,
                ReverseSurveyEntity.TDate,
                ReverseSurveyEntity.TName,
                ReverseSurveyEntity.TSurveyTime,
                ReverseSurveyEntity.TStation,
                ReverseSurveyEntity.TDire,
                ReverseSurveyEntity.TTrainStartTime,
                ReverseSurveyEntity.TCount
        };
        Cursor cursor = db.query(table, columns, null, null, null, null, null);
        return cursor;
    }

    //删除选中的调查数据
    public static void delReverseSurveyInfoByID(Context context,long id){
        SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
        String sql = "delete from t_reverseSurveyInfo where _id = "+id;
        db.execSQL(sql);
    }

    //删除表中数据
    public static void delReverseSurveyInfo(Context context){
        SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
        String sql = "delete from t_reverseSurveyInfo ";
        db.execSQL(sql);
    }

    //删除后的更新数据
    public static void updateNo(Context context,ReverseSurveyEntity rsSurveyEntity,int tempID){
        try{
            SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
            String table = ReverseSurveyEntity.TABLE_NAME_REVERSE_SURVEYINFO;
            ContentValues updates = new ContentValues();

            updates.put(ReverseSurveyEntity._ID, tempID-1);

            String where = ReverseSurveyEntity._ID + "=" + tempID;
            db.update(table, updates, where, null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //用于编辑后的更新数据库
    public static void updateCountByNo(Context context,String newCountNum,int no){
        try {
            SQLiteDatabase db = SubSurveyDBHelper.getInstance(context).getWritableDatabase();
            String table = ReverseSurveyEntity.TABLE_NAME_REVERSE_SURVEYINFO;
            ContentValues updates = new ContentValues();

            updates.put(ReverseSurveyEntity.TCount, newCountNum);

            String where = ReverseSurveyEntity._ID + "=" + no;
            db.update(table, updates, where, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
