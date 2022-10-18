/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerClass;

import User.*;
import java.sql.SQLException;

/**
 *
 * @author XDpartypooper
 */
public class LoginController {
    
    public LoginController()
    {
        //default
    }
    
    public void LoginCon(String Username,String Password) throws SQLException, ClassNotFoundException
    {
        User U=new User();//make user obj
        U.login(Username, Password); 
    }
     
}
