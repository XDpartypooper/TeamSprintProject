package User;

/*
Class that contains fuction for system admin to use 
only system admin has access to this 
 */

/**
 @author XDpartypooper
 */
public class UserAccount {
    
    public UserAccount()
    {
        //default
    }
           
    void CreateAccount(String Name,String Password,String Email,int ProfileType)
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
