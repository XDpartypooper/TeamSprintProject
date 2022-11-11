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
    int Review_status;
    String Status_word;
    String Rating_word;
    
    public Reviews()
    {
    }
    
    public Reviews(String PaperID, String ReviewerID,String Review,int Rating,int Review_status)
    {
        this.PaperID=PaperID;
        this.ReviewerID=ReviewerID;
        this.Review=Review;
        this.Rating=Rating;// 1 2 3 4 5 
        this.Review_status=Review_status;
    }
     //paper name,Author,co author, review , Rating null = pending
    
        public Reviews(String PaperID, String AuthorID,String Co_Author,String Review,int Rating,int Review_status)
    {
        //paper name, reviewer(id), review , Rating null =0
        this.PaperID=PaperID;
        this.AuthorID=AuthorID;
        this.Co_Author=Co_Author;
        this.Review=Review;
        this.Rating=Rating;// 1 2 3 4 5 
        this.Review_status=Review_status;
        // 
    }
       public Reviews(String PaperID, String ReviewerID,String Review,int Rating)
    {
        //paper name, reviewer(id), review , Rating null =0
        this.PaperID=PaperID;
        this.ReviewerID=ReviewerID;
        this.Review=Review;
        this.Rating=Rating;// 1 2 3 4 5 
        // 
    }
    public String[] GetReviewDATA2()//for reviewer
    {
            convertReviewStatus();
            String file[]={PaperID,AuthorID,Co_Author,String.valueOf(Rating),Status_word};
            return file;
    }
    
      public String[] GetReviewDATA3()//for Con Chair
    {
            convertReviewStatus();
            String file[]={PaperID,ReviewerID,Status_word};
            return file;
    }
 
    
     public String[] GetReviewDATA()//for author
    { 
        ConvertRating();
        convertReviewStatus();
        String file[]={PaperID,ReviewerID,Rating_word,Status_word};
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
    
    public int GetRating()
    {
        return Rating;
    }
    
    public void convertReviewStatus()
    {
             switch(Review_status)
             {
                 case 0:// pending
                     Status_word="Pending";
                      break;
                 case 1://accepted
                     Status_word="Accepted";
                      break;
                 case 2://rejected
                     Status_word="Rejected";
                     Rating_word="Null";
                      break;
                 default:
                     Status_word="Pending";
             }
 
            
    }
    
    public void ConvertRating()
    {
        if(Review_status < 1 || Review_status > 2 )//1 means pending ,0 means nth there , 2 means accept 
        {
            Rating_word="Pending";
        }
        else
        {
         switch(Rating)
                {
                    case -3:
                        Rating_word="-3 (strong reject)";
                    break;
                    case -2:
                        Rating_word="-2 (reject)";
                    break;
                    case -1:
                        Rating_word="-1 (weak reject)";
                    break;
                    case 0:
                        Rating_word="0 (borderline paper)";
                    break;
                    case 1:
                        Rating_word="1 (weak accept)";
                    break; 
                    case 2:
                        Rating_word="2 (Accept)";
                    break; 
                    case 3:
                        Rating_word="3 (strong accept)";
                    break; 
                    default:
                         Rating_word="Pending";
                }
        }
    }
}
