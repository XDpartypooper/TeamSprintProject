/*
    Controller class for Author
 */
package ControllerClass;

import ETC.Papers;
import User.Author;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author XDpartypooper
 */
public class AuthorController {
    
    public void DownloadPaperCon(String PaperName,String ID) throws SQLException
    {
            Author A=new Author();
        try {
            A.DownloadPaper( PaperName, ID);
        } catch (IOException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList ReviewedPaperCon(String ID) throws SQLException
    {
        ArrayList al=null;
        Author A=new Author();
        al=A.ReviewedPaper(ID);
        return al;  
    }
    public ArrayList ReviewedDonePaperCon(String ID) throws SQLException
    {
        ArrayList al=null;
        Author A=new Author();
        al=A.ReviewedDonePaper(ID);
        return al;  
    }

    
    
    public ArrayList ViewPaperCon(String word,String ID) throws SQLException 
    {
        //returns papers depend on if word is empty
        ArrayList al=null;             
        Author A=new Author();
        al = A.ViewPapers(word,ID);
        return al;  
        
    }
    
    public ArrayList getAuthorsCon(String ID) throws SQLException
    {
        Author A = new Author();
        ArrayList<Author> al = A.GetAuthors(ID);         
        return al;  
    }
    
    //
    public void SubmitPaperCOn(String Pname,String AuthorID, String CoAuthorID,File file) throws SQLException
    {
        Author A = new Author();
        try {
            A.SubmitPaper( Pname, AuthorID, CoAuthorID, file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void UpdatePaperCon(String Pname,String newPname, String CoAuthorID,File file) throws SQLException
    {
      Author A = new Author();
        try {
            A.UpdatePaper(Pname,newPname,CoAuthorID,file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean CheckPID(String PID) throws SQLException
    {
        Author A = new Author();
        return A.CheckPID(PID);
    }

    public boolean deletePaperCon(String Name) throws SQLException
    {
        Author A = new Author();
        return A.DeletePaper(Name);
    }
    
}
