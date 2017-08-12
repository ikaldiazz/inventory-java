/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbutgae.ui;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author ALPABETAPINTAR
 */
public class PictureOpr {

    
    
    FileDialog fd;
    String lastfolder;

    PictureOpr(JTextField tf, JFrame fr, String title) {
        String userhome;
        userhome = System.getProperty("user.home")+File.separator+"Pictures";
        
        //lastfolder = userhome;
        fd = new FileDialog(fr, title, FileDialog.LOAD);
        System.out.println(System.getProperty("user.home"));
        //System.out.println("Last Folder:"+lastfolder);
        System.out.println("User home:"+userhome);

        if (lastfolder == null) {
            fd.setDirectory(userhome);
        } else {
            fd.setDirectory(lastfolder);
        }
        System.out.println("++++++++++++++");
        System.out.println("Last Folder:"+lastfolder);
        System.out.println("User home:"+userhome);
        //fd.setDirectory("C:\\");
        //fd.setFile("*.xml");
        fd.setVisible(true);
        String filename = fd.getFile();

        tf.setText(fd.getDirectory() + fd.getFile());
        if (filename == null) {
            System.out.println("You cancelled the choice");
        } else {
            System.out.println("You chose " + filename);
            lastfolder = fd.getDirectory();
            System.out.println(lastfolder);
        }
    }
    
    public static void main(String[] args) {
        
    }

}
