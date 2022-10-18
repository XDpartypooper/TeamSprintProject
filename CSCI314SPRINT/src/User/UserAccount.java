package User;

import Gui.*;
import java.util.Random;

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
    
    
    public void GotoAccMan()
    {
         new AccountCreate().setVisible(true);
    }
    
    public void CreateAccount(String Name,String Password,String Email,String ProfileType)
    {
        //name, password, email, ProfileType
        //User newUser= new User(Name,Password,Email,ProfileType);// create new obj
    }
    
    //randomly make password
    public String passwordGenerator()
    {
        String PasswordGen;
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
         Random rand = new Random(); 
         
         for (int i = 0; i < 10; i++)//make a password of 10 char 
        {
            int randomIndex = rand.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
         return sb.toString();
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
