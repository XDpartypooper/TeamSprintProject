package User;
import Gui.ReviewerMenu;
import Gui.*;
import java.sql.*;


/**
  @author XDpartypooper
  Use class that has login and log out
 */
public class User {
    String UserName;   //1
    String Password;   //2
    String Email;      //3
    String ProfileType;//4
    //1=Author , 2=ConChair, 3=Reviwer, 4=SystemAdmin
    String ID;         //5

    public User()
    {
        //default 
    }
    public User(String name,String Password,String Email,String ProfileType)
    {
        //create account 
        this.UserName=name;
        this.Password=Password;
        this.Email=Email;
        this.ProfileType=ProfileType;
    }

    //login 
    public void login(String UserName,String Password) throws SQLException, ClassNotFoundException
    {
        //login check details 
        java.sql.Connection conn=null;
        ResultSet rs =null;
     
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("SELECT * FROM users WHERE userName = ? and password = ?");  
        mySmt.setString(1, UserName);
        mySmt.setString(2, Password);
 
            rs = mySmt.executeQuery();
            if(rs.next()) // log in works
            {            
                System.out.println("user:"+rs.getString(1));//username entered for testing
                System.out.println("Password:"+rs.getString(2));//Password entered for testing
                
               ProfileType = rs.getString(4);            
                   //redirect to main menu
                   //1=Author , 2=ConChair, 3=Reviwer, 4=SystemAdmin

                   switch (ProfileType)
                    {
                    case "1":
                       //author
                        new AuthorMenu().setVisible(true);
                    break;
                    case "2":
                       //ConChair
                       new ConChairMenu().setVisible(true);
                    break;
                    case "3":
                        //Reviwer
                        new ReviewerMenu().setVisible(true);
                    break;
                    case "4":
                        //system admin
                        new SystemAdminMenu().setVisible(true);
                    break;               
                    
                    }
                    
                }
                else
                {
                    throw new SQLException();
                }
            
                conn.close();
        }
   
    
    
    //logout
    public void logout()
    {
       //redirect to logout menu 
       
       new LoginGui().setVisible(true);
    }
    
    
}
