/*
    Controller class for Author
 */
package ControllerClass;

import ETC.Papers;
import User.Author;
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
    
    
    public ArrayList ViewPaperCon(String word,String ID) throws SQLException 
    {
        //returns papers depend on if word is empty
        ArrayList al=null;
               
        Author A=new Author();
        al = A.ViewPapers(word,ID);
        
        //note to convert ID to name - or can also keep the same
        return al;  
        
    }
    
    public ArrayList getAuthorsCon(String ID) throws SQLException
    {
        Author A = new Author();
        ArrayList<Author> al = A.GetAuthors(ID);         
        return al;  
    }
    
    public void SubmitPaperCOn(String Pname,String AuthorID, String CoAuthorID) throws SQLException
    {
        Author A = new Author();
        A.SubmitPaper( Pname, AuthorID, CoAuthorID);
    }
    
    public boolean CheckPID(String PID) throws SQLException
    {
        Author A = new Author();
        return A.CheckPID(PID);
    }
    public void UpdatePaperCon(String Pname,String newPname, String CoAuthorID) throws SQLException
    {
      Author A = new Author();
      A.UpdatePaper(Pname,newPname,CoAuthorID);
    }
    public boolean deletePaperCon(String Name) throws SQLException
    {
        Author A = new Author();
        return A.DeletePaper(Name);
    }
    
}
