/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbutgae.db;

import com.mbutgae.ui.HomeF;
import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ALPHABETA
 */
public class Server {

    static ServerSocket server;
    static Socket client;
    static boolean signal = false;

    /**
     * @param args the command line arguments
     */
    public Server() {

        try {
            System.out.println("Loading n Waitting");
            HomeF.btn_start.setText("...");
            server = new ServerSocket(Parameter.PORT);
            signal = true;
        } catch (Exception ex) {
            signal = false;
        }

        if (signal == true) {
            new terimaKoneksi("RunServer");
            HomeF.btn_start.setText("STOP");
            HomeF.lbl_status.setText("ON");
            HomeF.lbl_status.setBackground(Color.BLUE);
        }
    }

    public static class terimaKoneksi implements Runnable {

        Thread t;

        terimaKoneksi(String imeNiti) {
            t = new Thread(this, imeNiti);
            t.start();
        }

        public void run() {
            while (true) {
                try {
                    try {
                        client = server.accept();
                        System.out.println("Access Client.....");
                        HomeF.lbl_status.setText("CON..");
                        HomeF.lbl_status.setBackground(Color.GREEN);
                    } catch (Exception ex) {
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void serverOff() {
        try {
            server.close();
            HomeF.btn_start.setText("START");
            HomeF.lbl_status.setText("OFF");
            HomeF.lbl_status.setBackground(Color.RED);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
