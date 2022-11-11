/*
 This is a sub class of User
 */
package User;

import Gui.ProfileTypeView;
import Gui.ViewUsers;
import java.sql.SQLException;

/**
 * @author XDpartypooper
 */
public class SystemAdmin extends UserProfile{
    
    public SystemAdmin()
    {
        
    }
       public void GotoPTview(String name ,String ID) throws SQLException
    {
         new ProfileTypeView(name,ID).setVisible(true);
         //DONT NEED ALREADY but keep first
    }
    
    public void GotoAccView(String name,String ID) throws SQLException
    {
         new ViewUsers(name,ID).setVisible(true);
    }

}
