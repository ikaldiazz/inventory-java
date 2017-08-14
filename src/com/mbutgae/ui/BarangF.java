/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbutgae.ui;

import com.mbut.img.ImagePanel;
import com.mbutgae.db.DatabaseConn;
import com.mbutgae.db.Parameter;
import com.mbutgae.db.ResultSetTableModel;
import com.mbutgae.misc.Iconic;
import com.mbutgae.obj.Barang;
import com.mbutgae.obj.Kategori;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.sf.jcarrierpigeon.WindowPosition;
import net.sf.jtelegraph.Telegraph;
import net.sf.jtelegraph.TelegraphQueue;
import net.sf.jtelegraph.TelegraphType;

/**
 *
 * @author ALPABETAPINTAR
 */
public class BarangF extends javax.swing.JFrame {

    ResultSet rs;
    DatabaseConn db;
    //String u, p, r;// username, password, rights variable 
    Iconic iconic = new Iconic();
    HomeF h;
    //String category_id;

    Kategori kat = new Kategori("", "", "");
    Barang bar = new Barang("", "", "", "", "", "");

    String lastfolder;
    static String userhome = System.getProperty("user.home") + File.separator + "Pictures";

    ImagePanel ip = new ImagePanel();
    Icon cari = setIcon("./src/icon/windows/metro/black/search.png", 26);

    String[] sIdBarang;

    /**
     * Creates new form BarangF
     */
    public BarangF() {
        db = new DatabaseConn(new Parameter().HOST_DB, new Parameter().USERNAME_DB, new Parameter().PASSWORD_DB, new Parameter().IPHOST, new Parameter().PORT);
        initComponents();
        db.rsToComboBox("category", "cat_name", catCb);
        ip.setPreferredSize(new Dimension(305, 258));

        cariBtn.setIcon(cari);
        imgPanel.add(ip);

        table();
    }

