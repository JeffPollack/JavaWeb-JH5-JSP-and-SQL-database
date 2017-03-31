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

public class CreateCities {

    public static void main(String args[]) throws IOException {

        Statement statement;
        FileReader fileRead = new FileReader(new File("cities.sql"));
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

            System.out.println("Processing file: cities.sql");
            for (int i = 0; i < sqlCom.length; i++) {
                statement.executeUpdate(sqlCom[i]);
            }
            buffRead.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            statement = con.createStatement();

            ResultSet result = statement.executeQuery("SELECT * FROM cities ORDER BY Population DESC;");
            ResultSetMetaData metaData = result.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] colNames = new String[columnCount];

            for (int i = 1; i <= columnCount; i++) {
                colNames[i - 1] = metaData.getColumnName(i);
            }
            System.out.printf("\n");
            System.out.print("Cities: ");
            for (int i = 0; i < columnCount; i++) {
                System.out.print(colNames[i]);
                if (i != (columnCount - 1)) {
                    System.out.print(", ");
                }
            }
            System.out.printf("\n===============================================================\n");
            while (result.next()) {
                String n = result.getString("CityName");
                String r = result.getString("StateName");
                int p = result.getInt("Population");
                System.out.println("CityName=" + n + " StateName=" + r + " Population=" + p);
            }
            System.out.printf("\n");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
