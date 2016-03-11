package com.iceoton.managesqlite;

import com.iceoton.managesqlite.sqlite.SQLiteDAO;

import java.util.Scanner;

public class SQLiteJDBC {
    public static void main(String args[]) {
        System.out.print("Bus line id:");
        Scanner scanner = new Scanner(System.in);
        int busLineId = scanner.nextInt();

        SQLiteDAO dao = new SQLiteDAO();
        String stopList = dao.getBusStopList(busLineId);
        System.out.println("Bus stop list = " + stopList);
        System.out.println();
        System.out.println("Operation done successfully");
    }
}
