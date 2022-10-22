package User;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
Super class of 
 ConChair,reviwer,systemadmin,author classes
*/

/**
  @author XDpartypooper
 */
public class UserProfile {
    String UserName;   //1
    String Password;   //2
    String Email;      //3
    String ProfileType;//4
    //1=Author , 2=ConChair, 3=Reviwer, 4=SystemAdmin
    String ID;         //5  (new users will be last user+1)
    String ProfileType_ID;//id of profile type

    public UserProfile( )
    {

    }
     
    public UserProfile(String ProfileType,String ProfileType_ID)
    {
        this.ProfileType=ProfileType;//obj to only store profile type as data
        this.ProfileType_ID=ProfileType_ID;//obj to only store profile type as data
    }
    
    public UserProfile(String name,String Password,String Email,String ProfileType,String ID)
    {
        //create account 
        this.UserName=name;
        this.Password=Password;
        this.Email=Email;
        this.ProfileType=ProfileType;
        
        this.ID=ID;
    }
     
    public String[] GetProfile()
    {
        String file[]={UserName,Password,Email,ProfileType,ID};
        return file;
    }
    
    public String ReturnProfileType()
    {
        return ProfileType;
    }
    
    public String[] ReturnProfileTypeArray()
    {
        String file[]={ProfileType,ProfileType_ID};
        return file;
        
    }
    
    
     public Boolean IDExsist(String ID) throws SQLException
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;
        boolean CHECK=false;//true=exist false=dont exsist
        
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        
        PreparedStatement mySmt = conn.prepareStatement("select ID from users WHERE ID = ? ");        

        mySmt.setString(1, ID);//User entered    
        rs = mySmt.executeQuery();
        
        if(rs.next())
        {
            CHECK=true;//if statementworks return true means it exsist
        }
        conn.close();  
        return CHECK;
    }
     
     public Boolean PTIDExsist(String ProfileType_ID) throws SQLException
    {
        java.sql.Connection conn=null;
        ResultSet rs =null;
        boolean CHECK=false;//true=exist false=dont exsist
        
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        
        PreparedStatement mySmt = conn.prepareStatement("select * from usertype WHERE ProfileType_ID = ? ");        

        mySmt.setString(1, ProfileType_ID);//User entered    
        rs = mySmt.executeQuery();
        
        if(rs.next())
        {
            CHECK=true;//if statementworks return true means it exsist
        }
        conn.close();  
        return CHECK;
    }
     
    public String GetNameDB(String ID) throws SQLException
    {
        String name=null;
        java.sql.Connection conn=null;
        ResultSet rs =null;
        boolean CHECK=false;//true=exist false=dont exsist
        
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        
        PreparedStatement mySmt = conn.prepareStatement("select * from users WHERE ID = ? ");        

        mySmt.setString(1, ID);//User entered    
        rs = mySmt.executeQuery();
        
        if(rs.next())
        {
             name = rs.getString(1);
        }   
        conn.close();  
        return name;
    }
    
    public String GetPTNameDB(String ProfileType_ID) throws SQLException
    {
        String name=null;
        java.sql.Connection conn=null;
        ResultSet rs =null;
        boolean CHECK=false;//true=exist false=dont exsist
        
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sprint","root","pass");
        
        PreparedStatement mySmt = conn.prepareStatement("select * from usertype WHERE ProfileType_ID = ? ");        
        mySmt.setString(1, ProfileType_ID);//User entered    
        rs = mySmt.executeQuery();
        
        if(rs.next())
        {
             name = rs.getString(1);
        }   
        conn.close();  
        return name;
    }
      
      


   
    //SystemAdmin
    //ConChair
    //Reviewer
    //Author
}
