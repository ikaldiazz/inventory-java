/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbutgae.db;


import java.net.Socket;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConn {

    Connection connection;
    Statement statement;
    String SQL;
    String url;
    String username;
    String password;
    Socket client;
    int Port;
    String Host;

    public DatabaseConn(String url, String username, String password, String Host, int Port) {

        this.url = url;
        this.username = username;
        this.password = password;
        this.Host = Host;
        this.Port = Port;
    }

    public Connection koneksiDatabase(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }catch (NullPointerException |SQLException | ClassNotFoundException e) {
            //System.err.println(""+e.getMessage()+"\n\n");
            //getRootCause(e).getMessage());
            System.out.println(""+getRootCause(e).getMessage());
            //e.printStackTrace();
            //
        } 
        
        return connection;
    }

    public Connection closeKoneksi() {

        try {
            connection.close();
        } catch (Exception e) {
            System.err.println(""+e.getMessage()+"\n\n");
            e.printStackTrace();
        }
        return connection;
    }

    public ResultSet eksekusiQuery(String sql) {
        koneksiDatabase();
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            System.out.println(sql);
        } catch (SQLException ex) {
            ex.toString();
            System.err.println(""+ex.getMessage()+"\n\n");
            ex.printStackTrace();
        }
        return resultSet;
    }

    public String eksekusiUpdate(String sql) {
        koneksiDatabase();
        String result = "";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            result = sql;

        } catch (SQLException ex) {
            result = ex.toString();
            System.err.println(""+ex.getMessage()+"\n\n");
            ex.printStackTrace();
        }
        return result;

    }

//Fungsi untuk eksekusi query select semua kolom
    public ResultSet querySelectAll(String namaTabel) {

        koneksiDatabase();
        SQL = "SELECT * FROM " + namaTabel;
        System.out.println(SQL);
        return this.eksekusiQuery(SQL);

    }

//Overload fungsi untuk eksekusi query select semua kolom dengan where
    public ResultSet querySelectAll(String namaTabel, String kondisi) {

        koneksiDatabase();
        SQL = "SELECT * FROM " + namaTabel + " WHERE " + kondisi;
        return this.eksekusiQuery(SQL);

    }

//Fungsi untuk eksekusi query select dengan kolom spesifik
    public ResultSet querySelect(String[] namaKolom, String namaTabel) {

        koneksiDatabase();
        int i;
        SQL = "SELECT ";

        for (i = 0; i <= namaKolom.length - 1; i++) {
            SQL += namaKolom[i];
            if (i < namaKolom.length - 1) {
                SQL += ",";
            }
        }

        SQL += " FROM " + namaTabel;
        return this.eksekusiQuery(SQL);

    }

//Overload fungsi untuk eksekusi query select dengan kolom spesifik dengan where
    public ResultSet fcSelectCommand(String[] namaKolom, String namaTabel, String kondisi) {

        koneksiDatabase();
        int i;
        SQL = "SELECT ";

        for (i = 0; i <= namaKolom.length - 1; i++) {
            SQL += namaKolom[i];
            if (i < namaKolom.length - 1) {
                SQL += ",";
            }
        }

        SQL += " FROM " + namaTabel + " WHERE " + kondisi;
        return this.eksekusiQuery(SQL);

    }

    public String queryInsert(String namaTabel, String[] isiTabel) {

        koneksiDatabase();
        int i;
        SQL = "INSERT INTO " + namaTabel + " VALUES(";

        for (i = 0; i <= isiTabel.length - 1; i++) {
            SQL += "'" + isiTabel[i] + "'";
            if (i < isiTabel.length - 1) {
                SQL += ",";
            }
        }

        SQL += ")";
        return this.eksekusiUpdate(SQL);

    }
//Fungsi eksekusi query insert

    public String queryInsert(String namaTabel, String[] namaKolom, String[] isiTabel) {

        koneksiDatabase();
        int i;
        SQL = "INSERT INTO " + namaTabel + "(";
        for (i = 0; i <= namaKolom.length - 1; i++) {
            SQL += namaKolom[i];
            if (i < namaKolom.length - 1) {
                SQL += ",";
            }
        }
        SQL += ") VALUES(";
        for (i = 0; i <= isiTabel.length - 1; i++) {
            SQL += "'" + isiTabel[i] + "'";
            if (i < isiTabel.length - 1) {
                SQL += ",";
            }
        }

        SQL += ")";
        return this.eksekusiUpdate(SQL);

    }

//Fungsi eksekusi query update
    public String queryUpdate(String namaTabel, String[] namaKolom, String[] isiTabel, String kondisi) {

        koneksiDatabase();
        int i;
        SQL = "UPDATE " + namaTabel + " SET ";

        for (i = 0; i <= namaKolom.length - 1; i++) {
            SQL += namaKolom[i] + "='" + isiTabel[i] + "'";
            if (i < namaKolom.length - 1) {
                SQL += ",";
            }
        }

        SQL += " WHERE " + kondisi;
        return this.eksekusiUpdate(SQL);

    }

//Fungsi eksekusi query delete
    public String queryDelete(String namaTabel) {

        koneksiDatabase();
        SQL = "DELETE FROM " + namaTabel;
        return this.eksekusiUpdate(SQL);

    }

//Overload fungsi eksekusi query delete dengan where
    public String queryDelete(String namaTabel, String kondisi) {

        koneksiDatabase();
        SQL = "DELETE FROM " + namaTabel + " WHERE " + kondisi;
        return this.eksekusiUpdate(SQL);

    }
    
    
    public static Throwable getRootCause(Throwable throwable) {
    if (throwable.getCause() != null)
        return getRootCause(throwable.getCause());

    return throwable;
}
}
