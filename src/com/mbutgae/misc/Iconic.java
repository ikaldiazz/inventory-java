/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbutgae.misc;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author ALPABETAPINTAR
 */
public class Iconic {
    ImageIcon imgicon; 
    Image img, newimg;
    JButton button = new JButton();
    
    public Iconic(){
        
    }
    
    public Iconic(String path, int size){
        
    }
    
    public Icon getIcon(String path, int size) {
        imgicon = new ImageIcon(path);
        img = imgicon.getImage();
        newimg = img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        imgicon = new ImageIcon(newimg);
        return imgicon;
    }
    
    public void setIconButton(JButton btn, String path, int size){
        btn = this.button;
        imgicon = new ImageIcon(path);
        img = imgicon.getImage();
        Image newimg = img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        imgicon = new ImageIcon(newimg);        
        Icon icon = imgicon;
        button.setIcon(icon);
        
    }
    
}
