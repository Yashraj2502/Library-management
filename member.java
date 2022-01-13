
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
public class member {
    private int id;
    private String name, motherName, emailID, contactNo;
    private String Gender;
    
    functionClass func = new functionClass();
    
    //Constuctor
    public member(){
        ;
    }
    
    public member(int ID, String NAME, String MOTHERNAME, String EMAILID, String CONTACTNO, String GENDER){
        this.id = ID;
        this.name = NAME;
        this.motherName = MOTHERNAME;
        this.emailID = EMAILID;
        this.contactNo = CONTACTNO;
        this.Gender = GENDER;
    }

    //Getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getEmailID() {
        return emailID;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getGender() {
        return Gender;
    }
    
    
    //Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }
    

    
    //Add new genre Functions:-
    public void addMember(String ID, String NAME, String MOTHERNAME, String EMAILID, String CONTACTNO, String GENDER){
        
        //String insertQuery = "insert into `genreTest` (`name`) values(?)";
        String insertQuery = "insert into `manageStudent` (`ID`, `name`, `MothersName`, `emailID`, `ContactNO`, `Gender`) values(?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = DataBase.getCon().prepareStatement(insertQuery);
            ps.setString(1, ID);
            ps.setString(2, NAME);
            ps.setString(3, MOTHERNAME);
            ps.setString(4, EMAILID);
            ps.setString(5, CONTACTNO);
            ps.setString(6, GENDER);
            
            if(ps.executeUpdate() == 1)
            {
                JOptionPane.showMessageDialog(null, "Member Added", "Add Member", 1);
            }
            else{
                JOptionPane.showMessageDialog(null, "Member NOT Added", "Add Member", 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(member.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //edit genre by ID Functions:-
    public void editMember(String ID, String NAME, String MOTHERNAME, String EMAILID, String CONTACTNO, String GENDER){
        
        //String editQuery = "Update `genreTest` SET `name` = ? WHERE `id` = ?";
        String editQuery = "Update `manageStudent` SET `name` = ?, `mothersName` = ?, `emailID` = ?, `ContactNo` = ?, `Gender` = ? WHERE `ID` = ?";
        
        try {
            PreparedStatement ps = DataBase.getCon().prepareStatement(editQuery);
            
            ps.setString(1, NAME);
            ps.setString(2, MOTHERNAME);
            ps.setString(3, EMAILID);
            ps.setString(4, CONTACTNO);
            ps.setString(5, GENDER);
            ps.setString(6, ID);
            
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Member Edited", "edit Member", 1);
            }
            else{
                JOptionPane.showMessageDialog(null, "Member NOT Edited", "edit Member", 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(member.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //remove genre by ID Functions:-
    public void removeMember(int ID){
        
        //String editQuery = "DELETE FROM `genreTest` WHERE `id` = ?";
        String editQuery = "DELETE FROM `manageStudent` WHERE `id` = ?";
        
        try {
            PreparedStatement ps = DataBase.getCon().prepareStatement(editQuery);
            
            ps.setInt(1, ID);
            
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Member Deleted", "remove", 1);
            }
            else{
                JOptionPane.showMessageDialog(null, "Member NOT Deleted", "remove", 2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(member.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Function to populate an arrayList with genres
    public ArrayList<member> memberList(String query){
        ArrayList<member> mList = new ArrayList<>();
        
        try {
            if(query.equals("")){   
                //If the user enter empty string makes this the default select.
                query = "SELECT * FROM `manageStudent`";
            }

            //ResultSet rs = func.getData("SELECT * FROM `manageStudent`");
            ResultSet rs = func.getData(query);
            
            member member;
            
            while(rs.next()){
                member = new member(rs.getInt("ID"), rs.getString("name"), rs.getString("mothersName"), rs.getString("emailID"), rs.getString("ContactNo"), rs.getString("Gender"));

                mList.add(member);
            }
        } catch (SQLException ex) {
            Logger.getLogger(member.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return mList;
    }

    public member getMemberById(Integer ID)throws SQLException{
        
        String Query = "Select * from `manageStudent` where `ID` = " + ID;
        functionClass func = new functionClass();
        ResultSet rs = func.getData(Query);
        
        if(rs.next()){
            return new member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
        }
        else{
            return null;
        }
    }
    
    //Function to populate an arrayList with genres
    public ArrayList<member> memberList(){
        ArrayList<member> mList = new ArrayList<>();
        
        //String selectQuery = "select * from `genreTest`";
       String selectQuery = "select * from `manageStudent`";
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = DataBase.getCon().prepareStatement(selectQuery);
            rs = ps.executeQuery();
           
            member member;
            
            while(rs.next()){
                member = new member(rs.getInt("ID"), rs.getString("name"), rs.getString("mothersName"), rs.getString("emailID"), rs.getString("ContactNo"), rs.getString("Gender"));

                mList.add(member);
            }
        } catch (SQLException ex) {
            Logger.getLogger(member.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return mList;
    }
}
