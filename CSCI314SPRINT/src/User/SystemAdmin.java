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
       public void GotoAccCre() throws SQLException
    {
         new ProfileTypeView().setVisible(true);
         //DONT NEED ALREADY but keep first
    }
    
    public void GotoAccView() throws SQLException
    {
         new ViewUsers().setVisible(true);
    }
    
    public void GoProfView() throws SQLException
    {
      //   new ViewProfiles().setVisible(true);
      //go to the gui for userprofile 
    }
}
