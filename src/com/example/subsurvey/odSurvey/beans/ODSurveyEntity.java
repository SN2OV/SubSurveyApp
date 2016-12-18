package com.example.subsurvey.odSurvey.beans;

import java.io.Serializable;

import com.example.subsurvey.AppConfig;

import android.provider.BaseColumns;

public class ODSurveyEntity implements Serializable, BaseColumns{
	
	public static int ID = 1;
	public String sequenceNO = "0";
	public Boolean isFinished = true;
	public String rowID,name,date,station,cardNum,IDNo,getinStation,getinStationLine,stationCount,distanceTotal,timeTotal,
				  weekdayIf,pathType,peakIf,getinStationTime,arriveStationTime1,transferCount,
				  trainDire1,shift1,trainStartingTime1,getoffStation1,getoffStationTime1,getoutStationTime,transferLine1,
				  arriveStationTime2,trainDire2,shift2,trainStartingTime2, getoffStation2, getoffStationTime2,transferLine2,
				  arriveStationTime3,trainDire3,shift3,trainStartingTime3,getoffStation3,getoffStationTime3;
	public static final String TABLE_NAME_TODSURVEYINFO = "t_odSurveyInfo"; // OD调查信息的表
	public static final String SQL_DROP_TABLE_TODSURVEINFO = "drop table if exists"+ODSurveyEntity.TABLE_NAME_TODSURVEYINFO;
		
	
	public static final String TName = "姓名";
	public static final String TDate = "日期";
	public static final String TCardNum = "卡号";
	public static final String TIDNo = "身份证号";
	public static final String TGetinStation = "车站";
	public static final String TGetinStationLine = "线路";
	public static final String TTransferCount = "总换乘数";
	public static final String TStationCount = "总站数";
	public static final String TDistanceTotal = "总距离";
	public static final String TWeekdayIf = "是否工作日";
	public static final String TPathType = "路径类型";//不在total中显示
	public static final String TPeakIf = "出行时间段";
	public static final String TGetinStationTime = "进站刷卡时刻";
	public static final String TArriveStationTime1 = "到达站台时刻";
	public static final String TTrainDire1 = "方向";
	public static final String TShift1 = "班次";
	public static final String TTrainStartingTime1 = "列车开行时刻";
	public static final String TGetoffStation1 = "下车站";
	public static final String TGetoffStationTime1 = "下车时刻";
	public static final String TTransferLine1 = "换入线路";
	public static final String TArriveStationTime2 = "到达站台时刻2";
	public static final String TTrainDire2 = "方向2";
	public static final String TShift2 = "班次2";
	public static final String TTrainStartingTime2 = "列车开行时刻2";
	public static final String TGetoffStation2 = "下车站2";
	public static final String TGetoffStationTime2 = "下车时刻2";
	public static final String TTransferLine2 = "换入线路2";
	public static final String TArriveStationTime3 = "到达站台时刻3";
	public static final String TTrainDire3 = "方向3";
	public static final String TShift3 = "班次3";
	public static final String TTrainStartingTime3 = "列车开行时刻3";
	public static final String TGetoffStation3 = "下车站3";
	public static final String TGetoffStationTime3 = "下车时刻3";
	public static final String TGetoutStationTime = "出站刷卡时刻";
	
