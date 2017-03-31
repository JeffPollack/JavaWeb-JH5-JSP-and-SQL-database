/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JH5_jdpollack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class CreateStates {

    public static void main(String args[]) throws IOException {

        Statement statement;
        FileReader fileRead = new FileReader(new File("states.sql"));
        BufferedReader buffRead = new BufferedReader(fileRead);
        String s = "";
        StringBuilder sb = new StringBuilder();
        while ((s = buffRead.readLine()) != null) {
            sb.append(s);
        }
        String[] sqlCom = sb.toString().split(";");
        Connection con = MyConnection.getConnection(args);
        try {
            statement = con.createStatement();

            System.out.println("Processing file: states.sql");
            for (int i = 0; i < sqlCom.length; i++) {
                statement.executeUpdate(sqlCom[i]);
            }
            buffRead.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            statement = con.createStatement();

            ResultSet result = statement.executeQuery("SELECT * FROM states ORDER BY Population DESC;");
            ResultSetMetaData metaData = result.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] colNames = new String[columnCount];

            for (int i = 1; i <= columnCount; i++) {
                colNames[i - 1] = metaData.getColumnName(i);
            }
            System.out.printf("\n");
            System.out.print("States: ");
            for (int i = 0; i < columnCount; i++) {
                System.out.print(colNames[i]);
                if (i != (columnCount - 1)) {
                    System.out.print(", ");
                }
            }
            System.out.printf("\n==========================================================================\n");
            while (result.next()) {
                String n = result.getString("StateName");
                String r = result.getString("Region");
                String l = result.getString("LargestCity");
                String c = result.getString("Capital");
                int p = result.getInt("Population");
                System.out.println("Name=" + n + " Region=" + r + " LargestCity=" + l + " Capital=" + c + " Population=" + p);
            }
            System.out.printf("\n");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
