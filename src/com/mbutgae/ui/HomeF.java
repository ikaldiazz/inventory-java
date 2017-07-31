/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbutgae.ui;

import com.mbutgae.db.DatabaseConn;
import com.mbutgae.db.Parameter;
import com.mbutgae.misc.Clock;
import com.mbutgae.misc.HourTime;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import com.mbutgae.obj.User;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ALPABETAPINTAR
 */
public class HomeF extends javax.swing.JFrame {

    User u = new User();
    ResultSet rs;
    DatabaseConn db;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int maxHeight, maxWidth;
    Dimension windowsMaxState;

    /**
     * Creates new form HomeF
     */
    public HomeF() {
        db = new DatabaseConn(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB, new Parameter().IPHOST, new Parameter().PORT);
        initComponents();
        u.setUsername("manager");
        u.setRights("2");
        welcomelabel.setText("Selamat Datang " + u.getUsername());
        
        HourTime c1 = new HourTime(jamlbl);
        Thread t1 = new Thread(c1);
        t1.start();
        hari();
        
        setExtendedState(HomeF.MAXIMIZED_BOTH);
        windowsMaxState = this.screen();

    }

    public HomeF(User us) {
        db = new DatabaseConn(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB, new Parameter().IPHOST, new Parameter().PORT);
        initComponents();

        ImageIcon icon = new ImageIcon("./src/icon/maximize.png");
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);

        maximize.setIcon(icon);

        u.setUsername(us.getUsername());
        u.setRights(us.getRights());

        us.showStatus();

        welcomelabel.setText("Selamat Datang " + u.getUsername());
        HourTime c1 = new HourTime(jamlbl);
        Thread t1 = new Thread(c1);
        t1.start();
        hari();
        
        setExtendedState(HomeF.MAXIMIZED_BOTH);

    }

    public final Dimension screen() {
        Dimension a = new Dimension(0, 0);
        a.height = (int) screenSize.getWidth();
        a.width = (int) screenSize.getHeight();
        System.out.println("Width : " + a.height);
        System.out.println("Height : " + a.width);
        return a;
    }

    public Icon setIcon(String path, int size) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        return icon;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        minimize = new javax.swing.JLabel();
        close = new javax.swing.JLabel();
        maximize = new javax.swing.JLabel();
        PanelBottom = new javax.swing.JPanel();
        jamlbl = new javax.swing.JLabel();
        tanggallbl = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        rightPanel = new javax.swing.JPanel();
        desktop = new javax.swing.JDesktopPane();
        leftPanel = new javax.swing.JPanel();
        PanelStatus = new javax.swing.JPanel();
        welcomelabel = new javax.swing.JLabel();
        lbl_status = new javax.swing.JLabel();
        btn_start = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 102, 255));

        minimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/windows/minimise16.png"))); // NOI18N
        minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeMouseClicked(evt);
            }
        });

        close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/windows/close16.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });

        maximize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        maximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/windows/maximize16.png"))); // NOI18N
        maximize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maximizeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(minimize)
                .addGap(10, 10, 10)
                .addComponent(maximize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(close)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(maximize)
                    .addComponent(close)
                    .addComponent(minimize))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelBottom.setBackground(new java.awt.Color(0, 102, 255));

        jamlbl.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jamlbl.setForeground(new java.awt.Color(255, 255, 255));
        jamlbl.setText("TIME");
        jamlbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jamlblMouseClicked(evt);
            }
        });

        tanggallbl.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        tanggallbl.setForeground(new java.awt.Color(255, 255, 255));
        tanggallbl.setText("DAY");
        tanggallbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tanggallblMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelBottomLayout = new javax.swing.GroupLayout(PanelBottom);
        PanelBottom.setLayout(PanelBottomLayout);
        PanelBottomLayout.setHorizontalGroup(
            PanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jamlbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tanggallbl)
                .addContainerGap())
        );
        PanelBottomLayout.setVerticalGroup(
            PanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jamlbl)
                    .addComponent(tanggallbl))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rightPanel.setBackground(new java.awt.Color(0, 102, 255));
        rightPanel.setPreferredSize(new java.awt.Dimension(800, 0));

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rightPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rightPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jSplitPane1.setRightComponent(rightPanel);

        leftPanel.setBackground(new java.awt.Color(153, 255, 255));
        leftPanel.setPreferredSize(new java.awt.Dimension(250, 23));

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 322, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(leftPanel);

        PanelStatus.setBackground(new java.awt.Color(0, 102, 255));

        welcomelabel.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        welcomelabel.setForeground(new java.awt.Color(255, 255, 255));
        welcomelabel.setText("Welcome");
        welcomelabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                welcomelabelMouseClicked(evt);
            }
        });

        lbl_status.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        lbl_status.setForeground(new java.awt.Color(255, 255, 255));
        lbl_status.setText("STATUS");
        lbl_status.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_statusMouseClicked(evt);
            }
        });

        btn_start.setText("Start SERVER");
        btn_start.setPreferredSize(new java.awt.Dimension(73, 25));

        javax.swing.GroupLayout PanelStatusLayout = new javax.swing.GroupLayout(PanelStatus);
        PanelStatus.setLayout(PanelStatusLayout);
        PanelStatusLayout.setHorizontalGroup(
            PanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcomelabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_start, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(lbl_status)
                .addContainerGap())
        );
        PanelStatusLayout.setVerticalGroup(
            PanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(welcomelabel)
                    .addComponent(lbl_status))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStatusLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelBottom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(PanelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        Icon icon = setIcon("./src/icon/close-icon.png", 50);
        u.showStatus();
        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Apakah anda ingin Keluar dari Program?",
                "Exit Program",
                JOptionPane.YES_NO_OPTION,
                0,
                icon);
        if (selectedOption == JOptionPane.YES_OPTION) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            System.out.println(dateFormat.format(date));

            String[] kolom = {"user_id", "date", "operation"};
            String[] isi = {u.getUsername(), dateFormat.format(date), "0"};
            System.out.println(db.queryInsert("user_log", kolom, isi));
            System.exit(0);
        }
    }//GEN-LAST:event_closeMouseClicked

    private void minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseClicked
        this.setState(Frame.ICONIFIED);
        //Another way to minimize
        //this.setExtendedState(Frame.ICONIFIED);
    }//GEN-LAST:event_minimizeMouseClicked

    private void maximizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maximizeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_maximizeMouseClicked

    private void jamlblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jamlblMouseClicked
        desktop.removeAll();
        LoginF log = new LoginF();
        desktop.add(log);
        log.setVisible(true);
    }//GEN-LAST:event_jamlblMouseClicked

    private void welcomelabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_welcomelabelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_welcomelabelMouseClicked

    private void tanggallblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tanggallblMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggallblMouseClicked

    private void lbl_statusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_statusMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_statusMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeF().setVisible(true);
            }
        });
    }
    public void hari() {
        Date skrg = new Date();
        SimpleDateFormat tgl = new SimpleDateFormat("EEEE, dd MMM yyyy");
//        SimpleDateFormat hari = new SimpleDateFormat("HH:mm");
//        jamlbl.setText(hari.format(skrg));
        tanggallbl.setText(tgl.format(skrg));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelBottom;
    private javax.swing.JPanel PanelStatus;
    public static javax.swing.JButton btn_start;
    private javax.swing.JLabel close;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel jamlbl;
    public static javax.swing.JLabel lbl_status;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JLabel maximize;
    private javax.swing.JLabel minimize;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JLabel tanggallbl;
    private javax.swing.JLabel welcomelabel;
    // End of variables declaration//GEN-END:variables
}
