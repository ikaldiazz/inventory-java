/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbutgae.obj;

/**
 *
 * @author ALPABETAPINTAR
 */
public class Kategori {
    private String catid, catname, descr;

    public Kategori(String id, String nama, String description) {
        this.catid = id;
        this.catname = nama;
        this.descr = description;
    }

    public String getCatId() {
        return catid;
    }

    public void setCatId(String catid) {
        this.catid = catid;
    }

    public String getCatName() {
        return catname;
    }

    public void setCatName(String catname) {
        this.catname = catname;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
    
    public void showStatus() {
        //return super.toString(); //To change body of generated methods, choose Tools | Templates.
        System.out.println("====STATUS PRODUCT====");
        String[] cate={this.catid, this.catname, this.descr};
        String[] title = {"Category ID","Category Code","Description"};
        for (int i = 0; i < title.length; i++) {
            System.out.println(title[i]+" : "+cate[i]);            
        }
        
        //System.out.println("Product ID : "+this.prod_id);
        
        
    }
    
}
