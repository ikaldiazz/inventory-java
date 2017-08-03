/*
 * To iconic this license header, choose License Headers in Project Properties.
 * To iconic this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbutgae.ui;

import com.mbutgae.db.DatabaseConn;
import com.mbutgae.db.Parameter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import com.mbutgae.misc.Clock;
import com.mbutgae.misc.Iconic;
import com.mbutgae.obj.User;
import net.sf.jcarrierpigeon.WindowPosition;
import net.sf.jtelegraph.Telegraph;
import net.sf.jtelegraph.TelegraphQueue;
import net.sf.jtelegraph.TelegraphType;

/**
 *
 * @author ALPABETAPINTAR
 */
public class LoginF extends javax.swing.JFrame {

    ResultSet rs;
    DatabaseConn db;
    String u, p, r;// username, password, rights variable 
    Iconic iconic = new Iconic();

    /**
     * Creates new form LoginF
     */
    public LoginF() {
        db = new DatabaseConn(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB, new Parameter().IPHOST, new Parameter().PORT);
        initComponents();
        Clock c1 = new Clock(timeLabel);
        Thread t1 = new Thread(c1);
        t1.start();
        this.setLocationRelativeTo(null);
        username.requestFocus();

    }

//    public static void changeFont(Component component, Font font) {
//        component.setFont(font);
//        if (component instanceof Container) {
//            for (Component child : ((Container) component).getComponents()) {
//                changeFont(child, font);
//            }
//        }
//    }

