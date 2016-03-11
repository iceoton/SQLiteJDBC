package com.iceoton.managesqlite.sqlite;

import com.iceoton.managesqlite.model.Busstop;

import java.sql.*;
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

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return stopList;
    }

    public Busstop getBusStop(int Id){
        Busstop busstop = null;
        String sql = "SELECT * FROM busstop WHERE id=" + Id;
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                busstop = new Busstop();
                busstop.fromResultSet(rs);
            }
            rs.close();
            statement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return busstop;
    }


}
