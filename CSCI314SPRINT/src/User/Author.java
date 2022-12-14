/*
 This is a sub class of User
 */
package User;

import ETC.Papers;
import ETC.Reviews;
import Gui.AuthorMenu;
import Gui.AuthorPapers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
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

     public ArrayList ReviewedPaper(String ID) throws SQLException
    {
        //get all authors except self 
         java.sql.Connection conn=null;
        ResultSet rs =null;
   
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("select papers.PaperName, Reviews.ReviewerID,Reviews.Review,Reviews.Rating,Reviews.Review_status from papers inner join reviews on reviews.PaperID=papers.PaperID where AuthorID=? or co_AuthorID=?");  
        mySmt.setString(1, ID);
        mySmt.setString(2, ID);//author and co author can view reviewed paper
        
        rs = mySmt.executeQuery();
        
        ArrayList<Reviews> al = new ArrayList<Reviews>();
        
         while(rs.next()) //find works
         {
                Reviews R = new Reviews(rs.getString(1),GetNameDB(rs.getString(2)),rs.getString(3),rs.getInt(4),rs.getInt(5));
                //paper name, reviewer(id), review , Rating null =0, rating status
                al.add(R);                      
         }    
  
         conn.close();
         return al;  
    }
     
     public ArrayList ReviewedDonePaper(String ID) throws SQLException
    {
        //get all authors except self 
         java.sql.Connection conn=null;
        ResultSet rs =null;
   
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("select papers.PaperName, Reviews.ReviewerID,Reviews.Review,Reviews.Rating from papers inner join reviews on reviews.PaperID=papers.PaperID where Review_status=1 and AuthorID=? or co_AuthorID=?");  
        mySmt.setString(1, ID);
        mySmt.setString(2, ID);//author and co author can view reviewed paper
        
        rs = mySmt.executeQuery();
        
        ArrayList<Reviews> al = new ArrayList<Reviews>();
        
         while(rs.next()) //find works
         {
                Reviews R = new Reviews(rs.getString(1),GetNameDB(rs.getString(2)),rs.getString(3),rs.getInt(4));
                //paper name, reviewer(id), review , Rating null =0
                al.add(R);                      
         }    
  
         conn.close();
         return al;  
    }
   
           
    
    
    public ArrayList GetAuthors(String ID) throws SQLException
    {
        //get all authors except self 
         java.sql.Connection conn=null;
        ResultSet rs =null;
   
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("select * from users where ProfileType='Author' and ID != ?");  
        mySmt.setString(1, ID);
        
        rs = mySmt.executeQuery();
        
        ArrayList<Author> al = new ArrayList<Author>();
        
         while(rs.next()) //find works
         {
                Author A = new Author(rs.getString(1),rs.getString(5));
                //take the username+ID
                al.add(A);                      
         }    
  
         conn.close();
         return al;  
    }
    
    public void SubmitPaper(String Pname,String AuthorID, String CoAuthorID,File file) throws SQLException, FileNotFoundException
    {
        //String Pname,String AuthorID, String CoAuthorID,,File file
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
             FileInputStream file0 = new FileInputStream(file); 
             mySmt = conn.prepareStatement("INSERT INTO papers (PaperName,AuthorID,co_AuthorID,PaperID,ALReviewerID,Paper_File,SubmitedDate) VALUES (?, ?, ?, ? , null, ?, CURDATE());");         
             mySmt.setString(1, Pname);//Paper name
             mySmt.setString(2, AuthorID);//author id
             mySmt.setString(3, CoAuthorID);//co author id
             mySmt.setString(4, PaperID);//paper ID
             mySmt.setBlob(  5, file0);//file
      
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
                Papers P = new Papers(rs.getString(1),GetNameDB(rs.getString(2)),GetNameDB(rs.getString(3)),rs.getString(4),GetNameDB(rs.getString(5)),rs.getString(7));
                //paper name, paper id , author ID , co author name , reviewer ID, Date
                al.add(P);                       
         }
         conn.close();
        return al;
    }
    
    public void DownloadPaper(String PaperName,String ID) throws SQLException, FileNotFoundException, IOException
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;
        PreparedStatement mySmt ;
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        mySmt = conn.prepareStatement("Select * from papers where PaperName =? and AuthorID =?"); 
        mySmt.setString(1, PaperName);//author name
        mySmt.setString(2, ID);//Co author name
 
        rs = mySmt.executeQuery();
        
         while(rs.next()) //find works
         { 
               
                String PName = rs.getString(1);;
                Blob  Blob   = rs.getBlob(6);
                
                if(Blob==null)
                {
                    JOptionPane.showMessageDialog(null,"NO FILE in DATABASE");
                    throw new SQLException();
                }
                byte[] bdata = Blob.getBytes(1, (int) Blob.length());
                String s = new String(bdata);
                
                File file = new File("C:\\Users\\Public\\Downloads\\"+PName+".txt");
                FileOutputStream fos = new FileOutputStream(file);
                
                if (!file.exists())
                {
                   file.createNewFile();
                }
                byte[] B = s.getBytes();
                fos.write(B);
		fos.flush();
		fos.close();
                String dir = "C:\\Users\\Public\\Downloads\\";
                JOptionPane.showMessageDialog(null,"File downloaded At "+dir);
         }
         conn.close();

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
    
    public void UpdatePaper(String Pname,String newPname,String CoAuthorID,File file) throws SQLException, FileNotFoundException
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
            
            
            if(file!=null){
                FileInputStream file0 = new FileInputStream(file); 
                mySmt= conn.prepareStatement("update papers set PaperName = ? , co_AuthorID = ?,Paper_File = ? where PaperName=?");    
                mySmt.setString(1, newPname);//newPname  
                mySmt.setString(2, CoAuthorID);//CoAuthorID  
                mySmt.setBlob(3, file0);//CoAuthorID  
                mySmt.setString(4, Pname);//CoAuthorID  
                mySmt.executeUpdate();
            //update values
            }
            else
            {
                mySmt= conn.prepareStatement("update papers set PaperName = ? , co_AuthorID = ? where PaperName=?");    
                mySmt.setString(1, newPname);//newPname  
                mySmt.setString(2, CoAuthorID);//CoAuthorID  
                mySmt.setString(3, Pname);//CoAuthorID  
                mySmt.executeUpdate();
            }
            
            
           conn.close();  
    }
}  

