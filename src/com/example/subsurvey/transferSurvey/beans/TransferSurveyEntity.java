package com.example.subsurvey.transferSurvey.beans;

import java.io.Serializable;

import android.provider.BaseColumns;

import com.example.subsurvey.AppConfig;
import com.example.subsurvey.odSurvey.beans.ODSurveyEntity;

public class TransferSurveyEntity implements Serializable, BaseColumns{

	public static int ID = 1;
	public String rowId;
	private String name;
	private String date;
	private String station;
	private String timePeriod;
	private String dire;
	private String surveyTime;
	private String countPer;
	private String countTotal;
	private String count;
	private String endTime;
	private String startTime;
	
	public static final String TABLE_NAME_TTRANSFERSURVEYINFO = "t_transferSurveyInfo"; 
	public static final String SQL_DROP_TABLE_TTRANSFERSURVEINFO = "drop table if exists"+TransferSurveyEntity.TABLE_NAME_TTRANSFERSURVEYINFO;
	
	public static final String TName = "姓名";
	public static final String TDate = "日期";
	public static final String TStation = "车站";
	public static final String TTimePeriod = "调查时间段";
	public static final String TDire = "调查方向";
	public static final String TSurveyTime = "时间";
	public static final String TCount = "人数";

	//建表
	public static final String SQL_CREATE_TABLE_TTRANSFERSURVEYINFO = "create table if not exists "
			+TransferSurveyEntity.TABLE_NAME_TTRANSFERSURVEYINFO+" (" + _ID + " INTEGER PRIMARY KEY"+AppConfig.COMMA_SEP
			+TransferSurveyEntity.TName+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+TransferSurveyEntity.TDate+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+TransferSurveyEntity.TStation+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+TransferSurveyEntity.TTimePeriod+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+TransferSurveyEntity.TDire+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+TransferSurveyEntity.TSurveyTime+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+TransferSurveyEntity.TCount+ AppConfig.TYPE_TEXT
			+ ");";
	
	public static int getID() {
		return ID;
	}
	public static void setID(int iD) {
		ID = iD;
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
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getCountTotal() {
		return countTotal;
	}
	public void setCountTotal(String countTotal) {
		this.countTotal = countTotal;
	}

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}
}
