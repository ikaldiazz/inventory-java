/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbut.img;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author ALPABETAPINTAR
 */
public class ImagePanel extends JPanel {

    private Image image = null;

    public void setImage(Image img) {
        image = img;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {

            int imgWidth, imgHeight;
            double contRatio = (double) getWidth() / (double) getHeight();
            double imgRatio =  (double) image.getWidth(this) / (double) image.getHeight(this);

            //width limited
            if(contRatio < imgRatio){
                imgWidth = getWidth();
                imgHeight = (int) (getWidth() / imgRatio);

            //height limited
            }else{
                imgWidth = (int) (getHeight() * imgRatio);
                imgHeight = getHeight();
            }

            //to center
            int x = (int) (((double) getWidth() / 2) - ((double) imgWidth / 2));
            int y = (int) (((double) getHeight()/ 2) - ((double) imgHeight / 2));

            g.drawImage(image, x, y, imgWidth, imgHeight, this);
        }
    }
    
    
    
}