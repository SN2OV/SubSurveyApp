package com.example.subsurvey.personalSetting;

import java.io.Serializable;

public class UserEntity implements Serializable{

	private String name;
	private String date;
	private String station;
	private String line;
	private String weekdayIf;
	private String tsLoc;
	private String tsTimePeriod;
	private String wsTime = "早高峰";
	private String wsType = "进站-出站";
	private String wsGateLoc;
	private String wsGateLine;
	private String wsOffDire;
	private String wsOffLine;
	private String wsOnDire;
	private String wsOnLine;
	private String wsLine;
	private String wslStartGLoc;
	private String wslGLine;
	private String wslEndGLoc;
	private String wslDireSToE;
	private String wslTrainLine;
	private String wslDireEToS;
	private String wslOffDire;
	private String wslOffLine;
	private String wslOnDire;
	private String wslOnLine;
	private String wslStartLine;
	private String wslStartLineStartDire;
	private String wslStartLineEndDire;
	private String wslEndLine;
	private String wslEndLineStartDire;
	private String wslEndLineEndDire;
	private String odCardNum;
	private String odIDNum;
	private String odDistance;
	private String odType = "无换乘";
	private String odStationCount;
	private String odTimePeriod = "平峰";
	private String odlDire1;
	private String odlOffStation1;
	private String odlTransLine2;
	private String odlDire2;
	private String odlOffStation2;
	private String odlTransLine3;
	private String odlDire3;
	private String odlOffStation3;
	private String ssCarriageLoc;
	private String ssTimePeriod = "平峰";
	private String ssFromLine;
	private String ssFromLoc;
	private String sslGoinStationLine;
	private String sslGoinStationLoc;
	private String sslTransferLine;
	private String sslTransferLoc;
	private String ssToLine;
	private String ssToLoc;
	private String ssType = "进站";
	private String rsTimePeriod = "07:00~09:00";
	private String rsCarriageLoc;
	private String rsDire;
	private String rsType = "上车";
	
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
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getWeekdayIf() {
		return weekdayIf;
	}
	public void setWeekdayIf(String weekdayIf) {
		this.weekdayIf = weekdayIf;
	}
	public String getTsLoc() {
		return tsLoc;
	}
	public void setTsLoc(String tsLoc) {
		this.tsLoc = tsLoc;
	}
	public String getWsTime() {
		return wsTime;
	}
	public void setWsTime(String wsTime) {
		this.wsTime = wsTime;
	}
	public String getWsType() {
		return wsType;
	}
	public void setWsType(String wsType) {
		this.wsType = wsType;
	}
	public String getWsGateLoc() {
		return wsGateLoc;
	}
	public void setWsGateLoc(String wsGateLoc) {
		this.wsGateLoc = wsGateLoc;
	}
	public String getWsGateLine() {
		return wsGateLine;
	}
	public void setWsGateLine(String wsGateLine) {
		this.wsGateLine = wsGateLine;
	}
	public String getWsOffDire() {
		return wsOffDire;
	}
	public void setWsOffDire(String wsOffDire) {
		this.wsOffDire = wsOffDire;
	}
	public String getWsOffLine() {
		return wsOffLine;
	}
	public void setWsOffLine(String wsOffLine) {
		this.wsOffLine = wsOffLine;
	}
	public String getWsOnDire() {
		return wsOnDire;
	}
	public void setWsOnDire(String wsOnDire) {
		this.wsOnDire = wsOnDire;
	}
	public String getWsOnLine() {
		return wsOnLine;
	}
	public void setWsOnLine(String wsOnLine) {
		this.wsOnLine = wsOnLine;
	}
	public String getWsLine() {
		return wsLine;
	}
	public void setWsLine(String wsLine) {
		this.wsLine = wsLine;
	}
	public String getWslStartGLoc() {
		return wslStartGLoc;
	}
	public void setWslStartGLoc(String wslStartGLoc) {
		this.wslStartGLoc = wslStartGLoc;
	}
	public String getWslGLine() {
		return wslGLine;
	}
	public void setWslGLine(String wslGLine) {
		this.wslGLine = wslGLine;
	}
	public String getWslEndGLoc() {
		return wslEndGLoc;
	}
	public void setWslEndGLoc(String wslEndGLoc) {
		this.wslEndGLoc = wslEndGLoc;
	}
	public String getWslDireSToE() {
		return wslDireSToE;
	}
	public void setWslDireSToE(String wslDireSToE) {
		this.wslDireSToE = wslDireSToE;
	}
	public String getWslTrainLine() {
		return wslTrainLine;
	}
	public void setWslTrainLine(String wslTrainLine) {
		this.wslTrainLine = wslTrainLine;
	}
	public String getWslDireEToS() {
		return wslDireEToS;
	}
	public void setWslDireEToS(String wslDireEToS) {
		this.wslDireEToS = wslDireEToS;
	}
	public String getWslOffDire() {
		return wslOffDire;
	}
	public void setWslOffDire(String wslOffDire) {
		this.wslOffDire = wslOffDire;
	}
	public String getWslOffLine() {
		return wslOffLine;
	}
	public void setWslOffLine(String wslOffLine) {
		this.wslOffLine = wslOffLine;
	}
	public String getWslOnDire() {
		return wslOnDire;
	}
	public void setWslOnDire(String wslOnDire) {
		this.wslOnDire = wslOnDire;
	}
	public String getWslOnLine() {
		return wslOnLine;
	}
	public void setWslOnLine(String wslOnLine) {
		this.wslOnLine = wslOnLine;
	}

