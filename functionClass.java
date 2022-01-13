
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yalap
 */
public class functionClass {
    
    //create a function to display the image in jLabel
    public void displayImage(int width, int height, String imagePath, JLabel label){
        //Get the image
        ImageIcon ImgIco = new ImageIcon(getClass().getResource(imagePath));
        
        //Make the image fit the jLabel
        Image image = ImgIco.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        
        //Set the image into the jLabel
        label.setIcon(new ImageIcon(image));
    }
    
    public void customTable(JTable table){
        table.setSelectionBackground(new Color(249, 105, 14));
        table.setSelectionForeground(Color.white);
        table.setRowHeight(35);
        table.setShowGrid(false);
        table.setBackground(new Color(248, 248, 248));
        table.setGridColor(Color.ORANGE);
    }
    
    //Create a function to customize the jTable Header
    public void customTableHeader(JTable table, Color back_Color, Integer fontSize){
        table.getTableHeader().setBackground(back_Color);
        table.getTableHeader().setForeground(Color.black);
        table.getTableHeader().setFont(new Font("Verdana", Font.BOLD, fontSize));
        table.getTableHeader().setOpaque(false);
    }
    
    public ResultSet getData(String Query){
        PreparedStatement ps;
        ResultSet rs = null;
        
        try{
            ps = DataBase.getCon().prepareStatement(Query);
            rs = ps.executeQuery();
        } catch (SQLException ex){
            Logger.getLogger(functionClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
   
    public int countData(String tableName){
        int total = 0;
        ResultSet rs;
        Statement st;
        
        try{
            st = DataBase.getCon().createStatement();
            rs = st.executeQuery("select count(*) as total from `" + tableName + "`");
            
            if(rs.next()){
                total = rs.getInt("Total");
            }
        }   catch(SQLException ex){
            Logger.getLogger(functionClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
}
