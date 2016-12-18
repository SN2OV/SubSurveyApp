package com.example.subsurvey.common;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.odSurvey.beans.ODSurveyEntity;
import com.example.subsurvey.reverseSurvey.beans.ReverseSurveyEntity;
import com.example.subsurvey.staySurvey.beans.StaySurveyEntity;
import com.example.subsurvey.transferSurvey.beans.TransferSurveyEntity;
import com.example.subsurvey.walkSurvey.beans.WalkSurveyEntity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SubSurveyDBHelper extends SQLiteOpenHelper {

private static volatile SubSurveyDBHelper instance = null;
	
	private static final String TAG = SubSurveyDBHelper.class.getSimpleName();
	
	/**
     * 数据库helper操作类
     * 
     * @param context
     * @return
     */
    public static SubSurveyDBHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (SubSurveyDBHelper.class) {
                if (instance == null) {
                    instance = new SubSurveyDBHelper(context);
                }
            }
        }
        return instance;
    }
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(WalkSurveyEntity.SQL_CREATE_TABLE_TWALKSURVETINFO);
		db.execSQL(StaySurveyEntity.SQL_CREATE_TABLE_TSTAYSURVEYINFO);
		db.execSQL(ODSurveyEntity.SQL_CREATE_TABLE_TODSURVEYINFO);
		db.execSQL(TransferSurveyEntity.SQL_CREATE_TABLE_TTRANSFERSURVEYINFO);
		db.execSQL(ReverseSurveyEntity.SQL_CREATE_TABLE_REVERSE_SURVEYINFO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(WalkSurveyEntity.SQL_DROP_TABLE_TWALKSURVEINFO);
		db.execSQL(StaySurveyEntity.SQL_DROP_TABLE_TSTAYSURVEINFO);
		db.execSQL(ODSurveyEntity.SQL_DROP_TABLE_TODSURVEINFO);
		db.execSQL(TransferSurveyEntity.SQL_DROP_TABLE_TTRANSFERSURVEINFO);
		db.execSQL(ReverseSurveyEntity.SQL_DROP_TABLE_REVERSE_SURVEINFO);
	}
	
	/**
     * @param context
     */
    public SubSurveyDBHelper(Context context) {
        super(context, AppConfig.DATABASE_NAME, null, AppConfig.DATABASE_VERSION);
        Log.i(TAG, "Create database: " + AppConfig.DATABASE_NAME + ", version: " + AppConfig.DATABASE_VERSION);
    }


}
