/*
 This is a sub class of User
 */
package User;

import Gui.AccountCreate;
import Gui.ViewUsers;
import java.sql.SQLException;

/**
 * @author XDpartypooper
 */
public class SystemAdmin extends UserProfile{
    
    public SystemAdmin()
    {
        
    }
       public void GotoAccCre() throws SQLException
    {
         new AccountCreate().setVisible(true);
    }
    
    public void GotoAccView() throws SQLException
    {
         new ViewUsers().setVisible(true);
    }
    
    public void GotoProfCre() throws SQLException
    {
      //   new ProfileUsers().setVisible(true);
      //go to the gui for userprofile creation
    }
}
