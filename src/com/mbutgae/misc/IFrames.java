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
import javax.swing.*;

public class IFrames
{
  public static void main(String[] args)
  {
    try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
    catch(Exception ex){}
    JFrame f=new JFrame();
    JSplitPane x = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
      createFrame("Left"), createFrame("right") );
    x.setEnabled(true);
    f.setContentPane(x);
    f.setSize(300, 300);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }

  private static JInternalFrame createFrame(String title)
  {
    final JInternalFrame f1 = new JInternalFrame(title, true, true);
    f1.setVisible(true);
    f1.getContentPane().add(new JLabel(title));
    return f1;
  }
}