/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbutgae.obj;

import com.mbutgae.misc.Encryptor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ALPABETAPINTAR
 */
public class Barang {
    private String prod_id, prod_code, product, category, image, descr;

    public Barang(String prod_id, String prod_code, String product, String category, String image, String descr) {
        this.prod_id = prod_id;
        this.prod_code = prod_code;
        this.product = product;
        this.category = category;
        this.image = image;
        this.descr = descr;
    }

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_code() {
        return prod_code;
    }

    public void setProd_code(String prod_code) {
        this.prod_code = prod_code;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        String[] pro={this.prod_id, this.prod_code, this.product, this.category, this.image, this.descr};
        String[] title = {"Product ID","Product Code","Product Name","Category","Image","Description"};
        for (int i = 0; i < title.length; i++) {
            System.out.println(title[i]+" : "+pro[i]);            
        }
        
        //System.out.println("Product ID : "+this.prod_id);
        
        
    }
    
    
}
