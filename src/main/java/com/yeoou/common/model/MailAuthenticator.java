package com.yeoou.common.model;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * 邮件认证模型
 * @author kensin
 *
 */
public class MailAuthenticator extends Authenticator
{
	 String userName=null;   
	 String password=null;   
	        
	    public MailAuthenticator()
	    {   
	    }   
	    public MailAuthenticator(String username, String password) 
	    {    
	        this.userName = username;    
	        this.password = password;    
	    }    
	    protected PasswordAuthentication getPasswordAuthentication()
	    {   
	        return new PasswordAuthentication(userName, password);   
	    }  
}
