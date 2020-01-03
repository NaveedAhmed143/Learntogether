package com.example.learntogether;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbclass{

    String classs = "com.mysql.jdbc.Driver";
    String dburl = "jdbc:mysql://192.168.0.106:3306/centerofexecellence";
    String uname = "bilal";
    String password = "bilal1234";

    @SuppressLint("NewApi")
    public Connection ConnectToDB() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        try {
            Class.forName(classs);
            conn = DriverManager.getConnection(dburl, uname, password);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }

        return conn;
    }
}