    public Icon setIcon(String path, int size) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        return icon;
    }

    public boolean cekInputForm() {
        boolean simpan = false;
        boolean cekid;
        cekid = ((!idTextField.getText().isEmpty()) && (!nameTf.getText().isEmpty()));
        if (cekid) {
            bar.setProd_id(idTextField.getText());
            if (codeTf.getText().isEmpty()) {
                bar.setProd_code("NO CODE");
            } else {
                bar.setProd_code(codeTf.getText());
            }

            bar.setProduct(nameTf.getText());
            bar.setCategory(catCb.getSelectedItem().toString());
            bar.setDescr(descrTa.getText());
            simpan = true;
        } else {
            simpan = false;
        }
        return simpan;
    }

    public void getImageFromDatabase() {
        String idTemp = String.valueOf(tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 0));
        System.out.println("ID Produst Temp:" + idTemp);
        String[] kolomGambar = {"image"};
        byte[] imageBytes = null;
        rs = db.fcSelectCommand(kolomGambar, "product", "prod_id = " + idTemp);
        try {
            if (rs.next()) {
                Blob imageBlob = rs.getBlob(1);
                imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
            }
            Image preview = byteArrayToImage(imageBytes);
            ip.setImage(preview);
            db.closeKoneksi();

        } catch (SQLException ex) {
            Logger.getLogger(BarangF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            //Logger.getLogger(BarangF.class.getName()).log(Level.SEVERE, null, ex);            
            ImageIcon a = new ImageIcon("./src/img/Image/noimage.png");
            Image preview = a.getImage();
            ip.setImage(preview);
        }
    }

    public static BufferedImage byteArrayToImage(byte[] bytes) {
        BufferedImage bufferedImage = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(bytes);
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return bufferedImage;
    }

    public void table() {
        String namaHeader[] = {"ID", "Code", "Product", "Kategori"};
        rs = db.eksekusiQuery("SELECT p.prod_id, p.prod_code, p.product , c.cat_name FROM product AS p INNER JOIN category AS c ON p.cat_id = c.cat_id");
        tabelBarang.setModel(new ResultSetTableModel(rs));
        changeHeader(namaHeader, tabelBarang);

    }

    public void notifPush(String title, String message, TelegraphType type, int duration) {
        Telegraph tele = new Telegraph(title, message, type, WindowPosition.BOTTOMLEFT, duration);
        TelegraphQueue q = new TelegraphQueue();
        q.add(tele);
    }

    public void changeHeader(String[] header, JTable jt) {

        for (int i = 0; i <= header.length - 1; i++) {
            jt.getColumnModel().getColumn(i).setHeaderValue(header[i]);
        }
        //jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

//        for (int column = 0; column < jt.getColumnCount(); column++) {
//            TableColumn tableColumn = jt.getColumnModel().getColumn(column);
//            int preferredWidth = tableColumn.getMinWidth();
//            int maxWidth = tableColumn.getMaxWidth();
//
//            for (int row = 0; row < jt.getRowCount(); row++) {
//                TableCellRenderer cellRenderer = jt.getCellRenderer(row, column);
//                Component c = jt.prepareRenderer(cellRenderer, row, column);
//                int width = c.getPreferredSize().width + jt.getIntercellSpacing().width;
//                preferredWidth = Math.max(preferredWidth, width);
//
//                //  We've exceeded the maximum width, no need to check other rows
//                if (preferredWidth >= maxWidth) {
//                    preferredWidth = maxWidth;
//                    break;
//                }
//            }
//
//            tableColumn.setPreferredWidth(preferredWidth);
//        }
//        //jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }
// ...

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        tablePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();
        carTf = new javax.swing.JTextField();
        cariCb = new javax.swing.JComboBox<>();
        cariBtn = new javax.swing.JButton();
        imgPanel = new ImagePanel();
        jPanel4 = new javax.swing.JPanel();
        crudPanel = new javax.swing.JPanel();
        idTextField = new javax.swing.JTextField() {
            public void processKeyEvent(KeyEvent ev) {
                char c = ev.getKeyChar();
                try {
                    // Ignore all non-printable characters. Just check the printable ones.
                    if (c > 31 && c < 127) {
                        Integer.parseInt(c + "");
                    }
                    super.processKeyEvent(ev);
                }
                catch (NumberFormatException nfe) {
                    // Do nothing. Character inputted is not a number, so ignore it.
                }
            }
        };
        browseBtn = new javax.swing.JButton();
        idLabel = new javax.swing.JLabel();
        codeLabel = new javax.swing.JLabel();
        codeTf = new javax.swing.JTextField();
        catCb = new javax.swing.JComboBox<>();
        nameLabel = new javax.swing.JLabel();
        nameTf = new javax.swing.JTextField();
        catLabel = new javax.swing.JLabel();
        imgTf = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        descrTa = new javax.swing.JTextArea();
        imgLabel = new javax.swing.JLabel();
        descrlabel = new javax.swing.JLabel();
        addCatBtn = new javax.swing.JButton();
        simpanBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        tablePanel.setBackground(new java.awt.Color(0, 102, 102));

        tabelBarang.setFont(new java.awt.Font("Tw Cen MT", 0, 16)); // NOI18N
        tabelBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBarangMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelBarangMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelBarang);

        carTf.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        carTf.setMinimumSize(new java.awt.Dimension(26, 26));
        carTf.setPreferredSize(new java.awt.Dimension(28, 26));

        cariCb.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        cariCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "PRODUCT", "NAME", "CATEGORY" }));

        cariBtn.setIcon(cari);
        cariBtn.setPreferredSize(new java.awt.Dimension(26, 26));
        cariBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(tablePanelLayout.createSequentialGroup()
                        .addComponent(cariCb, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(carTf, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(carTf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cariCb)
                    .addComponent(cariBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addContainerGap())
        );

        imgPanel.setBackground(new java.awt.Color(0, 102, 102));
        imgPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 10));

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));

        crudPanel.setBackground(new java.awt.Color(102, 255, 204));
        crudPanel.setForeground(new java.awt.Color(0, 102, 102));

        idTextField.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        idTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 102, 102)));
        idTextField.setOpaque(false);

        browseBtn.setBackground(crudPanel.getForeground());
        browseBtn.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        browseBtn.setForeground(crudPanel.getBackground());
        browseBtn.setText("Browse");
        browseBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        browseBtn.setContentAreaFilled(false);
        browseBtn.setOpaque(true);
        browseBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                browseBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                browseBtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                browseBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                browseBtnMouseReleased(evt);
            }
        });
        browseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseBtnActionPerformed(evt);
            }
        });

        idLabel.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        idLabel.setText("ID");

        codeLabel.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        codeLabel.setText("PRODUCT CODE");

        codeTf.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        codeTf.setBorder(idTextField.getBorder());
        codeTf.setOpaque(false);

        catCb.setBackground(crudPanel.getBackground());
        catCb.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        catCb.setMaximumRowCount(7);
        catCb.setBorder(new javax.swing.border.MatteBorder(null));
        catCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catCbActionPerformed(evt);
            }
        });

        nameLabel.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        nameLabel.setText("NAME");

        nameTf.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        nameTf.setBorder(idTextField.getBorder());
        nameTf.setOpaque(false);

        catLabel.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        catLabel.setText("CATEGORY");

        imgTf.setEditable(false);
        imgTf.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        imgTf.setBorder(idTextField.getBorder());
        imgTf.setOpaque(false);

        descrTa.setColumns(20);
        descrTa.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        descrTa.setRows(5);
        descrTa.setText("No Description");
        descrTa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                descrTaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                descrTaFocusLost(evt);
            }
        });
        descrTa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descrTaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(descrTa);

        imgLabel.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        imgLabel.setText("IMAGE");

        descrlabel.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        descrlabel.setText("DESCRIPTION");

        addCatBtn.setBackground(crudPanel.getForeground());
        addCatBtn.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        addCatBtn.setForeground(crudPanel.getBackground());
        addCatBtn.setText("+");
        addCatBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        addCatBtn.setContentAreaFilled(false);
        addCatBtn.setOpaque(browseBtn.isOpaque());
        addCatBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addCatBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addCatBtnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addCatBtnMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addCatBtnMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout crudPanelLayout = new javax.swing.GroupLayout(crudPanel);
        crudPanel.setLayout(crudPanelLayout);
        crudPanelLayout.setHorizontalGroup(
            crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crudPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(catLabel)
                    .addGroup(crudPanelLayout.createSequentialGroup()
                        .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imgLabel)
                            .addComponent(descrlabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crudPanelLayout.createSequentialGroup()
                                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(catCb, 0, 286, Short.MAX_VALUE)
                                    .addComponent(imgTf))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(browseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addCatBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, crudPanelLayout.createSequentialGroup()
                        .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(crudPanelLayout.createSequentialGroup()
                                .addComponent(nameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nameTf, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(crudPanelLayout.createSequentialGroup()
                                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(codeLabel)
                                    .addComponent(idLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(idTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                                    .addComponent(codeTf, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addContainerGap())))
        );
        crudPanelLayout.setVerticalGroup(
            crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crudPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel)
                    .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codeLabel))
                .addGap(7, 7, 7)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imgTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgLabel)
                    .addComponent(browseBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addCatBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(catCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(catLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descrlabel))
                .addGap(14, 14, 14))
        );

        simpanBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/windows/metro/black/add.png"))); // NOI18N
        simpanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanBtnActionPerformed(evt);
            }
        });

        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/windows/metro/black/delete.png"))); // NOI18N
        deleteBtn.setEnabled(false);
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        editBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/windows/metro/black/edit.png"))); // NOI18N
        editBtn.setEnabled(false);
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        refreshBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/windows/metro/black/refresh.png"))); // NOI18N
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(crudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(simpanBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                        .addComponent(deleteBtn))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(editBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshBtn)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(crudPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpanBtn)
                    .addComponent(deleteBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editBtn)
                    .addComponent(refreshBtn))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void catCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catCbActionPerformed
        //category_id = "";
        //db.rsToComboBox("category", "cat_name", catComboBox);
        String category_name = catCb.getSelectedItem().toString();
        rs = db.querySelectAll("category", " cat_name = '" + category_name + "' ORDER BY cat_id ASC");
        try {
            while (rs.next()) {
                //category_id += rs.getString("cat_id");
                kat.setCatId(rs.getString("cat_id"));
                kat.setCatName(rs.getString("cat_name"));
                kat.setDescr(rs.getString("descr"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BarangF.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(category_id);
        //kat.showStatus();
        db.closeKoneksi();
    }//GEN-LAST:event_catCbActionPerformed

    private void browseBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseBtnMouseEntered
        if (browseBtn.isEnabled()) {
            browseBtn.setForeground(crudPanel.getForeground());
            browseBtn.setBackground(crudPanel.getBackground());
        }
    }//GEN-LAST:event_browseBtnMouseEntered

    private void browseBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseBtnMouseExited
        if (browseBtn.isEnabled()) {
            browseBtn.setForeground(crudPanel.getBackground());
            browseBtn.setBackground(crudPanel.getForeground());
        }
    }//GEN-LAST:event_browseBtnMouseExited

    private void browseBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseBtnMousePressed
        if (browseBtn.isEnabled()) {
            browseBtn.setForeground(crudPanel.getBackground());
            browseBtn.setBackground(crudPanel.getForeground());
        }

    }//GEN-LAST:event_browseBtnMousePressed

    private void browseBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseBtnMouseReleased
        if (browseBtn.isEnabled()) {
            browseBtn.setForeground(crudPanel.getForeground());
            browseBtn.setBackground(crudPanel.getBackground());
        }
    }//GEN-LAST:event_browseBtnMouseReleased

    private void addCatBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCatBtnMouseEntered
        addCatBtn.setForeground(crudPanel.getForeground());
        addCatBtn.setBackground(crudPanel.getBackground());
    }//GEN-LAST:event_addCatBtnMouseEntered

    private void addCatBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCatBtnMouseExited
        addCatBtn.setForeground(crudPanel.getBackground());
        addCatBtn.setBackground(crudPanel.getForeground());
    }//GEN-LAST:event_addCatBtnMouseExited

    private void addCatBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCatBtnMousePressed
        addCatBtn.setForeground(crudPanel.getBackground());
        addCatBtn.setBackground(crudPanel.getForeground());
    }//GEN-LAST:event_addCatBtnMousePressed

    private void addCatBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCatBtnMouseReleased
        addCatBtn.setForeground(crudPanel.getForeground());
        addCatBtn.setBackground(crudPanel.getBackground());
    }//GEN-LAST:event_addCatBtnMouseReleased

    private void browseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseBtnActionPerformed
        //System.out.println(userhome);
        //System.out.println("Last Folder " + lastfolder);
        FileDialog fd = new FileDialog(this, "Choose a file", FileDialog.LOAD);
        boolean filter = false;

        if (lastfolder == null) {
            fd.setDirectory(userhome);
        } else if (lastfolder != null) {
            fd.setDirectory(lastfolder);
        }

        //fd.setDirectory("C:\\");
        //fd.setFile("*.jpg;*.jpeg;*.png;*.gif;*.JPG;*.PNG;*.GIF;*.JPEG");
        fd.setVisible(true);
        String filename = fd.getFile();

        //imgTf.setText(fd.getDirectory() + fd.getFile());
        if (filename == null) {
            System.out.println("You cancelled the choice");
        } else {
            System.out.println("You choose " + filename);

            String ext = filename.substring(filename.length() - 3);
            System.out.println("File Extension : " + ext);

            if (ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("peg") || ext.equalsIgnoreCase("bmp")) {
                filter = true;
            }

            lastfolder = fd.getDirectory();
            System.out.println(lastfolder);
        }

        //imgTf.setText(fd.getDirectory() + fd.getFile());
        if (filter == true) {
            imgTf.setText(fd.getDirectory() + fd.getFile());
            try {
                Image image = ImageIO.read(new File(fd.getDirectory() + fd.getFile()));
                //imgPreviewLabel.setIcon(new ImageIcon(image));
                //imgPanel.setImage(image);

                ip.setImage(image);

            } catch (IOException ex) {
                System.err.println(ex);
            }
        } else {
            notifPush("Gagal", "Bukan File Gambar", TelegraphType.NOTIFICATION_WARNING, 3000);
            //System.out.println("Salah pilih File");
        }


    }//GEN-LAST:event_browseBtnActionPerformed

    private void tabelBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarangMouseClicked
        simpanBtn.setEnabled(false);
        editBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
        getImageFromDatabase();
        String idTemp = String.valueOf(tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 0));
        idTextField.setText(idTemp);

        codeTf.setText(String.valueOf(tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 1)));
        nameTf.setText(String.valueOf(tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 2)));

        kat.setCatName(String.valueOf(tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 3)));
        catCb.setSelectedItem(kat.getCatName());
        kat.showStatus();

        String[] kolom = {"descr"};
        String deskripsi = "";
        rs = db.querySelectAll("product", "prod_id = " + idTemp);

        try {
            while (rs.next()) {
                deskripsi += rs.getString("descr");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BarangF.class.getName()).log(Level.SEVERE, null, ex);
        }
        descrTa.setText(deskripsi);

        bar.setProd_id(idTemp);
        bar.setProd_code(String.valueOf(tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 1)));
        bar.setProduct(String.valueOf(tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 2)));
        bar.setCategory(kat.getCatId());
        bar.setDescr(deskripsi);
        bar.setImage("null");
        bar.showStatus();


    }//GEN-LAST:event_tabelBarangMouseClicked

    private void simpanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanBtnActionPerformed
        String categoryx = kat.getCatId();
        System.out.println(kat.getCatId());
        if (nameTf.getText().isEmpty()) {
            notifPush("Data Kosong", "Isikan data dengan benar", TelegraphType.NOTIFICATION_WARNING, 3000);
        } else {
            if (imgTf.getText().isEmpty()) {

                //INSERT INTO `product` (`prod_id`, `prod_code`, `product`, `cat_id`, `image`, `descr`) VALUES (NULL, NULL, 'Quaker oat', '1', NULL, NULL);
                String[] kolom = {"prod_code", "product", "cat_id", "descr"};
                String description = "";
                if (!description.isEmpty()) {
                    description = descrTa.getText();
                } else {
                    description = "No Description";
                }
                String[] isi = {codeTf.getText(), nameTf.getText(), kat.getCatId(), description};

                System.out.println(db.queryInsert("product", kolom, isi));
            }
        }

        table();
    }//GEN-LAST:event_simpanBtnActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        ImageIcon a = new ImageIcon("./src/img/Image/noimage.png");
        Image preview = a.getImage();
        ip.setImage(preview);
        table();
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void descrTaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descrTaFocusGained
        if (!descrTa.getText().isEmpty()) {
            descrTa.setText(bar.getDescr());
        } else {
            descrTa.setText("");
        }

    }//GEN-LAST:event_descrTaFocusGained

    private void descrTaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descrTaFocusLost
        if (descrTa.getText().isEmpty()) {
            descrTa.setText("No Description");
        }
    }//GEN-LAST:event_descrTaFocusLost

    private void cariBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        simpanBtn.setEnabled(true);
    }//GEN-LAST:event_editBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        ImageIcon a = new ImageIcon("./src/img/Image/noimage.png");
        Image preview = a.getImage();
        ip.setImage(preview);
        
        editBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        simpanBtn.setEnabled(true);        
        

        int[] selectedrows = tabelBarang.getSelectedRows();
        sIdBarang = new String[tabelBarang.getSelectedRowCount()];
        System.out.println("===============");
        for (int i = 0; i < selectedrows.length; i++) {
            sIdBarang[i] = String.valueOf(tabelBarang.getValueAt(selectedrows[i], 0).toString());
            System.out.println(sIdBarang[i]);
        }
        System.out.println("===============");

        if (tabelBarang.getSelectedRowCount() == 1) {
            String id = String.valueOf(tabelBarang.getValueAt(tabelBarang.getSelectedRow(), 0));
            if (JOptionPane.showConfirmDialog(this, "Are you sure want to delete this data?", "Warning!!!", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                db.queryDelete("product", "prod_id=" + id);
                notifPush("DELETE", "Data Terhapus dengan ID " + id, TelegraphType.FILE_DELETE, 3000);
            } else {
                //return;
            }
        } else {
            if (JOptionPane.showConfirmDialog(this, "Are you sure want to delete selected data?", "Warning!!!", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                String deletedId = "";
                for (int j = 0; j < sIdBarang.length; j++) {
                    //db.queryDelete("product", "prod_id=" + id);
                    db.queryDelete("product", "prod_id=" + sIdBarang[j]);
                    deletedId += " , " + sIdBarang[j];
                }
                notifPush("DELETE", "Data Terhapus dengan ID " + deletedId, TelegraphType.FILE_DELETE, 8000);
            } else {
                //return;
            }
        }

        
        table();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void descrTaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descrTaMouseClicked
        if (!descrTa.getText().isEmpty()) {
            descrTa.setText(bar.getDescr());
        }
    }//GEN-LAST:event_descrTaMouseClicked

    private void tabelBarangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarangMouseReleased
        int[] selectedrows = tabelBarang.getSelectedRows();
        sIdBarang = new String[tabelBarang.getSelectedRowCount()];

        for (int i = 0; i < selectedrows.length; i++) {
            sIdBarang[i] = String.valueOf(tabelBarang.getValueAt(selectedrows[i], 0).toString());
            System.out.println(sIdBarang[i]);
        }
        System.out.println("===============");
    }//GEN-LAST:event_tabelBarangMouseReleased

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BarangF.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BarangF.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BarangF.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BarangF.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BarangF().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCatBtn;
    private javax.swing.JButton browseBtn;
    private javax.swing.JTextField carTf;
    private javax.swing.JButton cariBtn;
    private javax.swing.JComboBox<String> cariCb;
    private javax.swing.JComboBox<String> catCb;
    private javax.swing.JLabel catLabel;
    private javax.swing.JLabel codeLabel;
    private javax.swing.JTextField codeTf;
    private javax.swing.JPanel crudPanel;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextArea descrTa;
    private javax.swing.JLabel descrlabel;
    private javax.swing.JButton editBtn;
    private javax.swing.JLabel idLabel;
    private javax.swing.JTextField idTextField;
    private javax.swing.JLabel imgLabel;
    public javax.swing.JPanel imgPanel;
    private javax.swing.JTextField imgTf;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTf;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JButton simpanBtn;
    private javax.swing.JTable tabelBarang;
    private javax.swing.JPanel tablePanel;
    // End of variables declaration//GEN-END:variables
}
