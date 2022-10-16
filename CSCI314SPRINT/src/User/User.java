package User;
import Gui.*;

/**
  @author XDpartypooper
  This is super class of all the users 
 */
public class User {
    String UserName;
    String Password;
    String Email;
    int ProfileType;
    //1=Author , 2=ConChair, 3=Reviwer, 4=SystemAdmin
    

    public User()
    {
        //default 
    }
    public User(String name,String Password,String Email,int ProfileType)
    {
        //create account 
        this.UserName=name;
        this.Password=Password;
        this.Email=Email;
        this.ProfileType=ProfileType;
    }

    //login
    public void login(String UserName,String Password)
    {
        //login check details 
        
        //redirect to main menu
        new LoginTestPage().setVisible(true);
        
    }
    
    //logout
    public void logout()
    {
       //redirect to logout menu 
    }
    
    
}