	public String getWslStartLine() {
		return wslStartLine;
	}

	public void setWslStartLine(String wslStartLine) {
		this.wslStartLine = wslStartLine;
	}

	public String getWslStartLineStartDire() {
		return wslStartLineStartDire;
	}

	public void setWslStartLineStartDire(String wslStartLineStartDire) {
		this.wslStartLineStartDire = wslStartLineStartDire;
	}

	public String getWslStartLineEndDire() {
		return wslStartLineEndDire;
	}

	public void setWslStartLineEndDire(String wslStartLineEndDire) {
		this.wslStartLineEndDire = wslStartLineEndDire;
	}

	public String getWslEndLine() {
		return wslEndLine;
	}

	public void setWslEndLine(String wslEndLine) {
		this.wslEndLine = wslEndLine;
	}

	public String getWslEndLineStartDire() {
		return wslEndLineStartDire;
	}

	public void setWslEndLineStartDire(String wslEndLineStartDire) {
		this.wslEndLineStartDire = wslEndLineStartDire;
	}

	public String getWslEndLineEndDire() {
		return wslEndLineEndDire;
	}

	public void setWslEndLineEndDire(String wslEndLineEndDire) {
		this.wslEndLineEndDire = wslEndLineEndDire;
	}

	public String getODCardNum() {
		return odCardNum;
	}
	public void setODCardNum(String odCardNum) {
		this.odCardNum = odCardNum;
	}
	public String getODIDNum() {
		return odIDNum;
	}
	public void setODIDNum(String odIDNum) {
		this.odIDNum = odIDNum;
	}
	public String getODDistance() {
		return odDistance;
	}
	public void setODDistance(String odDistance) {
		this.odDistance = odDistance;
	}
	public String getOdCardNum() {
		return odCardNum;
	}
	public void setOdCardNum(String odCardNum) {
		this.odCardNum = odCardNum;
	}
	public String getOdIDNum() {
		return odIDNum;
	}
	public void setOdIDNum(String odIDNum) {
		this.odIDNum = odIDNum;
	}
	public String getOdDistance() {
		return odDistance;
	}
	public void setOdDistance(String odDistance) {
		this.odDistance = odDistance;
	}
	public String getOdlDire1() {
		return odlDire1;
	}
	public void setOdlDire1(String odlDire1) {
		this.odlDire1 = odlDire1;
	}
	public String getOdlOffStation1() {
		return odlOffStation1;
	}
	public void setOdlOffStation1(String odlOffStation1) {
		this.odlOffStation1 = odlOffStation1;
	}
	public String getOdlDire2() {
		return odlDire2;
	}
	public void setOdlDire2(String odlDire2) {
		this.odlDire2 = odlDire2;
	}
	public String getOdlOffStation2() {
		return odlOffStation2;
	}
	public void setOdlOffStation2(String odlOffStation2) {
		this.odlOffStation2 = odlOffStation2;
	}
	public String getOdlDire3() {
		return odlDire3;
	}
	public void setOdlDire3(String odlDire3) {
		this.odlDire3 = odlDire3;
	}
	public String getOdlOffStation3() {
		return odlOffStation3;
	}
	public void setOdlOffStation3(String odlOffStation3) {
		this.odlOffStation3 = odlOffStation3;
	}
	public String getOdlTransLine2() {
		return odlTransLine2;
	}
	public void setOdlTransLine2(String odlTransLine2) {
		this.odlTransLine2 = odlTransLine2;
	}
	public String getOdlTransLine3() {
		return odlTransLine3;
	}
	public void setOdlTransLine3(String odlTransLine3) {
		this.odlTransLine3 = odlTransLine3;
	}
	public String getOdType() {
		return odType;
	}
	public void setOdType(String odType) {
		this.odType = odType;
	}
	public String getOdStationCount() {
		return odStationCount;
	}
	public void setOdStationCount(String odStationCount) {
		this.odStationCount = odStationCount;
	}
	public String getOdTimePeriod() {
		return odTimePeriod;
	}
	public void setOdTimePeriod(String odTimePeriod) {
		this.odTimePeriod = odTimePeriod;
	}
	public String getSsCarriageLoc() {
		return ssCarriageLoc;
	}
	public void setSsCarriageLoc(String ssCarriageLoc) {
		this.ssCarriageLoc = ssCarriageLoc;
	}
	public String getSsTimePeriod() {
		return ssTimePeriod;
	}
	public void setSsTimePeriod(String ssTimePeriod) {
		this.ssTimePeriod = ssTimePeriod;
	}
	public String getSsFromLine() {
		return ssFromLine;
	}
	public void setSsFromLine(String ssFromLine) {
		this.ssFromLine = ssFromLine;
	}
	public String getSsFromLoc() {
		return ssFromLoc;
	}
	public void setSsFromLoc(String ssFromLoc) {
		this.ssFromLoc = ssFromLoc;
	}
	public String getSsToLine() {
		return ssToLine;
	}
	public void setSsToLine(String ssToLine) {
		this.ssToLine = ssToLine;
	}
	public String getSsToLoc() {
		return ssToLoc;
	}
	public void setSsToLoc(String ssToLoc) {
		this.ssToLoc = ssToLoc;
	}
	public String getSslGoinStationLine() {
		return sslGoinStationLine;
	}
	public void setSslGoinStationLine(String sslGoinStationLine) {
		this.sslGoinStationLine = sslGoinStationLine;
	}
	public String getSslGoinStationLoc() {
		return sslGoinStationLoc;
	}
	public void setSslGoinStationLoc(String sslGoinStationLoc) {
		this.sslGoinStationLoc = sslGoinStationLoc;
	}
	public String getSslTransferLine() {
		return sslTransferLine;
	}
	public void setSslTransferLine(String sslTransferLine) {
		this.sslTransferLine = sslTransferLine;
	}
	public String getSslTransferLoc() {
		return sslTransferLoc;
	}
	public void setSslTransferLoc(String sslTransferLoc) {
		this.sslTransferLoc = sslTransferLoc;
	}
	public String getSsType() {
		return ssType;
	}
	public void setSsType(String ssType) {
		this.ssType = ssType;
	}

	public String getRsTimePeriod() {
		return rsTimePeriod;
	}

	public void setRsTimePeriod(String rsTimePeriod) {
		this.rsTimePeriod = rsTimePeriod;
	}

	public String getRsCarriageLoc() {
		return rsCarriageLoc;
	}

	public void setRsCarriageLoc(String rsCarriageLoc) {
		this.rsCarriageLoc = rsCarriageLoc;
	}

	public String getRsDire() {
		return rsDire;
	}

	public void setRsDire(String rsDire) {
		this.rsDire = rsDire;
	}

	public String getTsTimePeriod() {
		return tsTimePeriod;
	}

	public void setTsTimePeriod(String tsTimePeriod) {
		this.tsTimePeriod = tsTimePeriod;
	}

	public String getRsType() {
		return rsType;
	}

	public void setRsType(String rsType) {
		this.rsType = rsType;
	}
}
