package com.iceoton.managesqlite;

import com.iceoton.managesqlite.model.Busstop;
import com.iceoton.managesqlite.sqlite.SQLiteDAO;

import java.util.Scanner;

public class SQLiteJDBC {
    public static void main(String args[]) {
        SQLiteDAO dao = new SQLiteDAO();
        System.out.print("Bus line id:");
        Scanner scanner = new Scanner(System.in);
        int busLineId = scanner.nextInt();
        String stopList = dao.getBusStopList(busLineId);
        stopList = stopList.substring(1, stopList.length()-1); // delete ',' at first and end
        System.out.println("Bus stop list = " + stopList);
        System.out.println();

        String[] arrayStation = stopList.split(",");
        Busstop busstop;
        System.out.println("id,stop_name,stop_name_en,stop_description,latitude,longitude,modify_date");
        for (String strId : arrayStation) {
            busstop = dao.getBusStop(Integer.valueOf(strId));
            if (busstop != null) {
                System.out.println(busstop.toString());
            }

        }

        dao.closeConnection();
    }


}
