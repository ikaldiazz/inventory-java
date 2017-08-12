/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbut.img;

/**
 *
 * @author ALPABETAPINTAR
 */
import java.awt.Graphics;
 
import javax.swing.ImageIcon;
import javax.swing.JLabel;
 
/**
 * This is an extended version of a JLabel which draws its icon image using
 * the ImageDrawer utility.
 *
 * @author www.codejava.net
 *
 */
public class ScaledImageLabel extends JLabel {
    @Override
    protected void paintComponent(Graphics g) {
        ImageIcon icon = (ImageIcon) getIcon();
        if (icon != null) {
            ImageDrawer.drawScaledImage(icon.getImage(), this, g);
        }
    }
}