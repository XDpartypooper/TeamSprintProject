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
public class Bids {
     String PaperName;//Paper name / ID
    String PaperID;//Paper name / ID
    String BidderID;//bidder name /ID
    Boolean status;//false=not accecpted , true = accepted
    String Bid_status;//false=not accecpted, true = accepted
    
    public Bids()
    {}
    
    public Bids(String PaperName,String PaperID,Boolean status)
    {
        this.PaperName=PaperName;
        this.PaperID=PaperID;
        this.status=status;
    }
    
    
      public String[] GetBid()
    {
        if(status==true)
        {
            Bid_status="Accepted";
        } 
        else 
        {
            Bid_status="Pending";
        }    
        String file[]={PaperName,PaperID,Bid_status};
        return file;
    }
    
}
