/*
    Controller class for Reviwer
 */
package ControllerClass;

import User.ConChair;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author XDpartypooper
 */
public class ConChairController {
   
    
     public ArrayList ReviewedPaperCon() throws SQLException
    {
        ArrayList al=null;
        ConChair CC=new ConChair();
        al=CC.ReviewedPaper();
        return al;  
    }
       public ArrayList ReviewedPendingPaperCon() throws SQLException
    {
        ArrayList al=null;
        ConChair CC=new ConChair();
        al=CC.ReviewedPendingPaper();
        return al;  
    }
     
    
    
       public ArrayList ViewPaperCon(int Choice,String word,String Search) throws SQLException 
    {
        //papers based on int CHOICE
        //1 for papers without reviewers, 2 for papers with reviewers ,3 ALL papers
        ArrayList al=null;              
        ConChair CC=new ConChair();
        
            switch (Choice)
          {
              case 1:
              case 2:
              case 3:
                  al = CC.ViewPapers(Choice); //ALL papers  ,papers with reviewers  , papers without reviewers Based on int choice                
                  return al;
              case 4 :
                  al = CC.ViewSearchPapers(word,Search); // Search Paper 
                  return al;
              
          }  
      
    
    return al;         
    }
       
    public ArrayList ViewBidCon() throws SQLException 
    {
        //returns all papers 
        ArrayList al=null;
               
        ConChair CC=new ConChair();
        al = CC.ViewBid();
        
        return al;         
    }
    
    public void AutoAllocationCon() throws SQLException
    {
        ConChair CC=new ConChair();
        CC.AutoAllocation();
    }
    
  
       
      public ArrayList getReviewersCon() throws SQLException
      {
          ArrayList al=null;
          ConChair CC=new ConChair();
          al=CC.getReviewers();
          return al; 
      }
      
        public void UpdateBidstatusCon(String PaperName,String Reviewer) throws SQLException
         {
            ConChair CC=new ConChair();
             CC.UpdateBidstatus(PaperName,Reviewer);
          }
    
     public void  UpdatePaperReviewStatus(String PaperName,String Reviewer) throws SQLException
      {
            ConChair CC=new ConChair();
             CC.UpdatePaperReviewStatusCon(PaperName,Reviewer);
        }
     
     public void UpdatePaperStatusCon(String PaperName,int review_s) throws SQLException
     {
         ConChair CC=new ConChair();
        CC.UpdatePaperStatus(PaperName,review_s);
     }
     
      
      public void UpdatePaperCon(String PaperName,String Reviewer)
      {
        ConChair CC=new ConChair();  
           try {
               CC.UpdatePaper(PaperName,Reviewer);
               UpdateBidstatusCon(PaperName,Reviewer);
               UpdatePaperReviewStatus(PaperName,Reviewer);
           } catch (SQLException ex) {
               Logger.getLogger(ConChairController.class.getName()).log(Level.SEVERE, null, ex);
           }
          
      }
}
