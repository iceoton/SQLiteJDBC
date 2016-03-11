package com.iceoton.managesqlite.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SQLiteDAO {
    Connection connection = null;

    public SQLiteDAO() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:bmtadatabase.sqlite");
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void testConnection(){
        Statement stmt = null;
        try {
            System.out.print("Bus line id:");
            Scanner scanner = new Scanner(System.in);
            int busLineId = scanner.nextInt();

            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM busline WHERE id=" + busLineId + ";");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("bus_name");
                String busLine = rs.getString("bus_line");
                String stopList = rs.getString("busstop_list");
                System.out.println("ID = " + id);
                System.out.println("Name = " + name);
                System.out.println("Bus line = " + busLine);
                System.out.println("Bus stop list = " + stopList);
                System.out.println();
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public String getBusStopList(int busId) {
        String stopList = "";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT busstop_list FROM busline WHERE id=" + busId + ";");
            if (rs.next()) {
                stopList = rs.getString("busstop_list");
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return stopList;
    }


}
