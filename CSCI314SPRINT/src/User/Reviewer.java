/*
 This is a sub class of User
 */
package User;

import ETC.Papers;
import Gui.ReviewerMenu;
import Gui.ReviewerPapers;
import Gui.ReviewerBid;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author XDpartypooper
 */
public class Reviewer extends UserProfile{
    
    public Reviewer()
    {
    }
    
    public Reviewer(String name,String ID)
    {
        UserName=name;
        this.ID=ID;
    }
    
    public String GetName()
    {
        return UserName;
    }
    
    
    public String WorkLoadGet(String ID) throws SQLException
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;
        String WL=null;

        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("Select * from Reviewer where ReviewerID = ? ");  
        mySmt.setString(1, ID);//get name from ID id

        rs = mySmt.executeQuery();
       
         if(rs.next()) //find works
         {                                
            WL = rs.getString(2);               
         }
         
        conn.close();
        return WL;
    }
    
    public void EditWorkLoad(String WL,String ID) throws SQLException
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;

        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("Select * from Reviewer where ReviewerID = ? ");  
        mySmt = conn.prepareStatement("update Reviewer set WorkLoad = ? where ReviewerID=?"); 
        mySmt.setString(1, WL);//get name from ID id
        mySmt.setString(2, ID);//get name from ID id

        mySmt.executeUpdate();
         
        conn.close();
    }
    
    public ArrayList ViewPapers(String ID) throws SQLException
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;

        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("Select * from papers where ALReviewerID = ? ");  
        mySmt.setString(1, ID);//reviewer id
        rs = mySmt.executeQuery();
        
        ArrayList<Papers> al = new ArrayList<Papers>();
        
         while(rs.next()) //find works
         {
   
                Papers P = new Papers(rs.getString(1),GetNameDB(rs.getString(2)),GetNameDB(rs.getString(3)),rs.getString(4),GetNameDB(rs.getString(5)));
                //paper name, paper id , author ID , co author name , reviewer ID
                al.add(P);                       
         }
         conn.close();
        return al;
    }
     
    public void GotoReviewMenu(String name,String ID)
    {
         new ReviewerMenu(name,ID).setVisible(true);
    }
    
    public void GotoReviewPapers(String name,String ID)
    {
         new ReviewerPapers(name,ID).setVisible(true);
    }
    
    public void GotoReviewBid(String name,String ID)
    {
         new ReviewerBid(name,ID).setVisible(true);
    }
    
    
    
}
