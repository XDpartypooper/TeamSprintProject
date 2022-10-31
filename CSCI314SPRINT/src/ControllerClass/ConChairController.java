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
   
    
       public ArrayList ViewPaperCon(int Choice,String word,String Search) throws SQLException 
    {
        //papers based on int CHOICE
        //1 for papers without reviewers, 2 for papers with reviewers ,3 ALL papers
        ArrayList al=null;              
        ConChair CC=new ConChair();
        

            switch (Choice)
          {
              case 1:
                  al = CC.ViewPapers(); //ALL papers                  
                  return al;
              case 2:
                  al = CC.ViewAPapers(); // papers with reviewers 
                  return al;
              case 3:
                  al = CC.ViewNAPapers(); // papers without reviewers,  
                  return al;
              case 4 :
                  al = CC.ViewSearchPapers(word,Search); // Search Paper 
                  return al;
              case 5 :
                  al = CC.ViewSearchPapers(word,Search); // Search Paper 
                  return al; 
              
          }  
      
    
    return al;         
    }
       
      public ArrayList getReviewersCon() throws SQLException
      {
          ArrayList al=null;
          ConChair CC=new ConChair();
          al=CC.getReviewers();
          return al; 
      }
      
      public void UpdatePaperCon(String PaperName,String Reviewer)
      {
        ConChair CC=new ConChair();  
           try {
               CC.UpdatePaper(PaperName,Reviewer);
           } catch (SQLException ex) {
               Logger.getLogger(ConChairController.class.getName()).log(Level.SEVERE, null, ex);
           }
          
      }
}
