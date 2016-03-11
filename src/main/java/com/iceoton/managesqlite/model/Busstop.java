package com.iceoton.managesqlite.model;


import java.sql.ResultSet;
import java.sql.SQLException;

public class Busstop {
    int id;
    String stopName;
    String stopNameEng;
    String stopDescription;
    float latitude;
    float longitude;
    String modifyDate;

    public void fromResultSet(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt(BusstopTable.Column._ID);
            this.stopName = resultSet.getString(BusstopTable.Column._STOP_NAME);
            this.stopNameEng = resultSet.getString(BusstopTable.Column._STOP_NAME_ENG);
            this.stopDescription = resultSet.getString(BusstopTable.Column._STOP_DESCRIPTION);
            this.latitude = resultSet.getFloat(BusstopTable.Column._LATITUDE);
            this.longitude = resultSet.getFloat(BusstopTable.Column._LONGITUDE);
            this.modifyDate = resultSet.getString(BusstopTable.Column._MODIFY_DATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String showText = id + "," + stopName + "," + stopNameEng + "," + stopDescription + "," + latitude + "," + longitude + "," + modifyDate;

        return showText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getStopNameEng() {
        return stopNameEng;
    }

    public void setStopNameEng(String stopNameEng) {
        this.stopNameEng = stopNameEng;
    }

    public String getStopDescription() {
        return stopDescription;
    }

    public void setStopDescription(String stopDescription) {
        this.stopDescription = stopDescription;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
}
