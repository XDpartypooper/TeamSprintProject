package User;
/*
Super class of 
 ConChair,reviwer,systemadmin,author classes
*/

/**
  @author XDpartypooper
 */
public class UserProfile {
    String UserName;   //1
    String Password;   //2
    String Email;      //3
    String ProfileType;//4
    //1=Author , 2=ConChair, 3=Reviwer, 4=SystemAdmin
    String ID;         //5  (new users will be last user+1)
    
    public UserProfile( )
    {

    }
    
    public UserProfile(String ProfileType)
    {
        this.ProfileType=ProfileType;//obj to only store profile type as data
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
     
    public String[] GetProfile()
    {
        String file[]={UserName,Password,Email,ProfileType,ID};
        return file;
    }
    
    public String ReturnProfileType()
    {
        return ProfileType;
    }
    
     public void con()
    {
     
    }


   
    //SystemAdmin
    //ConChair
    //Reviewer
    //Author
}