	//建表
	public static final String SQL_CREATE_TABLE_TODSURVEYINFO = "create table if not exists "
			+ODSurveyEntity.TABLE_NAME_TODSURVEYINFO+" (" + _ID + " INTEGER PRIMARY KEY"+AppConfig.COMMA_SEP
			+ODSurveyEntity.TName+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TDate+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TCardNum+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TIDNo+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TGetinStation+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TGetinStationLine+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TTransferCount+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TStationCount+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TDistanceTotal+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TWeekdayIf+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TPathType+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TPeakIf+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			
			+ODSurveyEntity.TGetinStationTime+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TArriveStationTime1+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TTrainDire1+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TShift1+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TTrainStartingTime1+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TGetoffStation1+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TGetoffStationTime1+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TTransferLine1+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TArriveStationTime2+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TTrainDire2+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TShift2+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TTrainStartingTime2+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TGetoffStation2+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TGetoffStationTime2+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TTransferLine2+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			
			+ODSurveyEntity.TArriveStationTime3+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TTrainDire3+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TShift3+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TTrainStartingTime3+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TGetoffStation3+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TGetoffStationTime3+ AppConfig.TYPE_TEXT + AppConfig.COMMA_SEP
			+ODSurveyEntity.TGetoutStationTime+ AppConfig.TYPE_TEXT 
			+ ");";
	
	
	public static int getID() {
		return ID;
	}
	public static void setID(int iD) {
		ID = iD;
	}
	public String getRowID() {
		return rowID;
	}
	public void setRowID(String rowID) {
		this.rowID = rowID;
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
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getIDNo() {
		return IDNo;
	}
	public void setIDNo(String iDNo) {
		IDNo = iDNo;
	}
	public String getGetinStation() {
		return getinStation;
	}
	public void setGetinStation(String getinStation) {
		this.getinStation = getinStation;
	}
	public String getGetinStationLine() {
		return getinStationLine;
	}
	public void setGetinStationLine(String getinStationLine) {
		this.getinStationLine = getinStationLine;
	}
	public String getStationCount() {
		return stationCount;
	}
	public void setStationCount(String stationCount) {
		this.stationCount = stationCount;
	}
	public String getDistanceTotal() {
		return distanceTotal;
	}
	public void setDistanceTotal(String distanceTotal) {
		this.distanceTotal = distanceTotal;
	}
	public String getTimeTotal() {
		return timeTotal;
	}
	public void setTimeTotal(String timeTotal) {
		this.timeTotal = timeTotal;
	}
	public String getWeekdayIf() {
		return weekdayIf;
	}
	public void setWeekdayIf(String weekdayIf) {
		this.weekdayIf = weekdayIf;
	}
	public String getPathType() {
		return pathType;
	}
	public void setPathType(String pathType) {
		this.pathType = pathType;
	}
	public String getPeakIf() {
		return peakIf;
	}
	public void setPeakIf(String peakIf) {
		this.peakIf = peakIf;
	}
	public String getGetinStationTime() {
		return getinStationTime;
	}
	public void setGetinStationTime(String getinStationTime1) {
		this.getinStationTime = getinStationTime1;
	}
	public String getArriveStationTime1() {
		return arriveStationTime1;
	}
	public void setArriveStationTime1(String arriveStationTime1) {
		this.arriveStationTime1 = arriveStationTime1;
	}
	public String getTrainDire1() {
		return trainDire1;
	}
	public void setTrainDire1(String trainDire1) {
		this.trainDire1 = trainDire1;
	}
	public String getShift1() {
		return shift1;
	}
	public void setShift1(String shift1) {
		this.shift1 = shift1;
	}
	public String getTrainStartingTime1() {
		return trainStartingTime1;
	}
	public void setTrainStartingTime1(String trainStartingTime1) {
		this.trainStartingTime1 = trainStartingTime1;
	}
	public String getGetoffStation1() {
		return getoffStation1;
	}
	public void setGetoffStation1(String getoffStation1) {
		this.getoffStation1 = getoffStation1;
	}
	public String getGetoffStationTime1() {
		return getoffStationTime1;
	}
	public void setGetoffStationTime1(String getoffStationTime1) {
		this.getoffStationTime1 = getoffStationTime1;
	}
	public String getGetoutStationTime() {
		return getoutStationTime;
	}
	public void setGetoutStationTime(String getoutStationTime) {
		this.getoutStationTime = getoutStationTime;
	}
	public String getTransferLine1() {
		return transferLine1;
	}
	public void setTransferLine1(String transferLine1) {
		this.transferLine1 = transferLine1;
	}
	public String getArriveStationTime2() {
		return arriveStationTime2;
	}
	public void setArriveStationTime2(String arriveStationTime2) {
		this.arriveStationTime2 = arriveStationTime2;
	}
	public String getTrainDire2() {
		return trainDire2;
	}
	public void setTrainDire2(String trainDire2) {
		this.trainDire2 = trainDire2;
	}
	public String getShift2() {
		return shift2;
	}
	public void setShift2(String shift2) {
		this.shift2 = shift2;
	}
	public String getTrainStartingTime2() {
		return trainStartingTime2;
	}
	public void setTrainStartingTime2(String trainStartingTime2) {
		this.trainStartingTime2 = trainStartingTime2;
	}
	public String getGetoffStation2() {
		return getoffStation2;
	}
	public void setGetoffStation2(String getoffStation2) {
		this.getoffStation2 = getoffStation2;
	}
	public String getGetoffStationTime2() {
		return getoffStationTime2;
	}
	public void setGetoffStationTime2(String getoffStationTime2) {
		this.getoffStationTime2 = getoffStationTime2;
	}
	public String getTransferLine2() {
		return transferLine2;
	}
	public void setTransferLine2(String transferLine2) {
		this.transferLine2 = transferLine2;
	}
	public String getArriveStationTime3() {
		return arriveStationTime3;
	}
	public void setArriveStationTime3(String arriveStationTime3) {
		this.arriveStationTime3 = arriveStationTime3;
	}
	public String getTrainDire3() {
		return trainDire3;
	}
	public void setTrainDire3(String trainDire3) {
		this.trainDire3 = trainDire3;
	}
	public String getShift3() {
		return shift3;
	}
	public void setShift3(String shift3) {
		this.shift3 = shift3;
	}
	public String getTrainStartingTime3() {
		return trainStartingTime3;
	}
	public void setTrainStartingTime3(String trainStartingTime3) {
		this.trainStartingTime3 = trainStartingTime3;
	}
	public String getGetoffStation3() {
		return getoffStation3;
	}
	public void setGetoffStation3(String getoffStation3) {
		this.getoffStation3 = getoffStation3;
	}
	public String getGetoffStationTime3() {
		return getoffStationTime3;
	}
	public void setGetoffStationTime3(String getoffStationTime3) {
		this.getoffStationTime3 = getoffStationTime3;
	}
	public String getTransferCount() {
		return transferCount;
	}
	public void setTransferCount(String transferCount) {
		this.transferCount = transferCount;
	}

	public String getSequenceNO() {
		return sequenceNO;
	}

	public void setSequenceNO(String sequenceNO) {
		this.sequenceNO = sequenceNO;
	}

	public Boolean getFinished() {
		return isFinished;
	}

	public void setFinished(Boolean finished) {
		isFinished = finished;
	}
}
