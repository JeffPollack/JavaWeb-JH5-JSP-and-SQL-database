/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JH5_jdpollack;

import java.sql.*;

public class SelectJoin {

    public static void main(String args[]){

        Statement statement;
        Connection con = MyConnection.getConnection(args);
        try {
            statement = con.createStatement();

            ResultSet result = statement.executeQuery("SELECT c.*, s.Region "
                    + "FROM cities AS c "
                    + "JOIN states AS s "
                    + "WHERE c.StateName=s.StateName "
                    + "ORDER BY Population DESC;");
            ResultSetMetaData metaData = result.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] colNames = new String[columnCount];

            for (int i = 1; i <= columnCount; i++) {
                colNames[i - 1] = metaData.getColumnName(i);
            }
            System.out.printf("\n");
            System.out.print("Cities join States: ");
            for (int i = 0; i < columnCount; i++) {
                System.out.print(colNames[i]);
                if (i != (columnCount - 1)) {
                    System.out.print(", ");
                }
            }
            System.out.printf("\n===============================================================\n");
            while (result.next()) {
                String n = result.getString("c.CityName");
                int p = result.getInt("c.Population");
                String s = result.getString("c.StateName");
                String r = result.getString("s.Region");

                System.out.println("CityName=" + n + " Population=" + p + " StateName=" + s + " Region=" + r);
            }
            System.out.printf("\n");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
