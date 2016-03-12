package com.iceoton.managesqlite;

import com.google.maps.model.LatLng;
import com.iceoton.managesqlite.map.GoogleMatrixRequest;
import com.iceoton.managesqlite.model.Busstop;
import com.iceoton.managesqlite.model.Elements;
import com.iceoton.managesqlite.sqlite.SQLiteDAO;

import java.util.ArrayList;
import java.util.Scanner;

public class MainApplication {
    public static void main(String args[]) {
        SQLiteDAO dao = new SQLiteDAO();
        System.out.print("Bus line id:");
        Scanner scanner = new Scanner(System.in);
        int busLineId = scanner.nextInt();
        String stopList = dao.getBusStopList(busLineId);
        stopList = stopList.substring(1, stopList.length()-1); // delete ',' at first and end
        System.out.println("Bus stop list = " + stopList);
        System.out.println("\n---------------------- CSV ----------------------");

        String[] arrayStationId = stopList.split(",");
        ArrayList<Busstop> busstops = new ArrayList<Busstop>(arrayStationId.length);

        //System.out.println("id,stop_name,stop_name_en,stop_description,latitude,longitude,modify_date");
        for (int i = 0; i < arrayStationId.length; i++) {
            Busstop busstop = dao.getBusStop(Integer.valueOf(arrayStationId[i]));
            if (busstop != null) {
                //System.out.println(busstop.toString());
                busstops.add(busstop);
            }
        }
        ArrayList<Elements> elementses = new ArrayList<Elements>();
        elementses.add(new Elements(0,0));
        GoogleMatrixRequest googleMatrixRequest = new GoogleMatrixRequest();
        for (int i = 1; i< busstops.size(); i++){
            LatLng start = new LatLng(busstops.get(i-1).getLatitude(),busstops.get(i-1).getLongitude());
            LatLng end = new LatLng(busstops.get(i).getLatitude(),busstops.get(i).getLongitude());
            Elements elements = googleMatrixRequest.getDistanceDuration(start, end);
            elementses.add(elements);
        }

        System.out.println("id,name,name_en,lat,lng,distance,duration");
        for (int i = 0; i< busstops.size(); i++) {
            Busstop busstop = busstops.get(i);
            Elements elements = elementses.get(i);
            String out = busstop.getId() + "," + busstop.getStopName() + "," +busstop.getStopNameEng() +
                    "," + busstop.getLatitude() + "," +busstop.getLongitude() + "," +
                    elements.getDistance() + "," + elements.getDuration();

            System.out.println(out);
        }


        dao.closeConnection();
    }


}
