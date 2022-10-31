package User;
import Gui.ReviewerMenu;
import Gui.*;
import java.sql.*;
import javax.swing.JOptionPane;


/**
  @author XDpartypooper
  Use class that has login and log out
  ITS OWN IDEPENDENT CLASS
 */
public class User {
    String UserName;   //1
    String Password;   //2
    String Email;      //3
    String ProfileType;//4
    String ID;         //5

    public User()
    {
        //default 
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
                System.out.println("user ID"+rs.getString(5));//Password entered for testing
                UserName = rs.getString(1);//username entered for testing
                ID = rs.getString(5);//pass on the ID of user to the menus
                
               ProfileType = rs.getString(4);            
                   //redirect to main menu
                   switch (ProfileType)
                    {
                    case "Author":
                       //author
                        new AuthorMenu(UserName,ID).setVisible(true);
                    break;
                    case "Conference Chair":
                       //ConChair
                       new ConChairMenu(UserName,ID).setVisible(true);
                    break;
                    case "Reviewer":
                        //Reviwer
                        new ReviewerMenu(UserName,ID).setVisible(true);
                    break;
                    case "System Admin":
                        //system admin
                        new SystemAdminMenu(UserName,ID).setVisible(true);
                    break;                                   
                    default:
                        JOptionPane.showMessageDialog(null,"Profile Type Menu Doesnt exsist","ERROR",JOptionPane.ERROR_MESSAGE);
                        throw new ClassNotFoundException();
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
