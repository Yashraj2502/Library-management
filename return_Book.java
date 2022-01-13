
import java.awt.print.Book;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class return_Book {
    private int  bookID;
    private int memberID;
    private String status;     //Issued/returned/lost
    private String issueBook;
    private String returnBook;
    private String note;
    
    Book book = new Book();
    //Add a new issue
    public void addIssue(int BOOKID, int MEMBERID, String STATUS, String ISSUEDATE, String RETURNDATE, String NOTE){
        String issueQuery = "INSERT INTO `issueBook` (`bookID`,`memberID`,`status`,`issueDate`,`returnDate`, `note`) VALUES (?,?,?,?,?,?)";
        
        try{
            PreparedStatement ps = DataBase.getCon().prepareStatement(issueQuery);
            
            ps.setInt(1, BOOKID);
            ps.setInt(2, MEMBERID);
            ps.setString(3, STATUS);
            ps.setString(4, ISSUEDATE);
            ps.setString(5, RETURNDATE);
            ps.setString(6, NOTE);

            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "Issue Added", "add issue", 1);
            }
            else{
                JOptionPane.showConfirmDialog(null, "Issue not added", "add issue", 2);
            }
        } catch(SQLException ex){
            Logger.getLogger(issue_Book.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
