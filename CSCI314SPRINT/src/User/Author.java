/*
 This is a sub class of User
 */
package User;

import ETC.Papers;
import Gui.AuthorMenu;
import Gui.AuthorPapers;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author XDpartypooper
 */
public class Author extends UserProfile{

    public Author()
    {
        
    }
    public Author(String ID)
    {
        this.ID=ID;
    }
    public Author (String name,String ID)
    {
        UserName=name;
        this.ID=ID;
    }
    
    public String GetName()
    {
        return UserName;
    }
    
    
    public void GotoAuPapers(String name,String ID)
    {
         new AuthorPapers(name,ID).setVisible(true);
    }
    
     public void GotoAuMenu(String name,String ID)
    {
         new AuthorMenu(name,ID).setVisible(true);
    }
    
   
           
    
    
    public ArrayList GetAuthors(String ID) throws SQLException
    {
        //get all authors except
         java.sql.Connection conn=null;
        ResultSet rs =null;
   
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("select * from users where ProfileType='Author' and ID != ?");  
        mySmt.setString(1, ID);
        
        rs = mySmt.executeQuery();
        
        ArrayList<Author> al = new ArrayList<Author>();
        
         while(rs.next()) //find works
         {
                Author P = new Author(rs.getString(1),rs.getString(5));
                //take the username+ID
                al.add(P);                      
         }    
  
         conn.close();
         return al;  
    }
    
    public void SubmitPaper(String Pname,String AuthorID, String CoAuthorID) throws SQLException
    {
        //submit paper
        java.sql.Connection conn=null;
        ResultSet rs =null;
        String PaperID=null;
        PreparedStatement mySmt;  
        
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        
            mySmt = conn.prepareStatement("SELECT * FROM papers where PaperName = ?");   // check for exsisting paperName        
            mySmt.setString(1, Pname);//co author id
             rs = mySmt.executeQuery();
             if(rs.next())// insert data
             {
                 JOptionPane.showMessageDialog(null,"Paper with that name Already exist.");  
                 throw new SQLException();
             }
        
                  
             mySmt = conn.prepareStatement("SELECT * FROM papers order by PaperID desc");           
            //Get last ID from DB
             rs = mySmt.executeQuery();
             if(rs.next())// insert data
             {
                    PaperID = rs.getString(4);//get last ID
                    int idNum=Integer.parseInt(PaperID);//convert last id from string to int
                    idNum++;//LAST ID + 1
                    PaperID=String.format("%d",idNum);
             }
                
             mySmt = conn.prepareStatement("SELECT * FROM users where UserName = ?");           
             mySmt.setString(1, CoAuthorID);//co author id
             //Get ID from DB of co author
             rs = mySmt.executeQuery();
             if(rs.next())// insert data
             {
                    CoAuthorID = rs.getString(5);
             }
             else
             {
                    CoAuthorID=null;
             }
       
             mySmt = conn.prepareStatement("INSERT INTO papers (PaperName,AuthorID,co_AuthorID,PaperID,ALReviewerID) VALUES (?, ?, ?, ? , null);");         
             mySmt.setString(1, Pname);//Paper name
             mySmt.setString(2, AuthorID);//author id
             mySmt.setString(3, CoAuthorID);//co author id
             mySmt.setString(4, PaperID);//paper ID
             
             mySmt.executeUpdate();
                
             JFrame f=new JFrame();
             JOptionPane.showMessageDialog(f,"New paper succesfully submitted.");  
    }
    
    public ArrayList ViewPapers(String word,String ID) throws SQLException
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;
        PreparedStatement mySmt ;
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        mySmt = conn.prepareStatement("Select * from papers where AuthorID = ? or co_AuthorID=? "); 
        mySmt.setString(1, ID);//author name
        mySmt.setString(2, ID);//Co author name
        
        if (word!=null || word !="")
        {
            mySmt = conn.prepareStatement("Select * from papers where PaperName like ? and AuthorID = ? or co_AuthorID=? ");
            mySmt.setString(1,'%'+word+'%');//
            mySmt.setString(2, ID);//author name
            mySmt.setString(3, ID);//Co author name
           
        }
        
        
        
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
    
    public boolean CheckPID(String PID) throws SQLException
    {
        //if it exsist or not
        //submit paper
        java.sql.Connection conn=null;
        ResultSet rs =null;
        boolean check=false;
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        
             PreparedStatement mySmt;       
             mySmt = conn.prepareStatement("SELECT * FROM  where PaperID=?");           
            //Get last ID from DB
             mySmt.setString(1, PID);
             rs = mySmt.executeQuery();
             if(rs.next())// insert data
             {
                 check=true;
             }
        return check;
    }
    
    public boolean DeletePaper(String Name) throws SQLException
    {
           java.sql.Connection conn=null;
           boolean check=false;
           conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
           PreparedStatement mySmt = conn.prepareStatement("DELETE FROM Papers WHERE PaperName = ?"); 
           mySmt.setString(1, Name);//name of paper

           check = mySmt.execute();
           conn.close(); 
           return check;                  
    }
    
    public void UpdatePaper(String Pname,String newPname,String CoAuthorID) throws SQLException
    {
            java.sql.Connection conn=null;
            ResultSet rs =null;
            CoAuthorID =  GetUserID(CoAuthorID);
            PreparedStatement mySmt;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
            
            
            mySmt = conn.prepareStatement("SELECT * FROM papers where PaperName = ?");   // check for exsisting paperName        
            mySmt.setString(1, newPname);//co author id
             rs = mySmt.executeQuery();
             if(rs.next())// insert data
             {
                 JOptionPane.showMessageDialog(null,"Paper with that name Already exist.");  
                 throw new SQLException();
             }
            
            
            
            mySmt= conn.prepareStatement("update papers set PaperName = ? , co_AuthorID = ? where PaperName=?");    
            mySmt.setString(1, newPname);//newPname  
            mySmt.setString(2, CoAuthorID);//CoAuthorID  
            mySmt.setString(3, Pname);//CoAuthorID  
            mySmt.executeUpdate();
            //update values
            
           conn.close();  
    }
}  

