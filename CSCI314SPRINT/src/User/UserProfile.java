package User;

/*
 ConChair,reviwer,systemadmin,author

 */

/**
  @author XDpartypooper
 */
public class UserProfile {
    String UserName;
    String Password;
    String Email;
    String ProfileType;
    //1=Author , 2=ConChair, 3=Reviwer, 4=SystemAdmin
    String ID;
    
    public UserProfile()
    {
        
    }
    public UserProfile(String name,String Password,String Email,String ProfileType,String ID)
    {
        //create account 
        this.UserName=name;
        this.Password=Password;
        this.Email=Email;
        this.ProfileType=ProfileType;
        this.ID=ID;
    }
    
   
    //SystemAdmin
    //ConChair
    //Reviewer
    //Author
}
