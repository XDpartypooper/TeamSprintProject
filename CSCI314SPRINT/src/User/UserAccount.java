package User;

import Gui.*;
import java.awt.List;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
    
    
    public void GotoAccCre()
    {
         new AccountCreate().setVisible(true);
    }
    
    public void GotoAccView() throws SQLException
    {
         new ViewUsers().setVisible(true);
    }
    
    
    public void CreateAccount(String UserName,String Password,String Email,String ProfileType) throws SQLException
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;
        boolean CHECK=true;//default means fail
        
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        
        PreparedStatement mySmt = conn.prepareStatement("SELECT * FROM users WHERE UserName = ? ");        
        mySmt.setString(1, UserName);//User entered    
        rs = mySmt.executeQuery();
        if(rs.next()) // CONNECT to database and check if USERNAME EXSIST ALREADY
        {
            CHECK=false;
        }          
        
        mySmt = conn.prepareStatement("SELECT * FROM users WHERE Email = ?"); 
        mySmt.setString(1, Email);//email entered
        rs = mySmt.executeQuery();
        if(rs.next()) // CONNECT to database and check if USERNAME EXSIST ALREADY
        {
                CHECK=false;
                //if username+email exsist already
        }
 
        if(CHECK==true)//if username and email doesnt exsist - procced to create account
        {
                mySmt = conn.prepareStatement("select ID from users order by ID desc");           
                //Get last ID from DB
                rs = mySmt.executeQuery();
                if(rs.next())// insert data
                {
                    ID = rs.getString(1);//get last ID
                    int idNum=Integer.parseInt(ID);//convert last id from string to int
                    idNum++;//LAST ID + 1
                    ID=String.format("%03d",idNum);
                }
                else
                {
                    throw new SQLException();
                }
                
                //change string profle type to 
                switch (ProfileType)
                {
                    case "Author":
                        ProfileType="1";
                    break;
                    case "Confernce Chair":
                        ProfileType="2";
                    break;
                    case "Reviwer":
                        ProfileType="3";
                    break;
                    case "System Admin":
                        ProfileType="4";
                    break;
                }
                        
             mySmt = conn.prepareStatement("INSERT INTO Users (UserName,Password,Email,ProfileType,ID) VALUES (?, ?, ?, ?, ?);");         
             mySmt.setString(1, UserName);//User entered
             mySmt.setString(2, Password);//generated password
             mySmt.setString(3, Email);//Email entered
             mySmt.setString(4, ProfileType);//Profile type
             mySmt.setString(5, ID);//ID
             
                mySmt.executeUpdate();
                
                JFrame f=new JFrame();
                JOptionPane.showMessageDialog(f,"Account succesfully created.");
              
         }
         else
         {
             JOptionPane.showMessageDialog(null,"Username or Email already exsist","ERROR",JOptionPane.ERROR_MESSAGE);
             throw new SQLException();
         }        
       conn.close();  
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
    public ArrayList<User> ViewAccount() throws SQLException
    {
        
        java.sql.Connection conn=null;
        ResultSet rs =null;
        boolean Check=false;
 
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("SELECT * FROM users order by ID asc");  
        
        rs = mySmt.executeQuery();
        
        ArrayList<User> al = new ArrayList<User>();
        
         while(rs.next()) //find works
         {
                ProfileType=rs.getString(4);
     
                if("1".equals(ProfileType))
                {
                    ProfileType="Author";
                }
                if("2".equals(ProfileType))
                {
                    ProfileType="Confernce Chair";
                }
                if("3".equals(ProfileType))
                {
                    ProfileType="Reviwer";
                }
                if("4".equals(ProfileType))
                {
                    ProfileType="System Admin";
                }
                
                User UP = new User(rs.getString(1),rs.getString(2),rs.getString(3),ProfileType,rs.getString(5));
                al.add(UP);
                       
            Check=true;
         }
         
         if(Check==false)
         {
             throw new SQLException();
         }   
         conn.close();
        return al;
    }
   
    //update
    void UpdateAccount()
    {
    }
  
    
    
}
