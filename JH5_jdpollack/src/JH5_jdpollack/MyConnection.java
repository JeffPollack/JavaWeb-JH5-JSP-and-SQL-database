/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JH5_jdpollack;

import java.sql.*;

public class MyConnection {

    public static Connection getConnection(String args[]) {

        Connection con = null;
        String urlStr = null;

        if (args.length < 2) {
            System.out.println("You need to enter:");
            System.out.println("         arg[0] = Userid, arg[1] = password");
            return con;
        }

        try {
            //Load the Driver Class Now       
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            urlStr = "jdbc:mysql://russet.wccnet.edu/" + args[0]
                    + "?user=" + args[0] + "&password=" + args[1];
            System.out.println("Connecting to : " + urlStr);
            con = DriverManager.getConnection(urlStr);

        } catch (SQLException ex) {
            System.err.println("SQLException(" + urlStr + "): " + ex);
        } catch (Exception ex) {
            System.err.println("Exception(" + urlStr + "): " + ex);
        }

        return con;
    }
}
