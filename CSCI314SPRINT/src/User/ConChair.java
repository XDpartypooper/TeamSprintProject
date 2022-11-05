/*
 This is a sub class of User
 */
package User;

import ETC.Bids;
import ETC.Papers;
import Gui.ConChairMenu;
import Gui.ConChairPapers;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author XDpartypooper
 */
public class ConChair extends UserProfile{
    
  
    public ArrayList ViewPapers(int choice) throws SQLException
    {
        // allocated papers
        java.sql.Connection conn=null;
        ResultSet rs =null;

        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt=null;
        
        switch (choice)
        {
            case 1:
                mySmt = conn.prepareStatement("Select * from papers order by PaperID asc");
                break;
            case 2:
                mySmt = conn.prepareStatement("Select * from papers where ALReviewerID is not null order by PaperID asc");  
                break;
            case 3:
                mySmt = conn.prepareStatement("Select * from papers where ALReviewerID is null order by PaperID asc"); 
                break;
        }
        
        
        
        rs = mySmt.executeQuery();
        
        ArrayList<Papers> al = new ArrayList<Papers>();
        
         while(rs.next()) //find works
         {
   
                Papers P = new Papers(rs.getString(1),GetNameDB(rs.getString(2)),GetNameDB(rs.getString(3)),rs.getString(4),GetNameDB(rs.getString(5)),rs.getString(7));
                //paper name, paper id , author ID , co author name , reviewer ID
                al.add(P);                       
         }
         conn.close();
        return al;
    }
    
    public ArrayList ViewBid() throws SQLException
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;

        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("select  papers.PaperName, bids.PaperID ,bids.BidderID, Bid_status from bids inner join papers where bids.PaperID=papers.PaperID ORDER BY Bid_status ASC");  
 
        rs = mySmt.executeQuery();
        ArrayList<Bids> al = new ArrayList<Bids>();
        
         while(rs.next()) //find works
         {
                Bids B = new Bids(rs.getString(1),rs.getString(2),GetNameDB(rs.getString(3)),rs.getInt(4));
                //paper name ,paper id, BidderID, status
                al.add(B);                       
         }
         conn.close();
        return al;
    }
    
    
    public ArrayList ViewSearchPapers(String word,String Search) throws SQLException
    {
        // allocated papers
        java.sql.Connection conn=null;
        ResultSet rs =null;
        PreparedStatement mySmt=null;
        
        if(Search=="P")
        {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
            mySmt = conn.prepareStatement("SELECT * FROM papers WHERE PaperName LIKE ? order by PaperID asc");  
            mySmt.setString(1,'%'+word+'%');//User entered   
        }
        
        if(Search=="R")
        {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
            mySmt = conn.prepareStatement("SELECT papers.PaperName,papers.AuthorID,papers.co_AuthorID,papers.PaperID,papers.ALReviewerID FROM papers inner join users on users.ID =papers.ALReviewerID WHERE users.UserName like ? order by PaperID asc");  
            mySmt.setString(1,'%'+word+'%');//User entered   
        }
        
        
        rs = mySmt.executeQuery();
        
        ArrayList<Papers> al = new ArrayList<Papers>();
        
         while(rs.next()) //find works
         {
                Papers P = new Papers(rs.getString(1),GetNameDB(rs.getString(2)),GetNameDB(rs.getString(3)),rs.getString(4),GetNameDB(rs.getString(5)),rs.getString(7));
                //paper name, paper id , author ID , co author name , reviewer ID,date
                al.add(P);                       
         }
         conn.close();
        return al;
    }
    
    public void UpdatePaper(String PaperName,String Reviewer) throws SQLException//allocate
    {
            java.sql.Connection conn=null;
            Reviewer=GetUserID(Reviewer);
            
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
            PreparedStatement mySmt = conn.prepareStatement("update papers set ALReviewerID=? where PaperName=?");    
            mySmt.setString(1, Reviewer);//newPname  
            mySmt.setString(2, PaperName);//CoAuthorID  
 
            mySmt.executeUpdate();
            //update values
            
           conn.close();
    
    }
    
    public void UpdateBidstatus(String PaperName,String Reviewer) throws SQLException
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;
        String paperID=null;
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt=conn.prepareStatement("Select * from papers where PaperName = ?");
        mySmt.setString(1, PaperName);//Paper id
        
        rs = mySmt.executeQuery();
         
         Reviewer=GetUserID(Reviewer);// convert reviewer name to ID
         if(rs.next()) //find works
         {                                
            paperID = rs.getString(4);  //get PaperID             
         }
         mySmt= conn.prepareStatement("update Bids set Bid_status = 1 where BidderID=? and  PaperID = ?");  
         mySmt.setString(1, Reviewer);//CoAuthorID
         mySmt.setString(2, paperID);//CoAuthorID
         mySmt.executeUpdate();
         
         mySmt= conn.prepareStatement("update Bids set Bid_status = 2 where BidderID!=? and  PaperID = ?"); 
         mySmt.setString(1, Reviewer);//CoAuthorID
         mySmt.setString(2, paperID);//CoAuthorID
         mySmt.executeUpdate();
    }
    
    
     public ArrayList getReviewers() throws SQLException
    {
        //get all authors except
         java.sql.Connection conn=null;
        ResultSet rs =null;
   
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("select * from users where ProfileType='Reviewer'");  
 
        rs = mySmt.executeQuery();
        
        ArrayList<Reviewer> al = new ArrayList<Reviewer>();
        
         while(rs.next()) //find works
         {
                Reviewer P = new Reviewer(rs.getString(1),rs.getString(5));
                //take the username+ID
                al.add(P);                      
         }    
  
         conn.close();
         return al;  
    }
     

    public void GotoCCMenu(String name,String ID)
    {
        new ConChairMenu(name,ID).setVisible(true);
    }
    public void GotoCCPapers(String name,String ID)
    {
        new ConChairPapers(name,ID).setVisible(true);
    }
    
}
