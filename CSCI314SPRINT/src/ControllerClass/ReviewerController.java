/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerClass;

import User.Reviewer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author XDpartypooper
 */
public class ReviewerController {
    
        public String WorkLoadGetCon(String ID)
    {
            String WL=null;
            Reviewer R=new Reviewer();
            try {
                WL=R.WorkLoadGet(ID);
            } catch (SQLException ex) {
                Logger.getLogger(ReviewerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        return WL;
    }
        
        public void EditWorkLoadCon(String WL,String ID) throws SQLException
    {
        Reviewer R=new Reviewer();
        R.EditWorkLoad(WL,ID);
    }
        
        public ArrayList ViewPaperCon(String ID) throws SQLException 
    {
        //returns all papers 
        ArrayList al=null;
               
        Reviewer R=new Reviewer();
        al = R.ViewPapers(ID);
        
        return al;  
        
    }
    
}
