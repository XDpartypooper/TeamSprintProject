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
public class Reviews {
    String PaperID;//or NAME
    String ReviewerID;//or NAME
    String AuthorID;
    String Co_Author;
    String Review;
    int Rating;
    String Rating_word;
    
    public Reviews()
    {
    }
    
    public Reviews(String PaperID, String ReviewerID,String Review,int Rating)
    {
        this.PaperID=PaperID;
        this.ReviewerID=ReviewerID;
        this.Review=Review;
        this.Rating=Rating;// 1 2 3 4 5 
    }
    
       public Reviews(String PaperID, String AuthorID,String Co_Author,String Review,int Rating)
    {
        this.PaperID=PaperID;
        this.AuthorID=AuthorID;
        this.Co_Author=Co_Author;
        this.Review=Review;
        this.Rating=Rating;// 1 2 3 4 5 
        // 
    }
    public String[] GetReviewDATA2()//for reviewer
    {
            ConvertRating();
            String file[]={PaperID,AuthorID,Co_Author,Rating_word};
            return file;
    }
 
    
     public String[] GetReviewDATA()//for author
    { 
        ConvertRating();
        String file[]={PaperID,ReviewerID,Rating_word};
        return file;
    }
       public String GetReview()
    {
       return Review;
    }
    
    public String GetReviewPaperName()
    {
       return PaperID;
    }
    
    public void ConvertRating()
    {
         switch(Rating)
                {
                    case 1:
                        Rating_word="Quite bad bruh";
                    break;
                    case 2:
                        Rating_word="somewhat bad bruh";
                    break;
                    case 3:
                        Rating_word="mediocore";
                    break;
                    case 4:
                        Rating_word="pretty good";
                    break;
                    case 5:
                        Rating_word="Very good";
                    break; 
                    default:
                         Rating_word="Pending";
                }
    }
}
