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
        UA.CreateAccount( Username, Password, Email, ProfileType);
  
    }

    public ArrayList ViewAccCon() throws SQLException
    {
        UserAccount UA=new UserAccount();
        ArrayList al = UA.ViewAccount();
        return al;
    }
}
