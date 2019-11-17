package java_project_1;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import sun.util.logging.PlatformLogger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class Main_Window1 extends javax.swing.JFrame {

    /**
     * Creates new form Main_Window1
     */
    public Main_Window1() {
        initComponents();
        getConnection();
        Show_Products_In_JTable();
    }
    
    public Connection getConnection (){
         Connection con = null;
        try{
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products_db","root","");
                   //OptionPane.showMessageDialog(null,"Connected");
                    return con;
         } catch (SQLException ex) {
            Logger.getLogger(Main_Window1.class.getName()).log(Level.SEVERE, null, ex);
           //OptionPane.showMessageDialog(null,"Not Connected");
            return null;
        }
    
    }
//Check input fileds
  public boolean checkInputs(){
      if     (txt_name.getText()== null
          || txt_price.getText()== null
          || txt_qty.getText() == null)
      {
          return false;
      }
      else{
          try{
              Float.parseFloat(txt_price.getText());
              return true;
          }
          catch(Exception ex){
              return false;
          }
      }
  }
    
//Resize image
    String ImgPath = null;
    int pos=0;
  public ImageIcon ResizeImage(String imagePath,byte[] pic){ 
                 ImageIcon myImage = null;
                 if(imagePath!= null){
                     myImage = new ImageIcon(imagePath);
                 }
                 else{
                     myImage = new ImageIcon(pic);
                 }
                 Image img = myImage.getImage();
                 Image img2 = img.getScaledInstance(lbl_image.getWidth(),lbl_image.getHeight(),Image.SCALE_SMOOTH);
                 ImageIcon image = new ImageIcon(img2);
                 return image;
    }
  
    //Display table in jframe_table
//    1 - fill arraylist with the data
  
      public ArrayList<Product1> getProduct1List(){
          ArrayList<Product1> productList = new ArrayList<Product1>();
          Connection con = getConnection();
          String query = "SELECT * FROM products";
          
          Statement st;
          ResultSet rs;
          try{
                  st = con.createStatement();
                  rs = st.executeQuery(query);
                  Product1 product;
                  
                  while(rs.next()){
                      product = new Product1(rs.getInt("id"),rs.getString("name"),
                              Float.parseFloat(rs.getString("price")),Float.parseFloat(rs.getString("quantity")),
                              rs.getBytes("image"));
                              productList.add(product);
                  }
          } 
          catch (SQLException ex) {
            Logger.getLogger(Main_Window1.class.getName()).log(Level.SEVERE, null, ex);
        }
          return productList;
         }
      
//    2-  populate the table
      
      public void Show_Products_In_JTable(){
          ArrayList<Product1> list = getProduct1List();
          DefaultTableModel model =(DefaultTableModel)JTable_Products.getModel();
          //clear jtable content
          model.setRowCount(0);
          Object[] row = new Object[4];
          for(int i=0;i<list.size();i++){
              row[0] = list.get(i).getId();
              row[1] = list.get(i).getName();
              row[2] = list.get(i).getPrice();
              row[3] = list.get(i).getQuantity();
              
              model.addRow(row);
          }
          
            }
  public void ShowItem(int index){
      txt_id.setText(Integer.toString(getProduct1List().get(index).getId()));
      txt_name.setText(getProduct1List().get(index).getName());
      txt_price.setText(Float.toString(getProduct1List().get(index).getPrice()));
      txt_qty.setText(Float.toString(getProduct1List().get(index).getQuantity()));
      lbl_image.setIcon(ResizeImage(null,getProduct1List().get(index).getPicture()));
  }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lbl_image = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Products = new javax.swing.JTable();
        Btn_choose_image = new javax.swing.JButton();
        Btn_Insert = new javax.swing.JButton();
        Btn_update = new javax.swing.JButton();
        Btn_last = new javax.swing.JButton();
        Btn_delete = new javax.swing.JButton();
        Btn_previous = new javax.swing.JButton();
        Btn_first = new javax.swing.JButton();
        Btn_next = new javax.swing.JButton();
        txt_qty = new javax.swing.JTextField();
        Btn_clear = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 51));
        jLabel2.setText("ID:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(0, 102, 51));
        jPanel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 0));
        jLabel1.setText("ID:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 0));
        jLabel3.setText("Quantity:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 0));
        jLabel4.setText("Price:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 0));
        jLabel5.setText("Image:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 0));
        jLabel6.setText("Name:");

        txt_id.setEnabled(false);

        lbl_image.setBackground(new java.awt.Color(51, 204, 255));
        lbl_image.setOpaque(true);

        JTable_Products.setBackground(new java.awt.Color(0, 0, 0));
        JTable_Products.setForeground(new java.awt.Color(255, 51, 0));
        JTable_Products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "Quantity"
            }
        ));
        JTable_Products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Products);

        Btn_choose_image.setBackground(new java.awt.Color(255, 51, 0));
        Btn_choose_image.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Btn_choose_image.setText("CHOOSE IMAGE");
        Btn_choose_image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_choose_imageActionPerformed(evt);
            }
        });

        Btn_Insert.setBackground(new java.awt.Color(255, 51, 0));
        Btn_Insert.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Btn_Insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_icons/insert.png"))); // NOI18N
        Btn_Insert.setText("Insert");
        Btn_Insert.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Btn_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_InsertActionPerformed(evt);
            }
        });

        Btn_update.setBackground(new java.awt.Color(255, 51, 0));
        Btn_update.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_icons/refresh.png"))); // NOI18N
        Btn_update.setText("Update");
        Btn_update.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_updateActionPerformed(evt);
            }
        });

        Btn_last.setBackground(new java.awt.Color(255, 51, 0));
        Btn_last.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Btn_last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_icons/last.png"))); // NOI18N
        Btn_last.setText("Last");
        Btn_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_lastActionPerformed(evt);
            }
        });

        Btn_delete.setBackground(new java.awt.Color(255, 51, 0));
        Btn_delete.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_icons/deletesign.png"))); // NOI18N
        Btn_delete.setText("Delete");
        Btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_deleteActionPerformed(evt);
            }
        });

        Btn_previous.setBackground(new java.awt.Color(255, 51, 0));
        Btn_previous.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Btn_previous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_icons/previous.png"))); // NOI18N
        Btn_previous.setText("Previous");
        Btn_previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_previousActionPerformed(evt);
            }
        });

        Btn_first.setBackground(new java.awt.Color(255, 51, 0));
        Btn_first.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Btn_first.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_icons/first.png"))); // NOI18N
        Btn_first.setText("First");
        Btn_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_firstActionPerformed(evt);
            }
        });

        Btn_next.setBackground(new java.awt.Color(255, 51, 0));
        Btn_next.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Btn_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/project_icons/next.png"))); // NOI18N
        Btn_next.setText("Next");
        Btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_nextActionPerformed(evt);
            }
        });

        Btn_clear.setBackground(new java.awt.Color(255, 102, 0));
        Btn_clear.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Btn_clear.setText("Clear");
        Btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Btn_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_first, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_previous, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(3, 3, 3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_name, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                    .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_id)
                                    .addComponent(Btn_choose_image, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txt_qty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(673, 673, 673)))
                .addComponent(jLabel7))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(Btn_clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(75, 75, 75)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Btn_choose_image)
                                .addGap(6, 6, 6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(jLabel5)
                                .addGap(19, 19, 19)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Btn_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Btn_last, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Btn_first)
                            .addComponent(Btn_previous)))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(664, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_lastActionPerformed
        pos = getProduct1List().size()-1;
        ShowItem(pos);
    }//GEN-LAST:event_Btn_lastActionPerformed

    private void Btn_choose_imageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_choose_imageActionPerformed
            JFileChooser file = new  JFileChooser();
            file.setCurrentDirectory(new File(System.getProperty("user.home")));
            
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image","jpg","png");
            file.addChoosableFileFilter(filter);
            int result = file.showSaveDialog(null);
            if(result == JFileChooser.APPROVE_OPTION){
                File selectedFile = file.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                lbl_image.setIcon(ResizeImage(path,null));
                ImgPath = path;
            }
            else{
                System.out.println("No File Selected");
            }
            
    }//GEN-LAST:event_Btn_choose_imageActionPerformed

    private void Btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_deleteActionPerformed
        if(!txt_id.getText().equals("")){
            
            try{
            Connection con  = getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM products WHERE id = ?");
            int id = Integer.parseInt(txt_id.getText());
            ps.setInt(1,id);
            ps.executeUpdate();
            Show_Products_In_JTable();
            JOptionPane.showMessageDialog(null,"Product Deleted");
        }   catch (SQLException ex) {
                Logger.getLogger(Main_Window1.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"product deleted");
            }
    }//GEN-LAST:event_Btn_deleteActionPerformed
        else{
            JOptionPane.showMessageDialog(null,"product not deleted : No id to delete");
        }
    }
    
    private void Btn_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_InsertActionPerformed
         if(checkInputs() && ImgPath != null){
             
             try{
             Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO products(name,price,quantity,image)"+"values(?,?,?,?)");
             ps.setString(1,txt_name.getText());
             ps.setString(2,txt_price.getText());
             ps.setString(3,txt_qty.getText());
             
//             SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
//             String addDate = dateFormat.format(txt_AddDate.getText());
//             ps.setString(3, addDate);
             
             InputStream img = new FileInputStream(new File(ImgPath));
             ps.setBlob(4,img);
             ps.executeUpdate();
             
             Show_Products_In_JTable();
             
             JOptionPane.showMessageDialog(null,"Data Entry");
               txt_id.setText(null);
               txt_name.setText(null);
               txt_price.setText(null);
               txt_qty.setText(null);
               lbl_image.setIcon(null);
             
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null,ex.getMessage());
             }
         }
         else{
             JOptionPane.showMessageDialog(null,"One or More Fields Empty");
         }
    }//GEN-LAST:event_Btn_InsertActionPerformed

    private void Btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_updateActionPerformed
        if(checkInputs() && txt_id.getText() != null){
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
 //update without image
            if(ImgPath == null){
                try {
                    UpdateQuery = "UPDATE products SET name = ?, price = ?"+
                                   ", quantity = ? WHERE id = ?";
                    ps = con.prepareStatement(UpdateQuery);
                    
                    ps.setString(1,txt_name.getText());
                    ps.setString(2,txt_price.getText());
                    ps.setString(3,txt_qty.getText());
                    ps.setInt(4,Integer.parseInt(txt_id.getText()));
                    ps.executeUpdate();
                    Show_Products_In_JTable();
                    
                     JOptionPane.showMessageDialog(null,"product updated");
                       txt_id.setText(null);
                       txt_name.setText(null);
                       txt_price.setText(null);
                       txt_qty.setText(null);
                       lbl_image.setIcon(null);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Main_Window1.class.getName()).log(Level.SEVERE, null, ex);
                     JOptionPane.showMessageDialog(null,"Product is not updated");
                }
            
      
            }
            //update with image
            else{
                    
               try {
                    InputStream img = new FileInputStream(new File (ImgPath));
                    UpdateQuery = "UPDATE products SET name = ?, price = ?"+
                                  ", quantity = ?, image = ? WHERE id = ?";
                    ps = con.prepareStatement(UpdateQuery);
                    ps.setString(1,txt_name.getText());
                    ps.setString(2,txt_price.getText());
                    ps.setString(3,txt_qty.getText());
                    ps.setBlob(4, img);
                    ps.setInt(5,Integer.parseInt(txt_id.getText()));
                    ps.executeUpdate();
                     Show_Products_In_JTable();
                      JOptionPane.showMessageDialog(null,"product updated");
                        txt_id.setText(null);
                        txt_name.setText(null);
                        txt_price.setText(null);
                        txt_qty.setText(null);
                        lbl_image.setIcon(null);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                    
                    
                } 
                          
            }
         }
        else{
            JOptionPane.showMessageDialog(null,"One or More Fields Empty or Wrong");
        }
    }//GEN-LAST:event_Btn_updateActionPerformed

    private void Btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_firstActionPerformed
        pos=0;
        ShowItem(pos);
    }//GEN-LAST:event_Btn_firstActionPerformed

    private void Btn_previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_previousActionPerformed
        pos--;
        if(pos<0){
            pos = 0;
        }
        ShowItem(pos);
    }//GEN-LAST:event_Btn_previousActionPerformed

    private void Btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_nextActionPerformed
        pos++;
        if(pos >= getProduct1List().size()){
            pos = getProduct1List().size()-1;
        }
        ShowItem(pos);
    }//GEN-LAST:event_Btn_nextActionPerformed

    private void JTable_ProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ProductsMouseClicked
        // TODO add your handling code here:
           int index = JTable_Products.getSelectedRow();
           ShowItem(index);
    }//GEN-LAST:event_JTable_ProductsMouseClicked

    private void Btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_clearActionPerformed
     txt_id.setText(null);
      txt_name.setText(null);
      txt_price.setText(null);
      txt_qty.setText(null);
      lbl_image.setIcon(null);
    }//GEN-LAST:event_Btn_clearActionPerformed

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
            java.util.logging.Logger.getLogger(Main_Window1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Window1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Window1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Window1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Window1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Insert;
    private javax.swing.JButton Btn_choose_image;
    private javax.swing.JButton Btn_clear;
    private javax.swing.JButton Btn_delete;
    private javax.swing.JButton Btn_first;
    private javax.swing.JButton Btn_last;
    private javax.swing.JButton Btn_next;
    private javax.swing.JButton Btn_previous;
    private javax.swing.JButton Btn_update;
    private javax.swing.JTable JTable_Products;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_price;
    private javax.swing.JTextField txt_qty;
    // End of variables declaration//GEN-END:variables
}
