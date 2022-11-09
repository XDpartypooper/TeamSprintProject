/*
 This is a sub class of User
 */
package User;

import ControllerClass.ConChairController;
import ETC.Bids;
import ETC.Papers;
import Gui.ConChairMenu;
import Gui.ConChairPapers;
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
            mySmt = conn.prepareStatement("SELECT papers.PaperName,papers.AuthorID,papers.co_AuthorID,papers.PaperID,papers.ALReviewerID,papers.SubmitedDate FROM papers inner join users on users.ID =papers.ALReviewerID WHERE users.UserName like ? order by PaperID asc");  
            mySmt.setString(1,'%'+word+'%');//User entered   
        }
        
        
        rs = mySmt.executeQuery();
        
        ArrayList<Papers> al = new ArrayList<Papers>();
        
         while(rs.next()) //find works
         {
                if(Search=="R")
                {
                    Papers P = new Papers(rs.getString(1),GetNameDB(rs.getString(2)),GetNameDB(rs.getString(3)),rs.getString(4),GetNameDB(rs.getString(5)),rs.getString(6));
                    //paper name, paper id , author ID , co author name , reviewer ID,date
                    al.add(P);                       
                }
                else
                {
                    Papers P = new Papers(rs.getString(1),GetNameDB(rs.getString(2)),GetNameDB(rs.getString(3)),rs.getString(4),GetNameDB(rs.getString(5)),rs.getString(7));
                    al.add(P); 
                }
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
    
    public void UpdatePaperReviewStatusCon(String PaperName,String Reviewer) throws SQLException
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
            mySmt=conn.prepareStatement("Select * from Reviews where paperID = ?");
            mySmt.setString(1, paperID);//Paper id
            rs = mySmt.executeQuery();
                if(rs.next()) //exsist
                {
                    mySmt = conn.prepareStatement("update Reviews set ReviewerID=?,Review = null, Rating=0  where PaperID=?");
                    mySmt.setString(1, GetUserID(Reviewer));//
                    mySmt.setString(2, paperID);//g
                    mySmt.executeUpdate();  
                }
                else//create new entry               
                {
                   mySmt = conn.prepareStatement("INSERT INTO Reviews (PaperID,ReviewerID,Review,Rating) VALUES (?,?,null,null)"); 
                   mySmt.setString(1, paperID);//
                   mySmt.setString(2, GetUserID(Reviewer));//get name from ID id
                   mySmt.executeUpdate();
                }    
         }
        conn.close();
    }
    
    
     public ArrayList getReviewers() throws SQLException
    {
        //get all authors except
         java.sql.Connection conn=null;
        ResultSet rs =null;
          ResultSet rs1 =null;
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("select * from users where ProfileType='Reviewer'");  
 
        rs = mySmt.executeQuery();
        
        ArrayList<Reviewer> al = new ArrayList<Reviewer>();
        
         while(rs.next()) //find works
         {     
                String name=rs.getString(1);
                String ID=rs.getString(5);
                String WL;
                
                PreparedStatement mySmt1 = conn.prepareStatement("select * from Reviewer where ReviewerID = ?");  
                mySmt1.setString(1, ID);//Paper id
                
                rs1 = mySmt1.executeQuery();
                    if(rs1.next()) //
                    {
                        WL=rs1.getString(2);                  
                    }
                    else
                    {
                        WL=null;
                    }

                Reviewer P = new Reviewer(name,ID,WL);
                //take the username ,  ID  , workload
                al.add(P);                      
         }    
  
         conn.close();
         return al;  
    }
     
    public void AutoAllocation() throws SQLException
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;
        ResultSet rs1 =null;
        String ReviewerName;
        String PaperName=null;
        PreparedStatement mySmt1; 
        int ReviewerCount=0;
        int PaperCount=0;
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("select count(*) from users where ProfileType='Reviewer'");       
        rs = mySmt.executeQuery();
        
          if(rs.next()) //
          {
             ReviewerCount=rs.getInt(1);//get num of reviewer
          }  

        mySmt = conn.prepareStatement("select count(*) from papers where ALReviewerID is null");       
        rs = mySmt.executeQuery();
        
          if(rs.next()) //
          {
             PaperCount=rs.getInt(1);//get num of papers left to allocate
          }  
          
          double avg=(double)PaperCount/(double)ReviewerCount;
          
          ConChairController CCC= new ConChairController();
         
          if(avg<1)// allocate 1:1 till finished
          {
             mySmt = conn.prepareStatement("select * from users where ProfileType='Reviewer'");
             rs = mySmt.executeQuery();
             while(rs.next())//for each reviewer
             {
                 
                ReviewerName=rs.getString(1);//get the reviewer ID
                 
                    mySmt1 = conn.prepareStatement("  select * from papers where ALReviewerID is null");
                    rs1 = mySmt1.executeQuery();//do papers query

                     if(rs1.next()) // allocate paper
                     {
                         PaperName=rs1.getString(1);//get papers ID
                     }  

                     CCC.UpdatePaperCon( PaperName, ReviewerName);                                        
             }
          }
          else // allocate avg
          {
             avg=Math.floor(avg);
             mySmt = conn.prepareStatement("select * from users where ProfileType='Reviewer'");
             rs = mySmt.executeQuery();
             while(rs.next())//for each reviewer
             {
                 
                ReviewerName=rs.getString(1);//get the reviewer ID
                
                for(int i=0;i<avg;i++)//do allocate based on avg
                {
                    mySmt1 = conn.prepareStatement("  select * from papers where ALReviewerID is null");
                    rs1 = mySmt1.executeQuery();//do papers query

                     if(rs1.next()) // allocate paper
                     {
                         PaperName=rs1.getString(1);//get papers ID
                     }  

                     CCC.UpdatePaperCon( PaperName, ReviewerName);                         
                }
             }
             
          }
          
        JOptionPane.showMessageDialog(null,"Successfully Allocated Papers Automaticlly");   
        conn.close();   
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
