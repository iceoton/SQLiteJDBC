package com.iceoton.managesqlite.model;


public class BusstopTable {
    final static String TABLE_NAME = "busstop";

    public static class Column {
        final static String _ID = "id";
        final static String _STOP_NAME = "stop_name";
        final static String _STOP_NAME_ENG = "stop_name_en";
        final static String _STOP_DESCRIPTION = "stop_description";
        final static String _LATITUDE= "latitude";
        final static String _LONGITUDE = "longitude";
        final static String _MODIFY_DATE = "modify_date";
        final static String _STATUS = "status";
    }
}
