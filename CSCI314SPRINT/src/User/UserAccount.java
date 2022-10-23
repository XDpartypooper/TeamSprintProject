package User;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
Class that contains fuction for system admin to use 
only system admin has access to this 
ITS OWN IDEPENDENT CLASS
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
    String ID;         //5  (new users will be last user+1)
    String ProfileType_ID;
    
    public UserAccount()
    {
        //default
    }
    
     public UserAccount(String ProfileType,String ProfileType_ID)
    {
        this.ProfileType=ProfileType;//obj to only store profile type as data
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
    public ArrayList<UserProfile> ViewAccount() throws SQLException
    {
        
        java.sql.Connection conn=null;
        ResultSet rs =null;
        boolean Check=false;
 
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("SELECT * FROM users order by ID asc");  
        
        rs = mySmt.executeQuery();
        
        ArrayList<UserProfile> al = new ArrayList<UserProfile>();
        
         while(rs.next()) //find works
         {
                UserProfile UP = new UserProfile(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
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
    
    public ArrayList<UserProfile> SearchAccount(String word) throws SQLException
    {
        
        java.sql.Connection conn=null;
        ResultSet rs =null;

        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        PreparedStatement mySmt = conn.prepareStatement("SELECT * FROM users WHERE UserName LIKE ? order by ID asc");  
        mySmt.setString(1,'%'+word+'%');//User entered    
        rs = mySmt.executeQuery();
        
        ArrayList<UserProfile> al = new ArrayList<UserProfile>();
        
         while(rs.next()) //find works
         {
                UserProfile UP = new UserProfile(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                al.add(UP);
                       
         }

         conn.close();
         return al;
    }
   
    //update?
    public void UpdateAccount(String UserName,String Password,String Email,String ProfileType,String ID) throws SQLException
    {
        //first thing Check if ID exsist
        //check if user,Email exsist already
        java.sql.Connection conn=null;
        ResultSet rs =null;
        boolean CHECK=true;//false=exsist ,true=doesnt exsist
        
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        
        PreparedStatement mySmt = conn.prepareStatement("SELECT * FROM users WHERE UserName = ? and ID!=?");        
        mySmt.setString(1, UserName);//User entered    
        mySmt.setString(2, ID);
        rs = mySmt.executeQuery();
        if(rs.next()) // CONNECT to database and check if USERNAME EXSIST ALREADY
        {
            CHECK=false;
        }          
        
        mySmt = conn.prepareStatement("SELECT * FROM users WHERE Email = ? and ID!=?"); 
        mySmt.setString(1, Email);//email entered
        mySmt.setString(2, ID);
        rs = mySmt.executeQuery();
        if(rs.next()) // CONNECT to database and check if USERNAME EXSIST ALREADY
        {
            CHECK=false;           
        }
        
        if(CHECK==true)//if username and email doesnt exsist - procced to update acc
        {
            mySmt = conn.prepareStatement("update users set UserName = ? ,Password = ?, Email = ? ,ProfileType = ? where ID=?");    
            mySmt.setString(1, UserName);//User entered
            mySmt.setString(2, Password);//generated password
            mySmt.setString(3, Email);//Email entered
            mySmt.setString(4, ProfileType);//Profile type
            mySmt.setString(5, ID);//ID

            mySmt.executeUpdate();
            //update values
            conn.close();  
        }   
    }
    
    //delete
    public boolean DeleteAccount(String ID) throws SQLException
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;
        boolean check=false;

       conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
       PreparedStatement mySmt = conn.prepareStatement("DELETE FROM users WHERE ID=?");  
       mySmt.setString(1, ID);//User entered
         
       check = mySmt.execute();
       conn.close();  
       return check;
    }
    
    public ArrayList<UserProfile> DBProfileType() throws SQLException
    { 
        java.sql.Connection conn=null;
        ResultSet rs =null;
        ArrayList<UserProfile> al = new ArrayList<UserProfile>();
 
       conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
       PreparedStatement mySmt = conn.prepareStatement(" SELECT * FROM usertype order by ProfileType_ID asc");  
        
       rs = mySmt.executeQuery();
       while(rs.next()) //find works
       { 
            UserProfile UP = new UserProfile(rs.getString(1),rs.getString(2));
            al.add(UP);
       }
       conn.close();  
        return al;
    }  
    
    public void CreateProfileType(String ProfileType) throws SQLException
    {
       java.sql.Connection conn=null;
        ResultSet rs =null;
        boolean CHECK=true;//default means fail
        
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        
        PreparedStatement mySmt = conn.prepareStatement("SELECT * FROM usertype WHERE ProfileType = ? ");        
        mySmt.setString(1, ProfileType);//ProfileType entered    
        rs = mySmt.executeQuery();
        if(rs.next()) // CONNECT to database and check if ProfileType EXSIST ALREADY
        {
            CHECK=false;
        }          
        

 
        if(CHECK==true)//if ProfileType doesnt exsist - procced to create PT
        {
                mySmt = conn.prepareStatement("SELECT * FROM usertype order by ProfileType_ID desc");           
                //Get last ID from DB
                rs = mySmt.executeQuery();
                if(rs.next())// insert data
                {
                    ProfileType_ID = rs.getString(2);//get last ID
                    int idNum=Integer.parseInt(ProfileType_ID);//convert last id from string to int
                    idNum++;//LAST ID + 1
                    ProfileType_ID=String.format("%d",idNum);
                }
                else
                {
                    throw new SQLException();
                }    
             mySmt = conn.prepareStatement("INSERT INTO usertype (ProfileType,ProfileType_ID) VALUES (?, ?);");         
             mySmt.setString(1, ProfileType);//Profile type
             mySmt.setString(2, ProfileType_ID);//ID
             
             mySmt.executeUpdate();
                
             JFrame f=new JFrame();
             JOptionPane.showMessageDialog(f,"New Profile Type succesfully created.");      
         }
         else
         {
             JOptionPane.showMessageDialog(null,"Profile Type already exsist","ERROR",JOptionPane.ERROR_MESSAGE);
             throw new SQLException();
         }        
       conn.close();  
    }
    
    public void UpdatePT(String ProfileType,String ProfileType_ID) throws SQLException
    {
        //first thing Check if ID exsist
        //check if user,Email exsist already
        java.sql.Connection conn=null;
        ResultSet rs =null;
        boolean CHECK=true;//false=exsist ,true=doesnt exsist
        
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        
        PreparedStatement mySmt = conn.prepareStatement("SELECT * FROM usertype WHERE ProfileType = ?");        
        mySmt.setString(1, ProfileType);//ProfileType entered    
        rs = mySmt.executeQuery();
        if(rs.next()) // CONNECT to database and check if ProfileType EXSIST ALREADY
        {
            CHECK=false;//exsist
        }          
           
        
        if(CHECK==true)//if username and email doesnt exsist - procced to update acc
        {
            mySmt = conn.prepareStatement("update usertype set ProfileType = ? where ProfileType_ID=?");    
            mySmt.setString(1, ProfileType);//Profile type 
            mySmt.setString(2, ProfileType_ID);//Profile type 

            mySmt.executeUpdate();
            //update values
            conn.close();  
        }   
    }
    
    public boolean DeletePT(String ProfileType_ID) throws SQLException
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;
        boolean check=false;

       conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
       PreparedStatement mySmt = conn.prepareStatement("DELETE FROM usertype WHERE ProfileType_ID=?");  
       mySmt.setString(1, ProfileType_ID);//User entered
         
       check = mySmt.execute();
       conn.close();  
       return check;
    }
}