    public Icon setIcon(String path, int size) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        return icon;
    }

    public void notifPush(String title, String message, TelegraphType type,int duration) {
        Telegraph tele = new Telegraph(title, message, type, WindowPosition.BOTTOMLEFT, duration);
        TelegraphQueue q = new TelegraphQueue();
        q.add(tele);
    }

    public void loginProcess() {
        User user;
        if (username.getText().equals("") || new String(pass.getPassword()).equals("")) {
            //JOptionPane.showMessageDialog(this, "Isikan Data dengan Benar!");
            notifPush("Gagal", "Masukkan data dengan benar", TelegraphType.NOTIFICATION_WARNING, 3000);
        } else {
            rs = db.querySelectAll("user", "user_id='" + username.getText() + "' and pass='" + new String(pass.getPassword()) + "'");
            try {
                while (rs.next()) {
                    u = rs.getString("user_id");
                    p = rs.getString("pass");
                    r = rs.getString("rights");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (u == null && p == null) {
                //JOptionPane.showMessageDialog(this, "Username dan Password Salah!");
                notifPush("Gagal", "Username dan Password Salah!", TelegraphType.NOTIFICATION_ERROR, 3000);
            } else {
                if (r.equals("1")) {
                    //              Home h = new Home();
                    //              h.setVisible(true);
                    //              this.dispose();
                    System.out.println("GOD_Mode");
                    System.out.println("Log");

                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    System.out.println(dateFormat.format(date));

                    String[] kolom = {"user_id", "date", "operation"};
                    String[] isi = {username.getText(), dateFormat.format(date), "1"};
                    System.out.println(db.queryInsert("user_log", kolom, isi));

                    user = new User(u, p, r);
                    System.out.println("Succsessfully Log...");
                    System.out.println("");
                    
                    notifPush("Login Sukses","Selamat Datang "+user.getUsername(), TelegraphType.NOTIFICATION_DONE, 3000);
                    //JOptionPane.showMessageDialog(this, "Wrong Username and Password");
                    this.dispose();
                    HomeF h = new HomeF(user);
                    h.setVisible(true);
                } else {
                    //                Transaction t = new Transaction();
                    //                t.setVisible(true);
                    //                this.dispose();
                }
            }
        }

        username.setText("");
        pass.setText("");

        username.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JustPanel = new javax.swing.JPanel();
        iconLabel = new javax.swing.JLabel();
        InputPanel = new javax.swing.JPanel();
        close = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        userLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        passLabel = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        minimize = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(51, 255, 255));
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        JustPanel.setBackground(new java.awt.Color(102, 255, 204));
        JustPanel.setForeground(new java.awt.Color(102, 255, 204));

        iconLabel.setFont(username.getFont());
        iconLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lock-icon.png"))); // NOI18N
        iconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JustPanelLayout = new javax.swing.GroupLayout(JustPanel);
        JustPanel.setLayout(JustPanelLayout);
        JustPanelLayout.setHorizontalGroup(
            JustPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JustPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JustPanelLayout.setVerticalGroup(
            JustPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JustPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        InputPanel.setBackground(new java.awt.Color(0, 102, 102));

        close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/windows/close16w.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeMouseExited(evt);
            }
        });

        username.setBackground(new java.awt.Color(0, 102, 102));
        username.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setAlignmentX(0.0F);
        username.setAlignmentY(0.0F);
        username.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 255, 204)));
        username.setMargin(new java.awt.Insets(0, 0, 0, 0));
        username.setPreferredSize(new java.awt.Dimension(0, 25));
        username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameKeyPressed(evt);
            }
        });

        userLabel.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        userLabel.setForeground(new java.awt.Color(102, 255, 204));
        userLabel.setLabelFor(username);
        userLabel.setText("USER");

        timeLabel.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        timeLabel.setForeground(new java.awt.Color(102, 255, 204));
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        timeLabel.setText("TIME");
        timeLabel.setAlignmentY(0.0F);
        timeLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 255, 204)));

        loginButton.setBackground(new java.awt.Color(0, 0, 0));
        loginButton.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        loginButton.setForeground(new java.awt.Color(102, 255, 204));
        loginButton.setText("LOGIN");
        loginButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 255, 204)));
        loginButton.setContentAreaFilled(false);
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                loginButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                loginButtonMouseReleased(evt);
            }
        });
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        passLabel.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        passLabel.setForeground(new java.awt.Color(102, 255, 204));
        passLabel.setLabelFor(pass);
        passLabel.setText("PASSWORD");

        pass.setBackground(username.getBackground());
        pass.setFont(username.getFont());
        pass.setForeground(username.getForeground());
        pass.setBorder(username.getBorder());
        pass.setPreferredSize(new java.awt.Dimension(0, 25));
        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passKeyPressed(evt);
            }
        });

        minimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/windows/minimise16w.png"))); // NOI18N
        minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minimizeMouseExited(evt);
            }
        });

        javax.swing.GroupLayout InputPanelLayout = new javax.swing.GroupLayout(InputPanel);
        InputPanel.setLayout(InputPanelLayout);
        InputPanelLayout.setHorizontalGroup(
            InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InputPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(minimize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(close)
                .addGap(10, 10, 10))
            .addGroup(InputPanelLayout.createSequentialGroup()
                .addGroup(InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(InputPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InputPanelLayout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addGroup(InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(userLabel)
                                .addComponent(passLabel))
                            .addGap(29, 29, 29)
                            .addGroup(InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        InputPanelLayout.setVerticalGroup(
            InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(close)
                    .addComponent(minimize))
                .addGap(100, 100, 100)
                .addGroup(InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userLabel))
                .addGap(20, 20, 20)
                .addGroup(InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passLabel)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(timeLabel)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JustPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(InputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(InputPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JustPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
//        ImageIcon icon = new ImageIcon("./src/icon/close-icon.png");
//        Image img = icon.getImage();
//        Image newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
//        icon = new ImageIcon(newimg);

        //close.setIcon(iconic.getIcon("./src/icon/windows/close16.png", 16));
        //Icon icon = setIcon("./src/icon/close-icon.png", 50);
        Icon icon = iconic.getIcon("./src/icon/close-icon.png", 50);

        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Apakah anda ingin membatalkan login?",
                "Abort Login",
                JOptionPane.YES_NO_OPTION,
                0,
                icon);
        if (selectedOption == JOptionPane.YES_OPTION) {
            System.exit(0);
        }


    }//GEN-LAST:event_closeMouseClicked

    private void loginButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseEntered
        loginButton.setBackground(JustPanel.getBackground());
        loginButton.setForeground(InputPanel.getBackground());
        loginButton.setOpaque(true);
    }//GEN-LAST:event_loginButtonMouseEntered

    private void loginButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseExited
        loginButton.setBackground(InputPanel.getBackground());
        loginButton.setForeground(JustPanel.getBackground());
    }//GEN-LAST:event_loginButtonMouseExited

    private void loginButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMousePressed
        loginButton.setBackground(Color.GRAY);
        loginButton.setOpaque(true);

    }//GEN-LAST:event_loginButtonMousePressed

    private void loginButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseReleased
        loginButton.setBackground(InputPanel.getBackground());
        loginButton.setForeground(JustPanel.getBackground());
    }//GEN-LAST:event_loginButtonMouseReleased

    private void minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseClicked
        this.setState(Frame.ICONIFIED);
        //Another way to minimize
        //this.setExtendedState(Frame.ICONIFIED);
    }//GEN-LAST:event_minimizeMouseClicked

    private void iconLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconLabelMouseClicked
        this.setExtendedState(Frame.ICONIFIED);
    }//GEN-LAST:event_iconLabelMouseClicked

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        loginProcess();
    }//GEN-LAST:event_loginButtonActionPerformed

    private void closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseEntered
        close.setIcon(iconic.getIcon("./src/icon/windows/close16.png", 16));
    }//GEN-LAST:event_closeMouseEntered

    private void closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseExited
        Icon icon = setIcon("./src/icon/windows/close16w.png", 16);
        close.setIcon(icon);
    }//GEN-LAST:event_closeMouseExited

    private void minimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseEntered
        Icon icon = setIcon("./src/icon/windows/minimise16.png", 16);
        minimize.setIcon(icon);
    }//GEN-LAST:event_minimizeMouseEntered

    private void minimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseExited
        Icon icon = setIcon("./src/icon/windows/minimise16w.png", 16);
        minimize.setIcon(icon);
    }//GEN-LAST:event_minimizeMouseExited

    private void passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyPressed
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            loginProcess();
        }//No
    }//GEN-LAST:event_passKeyPressed

    private void usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyPressed
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
//            if (new String(pass.getPassword()).equals("")) {
//                JOptionPane.showMessageDialog(this, "Anda belum memasukkan Password!");                
//                pass.requestFocus();
//            }
            pass.requestFocus();

        }//No
    }//GEN-LAST:event_usernameKeyPressed

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
            java.util.logging.Logger.getLogger(LoginF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel InputPanel;
    private javax.swing.JPanel JustPanel;
    private javax.swing.JLabel close;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel minimize;
    private javax.swing.JPasswordField pass;
    private javax.swing.JLabel passLabel;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel userLabel;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
