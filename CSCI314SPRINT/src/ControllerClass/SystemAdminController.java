/*
    Controller class for Reviwer
 */
package ControllerClass;

import User.UserAccount;
import User.UserProfile;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author XDpartypooper
 */
public class SystemAdminController {
    
    public SystemAdminController()
    {
        
    }
    
                
    public void AccCreCon(String Username,String Password,String Email,String ProfileType) throws SQLException
    {
        UserAccount UA=new UserAccount();//make user obj       
        if((Username.length()<20) && (Password.length() <20))//checks if user name and password is less then 20 varchar
        {
        UA.CreateAccount( Username, Password, Email, ProfileType);
        }
        else
        {
            throw new SQLException();
        }
  
    }

    public ArrayList ViewAccCon() throws SQLException
    {
        UserAccount UA=new UserAccount();
        ArrayList al = UA.ViewAccount();
        return al;
    }
    
    public ArrayList<UserProfile> GetProfileTypeCon() throws SQLException
    {
        UserAccount UP=new UserAccount();       
        ArrayList<UserProfile> al = UP.DBProfileType();
        return al;
    }
    
    
}
