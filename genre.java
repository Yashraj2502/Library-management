
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yalap
 */
public class genre {
    private int id;
    private String name, AuthorName, Publisher, Price;
    private float Quantity;
    
    functionClass func = new functionClass();
    
    //Constuctor
    public genre(){
        ;
    }
    
    public genre(int ID, String NAME, String AUTHORNAME, float Quantity, String PUBLISHER, String Price){
        this.id = ID;
        this.name = NAME;
        this.AuthorName = AUTHORNAME;
        this.Publisher = PUBLISHER;
        this.Quantity = Quantity;
        this.Price = Price;
    }

    //Getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public String getPublisher() {
        return Publisher;
    }

    public float getQuantity() {
        return Quantity;
    }

    public String getPrice() {
        return Price;
    }
   
    
    
    //Setter
    public void setId(int ID) {
        this.id = ID;
    }

    public void setName(String NAME) {
        this.name = NAME;
    }

    public void setAuthorName(String AuthorName) {
        this.AuthorName = AuthorName;
    }

    public void setPublisher(String Publisher) {
        this.Publisher = Publisher;
    }

    public void setQuantity(float Quantity) {
        this.Quantity = Quantity;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }
   
    
    
    //Add new genre Functions:-
    public void addGenre(String ID, String NAME, String AName, String quantity, String publisher, String price){
        
        //String insertQuery = "insert into `genreTest` (`name`) values(?)";
        String insertQuery = "insert into `bookGenre` (`ID`, `name`, `authorName`, `Quantity`, `Publisher`, `Price`) values(?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = DataBase.getCon().prepareStatement(insertQuery);
            ps.setString(1, ID);
            ps.setString(2, NAME);
            ps.setString(3, AName);
            ps.setString(4, quantity);
            ps.setString(5, publisher);
            ps.setString(6, price);
            
            if(ps.executeUpdate() == 1)
            {
                JOptionPane.showMessageDialog(null, "Genre Added", "Add Genre", 1);
            }
            else{
                JOptionPane.showMessageDialog(null, "Genre NOT Added", "add genre", 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(genre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //edit genre by ID Functions:-
    public void editGenre(String ID, String NAME, String AName, String quantity, String publisher, String price){
        
        String editQuery = "Update `bookGenre` SET `name` = ?, `authorName` = ?, `Quantity` = ?, `Publisher` = ?, `Price` = ? WHERE `id` = ?";
        
        try {
            PreparedStatement ps = DataBase.getCon().prepareStatement(editQuery);
            
            ps.setString(1, NAME);
            ps.setString(2, AName);
            ps.setString(3, quantity);
            ps.setString(4, publisher);
            ps.setString(5, price);
            ps.setString(6, ID);
            
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Genre Edited", "edit Genre", 1);
            }
            else{
                JOptionPane.showMessageDialog(null, "Genre NOT Edited", "edit genre", 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(genre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //remove genre by ID Functions:-
    public void removeGenre(int ID){
        
        String editQuery = "DELETE FROM `bookGenre` WHERE `id` = ?";
        
        try {
            PreparedStatement ps = DataBase.getCon().prepareStatement(editQuery);
            
            ps.setInt(1, ID);
            
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Genre Deleted", "remove", 1);
            }
            else{
                JOptionPane.showMessageDialog(null, "Genre NOT Deleted", "remove", 2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(genre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Function to populate an arrayList with genres
    public ArrayList<genre> genreList(){
        ArrayList<genre> gList = new ArrayList<>();
        
        String selectQuery = "select * from `bookGenre`";
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = DataBase.getCon().prepareStatement(selectQuery);
            rs = ps.executeQuery();
            
            genre genre;
            
            while(rs.next()){
                genre = new genre(rs.getInt("ID"), rs.getString("name"), rs.getString("AuthorName"), rs.getFloat("Quantity"), rs.getString("Publisher"), rs.getString("Price"));
                
                gList.add(genre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(genre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return gList;
    }
    
    public genre getBookById(Integer ID)throws SQLException{
        
        String Query = "Select * from `bookGenre` where `ID` = " + ID;
        functionClass func = new functionClass();
        ResultSet rs = func.getData(Query);
        
        if(rs.next()){
            return new genre(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5), rs.getString(6));
        }
        else{
            return null;
        }
    }
    
    public void setQuantityMinusOne(int BOOKID, float QUANTITY){
        String updateQuantityQuery;
        PreparedStatement ps;
        
        try{
            updateQuantityQuery = "update `bookGenre` set `Quantity` = ? where `ID` = ?";
            ps = DataBase.getCon().prepareStatement(updateQuantityQuery);
            
            ps.setFloat(1, QUANTITY);
            ps.setInt(2, BOOKID);
            
            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "This Book is SET to LOST, and Quantity is reduced by 1", "edit Book Quantity", 1);
            }
            else{
                JOptionPane.showMessageDialog(null, "Book Quantity not edited", "edit Book Quantity", 2);
            }
        }   catch(SQLException ex){
            Logger.getLogger(genre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Function to populate an arrayList with genres
    public ArrayList<genre> genreList(String query){
        ArrayList<genre>bkList = new ArrayList<>();
        
        try {
            
            if(query.equals("")){   
                //If the user enter empty string makes this the default select.
                query = "SELECT * FROM `bookGenre`";
            }

            //ResultSet rs = func.getData("SELECT * FROM `manageStudent`");
            ResultSet rs = func.getData(query);
            
            genre genre;
            
            while(rs.next()){
                genre = new genre(rs.getInt("ID"), rs.getString("name"), rs.getString("AuthorName"), rs.getFloat("Quantity"), rs.getString("Publisher"), rs.getString("Price"));

                bkList.add(genre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(member.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bkList;
    }
}
