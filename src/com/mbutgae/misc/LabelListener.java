/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbutgae.misc;

/**
 *
 * @author ALPABETAPINTAR
 */
/*
 * Class yang digunakan untuk menggerakkan text
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LabelListener implements ActionListener
{
	JLabel lbl;
	//Konstruktor
	public LabelListener(JLabel lblParam)
	{
		lbl = lblParam;
	} //Akhir Konstruktor
	
	public void actionPerformed(ActionEvent act)
	{
		String text = lbl.getText();
		String text2 = text.substring(1) + text.substring(0,1);
		lbl.setText(text2);
	}
}