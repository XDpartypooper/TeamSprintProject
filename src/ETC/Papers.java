/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ETC;


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
    String Date;
    
    public Papers()
    {
    }
    
    public Papers(String PaperName,String PaperID,String Author,String Co_Author,String ALReviewerID,String Date)
    {
        this.PaperName=PaperName;
        this.Author=Author;
        this.PaperID=PaperID;
        this.Co_Author=Co_Author;
        this.ALReviewerID=ALReviewerID;
        this.Date=Date;
    }
    
    public String[] GetPaper()
    {
        String file[]={PaperName,PaperID,Author,Co_Author,ALReviewerID,Date};
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
    
      
 
}
