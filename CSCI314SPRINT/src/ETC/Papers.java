/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ETC;

import User.UserProfile;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author XDpartypooper
 */
public class Papers{
    String PaperName;
    String PaperID;
    String Author; // can be ID or Name
    String Co_Author;// can be ID or Name
    String ALReviewerID;// can be ID or Name
    
    public Papers()
    {
    }
    
    public Papers(String PaperName,String PaperID,String Author,String Co_Author,String ALReviewerID)
    {
        this.PaperName=PaperName;
        this.Author=Author;
        this.PaperID=PaperID;
        this.Co_Author=Co_Author;
        this.ALReviewerID=ALReviewerID;
        
        
    }
    
    public String[] GetPaper()
    {
        String file[]={PaperName,PaperID,Author,Co_Author,ALReviewerID};
        return file;
    }
    
     public String GetAuthor()
    {       
        return Author;
    }
    
      public String GetPName()
    {       
        return PaperName;
    }
    
      
       public ArrayList ViewPapers( ) throws SQLException // get all papers
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;

        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("Select * from papers");  
        
        rs = mySmt.executeQuery();
        UserProfile UP = new UserProfile();
        
        ArrayList<Papers> al = new ArrayList<Papers>();
        
         while(rs.next()) //find works
         {
   
                Papers P = new Papers(rs.getString(1),UP.GetNameDB(rs.getString(2)),UP.GetNameDB(rs.getString(3)),rs.getString(4),UP.GetNameDB(rs.getString(5)));
                //paper name, paper id , author ID , co author name , reviewer ID
                al.add(P);                       
         }
         conn.close();
        return al;
    }
}
