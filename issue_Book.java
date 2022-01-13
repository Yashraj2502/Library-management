
import java.awt.print.Book;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class issue_Book {
    genre genre = new genre();
    functionClass func = new functionClass();
    
    private int  bookID;
    private int memberID;
    private String status;     //Issued/returned/lost
    private String issueBook;
    private String returnBook;
    private String note;
    
    //Getter
    public int getBookID() {
        return bookID;
    }

    public int getMemberID() {
        return memberID;
    }

    public String getStatus() {
        return status;
    }

    public String getIssueBook() {
        return issueBook;
    }

    public String getReturnBook() {
        return returnBook;
    }

    public String getNote() {
        return note;
    }

    public genre getGenre() {
        return genre;
    }

    
    
    //Setter
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIssueBook(String issueBook) {
        this.issueBook = issueBook;
    }

    public void setReturnBook(String returnBook) {
        this.returnBook = returnBook;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setGenre(genre genre) {
        this.genre = genre;
    }
    
    public issue_Book(){
        
    }
    
    public issue_Book(int BOOKID, int MEMBERID, String STATUS, String ISSUEBOOK, String RETURNBOOK, String NOTE){
        this.bookID = BOOKID;
        this.memberID = MEMBERID;
        this.status = STATUS;
        this.issueBook = ISSUEBOOK;
        this.returnBook = RETURNBOOK;
        this.note = NOTE;
    }
    
    
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
    
    public boolean checkBookAvailability(int BOOKID){
        //Get the book quantity ffrom the table BOOK and compare it to the no. of the book issued int table ISSUEBOOK
        
        boolean availability = false;
        
        try{
            //first get the book quantity
            genre selectedBook = genre.getBookById(BOOKID);
            float quantity = selectedBook.getQuantity();
            
            //Get the number of books issued(Same book)
            int issueBookCount = countData(BOOKID);
            
            if(quantity > issueBookCount){
                availability = true;
            }
            else{
                availability = false;
            }
            
            //1:33:40
        }   catch(SQLException ex){
            Logger.getLogger(issue_Book.class.getName()).log(Level.SEVERE, null, ex);            
        }
        
        return availability;
    }
    
    //Update the records Functions:-
    public void updateIssue(int BookID, int MemberID, String STATUS, String ISSUEDATE, String RETURNDATE, String NOTE){
        
        String updateQuery = "update `issueBook` set `status` = ?, `returnDate` = ?, `note` = ? where `bookID` = ? and `memberID` = ? and `issueDate` = ?";
        
        try {
            PreparedStatement ps = DataBase.getCon().prepareStatement(updateQuery);
            
            ps.setString(1, STATUS);
            ps.setString(2, RETURNDATE);
            ps.setString(3, NOTE);
            ps.setInt(4, BookID);
            ps.setInt(5, MemberID);
            ps.setString(6, ISSUEDATE);
            
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "STATUS Updated", "Book Issue", 1);
            }
            else{
                JOptionPane.showMessageDialog(null, "STATUS Not Updated", "Book Issue", 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(genre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int countData(int _book_id){
        int total = 0;
        ResultSet rs;
        PreparedStatement ps;
        
        try{
            ps = DataBase.getCon().prepareStatement("SELECT COUNT(*) as total FROM `issueBook` WHERE `bookID` = ? and `status` = 'issued'");
            ps.setInt(1, _book_id);
            rs = ps.executeQuery();
            
            if(rs.next()){
                total = rs.getInt("Total");
            }
        }   catch(SQLException ex){
            Logger.getLogger(issue_Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    //Function to populate an arrayList with issued/return/lost books
    public ArrayList<issue_Book> issueBookList(String Status){
        
        //Create a list
        ArrayList<issue_Book> issBookList = new ArrayList<>();
        String query = "";
        
        if(Status.equals("")){
            //If STATUS is empty, show all data
            query = "select * from `issueBook`";
        }
        else{
            //Show data depending on the selected Genre
            query = "select * from `issueBook` where `status` = '" + Status + "'";
        }

        try {
            ResultSet rs = func.getData(query);
            
            issue_Book issBook;
            
            while(rs.next()){
                issBook = new issue_Book(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                
                issBookList.add(issBook);
            }
        } catch (SQLException ex) {
            Logger.getLogger(genre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return issBookList;
    }
    
    //remove issuedBook using BookID:-
    public void removeRow(int bookID, int memberID, String issueDate){
        
        String editQuery = "delete FROM `issueBook` WHERE `bookID` = ? and `memberID` = ? and `issueDate` = ?";
        
        try {
            PreparedStatement ps = DataBase.getCon().prepareStatement(editQuery);
            
            ps.setInt(1, bookID);
            ps.setInt(2, memberID);
            ps.setString(3, issueDate);
            
            if(ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Deleted Successfully", "remove", 1);
            }
            else{
                JOptionPane.showMessageDialog(null, "NOT Deleted", "remove", 2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(issue_Book.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
