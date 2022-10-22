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
    
     
    public void UpdateAccountCon(String Username,String Password,String Email,String ProfileType,String ID) throws SQLException
    {
        UserAccount UP=new UserAccount();      
        UP.UpdateAccount(Username,Password,Email,ProfileType,ID);
    }
    
    public boolean IDExsistCon(String ID) throws SQLException
    {
        UserProfile UP=new UserProfile();
        boolean IDex = UP.IDExsist(ID);
        return IDex;
    }
    public boolean PTIDExsistCon(String ProfileType_ID) throws SQLException 
    {
        UserProfile UP=new UserProfile();
        boolean PTIDex = UP.PTIDExsist(ProfileType_ID);
        return PTIDex;
    }
    
     public String GetNameCon(String ID) throws SQLException
    {
        UserProfile UP=new UserProfile();
        String Name = UP.GetNameDB(ID);
        return Name;    
    }
      public String GetPTNameDBCon(String ProfileType_ID) throws SQLException
    {
        UserProfile UP=new UserProfile();
        String Name = UP.GetPTNameDB(ProfileType_ID);
        return Name;    
    }
     
     
     public boolean DelAccCon(String ID) throws SQLException
    {
        UserAccount UA=new UserAccount();
        boolean DelAcc = UA.DeleteAccount(ID);
        return DelAcc;
        
    }
     
     public void NewProTypeCon(String ProfileType) throws SQLException
     {
        UserAccount UA=new UserAccount(); 
        UA.CreateProfileType(ProfileType);

     } 
     
      public void UpdatePTCon(String ProfileType,String ProfileType_ID) throws SQLException
    {
        UserAccount UP=new UserAccount();      
        UP.UpdatePT(ProfileType,ProfileType_ID);
    }
       public void DeletePTCon(String ProfileType_ID) throws SQLException
    {
        UserAccount UP=new UserAccount();      
        UP.DeletePT(ProfileType_ID);
    }
       
                         
}
