/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_project_1;

/**
 *
 * @author admin
 */
public class Product1 {
 
    private int id;
    private String name;
    private float price;
    private String addDate;
    private byte[] picture;
    public Product1 (int pid,String pname,float pprice,String pAddDate,byte[] pimg){
        this.id = pid;
        this.name = pname;
        this.price = pprice;
        this.addDate = pAddDate;
        this.picture = pimg;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getAddDate() {
        return addDate;
    }

    public byte[] getPicture() {
        return picture;
    }
    
}
