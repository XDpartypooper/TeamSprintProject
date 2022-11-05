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
    int status;//false=not accecpted , true = accepted
    String Bid_status;//false=not accecpted, true = accepted
    
    public Bids()
    {}
    
    public Bids(String PaperName,String PaperID,int status)
    {
        this.PaperName=PaperName;
        this.PaperID=PaperID;
        this.status=status;
    }
    
    public Bids(String PaperName,String PaperID,String BidderID,int status)
    {
        this.PaperName=PaperName;
        this.PaperID=PaperID;
        this.BidderID=BidderID;
        this.status=status;
    }
    
       public String GetPName()
    {       
        return PaperName;
    }
    
      public String[] GetBid()
    {
        if(status==0)
        {
              Bid_status="Pending";
        }
        else if(status==1)
        {
            Bid_status="Accepted";
        } 
        else if(status==2)
        {
            Bid_status="rejected";
        }
        
        String file[]={PaperName,PaperID,Bid_status};
        return file;
    }
      
        public String[] GetCCBid()
    {
        if(status==0)
        {
              Bid_status="Pending";
        }
        else if(status==1)
        {
            Bid_status="Accepted";
        } 
        else if(status==2)
        {
            Bid_status="rejected";
        }
        
        String file[]={PaperName,BidderID,Bid_status};
        return file;
    }
    
}
