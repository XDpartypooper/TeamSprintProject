/*
 This is a sub class of User
 */
package User;

import ETC.Bids;
import ETC.Papers;
import ETC.Reviews;
import Gui.ReviewerMenu;
import Gui.ReviewerPapers;
import Gui.ReviewerBid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
         else
         {
             WL=null;
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
        mySmt.setString(1, ID);//get name from ID id
        rs = mySmt.executeQuery();
        
         if(rs.next()) //exist
         {                                
            mySmt = conn.prepareStatement("update Reviewer set WorkLoad = ? where ReviewerID=?"); 
            mySmt.setString(1, WL);//
            mySmt.setString(2, ID);//get name from ID id
            mySmt.executeUpdate();      
         }
         else//make new entry
         {
            mySmt = conn.prepareStatement("INSERT INTO Reviewer (ReviewerID,WorkLoad) VALUES (?, ?);");          
            mySmt.setString(1, ID);//newPname  
            mySmt.setString(2, WL);//CoAuthorID    
            mySmt.executeUpdate();
         }  
        conn.close();
    }
    
    public ArrayList ReviewedPaper(String ID) throws SQLException
    {
        //get all authors except self 
         java.sql.Connection conn=null;
        ResultSet rs =null;
   
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("select papers.PaperName, papers.AuthorID,papers.co_AuthorID,Reviews.Review,Reviews.Rating from papers inner join reviews on reviews.PaperID=papers.PaperID where ReviewerID=?");  
        mySmt.setString(1, ID);//Reviewer ID
        
        rs = mySmt.executeQuery();
        
        ArrayList<Reviews> al = new ArrayList<Reviews>();
        
         while(rs.next()) //find works
         {
                Reviews R = new Reviews(rs.getString(1),GetNameDB(rs.getString(2)),GetNameDB(rs.getString(3)),rs.getString(4),rs.getInt(5));
                //paper name,Author,co author, review , Rating null = pending
                al.add(R);                      
         }    
  
         conn.close();
         return al;  
    }
    
    
    public void ReviewerSUBUP(String PaperName,String Review,int Rating) throws SQLException
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;
        String paperID;
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt=conn.prepareStatement("Select * from papers where PaperName = ?");
        mySmt.setString(1, PaperName);//Paper id
        
        rs = mySmt.executeQuery();

         if(rs.next()) //find works
         {                                
            paperID = rs.getString(4);  //get PaperID   
            
            mySmt = conn.prepareStatement("update Reviews set Review = ?, Rating=?  where PaperID=?"); 
            mySmt.setString(1, Review);//
            mySmt.setInt(2, Rating);//get name from ID id
            mySmt.setString(3, paperID);//
            mySmt.executeUpdate();      
         }
        conn.close();
    }
    
    public void DeleteReview(String PaperName) throws SQLException
    {
    java.sql.Connection conn=null;
        ResultSet rs =null;
        String paperID;
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt=conn.prepareStatement("Select * from papers where PaperName = ?");
        mySmt.setString(1, PaperName);//Paper id
        
        rs = mySmt.executeQuery();

         if(rs.next()) //find works
         {                                
            paperID = rs.getString(4);  //get PaperID   
            
            mySmt = conn.prepareStatement("update Reviews set Review = null, Rating=0  where PaperID=?"); 
            mySmt.setString(1, paperID);//
            mySmt.executeUpdate();      
         }    
        conn.close();
    }
    
    public ArrayList ViewBid(String ID) throws SQLException
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;

        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("select  papers.PaperName, bids.PaperID , Bid_status from bids inner join papers where bids.PaperID=papers.PaperID and BidderID=?");  
        mySmt.setString(1, ID);//reviewer id
       
        
        rs = mySmt.executeQuery();
        ArrayList<Bids> al = new ArrayList<Bids>();
        
         while(rs.next()) //find works
         {
   
                Bids B = new Bids(rs.getString(1),rs.getString(2),rs.getInt(3));
                //paper name , status
                al.add(B);                       
         }
         conn.close();
        return al;
    }
    
    public ArrayList ViewBidDel(String ID) throws SQLException
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;

        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("select  papers.PaperName, bids.PaperID , Bid_status from bids inner join papers where bids.PaperID=papers.PaperID and BidderID=? and Bid_status=0");  
        mySmt.setString(1, ID);//reviewer id
       
        
        rs = mySmt.executeQuery();
        ArrayList<Bids> al = new ArrayList<Bids>();
        
         while(rs.next()) //find works
         {
   
                Bids B = new Bids(rs.getString(1),rs.getString(2),rs.getInt(3));
                //paper name , status
                al.add(B);                       
         }
         conn.close();
        return al;
    }
    
    
    public ArrayList ViewPapers(String ID,int choice) throws SQLException
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;

        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("Select * from papers where ALReviewerID = ? ");  
        mySmt.setString(1, ID);//reviewer id
       
        
        if(choice==1)
        {
             mySmt = conn.prepareStatement("Select * from papers where ALReviewerID is null order by PaperID asc"); 
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
    
     public void DownloadPaper(String PaperName) throws SQLException, FileNotFoundException, IOException
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;
        PreparedStatement mySmt ;
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        mySmt = conn.prepareStatement("Select * from papers where PaperName =?"); 
        mySmt.setString(1, PaperName);//author name

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
     
     
     public void BidPaper(String paperName,String ID) throws SQLException
     {
        java.sql.Connection conn=null;
        ResultSet rs =null;

        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("Select * from papers where PaperName = ? ");  
        mySmt.setString(1, paperName);//reviewer id
         rs = mySmt.executeQuery();
         if(rs.next()) //find works
         {                                
            paperName = rs.getString(4);  //get PaperID             
         }
         
        mySmt = conn.prepareStatement("Select * from Bids where PaperID = ? and BidderID =?");
        mySmt.setString(1, paperName);//reviewer id
        mySmt.setString(2, ID);//reviewer id
         if(rs.next()) //find works
         {                                
            JOptionPane.showMessageDialog(null,"You cannot place more then 1 bid on a paper");  
            throw new SQLException();
         }
       
         mySmt = conn.prepareStatement("INSERT INTO Bids (PaperID,BidderID,Bid_status) VALUES (?, ?, 0);");          
         mySmt.setString(1, paperName);//newPname  
         mySmt.setString(2, ID);//CoAuthorID    
         mySmt.executeUpdate();   
         JOptionPane.showMessageDialog(null,"Successfully Bid paper");
     }
     
      public void DeleteBidPaper(String paperName,String ID) throws SQLException
     {
        java.sql.Connection conn=null;
        ResultSet rs =null;

        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("Select * from papers where PaperName = ? ");  
        mySmt.setString(1, paperName);//reviewer id
         rs = mySmt.executeQuery();
        
         if(rs.next()) //find works
         {                                
            paperName = rs.getString(4);  //get PaperID             
         }
         
   
         

          mySmt = conn.prepareStatement("delete from Bids where PaperID=? and BidderID=?"); 
          mySmt.setString(1, paperName);//Paper ID  
          mySmt.setString(2, ID);//reviewer ID  

           mySmt.execute();
           conn.close();
         
         JOptionPane.showMessageDialog(null,"Successfully deleted paper Bid");
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
