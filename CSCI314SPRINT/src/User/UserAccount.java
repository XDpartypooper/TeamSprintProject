package User;

/*
Class that contains fuction for system admin to use 
only system admin has access to this 
 */

/**
 @author XDpartypooper
 */
public class UserAccount {
    String UserName;   //1
    String Password;   //2
    String Email;      //3
    String ProfileType;//4
    //1=Author , 2=ConChair, 3=Reviwer, 4=SystemAdmin
    String ID;         //5  ( will be last user+1)
    
    public UserAccount()
    {
        //default
    }
           
    void CreateAccount(String Name,String Password,String Email,String ProfileType)
    {
        //name, password, email, ProfileType
        User newUser= new User(Name,Password,Email,ProfileType);// create new obj
    }
    
    //randomly make password
    void passwordGenerator()
    {
        
    }
    
    //view
    void ViewAccount()
    {
    }
   
    //update
    void UpdateAccount()
    {
    }
  
    
    
}
