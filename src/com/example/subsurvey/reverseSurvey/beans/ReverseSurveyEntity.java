package com.example.subsurvey.reverseSurvey.beans;

import android.provider.BaseColumns;

import com.example.subsurvey.AppConfig;

import java.io.Serializable;

/**
 * Created by SN2OV on 2016/5/4.
 */
public class ReverseSurveyEntity implements Serializable,BaseColumns {

    public static int ID = 1;
    public String rowId;
    private String name;
    private String date;
    private String station;
    private String dire;
    private String surveyTime;
    private String countPer;
    private String countTotal;
    private String count;
    private String trainStartTime;

    public static final String TName = "姓名";
    public static final String TDate = "日期";
    public static final String TSurveyTime = "时间段";
    public static final String TStation = "车站";
    public static final String TDire = "调查方向";
    public static final String TTrainStartTime = "列车启动时刻";
    public static final String TCount = "人数";

    public static final String TABLE_NAME_REVERSE_SURVEYINFO = "t_reverseSurveyInfo";
    public static final String SQL_DROP_TABLE_REVERSE_SURVEINFO = "drop table if exists"+ReverseSurveyEntity.TABLE_NAME_REVERSE_SURVEYINFO;

    //建表
    public static final String SQL_CREATE_TABLE_REVERSE_SURVEYINFO = "create table if not exists "
            +ReverseSurveyEntity.TABLE_NAME_REVERSE_SURVEYINFO+" (" + _ID + " INTEGER PRIMARY KEY"+ AppConfig.COMMA_SEP
            +ReverseSurveyEntity.TName+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
            +ReverseSurveyEntity.TDate+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
            +ReverseSurveyEntity.TSurveyTime+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
            +ReverseSurveyEntity.TStation+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
            +ReverseSurveyEntity.TDire+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
            +ReverseSurveyEntity.TTrainStartTime+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
            +ReverseSurveyEntity.TCount+ AppConfig.TYPE_TEXT
            + ");";


    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        ReverseSurveyEntity.ID = ID;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getDire() {
        return dire;
    }

    public void setDire(String dire) {
        this.dire = dire;
    }

    public String getSurveyTime() {
        return surveyTime;
    }

    public void setSurveyTime(String surveyTime) {
        this.surveyTime = surveyTime;
    }

    public String getCountPer() {
        return countPer;
    }

    public void setCountPer(String countPer) {
        this.countPer = countPer;
    }

    public String getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(String countTotal) {
        this.countTotal = countTotal;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTrainStartTime() {
        return trainStartTime;
    }

    public void setTrainStartTime(String trainStartTime) {
        this.trainStartTime = trainStartTime;
    }
}
